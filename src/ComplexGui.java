import java.util.Scanner;

public class ComplexGui {
    public static void displayComplexNumber(ComplexNumbers number) {
        System.out.println(number.toString());
    }

    public static void displayComplexTrigForm(ComplexNumbers number) {
        System.out.println(number.r() + " * (cos(" + number.theta() + ") + i * sin(" + number.theta() + "))");
    }

    public static void readComplexNumberInput(Scanner inputScanner, ComplexNumbers number) {
        number.SetReal(inputScanner.nextDouble());
        number.SetImag(inputScanner.nextDouble());
    }
}
