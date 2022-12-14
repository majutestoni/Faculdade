a) Criação das tabelas:
categoria - CREATE TABLE categoria (cd_categoria INTEGER, ds_categoria VARCHAR(50), PRIMARY KEY(cd_categoria));
orcamento - CREATE TABLE orcamento (nr_orcamento INTEGER, dt_emissao DATE, vl_total DECIMAL(8,2), PRIMARY KEY (nr_orcamento));
produto - CREATE TABLE produto(cd_produto INTEGER, nm_produto VARCHAR(50), qt_estoque INTEGER, vl_produto DECIMAL(8,2), cd_categoria INTEGER, 
          FOREIGN KEY (cd_categoria) REFERENCES categoria(cd_categoria), PRIMARY KEY(cd_produto), UNIQUE (nm_produto));	  
item_orcamento - CREATE TABLE item_orcamento (nr_orcamento INTEGER, cd_produto INTEGER,qt_produto INTEGER, vl_produto DECIMAL(8,2), vl_total_item DECIMAL(8,2),  
                 PRIMARY KEY (nr_orcamento, cd_produto), FOREIGN KEY (nr_orcamento) REFERENCES orcamento(nr_orcamento), FOREIGN KEY (cd_produto)  REFERENCES produto(cd_produto));
				 
b) adicionado já na criação da table - UNIQUE (nm_produto)

c) inserindo dados:
INSERT INTO categoria VALUES (1, 'roupa feminina');
INSERT INTO categoria VALUES (2, 'sapato');
INSERT INTO categoria VALUES (3, 'eletronico');
INSERT INTO categoria VALUES (4, 'decoracao');
INSERT INTO categoria VALUES (5, 'adereco de cozinha');

INSERT INTO produto VALUES (1, 'chinelo havaianas', 200, 15.99, 2);
INSERT INTO produto VALUES (2, 'frigideira', 50, 10.00, 5);
INSERT INTO produto VALUES (3, 'camisa polo', 100, 20.50, 1);
INSERT INTO produto VALUES (4, 'quadro', 10, 50.00, 4);
INSERT INTO produto VALUES (5, 'celular samsung', 90, 35.00, 3);
INSERT INTO produto VALUES (6, 'cabo usb', 300, 12.99, 3);
INSERT INTO produto VALUES (7, 'jogo de talheres', 30, 25.50, 5);
INSERT INTO produto VALUES (8, 'chinelo ipanema', 150, 10.00, 2);
INSERT INTO produto VALUES (9, 'shorts jeans', 40, 40.00, 1);
INSERT INTO produto VALUES (10, 'planta', 5, 42.00, 4);

INSERT INTO orcamento VALUES (1234, '2022-01-01', 200.00);
INSERT INTO orcamento VALUES (4321, '2022-11-10', 150.50);
INSERT INTO orcamento VALUES (7412, '2022-11-04', 50.00);
INSERT INTO orcamento VALUES (9632, '2022-11-03', 90.10);
INSERT INTO orcamento VALUES (8526, '2022-05-10', 122.90);
INSERT INTO orcamento VALUES (9635, '2022-03-20', 84.00);
INSERT INTO orcamento VALUES (7458, '2022-08-02', 20.90);

INSERT INTO item_orcamento VALUES (1234, 1, 3, 15.99, 47.97);
INSERT INTO item_orcamento VALUES (1234, 5, 10, 10.00, 50.00);
INSERT INTO item_orcamento VALUES (4321, 4, 3, 50.00, 50.00);
INSERT INTO item_orcamento VALUES (7412, 8, 1, 10.00, 10.00);
INSERT INTO item_orcamento VALUES (7412, 9, 1, 40.00, 40.00);
INSERT INTO item_orcamento VALUES (7458, 7, 1, 25.50, 25.50);
INSERT INTO item_orcamento VALUES (8526, 5, 2, 35.00, 70.00);
INSERT INTO item_orcamento VALUES (9632, 3, 4, 20.50, 82.00);
INSERT INTO item_orcamento VALUES (9635, 2, 6, 10.00, 60.00);

d) SELECT * FROM produto WHERE vl_produto BETWEEN 10.00 AND 50.00;

f) SELECT p.cd_produto, p.nm_produto, p.qt_estoque, p.vl_produto, c.cd_categoria, c.ds_categoria FROM produto p JOIN categoria c ON p.cd_categoria = c.cd_categoria;

e) SELECT * FROM orcamento WHERE dt_emissao BETWEEN '2022-11-01' AND '2022-11-05'; ou SELECT * FROM orcamento WHERE dt_emissao > '2022-11-01' AND  dt_emissao < '2022-11-05'; (caso não queira inluir)


