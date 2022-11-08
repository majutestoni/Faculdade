package Prova2;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;

import org.junit.Test;

class TRESCTest {
    private TRESC tresc = new TRESC();

    /**
     * @throws Exception
     */
    @BeforeAll
    static void setUpBeforeClass() throws Exception {
    }

    @Test
    void testGetVolume() {
        LocalDate b = LocalDate.of(2021, 11, 22);
        Equipamento a = new Computador("Rua a", 88, b, "CMP-1023", "Windows", "5.1.876", 157);
        Equipamento c = new Urna(88, b, "UE-0001", 370, 264, "5.1.876");

        tresc.addEquipamento(a);
        tresc.addEquipamento(c);
        assertEquals(841.000, tresc.getVolume());
    }

    @Test
    void testGetChave() {
        LocalDate b = LocalDate.of(2021, 11, 22);
        Equipamento a = new Computador("Rua Antonio da Veiga", 88, b, "CMP-1023", "Windows", "10.1.002.835", 157);

        assertEquals("CAA881578", a.getChave());
    }

    @Test
    void testGetChave2() {
        LocalDate b = LocalDate.of(2018, 03, 14);
        Equipamento c = new Urna(88, b, "UE-00001", 370, 264, "5.1.876");

        assertEquals("UE5.1.876ANT88370", c.getChave());
    }

    @Test
    void testGetChave3() {
        LocalDate b = LocalDate.of(2021, 05, 15);
        Equipamento c = new Urna(88, b, "UE-00001", 369, 301, "5.2.878");

        assertEquals("UE5.2.878ATU88369", c.getChave());
    }

}
