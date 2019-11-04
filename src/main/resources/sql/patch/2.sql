alter table sensor drop column length_value;

alter table sensor add column start_length_value float;
alter table sensor add column end_length_value float;
alter table sensor add column start_file_length_value float;
alter table sensor add column end_file_length_value float;

alter table sensor add column start_length_value_2 float;
alter table sensor add column end_length_value_2 float;
alter table sensor add column start_file_length_value_2 float;
alter table sensor add column end_file_length_value_2 float;

/* Таблица блоков */
create table sensor_block(
     id integer,
     file_name text,
     primary key (id)
);

alter table sensor add column id_block int references sensor_block(id);