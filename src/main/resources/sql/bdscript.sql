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

/* Добавление границ датчиков */
insert into sensor_range values(16.8,28.8);

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

/* Загрузка данных о датчиках */
insert into sensor values(1,16.80,117,664,96,96);
insert into sensor values(2,17.00,117,568,96,96);
insert into sensor values(3,17.20,117,472,96,96);
insert into sensor values(4,17.40,117,376,96,96);
insert into sensor values(5,17.60,117,280,96,96);
insert into sensor values(6,17.80,117,184,96,96);
insert into sensor values(7,18.00,117,88,192,96);
insert into sensor values(8,18.20,213,184,96,96);
insert into sensor values(9,18.40,213,280,96,96);
insert into sensor values(10,18.60,213,376,96,96);
insert into sensor values(11,18.80,213,472,96,96);
insert into sensor values(12,19.00,213,568,96,96);
insert into sensor values(13,19.20,213,664,192,96);
insert into sensor values(14,19.40,309,568,96,96);
insert into sensor values(15,19.60,309,472,96,96);
insert into sensor values(16,19.80,309,376,96,96);
insert into sensor values(17,20.00,309,280,96,96);
insert into sensor values(18,20.20,309,184,96,96);
insert into sensor values(19,20.40,309,88,192,96);
insert into sensor values(20,20.60,405,184,96,96);
insert into sensor values(21,20.80,405,280,96,96);
insert into sensor values(22,21.00,405,376,96,96);
insert into sensor values(23,21.20,405,472,96,96);
insert into sensor values(24,21.40,405,568,96,96);
insert into sensor values(25,21.60,405,664,192,96);
insert into sensor values(26,21.80,501,568,96,96);
insert into sensor values(27,22.00,501,472,96,96);
insert into sensor values(28,22.20,501,376,96,96);
insert into sensor values(29,22.40,501,280,96,96);
insert into sensor values(30,22.60,501,184,96,96);
insert into sensor values(31,22.80,501,88,192,96);
insert into sensor values(32,23.00,597,184,96,96);
insert into sensor values(33,23.20,597,280,96,96);
insert into sensor values(34,23.40,597,376,96,96);
insert into sensor values(35,23.60,597,472,96,96);
insert into sensor values(36,23.80,597,568,96,96);
insert into sensor values(37,24.00,597,664,192,96);
insert into sensor values(38,24.20,693,568,96,96);
insert into sensor values(39,24.40,693,472,96,96);
insert into sensor values(40,24.60,693,376,96,96);
insert into sensor values(41,24.80,693,280,96,96);
insert into sensor values(42,25.00,693,184,96,96);
insert into sensor values(43,25.20,693,88,192,96);
insert into sensor values(44,25.40,789,184,96,96);
insert into sensor values(45,25.60,789,280,96,96);
insert into sensor values(46,25.80,789,376,96,96);
insert into sensor values(47,26.00,789,472,96,96);
insert into sensor values(48,26.20,789,568,96,96);
insert into sensor values(49,26.40,789,664,192,96);
insert into sensor values(50,26.60,885,568,96,96);
insert into sensor values(51,26.80,885,472,96,96);
insert into sensor values(52,27.00,885,376,96,96);
insert into sensor values(53,27.20,885,280,96,96);
insert into sensor values(54,27.40,885,184,96,96);
insert into sensor values(55,27.60,885,88,192,96);
insert into sensor values(56,27.80,981,184,96,96);
insert into sensor values(57,28.00,981,280,96,96);
insert into sensor values(58,28.20,981,376,96,96);
insert into sensor values(59,28.40,981,472,96,96);
insert into sensor values(60,28.60,981,568,96,96);
insert into sensor values(61,28.80,981,664,96,96);

/*Загрузка значений датчиков в оперативную таблицу*/
insert into sensor_value values (1,1,1.0,CURRENT_DATE,CURRENT_TIME);
insert into sensor_value values (2,2,2.0,CURRENT_DATE,CURRENT_TIME);
insert into sensor_value values (3,3,3.0,CURRENT_DATE,CURRENT_TIME);
insert into sensor_value values (4,4,4.0,CURRENT_DATE,CURRENT_TIME);
insert into sensor_value values (5,5,5.0,CURRENT_DATE,CURRENT_TIME);
insert into sensor_value values (6,6,6.0,CURRENT_DATE,CURRENT_TIME);
insert into sensor_value values (7,7,7.0,CURRENT_DATE,CURRENT_TIME);
insert into sensor_value values (8,8,8.0,CURRENT_DATE,CURRENT_TIME);
insert into sensor_value values (9,9,9.0,CURRENT_DATE,CURRENT_TIME);
insert into sensor_value values (10,10,10.0,CURRENT_DATE,CURRENT_TIME);
insert into sensor_value values (11,11,11.0,CURRENT_DATE,CURRENT_TIME);
insert into sensor_value values (12,12,12.0,CURRENT_DATE,CURRENT_TIME);
insert into sensor_value values (13,13,13.0,CURRENT_DATE,CURRENT_TIME);
insert into sensor_value values (14,14,14.0,CURRENT_DATE,CURRENT_TIME);
insert into sensor_value values (15,15,15.0,CURRENT_DATE,CURRENT_TIME);
insert into sensor_value values (16,16,16.0,CURRENT_DATE,CURRENT_TIME);
insert into sensor_value values (17,17,17.0,CURRENT_DATE,CURRENT_TIME);
insert into sensor_value values (18,18,18.0,CURRENT_DATE,CURRENT_TIME);
insert into sensor_value values (19,19,19.0,CURRENT_DATE,CURRENT_TIME);
insert into sensor_value values (20,20,20.0,CURRENT_DATE,CURRENT_TIME);
insert into sensor_value values (21,21,21.0,CURRENT_DATE,CURRENT_TIME);
insert into sensor_value values (22,22,22.0,CURRENT_DATE,CURRENT_TIME);
insert into sensor_value values (23,23,23.0,CURRENT_DATE,CURRENT_TIME);
insert into sensor_value values (24,24,24.0,CURRENT_DATE,CURRENT_TIME);
insert into sensor_value values (25,25,25.0,CURRENT_DATE,CURRENT_TIME);
insert into sensor_value values (26,26,26.0,CURRENT_DATE,CURRENT_TIME);
insert into sensor_value values (27,27,27.0,CURRENT_DATE,CURRENT_TIME);
insert into sensor_value values (28,28,28.0,CURRENT_DATE,CURRENT_TIME);
insert into sensor_value values (29,29,29.0,CURRENT_DATE,CURRENT_TIME);
insert into sensor_value values (30,30,30.0,CURRENT_DATE,CURRENT_TIME);
insert into sensor_value values (31,31,31.0,CURRENT_DATE,CURRENT_TIME);
insert into sensor_value values (32,32,32.0,CURRENT_DATE,CURRENT_TIME);
insert into sensor_value values (33,33,33.0,CURRENT_DATE,CURRENT_TIME);
insert into sensor_value values (34,34,34.0,CURRENT_DATE,CURRENT_TIME);
insert into sensor_value values (35,35,35.0,CURRENT_DATE,CURRENT_TIME);
insert into sensor_value values (36,36,36.0,CURRENT_DATE,CURRENT_TIME);
insert into sensor_value values (37,37,37.0,CURRENT_DATE,CURRENT_TIME);
insert into sensor_value values (38,38,38.0,CURRENT_DATE,CURRENT_TIME);
insert into sensor_value values (39,39,39.0,CURRENT_DATE,CURRENT_TIME);
insert into sensor_value values (40,40,40.0,CURRENT_DATE,CURRENT_TIME);
insert into sensor_value values (41,41,41.0,CURRENT_DATE,CURRENT_TIME);
insert into sensor_value values (42,42,42.0,CURRENT_DATE,CURRENT_TIME);
insert into sensor_value values (43,43,43.0,CURRENT_DATE,CURRENT_TIME);
insert into sensor_value values (44,44,44.0,CURRENT_DATE,CURRENT_TIME);
insert into sensor_value values (45,45,45.0,CURRENT_DATE,CURRENT_TIME);
insert into sensor_value values (46,46,46.0,CURRENT_DATE,CURRENT_TIME);
insert into sensor_value values (47,47,47.0,CURRENT_DATE,CURRENT_TIME);
insert into sensor_value values (48,48,48.0,CURRENT_DATE,CURRENT_TIME);
insert into sensor_value values (49,49,49.0,CURRENT_DATE,CURRENT_TIME);
insert into sensor_value values (50,50,50.0,CURRENT_DATE,CURRENT_TIME);
insert into sensor_value values (51,51,51.0,CURRENT_DATE,CURRENT_TIME);
insert into sensor_value values (52,52,52.0,CURRENT_DATE,CURRENT_TIME);
insert into sensor_value values (53,53,53.0,CURRENT_DATE,CURRENT_TIME);
insert into sensor_value values (54,54,54.0,CURRENT_DATE,CURRENT_TIME);
insert into sensor_value values (55,55,55.0,CURRENT_DATE,CURRENT_TIME);
insert into sensor_value values (56,56,56.0,CURRENT_DATE,CURRENT_TIME);
insert into sensor_value values (57,57,57.0,CURRENT_DATE,CURRENT_TIME);
insert into sensor_value values (58,58,58.0,CURRENT_DATE,CURRENT_TIME);
insert into sensor_value values (59,59,59.0,CURRENT_DATE,CURRENT_TIME);
insert into sensor_value values (60,60,60.0,CURRENT_DATE,CURRENT_TIME);
insert into sensor_value values (61,61,61.0,CURRENT_DATE,CURRENT_TIME);

/* Загрузка значений датчиков в хранилище */
insert into sensor_value_storage(id_sensor, value, v_date, v_time) values (1,1.0,CURRENT_DATE,CURRENT_TIME);
insert into sensor_value_storage(id_sensor, value, v_date, v_time) values (2,2.0,CURRENT_DATE,CURRENT_TIME);
insert into sensor_value_storage(id_sensor, value, v_date, v_time) values (3,3.0,CURRENT_DATE,CURRENT_TIME);
insert into sensor_value_storage(id_sensor, value, v_date, v_time) values (4,4.0,CURRENT_DATE,CURRENT_TIME);
insert into sensor_value_storage(id_sensor, value, v_date, v_time) values (5,5.0,CURRENT_DATE,CURRENT_TIME);
insert into sensor_value_storage(id_sensor, value, v_date, v_time) values (6,6.0,CURRENT_DATE,CURRENT_TIME);
insert into sensor_value_storage(id_sensor, value, v_date, v_time) values (7,7.0,CURRENT_DATE,CURRENT_TIME);
insert into sensor_value_storage(id_sensor, value, v_date, v_time) values (8,8.0,CURRENT_DATE,CURRENT_TIME);
insert into sensor_value_storage(id_sensor, value, v_date, v_time) values (9,9.0,CURRENT_DATE,CURRENT_TIME);
insert into sensor_value_storage(id_sensor, value, v_date, v_time) values (10,10.0,CURRENT_DATE,CURRENT_TIME);
insert into sensor_value_storage(id_sensor, value, v_date, v_time) values (11,11.0,CURRENT_DATE,CURRENT_TIME);
insert into sensor_value_storage(id_sensor, value, v_date, v_time) values (12,12.0,CURRENT_DATE,CURRENT_TIME);
insert into sensor_value_storage(id_sensor, value, v_date, v_time) values (13,13.0,CURRENT_DATE,CURRENT_TIME);
insert into sensor_value_storage(id_sensor, value, v_date, v_time) values (14,14.0,CURRENT_DATE,CURRENT_TIME);
insert into sensor_value_storage(id_sensor, value, v_date, v_time) values (15,15.0,CURRENT_DATE,CURRENT_TIME);
insert into sensor_value_storage(id_sensor, value, v_date, v_time) values (16,16.0,CURRENT_DATE,CURRENT_TIME);
insert into sensor_value_storage(id_sensor, value, v_date, v_time) values (17,17.0,CURRENT_DATE,CURRENT_TIME);
insert into sensor_value_storage(id_sensor, value, v_date, v_time) values (18,18.0,CURRENT_DATE,CURRENT_TIME);
insert into sensor_value_storage(id_sensor, value, v_date, v_time) values (19,19.0,CURRENT_DATE,CURRENT_TIME);
insert into sensor_value_storage(id_sensor, value, v_date, v_time) values (20,20.0,CURRENT_DATE,CURRENT_TIME);
insert into sensor_value_storage(id_sensor, value, v_date, v_time) values (21,21.0,CURRENT_DATE,CURRENT_TIME);
insert into sensor_value_storage(id_sensor, value, v_date, v_time) values (22,22.0,CURRENT_DATE,CURRENT_TIME);
insert into sensor_value_storage(id_sensor, value, v_date, v_time) values (23,23.0,CURRENT_DATE,CURRENT_TIME);
insert into sensor_value_storage(id_sensor, value, v_date, v_time) values (24,24.0,CURRENT_DATE,CURRENT_TIME);
insert into sensor_value_storage(id_sensor, value, v_date, v_time) values (25,25.0,CURRENT_DATE,CURRENT_TIME);
insert into sensor_value_storage(id_sensor, value, v_date, v_time) values (26,26.0,CURRENT_DATE,CURRENT_TIME);
insert into sensor_value_storage(id_sensor, value, v_date, v_time) values (27,27.0,CURRENT_DATE,CURRENT_TIME);
insert into sensor_value_storage(id_sensor, value, v_date, v_time) values (28,28.0,CURRENT_DATE,CURRENT_TIME);
insert into sensor_value_storage(id_sensor, value, v_date, v_time) values (29,29.0,CURRENT_DATE,CURRENT_TIME);
insert into sensor_value_storage(id_sensor, value, v_date, v_time) values (30,30.0,CURRENT_DATE,CURRENT_TIME);
insert into sensor_value_storage(id_sensor, value, v_date, v_time) values (31,31.0,CURRENT_DATE,CURRENT_TIME);
insert into sensor_value_storage(id_sensor, value, v_date, v_time) values (32,32.0,CURRENT_DATE,CURRENT_TIME);
insert into sensor_value_storage(id_sensor, value, v_date, v_time) values (33,33.0,CURRENT_DATE,CURRENT_TIME);
insert into sensor_value_storage(id_sensor, value, v_date, v_time) values (34,34.0,CURRENT_DATE,CURRENT_TIME);
insert into sensor_value_storage(id_sensor, value, v_date, v_time) values (35,35.0,CURRENT_DATE,CURRENT_TIME);
insert into sensor_value_storage(id_sensor, value, v_date, v_time) values (36,36.0,CURRENT_DATE,CURRENT_TIME);
insert into sensor_value_storage(id_sensor, value, v_date, v_time) values (37,37.0,CURRENT_DATE,CURRENT_TIME);
insert into sensor_value_storage(id_sensor, value, v_date, v_time) values (38,38.0,CURRENT_DATE,CURRENT_TIME);
insert into sensor_value_storage(id_sensor, value, v_date, v_time) values (39,39.0,CURRENT_DATE,CURRENT_TIME);
insert into sensor_value_storage(id_sensor, value, v_date, v_time) values (40,40.0,CURRENT_DATE,CURRENT_TIME);
insert into sensor_value_storage(id_sensor, value, v_date, v_time) values (41,41.0,CURRENT_DATE,CURRENT_TIME);
insert into sensor_value_storage(id_sensor, value, v_date, v_time) values (42,42.0,CURRENT_DATE,CURRENT_TIME);
insert into sensor_value_storage(id_sensor, value, v_date, v_time) values (43,43.0,CURRENT_DATE,CURRENT_TIME);
insert into sensor_value_storage(id_sensor, value, v_date, v_time) values (44,44.0,CURRENT_DATE,CURRENT_TIME);
insert into sensor_value_storage(id_sensor, value, v_date, v_time) values (45,45.0,CURRENT_DATE,CURRENT_TIME);
insert into sensor_value_storage(id_sensor, value, v_date, v_time) values (46,46.0,CURRENT_DATE,CURRENT_TIME);
insert into sensor_value_storage(id_sensor, value, v_date, v_time) values (47,47.0,CURRENT_DATE,CURRENT_TIME);
insert into sensor_value_storage(id_sensor, value, v_date, v_time) values (48,48.0,CURRENT_DATE,CURRENT_TIME);
insert into sensor_value_storage(id_sensor, value, v_date, v_time) values (49,49.0,CURRENT_DATE,CURRENT_TIME);
insert into sensor_value_storage(id_sensor, value, v_date, v_time) values (50,50.0,CURRENT_DATE,CURRENT_TIME);
insert into sensor_value_storage(id_sensor, value, v_date, v_time) values (51,51.0,CURRENT_DATE,CURRENT_TIME);
insert into sensor_value_storage(id_sensor, value, v_date, v_time) values (52,52.0,CURRENT_DATE,CURRENT_TIME);
insert into sensor_value_storage(id_sensor, value, v_date, v_time) values (53,53.0,CURRENT_DATE,CURRENT_TIME);
insert into sensor_value_storage(id_sensor, value, v_date, v_time) values (54,54.0,CURRENT_DATE,CURRENT_TIME);
insert into sensor_value_storage(id_sensor, value, v_date, v_time) values (55,55.0,CURRENT_DATE,CURRENT_TIME);
insert into sensor_value_storage(id_sensor, value, v_date, v_time) values (56,56.0,CURRENT_DATE,CURRENT_TIME);
insert into sensor_value_storage(id_sensor, value, v_date, v_time) values (57,57.0,CURRENT_DATE,CURRENT_TIME);
insert into sensor_value_storage(id_sensor, value, v_date, v_time) values (58,58.0,CURRENT_DATE,CURRENT_TIME);
insert into sensor_value_storage(id_sensor, value, v_date, v_time) values (59,59.0,CURRENT_DATE,CURRENT_TIME);
insert into sensor_value_storage(id_sensor, value, v_date, v_time) values (60,60.0,CURRENT_DATE,CURRENT_TIME);
insert into sensor_value_storage(id_sensor, value, v_date, v_time) values (61,61.0,CURRENT_DATE,CURRENT_TIME);

