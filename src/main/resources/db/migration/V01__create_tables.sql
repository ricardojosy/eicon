CREATE TABLE pedidos(
    id INT AUTO_INCREMENT,
    numero_controle INT NOT NULL UNIQUE,
    data_cadastro DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    nome_produto VARCHAR(200) NOT NULL,
    valor_unitario DOUBLE(10,2) NOT NULL,
    quantidade INT DEFAULT 1,
    codigo_cliente INT NOT NULL,
    valor_total DOUBLE(10,2) NOT NULL,
    PRIMARY KEY (id)
);
