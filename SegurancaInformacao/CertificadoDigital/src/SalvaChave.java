import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;

public class SalvaChave {
	public static void main(String[] args) throws IOException {

		String base64 = "lX3HmEQo5lpbZAH59OwBEYEc4rCRsbmxQ2fCTD1AWiqAGQ3BPeY8sFMiX1x8NpM1ybR8v2yvkZAu1Z2H8vRFpg==";

		byte[] keyBytes = Base64.getDecoder().decode(base64);

		try (FileOutputStream fos = new FileOutputStream("key.aes")) {
			fos.write(keyBytes);
		}

		System.out.println("Arquivo key.aes salvo com sucesso!");
	}
}
