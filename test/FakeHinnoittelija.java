import MockEsimerkki.Asiakas;
import MockEsimerkki.Hinnoittelija;
import MockEsimerkki.Tuote;

public class FakeHinnoittelija extends Hinnoittelija {
    private float alennus;

    public FakeHinnoittelija(float alennus) {
        this.alennus = alennus;
    }

    public float getAlennusProsentti(Asiakas asiakas, Tuote tuote) {
        return alennus;
    }
}
