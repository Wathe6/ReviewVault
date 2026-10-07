# ReviewVault

ReviewVault - backend для сайта рецензий и личной медиатеки. Проект должен хранить рецензии на медиа, которые потребляют пользователи: фильмы, сериалы, аниме, мангу, книги, музыку и игры. 
## Текущий состав

Проект собран как Gradle multi-module монорепозиторий на Java 26 и Spring Boot:

- `services:eureka` - discovery service на Spring Cloud Netflix Eureka.
- `services:gateway` - API gateway на Spring Cloud Gateway MVC, регистрируется в Eureka.
- `services:media` - сервис медиатеки: категории, форматы, жанры, произведения, группы произведений, альтернативные названия, переводы, внешние ссылки, персоны, компании и роли участия.
- `infrastructure` - Docker Compose конфигурации для Postgres, Keycloak и контейнерного запуска сервисов.

## Модель media

Основная топология хранится в сервисе `media`.

- `media_category` - крупные типы медиа: video, comic, literature, music, game.
- `media_format` - формат внутри категории: movie, series, anime, manga, book, album, game и т.д.
- `media_genre` + `media_genre_category` - жанры и привязка жанров к допустимым категориям.
- `media_group` - франшиза, серия, музыкальный артист/группа или другая объединяющая сущность.
- `media_item` - конкретное произведение: фильм, сезон/тайтл, книга, альбом, игра.
- `media_item_titles` / `media_group_titles` - альтернативные названия для поиска.
- `*_translation` - локализованные названия и описания.
- `person`, `company`, `credit_role`, `media_credit` - участники, компании и их роли в произведении.
- `link_type`, `link`, `media_external` - ссылки и идентификаторы во внешних источниках.

Миграции Flyway лежат в `services/media/src/main/resources/db/migration`.

## Инфраструктура

Локальная инфраструктура:

```powershell
cd infrastructure
docker compose -f docker-compose.infra.yaml up -d
```

По умолчанию из `.env.example`:

- Postgres: `localhost:5432`
- Keycloak: `localhost:8090`
- Eureka: `localhost:8761`
- Gateway: `localhost:8080`
- Media: `localhost:8081` при контейнерном запуске

## Запуск сервисов

Собрать и запустить все сервисы в контейнерах:

```powershell
.\infrastructure\build-all.ps1
```
```cmd
infrastructure\build-all.sh
```
Для отладки в IntelliJ IDEA удобнее поднять только инфраструктуру, затем запускать нужные Spring Boot приложения из IDE:

- `io.envoi.eureka.EurekaApplication`
- `io.envoi.gateway.GatewayApplication`
- `io.envoi.media.MediaApplication`

Для `gateway` при запуске из IDE нужно указать:

```text
EUREKA_CLIENT_SERVICEURL_DEFAULTZONE=http://localhost:8761/eureka/
```

## Планируемые сервисы

В архитектуре также предполагаются отдельные сервисы для рецензий, профилей пользователей, скрапинга внешних каталогов и хранения изображений. Сейчас в репозитории реализованы базовая инфраструктура, discovery/gateway и первый доменный сервис `media`.
