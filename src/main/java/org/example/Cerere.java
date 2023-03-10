package org.example;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.Locale;

public class Cerere {
    private String numeSolicitor;
    private String descriere;
    private int prioritate;
    private Date date;
    private TipCerere tip;
    private SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss", Locale.ENGLISH);

    enum TipCerere {
        INLOCUIRE_BULETIN,
        INREGISTRARE_VENIT_SALARIAL,
        INLOCUIRE_CARNET_DE_SOFER,
        INLOCUIRE_CARNET_DE_ELEV,
        CREARE_ACT_CONSTITUTIV,
        REINNOIRE_AUTORIZATIE,
        INREGISTRARE_CUPOANE_DE_PENSIE
    }

    public Cerere() {
    }

    public Cerere(String numeSolicitor, TipCerere tip, String dat, int prioritate) {

        this.setNumeSolicitor(numeSolicitor);
        this.setTip(tip);

        try {
            this.setDate(getFormatter().parse(dat));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        this.setPrioritate(prioritate);
    }

    public void setNumeSolicitor(String numeSolicitor) {
        this.numeSolicitor = numeSolicitor;
    }

    public void setPrioritate(int prioritate) {
        this.prioritate = prioritate;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setTip(TipCerere tip) {
        this.tip = tip;
    }

    public SimpleDateFormat getFormatter() {
        return formatter;
    }

    public void setFormatter(SimpleDateFormat formatter) {
        this.formatter = formatter;
    }

    public String getNumeSolicitor() {
        return numeSolicitor;
    }

    public Date getDate() {
        return date;
    }

    public int getPrioritate() {
        return prioritate;
    }

    public void setDescriere(String descriere) {
        this.descriere = descriere;
    }

    public String getDescriere() {
        return descriere;
    }

    public TipCerere getTip() {
        return tip;
    }

    public static TipCerere convertesteStringInTip(String TipString) {

        TipCerere tip = null;

        switch (TipString) {
            case ("inlocuire buletin"):
                tip = TipCerere.INLOCUIRE_BULETIN;
                break;
            case ("inlocuire carnet de sofer"):
                tip = TipCerere.INLOCUIRE_CARNET_DE_SOFER;
                break;
            case ("inlocuire carnet de elev"):
                tip = TipCerere.INLOCUIRE_CARNET_DE_ELEV;
                break;
            case ("inregistrare venit salarial"):
                tip = TipCerere.INREGISTRARE_VENIT_SALARIAL;
                break;
            case ("inregistrare cupoane de pensie"):
                tip = TipCerere.INREGISTRARE_CUPOANE_DE_PENSIE;
                break;
            case ("creare act constitutiv"):
                tip = TipCerere.CREARE_ACT_CONSTITUTIV;
                break;
            case ("reinnoire autorizatie"):
                tip = TipCerere.REINNOIRE_AUTORIZATIE;
                break;
        }
        return tip;
    }

    static class ComparatorCerereInAsteptare implements Comparator<Cerere> {
        @Override
        public int compare(Cerere o1, Cerere o2) {
            if (o1.getDate().after(o2.getDate())) {
                return 1;
            } else if (o1.getDate().before(o2.getDate())) {
                return -1;
            } else {
                return 0;
            }
        }

    }

    static class ComparatorCerere implements Comparator<Cerere> {

        public int compare(Cerere o1, Cerere o2) {
            if (o1.getPrioritate() > o2.getPrioritate()) {
                return -1;
            } else if (o1.getPrioritate() < o2.getPrioritate()) {
                return 1;
            } else {
                // compare by date
                if (o1.getDate().after(o2.getDate())) {
                    return 1;
                } else if (o1.getDate().before(o2.getDate())) {
                    return -1;
                } else {
                    return 0;
                }
            }
        }
    }
}
