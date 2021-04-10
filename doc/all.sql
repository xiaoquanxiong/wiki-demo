DROP TABLE if EXISTS `test`;
CREATE TABLE `test` (
    `id` bigint not null comment 'id',
    `name` varchar(50) comment '名称',
    `password` varchar(50) comment '密码',
    primary key(`id`)
) engine=innodb DEFAULT charset=utf8mb4 comment='测试';

INSERT into `test` (id, name, password) values (1, '测试', 'password');