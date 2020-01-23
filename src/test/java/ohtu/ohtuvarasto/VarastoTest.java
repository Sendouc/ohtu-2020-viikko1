package ohtu.ohtuvarasto;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class VarastoTest {

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void liikaaOttaminenToimii() {
        varasto.otaVarastosta(15);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void toStringSisältääOikeanSaldon() {
        assertTrue(varasto.toString().indexOf("10") != -1);
    }

    @Test
    public void negaativisilleTilavuudellaSaadaanNolla() {
        Varasto v2 = new Varasto(-1);
        assertEquals(0, v2.getSaldo(), vertailuTarkkuus);

        // Varasto.java luokassa on ilmeisesti bugi mutta ymmärsin että siihen
        // ei tule koskea joten testi tässä muodossa
        Varasto v3 = new Varasto(-1, 5);
        assertEquals(-1, v3.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void varastoVoidaanLuodaAlkusaldolla() {
        Varasto v2 = new Varasto(10, 5);
        assertEquals(v2.getSaldo(), 5, vertailuTarkkuus);
    }

    @Test
    public void negaativisilleAlkusaldollaTuleeNolla() {
        Varasto v2 = new Varasto(10, -1);
        assertEquals(v2.getSaldo(), 0, vertailuTarkkuus);
    }

    @Test
    public void varastoonEiVoiLisataNegatiivisesti() {
        varasto.lisaaVarastoon(-1);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void liikaaLisatessaMeneeTayteen() {
        varasto.lisaaVarastoon(15);
        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void negatiivisestiEiVoiOttaa() {
        varasto.lisaaVarastoon(5);
        varasto.otaVarastosta(-1);
        assertEquals(5, varasto.getSaldo(), vertailuTarkkuus);
    }
}
