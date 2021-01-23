import java.util.Scanner;
import java.util.Random;

public class Main {

    private static char[][] gameMap;
    public static int x = 0;
    public static int y = 0;
    public static Scanner sc = new Scanner(System.in);
    public static Random random = new Random();

    private static final char EMPTY_DOT = '•';
    private static final char X_DOT = 'X';
    private static final char O_DOT = 'O';

    public static int gameCount;

    public static void main(String[] args) {

        startGame();

    }

    private static void startGame() {

        initMap();
        paintMap();

        while (true) {
            if (readyToStep()) {
                userStep();
                paintMap();
                if (isWin('X')) {
                    System.out.println("YOU WIN!");
                    break;
                }
            } else {
                break;
            }

            if (readyToStep()) {
                try {
                    pcStep();
                } catch (InterruptedException e) {
                    System.out.println("Fucking error in the PC step");
                }

                paintMap();
                if (isWin('O')) {
                    System.out.println("YOU LOST!");
                    break;
                }
            } else {
                break;
            }
        }
    }

    private static void pcStep() throws InterruptedException {
        System.out.println("PC step. Please wait...");
        Thread.sleep(1000);
        boolean NoDanger = true;
        if (NoDanger) {
            for (int i = 0; i < gameMap.length; i++) { // горизонталь
                for (int j = 0; j < gameMap[i].length - 3; j++) {
                    if (gameMap[i][j] == 'O' && gameMap[i][j + 1] == 'O' && gameMap[i][j + 2] == 'O' && gameMap[i][j + 3] != 'O' && gameMap[i][j + 3] != 'X') {
                        gameMap[i][j + 3] = 'O';
                        NoDanger = false;
                        gameCount--;
                    }
                }
            }
        }
        if (NoDanger) {
            for (int i = 0; i < gameMap.length - 3; i++) { //вертикаль
                for (int j = 0; j < gameMap[i].length; j++) {
                    if (gameMap[i][j] == 'O' && gameMap[i + 1][j] == 'O' && gameMap[i + 2][j] == 'O' && gameMap[i + 3][j] != 'O' && gameMap[i + 3][j] != 'X') {
                        gameMap[i + 3][j] = 'O';
                        NoDanger = false;
                        gameCount--;
                    }
                }
            }
        }
        if (NoDanger) {
            for (int i = 0; i < gameMap.length - 3; i++) { //диагональ левый верхний - правый нижний
                for (int j = 0; j < gameMap[i].length - 3; j++) {
                    if (gameMap[i][j] == 'O' && gameMap[i + 1][j + 1] == 'O' && gameMap[i + 2][j + 2] == 'O' && gameMap[i + 3][j + 3] != 'O' && gameMap[i + 3][j + 3] != 'X') {
                        gameMap[i + 3][j + 3] = 'O';
                        NoDanger = false;
                        gameCount--;
                    }
                }
            }
        }
        if (NoDanger) {
            for (int i = 3; i < gameMap.length; i++) { //диагональ левый нижний - правый верхний
                for (int j = 0; j < gameMap[i].length - 3; j++) {
                    if (gameMap[i][j] == 'O' && gameMap[i - 1][j + 1] == 'O' && gameMap[i - 2][j + 2] == 'O' && gameMap[i - 3][j + 3] != 'O' && gameMap[i - 3][j + 3] != 'X') {
                        gameMap[i - 3][j + 3] = 'O';
                        NoDanger = false;
                        gameCount--;
                    }
                }
            }
        }
        if (NoDanger) {
            for (int i = 0; i < gameMap.length; i++) { // горизонталь реверс
                for (int j = 3; j < gameMap[i].length; j++) {
                    if (gameMap[i][j] == 'O' && gameMap[i][j - 1] == 'O' && gameMap[i][j - 2] == 'O' && gameMap[i][j - 3] != 'O' && gameMap[i][j - 3] != 'X') {
                        gameMap[i][j - 3] = 'O';
                        NoDanger = false;
                        gameCount--;
                    }
                }
            }
        }
        if (NoDanger) {
            for (int i = 3; i < gameMap.length; i++) { //вертикаль реверс
                for (int j = 0; j < gameMap[i].length; j++) {
                    if (gameMap[i][j] == 'O' && gameMap[i - 1][j] == 'O' && gameMap[i - 2][j] == 'O' && gameMap[i - 3][j] != 'O' && gameMap[i - 3][j] != 'X') {
                        gameMap[i - 3][j] = 'O';
                        NoDanger = false;
                        gameCount--;
                    }
                }
            }
        }
        if (NoDanger) {
            for (int i = 3; i < gameMap.length; i++) { //диагональ левый верхний - правый нижний         реверс
                for (int j = 3; j < gameMap[i].length; j++) {
                    if (gameMap[i][j] == 'O' && gameMap[i - 1][j - 1] == 'O' && gameMap[i - 2][j - 2] == 'O' && gameMap[i - 3][j - 3] != 'O' && gameMap[i - 3][j - 3] != 'X') {
                        gameMap[i - 3][j - 3] = 'O';
                        NoDanger = false;
                        gameCount--;
                    }
                }
            }
        }
        if (NoDanger) {
            for (int i = 0; i < gameMap.length - 3; i++) { //диагональ левый нижний - правый верхний
                for (int j = 3; j < gameMap[i].length; j++) {
                    if (gameMap[i][j] == 'O' && gameMap[i + 1][j - 1] == 'O' && gameMap[i + 2][j - 2] == 'O' && gameMap[i + 3][j - 3] != 'O' && gameMap[i + 3][j - 3] != 'X') {
                        gameMap[i + 3][j - 3] = 'O';
                        NoDanger = false;
                        gameCount--;
                    }
                }
            }
        }
        if (NoDanger) {
            for (int i = 0; i < gameMap.length; i++) { // горизонталь
                for (int j = 0; j < gameMap[i].length - 2; j++) {
                    if (gameMap[i][j] == 'X' && gameMap[i][j + 1] == 'X' && gameMap[i][j + 2] != 'O' && gameMap[i][j + 2] != 'X') {
                        gameMap[i][j + 2] = 'O';
                        NoDanger = false;
                        gameCount--;
                    }
                }
            }
        }
        if (NoDanger) {
            for (int i = 0; i < gameMap.length - 2; i++) { //вертикаль
                for (int j = 0; j < gameMap[i].length; j++) {
                    if (gameMap[i][j] == 'X' && gameMap[i + 1][j] == 'X' && gameMap[i + 2][j] != 'O' && gameMap[i + 2][j] != 'X') {
                        gameMap[i + 2][j] = 'O';
                        NoDanger = false;
                        gameCount--;
                    }
                }
            }
        }
        if (NoDanger) {
            for (int i = 0; i < gameMap.length - 2; i++) { //диагональ левый верхний - правый нижний
                for (int j = 0; j < gameMap[i].length - 2; j++) {
                    if (gameMap[i][j] == 'X' && gameMap[i + 1][j + 1] == 'X' && gameMap[i + 2][j + 2] != 'O' && gameMap[i + 2][j + 2] != 'X') {
                        gameMap[i + 2][j + 2] = 'O';
                        NoDanger = false;
                        gameCount--;
                    }
                }
            }
        }
        if (NoDanger) {
            for (int i = 2; i < gameMap.length; i++) { //диагональ левый нижний - правый верхний
                for (int j = 0; j < gameMap[i].length - 2; j++) {
                    if (gameMap[i][j] == 'X' && gameMap[i - 1][j + 1] == 'X' && gameMap[i - 2][j + 2] != 'O' && gameMap[i - 2][j + 2] != 'X') {
                        gameMap[i - 2][j + 2] = 'O';
                        NoDanger = false;
                        gameCount--;
                    }
                }
            }
        }
        if (NoDanger) {
            for (int i = 0; i < gameMap.length; i++) { // горизонталь реверс
                for (int j = 2; j < gameMap[i].length; j++) {
                    if (gameMap[i][j] == 'X' && gameMap[i][j - 1] == 'X' && gameMap[i][j - 2] != 'O' && gameMap[i][j - 2] != 'X') {
                        gameMap[i][j - 2] = 'O';
                        NoDanger = false;
                        gameCount--;
                    }
                }
            }
        }
        if (NoDanger) {
            for (int i = 2; i < gameMap.length; i++) { //вертикаль реверс
                for (int j = 0; j < gameMap[i].length; j++) {
                    if (gameMap[i][j] == 'X' && gameMap[i - 1][j] == 'X' && gameMap[i - 2][j] != 'O' && gameMap[i - 2][j] != 'X') {
                        gameMap[i - 2][j] = 'O';
                        NoDanger = false;
                        gameCount--;
                    }
                }
            }
        }
        if (NoDanger) {
            for (int i = 2; i < gameMap.length; i++) { //диагональ левый верхний - правый нижний         реверс
                for (int j = 2; j < gameMap[i].length; j++) {
                    if (gameMap[i][j] == 'X' && gameMap[i - 1][j - 1] == 'X' && gameMap[i - 2][j - 2] != 'O' && gameMap[i - 2][j - 2] != 'X') {
                        gameMap[i - 2][j - 2] = 'O';
                        NoDanger = false;
                        gameCount--;
                    }
                }
            }
        }
        if (NoDanger) {
            for (int i = 0; i < gameMap.length - 2; i++) { //диагональ левый нижний - правый верхний
                for (int j = 2; j < gameMap[i].length; j++) {
                    if (gameMap[i][j] == 'X' && gameMap[i + 1][j - 1] == 'X' && gameMap[i + 2][j - 1] != 'O' && gameMap[i + 2][j - 1] != 'X') {
                        gameMap[i + 2][j - 2] = 'O';
                        NoDanger = false;
                        gameCount--;
                    }
                }
            }
        }
        while (NoDanger) {
            int x = random.nextInt(gameMap.length);
            int y = random.nextInt(gameMap[0].length);
            if (gameMap[x][y] != 'X' && gameMap[x][y] != 'O') {
                gameMap[x][y] = O_DOT;
                gameCount--;
                break;
            }
        }
    }

    private static boolean isWin(char userOrPc) {

        for (int i = 0; i < gameMap.length; i++) { // горизонталь
            for (int j = 0; j < gameMap[i].length - 3; j++) {
                if (gameMap[i][j] == userOrPc && gameMap[i][j + 1] == userOrPc && gameMap[i][j + 2] == userOrPc && gameMap[i][j + 3] == userOrPc) {
                    return true;
                }
            }
        }
        for (int i = 0; i < gameMap.length - 3; i++) { //вертикаль
            for (int j = 0; j < gameMap[i].length; j++) {
                if (gameMap[i][j] == userOrPc && gameMap[i + 1][j] == userOrPc && gameMap[i + 2][j] == userOrPc && gameMap[i + 3][j] == userOrPc) {
                    return true;
                }
            }
        }
        for (int i = 0; i < gameMap.length - 3; i++) { //диагональ левый верхний - правый нижний
            for (int j = 0; j < gameMap[i].length - 3; j++) {
                if (gameMap[i][j] == userOrPc && gameMap[i + 1][j + 1] == userOrPc && gameMap[i + 2][j + 2] == userOrPc && gameMap[i + 3][j + 3] == userOrPc) {
                    return true;
                }
            }
        }
        for (int i = 2; i < gameMap.length; i++) { //диагональ левый нижний - правый верхний
            for (int j = 0; j < gameMap[i].length - 3; j++) {
                if (gameMap[i][j] == userOrPc && gameMap[i - 1][j + 1] == userOrPc && gameMap[i - 2][j + 2] == userOrPc && gameMap[i - 3][j + 3] == userOrPc) {
                    return true;
                }
            }
        }
        return false;////////////// нет совпадений
    }

    private static boolean readyToStep() {
        if (gameCount == 0) {
            System.out.println("FRENDLY WIN");
            return false;
        }
        return true;
    }

    private static void userStep() {
        System.out.println("input your step (expire: \"2 2\")");
        giveMe2Int();
        if (gameMap.length >= x && gameMap[0].length >= y && (gameMap[x - 1][y - 1] != 'X' && gameMap[x - 1][y - 1] != 'O')) {
            gameMap[x - 1][y - 1] = X_DOT;
            gameCount--;
        } else {
            paintMap();
            System.out.println("It is not correct or free");
            userStep();
        }
    }

    private static void initMap() {

        gameCount = 0;

        while ((Main.x < 4 || Main.x > 10) || (Main.y < 4 || Main.y > 10)) {
            System.out.println("input Map size.\nMin \"4 4\" Max \"10 10\"");
            giveMe2Int();
        }

        gameMap = new char[x][y];

        for (int i = 0; i < gameMap.length; i++) {
            for (int j = 0; j < gameMap[i].length; j++) {
                gameMap[i][j] = EMPTY_DOT;
                gameCount++;
            }
        }
    }

    private static void paintMap() {
        for (int i = 0; i < gameMap.length; i++) {
            System.out.println();
            for (int j = 0; j < gameMap[i].length; j++) {
                System.out.print(gameMap[i][j] + " ");
            }
        }
        System.out.println();
    }

    private static void giveMe2Int() {
        boolean num1 = false;
        boolean num2 = false;
        int n1 = 0;
        int n2 = 0;

        if (sc.hasNextInt()) {
            n1 = sc.nextInt();
            num1 = true;
        }
        if (sc.hasNextInt()) {
            n2 = sc.nextInt();
            num2 = true;
        }
        if (num1 && num2) {
            Main.y = n1;
            Main.x = n2;
            sc.nextLine();
        } else {
            System.out.println("Your input not a number.\nPlease, try again.");
            sc.nextLine();
            giveMe2Int();
        }
    }

}
