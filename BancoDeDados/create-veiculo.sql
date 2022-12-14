CREATE TABLE combustivel(cd_combustivel INTEGER not null, ds_combustivel VARCHAR(30), primary key (cd_combustivel));

CREATE TABLE localidade(cd_localidade INTEGER NOT NULL, nm_localidade VARCHAR(50), PRIMARY KEY (cd_localidade));

CREATE TABLE marca(cd_marca INTEGER, ds_marca VARCHAR(30), PRIMARY KEY (cd_marca));

CREATE TABLE modelo(cd_modelo INTEGER, 
ds_modelo VARCHAR(50), 
PRIMARY KEY (cd_modelo), 
cd_marca INTEGER,
FOREIGN KEY (cd_marca) REFERENCES marca(cd_marca)
);

CREATE TABLE acessorio(cd_acessorio  INTEGER, ds_acessorio VARCHAR(50), PRIMARY KEY (cd_acessorio));

CREATE TABLE cor(cd_cor INTEGER, ds_cor VARCHAR(30), PRIMARY KEY (cd_cor));

CREATE TABLE poprietario(cd_propietario INTEGER, nm_proprietario VARCHAR(50), ds_logradouro VARCHAR(50), 
ds_complemento VARCHAR(50), ds_bairro VARCHAR(50), nr_telefone VARCHAR(15), ds_email VARCHAR(50), sg_uf CHAR(2),
PRIMARY KEY (cd_propietario),
cd_localidade INTEGER,
FOREIGN KEY (cd_localidade) REFERENCES localidade(cd_localidade));

CREATE TABLE veiculo(nr_placa CHAR(7), nr_ano_fab INTEGER, nr_ano_mod INTEGER, qt_km_rodado INTEGER, qt_portas INTEGER, ds_observacao VARCHAR(100),
PRIMARY KEY (nr_placa),
cd_cor INTEGER, cd_propietario INTEGER, cd_modelo INTEGER,
FOREIGN KEY (cd_cor) REFERENCES cor(cd_cor), FOREIGN KEY (cd_propietario) REFERENCES poprietario(cd_propietario), FOREIGN KEY (cd_modelo) REFERENCES modelo(cd_modelo));

CREATE TABLE veiculo_combustivel(cd_veiculo_combustivel INTEGER, cd_combustivel INTEGER, nr_placa CHAR(7),
FOREIGN KEY (cd_combustivel) REFERENCES combustivel(cd_combustivel), FOREIGN KEY (nr_placa) REFERENCES veiculo(nr_placa));


