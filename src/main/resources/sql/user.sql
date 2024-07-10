create table user(
	id int primary key auto_increment,
	username varchar(30) not null unique,
	password char(32) not null,
	createTime timestamp default CURRENT_TIMESTAMP,
	updateTime timestamp default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP
);