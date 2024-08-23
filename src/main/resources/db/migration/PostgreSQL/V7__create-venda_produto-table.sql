CREATE TABLE venda_produto(
    venda_id SERIAL NOT NULL,
    produto_id INTEGER NOT NULL,
    quantidade INTEGER NOT NULL,
    CONSTRAINT pk_venda_produto PRIMARY KEY (venda_id, produto_id)
)