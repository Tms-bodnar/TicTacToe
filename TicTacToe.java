/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

import java.util.Scanner;

/**
 *
 * @author user
 */
public class TicTacToe {

    public static String nevBekero(String szoveg) {
        Scanner sc = new Scanner(System.in);
        System.out.println(szoveg);
        String nev = sc.nextLine();
        return nev;
    }

    public static String lepesBekero(String nev) {
        Scanner sc = new Scanner(System.in);
        System.out.println(nev + ", lépj egy mezőre!");
        System.out.println("Előszőr a sort, majd az oszlopot: (pl:A2, b3, stb...)");
        String beirt = sc.nextLine();
        if (Character.isLowerCase(beirt.charAt(0))) {
            String beirtNagy = beirt.toUpperCase();
            beirt = beirtNagy;
        }
        return beirt;
    }

    public static int[] lepesAtalakito(String beirt) {

        int[] lepes = new int[2];
        char[] fuggBetu = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L'};
        lepes[1] = Character.getNumericValue(beirt.charAt(1));
        for (int i = 0; i < fuggBetu.length; i++) {
            if (beirt.charAt(0) == fuggBetu[i]) {
                lepes[0] = (i);
            }
        }
        return lepes;
    }

    public static int lepesSor(int[] lepes) {
        int sor = lepes[0];
        return sor;
    }

    public static int lepesOszlop(int[] lepes) {
        int oszlop = lepes[1] - 1;
        return oszlop;
    }

    public static void tablaRajzolo(String[][] tabla) {

        String[][] matrix = tabla;
        String[] fuggBetu = {"A", "B", "C"};
//üres pálya felrajzolása:
//A cellák kitöltése
        boolean foglalt = false;
        boolean dontetlen = false;
        int foglaltMezo = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                if (matrix[i][j] == "O" || matrix[i][j] == "X") {
                    foglalt = true;
                    foglaltMezo++;
                }
                if (!foglalt) {
                    matrix[i][j] = " ";
                }
                if(foglaltMezo == (matrix.length*matrix.length)){
                    dontetlen = true;
                    }
            }
        }

//A tábla oszlopok számozása
        for (int i = 0; i < 1; i++) {
            for (int k = 0; k < matrix[i].length; k++) {
                if (k == 0) {
                    System.out.print(" ");
                }
                System.out.print("  " + (k + 1) + " ");
            }
            System.out.println();
        }
//A tábla első vízszintes sorai
        for (int i = 0; i < matrix.length; i++) {
            for (int k = 0; k < matrix[i].length; k++) {
                if (k == 0) {
                    System.out.print(" ");
                }
                System.out.print("+---");
            }
// A tábla sorok betűjelei   
            System.out.println("+");
            for (int k = 0; k < matrix[i].length; k++) {
                if (k == 0) {
                    System.out.print(fuggBetu[i]);
                }
                System.out.print("| " + matrix[i][k] + " ");
            }
            System.out.println("|");
        }
//A tábla legalsó sora
        for (int i = 0; i < 1; i++) {
            for (int k = 0; k < matrix[i].length; k++) {
                if (k == 0) {
                    System.out.print(" ");
                }
                System.out.print("+---");
            }
            System.out.print("+");
        }
        System.out.println();
        if (dontetlen){
            System.out.println("Az eredmény döntetlen!");
            System.exit(0);
        }
    }

    public static boolean nyertesVizsgalo(String[][] tabla, String jel) {
        boolean nyertes = false;
        String[][] matrix = tabla;
        for (int i = 0; i < matrix.length; i++) {
            int talalat = 0;
            for (int j = 0; j < matrix.length; j++) {
                if (matrix[i][j] == jel) {
                    talalat++;
                }
            }
            if (talalat == 3) {
                nyertes = true;
            }
        }
        for (int i = 0; i < matrix.length; i++) {
            int talalat = 0;
            for (int j = 0; j < matrix.length; j++) {
                if (matrix[j][i] == jel) {
                    talalat++;
                }
                if (talalat == 3) {
                    nyertes = true;
                }
            }
        }
        {  
        int talalat = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                if (i == j && matrix[i][j] == jel){
                        talalat++;
                    }
                if (talalat == 3) {
                    nyertes = true;
                }
            }
        }talalat = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                if ((i + j) == (matrix.length-1) && matrix[i][j] == jel){
                        talalat++;
                }
                if (talalat == 3) {
                    nyertes = true;
                }
            }
        }    
        }
        return nyertes;
    }

    public static void main(String[] args) {
        String jatekosO = nevBekero("Első Játékos, 'O'-val leszel, add meg a neved: ");
        String jatekosX = nevBekero("Második Játékos, 'X'-el leszel, add meg a nevedet:");
        String[][] tabla = new String[3][3];
        tablaRajzolo(tabla);
        int jatekosSorszam = 1;
        String jatekos = jatekosX;
        String jel = "";
        do {
            if (jatekosSorszam % 2 == 1) {
                jatekos = jatekosO;
            } else {
                jatekos = jatekosX;
            }
            jel = "X";
            String beirt = lepesBekero(jatekos);
            lepesAtalakito(beirt);
            int sor = lepesSor(lepesAtalakito(beirt));
            int oszlop = lepesOszlop(lepesAtalakito(beirt));
            if (tabla[sor][oszlop] != " ") {
                System.out.println("Hibás lépés!");
                beirt = lepesBekero(jatekos);
                lepesAtalakito(beirt);
                sor = lepesSor(lepesAtalakito(beirt));
                oszlop = lepesOszlop(lepesAtalakito(beirt));
                if (jatekosSorszam % 2 == 1) {
                    jel = "O";
                    tabla[sor][oszlop] = jel;
                    tablaRajzolo(tabla);
                } else {
                    tabla[sor][oszlop] = jel;
                    tablaRajzolo(tabla);
                }
            } else if (jatekosSorszam % 2 == 1) {
                jel = "O";
                tabla[sor][oszlop] = jel;
                tablaRajzolo(tabla);
            } else {
                tabla[sor][oszlop] = jel;
                tablaRajzolo(tabla);
            }
            jatekosSorszam++;

        } while (!nyertesVizsgalo(tabla, jel));
        if (nyertesVizsgalo(tabla, jel)) {
            System.out.println(jatekos + ", nyertél!");
        }

    }
}
