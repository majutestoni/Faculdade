import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Enumeration;

public class Main {
	public static void main(String[] args) throws KeyStoreException, CertificateException, IOException, NoSuchAlgorithmException {
		String senha = "maju";
		InputStream inputStream = new FileInputStream(
				"src/arquivo/repositorio.jks"
		);

		KeyStore keyStore = KeyStore.getInstance("JKS");
		keyStore.load(inputStream, senha.toCharArray());

		// pegar todos os certificados
		Enumeration<String> aliases = keyStore.aliases();
		Certificate[] certificate = keyStore.getCertificateChain(senha);

		// detalhar cada certificado
		while (aliases.hasMoreElements()) {
			String alias = aliases.nextElement();
			System.out.println("Alias encontrado: " + alias);

			X509Certificate cert = (X509Certificate) keyStore.getCertificate(alias);

			if (cert != null) {
				System.out.println("  Proprietário: " + cert.getSubjectDN());
				System.out.println("  Emissor:      " + cert.getIssuerDN());
				System.out.println("  Válido de:    " + cert.getNotBefore());
				System.out.println("  Válido até:   " + cert.getNotAfter());
				System.out.println("  Autoassinado: " + cert.getIssuerDN().equals(cert.getSubjectDN()));
				System.out.println();
			} else {
				System.out.println("  (Nenhum certificado associado)");
			}
		}
	}


}