create table userInfo(
	id int primary key auto_increment,
	uid int not null,
	nickname varchar(30),
	sex enum('null','male', 'female'),
	birth date,
	email varchar(40),
	qq varchar(30),
	wechat varchar(30),
	updateTime timestamp default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
	foreign key(uid) references user(id) on update cascade on delete cascade
);
