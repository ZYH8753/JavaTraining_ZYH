create table item
(
	id bigint auto_increment comment '商品id，商品编号'
		primary key,
	cid int null comment '商品类型',
	title varchar(100) null comment '商品名称',
	price decimal(10,2) default 0.00 null comment '商品价格',
	num int default 0 null comment '商品库存数量',
	image varchar(2000) null comment '商品图片',
	status int(1) default 1 null comment '商品状态 1正常 0下架',
	created_time datetime not null default current_timestamp,
	updated_time datetime not null default current_timestamp on update current_timestamp
)comment '商品表' charset=utf8mb4 engine = innodb;
create index cid on item(cid);



create table `order_list`
(
	id int auto_increment
		primary key,
	order_id varchar(50) default '' not null comment '订单id',
	user_id bigint null comment '用户id',
	payment decimal(10,2) null comment '订单总金额',
	payment_type tinyint(1) null comment '支付类型 1在线支付 2货到付款',
	post_fee decimal(10,2) null comment '邮费',
	status int(1) null comment '状态 0未付款 1已付款 2未发货 3已发货 4交易成功 5交易关闭 6交易失败',
	payment_time datetime null comment '付款时间',
	consign_time datetime null comment '发货时间',
	end_time datetime null comment '交易完成时间',
	shipping_name varchar(20) null comment '快递商',
	shipping_code varchar(20) null comment '快递号',
	buyer_message varchar(100) null comment '买家留言',
	buyer_nick varchar(50) null comment '买家昵称',
	buyer_comment tinyint(1) null comment '买家是否已经评价',
	created_time datetime not null default current_timestamp,
	updated_time datetime not null default current_timestamp on update current_timestamp
)comment '订单表' charset = utf8mb4 engine = innodb;
create index order_id on `order` (order_id);
create index user_id on `order` (user_id);



create table order_item
(
	id bigint auto_increment
		primary key,
	order_id varchar(50) not null comment '订单id',
	item_id bigint not null comment '商品id',
	price decimal(10,2) null comment '商品单价',
	num int(10) null comment '商品数量',
	total_price decimal(10,2) null comment '商品总金额',
	created_time datetime not null default current_timestamp,
	updated_time datetime not null default current_timestamp on update current_timestamp
)comment '订单商品表' charset = utf8 engine = innodb;
create index order_id on order_item (order_id);
create index item_id on order_item (item_id);



create table order_shipping
(
	id bigint auto_increment
		primary key,
	order_id varchar(50) not null comment '订单ID',
	receiver_name varchar(20) null comment '收货人姓名',
	receiver_mobile varchar(30) null comment '电话',
	receiver_state varchar(10) null comment '省份',
	receiver_city varchar(10) null comment '城市',
	receiver_district varchar(20) null comment '区/县',
	receiver_address varchar(200) null comment '收货地址',
	receiver_zip_code varchar(6) null comment '邮政编码,如：110001',
	created_time datetime not null default current_timestamp,
	updated_time datetime not null default current_timestamp on update current_timestamp
)comment '订单发货表' charset = utf8mb4 engine = innodb;
create index order_id on order_shipping (order_id);



create table user_role
(
	id int auto_increment
		primary key,
	name varchar(255) null,
	description varchar(255) null,
	created_time datetime not null default current_timestamp,
	updated_time datetime not null default current_timestamp on update current_timestamp
)comment '用户权限表' charset = utf8 engine = innodb;



create table user
(
	id bigint auto_increment
		primary key,
	username varchar(50) unique not null comment '用户名',
	password varchar(32) not null comment '密码',
	phone varchar(20) null comment '手机号',
	email varchar(50) null comment '邮箱',
	sex varchar(2) default '' null comment '性别',
	address varchar(255) null comment '地址',
	role_id int default 0 null comment '权限id',
	created_time datetime not null default current_timestamp,
	updated_time datetime not null default current_timestamp on update current_timestamp
)comment '用户表' charset = utf8mb4 engine = innodb;


