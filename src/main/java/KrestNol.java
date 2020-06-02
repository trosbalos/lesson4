
import java.util.Random;
import java.util.Scanner;

public class KrestNol {
    public static int SIZE = 5;
    public static int DOTS_TO_WIN = 4;
    public static final char DOT_EMPTY = '•';
    public static final char DOT_X = 'X';
    public static final char DOT_O = 'O';
    public static char[][] map;
    public static Scanner sc = new Scanner(System.in);
    public static Random rand = new Random();

    public static void main(String[] args) {
        initMap();
        printMap();
        while (true) {
            humanTurn();
            printMap();
            if (checkWin(DOT_X)) {
                System.out.println("Победил человек");
                break;
            }
            if (isMapFull()) {
                System.out.println("Ничья");
                break;
            }
            aiTurn();
            printMap();
            if (checkWin(DOT_O)) {
                System.out.println("Победил Искуственный Интеллект");
                break;
            }
            if (isMapFull()) {
                System.out.println("Ничья");
                break;
            }
        }
        System.out.println("Игра закончена");
    }

    public static boolean checkWin(char symb) {
        boolean chek1, chek2, chek3, chek4, chek5, chek6, chek7, chek8;
        chek1 = true;
        chek2 = true;
        chek3 = true;
        chek4 = true;
        chek5 = true;
        chek6 = true;
        chek7 = true;
        chek8 = true;

        for (int i = 0; i < 4; i++) {
            if (map[i][0] == symb && map[i][1] == symb && map[i][2] == symb && map[i][3] == symb) return true;
            if (map[i][1] == symb && map[i][2] == symb && map[i][3] == symb && map[i][4] == symb) return true;
            if (map[0][i] == symb && map[1][i] == symb && map[2][i] == symb && map[3][i] == symb) return true;
            if (map[1][i] == symb && map[2][i] == symb && map[3][i] == symb && map[4][i] == symb) return true;
            chek1 &= (map[i][i] == symb);
            chek8 &= (map[i + 1][i + 1] == symb);
            chek2 &= (map[4 - i][i] == symb);
            chek3 &= (map[i][4 - i] == symb);
            chek4 &= (map[i][i + 1] == symb);
            chek5 &= (map[i + 1][i] == symb);
            chek6 &= (map[3 - i][i] == symb);
            chek7 &= (map[i + 1][4 - i] == symb);


        }

        return (chek1 || chek2 || chek3 || chek4 || chek5 || chek6 || chek7 || chek8);

    }

    public static boolean isMapFull() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (map[i][j] == DOT_EMPTY) return false;
            }
        }
        return true;
    }

    public static void aiTurn() {
        int x, y;
        do {
            x = rand.nextInt(SIZE);
            y = rand.nextInt(SIZE);
        } while (!isCellValid(x, y));
        System.out.println("Компьютер походил в точку " + (x + 1) + " " + (y + 1));
        map[y][x] = DOT_O;
    }

    public static void humanTurn() {
        int x, y;
        do {
            System.out.println("Введите координаты в формате X Y");
            x = sc.nextInt();
            y = sc.nextInt();
        } while (!isCellValid(x, y)); // while(isCellValid(x, y) == false)
        map[y][x] = DOT_X;
    }

    public static boolean isCellValid(int x, int y) {
        if (x < 0 || x >= SIZE || y < 0 || y >= SIZE) return false;
        if (map[y][x] == DOT_EMPTY) return true;
        return false;
    }

    public static void initMap() {
        map = new char[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                map[i][j] = DOT_EMPTY;
            }
        }
    }

    public static void printMap() {
        for (int i = 0; i <= SIZE; i++) {
            System.out.print(i - 1 + " ");
        }
        System.out.println();
        for (int i = 0; i < SIZE; i++) {
            System.out.print((i) + " ");
            for (int j = 0; j < SIZE; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}

