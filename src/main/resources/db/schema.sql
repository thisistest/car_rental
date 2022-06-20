DROP TABLE if EXISTS tb_car_model;
DROP TABLE if EXISTS tb_car;
DROP TABLE if EXISTS tb_car_time_slot;
DROP TABLE if EXISTS tb_order;

-- car_rental.tb_car_model definition

CREATE TABLE `tb_car_model` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'primary key',
  `name` varchar(100) NOT NULL,
  `image_url` varchar(100) DEFAULT NULL,
  `is_deleted` bigint NOT NULL DEFAULT '0' COMMENT 'soft delete flag, 0: undeleted, otherwise delete unix timestamp',
  `created_by` bigint NOT NULL COMMENT 'creator user id',
  `created_time` datetime NOT NULL COMMENT 'created time',
  `updated_by` bigint NOT NULL COMMENT 'updater user id',
  `updated_time` datetime NOT NULL COMMENT 'updated time',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='car model';

-- car_rental.tb_car definition

CREATE TABLE `tb_car` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `car_model_id` bigint NOT NULL,
  `is_deleted` bigint NOT NULL DEFAULT '0' COMMENT 'soft delete flag, 0: undeleted, otherwise delete unix timestamp',
  `created_by` bigint NOT NULL COMMENT 'creator user id',
  `created_time` datetime NOT NULL COMMENT 'created time',
  `updated_by` bigint NOT NULL COMMENT 'updater user id',
  `updated_time` datetime NOT NULL COMMENT 'updated time',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='car';

-- car_rental.tb_order definition

CREATE TABLE `tb_order` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint NOT NULL,
  `car_id` bigint DEFAULT NULL,
  `status` tinyint DEFAULT NULL,
  `start_time` datetime NOT NULL,
  `end_time` datetime NOT NULL,
  `is_deleted` bigint NOT NULL DEFAULT '0' COMMENT 'soft delete flag, 0: undeleted, otherwise delete unix timestamp',
  `created_by` bigint NOT NULL COMMENT 'creator user id',
  `created_time` datetime NOT NULL COMMENT 'created time',
  `updated_by` bigint NOT NULL COMMENT 'updater user id',
  `updated_time` datetime NOT NULL COMMENT 'updated time',
  PRIMARY KEY (`id`),
  KEY `tb_order_start_time_IDX` (`start_time`,`car_id`) USING BTREE,
  KEY `tb_order_end_time_IDX` (`end_time`,`car_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='order';

-- car_rental.tb_car_time_slot definition

CREATE TABLE `tb_car_time_slot` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `car_id` bigint NOT NULL,
  `car_model_id` bigint NOT NULL,
  `status` tinyint NOT NULL COMMENT '0 unused, 1 used',
  `order_id` bigint DEFAULT NULL,
  `time_slot` datetime NOT NULL,
  `is_deleted` bigint NOT NULL DEFAULT '0' COMMENT 'soft delete flag, 0: undeleted, otherwise delete unix timestamp',
  `created_by` bigint NOT NULL COMMENT 'creator user id',
  `created_time` datetime NOT NULL COMMENT 'created time',
  `updated_by` bigint NOT NULL COMMENT 'updater user id',
  `updated_time` datetime NOT NULL COMMENT 'updated time',
  PRIMARY KEY (`id`),
  KEY `tb_car_time_slot_order_id_IDX` (`order_id`) USING BTREE,
  KEY `tb_car_time_slot_car_model_id_IDX` (`car_model_id`,`time_slot`,`car_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;