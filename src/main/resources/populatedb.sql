insert into depo.kinds (kind_id, kind)
values (0, 'Трамвай'),
       (0, 'Троллейбус');

insert into depo.types (type_id, type)
values (0, 'Пассажирский'),
       (0, 'Грузовой'),
       (0, 'Служебный');

insert into depo.parks (park_id, park_number, park_type)
values (0, 11, 'Трамвайный'),
       (0, 12, 'Троллейбусный'),
       (0, 13, 'Совмещённый');

insert into depo.transport (transport_id, number, created_at, modified_at, kind_id, park_id)
values (0, 34, current_time, null, 2, 2),
       (0, '40А', current_time, null, 2, 3),
       (0, 200, current_time, current_time, 1, 1),
       (0, '86А', current_time, null, 2, 2),
       (0, 102, current_time, null, 1, 3),
       (0, 63, current_time, null, 1, 1);

insert into depo.location (transport_id, length, width)
values (1, 59.839095, 30.315868),
       (2, 59.969095, 30.315868),
       (3, 59.879095, 30.215868),
       (4, 59.939095, 30.345868),
       (5, 59.829095, 30.325868),
       (6, 59.878399, 30.323124);

insert into depo.transport_type (transport_id, type_id)
values (1, 1),
       (2, 1),
       (2, 3),
       (3, 2),
       (3, 3),
       (4, 1),
       (4, 3),
       (5, 2),
       (6, 2);

insert into depo.transport_details(transport_id, capacity, tonnage)
values (1, 19, 0),
       (2, 27, 0),
       (3, 20000, 0),
       (4, 36, 0),
       (5, 15000, 0),
       (6, 18500, 0);
