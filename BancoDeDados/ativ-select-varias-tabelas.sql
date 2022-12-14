a) select p.nm_proprietario, l.nm_localidade from poprietario p, localidade l where l.cd_localidade  = p.cd_localidade; 

b) select m2.ds_modelo from marca m, modelo m2 where m2.cd_marca = 3 order by m2.ds_modelo;

c) select m.ds_marca, m2.ds_modelo  from marca m join modelo m2  on m.cd_marca = m2.cd_modelo order by m.cd_marca, m2.cd_modelo  

d) select v.nr_placa,v.nr_ano_mod, c.ds_cor  from veiculo v join cor c  on c.cd_cor = v.cd_cor order by nr_ano_mod,  c.ds_cor;  

f) select v.nr_placa, p.nm_proprietario, l.nm_localidade  from veiculo v join localidade l join poprietario p   
on l.cd_localidade = p.cd_localidade and v.cd_propietario = p.cd_propietario where p.sg_uf = 'SC';  

g) select v.nr_placa, m.ds_marca, m2.ds_modelo, va.cd_acessorio  from veiculo_acessorio va join veiculo v join marca m join modelo m2  
on va.nr_placa = v.nr_placa and v.cd_modelo = m2.cd_modelo and m2.cd_marca = m.cd_marca  where va.cd_acessorio = 6;

h) select v.*, c.ds_combustivel, a.ds_acessorio, c2.ds_cor  from veiculo v join veiculo_combustivel vc join combustivel c  join cor c2 join veiculo_acessorio va join acessorio a  
on c.cd_combustivel = vc.cd_combustivel and vc.nr_placa = v.nr_placa and c2.cd_cor = v.cd_cor and va.nr_placa = v.nr_placa and va.cd_acessorio = a.cd_acessorio; 