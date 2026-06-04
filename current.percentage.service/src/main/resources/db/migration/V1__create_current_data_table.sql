CREATE TABLE IF NOT EXISTS current_data
(
    id                 BIGSERIAL PRIMARY KEY,
    timestamp_hour     TIMESTAMP NOT NULL,
    community_depleted DECIMAL(10, 2),
    grid_portion       DECIMAL(10, 2)
);