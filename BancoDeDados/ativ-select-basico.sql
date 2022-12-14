a) select * from modelo order by ds_modelo;

b) select ds_marca from marca;

c) select nm_proprietario, sg_uf from poprietario where sg_uf = 'PR' or sg_uf = 'SC';

d) select * from poprietario  where ds_email is null and nr_telefone is not null;

e) select *  from poprietario p where nm_proprietario like 'Silva';

f) select * from veiculo where nr_ano_fab <> nr_ano_mod;

g) select nr_placa, nr_ano_mod, qt_km_rodado, qt_km_rodado  from veiculo v where nr_ano_fab >= 2000;

h) select nr_placa from veiculo v where ds_observacao is null;

i) select * from veiculo v where qt_portas >= 4 order by nr_ano_mod desc; 

j) select * from acessorio a order by ds_acessorio;

k) select nr_placa, nr_ano_fab, nr_ano_mod  from veiculo v where nr_ano_mod > 2015 and qt_km_rodado < 50000 and qt_portas >= 4 and ds_observacao is null;

l) select nm_proprietario  from poprietario p where  ds_bairro like 'Nov_' and sg_uf like 'SC';