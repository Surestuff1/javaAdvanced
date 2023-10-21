import java.util.Scanner;

public class FishingCompetition {

    static int shipRow;
    static int shipCol;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());

        char[][] field = new char[n][n];

        int[] coordinates = fillMatrixAndGetCoordinates(field, scanner);
        shipRow = coordinates[0];
        shipCol = coordinates[1];
        field[shipRow][shipCol] = '-';

        int tons = 0;
        boolean isTrue = false;
        String currentcommand = scanner.nextLine();

        while (!currentcommand.equals("collect the nets")) {
            move(currentcommand, field, n);

            if (field[shipRow][shipCol] == 'W') {
                tons = 0;
                System.out.printf("You fell into a whirlpool! The ship sank and you lost the fish you caught. " +
                        "Last coordinates of the ship: [%d,%d]", shipRow, shipCol);
                isTrue = true;
                break;
            } else if (Character.isDigit(field[shipRow][shipCol])) {

                int number = Integer.parseInt(String.valueOf(field[shipRow][shipCol]));
                tons += number;
                field[shipRow][shipCol] = '-';
            }
            currentcommand = scanner.nextLine();

        }
        if (!isTrue) {
            if (tons >= 20) {

                System.out.println("Success! You managed to reach the quota!");
                System.out.printf("Amount of fish caught: %d tons.",tons);
            } else if (tons < 20) {
                System.out.printf("You didn't catch enough fish and didn't reach the quota! " +
                        "You need %d tons of fish more.\n", 20 - tons);
                if (tons != 0) {
                    System.out.printf("Amount of fish caught: %d tons.", tons);
                }
            }
            System.out.println();
            field[shipRow][shipCol] = 'S';
            printMatrix(field);
        }
    }

    private static void move(String command, char[][] field,int n) {
        switch (command) {
            case "up":
                if (canMove(field, shipRow - 1, shipCol)) {
                    shipRow -= 1;
                    shipCol=shipCol;
                }
                else{
                    shipRow=n;
                    shipCol=shipCol;
                }
                break;
            case "down":
                if (canMove(field, shipRow + 1, shipCol)) {
                    shipRow += 1;
                    shipCol=shipCol;
                }
                else {
                    shipRow=0;
                    shipCol=shipCol;
                }
                break;
            case "left":
                if (canMove(field, shipRow, shipCol - 1)) {
                    shipCol -= 1;
                    shipRow=shipRow;
                }
                else {
                    shipCol=n;
                    shipRow=shipRow;
                }
                break;
            case "right":
                if (canMove(field, shipRow, shipCol + 1)) {
                    shipCol += 1;
                    shipRow=shipRow;
                }
                else {
                    shipCol=0;
                    shipRow=shipRow;
                }
                break;
            default:
                break;
        }

    }

    private static boolean canMove(char[][] field, int shipRow, int shipCol) {

        return shipRow >= 0 && shipRow < field.length &&
                shipCol >= 0 && shipCol < field[shipRow].length;
    }

    private static void printMatrix(char[][] field) {
        for (char[] row : field) {
            for (char symbol : row) {
                System.out.print(symbol);
            }
            System.out.println();
        }
    }

    private static int[] fillMatrixAndGetCoordinates(char[][] field, Scanner scanner) {
        int[] coordinates = new int[2];

        for (int row = 0; row < field.length; row++) {
            char[] currentRow = scanner.nextLine().toCharArray();
            field[row] = currentRow;

            // get Ship's coordinates
            for (int col = 0; col < currentRow.length; col++) {
                char symbol = currentRow[col];
                if (symbol == 'S') {
                    coordinates[0] = row;
                    coordinates[1] = col;
                    break;
                }
            }
        }
        return coordinates;
    }
}
