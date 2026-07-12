INSERT INTO media.language(
    id,
    created_at,
    updated_at,
    code,
    name,
    native_name
)
VALUES
    (1, now(), now(), 'ru', 'Russian', 'Русский'),
    (2, now(), now(), 'en', 'English', 'English'),
    (3, now(), now(), 'ja', 'Japanese', '日本語');

INSERT INTO media.media_category(
    id,
    default_name,
    normalized_name,
    created_at,
    updated_at
)
VALUES (1,    'Video',        'video',    now(),  now()),
       (2,    'Comic',        'comic',    now(),  now()),
       (3,    'Literature',   'literature',   now(),  now()),
       (4,    'Music',        'music',    now(),  now()),
       (5,    'Game',         'game',     now(),  now());

INSERT INTO media.media_category_translation(
                                             id,
                                             language_id,
                                             name,
                                             created_at,
                                             updated_at,
                                             media_category_id
)
VALUES (1, 1, 'Видео',      now(), now(), 1),
       (2, 1, 'Комиксы',    now(), now(), 2),
       (3, 1, 'Литература', now(), now(), 3),
       (4, 1, 'Музыка',     now(), now(), 4),
       (5, 1, 'Игры',       now(), now(), 5);

INSERT INTO media.media_format(
                               id,
                               created_at,
                               updated_at,
                               media_category_id,
                               default_name,
                               normalized_name
)
VALUES ( 1, now(), now(), 1, 'Movie',       'movie'),
       ( 2, now(), now(), 1, 'Series',      'series'),
       ( 3, now(), now(), 1, 'Anime',       'anime'),
       ( 4, now(), now(), 1, 'OVA',         'ova'),
       ( 5, now(), now(), 1, 'ONA',         'ona'),
       ( 6, now(), now(), 1, 'Special',     'special'),
       ( 7, now(), now(), 1, 'TV Show',     'tv show'),
       ( 8, now(), now(), 1, 'Documentary', 'documentary'),
       ( 9, now(), now(), 1, 'Short Film',  'short film'),
       (10, now(), now(), 2, 'Manga',       'manga'),
       (11, now(), now(), 2, 'Manhwa',      'manhwa'),
       (12, now(), now(), 2, 'Manhua',      'manhua'),
       (13, now(), now(), 2, 'Comic',       'comic'),
       (14, now(), now(), 2, 'Rumanga',     'rumanga'),
       (15, now(), now(), 2, 'OEL-manga',   'oel-manga'),
       (16, now(), now(), 3, 'Novel',       'novel'),
       (17, now(), now(), 3, 'Light Novel', 'light novel'),
       (18, now(), now(), 3, 'Web Novel',   'web novel'),
       (19, now(), now(), 3, 'Book',        'book'),
       (20, now(), now(), 4, 'Album',       'album'),
       (21, now(), now(), 4, 'Single',      'single'),
       (22, now(), now(), 4, 'EP',          'ep'),
       (23, now(), now(), 4, 'Track',       'track'),
       (24, now(), now(), 5, 'Game',        'game'),
       (25, now(), now(), 5, 'Visual Novel','visual novel'),
       (26, now(), now(), 5, 'DLC',         'dlc');

INSERT INTO media.media_format_translation(
                                           id,
                                           language_id,
                                           name,
                                           created_at,
                                           updated_at,
                                           media_format_id
)
VALUES
    ( 1, 1, 'Фильм',                    now(), now(), 1),
    ( 2, 1, 'Сериал',                   now(), now(), 2),
    ( 3, 1, 'Аниме',                    now(), now(), 3),
    ( 4, 1, 'OVA',                      now(), now(), 4),
    ( 5, 1, 'ONA',                      now(), now(), 5),
    ( 6, 1, 'Спешл',                    now(), now(), 6),
    ( 7, 1, 'ТВ-шоу',                   now(), now(), 7),
    ( 8, 1, 'Документальный фильм',     now(), now(), 8),
    ( 9, 1, 'Короткометражный фильм',   now(), now(), 9),
    (10, 1, 'Манга',                    now(), now(), 10),
    (11, 1, 'Манхва',                   now(), now(), 11),
    (12, 1, 'Маньхуа',                  now(), now(), 12),
    (13, 1, 'Комикс',                   now(), now(), 13),
    (14, 1, 'Руманга',                  now(), now(), 14),
    (15, 1, 'OEL-манга',                now(), now(), 15),
    (16, 1, 'Роман',                    now(), now(), 16),
    (17, 1, 'Ранобэ',                   now(), now(), 17),
    (18, 1, 'Вебновел',                 now(), now(), 18),
    (19, 1, 'Книга',                    now(), now(), 19),
    (20, 1, 'Альбом',                   now(), now(), 20),
    (21, 1, 'Сингл',                    now(), now(), 21),
    (22, 1, 'EP',                       now(), now(), 22),
    (23, 1, 'Трек',                     now(), now(), 23),
    (24, 1, 'Игра',                     now(), now(), 24),
    (25, 1, 'Визуальная новела',        now(), now(), 25),
    (26, 1, 'DLC',                      now(), now(), 26);

INSERT INTO media.media_item_status(
                                    id,
                                    created_at,
                                    updated_at,
                                    name
)
VALUES
    (1, now(), now(), 'Announced'),
    (2, now(), now(), 'Ongoing'),
    (3, now(), now(), 'Hiatus'),
    (4, now(), now(), 'Finished'),
    (5, now(), now(), 'Cancelled');


INSERT INTO media.media_item_status_translation(
                                                id,
                                                language_id,
                                                name,
                                                created_at,
                                                updated_at,
                                                media_item_status_id
)
VALUES
    (1, 1, 'Анонс',             now(), now(), 1),
    (2, 1, 'Онгоинг',           now(), now(), 2),
    (3, 1, 'Приостановлен',     now(), now(), 3),
    (4, 1, 'Завершён',          now(), now(), 4),
    (5, 1, 'Выпуск прекращён',  now(), now(), 5);

INSERT INTO media.credit_role(
                              id,
                              created_at,
                              updated_at,
                              original_name,
                              normalized_name
)
VALUES
    ( 1, now(), now(), 'Author',            'author'),
    ( 2, now(), now(), 'Writer',            'writer'),
    ( 3, now(), now(), 'Screenwriter',      'screenwriter'),
    ( 4, now(), now(), 'Original Creator',  'original creator'),
    ( 5, now(), now(), 'Illustrator',       'illustrator'),
    ( 6, now(), now(), 'Character designer','character designer'),
    ( 7, now(), now(), 'Concept Artist',    'concept artist'),
    ( 8, now(), now(), 'Animator',          'animator'),
    ( 9, now(), now(), 'Director',          'director'),
    (10, now(), now(), 'Episode Director',  'episode director'),
    (11, now(), now(), 'Assistant Director','assistant director'),
    (12, now(), now(), 'Composer',          'composer'),
    (13, now(), now(), 'Arranger',          'arranger'),
    (14, now(), now(), 'Lyricist',          'lyricist'),
    (15, now(), now(), 'Performer',         'performer'),
    (16, now(), now(), 'Vocalist',          'vocalist'),
    (17, now(), now(), 'Band',              'band'),
    (18, now(), now(), 'Voice Actor',       'voice actor'),
    (19, now(), now(), 'Actor',             'actor'),
    (20, now(), now(), 'Narrator',          'narrator'),
    (21, now(), now(), 'Studio',            'studio'),
    (22, now(), now(), 'Publisher',         'publisher'),
    (23, now(), now(), 'Producer',          'producer'),
    (24, now(), now(), 'Executive Producer','executive producer'),
    (25, now(), now(), 'Editor',            'editor'),
    (26, now(), now(), 'Translator',        'translator'),
    (27, now(), now(), 'Localizer',         'localizer'),
    (28, now(), now(), 'Developer',         'developer'),
    (29, now(), now(), 'Label',             'label');

INSERT INTO media.credit_role_translation(
                                          id,
                                          language_id,
                                          name,
                                          created_at,
                                          updated_at,
                                          credit_role_id
)
VALUES
    ( 1, 1, 'Автор',                    now(), now(), 1),
    ( 2, 1, 'Автор',                    now(), now(), 2),
    ( 3, 1, 'Сценарист',                now(), now(), 3),
    ( 4, 1, 'Оригинальный создатель',   now(), now(), 4),
    ( 5, 1, 'Художник',                 now(), now(), 5),
    ( 6, 1, 'Дизайнер персонажей',      now(), now(), 6),
    ( 7, 1, 'Концепт-художник',         now(), now(), 7),
    ( 8, 1, 'Аниматор',                 now(), now(), 8),
    ( 9, 1, 'Режиссёр',                 now(), now(), 9),
    (10, 1, 'Режиссёр эпизода',         now(), now(), 10),
    (11, 1, 'Помощник режиссёра',       now(), now(), 11),
    (12, 1, 'Композитор',               now(), now(), 12),
    (13, 1, 'Аранжировщик',             now(), now(), 13),
    (14, 1, 'Текст песни',              now(), now(), 14),
    (15, 1, 'Исполнитель',              now(), now(), 15),
    (16, 1, 'Вокалист',                 now(), now(), 16),
    (17, 1, 'Группа',                   now(), now(), 17),
    (18, 1, 'Актёр озвучки',            now(), now(), 18),
    (19, 1, 'Актёр',                    now(), now(), 19),
    (20, 1, 'Рассказчик',               now(), now(), 20),
    (21, 1, 'Студия',                   now(), now(), 21),
    (22, 1, 'Издатель',                 now(), now(), 22),
    (23, 1, 'Продюсер',                 now(), now(), 23),
    (24, 1, 'Исполнительный продюсер',  now(), now(), 24),
    (25, 1, 'Редактор',                 now(),  now(), 25),
    (26, 1, 'Переводчик',               now(), now(), 26),
    (27, 1, 'Локализатор',              now(), now(), 27),
    (28, 1, 'Разработчик',              now(), now(), 28),
    (29, 1, 'Лейбл',                    now(), now(), 29);

INSERT INTO media.link_type(
                            id,
                            created_at,
                            updated_at,
                            name
)
VALUES
    (1, now(), now(), 'Official Website'),
    (2, now(), now(), 'MyAnimeList'),
    (3, now(), now(), 'Anilist'),
    (4, now(), now(), 'Mangalib'),
    (5, now(), now(), 'NovelUpdates'),
    (6, now(), now(), 'Goodreads'),
    (7, now(), now(), 'Spotify'),
    (8, now(), now(), 'Steampowered')
;

INSERT INTO media.media_genre(
                              id,
                              created_at,
                              updated_at,
                              original_name,
                              normalized_name,
                              description
)
VALUES
    ( 1, now(), now(), 'Action',    'action', 'Physical challenges and high energy.'),
    ( 2, now(), now(), 'Adventure', 'adventure', 'Journeys and exciting quests.'),
    ( 3, now(), now(), 'Art',       'art', 'Focuses on artistic themes.'),
    ( 4, now(), now(), 'Boys Love', 'boys-love', 'Romance between male characters.'),
    ( 5, now(), now(), 'Cars',      'cars', 'Focuses on racing and automobiles.'),
    ( 6, now(), now(), 'Comedy',    'comedy', 'Funny and lighthearted.'),
    ( 7, now(), now(), 'Cyberpunk', 'cyberpunk', 'High-tech futuristic settings.'),
    ( 8, now(), now(), 'Demons',    'demons', 'Supernatural entities.'),
    ( 9, now(), now(), 'Drama',     'drama', 'Emotional life struggles.'),
    (10, now(), now(), 'Fantasy',   'fantasy', 'Imaginary worlds and magic.'),
    (11, now(), now(), 'Game',      'game', 'Centered around competitions.'),
    (12, now(), now(), 'Gender Bender', 'gender-bender', 'Physical transformation into the opposite sex.'),
    (13, now(), now(), 'Gender Intrigue', 'gender-intrigue', 'Disguising as the opposite sex.'),
    (14, now(), now(), 'Harem',     'harem', 'One character, multiple love interests.'),
    (15, now(), now(), 'Heroic Fantasy', 'heroic-fantasy', 'Chronicles of mighty heroes.'),
    (16, now(), now(), 'Historical','historical', 'Set in real-world historical eras.'),
    (17, now(), now(), 'Horror',    'horror', 'Dark and terrifying elements.'),
    (18, now(), now(), 'Isekai',    'isekai', 'Transported to another world.'),
    (19, now(), now(), 'Josei',     'josei', 'Realistic life for adult women.'),
    (20, now(), now(), 'Kodomo',    'kodomo', 'Created for young children.'),
    (21, now(), now(), 'Magic',     'magic', 'Features magical systems.'),
    (22, now(), now(), 'Mahou Shoujo', 'mahou-shoujo', 'Magical girl genre.'),
    (23, now(), now(), 'Martial Arts', 'martial-arts', 'Hand-to-hand combat.'),
    (24, now(), now(), 'Mecha',     'mecha', 'Giant robots and battles.'),
    (25, now(), now(), 'Military',  'military', 'Warfare and strategy.'),
    (26, now(), now(), 'Mystery',   'mystery', 'Investigation and secrets.'),
    (27, now(), now(), 'Music',     'music', 'Bands and singing.'),
    (28, now(), now(), 'Mysticism', 'mysticism', 'Spiritual knowledge.'),
    (29, now(), now(), 'Parody',    'parody', 'Humorous imitation.'),
    (30, now(), now(), 'Police',    'police', 'Law enforcement.'),
    (31, now(), now(), 'Post-Apocalyptic', 'post-apocalyptic', 'After a global collapse.'),
    (32, now(), now(), 'Psychological', 'psychological', 'Explores mental states.'),
    (33, now(), now(), 'Romance',   'romance', 'Love stories and bonds.'),
    (34, now(), now(), 'Samurai',   'samurai', 'Japanese swordsmanship.'),
    (35, now(), now(), 'School',    'school', 'Centered around school life.'),
    (36, now(), now(), 'Sci-Fi',    'sci-fi', 'Futuristic science concepts.'),
    (37, now(), now(), 'Sci-Fi Fantasy', 'sci-fi-fantasy', 'Technology and fantasy.'),
    (38, now(), now(), 'Seinen',    'seinen', 'Targeted at young adult men.'),
    (39, now(), now(), 'Shoujo',    'shoujo', 'Targeted at young girls.'),
    (40, now(), now(), 'Shounen',   'shounen', 'Targeted at young boys.'),
    (41, now(), now(), 'Slice of Life', 'slice-of-life', 'Everyday experiences.'),
    (42, now(), now(), 'Space',     'space', 'Outer space settings.'),
    (43, now(), now(), 'Sports',    'sports', 'Athletic competitions.'),
    (44, now(), now(), 'Super Power', 'super-power', 'Superhuman abilities.'),
    (45, now(), now(), 'Supernatural', 'supernatural', 'Defies laws of nature.'),
    (46, now(), now(), 'Thriller',  'thriller', 'Suspense and tension.'),
    (47, now(), now(), 'Tragedy',   'tragedy', 'Severe loss and suffering.'),
    (48, now(), now(), 'Vampires',  'vampires', 'Vampire lore.'),
    (49, now(), now(), 'Yuri',      'yuri', 'Romance between female characters.'),
    (50, now(), now(), 'Omegaverse','omegaverse', 'Hierarchy of Alphas, Betas, and Omegas'),
    (51, now(), now(), 'Ecchi',     'ecchi', 'Playfully sexual actions.'),
    (52, now(), now(), 'Pop',       'pop', 'Popular music with catchy melodies.'),
    (53, now(), now(), 'Rock',      'rock', 'Guitar-driven music with strong beats.'),
    (54, now(), now(), 'Electronic','electronic', 'Synthesizers and digital instruments.'),
    (55, now(), now(), 'Classical', 'classical', 'Traditional and orchestral music.'),
    (56, now(), now(), 'Hip Hop',   'hip-hop', 'Rhythmic backing beats and rapping.'),
    (57, now(), now(), 'Jazz',      'jazz', 'Improvisation and complex harmony.');

INSERT INTO media.media_genre_translation(
                                          id,
                                          language_id,
                                          name,
                                          created_at,
                                          updated_at,
                                          media_genre_id
)
VALUES
    ( 1, 1, 'Боевик',               now(), now(), 1),
    ( 2, 1, 'Приключения',          now(), now(), 2),
    ( 3, 1, 'Арт',                  now(), now(), 3),
    ( 4, 1, 'Яой',                  now(), now(), 4),
    ( 5, 1, 'Машины',               now(), now(), 5),
    ( 6, 1, 'Комедия',              now(), now(), 6),
    ( 7, 1, 'Киберпанк',            now(), now(), 7),
    ( 8, 1, 'Демоны',               now(), now(), 8),
    ( 9, 1, 'Драма',                now(), now(), 9),
    (10, 1, 'Фэнтези',              now(), now(), 10),
    (11, 1, 'Игра',                 now(), now(), 11),
    (12, 1, 'Гендер бендер',        now(), now(), 12),
    (13, 1, 'Гендерная интрига',    now(), now(), 13),
    (14, 1, 'Гарем',                now(), now(), 14),
    (15, 1, 'Героическое фэнтези',  now(), now(), 15),
    (16, 1, 'История',              now(), now(), 16),
    (17, 1, 'Ужасы',                now(), now(), 17),
    (18, 1, 'Исекай',               now(), now(), 18),
    (19, 1, 'Дзёсэй',               now(), now(), 19),
    (20, 1, 'Кодомо',               now(), now(), 20),
    (21, 1, 'Магия',                now(), now(), 21),
    (22, 1, 'Махо-сёдзё',           now(), now(), 22),
    (23, 1, 'Боевые искусства',     now(), now(), 23),
    (24, 1, 'Меха',                 now(), now(), 24),
    (25, 1, 'Военное',              now(), now(), 25),
    (26, 1, 'Детектив',             now(), now(), 26),
    (27, 1, 'Музыка',               now(), now(), 27),
    (28, 1, 'Мистика',              now(), now(), 28),
    (29, 1, 'Пародия',              now(), now(), 29),
    (30, 1, 'Полиция',              now(), now(), 30),
    (31, 1, 'Постапокалиптика',     now(), now(), 31),
    (32, 1, 'Психология',           now(), now(), 32),
    (33, 1, 'Романтика',            now(), now(), 33),
    (34, 1, 'Самураи',              now(), now(), 34),
    (35, 1, 'Школа',                now(), now(), 35),
    (36, 1, 'Научная фантастика',   now(), now(), 36),
    (37, 1, 'Фантастика',           now(), now(), 37),
    (38, 1, 'Сэйнэн',               now(), now(), 38),
    (39, 1, 'Сёдзё',                now(), now(), 39),
    (40, 1, 'Сёнэн',                now(), now(), 40),
    (41, 1, 'Повседневность',       now(), now(), 41),
    (42, 1, 'Космос',               now(), now(), 42),
    (43, 1, 'Спорт',                now(), now(), 43),
    (44, 1, 'Супер сила',           now(), now(), 44),
    (45, 1, 'Сверхестественное',    now(), now(), 45),
    (46, 1, 'Триллер',              now(), now(), 46),
    (47, 1, 'Трагедия',             now(), now(), 47),
    (48, 1, 'Вампиры',              now(), now(), 48),
    (49, 1, 'Юри',                  now(), now(), 49),
    (50, 1, 'Омегаверс',            now(), now(), 50),
    (51, 1, 'Эччи',                 now(), now(), 51),
    (52, 1, 'Поп',                  now(), now(),  52),
    (53, 1, 'Рок',                  now(), now(),  53),
    (54, 1, 'Электроника',          now(), now(),  54),
    (55, 1, 'Классика',             now(), now(),  55),
    (56, 1, 'Хип хоп',              now(), now(),  56),
    (57, 1, 'Джаз',                 now(), now(),  57);


WITH combined_relations AS (
    -- 1. Связываем жанры 1-51 с категориями Video(1), Comic(2), Literature(3), Game(5)
    SELECT g.id AS genre_id, c.id AS category_id
    FROM media.media_genre g
             CROSS JOIN media.media_category c
    WHERE g.id <= 51 AND c.id IN (1, 2, 3, 5)

    UNION ALL

    -- 2. Связываем новые музыкальные жанры 52-57 только с категорией Music(4)
    SELECT g.id AS genre_id, 4 AS category_id
    FROM media.media_genre g
    WHERE g.id BETWEEN 52 AND 57
)
INSERT INTO media.media_genre_category (
    id,
    created_at,
    updated_at,
    media_genre_id,
    media_category_id
)
SELECT
            ROW_NUMBER() OVER (ORDER BY category_id, genre_id) AS id,
            now(),
            now(),
            genre_id,
            category_id
FROM combined_relations;

INSERT INTO media.media_group(
                              id,
                              created_at,
                              updated_at,
                              cover_url,
                              title,
                              description,
                              original_language_id
)
VALUES
    ('11111111-1111-1111-1111-111111111111', now(), now(), '', 'Attack on Titan',    '', 2),
    ('22222222-2222-2222-2222-222222222222', now(), now(), '', 'Berserk',            '', 2),
    ('33333333-3333-3333-3333-333333333333', now(), now(), '', 'Harry Potter',       '', 2),
    ('44444444-4444-4444-4444-444444444444', now(), now(), '', 'Linkin Park',        '', 2),
    ('55555555-5555-5555-5555-555555555555', now(), now(), '', 'Cyberpunk 2077',     '', 2);

INSERT INTO media.media_group_translation(
                                          id,
                                          language_id,
                                          name,
                                          created_at,
                                          updated_at,
                                          media_group_id,
                                          description)
VALUES
    (1, 1, 'Атака Титанов', now(), now(), '11111111-1111-1111-1111-111111111111', ''),
    (2, 1, 'Берсерк',       now(), now(), '22222222-2222-2222-2222-222222222222', ''),
    (3, 1, 'Гарри Поттер',  now(), now(), '33333333-3333-3333-3333-333333333333', '');

INSERT INTO media.media_group_titles(
                                     id,
                                     created_at,
                                     updated_at,
                                     media_group_id,
                                     title,
                                     normalized_name
)
VALUES
    (1, now(), now(), '11111111-1111-1111-1111-111111111111', 'AoT',              'aot'),
    (2, now(), now(), '11111111-1111-1111-1111-111111111111', 'Атака на титанов', 'атака на титанов'),
    (3, now(), now(), '11111111-1111-1111-1111-111111111111', 'Атакующий титан',  'атакующий титан'),
    (4, now(), now(), '11111111-1111-1111-1111-111111111111', '進撃の巨人',         '進撃の巨人');

INSERT INTO media.media_item(
                             id,
                             created_at,
                             updated_at,
                             title,
                             media_group_id,
                             media_format_id,
                             media_category_id,
                             media_item_status_id,
                             description,
                             release_date,
                             end_date,
                             original_language_id,
                             cover_url,
                             metadata
)
VALUES
    ('11111111-1111-1111-1111-111111111111', now(), now(), 'Attack on Titan'                             , '11111111-1111-1111-1111-111111111111', 3,  1, 4, '', '2013-04-07', '2023-11-19', 3, '', '{}'),
    ('22222222-2222-2222-2222-222222222222', now(), now(), 'Berserk'                                     , '22222222-2222-2222-2222-222222222222', 10, 2, 2, '', '1989-01-01', NULL, 3, '', '{}'),
    ('33333333-3333-3333-3333-333333333333', now(), now(), 'Harry Potter and the Philosopher''s Stone'   , '33333333-3333-3333-3333-333333333333', 16, 3, 4, '', '1997-06-26', NULL, 2, '', '{}'),
    ('44444444-4444-4444-4444-444444444444', now(), now(), 'Hybrid Theory'                               , '44444444-4444-4444-4444-444444444444', 20, 4, 4, '', '2000-10-24', NULL, 2, '', '{}'),
    ('55555555-5555-5555-5555-555555555555', now(), now(), 'Cyberpunk 2077'                              , '55555555-5555-5555-5555-555555555555', 24, 5, 4, '', '2020-12-10', NULL, 2, '', '{}');

INSERT INTO media.media_item_titles(
                                    id,
                                    created_at,
                                    updated_at,
                                    media_item_id,
                                    title,
                                    normalized_name
)
VALUES
    (1, now(), now(), '11111111-1111-1111-1111-111111111111', 'Shingeki no Kyojin',  'shingeki no kyojin'),
    (2, now(), now(), '22222222-2222-2222-2222-222222222222', 'Berzerk',             'berzerk'),
    (3, now(), now(), '33333333-3333-3333-3333-333333333333', 'Harry Potter and the Sorcerer''s Stone', 'harry potter and the sorcerer''s stone'),
    (4, now(), now(), '44444444-4444-4444-4444-444444444444', 'Хибрид теори',        'хибрид теори'),
    (5, now(), now(), '55555555-5555-5555-5555-555555555555', 'Киберпанк 2077',      'киберпанк 2077');

INSERT INTO media.media_item_translation(
                                         id,
                                         language_id,
                                         name,
                                         created_at,
                                         updated_at,
                                         media_item_id,
                                         description
)
VALUES
    (1, 1, 'Атака титанов',                     now(), now(), '11111111-1111-1111-1111-111111111111', ''),
    (2, 1, 'Берсерк',                           now(), now(), '22222222-2222-2222-2222-222222222222', ''),
    (3, 1, 'Гарри Поттер и философский камень', now(), now(), '33333333-3333-3333-3333-333333333333', '');

INSERT INTO media.person(
                         id,
                         created_at,
                         updated_at,
                         name,
                         biography,
                         birth_date,
                         death_date,
                         cover_url
)
VALUES
    ('11111111-1111-1111-1111-111111111111', now(), now(), 'Hajime Isayama', '', 'August 29, 1986',      NULL,             ''),
    ('22222222-2222-2222-2222-222222222222', now(), now(), 'Kentaro Miura',  '', 'July 11, 1966',        'May 6, 2021',  ''),
    ('33333333-3333-3333-3333-333333333333', now(), now(), 'J. K. Rowling',  '', 'July 31, 1965',        NULL,             ''),
    ('44444444-4444-4444-4444-444444444444', now(), now(), 'Mike Shinoda',   '', 'February 11, 1977',    NULL,             ''),
    ('55555555-5555-5555-5555-555555555555', now(), now(), 'Keanu Reeves',   '', 'September 2, 1964 ',   NULL,             '');

INSERT INTO media.person_translation(
                                     id,
                                     language_id,
                                     name,
                                     created_at,
                                     updated_at,
                                     person_id,
                                     biography
)
VALUES
    (1, 1, 'Хадзимэ Исаяма',    now(), now(), '11111111-1111-1111-1111-111111111111', ''),
    (2, 1, 'Кэнтаро Миура',     now(), now(), '22222222-2222-2222-2222-222222222222', ''),
    (3, 1, 'Джоан Роулинг',     now(), now(), '33333333-3333-3333-3333-333333333333', ''),
    (4, 1, 'Майк Шинода',       now(), now(), '44444444-4444-4444-4444-444444444444', ''),
    (5, 1, 'Киану Ривз',        now(), now(), '55555555-5555-5555-5555-555555555555', '');

INSERT INTO media.company(
                          id,
                          created_at,
                          updated_at,
                          name,
                          description,
                          founded_date,
                          closed_date,
                          cover_url
)
VALUES
    ('11111111-1111-1111-1111-111111111111', now(), now(), 'Wit Studio',     '', 'June 1, 2012',         NULL, ''),
    ('22222222-2222-2222-2222-222222222222', now(), now(), 'Hakusensha',     '', 'December 1, 1973',     NULL, ''),
    ('33333333-3333-3333-3333-333333333333', now(), now(), 'Bloomsbury',     '', 'September 26, 1986',   NULL, ''),
    ('44444444-4444-4444-4444-444444444444', now(), now(), 'Warner Records', '', 'March 19, 1958',       NULL, ''),
    ('55555555-5555-5555-5555-555555555555', now(), now(), 'CD Projekt Red', '', 'March 6, 2002',        NULL, '');

INSERT INTO media.media_credit(
                               id,
                               created_at,
                               updated_at,
                               media_item_id,
                               person_id,
                               company_id,
                               credit_role_id
)
VALUES
    ( 1, now(), now(), '11111111-1111-1111-1111-111111111111', '11111111-1111-1111-1111-111111111111',    NULL,   1),
    ( 2, now(), now(), '11111111-1111-1111-1111-111111111111', NULL, '11111111-1111-1111-1111-111111111111',      21),
    ( 3, now(), now(), '22222222-2222-2222-2222-222222222222', '22222222-2222-2222-2222-222222222222',    NULL,   1),
    ( 4, now(), now(), '22222222-2222-2222-2222-222222222222', NULL, '22222222-2222-2222-2222-222222222222',      22),
    ( 5, now(), now(), '33333333-3333-3333-3333-333333333333', '33333333-3333-3333-3333-333333333333',    NULL,   1),
    ( 6, now(), now(), '33333333-3333-3333-3333-333333333333', NULL, '33333333-3333-3333-3333-333333333333',      22),
    ( 7, now(), now(), '44444444-4444-4444-4444-444444444444', '44444444-4444-4444-4444-444444444444',    NULL,   15),
    ( 8, now(), now(), '44444444-4444-4444-4444-444444444444', NULL, '44444444-4444-4444-4444-444444444444',      29),
    ( 9, now(), now(), '55555555-5555-5555-5555-555555555555', '55555555-5555-5555-5555-555555555555',    NULL,   28),
    (10, now(), now(), '55555555-5555-5555-5555-555555555555', NULL, '55555555-5555-5555-5555-555555555555',      19);

INSERT INTO media.media_external(
                                 id,
                                 created_at,
                                 updated_at,
                                 media_item_id,
                                 media_link_type_id,
                                 external_id
)
VALUES
    (1, now(), now(), '11111111-1111-1111-1111-111111111111', 2,    '16498'),
    (2, now(), now(), '22222222-2222-2222-2222-222222222222', 2,    '2'),
    (3, now(), now(), '33333333-3333-3333-3333-333333333333', 6,    '42844155'),
    (4, now(), now(), '44444444-4444-4444-4444-444444444444', 7,    '2pKw6GERJVAD61449B1EEM'),
    (5, now(), now(), '55555555-5555-5555-5555-555555555555', 8,    '1091500');

INSERT INTO media.link(
                       id,
                       created_at,
                       updated_at,
                       media_item_id,
                       media_link_type_id,
                       url
)
VALUES
    (1, now(), now(), '11111111-1111-1111-1111-111111111111', 2, 'https://myanimelist.net/anime/16498/Shingeki_no_Kyojin'),
    (2, now(), now(), '22222222-2222-2222-2222-222222222222', 2, 'https://myanimelist.net/manga/2/Berserk'),
    (3, now(), now(), '33333333-3333-3333-3333-333333333333', 6, 'https://www.goodreads.com/en/book/show/42844155-harry-potter-and-the-philosopher-s-stone'),
    (4, now(), now(), '44444444-4444-4444-4444-444444444444', 7, 'https://open.spotify.com/album/2pKw6GERJVAD61449B1EEM'),
    (5, now(), now(), '55555555-5555-5555-5555-555555555555', 7, 'https://store.steampowered.com/app/1091500/Cyberpunk_2077/');


