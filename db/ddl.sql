DROP TABLE IF EXISTS car_rental.demo;

CREATE TABLE car_rental.demo (
	id bigint(20) auto_increment NOT NULL COMMENT 'primary key',
	content varchar(100) NULL COMMENT 'content',
	CONSTRAINT demo_PK PRIMARY KEY (id)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_0900_ai_ci
COMMENT='demo';
