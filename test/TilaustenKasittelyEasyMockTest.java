import MockEsimerkki.*;
import org.junit.*;
import static org.junit.Assert.*;
import static org.easymock.EasyMock.*;

public class TilaustenKasittelyEasyMockTest {

    /*@Test
    public void testaaKäsittelijäWithEasyMockHinnoittelija() {
        // arrange
        float alkuSaldo = 100.0f;
        float listaHinta = 30.0f;
        float alennus = 20.0f;
        float loppuSaldo = alkuSaldo - (listaHinta * (1 - alennus / 100));

        Asiakas asiakas = new Asiakas(alkuSaldo);
        Tuote tuote = new Tuote("TDD in Action", listaHinta);
        Hinnoittelija hinnoittelija = createMock(Hinnoittelija.class);

        // record
        expect(hinnoittelija.getAlennusProsentti(asiakas, tuote))
                .andReturn(alennus);
        replay(hinnoittelija);

        // act
        TilaustenKäsittely käsittelijä = new TilaustenKäsittely();
        käsittelijä.setHinnoittelija(hinnoittelija);
        käsittelijä.käsittele(new Tilaus(asiakas, tuote));
        // assert
        assertEquals(loppuSaldo, asiakas.getSaldo(), 0.001);
        verify(hinnoittelija);
    }*/

    @Test
    public void testaaKäsittelijäWithEasyMockHinnoittelijaAlle100() {
        float alkuSaldo = 100.0f;
        float listaHinta = 50.0f;
        float alennus = 20.0f;
        float loppuSaldo = alkuSaldo - (listaHinta * (1 - alennus / 100));

        Asiakas asiakas = new Asiakas(alkuSaldo);
        Tuote tuote = new Tuote("TDD in Action", listaHinta);
        Hinnoittelija hinnoittelija = createMock(Hinnoittelija.class);

        hinnoittelija.aloita();
        expect(hinnoittelija.getAlennusProsentti(asiakas, tuote)).andReturn(alennus);
        expectLastCall().times(2);
        hinnoittelija.lopeta();
        replay(hinnoittelija);

        TilaustenKäsittely kasittelija = new TilaustenKäsittely();
        kasittelija.setHinnoittelija(hinnoittelija);
        kasittelija.käsittele(new Tilaus(asiakas, tuote));

        assertEquals(loppuSaldo, asiakas.getSaldo(), 0.001);
        verify(hinnoittelija);
    }

    @Test
    public void testaaKäsittelijäWithEasyMockhinnoittelijaYli100() {
        float alkuSaldo = 100.0f;
        float listaHinta = 250.0f;
        float alennus = 20.0f;
        float loppuSaldo = alkuSaldo - (listaHinta * (1 - alennus / 100));

        Asiakas asiakas = new Asiakas(alkuSaldo);
        Tuote tuote = new Tuote("TDD in Action", listaHinta);
        Hinnoittelija hinnoittelija = createMock(Hinnoittelija.class);

        hinnoittelija.aloita();
        expect(hinnoittelija.getAlennusProsentti(asiakas, tuote)).andReturn(alennus);
        expectLastCall().times(2);
        hinnoittelija.setAlennusProsentti(asiakas, alennus + 5);
        hinnoittelija.lopeta();
        replay(hinnoittelija);

        TilaustenKäsittely kasittelija = new TilaustenKäsittely();
        kasittelija.setHinnoittelija(hinnoittelija);
        kasittelija.käsittele(new Tilaus(asiakas, tuote));

        assertEquals(loppuSaldo, asiakas.getSaldo(), 0.001);
        verify(hinnoittelija);
    }

}
