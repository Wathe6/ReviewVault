DO $$
    DECLARE
        r record;
    BEGIN
        FOR r IN
            SELECT
                table_schema,
                table_name,
                column_name
            FROM information_schema.columns
            WHERE is_identity = 'YES'
              AND table_schema = 'review'
            LOOP
                EXECUTE format(
                        'SELECT setval(
                            pg_get_serial_sequence(''%I.%I'', ''%I''),
                            COALESCE((SELECT MAX(%I) FROM %I.%I), 1),
                            true
                        )',
                        r.table_schema,
                        r.table_name,
                        r.column_name,
                        r.column_name,
                        r.table_schema,
                        r.table_name
                        );
            END LOOP;
    END $$;