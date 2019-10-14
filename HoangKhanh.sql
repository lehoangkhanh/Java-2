create database HoangKhanh
go
use HoangKhanh
go
create table Student(
	id int identity(1,1) primary key not null,
	name nvarchar(50),
	age int
)
go
insert into Student values(N'Lê Hoàng Khanh', 21)
insert into Student values(N'Đỗ Thị Thúy Hằng',21)
insert into Student values(N'Lê Tú Trinh',21)
-------------------------------------------------------
create table Class(
	id int identity(1,1) primary key not null,
	class_name nvarchar(50),
)
go
insert into Class values(N'Lập trình Java')
insert into Class values(N'Cấu trúc dữ liệu và giải thuật')
insert into Class values(N'Lập trình PHP')
--------------------------------------------------------
create table DayOff(
	id int identity(1,1) primary key not null,
	student_id int foreign key references Student(id),
	day_off date,
)
go 
insert into DayOff values(1,convert(datetime,'14/10/2019',103))
insert into DayOff values(1,convert(datetime,'15/10/2019',103))
insert into DayOff values(2, convert(datetime,'15/10/2019',103))

