-- Make mobile unique (idempotent)
DO $$
BEGIN
  IF NOT EXISTS (
    SELECT 1 FROM pg_indexes WHERE indexname = 'ux_users_mobile'
  ) THEN
CREATE UNIQUE INDEX ux_users_mobile ON users(mobile);
END IF;
END$$;