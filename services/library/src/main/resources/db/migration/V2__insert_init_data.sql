INSERT INTO library.user_folder(
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