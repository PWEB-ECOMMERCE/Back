CREATE TABLE produto(
    id SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    descricao VARCHAR(255) NOT NULL,
    preco INTEGER NOT NULL,
    imagem_url VARCHAR(255) NOT NULL,
    quantidade INTEGER NOT NULL
)