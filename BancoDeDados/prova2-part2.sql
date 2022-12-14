1)
  a) CREATE TABLE cond_pagamento (cd_cond_pagamento INTEGER, ds_cond_pagamento VARCHAR(50), PRIMARY KEY(cd_cond_pagamento));

  b) ALTER TABLE orcamento ADD COLUMN cd_cond_pagamento int;
   ALTER TABLE orcamento ADD CONSTRAINT cd_cond_pagamento 
   FOREIGN KEY(cd_cond_pagamento) REFERENCES cond_pagamento(cd_cond_pagamento);

  c) INSERT INTO cond_pagamento VALUES (1, 'boleto');
   INSERT INTO cond_pagamento VALUES (2, 'credito');
   INSERT INTO cond_pagamento VALUES (3, 'cheque');

   UPDATE orcamento SET cd_cond_pagamento = 1 WHERE nr_orcamento = 1234;
   UPDATE orcamento SET cd_cond_pagamento = 2 WHERE nr_orcamento = 4321;
   UPDATE orcamento SET cd_cond_pagamento = 3 WHERE nr_orcamento = 7412;
   UPDATE orcamento SET cd_cond_pagamento = 3 WHERE nr_orcamento = 7458;
   UPDATE orcamento SET cd_cond_pagamento = 2 WHERE nr_orcamento = 8526;
   UPDATE orcamento SET cd_cond_pagamento = 1 WHERE nr_orcamento = 9632;
   UPDATE orcamento SET cd_cond_pagamento = 2 WHERE nr_orcamento = 9635;

2) SELECT o.nr_orcamento, o.dt_emissao, o.vl_total, c.ds_cond_pagamento FROM orcamento o JOIN cond_pagamento c 
   ON o.cd_cond_pagamento = c.cd_cond_pagamento
   WHERE o.dt_emissao BETWEEN '2022-11-04' AND '2022-11-07'
   ORDER BY o.vl_total DESC;

3) SELECT COUNT(p.cd_categoria), c.ds_categoria FROM categoria c, produto p
   WHERE c.cd_categoria = p.cd_categoria
   GROUP BY c.cd_categoria;

4) SELECT o.nr_orcamento, o.vl_total, COUNT(i.cd_produto) FROM orcamento o, item_orcamento i
   WHERE o.nr_orcamento = i.nr_orcamento
   GROUP BY o.nr_orcamento
   HAVING COUNT(*) > 1;

5) SELECT p.cd_produto FROM produto p WHERE p.cd_produto NOT IN
   (SELECT i.cd_produto FROM orcamento o, item_orcamento i
   WHERE o.nr_orcamento = i.nr_orcamento AND o.dt_emissao BETWEEN '2022-11-03' AND '2022-11-05');