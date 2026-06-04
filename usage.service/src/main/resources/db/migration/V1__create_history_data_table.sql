CREATE TABLE IF NOT EXISTS history_data (
    id                  BIGSERIAL PRIMARY KEY,
    timestamp_hour      TIMESTAMP NOT NULL,
    community_produced  DECIMAL(10, 3),
    community_used      DECIMAL(10, 3),
    grid_used           DECIMAL(10, 3)
);