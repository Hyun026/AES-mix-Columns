import java.util.Scanner;

public class AESMixColumns {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the 16 hexadecimal values of the 4x4 matrix:");
        String[] matrixValues = new String[16];
        for (int i = 0; i < 16; i++) {
            matrixValues[i] = scanner.next();
        }

        String[][] matrix = new String[4][4];
        int k = 0;
        for (int i = 0; i < 4; ++i) {
            for (int j = 0; j < 4; ++j) {
                matrix[i][j] = matrixValues[k++];
            }
        }

        System.out.println("Matrix before MixColumns:");
        printMatrix(matrix);

        String[][] resultMatrix = mixColumns(matrix);

        System.out.println("Matrix after MixColumns:");
        printMatrix(resultMatrix);

        scanner.close();
    }

    public static String[][] mixColumns(String[][] matrix) {
        String[][] result = new String[4][4];

        for (int col = 0; col < 4; col++) {
            result[0][col] = mixColumnElement(matrix[0][col], matrix[1][col], matrix[2][col], matrix[3][col]);
            result[1][col] = mixColumnElement(matrix[1][col], matrix[2][col], matrix[3][col], matrix[0][col]);
            result[2][col] = mixColumnElement(matrix[2][col], matrix[3][col], matrix[0][col], matrix[1][col]);
            result[3][col] = mixColumnElement(matrix[3][col], matrix[0][col], matrix[1][col], matrix[2][col]);
        }

        return result;
    }

    public static String mixColumnElement(String a, String b, String c, String d) {
        String result = MixColProd.mult("02*" + a);
        result = xorHexNumbers(result, MixColProd.mult("03*" + b));
        result = xorHexNumbers(result, c);
        result = xorHexNumbers(result, d);
        return result;
    }

    public static String xorHexNumbers(String a, String b) {
        int aInt = Integer.parseInt(a, 16);
        int bInt = Integer.parseInt(b, 16);
        int result = aInt ^ bInt;
        return String.format("%02X", result);
    }

    public static void printMatrix(String[][] matrix) {
        for (String[] row : matrix) {
            for (String value : row) {
                System.out.print(value + "\t");
            }
            System.out.println();
        }
    }
}
