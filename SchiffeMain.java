import java.util.Scanner;

public class SchiffeMain {
    //Initiialisierung Variablen und Arrays
    private String[][] spielfeld1 = new String[5][5];
    private String[][] spielfeld2 = new String[5][5];
    private String[][] anzeigeFeld1 = new String[5][5];
    private String[][] anzeigeFeld2 = new String[5][5];
    int anzahlBoote;
    int uebrigeBooteSpieler1 = anzahlBoote; 
    int uebrigeBooteSpieler2 = anzahlBoote; 
    int trefferSpieler1 = 0;
    int trefferSpieler2 = 0;
    
    public static void main(String[] args) {
        SchiffeMain main = new SchiffeMain();

        main.run(); //alles in run ausgelagert

    }

    private void run() { //"Main-Methodex" 
        init();
        begruessung();
        // printAnzeigeFelder();
        bootePlatzieren();
        printAnzeigeFelder();
        bombenPlatzieren();
    }

    private void init() { //Befüllen der Arrays für die Spielfelder in init Klasse ausgelagert
        anzeigeFelderInit();
        spielFelderInit();
    }

    // Spielfeld
    private void spielFelderInit() { //Aufabu der Spielfelder -- Befüllen
        spielfeldInit1();
        spielfeldInit2();
    }

    private void spielfeldInit1() { //Befüllen eines Einzelnen Spielfeldes
        for (int i = 0; i < spielfeld1.length; i++) {
            for (int j = 0; j < spielfeld1[i].length; j++) {
                spielfeld1[i][j] = "0";
            }
        }
    }

    private void spielfeldInit2() {
        for (int i = 0; i < spielfeld2.length; i++) {
            for (int j = 0; j < spielfeld2[i].length; j++) {
                spielfeld2[i][j] = "0";
            }
        }
    }

    private void printSpielfelder() { //Anzeigen der Spielfelder nur notwendig wärend der Entwicklung
        printSpielfeld1(spielfeld1);
        printSpielfeld2(spielfeld2);
    }

    private void printSpielfeld1(String[][] spielfeld1) {
        System.out.println("Spieler 1:");
        for (String[] row : spielfeld1) {
            for (String cell : row) {
                if (cell != "0") {
                    System.out.print("\u001B[31m" + cell + " \u001B[0m");
                } else {
                    System.out.print(cell + " ");
                }
            }
            System.out.println();
        }
        System.out.println("---------");
    }

    private void printSpielfeld2(String[][] spielfeld2) {
        for (String[] row : spielfeld2) {
            for (String cell : row) {
                if (cell != "0") {
                    System.out.print("\u001B[31m" + cell + " \u001B[0m");
                } else {
                    System.out.print(cell + " ");
                }
            }
            System.out.println();
        }
        System.out.println("Spieler 2:");
        System.out.println();
    }
    // Spielfeld

    //Begruessung 
    //Begruessung 
private void begruessung() {
    Scanner scan = new Scanner(System.in);
    System.out.println("  -- Schiffeversenken -- ");
    System.out.println("Mit wie vielen Schiffen wollt ihr spielen: ");
    boolean on = true; 
    while (true) {
        anzahlBoote = scan.nextInt();
        if (anzahlBoote > 25) {
            System.out.println("So viele Boote passen nicht auf das Spielfeld. Maximale Anzahl: 25\nVersuche es erneut! ");
        } else {
            // Hier die Instanzvariablen für die übrigen Boote initialisieren
            uebrigeBooteSpieler1 = anzahlBoote;
            uebrigeBooteSpieler2 = anzahlBoote;
            break; 
        }
    }
}
    //Begruessung 

    // Anzeige Feld  -- Funktion gleich den Spielfeldern
    private void anzeigeFelderInit() {
        anzeigeFeldInit1();
        anzeigeFeldInit2();
    }

    private void anzeigeFeldInit1() {
        for (int i = 0; i < anzeigeFeld1.length; i++) {
            for (int j = 0; j < anzeigeFeld1[i].length; j++) {
                anzeigeFeld1[i][j] = "0";
            }
        }
    }

    private void anzeigeFeldInit2() {
        for (int i = 0; i < anzeigeFeld2.length; i++) {
            for (int j = 0; j < anzeigeFeld2[i].length; j++) {
                anzeigeFeld2[i][j] = "0";
            }
        }
    }

    private void printAnzeigeFelder() {
        printAnzeigeFeld1(anzeigeFeld1);
        printAnzeigeFeld2(anzeigeFeld2);
    }

    private void printAnzeigeFeld1(String[][] anzeigeFeld1) {
        System.out.println("Spieler 1:\t Übrige Boote: " + uebrigeBooteSpieler1 + "\tTreffer: " + trefferSpieler1) ;
        for (String[] row : anzeigeFeld1) {
            for (String cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
        System.out.println("---------");
    }

    private void printAnzeigeFeld2(String[][] anzigeFeld2) {
        for (String[] row : anzeigeFeld2) {
            for (String cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
        System.out.println("Spieler 2:\t Übrige Boote: " + uebrigeBooteSpieler2  + "\tTreffer: " + trefferSpieler2);
    }
    // Annzeige Feld

    // Boote platzieren
    private void bootePlatzieren() { //Auslagern von 2 Methoden zu einer
        booteSpieler1();
        booteSpieler2();
    }

    private void booteSpieler1() {//Platzieren der Botte von Spieler 1 
        Scanner scan = new Scanner(System.in);
        int boote = 1;
        while (boote < anzahlBoote + 1) {
            System.out.println("Spieler 1: Boot " + boote + " platzieren (Reihe / Enter / Spalte): ");
            int row = scan.nextInt();
            int col = scan.nextInt();

            if (positionVergebenSpieler1(row, col)) {
                spielfeld1[row][col] = "B"; // Markierung der Position mit "B" für Boot im Spielfeld
                boote++;
            } else {
                System.out.println("Ungültige Position. Bitte wähle andere Koordinaten.");
            }
        }
    }

    private void booteSpieler2() {//Platzieren der Botte von Spieler 2 
        Scanner scan = new Scanner(System.in);
        int boote = 1;
        while (boote < anzahlBoote + 1) {
            System.out.println("Spieler 2: Boot " + boote + " platzieren (Reihe / Enter /Spalte): ");
            int row = scan.nextInt();
            int col = scan.nextInt();

            if (positionVergebenSpieler2(row, col)) {
                spielfeld2[row][col] = "B"; // Markierung der Position mit "B" für Boot im Spielfeld
                boote++;
            } else {
                System.out.println("Ungültige Position. Bitte wähle andere Koordinaten.");
            }
        }
    }

    private boolean positionVergebenSpieler1(int row, int col) { //Überprüfung ob eine gültige Position Verwende weird
        return row >= 0 && row < spielfeld1.length && col >= 0 && col < spielfeld1[0].length
                && spielfeld1[row][col].equals("0");
    }

    private boolean positionVergebenSpieler2(int row, int col) { //Überprüfung ob eine gültige Position Verwende weird
        return row >= 0 && row < spielfeld2.length && col >= 0 && col < spielfeld2[0].length
                && spielfeld2[row][col].equals("0");
    }
    // Boote platzieren

    // Bombe Platzieren
    private void bombenPlatzieren() {//Bomben platziern bie ein Spieler gewonnen hat
        int gefundenSpieler1 = 0;
        int gefundenSpieler2 = 0;
        boolean ende = false;
        Scanner scan = new Scanner(System.in);
        do {
            System.out.println();

            //Spieler 2 Bombe platzieren 
            System.out.println("Bombe Spieler 1: ");
            int rowSpieler1 = scan.nextInt();
            int colSpieler1 = scan.nextInt();
            if (bombeAufSpielfeld(rowSpieler1, colSpieler1)) {
                if (bombeTrifftBootSpieler1(rowSpieler1, colSpieler1)) {
                    anzeigeFeld2[rowSpieler1][colSpieler1] = "\u001B[32mX\u001B[0m";
                    uebrigeBooteSpieler2 = uebrigeBooteSpieler2-1;
                    trefferSpieler1++; 
                    printAnzeigeFelder();
                    gefundenSpieler1++;
                    if (gefundenSpieler1 == anzahlBoote) {
                        printAnzeigeFelder();
                        System.out.println("\u001B[32mSpieler 1 hat gewonnen!\u001B[0m");
                        break;
                    }
                } else {
                    anzeigeFeld2[rowSpieler1][colSpieler1] = "\u001B[31m*\u001B[0m";
                    printAnzeigeFelder();
                }
            }
            else {
                System.out.println("Ungültige Position. Bitte wähle beim nächsten mal eine andere Koordinaten.");
            }
            
            //Spieler 2 Bombe platzieren 
            System.out.println();
            System.out.println("Bombe Spieler 2: ");
            int rowSpieler2 = scan.nextInt();
            int colSpieler2 = scan.nextInt();
            if (bombeAufSpielfeld(rowSpieler2, colSpieler2)) {
                if (bombeTrifftBootSpieler2(rowSpieler2, colSpieler2)) {
                    anzeigeFeld1[rowSpieler2][colSpieler2] = "\u001B[32mX\u001B[0m";
                    uebrigeBooteSpieler1 = uebrigeBooteSpieler1-1;
                    trefferSpieler2++;
                    printAnzeigeFelder();
                    gefundenSpieler2++;
                    if (gefundenSpieler2 == anzahlBoote) {
                        printAnzeigeFelder();
                        System.out.println("\u001B[32mSpieler 1 hat gewonnen!\u001B[0m\"");
                        break;
                    }
                } else {
                    anzeigeFeld1[rowSpieler2][colSpieler2] = "\u001B[31m*\u001B[0m";
                    printAnzeigeFelder();
                }
            }
            else {
                System.out.println("Ungültige Position. Bitte wähle beim nächsten mal eine andere Koordinaten.");
            }

        } while (!ende);
        scan.close();
    }

    private boolean bombeTrifftBootSpieler1(int row, int col) {
        if (row >= 0 && row < spielfeld1.length && col >= 0 && col < spielfeld1[0].length
                && spielfeld1[row][col].equals("B")) {
            spielfeld1[row][col] = "\u001B[32mX\u001B[0m"; // Markiere getroffenes Boot
            // uebrigeBooteSpieler1--;
            return true;
        }
        return false;
    }
    
    private boolean bombeTrifftBootSpieler2(int row, int col) {
        if (row >= 0 && row < spielfeld2.length && col >= 0 && col < spielfeld2[0].length
                && spielfeld2[row][col].equals("B")) {
            spielfeld2[row][col] = "\u001B[32mX\u001B[0m"; // Markiere getroffenes Boot
            // uebrigeBooteSpieler2--;
            return true;
        }
        return false;
    }
    
    private boolean bombeAufSpielfeld(int row, int col) { //Überprüfen, ob Bombe auf Spielfeld
        return row >= 0 && row < spielfeld1.length && col >= 0 && col < spielfeld1[0].length;
    }
    // Bombe Platzieren
}