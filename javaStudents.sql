
--create table account(
	
--	username varchar(20) ,
--	Passwords varchar(10)
--);

drop database Student_List

create database Student_List

create table Students(
	studentid varchar(10) primary key,
	Firstname varchar(10),
	Lastname varchar(10),
	Gender char(10),
	AccadamicYear varchar(10),
	Spassword varchar(20)
);
create table course(
	courseid varchar(10) primary key,
	coursename varchar(10),
	credithour int
);
create table Studentcourse(
	studentid varchar(10),
	courseid varchar(10),
	Foreign key (studentid) references Students(studentid),
	Foreign key (courseid) references course(courseid)
);
create table Grade(
	studentid varchar(10),
	courseid varchar(10),
	Grade char(1)
	Foreign key (studentid) references Students(studentid),
	Foreign key (courseid) references course(courseid)
);

create table GPA(
	studentid varchar(10),
	GPA float,
	Foreign key (studentid) references Students(studentid)
);


insert into course(courseid,coursename,credithour)values
									('math1','Maths',4),
									('java1','java',3),
									('data1','database',3),
									('signal1','signal',3),
									('algo1','Algorithm',3);


insert into GPA(studentid,GPA)values('ugr/12341',3.4);

drop table Students

insert into Students values('ugr/125/45','beti','getachew','Female','2 year','1234');
insert into grade (studentid,courseid,Grade)values('ugr/125/45','java1','B');





select *from Students
select *from course
select *from Grade
select *from GPA

delete from Students where age=0
