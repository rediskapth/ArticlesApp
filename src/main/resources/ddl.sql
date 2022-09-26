CREATE EXTENSION "uuid-ossp";

CREATE TABLE users(
        id UUID DEFAULT uuid_generate_v4() PRIMARY KEY,
        username VARCHAR(100) NOT NULL UNIQUE,
        password VARCHAR(100) NOT NULL,
        user_role VARCHAR(100) NOT NULL
);

CREATE TABLE articles(
        id UUID DEFAULT uuid_generate_v4() PRIMARY KEY,
        title VARCHAR(100) NOT NULL,
        content VARCHAR(10000) NOT NULL,
        date_creation DATE NOT NULL,
        user_id UUID,
        FOREIGN KEY (user_id) REFERENCES users
);
