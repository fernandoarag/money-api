-- CREATE TABLE RELEASES
CREATE TABLE releases (
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	description VARCHAR(50) NOT NULL,
	due_date DATE NOT NULL,
	date_of_payment DATE,
	price DECIMAL(10,2) NOT NULL,
	observation VARCHAR(100),
	type VARCHAR(20) NOT NULL,
	category_id BIGINT(20) NOT NULL,
	person_id BIGINT(20) NOT NULL,
	FOREIGN KEY (category_id) REFERENCES categories(id),
	FOREIGN KEY (person_id) REFERENCES people(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- INSERT VALUES IN RELEASES TABLE
INSERT INTO releases (description, due_date, date_of_payment, price, observation, type, category_id, person_id)
	values ('Salário mensal', '2017-06-10', null, 6500.00, 'Distribuição de lucros', 'RECEITA', 1, 1);

INSERT INTO releases (description, due_date, date_of_payment, price, observation, type, category_id, person_id)
	values ('Bahamas', '2017-02-10', '2017-02-10', 100.32, null, 'DESPESA', 2, 2);

INSERT INTO releases (description, due_date, date_of_payment, price, observation, type, category_id, person_id)
	values ('Top Club', '2017-06-10', null, 120, null, 'RECEITA', 3, 3);

INSERT INTO releases (description, due_date, date_of_payment, price, observation, type, category_id, person_id)
	values ('CEMIG', '2017-02-10', '2017-02-10', 110.44, 'Geração', 'RECEITA', 3, 4);

INSERT INTO releases (description, due_date, date_of_payment, price, observation, type, category_id, person_id)
	values ('DMAE', '2017-06-10', null, 200.30, null, 'DESPESA', 3, 5);

INSERT INTO releases (description, due_date, date_of_payment, price, observation, type, category_id, person_id)
	values ('Extra', '2017-03-10', '2017-03-10', 1010.32, null, 'RECEITA', 4, 6);

INSERT INTO releases (description, due_date, date_of_payment, price, observation, type, category_id, person_id)
	values ('Bahamas', '2017-06-10', null, 500, null, 'RECEITA', 1, 7);

INSERT INTO releases (description, due_date, date_of_payment, price, observation, type, category_id, person_id)
	values ('Top Club', '2017-03-10', '2017-03-10', 400.32, null, 'DESPESA', 4, 8);

INSERT INTO releases (description, due_date, date_of_payment, price, observation, type, category_id, person_id)
	values ('Despachante', '2017-06-10', null, 123.64, 'Multas', 'DESPESA', 3, 9);

INSERT INTO releases (description, due_date, date_of_payment, price, observation, type, category_id, person_id)
	values ('Pneus', '2017-04-10', '2017-04-10', 665.33, null, 'RECEITA', 5, 10);

INSERT INTO releases (description, due_date, date_of_payment, price, observation, type, category_id, person_id)
	values ('Café', '2017-06-10', null, 8.32, null, 'DESPESA', 1, 5);

INSERT INTO releases (description, due_date, date_of_payment, price, observation, type, category_id, person_id)
	values ('Eletrônicos', '2017-04-10', '2017-04-10', 2100.32, null, 'DESPESA', 5, 4);

INSERT INTO releases (description, due_date, date_of_payment, price, observation, type, category_id, person_id)
	values ('Instrumentos', '2017-06-10', null, 1040.32, null, 'DESPESA', 4, 3);

INSERT INTO releases (description, due_date, date_of_payment, price, observation, type, category_id, person_id)
	values ('Café', '2017-04-10', '2017-04-10', 4.32, null, 'DESPESA', 4, 2);

INSERT INTO releases (description, due_date, date_of_payment, price, observation, type, category_id, person_id)
	values ('Lanche', '2017-06-10', null, 10.20, null, 'DESPESA', 4, 1);
