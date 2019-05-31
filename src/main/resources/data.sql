INSERT INTO conference (id, name, details)
VALUES (1, 'Lubelskie Dni Informatyki', '1-2 czerwca 2019 Lublin, Lubelski Park Naukowo-Technologiczny');

INSERT INTO users (id, name, email, conference_id)
VALUES (1, 'Robert', 'coffee@mail.com', 1),
       (2, 'Bjarne', 'bjarne@mail.com', 1),
       (3, 'Ethean', 'ethean@mail.com', 1),
       (4, 'Henryk', 'henryk@mail.com', 1),
       (5, 'Jeffre', 'jefree@mail.com', 1),
       (6, 'Leeman', 'leeman@mail.com', 1);

INSERT INTO lecture (id, name, lecturer, type, day, time, number_of_places, conference_id)
VALUES (1,  'Kotlin - napisz raz, uruchom wszędzie',              'John Doe',       'INSPIRATION', '1', '10:00-11:45', '5', 1),
       (2,  'Sztuczna inteligencja rekrutuje już dziś',           'Oliver Johnson', 'TECHNOLOGY',  '1', '10:00-11:45', '5', 1),
       (3,  'React Native vs Progressive Web Apps',               'Jack Smith',     'KNOWLEDGE',   '1', '10:00-11:45', '5', 1),
       (4,  'Alternatywne języki JVM',                            'Harry Miller',   'INSPIRATION', '1', '12:00-13:45', '5', 1),
       (5,  'Is TDD wrong? [EN]',                                 'Jacob Jones',    'TECHNOLOGY',  '1', '12:00-13:45', '5', 1),
       (6,  'Programista 2.0',                                    'Kyle Wang',      'KNOWLEDGE',   '1', '12:00-13:45', '5', 1),
       (7,  'UX inaczej',                                         'Joe Anderson',   'INSPIRATION', '2', '10:00-11:45', '5', 1),
       (8,  'Advanced feature-based workflow with Git',           'George Wilson',  'TECHNOLOGY',  '2', '10:00-11:45', '5', 1),
       (9,  'Czy technologia determinuje sukces projektu?',       'Oscar Brown',    'KNOWLEDGE',   '2', '10:00-11:45', '5', 1),
       (10, 'Xamarin|React Native|Java|Kotlin|Objective-c|Swift', 'Emily Taylor',   'INSPIRATION', '2', '12:00-13:45', '5', 1),
       (11, 'Systemy Zabezpieczeń w Inteligentnych Budynkach',    'Sophie Davies',  'TECHNOLOGY',  '2', '12:00-13:45', '5', 1),
       (12, 'Flutter.io',                                         'Jack Martin',    'KNOWLEDGE',   '2', '12:00-13:45', '5', 1);

INSERT INTO users_lecture
VALUES (2, 2),
       (3, 2),
       (4, 2),
       (5, 2),
       (6, 2);
