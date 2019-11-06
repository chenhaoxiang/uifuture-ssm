CREATE TABLE `users_recharge_ub` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '唯一标识码',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `money` decimal(11,2) NOT NULL COMMENT '充值金额-单位元',
  `ub_number` int(11) NOT NULL COMMENT '兑换的U币数量',
  `order_number` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '充值的订单编号,格式为当前年月日时分秒毫秒+随机生成10位数字，如："20170228155316339*****"',
  `pay_type_id` int(11) NOT NULL COMMENT '支付类型id',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `delete_time` int(11) NOT NULL DEFAULT '0',
  `users_pay_id` int(11) NOT NULL COMMENT '用户支付详细信息id',
  PRIMARY KEY (`id`),
  KEY `fk_user_recharge_ub_user_id` (`user_id`),
  KEY `fk_user_recharge_ub_pay_type_id` (`pay_type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户充值U币表，存放用户充值U币信息。';


CREATE TABLE `users_pay` (
  `id` int(11) NOT NULL,
  `money` decimal(11,2) NOT NULL COMMENT '金额',
  `info` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '留言',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '通知邮箱',
  `state` tinyint(4) NOT NULL COMMENT '显示状态 0待审核 1确认显示 2驳回 3通过不展示 4已扫码',
  `pay_type_en_name` varchar(64) NOT NULL COMMENT 'pay_type的en_name，支付类型',
  `pay_num` varchar(128) NOT NULL DEFAULT '' COMMENT '支付标识',
  `custom` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否自定义输入，0否',
  `mobile` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否移动端，0否',
  `device` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '用户支付设备信息',
  `token_num` varchar(64) NOT NULL COMMENT '生成二维码编号标识token',
  `pass_url` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '通过审核的url',
  `back_url` varchar(200) NOT NULL COMMENT '操作后的url',
  `close_url` varchar(200) NOT NULL COMMENT '关闭交易的url',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户支付信息详情表';
