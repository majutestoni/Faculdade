import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class VetorReaisTest {

	@Test
	void testDivisao_vetoresTamanhosDiferentes() {
		VetorReais v = new VetorReais(5);
		VetorReais r = new VetorReais(3);

		v.setN(0, 2);
		v.setN(1, 4);
		v.setN(2, 5);
		v.setN(3, 10);
		v.setN(4, 10);
		r.setN(0, 2);
		r.setN(1, 4);
		r.setN(2, 5);

		assertNull(v.divisao(r));
	}
	@Test
	void testDivisao_vetoresTamanhosIguais() {
		VetorReais v = new VetorReais(3);
		VetorReais r = new VetorReais(3);
		
		v.setN(0, 2);
		v.setN(1, -1);
		v.setN(2, 3.5);
		r.setN(0, 3);
		r.setN(1, 2);
		r.setN(2, 1);
		
		Double[] Vetor = {0.666666, -0.5, 3.5};

		
		assertArrayEquals(Vetor, v.divisao(r));
	}

}
