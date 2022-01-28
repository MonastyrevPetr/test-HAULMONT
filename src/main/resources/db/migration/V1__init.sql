create table if not exists client
(
    id    bigserial primary key,
    fio  varchar(255),
    phone varchar(255),
    email varchar(255),
    passport varchar(255)
);

create table if not exists credit
(
    id bigserial primary key,
    limit_credit int,
    percent int
);

create table if not exists credit_offer
(
    id bigserial primary key,
    client_id bigint references client (id),
    credit_id bigint references credit (id),
    summa int

);

create table if not exists payment
(
    id bigserial primary key,
    payment_date int,
    summa int,
    summa_credit int,
    summa_percent int,
    credit_offer_id bigint references credit_offer (id)
);

insert into client (fio, phone, email, passport)
values
    ('Иванов Иван Иванович', '+79000000001', 'ivanov@mail.ru', '1234567890'),
    ('Петров Петр Петрович', '+79000000002', 'petrov@mail.ru', '1234567891'),
    ('Сидоров Сидр Сидорович', '+79000000001', 'sidorov@mail.ru', '1234567892');

insert into credit (limit_credit, percent)
values
    (10000, 20),
    (100000, 15),
    (1000000, 10);

insert into credit_offer (client_id, credit_id, summa)
values
    (1, 1, 30000),
    (2, 1, 60000),
    (3, 2, 120000);

insert into payment (payment_date, summa, summa_credit, summa_percent, credit_offer_id)
values
    (1, 10000, 8000, 2000, 1),
    (2, 10000, 8000, 2000, 1),
    (3, 10000, 8000, 2000, 1),
    (1, 20000, 18000, 2000, 2),
    (2, 20000, 18000, 2000, 2),
    (3, 20000, 18000, 2000, 2),
    (1, 30000, 28000, 2000, 3),
    (2, 30000, 28000, 2000, 3),
    (3, 30000, 28000, 2000, 3),
    (4, 30000, 28000, 2000, 3);

