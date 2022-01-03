create table users
(
	user_id bigint auto_increment comment '用户id'
		primary key,
	name varchar(100) not null comment '用户名',
	created_time datetime not null default current_timestamp,
	updated_time datetime not null default current_timestamp on update current_timestamp
)comment '账户表' charset=utf8mb4 engine = innodb;

create table dollar_account
(
	id bigint auto_increment comment '账户id'
		primary key,
	user_id bigint not null unique comment '用户id',
	price int default 0 not null comment '美元账户余额',
	created_time datetime not null default current_timestamp,
	updated_time datetime not null default current_timestamp on update current_timestamp
)comment '美元账户表' charset=utf8mb4 engine = innodb;

create table rmb_account
(
	id bigint auto_increment comment '账户id'
		primary key,
	user_id bigint not null unique comment '用户id',
	price int default 0 not null comment '人民币账户余额',
	created_time datetime not null default current_timestamp,
	updated_time datetime not null default current_timestamp on update current_timestamp
)comment '人民币账户表' charset=utf8mb4 engine = innodb;

create table freeze
(
	id bigint auto_increment comment '冻结id'
		primary key,
	user_id bigint not null comment 'user_id',
	dollar_price int default 0 not null comment '美元冻结余额',
	rmb_price int default 0 not null comment '人民币冻结余额',
	created_time datetime not null default current_timestamp,
	updated_time datetime not null default current_timestamp on update current_timestamp
)comment '资产冻结表' charset=utf8mb4 engine = innodb;


