# ReviewVault

ReviewVault — разрабатываемый backend для личной медиатеки и рецензий на фильмы, сериалы, аниме, комиксы, книги, музыку и игры.

## Микросервисы

| Модуль | Текущее состояние                                                                                                     |
| --- |-----------------------------------------------------------------------------------------------------------------------|
| `services:contracts` | Java-библиотека с контрактами событий MediaItem V1 и базовыми JPA-сущностями; собирается как `jar`.                   |
| `services:eureka` | Discovery Service через Spring Cloud Netflix Eureka.                                                                  |
| `services:gateway` | Spring Cloud Gateway MVC и проверка JWT Keycloak для `/api/v1/**`.                                                    |
| `services:media` | Доменная модель, базовый CRUD MediaItem, MediaGroup, Person и Company, Flyway и публикация событий MediaItem в Kafka. |
| `services:review` | Получение событий MediaItem из Kafka и локальная реплика item. Сами оценки, рецензии и HTTP API ещё не реализованы.   |
| `services:profile` | Начальная модель профиля, папки и реплики MediaItem. Полный API и Kafka-consumer ещё не реализованы.                  |

`infrastructure` содержит Compose-файлы для PostgreSQL, Keycloak, Kafka, Kafka REST Proxy и приложений.

## Модель media

Основная топология хранится в сервисе `media`.

- `media_category` - крупные типы медиа: video, comic, literature, music, game.
- `media_format` - формат внутри категории: movie, series, anime, manga, book, album, game и т.д.
- `media_genre` + `media_genre_category` - жанры и привязка жанров к допустимым категориям.
- `media_group` - франшиза, серия, музыкальный исполнитель/группа или другая объединяющая сущность.
- `media_item` - конкретное произведение: фильм, сезон/тайтл, книга, альбом, игра.
- `media_item_titles` / `media_group_titles` - альтернативные названия для поиска.
- `*_translation` - локализованные названия и описания.
- `person`, `company`, `credit_role`, `media_credit` - люди, компании и их роли в произведении.
- `link_type`, `link`, `media_external` - ссылки и идентификаторы во внешних источниках.

Миграции Flyway лежат в `services/media/src/main/resources/db/migration`.

## Обмен событиями

Сервис `media` публикует `MediaItemCreatedV1`, `MediaItemUpdatedV1` и `MediaItemDeletedV1` в топик `reviewvault.media-item-events.v1` (настраивается через `KAFKA_TOPIC_MEDIA_ITEM_EVENTS`). Ключ сообщения — UUID MediaItem. Публикация запускается после коммита транзакции; `review` читает события и обновляет свою реплику. Snapshot события содержит UUID, название и URL обложки.

Контракты находятся в `services/contracts/src/main/java/io/envoi/contracts/media/v1`. Событий MediaGroup и работающей синхронизации реплики в `profile` пока нет.

## Инфраструктура

Для запуска нужны JDK 26, Docker Compose и Gradle Wrapper из репозитория. Команды ниже выполняются из корня проекта:

```powershell
if (-not (Test-Path infrastructure/.env)) { Copy-Item infrastructure/.env.example infrastructure/.env }
docker network create reviewvault-net
docker compose -f infrastructure/docker-compose.infra.yaml up -d
```

`infrastructure/.env` исключён из Git. Значения `.env.example` предназначены только для локальной разработки. SQL-инициализация PostgreSQL сейчас содержит демонстрационные пароли: при их смене согласуйте `infrastructure/postgres/init/00-create-databases.sql`. Скрипт инициализации БД выполняется только при создании нового тома PostgreSQL.

Порты по умолчанию: PostgreSQL — `5432`, Keycloak — `8090`, Kafka — `9092` с хоста и `kafka:19092` внутри Docker, Kafka REST Proxy — `8088`, Eureka — `8761`, Gateway — `8080`, Media — `8081`, Profile — `8082`, Review — `8083`.

Для Keycloak предусмотрен одноразовый скрипт `infrastracture/keycloak/init.sh`. Realm и OAuth-клиент настраиваются автоматически с параметрами из `.env`.

## Запуск сервисов

Собрать модули и запустить настроенные сервисы в контейнерах на Windows:

```powershell
.\infrastructure\build-all.ps1
```

На Bash:

```bash
bash infrastructure/build-all.sh
```

Скрипты собирают `contracts:jar` и Boot JAR приложений, останавливаются при ошибке Gradle, затем запускают `docker-compose.services.yaml`. Этот Compose-файл сейчас содержит `eureka`, `gateway`, `media` и `review`. Модуль `profile` собирается, но его сервиса в Compose пока нет.

Для отладки в IntelliJ IDEA можно поднять инфраструктуру и запускать приложения из IDE:

- `io.envoi.eureka.EurekaApplication`
- `io.envoi.gateway.GatewayApplication`
- `io.envoi.media.MediaApplication`
- `io.envoi.review.ReviewApplication`

Для `gateway` при запуске из IDE нужно указать Eureka и проверить адрес Keycloak: текущий `jwk-set-uri` использует Docker-имя `keycloak`, которое обычно не разрешается на хосте.

```text
EUREKA_CLIENT_SERVICEURL_DEFAULTZONE=http://localhost:8761/eureka/
```

Gateway проверяет JWT, но не выдаёт токены; без Bearer token запросы к `/api/v1/**` вернут `401`. Его маршруты для items и groups ведут в `media`. CRUD Person и Company есть в `media`, но маршруты gateway для них ещё не добавлены.

## Тесты и следующие шаги

```powershell
.\gradlew.bat test
```

В `media` и `review` есть интеграционные тесты messaging на Embedded Kafka. JPA-тесты обращаются к локальному PostgreSQL, поэтому для общего запуска тестов нужна доступная БД с миграциями. Прохождение всего набора тестов здесь не гарантируется.

`profile` пока не готов к запуску: его миграции лежат в `src/main/resources/migration`, а конфигурация Flyway указывает `classpath:db/migration`. Далее предстоит реализовать оценки и рецензии, содержимое коллекций, интеграцию профилей с аккаунтами, получение данных из внешних каталогов и работу с изображениями.
