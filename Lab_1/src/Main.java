import java.util.Scanner;

public class Main {

    public static void testComplexNumbers(Scanner scanner) {
        ComplexNumbers firstNumber = new ComplexNumbers();
        ComplexNumbers secondNumber = new ComplexNumbers();

        System.out.print("Enter the first complex number: ");
        ComplexGui.readComplexNumberInput(scanner, firstNumber);

        System.out.print("Enter the second complex number: ");
        ComplexGui.readComplexNumberInput(scanner, secondNumber);

        ComplexGui.displayComplexNumber(firstNumber);
        ComplexGui.displayComplexNumber(secondNumber);
        ComplexGui.displayComplexTrigForm(firstNumber);
        ComplexGui.displayComplexTrigForm(secondNumber);

        System.out.println("Sum: " + firstNumber.add(secondNumber));
        System.out.println("Difference: " + firstNumber.sub(secondNumber));
        System.out.println("Multiply: " + firstNumber.multiply(secondNumber));
    }

    public static void testMatrices(Scanner scanner) {
        System.out.print("Enter the number of rows (N) and columns (M) for matrix A: ");
        CustomMatrix matrixA = MatrixGui.readMatrixInput(scanner);

        System.out.print("Enter the number of rows (N) and columns (M) for matrix B: ");
        CustomMatrix matrixB = MatrixGui.readMatrixInput(scanner);

        System.out.println("Matrix A:");
        MatrixGui.displayMatrix(matrixA);

        System.out.println("Matrix B:");
        MatrixGui.displayMatrix(matrixB);

        System.out.println("Transposed A: " + System.lineSeparator() + matrixA.transpose());
        System.out.println("Determinant of A: " + matrixA.determinant());

        System.out.println("Sum: " + System.lineSeparator() + matrixA.add(matrixB));
        System.out.println("Sum: " + System.lineSeparator() + matrixB.add(matrixA));
        System.out.println("Difference: " + System.lineSeparator() + matrixA.subtract(matrixB));
        System.out.println("Multiply: " + System.lineSeparator() + matrixA.multiply(matrixB));
    }

    public static void main(String[] args) {
        System.out.println("1. Test complex numbers");
        System.out.println("2. Test matrices");

        Scanner scanner = new Scanner(System.in);
        byte choice = scanner.nextByte();

        switch (choice) {
            case 1 -> testComplexNumbers(scanner);
            case 2 -> testMatrices(scanner);
            default -> System.out.println("Invalid choice");
        }

        scanner.close();
    }
}
