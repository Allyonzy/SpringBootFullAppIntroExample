insert into user_test(id, age, confirm_code, email, first_name, last_name, password, state)
values
    (1, 45, null, 'test@ya.ru', 'Петр', 'Решетов', 'qweasd10', 'NOT_CONFIRMED'),
    (2, 23, null, 'test@ya.ru', 'Иван', 'Иванов', 'qweasd11', 'NOT_CONFIRMED'),
    (3, 12, null, 'test@ya.ru', 'Кристина', 'Цветкова', 'qweasd12', 'NOT_CONFIRMED'),
    (4, 34, null, 'test@ya.ru', 'Стивен', 'Кинг', 'qweasd13', 'NOT_CONFIRMED'),
    (5, 54, null, 'test@ya.ru', 'Роберт', 'Земекис', 'qweasd14', 'NOT_CONFIRMED'),
    (6, 67, null, 'test@ya.ru', 'Айгуль', 'Садыкова', 'qweasd15', 'NOT_CONFIRMED');

insert into customer(id, first_name, last_name) values (1, 'Customer 1', 'Customer 1');
insert into customer(id, first_name, last_name) values (2, 'Customer 2', 'Customer 2');
insert into customer(id, first_name, last_name) values (3, 'Customer 3', 'Customer 3');
insert into customer(id, first_name, last_name) values (4, 'Customer 4', 'Customer 4');