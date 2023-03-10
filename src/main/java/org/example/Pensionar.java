package org.example;

public class Pensionar extends Utilizator {

    public Pensionar(String nume) {
        this.nume = nume;
    }

    @Override
    public String scrieCerere(Cerere.TipCerere c) throws TipCerereInvalid {

        if (c != Cerere.TipCerere.INLOCUIRE_BULETIN && c != Cerere.TipCerere.INLOCUIRE_CARNET_DE_SOFER && c != Cerere.TipCerere.INREGISTRARE_CUPOANE_DE_PENSIE) {
            StringBuilder eroare = new StringBuilder();
            eroare.append("Utilizatorul de tip pensionar nu poate inainta o cerere de tip ");
            switch (c) {
                case INREGISTRARE_VENIT_SALARIAL:
                    eroare.append("inregistrare venit salarial");
                    break;
                case INLOCUIRE_CARNET_DE_ELEV:
                    eroare.append("inlocuire carnet de elev");
                    break;
                case CREARE_ACT_CONSTITUTIV:
                    eroare.append("creare act constitutiv");
                    break;
                case REINNOIRE_AUTORIZATIE:
                    eroare.append("reinnoire autorizatie");
                    break;

            }
            return eroare.toString();
        }


        String cerere = "";
        switch (c) {
            case INLOCUIRE_BULETIN:
                cerere = "inlocuire buletin";
                break;
            case INLOCUIRE_CARNET_DE_SOFER:
                cerere = "inlocuire carnet de sofer";
                break;
            case INREGISTRARE_CUPOANE_DE_PENSIE:
                cerere = "inregistrare cupoane de pensie";
                break;
        }

        return "Subsemnatul " + nume + ", va rog sa-mi aprobati urmatoarea solicitare: " + cerere;
    }
}
