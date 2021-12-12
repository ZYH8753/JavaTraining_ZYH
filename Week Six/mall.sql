create table item
(
	id bigint not null comment '商品id，商品编号'
		primary key,
	title varchar(100) null comment '商品标题',
	sell_point varchar(100) null comment '商品卖点',
	price decimal(10,2) default 0.00 null comment '商品价格',
	num int null comment '库存数量',
	limit_num int null comment '售卖数量限制',
	image varchar(2000) null comment '商品图片',
	cid bigint(11) null comment '所属分类',
	status int(1) default 1 null comment '商品状态 1正常 0下架',
	created_time datetime not null,
	updated_time datetime not null
)
comment '商品表' charset=utf8;

create index cid
	on item (cid);

create index status
	on item (status);

create table `order`
(
	id int auto_increment
		primary key,
	order_id varchar(50) default '' not null comment '订单id',
	user_id bigint null comment '用户id',
	payment decimal(10,2) null comment '实付金额',
	payment_type int(1) null comment '支付类型 1在线支付 2货到付款',
	post_fee decimal(10,2) null comment '邮费',
	status int(1) null comment '状态 0未付款 1已付款 2未发货 3已发货 4交易成功 5交易关闭 6交易失败',
	create_time datetime null comment '订单创建时间',
	update_time datetime null comment '订单更新时间',
	payment_time datetime null comment '付款时间',
	consign_time datetime null comment '发货时间',
	end_time datetime null comment '交易完成时间',
	shipping_name varchar(20) null comment '快递商',
	shipping_code varchar(20) null comment '快递号',
	buyer_message varchar(100) null comment '买家留言',
	buyer_nick varchar(50) null comment '买家昵称',
	buyer_comment tinyint(1) null comment '买家是否已经评价',
	created_time datetime not null,
	updated_time datetime not null
)
comment '订单表' collate=utf8_bin;

create index buyer_nick
	on `order` (buyer_nick);

create index create_time
	on `order` (create_time);

create index order_id
	on `order` (order_id);

create index payment_type
	on `order` (payment_type);

create index status
	on `order` (status);

create table order_item
(
	id bigint auto_increment
		primary key,
	order_id varchar(50) not null comment '订单id',
	item_id varchar(50) not null comment '商品id',
	price decimal(10,2) null comment '商品单价',
	num int(10) null comment '商品数量',
	total_price decimal(10,2) null comment '商品总金额',
	created_time datetime not null,
	updated_time datetime not null
)
comment '订单商品表' collate=utf8_bin;

create index item_id
	on order_item (item_id);

create index order_id
	on order_item (order_id);

create table order_shipping
(
	id bigint auto_increment
		primary key,
	order_id varchar(50) not null comment '订单ID',
	receiver_name varchar(20) null comment '收货人全名',
	receiver_mobile varchar(30) null comment '电话',
	receiver_state varchar(10) null comment '省份',
	receiver_city varchar(10) null comment '城市',
	receiver_district varchar(20) null comment '区/县',
	receiver_address varchar(200) null comment '收货地址',
	receiver_zip_code varchar(6) null comment '邮政编码,如：310001',
	created_time datetime not null,
	updated_time datetime not null
)
comment '订单发货表' charset=utf8;

create index order_id
	on order_shipping (order_id);

create table role
(
	id int auto_increment
		primary key,
	name varchar(255) null,
	description varchar(255) null,
	created_time datetime not null,
	updated_time datetime not null
)
comment '用户权限表' charset=utf8;

create table user
(
	id bigint auto_increment
		primary key,
	username varchar(50) not null comment '用户名',
	password varchar(32) not null comment '密码',
	phone varchar(20) null comment '手机号',
	email varchar(50) null comment '邮箱',
	sex varchar(2) default '' null comment '性别',
	address varchar(255) null comment '地址',
	role_id int default 0 null comment '权限id',
	created_time datetime not null,
	updated_time datetime not null,
	constraint email
		unique (email),
	constraint phone
		unique (phone),
	constraint username
		unique (username)
)
comment '用户表' charset=utf8;

