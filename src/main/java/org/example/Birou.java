package org.example;

import java.io.BufferedWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.PriorityQueue;

public class Birou<T extends Utilizator> {

    private ArrayList<T> utilizatori;
    private ArrayList<FunctionarPublic<T>> functionari;
    private PriorityQueue<Cerere> cereriBirou = new PriorityQueue<>(10, new Cerere.ComparatorCerere());

    public Birou() {
        this.setUtilizatori(new ArrayList<>());
        this.setFunctionari(new ArrayList<>());
    }

    public void adaugaUtilizator(T utilizator) {
        getUtilizatori().add(utilizator);
    }

    public void adaugaFunctionar(FunctionarPublic<T> functionar) {
        getFunctionari().add(functionar);
    }

    public FunctionarPublic<T> cautaFunctionar(String nume) {
        for (FunctionarPublic<T> f : getFunctionari()) {
            if (f.getNume().equals(nume)) {
                return f;
            }
        }
        return null;
    }

    public void adaugaCerereInBirou(Cerere cerere) {
        getCereriBirou().add(cerere);
    }

    public void retrageCerereDinBirou(String data) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss", Locale.ENGLISH);
        Date dataCerere;
        try {
            dataCerere = formatter.parse(data);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        getCereriBirou().removeIf(c -> c.getDate().equals(dataCerere));
    }

    public void scrieCereriDinBirouInAsteptare(String fileName, Birou<? extends Utilizator> birou, int tipBirou) {
        try {

            java.io.FileWriter fileWriter = new java.io.FileWriter(fileName, true);
            BufferedWriter bw = new BufferedWriter(fileWriter);
            PriorityQueue<Cerere> cereriDinBirou = new PriorityQueue<>(birou.getCereriBirou());

            switch (tipBirou) {
                case (1):
                    bw.write("angajat - cereri in birou:");
                    bw.newLine();
                    break;
                case (2):
                    bw.write("pensionar - cereri in birou:");
                    bw.newLine();
                    break;
                case (3):
                    bw.write("persoana - cereri in birou:");
                    bw.newLine();
                    break;
                case (4):
                    bw.write("entitate juridica - cereri in birou:");
                    bw.newLine();
                    break;
                case (5):
                    bw.write("elev - cereri in birou:");
                    bw.newLine();
                    break;
            }

            SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss", Locale.ENGLISH);

            while (!cereriDinBirou.isEmpty()) {
                Cerere cerere = cereriDinBirou.poll();
                bw.write(cerere.getPrioritate() + " - " + formatter.format(cerere.getDate()) + " - " + cerere.getDescriere());
                bw.newLine();
            }

            bw.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<T> getUtilizatori() {
        return utilizatori;
    }

    public void setUtilizatori(ArrayList<T> utilizatori) {
        this.utilizatori = utilizatori;
    }

    public ArrayList<FunctionarPublic<T>> getFunctionari() {
        return functionari;
    }

    public void setFunctionari(ArrayList<FunctionarPublic<T>> functionari) {
        this.functionari = functionari;
    }

    public PriorityQueue<Cerere> getCereriBirou() {
        return cereriBirou;
    }

    public void setCereriBirou(PriorityQueue<Cerere> cereriBirou) {
        this.cereriBirou = cereriBirou;
    }
}
