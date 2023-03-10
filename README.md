# Tema 2 POO - Sistem de management al unei primarii
Cristea Marius-Cristian, Grupa 323CB  
In cadrul temei am implementat un sistem de management al unei primarii cu ajutorul a mai multor clase:  
- 'ManagementPrimarie' - clasa principala, care contine metodele principale ale programului
- 'Utilizator' - clasa (abstracta) de baza a utilizatorului cu cateva metode pe care ar trebui sa le aiba toti utlizatorii, indiferent de tipul lor
- 'Persoana', 'Pensionar', 'Angajat' 'EntitateJuridica', 'Elev' - clasele care extind clasa 'Utilizator'
- 'Cerere' - clasa care modeleaza o cerere
- 'FunctionarPublic' - clasa care modeleaza un functionar public 
- 'EvidentaTipUtilizatori' - o clasa ajutatoare pentru a tine evidenta in primarie a tuturor utilizatorilor
- 'Birou' - clasa care modeleaza un birou din primarie

Intrucat clasele care extind Utilizator au aceeasi functie, nu voi discuta despre ele in amanunt. Acestea implementeaza aceeasi metoda abstracta din clasa parinte Utilizator, anume scrieCerere - aceasta metoda returneaza un String ce reprezinta cererea utilizatorului scrisa sau mesajul de eroare in cazul in care cererea nu este valida.

# Clasa Utilizator
Clasa utilizator are ca atribute un String pentru numele utilizatorului si doua cozi de prioritate - una pentru cererile in asteptare ale utilizatorului si cealalta pentru cererile finalizate  
Aceasta contine cateva metode, precum:
- 'retrageCerere' - ce scoate din cozi o anumita cerere (in functie de timpul la care a fost submisa)
- 'retrageCerereInAsteptare' - ce scoate doar din coada de asteptare cererea(cererea este data ca parametru)
- 'adaugaCerereFinalizata' - ce adauga in coada de cereri finalizate cererea respectiva

# Clasa Birou
Clasa birou are ca atribute o lista(un ArrayList) de utilizatori, o lista de functionari si o coada de prioritate(PriorityQueue) pentru cererile in asteptare din birou.  
Aceasta  clasa este una generica, care accepta doar clase ce extind clasa Utilizator.
Aceasta contine cateva metode simple, precum:
- 'cautaFunctionar' - ce cauta un functionar in lista dupa nume
- 'retrageCerereDinBirou' - ce scoate din coada de asteptare o cerere pe baza timpului la care a fost submisa
- 'scrieCereriDinBirouInAsteptare' - ce scrie in fisierul de output cererile in asteptare din birou pentru un anumit utilizator(elev, pensionar etc)

# Clasa FunctionarPublic
Aceasta clasa are ca atribute un String pentru nume. De asemenea, este o clasa generica ca clasa Birou.  
Metoda principala a acestei clase este 'solutioneazaCerere'
- functionarul scoate din coada de asteptare o cerere si o adauga in coada de finalizate si scrie in fisierul sau de output cererea respectiva ca a fost solutionata

# Clasa EvidentaTipUtilizatori
Aceasta clasa a fost creata pentru a usura gasirea tipului de utilizator in primarie. Ea are ca atribute un String pentru nume si un element de tip Enum pentru tipul utilizatorului.

# Clasa Cerere
Aceasta este clasa care modeleaza o cerere. Are ca atribute un String pentru numele solicitorului, un String pentru descrierea cererii(in acest camp stochez enuntul cererii), un int pentru prioritate, un element de tip Date pentru a retine data la care a fost submisa cererea si un element de tip Enum pentru tipul cererii. Clasa mai are si un atribut care este formatul datei cererii(dd-MMM-yyyy HH:mm:SS)  
Clasa contine si doua clase interne - 'ComparatorCerere' si 'ComparatorCerereInAsteptare' ce implementeaza Comparator - pentru a putea ordona cererile in cozi dupa cum ne-am dorit  

# Clasa ManagementPrimarie
`Aceasta este clasa unde se intampla magia`
Clasa are mai multe atribute: birouri pentru fiecare tip de utilizatori, un HashMap unde tin evidenta de utilizatori si tipul lor, doua Stringuri ce tin calea de input si output.  
Principalele metode din clasa sunt:
- 'adaugaUtilizatorInBirou' - ce adauga un utilizator in biroul sau specific si in HashMap-ul de evidenta
- 'BirouUtilizator' - ce returneaza un int pentru biroul in care se afla utilizatorul  
1 pentru angajat, 2 pentru pensionar, 3 pentru persoana, 4 pentru entitate juridica, 5 pentru elev  
Aceasta metoda cauta in hashmap utilizatorul si verifica campul tip al acestuia
- 'scrieCereriInAsteptare' - ce scrie in fisierul de output cererile in asteptare pentru un anumit utilizator
- 'scrieCereriFinalizate' - ce scrie in fisierul de output cererile finalizate pentru un anumit utilizator
- 'scrieEroare' - ce scrie in fisierul de output mesajul de eroare pentru cand o cerere nu poate fi realizata

In metoda main se citesc datele din fisierul de input si se apeleaza metodele necesare pentru a simula comenzile primariei.  
Metoda este destul de lunga deoarece am fost nevoit sa folosesc switch case pentru fiecare tip de utilizator.  
Pentru a retine utilizatorii si functionarii am folost in mare parte ArrayListuri - singura exceptie fiind HashMapul pentru evidenta tipului de utilizator pentru fiecare utilizator.  
Am folosit un HashMap deoarece as fi fost nevoit sa parcurg prin fiecare lista de utilizator din birouri pentru a gasi utilizatorul respectiv, ceea ce ar fi fost ineficient.  
Pentru cereri am folosit PriorityQueue's - pentru a putea retine cererile in ordine cronologica si dupa prioritate si pentru a putea scoate cererile in ordinea dorita.
