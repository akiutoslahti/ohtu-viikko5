package ohtu.intjoukkosovellus;

public class IntJoukko {

    public final static int OLETUS_KAPASITEETTI = 5;
    public final static int OLETUS_KASVATUS_KOKO = 5;

    private int kasvatusKoko;
    private int[] ljono;
    private int alkioidenLkm;

    public IntJoukko() {
        alustaJoukkoJaMuuttujat(OLETUS_KAPASITEETTI, OLETUS_KASVATUS_KOKO);
    }

    public IntJoukko(int kapasiteetti) {
        if (kapasiteetti < 0) {
            return;
        }
        alustaJoukkoJaMuuttujat(kapasiteetti, OLETUS_KASVATUS_KOKO);
    }

    public IntJoukko(int kapasiteetti, int kasvatuskoko) {
        if (kapasiteetti < 0 || kasvatuskoko < 0) {
            return;
        }
        alustaJoukkoJaMuuttujat(kapasiteetti, kasvatuskoko);
    }

    private void alustaJoukkoJaMuuttujat(int kapasiteetti, int kasvatusKoko) {
        this.ljono = new int[kapasiteetti];
        this.alkioidenLkm = 0;
        this.kasvatusKoko = kasvatusKoko;
    }

    public boolean lisaa(int luku) {
        if (!kuuluu(luku)) {
            ljono[this.alkioidenLkm] = luku;
            this.alkioidenLkm++;
            if (this.alkioidenLkm == this.ljono.length) {
                kasvataJoukkoa();
            }
            return true;
        }
        return false;
    }

    private void kasvataJoukkoa() {
        int[] uusiTaulukko = new int[this.alkioidenLkm + this.kasvatusKoko];
        System.arraycopy(this.ljono, 0, uusiTaulukko, 0, alkioidenLkm);
        this.ljono = uusiTaulukko;
    }

    public boolean kuuluu(int luku) {
        for (int i = 0; i < this.alkioidenLkm; i++) {
            if (this.ljono[i] == luku) {
                return true;
            }
        }
        return false;
    }

    public boolean poista(int luku) {
        int indeksi = etsiIndeksi(luku);
        if (indeksi != -1) {
            this.ljono[indeksi] = 0;
            for (int i = indeksi; i < this.alkioidenLkm - 1; i++) {
                vaihdaIndeksienLuvut(i, i + 1);
            }
            this.alkioidenLkm--;
        }
        return false;
    }

    private void vaihdaIndeksienLuvut(int i, int j) {
        int apu = this.ljono[i];
        this.ljono[i] = this.ljono[j];
        this.ljono[j] = apu;
    }

    private int etsiIndeksi(int luku) {
        if (kuuluu(luku)) {
            for (int i = 0; i < this.alkioidenLkm; i++) {
                if (this.ljono[i] == luku) {
                    return i;
                }
            }
        }
        return -1;
    }

    public int mahtavuus() {
        return alkioidenLkm;
    }

    @Override
    public String toString() {
        String tuotos = "{";
        for (int i = 0; i < this.alkioidenLkm - 1; i++) {
            tuotos += this.ljono[i] + ", ";
        }
        if (alkioidenLkm > 0) {
            tuotos += this.ljono[this.alkioidenLkm - 1];
        }
        tuotos += "}";
        return tuotos;
    }

    public int[] toIntArray() {
        int[] taulu = new int[alkioidenLkm];
        System.arraycopy(ljono, 0, taulu, 0, taulu.length);
        return taulu;
    }

    public static IntJoukko yhdiste(IntJoukko a, IntJoukko b) {
        IntJoukko yhdiste = new IntJoukko();
        lisaaJoukkoon(yhdiste, a.toIntArray());
        lisaaJoukkoon(yhdiste, b.toIntArray());
        return yhdiste;
    }

    private static void lisaaJoukkoon(IntJoukko joukko, int[] lukuTaulukko) {
        for (int i = 0; i < lukuTaulukko.length; i++) {
            joukko.lisaa(lukuTaulukko[i]);
        }
    }

    public static IntJoukko leikkaus(IntJoukko a, IntJoukko b) {
        IntJoukko leikkaus = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) {
            if (b.kuuluu(aTaulu[i])) {
                leikkaus.lisaa(aTaulu[i]);
            }
        }
        return leikkaus;

    }

    public static IntJoukko erotus(IntJoukko a, IntJoukko b) {
        IntJoukko erotus = new IntJoukko();
        lisaaJoukkoon(erotus, a.toIntArray());
        int[] tauluB = b.toIntArray();
        for (int i = 0; i < tauluB.length; i++) {
            erotus.poista(tauluB[i]);
        }
        return erotus;
    }

}
