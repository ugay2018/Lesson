import java.util.Random;
import java.util.Scanner;

public class Home_Work {

    static final int SIZE_X = 3;
    static final int SIZE_Y = 3;
    static final char[][] field = new char[SIZE_Y][SIZE_X];

    /*Символ игрока*/
    static final char DOT_PLAYER = 'X';

    /*Символ пользователя*/
    static final char DOT_AT = '0';

    /*Пустая строка*/
    static final char EMPTY_DOT = '*';


    static Scanner scanner = new Scanner(System.in);
    static Random random = new Random();
    static final int forWin = 3;

    public static void main(String[] args) {
        initField();
        printField();

        while(true) {
            movePerson();
            printField();
            if(checkWin(DOT_PLAYER)) {
                System.out.println("Победа игрока!");
                break;
            }
            if(isFreePlace()) {
                System.out.println("Ничья");
                break;
            }
            if(isFreePlace()) {
                System.out.println("Ничья");
                break;
            }
            moveAi();
            System.out.println();
            printField();
            if(checkWin(DOT_AT)) {
                System.out.println("Победа компьютера");
                break;
            }
            if(isFreePlace()) {
                System.out.println("Ничья");
                break;
            }
        }
    }
    /*Заполнение пустыми строка*/
    static void initField() {
        for (int i = 0; i < SIZE_Y; i++) {
            for (int j = 0; j < SIZE_X; j++) {
                field[i][j] = EMPTY_DOT;
            }
        }
    }

    /*Печатать поле пустое*/
    static void printField() {
        for (int i = 0; i < SIZE_Y; i++) {
            for (int j = 0; j < SIZE_X; j++) {
                System.out.print(field[i][j] + "\t");
            }
            System.out.println();
        }
    }

    /*Ставить на поле символ метод принимает
     координаты и какой символ поставиь
     * */
    static void setSym(int x, int y, char sym) {
        field[y][x] = sym;
    }

    /*Ход человека*/
    static void movePerson() {
        int x, y;
        do {
            System.out.println("Введите координаты");
            x = scanner.nextInt() - 1;
            y = scanner.nextInt() - 1;
        }while(!isCeleValid(x,y));
        field[y][x] = DOT_AT;
    }


    static void moveAi() {
        int x, y;
        do {
            x = random.nextInt(SIZE_X);
            y = random.nextInt(SIZE_Y);
        } while (!isCeleValid(x, y));
        field[y][x] = DOT_AT;
    }

    /*Проверка занята ли ячейка*/
    static boolean isCeleValid(int x, int y) {
        if (x < 0 || x >= SIZE_X || y < 0 || y >= SIZE_Y) return false;
        if (field[y][x] != EMPTY_DOT) return false;
        return true;
    }
    /*Проверка места для хода*/
    static boolean isFreePlace() {
        for (int i = 0; i < SIZE_Y; i++) {
            for (int j = 0; j < SIZE_X; j++) {
                if (field[i][j] == EMPTY_DOT) {
                    return true;
                }
            }

        }
        return false;
    }

    static boolean checkWin(char sym) {

        //for stroke

        for(int i = 0; i<SIZE_Y; i++) {
            int result = 0;
            for(int j = 0; j < SIZE_X; j++) {
                if(field[i][j] ==sym){
                    result ++;
                }
                if(result == forWin){
                    return true;
                }
            }
        }

        for(int i = 0; i<SIZE_Y; i++) {
            int result = 0;
            for(int j = 0; j < SIZE_X; j++) {
                if(field[j][i] ==sym){
                    result ++;
                }
                if(result == forWin){
                    return true;
                }
            }
        }
        int firstDiag = 0;
        for(int i = 0; i<SIZE_Y; i++) {

            for(int j = 0; j < SIZE_X; j++) {
                if(j ==i && field[i][j] == sym) {
                    firstDiag++;
                }
                if(firstDiag == forWin){
                    return true;
                }
            }
        }

        int secondDiag = 0;
        for(int i = 0,j = SIZE_Y -1; i< SIZE_X && j >= 0; i++, j--){
            if(field[i][j] == sym) {
                secondDiag ++;
            }
        }if(secondDiag == forWin) {
            return true;
        }
        return false;
    }
}




