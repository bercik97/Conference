INSERT INTO conference (name, details, number_of_available_places)
VALUES ('Lubelskie Dni Informatyki', '13 kwietnia 2019 Lublin, Lubelski Park Naukowo-Technologiczny', '59');

INSERT INTO users (name, email, conference_id)
VALUES ('Robert', 'coffee@mail.com', 1);

INSERT INTO lecture (name, lecturer, type, day, start_time, number_of_places, conference_id)
VALUES ('Kotlin - napisz raz, uruchom wszędzie',              'John Doe',       'Ścieżka inspiracji',  '1', '10:00', '5', 1),
       ('Sztuczna inteligencja rekrutuje już dziś',           'Oliver Johnson', 'Ścieżka technologii', '1', '10:00', '5', 1),
       ('React Native vs Progressive Web Apps',               'Jack Smith',     'Ścieżka wiedzy',      '1', '10:00', '5', 1),
       ('Alternatywne języki JVM',                            'Harry Miller',   'Ścieżka inspiracji',  '1', '12:00', '5', 1),
       ('Is TDD wrong? [EN]',                                 'Jacob Jones',    'Ścieżka technologii', '1', '12:00', '5', 1),
       ('Programista 2.0',                                    'Kyle Wang',      'Ścieżka wiedzy',      '1', '12:00', '5', 1),
       ('UX inaczej',                                         'Joe Anderson',   'Ścieżka inspiracji',  '2', '10:00', '5', 1),
       ('Advanced feature-based workflow with Git',           'George Wilson',  'Ścieżka technologii', '2', '10:00', '5', 1),
       ('Czy technologia determinuje sukces projektu?',       'Oscar Brown',    'Ścieżka wiedzy',      '2', '10:00', '5', 1),
       ('Xamarin|React Native|Java|Kotlin|Objective-c|Swift', 'Emily Taylor',   'Ścieżka inspiracji',  '2', '12:00', '5', 1),
       ('Systemy Zabezpieczeń w Inteligentnych Budynkach',    'Sophie Davies',  'Ścieżka technologii', '2', '12:00', '5', 1),
       ('Flutter.io',                                         'Jack Martin',    'Ścieżka wiedzy',      '2', '12:00', '5', 1);

INSERT INTO users_lectures (user_id, lecture_id)
VALUES (1,  1),
       (1,  5),
       (1,  9),
       (1, 10);
