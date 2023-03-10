package org.example;

import java.io.BufferedWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class FunctionarPublic<T extends Utilizator> {

    private String nume;

    public FunctionarPublic(String nume) {
        this.setNume(nume);
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public void solutioneazaCerere(Birou<T> birou, String numeFisier) {

        Cerere c = null;
        if (!birou.getCereriBirou().isEmpty()) {
            c = birou.getCereriBirou().poll();
        }

        if (c != null) {
            for (T u : birou.getUtilizatori()) {
                if (u.getNume().equals(c.getNumeSolicitor())) {
                    u.retrageCerereInAsteptare(c);
                    u.adaugaCerereFinalizata(c);
                    try {
                        java.io.FileWriter fileWriter = new java.io.FileWriter(numeFisier, true);
                        BufferedWriter bw = new BufferedWriter(fileWriter);
                        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss", Locale.ENGLISH);
                        bw.write(formatter.format(c.getDate()) + " - " + c.getNumeSolicitor());
                        bw.newLine();
                        bw.close();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                }
            }
        }
    }
}

