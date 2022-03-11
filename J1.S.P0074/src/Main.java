/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Legion
 */
public class Main {

    /**
     * Get choice
     *
     * @param ops
     * @return
     */
    public static int getchoice(String[] ops) {
        for (int i = 0; i < ops.length; i++) {
            System.out.println((i + 1) + ". " + ops[i]);
        }
        System.out.println();
        return Inputter.inputIntegerInRange("Enter your choice(1-" + ops.length + "): ", 1, ops.length);
    }

    public static void main(String[] args) {
        String[] ops = new String[]{"Addition Matrix", "Subtraction Matrix", "Multiplication Matrix", "Quit"};

        int choice;
        do {
            int row = 0, col = 0;
            int[][] matrix1, matrix2;

            System.out.println("======Caculator program======");
            choice = getchoice(ops);
            switch (choice) {
                case 1: {
                    System.out.println("-----Addition-----");
                    row = Inputter.inputInteger("Enter Row Matrix 1: ", false, true);
                    col = Inputter.inputInteger("Enter Column Matrix 1:", false, true);
                    matrix1 = MatrixManager.enterMatrix(row, col);

                    while (true) {
                        row = Inputter.inputInteger("Enter Row Matrix 2: ", false, true);
                        col = Inputter.inputInteger("Enter Column Matrix 2:", false, true);
                        if (row == matrix1.length && col == matrix1[0].length) {
                            break;
                        } else {
                            System.err.println("The row and col of matrix 2 must be equal the one!");
                        }
                    }

                    matrix2 = MatrixManager.enterMatrix(row, col);

                    System.out.println("----Result----");
                    MatrixManager.printMatrix(matrix1);
                    System.out.println("+");
                    MatrixManager.printMatrix(matrix2);
                    System.out.println("=");

                    try {
                        MatrixManager.printMatrix(MatrixManager.addMatrix(matrix1, matrix2));
                    } catch (Exception e) {
                        System.err.println(e.getMessage());
                    }

                    break;
                }
                case 2: {
                    System.out.println("-----Subtraction-----");
                    row = Inputter.inputInteger("Enter Row Matrix 1: ", false, true);
                    col = Inputter.inputInteger("Enter Column Matrix 1:", false, true);
                    matrix1 = MatrixManager.enterMatrix(row, col);

                    while (true) {
                        row = Inputter.inputInteger("Enter Row Matrix 2: ", false, true);
                        col = Inputter.inputInteger("Enter Column Matrix 2:", false, true);
                        if (row == matrix1.length && col == matrix1[0].length) {
                            break;
                        } else {
                            System.err.println("The row and col of matrix 2 must be equal the one!");
                        }
                    }

                    matrix2 = MatrixManager.enterMatrix(row, col);

                    System.out.println("----Result----");
                    MatrixManager.printMatrix(matrix1);
                    System.out.println("-");
                    MatrixManager.printMatrix(matrix2);
                    System.out.println("=");

                    try {
                        MatrixManager.printMatrix(MatrixManager.subtractMatrix(matrix1, matrix2));
                    } catch (Exception e) {
                        System.err.println(e.getMessage());
                    }

                    break;
                }
                case 3: {
                    System.out.println("-----Multiplication-----");
                    row = Inputter.inputInteger("Enter Row Matrix 1: ", false, true);
                    col = Inputter.inputInteger("Enter Column Matrix 1:", false, true);
                    matrix1 = MatrixManager.enterMatrix(row, col);

//                    while (true) {
//                        row = Inputter.inputInteger("Enter Row Matrix 2: ", false, true);
//                        if (row == col) {
//                            break;
//                        } else {
//                            System.err.println("The row of matrix 2 must equal col of matrix 1!");
//                        }
//                    }

                    row = Inputter.inputInteger("Enter Row Matrix 2: ", false, true);
                    col = Inputter.inputInteger("Enter Column Matrix 2:", false, true);
                    matrix2 = MatrixManager.enterMatrix(row, col);

                    System.out.println("----Result----");
                    MatrixManager.printMatrix(matrix1);
                    System.out.println("*");
                    MatrixManager.printMatrix(matrix2);
                    System.out.println("=");

                    try {
                        MatrixManager.printMatrix(MatrixManager.multiplicateMatrix(matrix1, matrix2));
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }

                    break;
                }
            }

        } while (choice < ops.length);
    }
}
