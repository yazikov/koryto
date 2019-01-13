drop table sensor_range;
drop table sensor;
drop table sensor_value;
drop table sensor_value_storage;
drop table criterion;

/* Граница датчиков */
create table sensor_range (
  start_range float,
  end_range float
);

/* Информация о датчике */
create table sensor(
  id integer,
  length_value float,
  x integer,
  y integer,
  width integer,
  height integer,
  primary key(id)
);
/* Оперативные значения датчиков */
create table sensor_value(
  id integer,
  id_sensor integer,
  value float,
  v_date date,
  v_time time,
  primary key(id)
);
/* Все значения датчиков */
create table sensor_value_storage(
  id serial,
  id_sensor integer,
  value float,
  v_date date,
  v_time time,
  length_value float,
  primary key(id)
);
/* Таблица критериев */
create table criterion(
  id integer,
  start_value float,
  end_value float,
  red int,
  blue int,
  green int,
  intensity float,
  primary key(id)
);

/* Добавлений значений критерия */
insert into criterion values(1,-9999999,1.0,0,255,0,0.9);
insert into criterion values(2,1.0,2.0,5,250,0,0.9);
insert into criterion values(3,2.0,3.0,10,245,0,0.9);
insert into criterion values(4,3.0,4.0,15,240,0,0.9);
insert into criterion values(5,4.0,5.0,20,235,0,0.9);
insert into criterion values(6,5.0,6.0,25,230,0,0.9);
insert into criterion values(7,6.0,7.0,30,225,0,0.9);
insert into criterion values(8,7.0,8.0,35,220,0,0.9);
insert into criterion values(9,8.0,9.0,40,215,0,0.9);
insert into criterion values(10,9.0,10.0,45,210,0,0.9);
insert into criterion values(11,10.0,11.0,50,205,0,0.9);
insert into criterion values(12,11.0,12.0,55,200,0,0.9);
insert into criterion values(13,12.0,13.0,60,195,0,0.9);
insert into criterion values(14,13.0,14.0,65,190,0,0.9);
insert into criterion values(15,14.0,15.0,70,185,0,0.9);
insert into criterion values(16,15.0,16.0,75,180,0,0.9);
insert into criterion values(17,16.0,17.0,80,175,0,0.9);
insert into criterion values(18,17.0,18.0,85,170,0,0.9);
insert into criterion values(19,18.0,19.0,90,165,0,0.9);
insert into criterion values(20,19.0,20.0,95,160,0,0.9);
insert into criterion values(21,20.0,21.0,100,155,0,0.9);
insert into criterion values(22,21.0,22.0,105,150,0,0.9);
insert into criterion values(23,22.0,23.0,110,145,0,0.9);
insert into criterion values(24,23.0,24.0,115,140,0,0.9);
insert into criterion values(25,24.0,25.0,120,135,0,0.9);
insert into criterion values(26,25.0,26.0,125,130,0,0.9);
insert into criterion values(27,26.0,27.0,130,125,0,0.9);
insert into criterion values(28,27.0,28.0,135,120,0,0.9);
insert into criterion values(29,28.0,29.0,140,115,0,0.9);
insert into criterion values(30,29.0,30.0,145,110,0,0.9);
insert into criterion values(31,30.0,31.0,150,105,0,0.9);
insert into criterion values(32,31.0,32.0,155,100,0,0.9);
insert into criterion values(33,32.0,33.0,160,95,0,0.9);
insert into criterion values(34,33.0,34.0,165,90,0,0.9);
insert into criterion values(35,34.0,35.0,170,85,0,0.9);
insert into criterion values(36,35.0,36.0,175,80,0,0.9);
insert into criterion values(37,36.0,37.0,180,75,0,0.9);
insert into criterion values(38,37.0,38.0,185,70,0,0.9);
insert into criterion values(39,38.0,39.0,190,65,0,0.9);
insert into criterion values(40,39.0,40.0,195,60,0,0.9);
insert into criterion values(41,40.0,41.0,200,55,0,0.9);
insert into criterion values(42,41.0,42.0,205,50,0,0.9);
insert into criterion values(43,42.0,43.0,210,45,0,0.9);
insert into criterion values(44,43.0,44.0,215,40,0,0.9);
insert into criterion values(45,44.0,45.0,220,35,0,0.9);
insert into criterion values(46,45.0,46.0,225,30,0,0.9);
insert into criterion values(47,46.0,47.0,230,25,0,0.9);
insert into criterion values(48,47.0,48.0,235,20,0,0.9);
insert into criterion values(49,48.0,49.0,240,15,0,0.9);
insert into criterion values(50,49.0,9999999,245,10,0,0.9);