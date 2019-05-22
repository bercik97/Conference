INSERT INTO conference (id, uuid, name, details)
VALUES (1, '3902996b-2b44-4525-b7d3-e958731c877c', 'Lubelskie Dni Informatyki', '1-2 czerwca 2019 Lublin, Lubelski Park Naukowo-Technologiczny');

INSERT INTO users (id, uuid, name, email, conference_id)
VALUES (1, '429969d7-a913-49db-8cab-92c79f48f675', 'Robert', 'coffee@mail.com', 1),
       (2, 'e563df3b-10cd-4386-b1ee-ac8e520e4952', 'Bjarne', 'bjarne@mail.com', 1),
       (3, 'f63248d0-d871-4f3f-ab86-1b39a6a0db44', 'Ethean', 'ethean@mail.com', 1),
       (4, 'f000347b-ace3-4fb7-a928-b37e72040a45', 'Henryk', 'henryk@mail.com', 1),
       (5, '5c8d9702-c477-4065-8d0a-c98b2134d810', 'Jeffre', 'jefree@mail.com', 1),
       (6, '8be99ca5-318a-46c0-9c04-65dd1e7e6caf', 'Leeman', 'leeman@mail.com', 1);

INSERT INTO lecture (id, uuid, name, lecturer, type, day, time, number_of_places, conference_id)
VALUES (1,  '99fb9a3d-cc7a-4cb8-9769-0e6d74ca8e40',
        'Kotlin - napisz raz, uruchom wszędzie',              'John Doe',       'INSPIRATION', '1', '10:00-11:45', '5', 1),
       (2,  '3f2124e4-481f-40b2-9ee8-4ab3d7c9ed82',
        'Sztuczna inteligencja rekrutuje już dziś',           'Oliver Johnson', 'TECHNOLOGY',  '1', '10:00-11:45', '5', 1),
       (3,  '18bf3e29-ebcf-475e-bcd7-3a100692bf7c',
        'React Native vs Progressive Web Apps',               'Jack Smith',     'KNOWLEDGE',   '1', '10:00-11:45', '5', 1),
       (4,  '7f7da690-2afe-429d-a89e-e09e0c34ca3a',
        'Alternatywne języki JVM',                            'Harry Miller',   'INSPIRATION', '1', '12:00-13:45', '5', 1),
       (5,  '938ef887-ccc7-4d78-b547-c174a912f6db',
        'Is TDD wrong? [EN]',                                 'Jacob Jones',    'TECHNOLOGY',  '1', '12:00-13:45', '5', 1),
       (6,  'caf6af07-474b-4fb5-817b-85fca63639ae',
        'Programista 2.0',                                    'Kyle Wang',      'KNOWLEDGE',   '1', '12:00-13:45', '5', 1),
       (7,  '404efe4c-8d0e-4a2c-8021-6d75fefa1244',
        'UX inaczej',                                         'Joe Anderson',   'INSPIRATION', '2', '10:00-11:45', '5', 1),
       (8,  'bc05caba-3280-47cf-85cd-07939fa9eeba',
        'Advanced feature-based workflow with Git',           'George Wilson',  'TECHNOLOGY',  '2', '10:00-11:45', '5', 1),
       (9,  'd18bc636-6e9e-4b81-bda2-e595f9c0b787',
        'Czy technologia determinuje sukces projektu?',       'Oscar Brown',    'KNOWLEDGE',   '2', '10:00-11:45', '5', 1),
       (10, '5a332398-dcca-4220-8647-e4a518c99c8e',
        'Xamarin|React Native|Java|Kotlin|Objective-c|Swift', 'Emily Taylor',   'INSPIRATION', '2', '12:00-13:45', '5', 1),
       (11, '0da842de-d8c3-484f-b449-49599c327029',
        'Systemy Zabezpieczeń w Inteligentnych Budynkach',    'Sophie Davies',  'TECHNOLOGY',  '2', '12:00-13:45', '5', 1),
       (12, '2b97e7ce-d5bb-42d4-a4b1-b7af5639643b',
        'Flutter.io',                                         'Jack Martin',    'KNOWLEDGE',   '2', '12:00-13:45', '5', 1);

INSERT INTO users_lecture
VALUES (2, 2),
       (3, 2),
       (4, 2),
       (5, 2),
       (6, 2);
