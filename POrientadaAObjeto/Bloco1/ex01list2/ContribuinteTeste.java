
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ContribuinteTest {

	@Test
	void testSetUf_SC() {
		Contribuinte c = new Contribuinte();
		c.setUf("SC");
		assertEquals("SC", c.getUf());
	}
	@Test
	void testSetUf_RS() {
		Contribuinte c = new Contribuinte();
		c.setUf("RS");
		assertEquals("RS", c.getUf());
	}
	@Test
	void testSetUf_PR() {
		Contribuinte c = new Contribuinte();
		c.setUf("PR");
		assertEquals("PR", c.getUf());
	}
	@Test
	void testSetUf_SP() {
		Contribuinte c = new Contribuinte();
		c.setUf("SP");
		assertEquals(null, c.getUf());
	}
	@Test
	void testSetUf_RJ() {
		Contribuinte c = new Contribuinte();
		c.setUf("RJ");
		assertEquals(null, c.getUf());
	}

	@Test
	void testSetRendaAnual_negativa() {
		Contribuinte c = new Contribuinte();
		c.setRendaAnual(-818.50);
		assertEquals(0, c.getRendaAnual());
	}
	@Test
	void testSetRendaAnual_negativa_2() {
		Contribuinte c = new Contribuinte();
		c.setRendaAnual(200);
		c.setRendaAnual(-818.50);
		assertEquals(200, c.getRendaAnual());
	}

	@Test
	void testImposto_renda3000_valor0() {
		Contribuinte c = new Contribuinte();
		c.setRendaAnual(3000);
		double impostoCalculado = c.calcularImposto();
		assertEquals(0.0, impostoCalculado, 0);
	}

	@Test
	void testImposto_renda9000_valor522() {
		Contribuinte d = new Contribuinte();
		d.setRendaAnual(9000);
		double impostoCalculado2 = d.calcularImposto();
		assertEquals(522.0, impostoCalculado2, 0);
	}

	@Test
	void testImposto_renda1000_valor1500() {
		Contribuinte e = new Contribuinte();
		e.setRendaAnual(10000);
		double impostoCalculado3 = e.calcularImposto();
		assertEquals(1500.0, impostoCalculado3, 0);
	}

	@Test
	void testImposto_rendaAlta_valor9600() {
		Contribuinte f = new Contribuinte();
		f.setRendaAnual(34911.73);
		double impostoCalculado4 = f.calcularImposto();
		assertEquals(9600.72, impostoCalculado4, 0.009);
	}

}
