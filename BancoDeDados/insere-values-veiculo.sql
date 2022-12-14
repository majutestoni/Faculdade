INSERT INTO combustivel VALUES(1, "gasolina comum");
INSERT INTO combustivel VALUES(2, "diesel 500");
INSERT INTO combustivel VALUES(3, null);
INSERT INTO combustivel VALUES(4, "diesel s10");
INSERT INTO combustivel VALUES(5, "etanol");

INSERT INTO marca VALUES (1, "nissan");
INSERT INTO marca VALUES (2, null);
INSERT INTO marca VALUES (3, "fiat");
INSERT INTO marca VALUES (4, "audi");
INSERT INTO marca VALUES (5, "joao");

INSERT INTO localidade VALUES (1, "gaspar");
INSERT INTO localidade VALUES (2, "rodeio");
INSERT INTO localidade VALUES (3, "ilhota");
INSERT INTO localidade VALUES (4, "blumenau");
INSERT INTO localidade VALUES (5, NULL);

INSERT INTO acessorio VALUES (1, "cross");
INSERT INTO acessorio VALUES (2, "luz led");
INSERT INTO acessorio VALUES (3, "capa protetora");
INSERT INTO acessorio VALUES (4, NULL);
INSERT INTO acessorio VALUES (5, "bandeira do brazil");

INSERT INTO cor VALUES (1, null);
INSERT INTO cor VALUES (2, "rosa");
INSERT INTO cor VALUES (3, "laranja");
INSERT INTO cor VALUES (4, "azul");
INSERT INTO cor VALUES (5, "verde");

INSERT INTO modelo VALUES (1 , "sedan", 3);
INSERT INTO modelo VALUES (2 , "caminhote", 1);
INSERT INTO modelo VALUES (3 , "SUV", 5);
INSERT INTO modelo VALUES (4 , "compacto", 2);
INSERT INTO modelo VALUES (5 , "saveiro", 4);

INSERT INTO poprietario VALUES (1, "joao", "rua joao", NULL, "longe", "2787-8442", "joao@joao.com", 'SC', 1); 
INSERT INTO poprietario VALUES (2, "maria", "rua maria", "apartamento", NULL, "1414-1414", "maria@maria.com", 'PR', 4); 
INSERT INTO poprietario VALUES (3, "fernanda", "rua fernanda", "casa", "são paulo", "8585-8585", "fernanda@fernanda.com", null, 5); 
INSERT INTO poprietario VALUES (4, "felipe", "rua felipe", "perto da loja abc", "centro", "3636-3636", null, 'RJ', 3); 
INSERT INTO poprietario VALUES (5, "rua martin", null, "perto do mercado vila nova", "vila nova", null, "martin@martin.com", 'SP', 2); 

INSERT INTO veiculo VALUES ('1234567', 2002, null, 130000, 2, "carro antigo", 2, 4, 5);
INSERT INTO veiculo VALUES ('7654321', null, 2013, 50000, 4, "carro padrao", 5, 3, 1);
INSERT INTO veiculo VALUES ('7775566', 1995, 1992, 150000, 2, NULL, 3, 2, 2);
INSERT INTO veiculo VALUES ('3232444', 2020, 2021, 20000, 4, "carro familia", 4, 5, null);
INSERT INTO veiculo VALUES ('9696347', 2010, 2011, 60000, 4, "carro ok", NULL, 1, 4);

INSERT INTO veiculo_combustivel VALUES (1, 4, '7775566');
INSERT INTO veiculo_combustivel VALUES (2, NULL, '1234567');
INSERT INTO veiculo_combustivel VALUES (3, 3, '9696347');
INSERT INTO veiculo_combustivel VALUES (4, 2, '1234567');
INSERT INTO veiculo_combustivel VALUES (5, 1, '3232444');


