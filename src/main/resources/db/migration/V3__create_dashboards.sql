-- V3__create_dashboards.sql
CREATE EXTENSION IF NOT EXISTS "pgcrypto";

CREATE TABLE dashboards (
                            id              character varying PRIMARY KEY DEFAULT gen_random_uuid()::text,
                            name            text NOT NULL,
                            owner_id        character varying NOT NULL REFERENCES users(id) ON DELETE CASCADE,
                            is_shared       boolean DEFAULT false,
                            created_at      timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
                            updated_at      timestamp without time zone DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE dashboard_widgets (
                                   id              character varying PRIMARY KEY DEFAULT gen_random_uuid()::text,
                                   dashboard_id    character varying NOT NULL REFERENCES dashboards(id) ON DELETE CASCADE,
                                   type            character varying NOT NULL,
                                   title           character varying,
                                   config          jsonb NOT NULL,
                                   position        jsonb,
                                   sort_order      integer DEFAULT 0,
                                   created_at      timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
                                   updated_at      timestamp without time zone DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX idx_dashboards_owner ON dashboards(owner_id);
CREATE INDEX idx_widgets_dashboard ON dashboard_widgets(dashboard_id);
CREATE INDEX idx_widgets_type ON dashboard_widgets(type);
CREATE INDEX idx_widgets_config_gin ON dashboard_widgets USING GIN (config);