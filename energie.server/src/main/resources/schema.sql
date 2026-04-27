DROP TABLE IF EXISTS history_data;
CREATE TABLE history_data
(
    id                 BIGINT AUTO_INCREMENT  PRIMARY KEY,
    timestamp_hour     TIMESTAMP NOT NULL,
    community_produced DECIMAL(10, 3),
    grid_produces      DECIMAL(10, 3),
    community_used     DECIMAL(10, 3),
    grid_used          DECIMAL(10, 3)
);

DROP TABLE IF EXISTS current_data;
CREATE TABLE current_data
(
    id                 BIGINT AUTO_INCREMENT  PRIMARY KEY,
    timestamp_hour     TIMESTAMP NOT NULL,
    community_depleted DECIMAL(10, 2),
    grid_portion       DECIMAL(10, 2)
);