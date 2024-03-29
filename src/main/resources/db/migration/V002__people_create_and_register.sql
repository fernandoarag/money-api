-- CREATE PEOPLE TABLE
CREATE TABLE people (
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR(50) NOT NULL,
	street VARCHAR(30),
	number VARCHAR(30),
	complement VARCHAR(30),
	neighborhood VARCHAR(30),
	zip_code VARCHAR(30),
	city VARCHAR(30),
	state VARCHAR(30),
	active BOOLEAN NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- INSERT VALUES INTO THE PEOPLE TABLE
INSERT INTO people (name, street, number, complement, neighborhood, zip_code, city, state, active)
	values ('João Silva', 'Rua do Abacaxi', '10', null, 'Brasil', '38.400-12', 'Uberlândia', 'MG', true);

INSERT INTO people (name, street, number, complement, neighborhood, zip_code, city, state, active)
	values ('Maria Rita', 'Rua do Sabiá', '110', 'Apto 101', 'Colina', '11.400-12', 'Ribeirão Preto', 'SP', true);

INSERT INTO people (name, street, number, complement, neighborhood, zip_code, city, state, active)
	values ('Pedro Santos', 'Rua da Bateria', '23', null, 'Morumbi', '54.212-12', 'Goiânia', 'GO', true);

INSERT INTO people (name, street, number, complement, neighborhood, zip_code, city, state, active)
	values ('Ricardo Pereira', 'Rua do Motorista', '123', 'Apto 302', 'Aparecida', '38.400-12', 'Salvador', 'BA', true);

INSERT INTO people (name, street, number, complement, neighborhood, zip_code, city, state, active)
	values ('Josué Mariano', 'Av Rio Branco', '321', null, 'Jardins', '56.400-12', 'Natal', 'RN', true);

INSERT INTO people (name, street, number, complement, neighborhood, zip_code, city, state, active)
	values ('Pedro Barbosa', 'Av Brasil', '100', null, 'Tubalina', '77.400-12', 'Porto Alegre', 'RS', true);

INSERT INTO people (name, street, number, complement, neighborhood, zip_code, city, state, active)
	values ('Henrique Medeiros', 'Rua do Sapo', '1120', 'Apto 201', 'Centro', '12.400-12', 'Rio de Janeiro', 'RJ', true);

INSERT INTO people (name, street, number, complement, neighborhood, zip_code, city, state, active)
	values ('Carlos Santana', 'Rua da Manga', '433', null, 'Centro', '31.400-12', 'Belo Horizonte', 'MG', true);

INSERT INTO people (name, street, number, complement, neighborhood, zip_code, city, state, active)
	values ('Leonardo Oliveira', 'Rua do Músico', '566', null, 'Segismundo Pereira', '38.400-00', 'Uberlândia', 'MG', true);

INSERT INTO people (name, street, number, complement, neighborhood, zip_code, city, state, active)
	values ('Isabela Martins', 'Rua da Terra', '1233', 'Apto 10', 'Vigilato', '99.400-12', 'Manaus', 'AM', true);
