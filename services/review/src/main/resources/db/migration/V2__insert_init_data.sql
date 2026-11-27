INSERT INTO review.rating(
    id,
    user_profile_replica_id,
    media_item_replica_id,
    media_group_replica_id,
    value,
    normalized_value,
    created_at,
    updated_at
) VALUES
      (1, '11111111-1111-1111-1111-111111111111', '11111111-1111-1111-1111-111111111111', NULL, 10, 10000, now(), now()),
      (2, '11111111-1111-1111-1111-111111111111', NULL, '11111111-1111-1111-1111-111111111111', 9,  9000,  now(), now()),
      (3, '11111111-1111-1111-1111-111111111111', '22222222-2222-2222-2222-222222222222', NULL, 1,  0,  now(), now()),
      (4, '11111111-1111-1111-1111-111111111111', NULL, '22222222-2222-2222-2222-222222222222', 2,  2500,  now(), now()),
      (5, '11111111-1111-1111-1111-111111111111', '33333333-3333-3333-3333-333333333333', NULL, 3.00, 5000, now(), now());

INSERT INTO review.rating_scale(
    id,
    name,
    min,
    max,
    step,
    total_steps,
    type,
    created_at,
    updated_at
) VALUES
      (1, '0 to 10', 0, 10, 1, 11, 'int', now(), now()),
      (2, '1 to 10', 1, 10, 1, 11, 'int', now(), now()),
      (3, '1 to 100', 1, 100, 1, 100, 'int', now(), now()),
      (4, '1 to 5', 1, 5, 1, 5, 'int', now(), now()),
      (5, '1.00 to 5.00', 1.00, 5.00, 0.01, 500, 'float', now(), now());

INSERT INTO review.user_rating_preference(
    id,
    user_profile_replica_id,
    rating_scale_id,
    created_at,
    updated_at
) VALUES
      (1, '11111111-1111-1111-1111-111111111111', 1, now(), now()),
      (2, '22222222-2222-2222-2222-222222222222', 2, now(), now()),
      (3, '33333333-3333-3333-3333-333333333333', 3, now(), now()),
      (4, '44444444-4444-4444-4444-444444444444', 4, now(), now()),
      (5, '55555555-5555-5555-5555-555555555555', 5, now(), now());

INSERT INTO review.user_rating_description (
    id,
    user_profile_replica_id,
    "left",
    "right",
    normalized_left,
    normalized_right,
    description,
    created_at,
    updated_at
)
VALUES
    (1, '33333333-3333-3333-3333-333333333333', 1, 20, 0, 1919, 'Very bad', now(), now()),
    (2, '33333333-3333-3333-3333-333333333333', 21, 40, 2020, 3939, 'Bad', now(), now()),
    (3, '33333333-3333-3333-3333-333333333333', 41, 60, 4040, 5960, 'Normal', now(), now()),
    (4, '33333333-3333-3333-3333-333333333333', 61, 80, 6061, 7980, 'Good', now(), now()),
    (5, '33333333-3333-3333-3333-333333333333', 81, 100, 8081, 10000, 'Very Good', now(), now());
