package org.example;

public class Elev extends Utilizator {

    private String scoala;

    public Elev(String nume, String scoala) {
        this.nume = nume;
        this.setScoala(scoala);
    }

    public String scrieCerere(Cerere.TipCerere c) throws TipCerereInvalid {

        if (c != Cerere.TipCerere.INLOCUIRE_BULETIN && c != Cerere.TipCerere.INLOCUIRE_CARNET_DE_ELEV) {
            StringBuilder eroare = new StringBuilder();
            eroare.append("Utilizatorul de tip elev nu poate inainta o cerere de tip ");
            switch (c) {
                case INREGISTRARE_VENIT_SALARIAL:
                    eroare.append("inregistrare venit salarial");
                    break;
                case CREARE_ACT_CONSTITUTIV:
                    eroare.append("creare act constitutiv");
                    break;
                case REINNOIRE_AUTORIZATIE:
                    eroare.append("reinnoire autorizatie");
                    break;
                case INREGISTRARE_CUPOANE_DE_PENSIE:
                    eroare.append("inregistrare cupoane de pensie");
                    break;
                case INLOCUIRE_CARNET_DE_SOFER:
                    eroare.append("inlocuire carnet de sofer");
                    break;
            }
            return eroare.toString();
        }

        String cerere = "";

        switch (c) {
            case INLOCUIRE_BULETIN:
                cerere = "inlocuire buletin";
                break;
            case INLOCUIRE_CARNET_DE_ELEV:
                cerere = "inlocuire carnet de elev";
                break;
        }

        return "Subsemnatul " + nume + ", elev la scoala " + getScoala() + ", va rog sa-mi aprobati urmatoarea solicitare: " + cerere;
    }

    public String getScoala() {
        return scoala;
    }

    public void setScoala(String scoala) {
        this.scoala = scoala;
    }
}
