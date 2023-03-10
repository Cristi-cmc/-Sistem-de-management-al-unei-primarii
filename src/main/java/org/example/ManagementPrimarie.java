package org.example;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.PriorityQueue;

public class ManagementPrimarie {

    static String caleInput = "src/main/resources/input/";
    static String caleOutput = "src/main/resources/output/";
    private Birou<Angajat> birouAngajati;
    private Birou<Pensionar> birouPensionari;
    private Birou<Persoana> birouPersoane;
    private Birou<EntitateJuridica> birouEntitatiJuridice;
    private Birou<Elev> birouElevi;

    private HashMap<String, EvidentaTipUtilizatori> EvidentaUtilizatori;


    public static String getCaleInput() {
        return caleInput;
    }

    public static String getCaleOutput() {
        return caleOutput;
    }

    public Birou<Angajat> getBirouAngajati() {
        return birouAngajati;
    }

    public void setBirouAngajati(Birou<Angajat> birouAngajati) {
        this.birouAngajati = birouAngajati;
    }

    public Birou<Pensionar> getBirouPensionari() {
        return birouPensionari;
    }

    public void setBirouPensionari(Birou<Pensionar> birouPensionari) {
        this.birouPensionari = birouPensionari;
    }

    public Birou<Persoana> getBirouPersoane() {
        return birouPersoane;
    }

    public void setBirouPersoane(Birou<Persoana> birouPersoane) {
        this.birouPersoane = birouPersoane;
    }

    public Birou<EntitateJuridica> getBirouEntitatiJuridice() {
        return birouEntitatiJuridice;
    }

    public void setBirouEntitatiJuridice(Birou<EntitateJuridica> birouEntitatiJuridice) {
        this.birouEntitatiJuridice = birouEntitatiJuridice;
    }

    public Birou<Elev> getBirouElevi() {
        return birouElevi;
    }

    public void setBirouElevi(Birou<Elev> birouElevi) {
        this.birouElevi = birouElevi;
    }

    public ManagementPrimarie() {
        birouAngajati = new Birou<>();
        birouPensionari = new Birou<>();
        birouPersoane = new Birou<>();
        birouEntitatiJuridice = new Birou<>();
        birouElevi = new Birou<>();
        setEvidentaUtilizatori(new HashMap<>());
    }

    public void adaugaUtilizatorInBirou(ManagementPrimarie Primarie, String[] elemente) {

        String nume = elemente[2];
        EvidentaTipUtilizatori.TipUtilizator tip = EvidentaTipUtilizatori.convertesteStringInTip(elemente[1]);
        EvidentaTipUtilizatori util = new EvidentaTipUtilizatori(nume, tip);
        getEvidentaUtilizatori().put(nume, util);

        switch (elemente[1]) {
            case ("angajat"):
                Angajat angajat = new Angajat(nume, elemente[3]);
                Primarie.getBirouAngajati().adaugaUtilizator(angajat);
                break;
            case ("pensionar"):
                Pensionar pensionar = new Pensionar(nume);
                Primarie.getBirouPensionari().adaugaUtilizator(pensionar);
                break;
            case ("persoana"):
                Persoana persoana = new Persoana(nume);
                Primarie.getBirouPersoane().adaugaUtilizator(persoana);
                break;
            case ("entitate juridica"):
                EntitateJuridica entitateJuridica = new EntitateJuridica(nume, elemente[3]);
                Primarie.getBirouEntitatiJuridice().adaugaUtilizator(entitateJuridica);
                break;
            case ("elev"):
                Elev elev = new Elev(nume, elemente[3]);
                Primarie.getBirouElevi().adaugaUtilizator(elev);
                break;
        }
    }

    public int BirouUtilizator(String nume) {
        // cauta in birouri utilizatorul si returneaza un numar pentru tipul biroului
        EvidentaTipUtilizatori util = getEvidentaUtilizatori().get(nume);
        switch (util.getTip()) {
            case ANGAJAT:
                return 1;
            case PENSIONAR:
                return 2;
            case PERSOANA:
                return 3;
            case ENTITATE_JURIDICA:
                return 4;
            case ELEV:
                return 5;
            default:
                return 0;
        }
    }

    public <T extends Utilizator> void scrieCereriInAsteptare(String numeFisier, T utilizator) throws IOException, Utilizator.TipCerereInvalid {

        try {
            java.io.FileWriter fileWriter = new java.io.FileWriter(numeFisier, true);
            BufferedWriter bw = new BufferedWriter(fileWriter);
            PriorityQueue<Cerere> cereri = new PriorityQueue<>(utilizator.cereriInAsteptare);
            bw.write(utilizator.nume + " - cereri in asteptare:");
            bw.newLine();
            SimpleDateFormat print = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss", Locale.ENGLISH);
            while (!cereri.isEmpty()) {
                Cerere cerere = cereri.poll();
                Cerere.TipCerere tip = cerere.getTip();
                String apel = utilizator.scrieCerere(tip);
                String output = print.format(cerere.getDate());
                bw.write(output + " - " + apel);
                bw.newLine();
            }
            bw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public <T extends Utilizator> void scrieCereriFinalizate(String numeFisier, T utilizator) throws IOException, Utilizator.TipCerereInvalid {

        try {
            java.io.FileWriter fileWriter = new java.io.FileWriter(numeFisier, true);
            BufferedWriter bw = new BufferedWriter(fileWriter);
            PriorityQueue<Cerere> cereri = new PriorityQueue<>(utilizator.cereriFinalizate);
            bw.write(utilizator.nume + " - cereri in finalizate:");
            bw.newLine();
            SimpleDateFormat print = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss", Locale.ENGLISH);
            while (!cereri.isEmpty()) {
                Cerere cerere = cereri.poll();
                Cerere.TipCerere tip = cerere.getTip();
                String apel = utilizator.scrieCerere(tip);
                String output = print.format(cerere.getDate());
                bw.write(output + " - " + apel);
                bw.newLine();
            }
            bw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void scrieEroare(String fileName, String eroare) {
        try {
            java.io.FileWriter fileWriter = new java.io.FileWriter(fileName, true);
            BufferedWriter bw = new BufferedWriter(fileWriter);
            bw.write(eroare);
            bw.newLine();
            bw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {

        String Input = getCaleInput() + args[0];
        String Output = getCaleOutput() + args[0];
        ManagementPrimarie Primarie = new ManagementPrimarie();

        // citeste linie cu linie din fisierul de input si prelucreaza informatiile
        try {

            BufferedReader br = new BufferedReader(new FileReader(Input));
            String line;

            while ((line = br.readLine()) != null) {

                String[] elemente = line.split("; ");

                if (elemente[0].equals("adauga_utilizator")) {
                    Primarie.adaugaUtilizatorInBirou(Primarie, elemente);
                }

                if (elemente[0].equals("cerere_noua")) {
                    String nume = elemente[1];
                    Cerere.TipCerere tipCerere = Cerere.convertesteStringInTip(elemente[2]);
                    int tipBirou = Primarie.BirouUtilizator(nume);

                    Cerere c = new Cerere(nume, tipCerere, elemente[3], Integer.parseInt(elemente[4]));
                    switch (tipBirou) {
                        case (1):
                            for (Angajat angajat : Primarie.getBirouAngajati().getUtilizatori()) {
                                if (angajat.nume.equals(nume)) {
                                    String apel = angajat.scrieCerere(tipCerere);
                                    if (apel.contains("nu poate inainta")) {
                                        Primarie.scrieEroare(Output, apel);
                                        break;
                                    } else {
                                        c.setDescriere(apel);
                                        angajat.cereriInAsteptare.add(c);
                                        Primarie.getBirouAngajati().adaugaCerereInBirou(c);
                                    }
                                }
                            }
                            break;
                        case (2):
                            for (Pensionar pensionar : Primarie.getBirouPensionari().getUtilizatori()) {
                                if (pensionar.nume.equals(nume)) {
                                    String apel = pensionar.scrieCerere(tipCerere);
                                    if (apel.contains("nu poate inainta")) {
                                        Primarie.scrieEroare(Output, apel);
                                        break;
                                    } else {
                                        c.setDescriere(apel);
                                        pensionar.cereriInAsteptare.add(c);
                                        Primarie.getBirouPensionari().adaugaCerereInBirou(c);
                                    }
                                }
                            }
                            break;
                        case (3):
                            for (Persoana persoana : Primarie.getBirouPersoane().getUtilizatori()) {
                                if (persoana.nume.equals(nume)) {
                                    String apel = persoana.scrieCerere(tipCerere);
                                    if (apel.contains("nu poate inainta")) {
                                        Primarie.scrieEroare(Output, apel);
                                        break;
                                    } else {
                                        c.setDescriere(apel);
                                        persoana.cereriInAsteptare.add(c);
                                        Primarie.getBirouPersoane().adaugaCerereInBirou(c);
                                    }
                                }
                            }
                            break;
                        case (4):
                            for (EntitateJuridica entitateJuridica : Primarie.getBirouEntitatiJuridice().getUtilizatori()) {
                                if (entitateJuridica.nume.equals(nume)) {
                                    String apel = entitateJuridica.scrieCerere(tipCerere);
                                    if (apel.contains("nu poate inainta")) {
                                        Primarie.scrieEroare(Output, apel);
                                        break;
                                    } else {
                                        c.setDescriere(apel);
                                        entitateJuridica.cereriInAsteptare.add(c);
                                        Primarie.getBirouEntitatiJuridice().adaugaCerereInBirou(c);
                                    }
                                }
                            }
                            break;
                        case (5):
                            for (Elev elev : Primarie.getBirouElevi().getUtilizatori()) {
                                if (elev.nume.equals(nume)) {
                                    String apel = elev.scrieCerere(tipCerere);
                                    if (apel.contains("nu poate inainta")) {
                                        Primarie.scrieEroare(Output, apel);
                                        break;
                                    } else {
                                        c.setDescriere(apel);
                                        elev.cereriInAsteptare.add(c);
                                        Primarie.getBirouElevi().adaugaCerereInBirou(c);
                                    }
                                }
                            }
                            break;
                        case (0):
                            System.out.println("Nu exista utilizatorul");
                            break;
                    }

                }

                if (elemente[0].equals("afiseaza_cereri_in_asteptare")) {
                    String nume = elemente[1];
                    int tipBirou = Primarie.BirouUtilizator(nume);
                    switch (tipBirou) {
                        case (1):
                            for (Angajat angajat : Primarie.getBirouAngajati().getUtilizatori()) {
                                if (angajat.nume.equals(nume)) {
                                    Primarie.scrieCereriInAsteptare(Output, angajat);
                                }
                            }
                            break;
                        case (2):
                            for (Pensionar pensionar : Primarie.getBirouPensionari().getUtilizatori()) {
                                if (pensionar.nume.equals(nume)) {
                                    Primarie.scrieCereriInAsteptare(Output, pensionar);
                                }
                            }
                            break;
                        case (3):
                            for (Persoana persoana : Primarie.getBirouPersoane().getUtilizatori()) {
                                if (persoana.nume.equals(nume)) {
                                    Primarie.scrieCereriInAsteptare(Output, persoana);
                                }
                            }
                            break;
                        case (4):
                            for (EntitateJuridica entitateJuridica : Primarie.getBirouEntitatiJuridice().getUtilizatori()) {
                                if (entitateJuridica.nume.equals(nume)) {
                                    Primarie.scrieCereriInAsteptare(Output, entitateJuridica);
                                }
                            }
                            break;
                        case (5):
                            for (Elev elev : Primarie.getBirouElevi().getUtilizatori()) {
                                if (elev.nume.equals(nume)) {
                                    Primarie.scrieCereriInAsteptare(Output, elev);
                                }
                            }
                            break;
                    }
                }

                if (elemente[0].equals("retrage_cerere")) {
                    String nume = elemente[1];
                    int tipBirou = Primarie.BirouUtilizator(nume);
                    switch (tipBirou) {
                        case (1):
                            for (Angajat angajat : Primarie.getBirouAngajati().getUtilizatori()) {
                                if (angajat.nume.equals(nume)) {
                                    angajat.retrageCerere(elemente[2]);
                                    Primarie.getBirouAngajati().retrageCerereDinBirou(elemente[2]);
                                }
                            }
                            break;
                        case (2):
                            for (Pensionar pensionar : Primarie.getBirouPensionari().getUtilizatori()) {
                                if (pensionar.nume.equals(nume)) {
                                    pensionar.retrageCerere(elemente[2]);
                                    Primarie.getBirouPensionari().retrageCerereDinBirou(elemente[2]);
                                }
                            }
                            break;
                        case (3):
                            for (Persoana persoana : Primarie.getBirouPersoane().getUtilizatori()) {
                                if (persoana.nume.equals(nume)) {
                                    persoana.retrageCerere(elemente[2]);
                                    Primarie.getBirouPersoane().retrageCerereDinBirou(elemente[2]);
                                }
                            }
                            break;
                        case (4):
                            for (EntitateJuridica entitateJuridica : Primarie.getBirouEntitatiJuridice().getUtilizatori()) {
                                if (entitateJuridica.nume.equals(nume)) {
                                    entitateJuridica.retrageCerere(elemente[2]);
                                    Primarie.getBirouEntitatiJuridice().retrageCerereDinBirou(elemente[2]);
                                }
                            }
                            break;
                        case (5):
                            for (Elev elev : Primarie.getBirouElevi().getUtilizatori()) {
                                if (elev.nume.equals(nume)) {
                                    elev.retrageCerere(elemente[2]);
                                    Primarie.getBirouElevi().retrageCerereDinBirou(elemente[2]);
                                }
                            }
                            break;
                    }
                }

                if (elemente[0].equals("afiseaza_cereri")) {
                    String tipBirou = elemente[1];
                    int tip;
                    switch (tipBirou) {
                        case ("angajat"):
                            tip = 1;
                            Primarie.getBirouAngajati().scrieCereriDinBirouInAsteptare(Output, Primarie.getBirouAngajati(), tip);
                            break;
                        case ("pensionar"):
                            tip = 2;
                            Primarie.getBirouPensionari().scrieCereriDinBirouInAsteptare(Output, Primarie.getBirouPensionari(), tip);
                            break;
                        case ("persoana"):
                            tip = 3;
                            Primarie.getBirouPersoane().scrieCereriDinBirouInAsteptare(Output, Primarie.getBirouPersoane(), tip);
                            break;
                        case ("entitate juridica"):
                            tip = 4;
                            Primarie.getBirouEntitatiJuridice().scrieCereriDinBirouInAsteptare(Output, Primarie.getBirouEntitatiJuridice(), tip);
                            break;
                        case ("elev"):
                            tip = 5;
                            Primarie.getBirouElevi().scrieCereriDinBirouInAsteptare(Output, Primarie.getBirouElevi(), tip);
                            break;
                    }
                }

                if (elemente[0].equals("adauga_functionar")) {
                    String birou = elemente[1];
                    String numeFunctionar = elemente[2];
                    switch (birou) {
                        case ("angajat"):
                            FunctionarPublic<Angajat> functionar = new FunctionarPublic<>(numeFunctionar);
                            Primarie.getBirouAngajati().adaugaFunctionar(functionar);
                            break;
                        case ("pensionar"):
                            FunctionarPublic<Pensionar> functionar1 = new FunctionarPublic<>(numeFunctionar);
                            Primarie.getBirouPensionari().adaugaFunctionar(functionar1);
                            break;
                        case ("persoana"):
                            FunctionarPublic<Persoana> functionar2 = new FunctionarPublic<>(numeFunctionar);
                            Primarie.getBirouPersoane().adaugaFunctionar(functionar2);
                            break;
                        case ("entitate juridica"):
                            FunctionarPublic<EntitateJuridica> functionar3 = new FunctionarPublic<>(numeFunctionar);
                            Primarie.getBirouEntitatiJuridice().adaugaFunctionar(functionar3);
                            break;
                        case ("elev"):
                            FunctionarPublic<Elev> functionar4 = new FunctionarPublic<>(numeFunctionar);
                            Primarie.getBirouElevi().adaugaFunctionar(functionar4);
                            break;
                    }
                }

                if (elemente[0].equals("rezolva_cerere")) {
                    String numeFunctionar = elemente[2];
                    String fisier = getCaleOutput() + "functionar_" + numeFunctionar + ".txt";

                    switch (elemente[1]) {
                        case ("angajat"):
                            FunctionarPublic<Angajat> functionar = Primarie.getBirouAngajati().cautaFunctionar(numeFunctionar);
                            functionar.solutioneazaCerere(Primarie.getBirouAngajati(), fisier);
                            break;
                        case ("pensionar"):
                            FunctionarPublic<Pensionar> functionar1 = Primarie.getBirouPensionari().cautaFunctionar(numeFunctionar);
                            functionar1.solutioneazaCerere(Primarie.getBirouPensionari(), fisier);
                            break;
                        case ("persoana"):
                            FunctionarPublic<Persoana> functionar2 = Primarie.getBirouPersoane().cautaFunctionar(numeFunctionar);
                            functionar2.solutioneazaCerere(Primarie.getBirouPersoane(), fisier);
                            break;
                        case ("entitate juridica"):
                            FunctionarPublic<EntitateJuridica> functionar3 = Primarie.getBirouEntitatiJuridice().cautaFunctionar(numeFunctionar);
                            functionar3.solutioneazaCerere(Primarie.getBirouEntitatiJuridice(), fisier);
                            break;
                        case ("elev"):
                            FunctionarPublic<Elev> functionar4 = Primarie.getBirouElevi().cautaFunctionar(numeFunctionar);
                            functionar4.solutioneazaCerere(Primarie.getBirouElevi(), fisier);
                            break;
                    }
                }

                if (elemente[0].equals("afiseaza_cereri_finalizate")) {
                    String nume = elemente[1];
                    int tip = Primarie.BirouUtilizator(nume);
                    switch (tip) {
                        case (1):
                            for (Angajat angajat : Primarie.getBirouAngajati().getUtilizatori()) {
                                if (angajat.nume.equals(nume)) {
                                    Primarie.scrieCereriFinalizate(Output, angajat);
                                    break;
                                }
                            }
                            break;
                        case (2):
                            for (Pensionar pensionar : Primarie.getBirouPensionari().getUtilizatori()) {
                                if (pensionar.nume.equals(nume)) {
                                    Primarie.scrieCereriFinalizate(Output, pensionar);
                                    break;
                                }
                            }
                            break;
                        case (3):
                            for (Persoana persoana : Primarie.getBirouPersoane().getUtilizatori()) {
                                if (persoana.nume.equals(nume)) {
                                    Primarie.scrieCereriFinalizate(Output, persoana);
                                    break;
                                }
                            }
                            break;
                        case (4):
                            for (EntitateJuridica entitate : Primarie.getBirouEntitatiJuridice().getUtilizatori()) {
                                if (entitate.nume.equals(nume)) {
                                    Primarie.scrieCereriFinalizate(Output, entitate);
                                    break;
                                }
                            }
                            break;
                        case (5):
                            for (Elev elev : Primarie.getBirouElevi().getUtilizatori()) {
                                if (elev.nume.equals(nume)) {
                                    Primarie.scrieCereriFinalizate(Output, elev);
                                    break;
                                }
                            }
                            break;
                    }
                }
            }
        } catch (IOException | Utilizator.TipCerereInvalid e) {
            throw new RuntimeException(e);
        }
    }

    public HashMap<String, EvidentaTipUtilizatori> getEvidentaUtilizatori() {
        return EvidentaUtilizatori;
    }

    public void setEvidentaUtilizatori(HashMap<String, EvidentaTipUtilizatori> evidentaUtilizatori) {
        EvidentaUtilizatori = evidentaUtilizatori;
    }
}