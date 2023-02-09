create database if not exists moviedb;

create table if not exists moviedb.admins(
ID integer primary key not null auto_increment,
name varchar(40) not null,
gender varchar(10) null,
dob date not null,
phone varchar(15) not null,
email varchar(40) not null unique,
password varchar(20) not null) auto_increment=1000;

create table if not exists moviedb.customers(
ID integer primary key not null auto_increment,
name varchar(40) not null,
gender varchar(10) null,
dob date not null,
phone varchar(15) not null,
email varchar(40) not null unique,
password varchar(20) not null) auto_increment=10000;

create table if not exists moviedb.movies(
ID integer not null auto_increment primary key,
name varchar(50) not null unique,
genre varchar(40),
year int,
description varchar(1000),
runtime varchar(20) ,
imgUrl varchar(1000) ,
vidUrl varchar(1000)
)auto_increment=1000000;

CREATE TABLE if not exists moviedb.bookings(
ID INT NOT null auto_increment primary key,
customerid INT NOT NULL,
FOREIGN KEY (customerid) REFERENCES moviedb.customers(ID),
date date
);

create table if not exists moviedb.movies_bookings(
ID integer not null auto_increment primary key,
moviesid int not null,
foreign key(moviesid) references moviedb.movies(ID),
bookingid int not null,
foreign key(bookingid) references moviedb.bookings(ID)
);