import java.util.Random;

public class SimpleImperfectMazeGenerator {

    public void holesMaker(int[][] maze, int[][] numbers, int num, int width, int length) {
        Random rand = new Random();
    

        int numberOfHoles = num / 4;
    
        int i = 0;
        while (i < numberOfHoles) {
            int x, y;
    
            do {
                x = rand.nextInt(width - 2)+ 1;
                y = rand.nextInt(length - 2) + 1;
            } while (maze[x][y] != 1);
    
            maze[x][y] = 0;
    
            i++;
        }
    }    
}