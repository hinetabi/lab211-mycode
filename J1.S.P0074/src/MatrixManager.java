/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Legion
 */
public class MatrixManager {
    public static int[][] enterMatrix(int x, int y) {
        int[][] newMatrix = new int[x][y];
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                newMatrix[i][j] = Inputter.inputInteger(String.format("Enter Matrix1[%d][%d]:", i, j), true, false);
            }
        }
        return newMatrix;
    }
    
    //thêm ràng buộc 
    public static int[][] addMatrix(int[][] matrix1, int[][] matrix2) throws Exception {
        int x = matrix1.length;
        int y = matrix1[0].length;
        int x1 = matrix2.length;
        int y2 = matrix2[0].length;
        if (x != x1 || y != y2) {
            throw new Exception("Can not add 2 matrix not have equal row and col!");
        }
        int[][] newMatrix = new int[x][y];
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                newMatrix[i][j] = matrix1[i][j] + matrix2[i][j];
            }
        }
        return newMatrix;
    }
    
    public static int[][] subtractMatrix(int[][] matrix1, int[][] matrix2) throws Exception {
        int x = matrix1.length;
        int y = matrix1[0].length;
        int x1 = matrix2.length;
        int y2 = matrix2[0].length;
        if (x != x1 || y != y2) {
            throw new Exception("Can not substract 2 matrix not have equal row and col!");
        }
        int[][] newMatrix = new int[x][y];
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                newMatrix[i][j] = matrix1[i][j] - matrix2[i][j];
            }
        }
        return newMatrix;
    }
    
    public static int[][] multiplicateMatrix(int[][] matrix1, int[][] matrix2) throws Exception {
        int m1 = matrix1.length;
        int n1 = matrix1[0].length;
        int m2 = matrix2.length;
        int n2 = matrix2[0].length;
        
        if (n1 != m2) {
            throw new Exception("Can not multiple 2 matrix!");
        }
        int[][] newMatrix = new int[m1][n2];
        
        // tính và in ra ma trận C = A * B
            for (int i = 0; i < m1; i++) {
                for (int j = 0; j < n2; j++) {
                    newMatrix[i][j] = 0;
                    for (int k = 0; k < n1; k++) {
                        newMatrix[i][j] += matrix1[i][k] * matrix2[k][j];
                    }
                }
            }
        
        return newMatrix;
    }
    
    public static void printMatrix(int[][] matrix) {
        for (int[] matrix1 : matrix) {
            for (int j = 0; j < matrix1.length; j++) {
                System.out.printf("[%d]", matrix1[j]);
            }
            System.out.println("");
        }
    }
    
    
    
    
    
}
