CREATE TABLE rides (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    passenger_id BIGINT NOT NULL,
    driver_id BIGINT,
    pickup_latitude DOUBLE NOT NULL,
    pickup_longitude DOUBLE NOT NULL,
    drop_latitude DOUBLE NOT NULL,
    drop_longitude DOUBLE NOT NULL,
    fare DOUBLE,
    status VARCHAR(50),
    created_at DATETIME,
    updated_at DATETIME
);