public class ComplexNumbers {
    private double real,imag;
    void SetReal(double real){this.real = real;}
    void SetImag(double imag){this.imag = imag;}


    public ComplexNumbers(){
        real = 0;
        imag = 0;
    }

    public ComplexNumbers(double reali, double imaginary) {
        real = reali;
        imag = imaginary;
    }

    public ComplexNumbers negative() {
        return new ComplexNumbers(-real, -imag);
    }

    public ComplexNumbers(ComplexNumbers rhs) {
        real = rhs.real;
        imag = rhs.imag;
    }
    public ComplexNumbers add(ComplexNumbers rhs) {
        return new ComplexNumbers(real + rhs.real, imag + rhs.imag);
    }

    public ComplexNumbers sub(ComplexNumbers rhs) {
        return add(rhs.negative());
    }

    public ComplexNumbers multiply(ComplexNumbers rhs) {
        return new ComplexNumbers(real * rhs.real - imag * rhs.imag, real * rhs.imag + rhs.real * imag);
    }

    public double r() {
        return Math.sqrt(real * real + imag * imag);
    }

    public double theta() {
        return Math.atan2(imag, real);
    }

    public String toString() {
        return real + (imag > 0 ? "+" : "") + (imag == 0 ? "" : (imag + "i"));
    }

}
