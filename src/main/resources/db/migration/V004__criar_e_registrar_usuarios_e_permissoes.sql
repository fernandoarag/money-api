CREATE TABLE usuario (
	codigo BIGINT(20) PRIMARY KEY,
	nome VARCHAR(50) NOT NULL,
	email VARCHAR(50) NOT NULL,
	senha VARCHAR(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE permissao (
	codigo BIGINT(20) PRIMARY KEY,
	descricao VARCHAR(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE usuario_permissao (
	codigo_usuario BIGINT(20) NOT NULL,
	codigo_permissao BIGINT(20) NOT NULL,
	PRIMARY KEY (codigo_usuario, codigo_permissao),
	FOREIGN KEY (codigo_usuario) REFERENCES usuario(codigo),
	FOREIGN KEY (codigo_permissao) REFERENCES permissao(codigo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- CADASTRO DE USUÁRIOS
-- senha: admin
INSERT INTO usuario (codigo, nome, email, senha) 
values (1, 'Administrador', 'admin@algamoney.com', '$2a$10$X607ZPhQ4EgGNaYKt3n4SONjIv9zc.VMWdEuhCuba7oLAL5IvcL5.');

-- senha: maria
INSERT INTO usuario (codigo, nome, email, senha) 
values (2, 'Maria Silva', 'maria@algamoney.com', '$2a$10$Zc3w6HyuPOPXamaMhh.PQOXvDnEsadztbfi6/RyZWJDzimE8WQjaq');


-- CADASTRO DE PERMISSÕES
-- Categoria
INSERT INTO permissao (codigo, descricao) 
values (1, 'ROLE_CADASTRAR_CATEGORIA');

INSERT INTO permissao (codigo, descricao) 
values (2, 'ROLE_PESQUISAR_CATEGORIA');

INSERT INTO permissao (codigo, descricao) 
values (3, 'ROLE_REMOVER_CATEGORIA');

INSERT INTO permissao (codigo, descricao) 
values (4, 'ROLE_ATUALIZAR_CATEGORIA');

-- Pessoa
INSERT INTO permissao (codigo, descricao) 
values (5, 'ROLE_CADASTRAR_PESSOA');

INSERT INTO permissao (codigo, descricao) 
values (6, 'ROLE_PESQUISAR_PESSOA');

INSERT INTO permissao (codigo, descricao) 
values (7, 'ROLE_REMOVER_PESSOA');

INSERT INTO permissao (codigo, descricao) 
values (8, 'ROLE_ATUALIZAR_PESSOA');

-- Lancamento
INSERT INTO permissao (codigo, descricao) 
values (9, 'ROLE_CADASTRAR_LANCAMENTO');

INSERT INTO permissao (codigo, descricao) 
values (10, 'ROLE_PESQUISAR_LANCAMENTO');

INSERT INTO permissao (codigo, descricao) 
values (11, 'ROLE_REMOVER_LANCAMENTO');

INSERT INTO permissao (codigo, descricao) 
values (12, 'ROLE_ATUALIZAR_LANCAMENTO');


-- VINCULANDO PERMISSÕES
-- admin
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) 
values (1, 1);

INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) 
values (1, 2);

INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) 
values (1, 3);

INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) 
values (1, 4);

INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) 
values (1, 5);

INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) 
values (1, 6);

INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) 
values (1, 7);

INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) 
values (1, 8);

INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) 
values (1, 9);

INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) 
values (1, 10);

INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) 
values (1, 11);

INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) 
values (1, 12);

-- maria
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) 
values (2, 2);

INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) 
values (2, 6);

INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) 
values (2, 10);
