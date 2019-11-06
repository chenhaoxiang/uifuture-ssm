CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `username` varchar(32) NOT NULL COMMENT '用户名',
  `money` int(22) NOT NULL DEFAULT '0' COMMENT '金额，单位：分  ',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `delete_time` int(22) NOT NULL DEFAULT '0' COMMENT '删除时间，0-未删除 ',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uniq_username` (`username`) USING BTREE COMMENT '用户名唯一建'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `ssm_test`.`users` (`username`, `money`, `create_time`, `update_time`, `delete_time`) VALUES ('a', 100000, DEFAULT, DEFAULT, DEFAULT);
INSERT INTO `ssm_test`.`users` (`username`, `money`, `create_time`, `update_time`, `delete_time`) VALUES ('b', 100000, DEFAULT, DEFAULT, DEFAULT);
