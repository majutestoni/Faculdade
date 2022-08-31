import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

class InteiroPositivoTest {

	@Test
	void test_fatorial_10() {
		InteiroPositivo i = new InteiroPositivo();
		
		assertEquals(3628800, i.fatorial(10), 0);
	}
	@Test
	void test_fatorial_20() {
		InteiroPositivo i = new InteiroPositivo();
		
		
		i.setValor(20);
		assertEquals(2432902008176640000L, i.fatorialValor(), 0);
	}
	
	@Test
	void test_divisor_14() {
		InteiroPositivo i = new InteiroPositivo();
		i.setValor(14);
		
		assertEquals(" 1, 2, 7, 14 e a quantidade de divisores é 4 ", i.divisores());
	}
	@Test
	void test_divisor_18() {
		InteiroPositivo i = new InteiroPositivo();
		i.setValor(18);
		
		assertEquals(" 1, 2, 3, 6, 9, 18 e a quantidade de divisores é 6 ", i.divisores());
	}
	@Test
	void test_fibonacci_9() {
		InteiroPositivo i = new InteiroPositivo();
		i.setValor(9);
		int[] Vetor = {1, 1, 2, 3, 5, 8, 13, 21, 34};
		
		assertEquals(Arrays.toString(Vetor),Arrays.toString(i.fibonacci()));
	}
	@Test
	void test_fibonacci_15() {
		InteiroPositivo i = new InteiroPositivo();
		i.setValor(15);
		int[] Vetor = {1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610};
		
		assertEquals(Arrays.toString(Vetor),Arrays.toString(i.fibonacci()));
	}

}
