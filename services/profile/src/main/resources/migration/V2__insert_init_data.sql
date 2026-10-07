INSERT INTO profile.media_item_replica(
                             id,
                             created_at,
                             updated_at,
                             title,
                             cover_url,
                             last_event_id,
                             last_event_at,
                             deleted
)
VALUES
    ('11111111-1111-1111-1111-111111111111', now(), now(), 'Attack on Titan'                             ,"", '11111111-1111-1111-1111-111111111111', NULL, FALSE),
    ('22222222-2222-2222-2222-222222222222', now(), now(), 'Berserk'                                     , "", '11111111-1111-1111-1111-111111111111', NULL, FALSE),
    ('33333333-3333-3333-3333-333333333333', now(), now(), 'Harry Potter and the Philosopher''s Stone'   , "", '11111111-1111-1111-1111-111111111111', NULL, FALSE),
    ('44444444-4444-4444-4444-444444444444', now(), now(), 'Hybrid Theory'                               , "", '11111111-1111-1111-1111-111111111111', NULL, FALSE),
    ('55555555-5555-5555-5555-555555555555', now(), now(), 'Cyberpunk 2077'                              , "", '11111111-1111-1111-1111-111111111111', NULL, FALSE);

INSERT INTO profile.user_profile(
                                 id,
                                 created_at,
                                 updated_at,
                                 name,
                                 cover_url,
                                 deleted
) VALUES
      ('11111111-1111-1111-1111-111111111111', now(), now(), 'test_user1', '', FALSE),
      ('22222222-2222-2222-2222-222222222222', now(), now(), 'test_user2', '', FALSE),
      ('33333333-3333-3333-3333-333333333333', now(), now(), 'test_user3', '', FALSE),
      ('44444444-4444-4444-4444-444444444444', now(), now(), 'test_user4', '', FALSE),
      ('55555555-5555-5555-5555-555555555555', now(), now(), 'test_user5', '', FALSE);

INSERT INTO profile.user_folder(
                                id,
                                created_at,
                                updated_at,
                                user_profile_id,
                                name,
                                cover_url,
                                position
) VALUES
      (1, now(), now(), '11111111-1111-1111-1111-111111111111', 'The bast', '', NULL),
      (2, now(), now(), '11111111-1111-1111-1111-111111111111', 'Good', '', NULL),
      (3, now(), now(), '11111111-1111-1111-1111-111111111111', 'Not bad, nor good', '', NULL),
      (4, now(), now(), '11111111-1111-1111-1111-111111111111', 'Bad', '', NULL),
      (5, now(), now(), '11111111-1111-1111-1111-111111111111', 'The worst', '', NULL);