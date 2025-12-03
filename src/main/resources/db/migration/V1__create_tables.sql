CREATE TABLE t_author
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE t_genre
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE t_book
(
    id        SERIAL PRIMARY KEY,
    name      VARCHAR(255) NOT NULL,
    author_id INT          NOT NULL REFERENCES t_author (id)
);

CREATE TABLE t_book_genres
(
    book_id  INT NOT NULL REFERENCES t_book (id) ON DELETE CASCADE,
    genre_id INT NOT NULL REFERENCES t_genre (id) ON DELETE CASCADE,
    PRIMARY KEY (book_id, genre_id)
);

