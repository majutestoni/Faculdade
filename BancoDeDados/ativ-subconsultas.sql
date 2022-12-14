a) SELECT c.ds_cor FROM cor c WHERE c.cd_cor NOT IN (SELECT v.cd_cor FROM veiculo v);

b) SELECT v.nr_placa, m.ds_modelo FROM veiculo v, modelo m WHERE v.cd_modelo = m.cd_modelo = (SELCT MAX(nr_ano_mod) FROM veiculo);

c) SELECT nr_placa, COUNT(*) FROM veiculo_combustivel GROUP BY nr_placa HAVING COUNT(*) >= 2;

d) SELECT m.ds_modelo  FROM modelo m, veiculo v  WHERE m.cd_modelo = v.cd_modelo 
   GROUP BY m.ds_modelo
   HAVING COUNT(*) >= ALL (SELECT COUNT(*) FROM  veiculo GROUP BY cd_modelo);
   
e) SELECT m.ds_marca FROM marca m, modelo mo WHERE m.cd_marca = mo.cd_marca
   GROUP BY m.ds_marca
   HAVING COUNT(*) <= ALL (SELECT COUNT(*) FROM modelo o GROUP BY o.cd_marca);
   
f) SELECT a.ds_acessorio FROM acessorio a, veiculo_acessorio vc WHERE vc.cd_acessorio = a.cd_acessorio
   GROUP BY a.ds_acessorio HAVING COUNT(*) >= ALL
   (SELECT COUNT(*) FROM veiculo_acessorio GROUP BY cd_acessorio)
   
 g) SELECT vc.cd_acessorio FROM veiculo_acessorio vc WHERE vc.nr_placa IN
   (SELECT nr_placa FROM veiculo WHERE nr_ano_fab < 2015)