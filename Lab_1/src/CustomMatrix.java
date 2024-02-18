public class CustomMatrix {
    private final int numRows;
    private final int numCols;
    private ComplexNumbers[][] matrixData;

    public CustomMatrix() {
        numRows = 0;
        numCols = 0;
    }

    public CustomMatrix(int numRows, int numCols, ComplexNumbers initialValue) {
        this.numRows = numRows;
        this.numCols = numCols;
        this.matrixData = new ComplexNumbers[numRows][numCols];
        for (int i = 0; i < numRows; ++i) {
            for (int j = 0; j < numCols; ++j) {
                matrixData[i][j] = new ComplexNumbers(initialValue);
            }
        }
    }

    public CustomMatrix(int numRows, int numCols) {
        this(numRows, numCols, new ComplexNumbers(0, 0));
    }

    public CustomMatrix(CustomMatrix sourceMatrix) {
        numRows = sourceMatrix.numRows;
        numCols = sourceMatrix.numCols;
        matrixData = new ComplexNumbers[numRows][numCols];
        for (int i = 0; i < numRows; ++i) {
            for (int j = 0; j < numCols; ++j) {
                matrixData[i][j] = new ComplexNumbers(sourceMatrix.matrixData[i][j]);
            }
        }
    }

    ComplexNumbers getElement(int i, int j) {
        return matrixData[i][j];
    }

    int getNumRows() {
        return this.numRows;
    }

    int getNumCols() {
        return this.numCols;
    }

    CustomMatrix negate() {
        CustomMatrix resultMatrix = new CustomMatrix(this);
        for (int i = 0; i < numRows; ++i) {
            for (int j = 0; j < numCols; ++j) {
                resultMatrix.matrixData[i][j] = resultMatrix.matrixData[i][j].negative();
            }
        }
        return resultMatrix;
    }

    CustomMatrix add(CustomMatrix rhs) {
        if (numRows != rhs.numRows || numCols != rhs.numCols) {
            throw new IllegalArgumentException("Matrix dimensions must match for addition");
        }
        CustomMatrix resultMatrix = new CustomMatrix(this);
        for (int i = 0; i < numRows; ++i) {
            for (int j = 0; j < numCols; ++j) {
                resultMatrix.matrixData[i][j] = resultMatrix.matrixData[i][j].add(rhs.matrixData[i][j]);
            }
        }
        return resultMatrix;
    }

    CustomMatrix subtract(CustomMatrix rhs) {
        return add(rhs.negate());
    }

    CustomMatrix multiply(CustomMatrix rhs) {
        if (numCols != rhs.numRows) {
            throw new IllegalArgumentException("Number of columns in the first matrix must be equal to the number of rows in the second matrix");
        }
        CustomMatrix resultMatrix = new CustomMatrix(numRows, rhs.numCols);
        for (int i = 0; i < numRows; ++i) {
            for (int j = 0; j < rhs.numCols; ++j) {
                for (int k = 0; k < numCols; ++k) {
                    resultMatrix.matrixData[i][j] = resultMatrix.matrixData[i][j].add(matrixData[i][k].multiply(rhs.matrixData[k][j]));
                }
            }
        }
        return resultMatrix;
    }

    CustomMatrix transpose() {
        CustomMatrix resultMatrix = new CustomMatrix(numCols, numRows);
        for (int i = 0; i < numCols; ++i) {
            for (int j = 0; j < numRows; ++j) {
                resultMatrix.matrixData[i][j] = matrixData[j][i];
            }
        }
        return resultMatrix;
    }

    boolean isSquare() {
        return numRows == numCols;
    }

    CustomMatrix getMinor(int row, int col) {
        CustomMatrix resultMatrix = new CustomMatrix(numRows - 1, numCols - 1);
        int di = 0;
        int dj = 0;
        for (int i = 0; i < numRows - 1; ++i) {
            if (i == row) {
                di = 1;
            }
            dj = 0;
            for (int j = 0; j < numCols - 1; ++j) {
                if (j == col) {
                    dj = 1;
                }
                resultMatrix.matrixData[i][j] = matrixData[i + di][j + dj];
            }
        }
        return resultMatrix;
    }

    ComplexNumbers determinant() {
        if (!isSquare()) {
            throw new IllegalArgumentException("Determinant calculation is only applicable to square matrices");
        }
        int n = numRows;
        if (n == 1) {
            return matrixData[0][0];
        }
        ComplexNumbers result = new ComplexNumbers(0, 0);
        ComplexNumbers power = new ComplexNumbers(1, 0);
        for (int i = 0; i < n; ++i) {
            result = result.add(matrixData[i][0].multiply(power).multiply(getMinor(i, 0).determinant()));
            power = power.negative();
        }
        return result;
    }

    public String toString() {
        StringBuilder resultString = new StringBuilder();
        for (int i = 0; i < numRows; ++i) {
            for (int j = 0; j < numCols; ++j) {
                resultString.append(matrixData[i][j]).append(" ");
            }
            resultString.append(System.lineSeparator());
        }
        if (!resultString.isEmpty()) {
            resultString.delete(resultString.length() - (System.lineSeparator().length() + 1), resultString.length());
        }
        return resultString.toString();
    }
}
