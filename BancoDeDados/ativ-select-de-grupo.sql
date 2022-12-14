a) SELECT AVG(v.qt_km_rodado) FROM veiculo v;

b) SELECT MAX(v.nr_ano_fab), MIN(V.nr_ano_fab) FROM veiculo v;

C) SELECT v.nr_ano_mod, COUNT(v.nr_ano_mod) FROM veiculo v GROUP BY v.nr_ano_mod;

d) SELECT c.ds_combustivel, COUNT(v.cd_combustivel) FROM combustivel c JOIN veiculo_combustivel v ON  c.cd_combustivel = v.cd_combustivel 
GROUP BY v.cd_combustivel ORDER BY COUNT(v.cd_combustivel) DESC

e) SELECT m.ds_modelo, COUNT(v.cd_modelo) FROM modelo m JOIN veiculo v ON m.cd_modelo = v.cd_modelo GROUP BY m.cd_modelo;

f) SELECT m.ds_marca, o.ds_modelo, COUNT(v.cd_modelo) FROM marca m JOIN modelo o JOIN  veiculo v ON m.cd_marca = o.cd_marca 
AND o.cd_modelo = v.cd_modelo GROUP BY (o.cd_modelo);

g) SELECT a.ds_acessorio, COUNT(vc.cd_acessorio) FROM acessorio a JOIN veiculo_acessorio vc ON a.cd_acessorio = vc.cd_acessorio GROUP BY vc.cd_acessorio;

h) SELECT l.nm_localidade, COUNT(p.cd_localidade) FROM localidade l JOIN poprietario p ON l.cd_localidade = p.cd_localidade  
WHERE p.sg_uf = 'SC' GROUP BY l.cd_localidade;

i) SELECT v.nr_placa, m.ds_marca, o.ds_modelo, COUNT(vc.cd_acessorio) FROM  marca m JOIN modelo o JOIN veiculo v JOIN veiculo_acessorio vc 
ON m.cd_marca = o.cd_marca AND o.cd_modelo = v.cd_modelo AND v.nr_placa = vc.nr_placa GROUP BY (v.nr_placa)
HAVING COUNT(0) > 1;

j) SELECT v.nr_placa, m.ds_marca, o.ds_modelo, COUNT(vc.cd_combustivel) FROM marca m JOIN  modelo o JOIN veiculo v JOIN veiculo_combustivel vc 
ON m.cd_marca = o.cd_marca AND o.cd_modelo = v.cd_modelo AND v.nr_placa = vc.nr_placa
GROUP BY v.nr_placa;