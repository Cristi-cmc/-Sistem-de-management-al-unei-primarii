package org.example;

public class EvidentaTipUtilizatori {

    private String nume;

    private TipUtilizator tip;

    enum TipUtilizator {
        ELEV,
        PENSIONAR,
        ANGAJAT,
        PERSOANA,
        ENTITATE_JURIDICA
    }

    public EvidentaTipUtilizatori(String nume, TipUtilizator tip) {
        this.nume = nume;
        this.tip = tip;
    }

    public TipUtilizator getTip() {
        return tip;
    }

    public static TipUtilizator convertesteStringInTip(String tip) {
        switch (tip) {
            case "elev":
                return TipUtilizator.ELEV;
            case "pensionar":
                return TipUtilizator.PENSIONAR;
            case "angajat":
                return TipUtilizator.ANGAJAT;
            case "persoana":
                return TipUtilizator.PERSOANA;
            case "entitate juridica":
                return TipUtilizator.ENTITATE_JURIDICA;
            default:
                return null;
        }
    }

}
