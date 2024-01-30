-- Criar a tabela roles
CREATE TABLE roles (
    id INT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL
);

-- Inserir dados iniciais
INSERT INTO roles (id, nome) VALUES (1, 'ADMINISTRADOR');
INSERT INTO roles (id, nome) VALUES (2, 'USUARIO');
INSERT INTO roles (id, nome) VALUES (3, 'TRANSFERENCIA');

