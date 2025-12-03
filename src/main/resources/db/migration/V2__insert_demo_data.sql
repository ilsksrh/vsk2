INSERT INTO t_author (name) VALUES ('Petya');
INSERT INTO t_author (name) VALUES ('Sasha');
INSERT INTO t_author (name) VALUES ('Pavel');
INSERT INTO t_author (name) VALUES ('Temirbek');

INSERT INTO t_genre (name) VALUES ('Fantasy');
INSERT INTO t_genre (name) VALUES ('Drama');
INSERT INTO t_genre (name) VALUES ('Horror');
INSERT INTO t_genre (name) VALUES ('Comedy');
INSERT INTO t_genre (name) VALUES ('Bromance');

INSERT INTO t_book (name, author_id) VALUES ('Nu Pogodi', 1);
INSERT INTO t_book (name, author_id) VALUES ('Masha and Ayu', 2);
INSERT INTO t_book (name, author_id) VALUES ('BenTen', 3);
INSERT INTO t_book (name, author_id) VALUES ('Auru', 4);

INSERT INTO t_book_genres (book_id, genre_id) VALUES (4, 3);
INSERT INTO t_book_genres (book_id, genre_id) VALUES (4, 2);
INSERT INTO t_book_genres (book_id, genre_id) VALUES (1, 4);
INSERT INTO t_book_genres (book_id, genre_id) VALUES (2, 5);
INSERT INTO t_book_genres (book_id, genre_id) VALUES (2, 1);
