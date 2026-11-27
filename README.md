# ReviewVault

ReviewVault — backend для личной медиатеки, оценок и рецензий на фильмы, сериалы, аниме, комиксы, книги, музыку и игры.

## Сервисы и библиотеки

| Модуль | Текущее состояние |
| --- | --- |
| `services:eureka` | Регистрация сервисов и поиск их адресов через Spring Cloud Netflix Eureka. |
| `services:gateway` | Spring Cloud Gateway MVC, проверка JWT Keycloak для `/api/v1/**` и проксирование Grafana через `/grafana/`. |
| `services:media` | Каталог произведений и групп, базовые операции с людьми и компаниями, публикация событий MediaItem в Kafka. |
| `services:profile` | API профилей. `GET /api/v1/profiles/me` находит или создаёт профиль по JWT. Изменения профиля публикуются в Kafka. |
| `services:review` | Миграции и сервисный слой числовых оценок, шкал, пользовательских предпочтений и описаний оценок. HTTP API оценок и текстовых рецензий предстоит реализовать. |
| `services:library` | Модель папок и их содержимого, сервисный слой папок пользователя. HTTP API медиатеки предстоит реализовать. |
| `libraries:contracts` | Версионированные контракты событий и общие Java-типы. |
| `libraries:replica` | JPA-сущности, репозитории, Kafka-consumer’ы и миграции реплик MediaItem, MediaGroup и профиля. |
| `libraries:app-config` | Общие фрагменты конфигурации Actuator, PostgreSQL, Kafka, Keycloak и Eureka. Сервисы подключают нужные файлы через `spring.config.import`. |

## Медиа, оценки и события

В каталоге `media_item` представляет конкретное произведение, а `media_group` объединяет произведения, например в серию или франшизу. Модель каталога также включает категории, форматы, жанры, альтернативные названия, переводы, людей и компании с их ролями, внешние ссылки и идентификаторы. Миграции каталога находятся в [`services/media/src/main/resources/db/migration`](services/media/src/main/resources/db/migration).

В `review` пользователь выбирает числовую шкалу и может задавать описания для её значений или диапазонов. Оценка относится ровно к одному MediaItem или MediaGroup. Сервис сохраняет введённое и нормализованное значения. Текстовая рецензия не нужна, чтобы поставить оценку.

| Топик Kafka по умолчанию | Источник и получатели |
| --- | --- |
| `reviewvault.media-item-events.v1` | `media` публикует создание, обновление и удаление MediaItem. `review` и `library` обновляют свои реплики. |
| `reviewvault.user-profile-events.v1` | `profile` публикует создание, обновление и удаление профиля. `review` и `library` обновляют свои реплики. |
| `reviewvault.media-group-events.v1` | Реплики и consumer’ы в `review` и `library` подключены. Публикацию событий MediaGroup предстоит добавить в `media`. |

Публикация существующих событий запускается после коммита транзакции. У `review` и `library` отдельные группы Kafka для каждого потока событий. При запуске Flyway сначала выполняет миграции подключённых реплик с отдельными таблицами истории, затем миграции сервиса. SQL для реплик лежит в [`libraries/replica/src/main/resources/db/replica`](libraries/replica/src/main/resources/db/replica).

## Локальный запуск

Нужны JDK 26, Docker с Compose и Gradle Wrapper из репозитория. Выполняй команды из корня проекта.

### 1. Инфраструктура

```powershell
if (-not (Test-Path infrastructure/.env)) { Copy-Item infrastructure/.env.example infrastructure/.env }
# Сеть создаётся один раз и используется всеми тремя Compose-проектами.
docker network create reviewvault-net
docker compose --env-file infrastructure/.env -f infrastructure/docker-compose.infra.yaml up -d
```

В `infrastructure/.env` хранятся локальные настройки. Файл исключён из Git. Значения из [`.env.example`](infrastructure/.env.example) предназначены для разработки. Если меняешь пароли БД перед первым запуском, согласуй их с [SQL инициализации PostgreSQL](infrastructure/postgres/init/00-create-databases.sql). PostgreSQL запускает этот SQL при инициализации пустого каталога данных. [Скрипт Keycloak](infrastructure/keycloak/init.sh) настраивает realm, тестового пользователя и клиент `postman`.

Порты по умолчанию: PostgreSQL — `5432`, Keycloak — `8090`, Kafka — `9092` с хоста и `kafka:19092` внутри Docker, Kafka REST Proxy — `8088`.

### 2. Сервисы

В Windows используй PowerShell.

```powershell
.\infrastructure\build-all.ps1
```

Для Bash есть отдельный скрипт.

```bash
bash infrastructure/build-all.sh
```

Скрипты собирают библиотеки `contracts` и `replica` и Boot JAR всех шести сервисов. Затем они запускают [`docker-compose.services.yaml`](infrastructure/docker-compose.services.yaml). Хостовые порты по умолчанию: Eureka — `8761`, Gateway — `8080`, Media — `8081`, Profile — `8082`, Review — `8083`, Library — `8084`.

Для запуска приложений из IDE подними инфраструктуру отдельно. Передай в конфигурацию запуска нужные переменные из `infrastructure/.env`, включая `KEYCLOAK_PORT` и `KEYCLOAK_REALM` для приложений с JWT. При необходимости укажи Eureka на `http://localhost:8761/eureka/` и переопредели `SPRING_SECURITY_OAUTH2_RESOURCESERVER_JWT_JWK_SET_URI` на `http://localhost:8090/realms/reviewvault/protocol/openid-connect/certs`. Общий конфиг использует Docker-адрес `keycloak:8080` для получения ключей JWT.

### 3. Мониторинг

```powershell
docker compose -f infrastructure/docker-compose.monitoring.yaml up -d
```

[Grafana](http://localhost:8080/grafana/) доступна через Gateway. Compose настраивает источники **Prometheus** (`http://prometheus:9090`) и **Loki** (`http://loki:3100`) внутри общей сети. Prometheus опрашивает `/actuator/prometheus` у шести приложений. Grafana Alloy собирает логи Docker и передаёт их в Loki.

- В **Explore → Prometheus** запрос `up{job="reviewvault"}` показывает доступность целей. При работающем стеке ожидаются шесть значений `1`. Ошибки сбора видны на [странице Targets](http://localhost:9090/targets).
- В **Explore → Loki** запрос `{container=~"reviewvault-.+"}` показывает логи контейнеров ReviewVault за выбранный период.
- Общий конфиг приложений добавляет к метрикам метку `application` и включает гистограммы HTTP-запросов. Для JVM можно импортировать [Micrometer Dashboard (4701)](https://grafana.com/grafana/dashboards/4701-jvm-micrometer/) и выбрать источник Prometheus.

Конфиги находятся в [`infrastructure/prometheus`](infrastructure/prometheus) и [`infrastructure/grafana`](infrastructure/grafana).

## API через Postman

На вкладке **Authorization** коллекции выбери **OAuth 2.0** и настрой **Configure New Token** для локального Keycloak.

| Поле | Значение |
| --- | --- |
| Grant Type | Authorization Code (With PKCE) |
| Callback URL | `https://oauth.pstmn.io/v1/callback` |
| Auth URL | `http://localhost:8090/realms/reviewvault/protocol/openid-connect/auth` |
| Access Token URL | `http://localhost:8090/realms/reviewvault/protocol/openid-connect/token` |
| Client ID | `postman` |
| Client Secret | Пусто |
| Code Challenge Method | `SHA-256` |
| Scope | `openid` |
| Client Authentication | Send client credentials in body |

Нажми **Get New Access Token**, войди с `KEYCLOAK_USERNAME` и `KEYCLOAK_DEMO_PASSWORD` из локального `.env` и выбери **Use Token**. Если Postman подставляет `/v1/browser-callback`, выключи **Authorize using browser** и восстанови callback из таблицы.

Для первой проверки отправь `GET http://localhost:8080/api/v1/profiles/me` с Bearer-токеном. Через Gateway также доступны маршруты каталога `/api/v1/items/**` и `/api/v1/groups/**`. Контроллеры Person и Company доступны напрямую в `media` на порту `8081`. Маршруты Gateway для `/api/v1/reviews/**` и `/api/v1/libraries/**` уже объявлены и ждут контроллеров.

## Тесты

```powershell
.\gradlew.bat test
```

В `media` и `review` есть интеграционные тесты с Embedded Kafka. Тестам с JPA нужна доступная PostgreSQL с конфигурацией тестового модуля. Для запуска одной группы тестов используй задачу модуля, например `:services:review:test`.

## Следующие шаги

1. **Открыть API оценок для MediaItem.** Добавить получение шкал и настройку предпочтения и описаний пользователя, затем создание, чтение, изменение и удаление оценки. Проверять владельца, диапазон и шаг шкалы, границы описаний и уникальность оценки. Использовать нормализованное значение для среднего и проверить, что смена шкалы не приводит к накоплению ошибок округления.
2. **Подключить оценки для MediaGroup.** Публиковать события групп из `media`, проверить обновление и удаление реплик. Убедиться, что новую группу можно оценить после доставки события.
3. **Довести медиатеку и рецензии до HTTP API.** Добавить операции с папками и их содержимым в `library`. В `review` добавить текстовые рецензии как отдельную сущность с собственным жизненным циклом. Покрыть сценарии владельца и действия чужого пользователя.
4. **Закрепить эксплуатационные проверки.** Хранить дашборды в репозитории и подключить постоянное хранилище мониторинга. Добавить метрики PostgreSQL, Keycloak и отставания Kafka consumer groups. Проверить повторную обработку событий и восстановление реплик после сбоя.
5. **Расширить каталог.** Добавить поиск и фильтры, затем импорт из внешних каталогов и загрузку обложек.
