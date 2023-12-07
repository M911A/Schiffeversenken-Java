import java.util.Scanner;

public class SchiffeMain {
    private String[][] spielfeld1 = new String[5][5];
    private String[][] spielfeld2 = new String[5][5];
    private String[][] anzeigeFeld1 = new String[5][5];
    private String[][] anzeigeFeld2 = new String[5][5];
    private int[] uebrig = new int[2];
    int anzahlBoote = 2;

    public static void main(String[] args) {
        SchiffeMain main = new SchiffeMain();

        main.run();

    }

    private void run() {
        init();
        printAnzeigeFelder();
        bootePlatzieren();
        printAnzeigeFelder();
        bombenPlatzieren();
    }

    private void init() {
        anzeigeFelderInit();
        spielFelderInit();
        uebrig[0] = anzahlBoote-1;
        uebrig[1] = anzahlBoote-1;
    }

    // Spielfeld
    private void spielFelderInit() {
        spielfeldInit1();
        spielfeldInit2();
    }

    private void spielfeldInit1() {
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

    // private void printSpielfelder() {
    //     printSpielfeld1(spielfeld1);
    //     printSpielfeld2(spielfeld2);
    // }

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
    }
    // Spielfeld

    // Anzeige Feld
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
        System.out.println("Spieler 1:\t Übrige Boote: " + uebrig[0]);
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
        System.out.println("Spieler 2:\t Übrige Boote: " + uebrig[1]);
    }
    // Annzeige Feld

    // Boote platzieren
    private void bootePlatzieren() {
        booteSpieler1();
        booteSpieler2();
    }

    private void booteSpieler1() {
        Scanner scan = new Scanner(System.in);
        int boote = 1;
        while (boote < anzahlBoote + 1) {
            System.out.println("Boot " + boote + " platzieren (Reihe Spalte): ");
            int row = scan.nextInt();
            int col = scan.nextInt();

            if (positionVergebenSpieler1(row, col)) {
                spielfeld1[row][col] = "B"; // Markiere die Position mit "B" für Boot
                boote++;
            } else {
                System.out.println("Ungültige Position. Bitte wähle andere Koordinaten.");
            }
        }
    }

    private void booteSpieler2() {
        Scanner scan = new Scanner(System.in);
        int boote = 1;
        while (boote < anzahlBoote + 1) {
            System.out.println("Boot " + boote + " platzieren (Reihe Spalte): ");
            int row = scan.nextInt();
            int col = scan.nextInt();

            if (positionVergebenSpieler2(row, col)) {
                spielfeld2[row][col] = "B"; // Markiere die Position mit "B" für Boot
                boote++;
            } else {
                System.out.println("Ungültige Position. Bitte wähle andere Koordinaten.");
            }
        }
    }

    private boolean positionVergebenSpieler1(int row, int col) {
        return row >= 0 && row < spielfeld1.length && col >= 0 && col < spielfeld1[0].length
                && spielfeld1[row][col].equals("0");
    }

    private boolean positionVergebenSpieler2(int row, int col) {
        return row >= 0 && row < spielfeld2.length && col >= 0 && col < spielfeld2[0].length
                && spielfeld2[row][col].equals("0");
    }
    // Boote platzieren

    // Bombe Platzieren
    private void bombenPlatzieren() {
        int gefundenSpieler1 = 0;
        int gefundenSpieler2 = 0;
        boolean ende = false;
        Scanner scan = new Scanner(System.in);
        do {
            System.out.println("Bombe Spieler 1: ");
            int rowSpieler1 = scan.nextInt();
            int colSpieler1 = scan.nextInt();
            if (bombeTrifftBootSpieler1(rowSpieler1, colSpieler1)) {
                anzeigeFeld2[rowSpieler1][colSpieler1] = "\u001B[32mX\u001B[0m";
                printAnzeigeFelder();
                uebrig[0]--;
                gefundenSpieler1++;
                if (gefundenSpieler1 == anzahlBoote) {
                    printAnzeigeFelder();
                    System.out.println("Spieler 1 hat gewonnen!");
                    break;
                }
            } else {
                anzeigeFeld2[rowSpieler1][colSpieler1] = "\u001B[31m*\u001B[0m";
                printAnzeigeFelder();
            }

            System.out.println("Bombe Spieler 2: ");
            int rowSpieler2 = scan.nextInt();
            int colSpieler2 = scan.nextInt();
            if (bombeTrifftBootSpieler2(rowSpieler2, colSpieler2)) {
                anzeigeFeld1[rowSpieler2][colSpieler2] = "\u001B[32mX\u001B[0m";
                printAnzeigeFelder();
                uebrig[1]--;
                gefundenSpieler2++;
                if (gefundenSpieler2 == anzahlBoote) {
                    printAnzeigeFelder();
                    System.out.println("Spieler 2 hat gewonnen!");
                    break;
                }
            } else {
                anzeigeFeld1[rowSpieler2][colSpieler2] = "\u001B[31m*\u001B[0m";
                printAnzeigeFelder();
            }

        } while (!ende);
        scan.close(); // Schließe den Scanner, wenn er nicht mehr benötigt wird
    }

    private boolean bombeTrifftBootSpieler1(int row, int col) {
        return row >= 0 && row < spielfeld1.length && col >= 0 && col < spielfeld1[0].length
                && spielfeld1[row][col].equals("B"); // Überprüfe, ob die Position im Spielfeld ein Boot ist
    }

    private boolean bombeTrifftBootSpieler2(int row, int col) {
        return row >= 0 && row < spielfeld2.length && col >= 0 && col < spielfeld2[0].length
                && spielfeld2[row][col].equals("B"); // Überprüfe, ob die Position im Spielfeld ein Boot ist
    }
    // Bombe Platzieren
}


//TODO: übrige Boote geht noch nicht 