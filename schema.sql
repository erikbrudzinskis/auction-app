CREATE TABLE users (
	id serial NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    email VARCHAR(64) NOT NULL,
    name VARCHAR(64) NOT NULL,
    password VARCHAR NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE categories (
    id serial NOT NULL,
    name VARCHAR(128),
    PRIMARY KEY (id)
);

CREATE TABLE lots (
    id serial NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    end_date TIMESTAMP NOT NULL,
    name VARCHAR(128) NOT NULL,
    category_id INT NOT NULL,
    description VARCHAR NOT NULL,
    image VARCHAR NOT NULL,
    start_price DECIMAL (16, 2) NOT NULL,
    lot_step DECIMAL (16, 2) NOT NULL,
    user_id INT NOT NULL,
    winner_id INT,
    PRIMARY KEY (id),
   	CONSTRAINT fk_user
      FOREIGN KEY(user_id)
	  REFERENCES users(id),
   	CONSTRAINT fk_winner
      FOREIGN KEY(winner_id)
	  REFERENCES users(id),
	CONSTRAINT fk_category
      FOREIGN KEY(category_id)
	  REFERENCES categories(id)
);

CREATE TABLE bids (
    id serial NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    amount DECIMAL (16, 2),
    user_id INT NOT NULL,
    lot_id INT NOT NULL,
    PRIMARY KEY (id),
   	CONSTRAINT fk_user
      FOREIGN KEY(user_id)
	  REFERENCES users(id),
	CONSTRAINT fk_lot
      FOREIGN KEY(lot_id)
	  REFERENCES lots(id)
);