use master
go
if EXISTS ( select name
                from sys.databases
                where name = N'sd_18' )
    drop database sd_18
create database sd_18
go
use sd_18
go

create table [roles] (
	[id] int primary key identity(1, 1),
	[name] nvarchar(max) not null,
	[created_date] datetime,
	[updated_date] datetime
);
go

create table [staffs] (
	[id] int primary key identity(1, 1),
	[name] nvarchar(max),
	[email] nvarchar(max),
	[phone] nvarchar(10),
	[avatar] nvarchar(max),
	[address] nvarchar(max),
	[password] nvarchar(max),
	[status] int default 0,
	[created_date] datetime,
	[updated_date] datetime,
	[role_id] int,
	foreign key (role_id) references [roles](id),
);
go

create table [brands] (
	[id] int primary key identity(1, 1),
	[name] nvarchar(max) not null,
	[status] int default 0,
	[created_date] datetime,
	[updated_date] datetime
);
go

create table [colors] (
	[id] int primary key identity(1, 1),
	[name] nvarchar(max) not null,
	[status] int default 0,
	[created_date] datetime,
	[updated_date] datetime
);
go

create table [categories] (
	[id] int primary key identity(1, 1),
	[name] nvarchar(max) not null,
	[status] int default 0,
	[created_date] datetime,
	[updated_date] datetime
);
go

create table [materials] (
	[id] int primary key identity(1, 1),
	[name] nvarchar(max) not null,
	[status] int default 0,
	[created_date] datetime,
	[updated_date] datetime
);
go

create table [sizes] (
	[id] int primary key identity(1, 1),
	[name] nvarchar(max) not null,
	[status] int default 0,
	[shirt_length] int,
	[shirt_width] int,
	[created_date] datetime,
	[updated_date] datetime
);
go

create table [discounts] (
	[id] int primary key identity(1, 1),
	[name] nvarchar(max) not null,
	[status] int default 0,
	[discount] int,
	[start_date] date,
	[end_date] date,
	[created_date] datetime,
	[updated_date] datetime,
);
go

create table [products] (
	[id] int primary key identity(1, 1),
	[name] nvarchar(max),
	[status] int default 0,
	[description] nvarchar(max),
	[created_date] datetime,
	[updated_date] datetime,
	[category_id] int,
	[brand_id] int,
	[material_id] int,
	[discount_id] int,
	foreign key (category_id) references [categories](id),
	foreign key (brand_id) references [brands](id),
	foreign key (material_id) references [materials](id),
	foreign key (discount_id) references [discounts](id),
);
go

create table [product_details] (
	[id] int primary key identity(1, 1),
	[quantity] int,
	[cost] float,
	[price] float,
	[weight] float,
	[status] int default 0,
	[created_date] datetime,
	[updated_date] datetime,
	[product_id] int,
	[size_id] int,
	[color_id] int,
	foreign key (product_id) references [products](id),
	foreign key (size_id) references [sizes](id),
	foreign key (color_id) references [colors](id),
);
go

create table [images] (
	[id] int primary key identity(1, 1),
	[product_id] int,
	[name] nvarchar(max),
	[url_image] nvarchar(max),
	[status] int,
	[created_date] datetime,
	[updated_date] datetime,
	foreign key (product_id) references [products](id),
);
go

create table [customers] (
	[id] int primary key identity(1, 1),
	[name] nvarchar(max),
	[phone] nvarchar(10),
	[avatar] nvarchar(max),
	[password] nvarchar(max),
	[status] int,
	[email] nvarchar(max),
	[created_date] datetime,
	[updated_date] datetime,
);
go

create table [addresses] (
	[id] int primary key identity(1, 1),
	[customer_id] int,
	[ward] nvarchar(max),
	[district] nvarchar(max),
	[city] nvarchar(max),
	[full_address] nvarchar(max),
	[status] int,
	[phone] nvarchar(10),
	[created_date] datetime,
	[updated_date] datetime,
	foreign key (customer_id) references [customers](id),
);
go


create table [evaluates] (
	[id] int primary key identity(1, 1),
	[star] int,
	[content] nvarchar(max),
	[created_date] datetime,
	[updated_date] datetime,
	[customer_id] int,
	[product_detail_id] int,
	foreign key (customer_id) references [customers](id),
	foreign key (product_detail_id) references [product_details](id),
);
go

create table [carts] (
	[id] int primary key identity(1, 1),
	[status] int,
	[created_date] datetime,
	[updated_date] datetime,
	[customer_id] int,
	foreign key (customer_id) references [customers](id),
);
go

create table [cart_details] (
	[id] int primary key identity(1, 1),
	[quantity] int,
	[price] float,
	[status] int,
	[created_date] datetime,
	[updated_date] datetime,
	[cart_id] int,
	[product_detail_id] int,
	foreign key (cart_id) references [carts](id),
	foreign key (product_detail_id) references [product_details](id),
);
go

create table [transactions] (
	[id] int primary key identity(1, 1),
	[name] nvarchar(max),
	[description] nvarchar(max),
	[status] int,
	[created_date] datetime,
	[updated_date] datetime,
);
go

create table [orders] (
	[id] int primary key identity(1, 1),
	[phone] nvarchar(10),
	[username] nvarchar(max),
	[total_price] float,
	[ship_cost] float,
	[weight] float,
	[note] nvarchar(max),
	[shopping] nvarchar(max),
	[address] nvarchar(max),
	[status] int,
	[confirm_date] datetime,
	[ship_date] datetime,
	[success_date] datetime,
	[created_date] datetime,
	[updated_date] datetime,
	[staff_id] int,
	[customer_id] int,
	[transaction_id] int,
	foreign key (staff_id) references [staffs](id),
	foreign key (customer_id) references [customers](id),
	foreign key (transaction_id) references [transactions](id),
);
go

create table [order_details] (
	[id] int primary key identity(1, 1),
	[quantity] int,
	[price] float,
	[status] int,
	[created_date] datetime,
	[updated_date] datetime,
	[order_id] int,
	[product_detail_id] int,
	foreign key (order_id) references [orders](id),
	foreign key (product_detail_id) references [product_details](id),
);
go