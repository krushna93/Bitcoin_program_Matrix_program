package Bitcoin_program;

public class Sizer {
    public static void main(String[] args) {
       
        char[][] matrix = {
                {'O', 'T', 'O', 'O'},
                {'O', 'T', 'O', 'T'},
                {'T', 'T', 'O', 'T'},
                {'O', 'T', 'O', 'T'}
        };

        int rows = matrix.length;
        int cols = matrix[0].length;
        boolean[][] visited = new boolean[rows][cols];

        
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                  if (matrix[i][j] == 'T' && !visited[i][j]) {
                    int Size = computerSize(matrix, visited, i, j);
                    System.out.print(Size + " ");
                }
            }
        }
    }

  
    private static int computerSize(char[][] matrix, boolean[][] visited, int row, int col) {
        int rows = matrix.length;
        int cols = matrix[0].length;

     
        if (row < 0 || row >= rows || col < 0 || col >= cols || visited[row][col]) {
            return 0;
        }

   
        if (matrix[row][col] != 'T') {
            return 0;
        }

       
        visited[row][col] = true;

       
        int size = 1;

   
        size += computerSize(matrix, visited, row - 1, col); // Up
        size += computerSize(matrix, visited, row + 1, col); // Down
        size += computerSize(matrix, visited, row, col - 1); // Left
        size += computerSize(matrix, visited, row, col + 1); // Right
        size += computerSize(matrix, visited, row - 1, col - 1); // Diagonal: Up-Left
        size += computerSize(matrix, visited, row - 1, col + 1); // Diagonal: Up-Right
        size += computerSize(matrix, visited, row + 1, col - 1); // Diagonal: Down-Left
        size += computerSize(matrix, visited, row + 1, col + 1); // Diagonal: Down-Right

        return size;
    }
}