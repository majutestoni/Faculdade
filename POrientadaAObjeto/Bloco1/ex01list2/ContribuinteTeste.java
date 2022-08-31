package ex01list2;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ContribuinteTest {

	@Test
	void testSetUf() {
		fail("Not yet implemented");
	}

	@Test
	void testSetRendaAnual() {
		fail("Not yet implemented");
	}

	@Test
	void testImposto_renda3000_valor0() {
		Contribuinte c = new Contribuinte();
		c.setRendaAnual(3000);
		double impostoCalculado = c.calcularImposto();
		assertEquals(0.0, impostoCalculado,0);
		
		
		
	}

}
