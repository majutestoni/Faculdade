package com.estudp.resumo_mensagem.codigo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Majú Testoni (maria.testoni@publicatecnologia.com.br)
 */
@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {

	UsuarioEntity getUsuarioByLoginAndSenha(String login, String senha);
}
