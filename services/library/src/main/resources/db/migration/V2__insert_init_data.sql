INSERT INTO library.user_folder(
    id,
    created_at,
    updated_at,
    user_profile_replica_id,
    name,
    cover_url,
    position
) VALUES
      (1, now(), now(), '11111111-1111-1111-1111-111111111111', 'The best', '', NULL),
      (2, now(), now(), '11111111-1111-1111-1111-111111111111', 'Good', '', NULL),
      (3, now(), now(), '11111111-1111-1111-1111-111111111111', 'Not bad, nor good', '', NULL),
      (4, now(), now(), '11111111-1111-1111-1111-111111111111', 'Bad', '', NULL),
      (5, now(), now(), '11111111-1111-1111-1111-111111111111', 'The worst', '', NULL);

INSERT INTO library.folder_item(
    id,
    created_at,
    updated_at,
    media_item_replica_id,
    media_group_replica_id,
    user_folder_id,
    position
) VALUES
      (1, now(), now(), '11111111-1111-1111-1111-111111111111', NULL, 1, 0),
      (2, now(), now(), NULL, '11111111-1111-1111-1111-111111111111', 1, 1),
      (3, now(), now(), '22222222-2222-2222-2222-222222222222', NULL, 2, 0),
      (4, now(), now(), NULL, '22222222-2222-2222-2222-222222222222', 2, 1),
      (5, now(), now(), '33333333-3333-3333-3333-333333333333', NULL, 3, 0);

