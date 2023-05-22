
/* Lenh xoa Database */
DROP DATABASE IF EXISTS shopping_online;
/* Lenh tao Database */
CREATE DATABASE IF NOT EXISTS shopping_online;
/* Lenh su dung Database */
USE shopping_online;
-- Mo hinh du lieu
-- 1. User có phân quyền: lưu thông tin người quản lý cùng với khách hàng
--    mã user, user name, password, email, địa chỉ, số điện thoại, ví tiền, avatar
DROP TABLE IF EXISTS users;
CREATE TABLE IF NOT EXISTS users(
  user_id             int AUTO_INCREMENT PRIMARY KEY ,
  email               varchar(50) NOT NULL UNIQUE key ,
  username            varchar(20) NOT NULL UNIQUE key,
  password            varchar(120) NOT NULL,
  adress			  varchar(50) ,
  phone 			  varchar(50),
  wallet_cast         decimal(15,2) DEFAULT 0, -- vi tien
  avatar              varchar(555) DEFAULT NULL
);

-- 2. Role: phân quyền
--    mã role, tên role
DROP TABLE IF EXISTS roles;
CREATE TABLE IF NOT EXISTS roles(
  role_id             int PRIMARY KEY AUTO_INCREMENT,
  role_name         varchar(20) DEFAULT 'ROLE_USER'
);

-- 3. users_role:
--    mã role, mã user
DROP TABLE IF EXISTS user_role;
CREATE TABLE IF NOT EXISTS user_role(
  user_id        int NOT NULL,
  role_id       int NOT NULL,
  PRIMARY KEY (user_id, role_id),
  FOREIGN KEY user_role( role_id) REFERENCES roles( role_id ),
  FOREIGN KEY user_role( user_id) REFERENCES users(user_id )
);
-- . Nhóm sản phẩm: lưu thông tin theo từng nhóm
--    mã nhóm sản phẩm, tên nhóm sản phẩm, ghi chú

DROP TABLE IF EXISTS catalog;
CREATE TABLE IF NOT EXISTS catalog(
	catalog_id          int primary key auto_increment,
    catalog_name 		varchar(100)
);
DROP TABLE IF EXISTS carts;
CREATE TABLE IF NOT EXISTS carts(
cart_id   int primary key auto_increment,
user_id int not null,
is_paid  BIT(1),
amount decimal(15,2),
comment text(150),
created_date datetime default now(),
FOREIGN KEY carts(user_id) REFERENCES users (user_id )
);

DROP TABLE IF EXISTS product;
CREATE TABLE IF NOT EXISTS product(
	product_id      int primary key auto_increment,
    product_name    varchar(100),
    catalog_id      int ,
    price          decimal(15,1) not null,
    discount       int,
    description    varchar(255) DEFAULT NULL,
	img_url        varchar(555) DEFAULT NULL,
    FOREIGN KEY product(catalog_id) REFERENCES catalog (catalog_id )
);

DROP TABLE IF EXISTS cart_product;
CREATE TABLE IF NOT EXISTS cart_product(
cart_id int not null,
product_id int not null,
 PRIMARY KEY (cart_id, product_id),
  FOREIGN KEY cart_product( cart_id) REFERENCES carts( cart_id ),
  FOREIGN KEY cart_product( product_id) REFERENCES product(product_id )
);


insert into roles(role_name) value('ROLE_ADMIN'),('ROLE_MANAGER'),('ROLE_USER');

--  password: 123456
insert into users(email, username, password, adress, phone, wallet_cast) value('chuongnguyen.dc@gmail.com', 'chuongnguyen', '$2a$12$aFcABuun7xramy0HoWXZkurTAUgpaFf7MTijS/N2qjGaUC.rrcpVa', 'Ho Chi Minh', '0746935283','1000000000');
insert into users(email, username, password, adress, phone, wallet_cast) value('haiyen1996@gmail.com', 'YenChan', '$2a$12$aFcABuun7xramy0HoWXZkurTAUgpaFf7MTijS/N2qjGaUC.rrcpVa', 'Bac Giang', '0745635283','100000000');
insert into users(email, username, password, adress, phone, wallet_cast) value('thinguyen110596@gmail.com', 'ThiThi', '$2a$12$aFcABuun7xramy0HoWXZkurTAUgpaFf7MTijS/N2qjGaUC.rrcpVa', 'Quang Ninh', '0746922223','10000000');
insert into users(email, username, password, adress, phone, wallet_cast) value('khanhquoc290508@gmail.com', 'Khanh Quoc', '$2a$12$aFcABuun7xramy0HoWXZkurTAUgpaFf7MTijS/N2qjGaUC.rrcpVa', 'Bac Ninh', '0746542456','10000000');
insert into users(email, username, password, adress, phone, wallet_cast) value('viennguyen@gmail.com', 'Vien Nguyen', '$2a$12$aFcABuun7xramy0HoWXZkurTAUgpaFf7MTijS/N2qjGaUC.rrcpVa', 'Ho Chi Minh', '0746555283','10000000000');
insert into users(email, username, password, adress, phone, wallet_cast) value('vuthithao@gmail.com', 'Vu Thao', '$2a$12$aFcABuun7xramy0HoWXZkurTAUgpaFf7MTijS/N2qjGaUC.rrcpVa', 'Ha Noi', '0745630003','1000000000');
insert into users(email, username, password, adress, phone, wallet_cast) value('huunguyen@gmail.com', 'Nguyen Huu', '$2a$12$aFcABuun7xramy0HoWXZkurTAUgpaFf7MTijS/N2qjGaUC.rrcpVa', 'Ha Noi', '0864422223','10000000');
insert into users(email, username, password, adress, phone, wallet_cast) value('nguyenhopp@gmail.com', 'Nguyen Hopp', '$2a$12$aFcABuun7xramy0HoWXZkurTAUgpaFf7MTijS/N2qjGaUC.rrcpVa', 'Hung Yen', '03365424567','10000000');
insert into users(email, username, password, adress, phone, wallet_cast) value('liennguyen83@gmail.com', 'Lien Nguyen', '$2a$12$aFcABuun7xramy0HoWXZkurTAUgpaFf7MTijS/N2qjGaUC.rrcpVa', 'Lang Son', '02965424567','100000000');
insert into users(email, username, password, adress, phone, wallet_cast) value('vuongnv011296@gmail.com', 'Vuong Nguyen', '$2a$12$aFcABuun7xramy0HoWXZkurTAUgpaFf7MTijS/N2qjGaUC.rrcpVa', 'Bac Giang', '07040234558','100000000');


insert into user_role(user_id, role_id) value(1,1), (2,2), (3,3),(4,3),(5,3), (6,3), (7,3),(8,3),(9,3);

insert into catalog(catalog_name) values("Apple"), ("Samsung"), ("Oppo"), ("Xiaomi");

insert into carts(user_id, is_paid, comment, amount) values(4, 0,"abcxyz",100000);


 insert into product(product_name, catalog_id, price, discount, description, img_url)          value('Iphone 14 Pro Max',					'1',   '33990000.0',	'16',	'6.7" Super Retina XDR',					'https://cdn.tgdd.vn/Products/Images/42/251192/TimerThumb/iphone-14-pro-max.jpg');
 insert into product(product_name, catalog_id, price, discount, description, img_url)          value('Iphone 14 Pro',						'1',   '30990000.0',	'10',	'6.1" Super Retina XDR',					'https://cdn.tgdd.vn/Products/Images/42/247508/TimerThumb/iphone-14-pro.jpg');
 insert into product(product_name, catalog_id, price, discount, description, img_url)          value('Iphone 14 Plus',						'1',   '27990000.0',	'15',	'6.7" Super Retina XDR',					'https://cdn.tgdd.vn/Products/Images/42/245545/TimerThumb/iphone-14-plus.jpg');
 insert into product(product_name, catalog_id, price, discount, description, img_url)          value('Samsung Galaxy Z Flip4',				'2',   '23990000.0',	'12',	'Chính 6.7" & Phụ 1.9" Full HD+',			'https://cdn.tgdd.vn/Products/Images/42/258047/TimerThumb/samsung-galaxy-z-flip4.jpg');
 insert into product(product_name, catalog_id, price, discount, description, img_url)          value('Samsung Galaxy S22 Ultra',			'2',   '30990000.0',	'16',	'6.8" Quad HD+ (2K+)',						'https://cdn.tgdd.vn/Products/Images/42/235838/Galaxy-S22-Ultra-Burgundy-600x600.jpg');
 insert into product(product_name, catalog_id, price, discount, description, img_url)          value('Samsung Galaxy A23',					'2',   '5690000.0',		'7',	'Full HD+',									'https://cdn.tgdd.vn/Products/Images/42/262650/TimerThumb/samsung-galaxy-a23-(6).jpg');
 insert into product(product_name, catalog_id, price, discount, description, img_url)          value('Samsung Galaxy Z Fold4',				'2',   '40990000.0',	'7',	'Chính 7.6" & Phụ 6.2" Quad HD+ (2K+)',		'https://cdn.tgdd.vn/Products/Images/42/250625/TimerThumb/samsung-galaxy-z-fold4.jpg');
 insert into product(product_name, catalog_id, price, discount, description, img_url)          value('OPPO Reno8 series',					'3',   '18990000.0',	'7',	'6.7" Full HD+',							'https://cdn.tgdd.vn/Products/Images/42/260546/TimerThumb/oppo-reno8-pro.jpg');
 insert into product(product_name, catalog_id, price, discount, description, img_url)          value('OPPO Find X5 Pro 5G',					'3',   '32990000.0',	'18',	'6.7" Quad HD+ (2K+)',						'https://cdn.tgdd.vn/Products/Images/42/250622/TimerThumb/oppo-find-x5-pro-(2).jpg');
 insert into product(product_name, catalog_id, price, discount, description, img_url)          value('OPPO A55',							'3',   '4490000.0',		'17',	'6.5" HD+',									'https://cdn.tgdd.vn/Products/Images/42/249944/TimerThumb/oppo-a55-4g-(10).jpg');
 insert into product(product_name, catalog_id, price, discount, description, img_url)          value('Xiaomi 12T Pro 5G',					'4',   '16990000.0',	'0',	'6.67" 1.5K',								'https://cdn.tgdd.vn/Products/Images/42/279066/xiaomi-12t-pro-thumb-bac-1-600x600.jpg');
 insert into product(product_name, catalog_id, price, discount, description, img_url)          value('Xiaomi 12 series',					'4',   '19990000.0',	'27',	'6.28" Full HD+',							'https://cdn.tgdd.vn/Products/Images/42/234621/Xiaomi-12-xam-thumb-mau-600x600.jpg');
 insert into product(product_name, catalog_id, price, discount, description, img_url)          value('Xiaomi Redmi Note 11',				'4',   '4690000.0',		'6',	'6.43" Full HD+',							'https://cdn.tgdd.vn/Products/Images/42/269831/TimerThumb/xiaomi-redmi-note-11-4gb-64gb-(12).jpg');

insert into cart_product(cart_id, product_id) value (1,2), (1,4), (1,9);


