package org.example;

public class Angajat extends Utilizator {

    private String companie;

    public Angajat(String nume, String companie) {
        this.nume = nume;
        this.setCompanie(companie);
    }

    public String scrieCerere(Cerere.TipCerere c) throws TipCerereInvalid {

        if (c != Cerere.TipCerere.INLOCUIRE_BULETIN && c != Cerere.TipCerere.INLOCUIRE_CARNET_DE_SOFER && c != Cerere.TipCerere.INREGISTRARE_VENIT_SALARIAL) {
            StringBuilder eroare = new StringBuilder();
            eroare.append("Utilizatorul de tip angajat nu poate inainta o cerere de tip ");
            switch (c) {
                case INLOCUIRE_CARNET_DE_ELEV:
                    eroare.append("inlocuire carnet de elev");
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
            }
            return eroare.toString();
        }

        String cerere = "";

        switch (c) {
            case INLOCUIRE_BULETIN :
                cerere = "inlocuire buletin";
                break;
            case INLOCUIRE_CARNET_DE_SOFER:
                cerere = "inlocuire carnet de sofer";
                break;
            case INREGISTRARE_VENIT_SALARIAL:
                cerere = "inregistrare venit salarial";
                break;
        }

        return "Subsemnatul " + nume + ", angajat la compania " + getCompanie() + ", va rog sa-mi aprobati urmatoarea solicitare: " + cerere;
    }

    public String getCompanie() {
        return companie;
    }

    public void setCompanie(String companie) {
        this.companie = companie;
    }
}

