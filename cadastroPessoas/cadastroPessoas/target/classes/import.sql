INSERT INTO tb_pessoa(nome, cpf, data_nascimento) VALUES ('Maria', '11111111111', '2001-01-01');
INSERT INTO tb_pessoa(nome, cpf, data_nascimento) VALUES ('Bob', '22222222222', '2002-02-02');
INSERT INTO tb_pessoa(nome, cpf, data_nascimento) VALUES ('Alex', '33333333333', '2003-03-03');
INSERT INTO tb_pessoa(nome, cpf, data_nascimento) VALUES ('Ana', '44444444444', '2004-04-04');

INSERT INTO tb_contato(pessoa_id, nome, email, telefone) VALUES (1, 'Maria', 'maria@gmail.com', '111111111');
INSERT INTO tb_contato(pessoa_id, nome, email, telefone) VALUES (2, 'Bob', 'bob@gmail.com', '222222222');
INSERT INTO tb_contato(pessoa_id, nome, email, telefone) VALUES (3, 'Alex', 'alex@gmail.com', '333333333');
INSERT INTO tb_contato(pessoa_id, nome, email, telefone) VALUES (4, 'Ana', 'ana@gmail.com', '444444444');
INSERT INTO tb_contato(pessoa_id, nome, email, telefone) VALUES (4, 'Ana', 'ana@gmail.com', '555555555');