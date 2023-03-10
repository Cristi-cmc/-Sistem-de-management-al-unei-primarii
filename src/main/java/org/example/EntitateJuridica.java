package org.example;

public class EntitateJuridica extends Utilizator {

    private String reprezentant;

    public EntitateJuridica(String nume, String reprezentant) {
        this.nume = nume;
        this.setReprezentant(reprezentant);
    }

    public String scrieCerere(Cerere.TipCerere c) throws TipCerereInvalid {

        if (c != Cerere.TipCerere.CREARE_ACT_CONSTITUTIV && c != Cerere.TipCerere.REINNOIRE_AUTORIZATIE) {
            StringBuilder eroare = new StringBuilder();
            eroare.append("Utilizatorul de tip entitate juridica nu poate inainta o cerere de tip ");
            switch (c) {
                case INREGISTRARE_VENIT_SALARIAL:
                    eroare.append("inregistrare venit salarial");
                    break;
                case INLOCUIRE_CARNET_DE_ELEV:
                    eroare.append("inlocuire carnet de elev");
                    break;
                case INREGISTRARE_CUPOANE_DE_PENSIE:
                    eroare.append("inregistrare cupoane de pensie");
                    break;
                case INLOCUIRE_CARNET_DE_SOFER:
                    eroare.append("inlocuire carnet de sofer");
                    break;
                case INLOCUIRE_BULETIN:
                    eroare.append("inlocuire buletin");
                    break;
            }
            return eroare.toString();
        }

        String cerere = "";

        switch (c) {
            case CREARE_ACT_CONSTITUTIV:
                cerere = "creare act constitutiv";
                break;
            case REINNOIRE_AUTORIZATIE:
                cerere = "reinnoire autorizatie";
                break;
        }

        return "Subsemnatul " + getReprezentant() + ", reprezentant legal al companiei " + nume + ", va rog sa-mi aprobati urmatoarea solicitare: " + cerere;
    }

    public String getReprezentant() {
        return reprezentant;
    }

    public void setReprezentant(String reprezentant) {
        this.reprezentant = reprezentant;
    }
}
