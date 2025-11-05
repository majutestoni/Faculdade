package com.estudp.resumo_mensagem.codigo;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Majú Testoni
 */
@RestController()
@RequestMapping("/usuario")
public class UsuarioController {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@PostMapping
	public ResponseEntity<?> cadastraUsuario(@RequestBody UsuarioDTO dto) throws NoSuchAlgorithmException {
		UsuarioEntity entity = new UsuarioEntity();

		byte[] senhaByte = dto.senha().getBytes();
		MessageDigest digest = MessageDigest.getInstance("SHA-256");
		byte[] senhaResumo = digest.digest(senhaByte);
		String senhaResumoString = bytesToHex(senhaResumo);

		entity.setLogin(dto.login());
		entity.setSenha(senhaResumoString);

		entity = usuarioRepository.save(entity);

		return ResponseEntity.ok(entity.toString());
	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody UsuarioDTO dto) throws NoSuchAlgorithmException {
		byte[] senhaByte = dto.senha().getBytes();
		MessageDigest digest = MessageDigest.getInstance("SHA-256");
		byte[] senhaResumo = digest.digest(senhaByte);
		String senhaResumoString = bytesToHex(senhaResumo);

		UsuarioEntity entity = usuarioRepository.getUsuarioByLoginAndSenha(dto.login(), senhaResumoString);

		if (entity == null) {
			return ResponseEntity.badRequest().build();
		}

		return ResponseEntity.ok(entity.toString());
	}

	private String bytesToHex(byte[] hash) {
		StringBuilder hexString = new StringBuilder();
		for (byte b : hash) {
			String hex = Integer.toHexString(0xff & b);
			if (hex.length() == 1) hexString.append('0');
			hexString.append(hex);
		}
		return hexString.toString();
	}

}
