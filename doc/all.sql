DROP TABLE if EXISTS `test`;
CREATE TABLE `test` (
    `id` bigint not null comment 'id',
    `name` varchar(50) comment '名称',
    `password` varchar(50) comment '密码',
    primary key(`id`)
) engine=innodb DEFAULT charset=utf8mb4 comment='测试';

INSERT into `test` (id, name, password) values (1, '测试', 'password');



DROP TABLE IF EXISTS `demo`;
CREATE TABLE `demo` (
    `id` bigint not null comment 'id',
    `name` varchar(50) comment '名称',
    primary key(`id`)
) engine=innodb DEFAULT charset=utf8mb4 comment='测试';

INSERT INTO `demo` (id, name) VALUES (1, '测试');



#电子书表
drop table if exists ebook;
create table ebook(
                      id bigint not null comment 'id',
                      name varchar(50) comment '名称',
                      category1_id bigint comment '分类1',
                      category2_id bigint comment '分类2',
                      description varchar(200) comment '描述',
                      cover varchar(200) comment '封面',
                      doc_count int comment '文档数',
                      view_count int comment '阅读数',
                      vote_count int comment '点赞数',
                      primary key (id)
)engine =innodb default charset =utf8mb4 comment '电子书';

insert into ebook(id, name, description) values (1,'Spring Boot 入门官方教程','零基础入门Java开发，企业级开发首选框架');
insert into ebook(id, name, description) values (2,'Vue 入门官方教程','零基础入门Vue开发，企业级开发首选框架');
insert into ebook(id, name, description) values (3,'Python 入门官方教程','零基础入门Python开发，企业级开发首选框架');
insert into ebook(id, name, description) values (4,'MySQL 入门官方教程','零基础入门MySQL开发，企业级开发首选框架');
insert into ebook(id, name, description) values (5,'Oracle 入门官方教程','零基础入门Oracle开发，企业级开发首选框架');