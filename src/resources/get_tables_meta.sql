SELECT
    table_catalog as database_name,
    table_name,
    table_schema,
    column_name,
    column_default as column_default_value,
    is_nullable,
    character_maximum_length as char_length,
    numeric_precision,
    numeric_scale,
    udt_name as column_type,
    is_updatable
FROM
    information_schema.columns
WHERE
    table_name = any (
    SELECT
    tablename
    FROM
    pg_catalog.pg_tables
    WHERE
    schemaname = 'public' AND
    tablename NOT LIKE 'pg_%' AND
    tablename NOT LIKE 'sql_%'
    );