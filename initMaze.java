import java.util.*;

public class initMaze {

    public static void mazeSidesFill(int[][] maze) {
        int height = maze.length;
        int width = maze[0].length;
        
        int j = 0;
        while (j < width) {
            maze[0][j] = 1;
            j++;
        }
    
        j = 0;
        while (j < width) {
            maze[height - 1][j] = 1;
            j++;
        }
    
        int i = 1;
        while (i < height - 1) {
            maze[i][0] = 1;
            i++;
        }
    
        i = 1;
        while (i < height - 1) {
            maze[i][width - 1] = 1;
            i++;
        }
    }
    
    public static void gridFill(int[][] maze) {
        int i = 1;
        int j = 1;
    
        while (i < maze.length) {
            while (j < maze[i].length) {
                if (i % 2 == 0) {
                    maze[i][j] = 1;
                }
                j++;
            }
            j = 1;
            i++;
        }
    
        i = 1;
        j = 1;
    
        while (j < maze[0].length) {
            while (i < maze.length) {
                if (j % 2 == 0) {
                    maze[i][j] = 1;
                }
                i++;
            }
            i = 1;
            j++;
        }

        maze[0][1] = 0;
        maze[maze.length - 1][maze[0].length - 2] = 0;
    }

    public static void mazeRandCase(int[][] maze, int[][] numbers, int num) {
        int i = 1;
        int j = 1;
        int rand = 0;
        int index = 0;
        ArrayList<Integer> availableNumbers = new ArrayList<>();
        for (int k = 2; k <= num + 1; k++) {
            availableNumbers.add(k);
        }
    
        while (i < maze.length - 1) {
            while (j < maze[i].length - 1) {
                if (maze[i][j] == 0 && availableNumbers.size() > 0) {
                    Collections.shuffle(availableNumbers);
                    rand = availableNumbers.remove(availableNumbers.size() - 1);
                    maze[i][j] = rand;
                    numbers[index][0] = rand;
                    numbers[index][1] = i;
                    numbers[index][2] = j;
                    index++;
                }
                j+=2;
            }
            j = 1;
            i++;
        }
    }
    
}