package org.example;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public abstract class Utilizator {
    String nume;
    PriorityQueue<Cerere> cereriInAsteptare = new PriorityQueue<>(10, new Cerere.ComparatorCerereInAsteptare()),
            cereriFinalizate = new PriorityQueue<>(10, new Cerere.ComparatorCerere());


    static class TipCerereInvalid extends Exception {
        String mesaj;

        public TipCerereInvalid(String message) {
            this.mesaj = message;
        }

    }

    public String getNume() {
        return nume;
    }

    public abstract String scrieCerere(Cerere.TipCerere c) throws TipCerereInvalid;

    // adauga cerere finalizata in coada de cereri finalizate
    public void adaugaCerereFinalizata(Cerere c) {
        cereriFinalizate.add(c);
    }

    // cauta si retrage cerere pe baza timpului specific cererii din coada de cereri in asteptare si coada de cereri finalizate
    public void retrageCerere(String data) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss", Locale.ENGLISH);
        Date date;
        try {
            date = formatter.parse(data);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        cereriInAsteptare.removeIf(c -> c.getDate().equals(date));
        cereriFinalizate.removeIf(c -> c.getDate().equals(date));
    }

    // retrage o cerere din coada de cereri in asteptare
    public void retrageCerereInAsteptare(Cerere c) {
        cereriInAsteptare.remove(c);
    }

}
