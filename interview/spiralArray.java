/**
 * Создание матрицы в спиральном виде
 */
public class spiralArray {

    public static void main(String[] args) {
        final int m = 3; //количество строк в матрице
        final int n = 4; //количество столбцов в матрице

        int[][] matrix = new int[m][n]; //матрица

        int maxRotations = m*n; //максимальное количество цифр внутри матрицы
        String direction = "right"; //направление, по умолчание 'вправо'
        int row = 0; //строка
        int col = 0; //столбец

        for (int i = 1; i <= maxRotations; i++) {

            if (direction == "right" && (col > n - 1 || matrix[row][col] != 0)) {
                direction = "down";
                row++;
                col--;
            }

            if (direction == "down" && (row > m - 1 || matrix[row][col] != 0)) {
                direction = "left";
                col--;
                row--;
            }

            if (direction == "left" && (col < 0 || matrix[row][col] != 0)) {
                direction = "up";
                row--;
                col++;
            }

            if (direction == "up" && (row < 0 || matrix[row][col] != 0)) {
                direction = "right";
                row++;
                col++;
            }

            matrix[row][col] = i;

            if (direction == "right") {
                col++;
            }
            else if (direction == "down") {
                row++;
            }
            else if (direction == "left") {
                col--;
            }
            else if (direction == "up") {
                row--;
            }

        }

        printMatrix(matrix, n, m);

    }

    //вывод матрицы на консоль
    private static void printMatrix(int[][] arr, int n, int m){

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(arr[i][j] + "\t");
            }
            System.out.println();
        }
    }

}
