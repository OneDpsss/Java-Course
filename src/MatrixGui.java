import java.util.Scanner;

public class MatrixGui {
    public static void displayMatrix(CustomMatrix matrix) {
        System.out.println(matrix.toString());
    }

    public static void readMatrixElements(Scanner scanner, CustomMatrix matrix) {
        for (int i = 0; i < matrix.getNumRows(); ++i) {
            for (int j = 0; j < matrix.getNumCols(); ++j) {
                System.out.print("Enter matrix[" + i + "][" + j + "]: ");
                ComplexGui.readComplexNumberInput(scanner, matrix.getElement(i, j));
            }
        }
    }

    public static CustomMatrix readMatrixInput(Scanner scanner) {
        int numRows = scanner.nextInt();
        int numCols = scanner.nextInt();
        CustomMatrix resultMatrix = new CustomMatrix(numRows, numCols);
        readMatrixElements(scanner, resultMatrix);
        return resultMatrix;
    }
}
