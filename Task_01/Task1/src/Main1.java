import java.util.Scanner;

public class Main1
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введіть кількість рядків: ");
        int numRows = scanner.nextInt();

        System.out.print("Введіть кількість стовпців: ");
        int numCols = scanner.nextInt();

        int[][] matrix = new int[numRows][numCols];

        for (int i = 0; i < numRows; i++)
        {
            for (int j = 0; j < numCols; j++)
            {
                System.out.print("Введіть елемент [" + (i + 1) + "][" + (j + 1) + "]: ");
                matrix[i][j] = scanner.nextInt();
            }
        }

        System.out.println("Введена матриця:");
        printMatrix(matrix);

        int[] result = findMinimumSymmetricElements(matrix);

        System.out.print("Мінімальні симетричні елементи у відповідних стовпцях матриці: ");
        for (int i = 0; i < result.length; i++)
        {
            System.out.print(result[i] + " ");
        }
    }

    public static int[] findMinimumSymmetricElements(int[][] matrix)
    {
        int numRows = matrix.length;
        int numCols = matrix[0].length;
        int[] minSymmetricElements = new int[numCols];

        for (int j = 0; j < numCols; j++)
        {
            int minSymmetricElement = matrix[j][j];

            for (int i = 0; i < numRows; i++)
            {
                if (i != j && matrix[i][j] < minSymmetricElement)
                {
                    minSymmetricElement = matrix[i][j];
                }
            }
            minSymmetricElements[j] = minSymmetricElement;
        }
        return minSymmetricElements;
    }

    public static void printMatrix(int[][] matrix)
    {
        for (int i = 0; i < matrix.length; i++)
        {
            for (int j = 0; j < matrix[0].length; j++)
            {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}
