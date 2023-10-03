import java.util.Random;
import java.util.Set;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class SimplePerfectMazeGenerator {

    public void generate(int[][] maze, int[][] numbers, int num, int width, int length) {
        Random rand = new Random();
        List<Integer> availableNumbers = new ArrayList<>();

        initializeAvailableNumbers(numbers, availableNumbers);

        while (!isOkay(numbers, maze)) {

            int[] coord = new int[2];
            int buff = 0;

            int randNum = getNextAvailableNumber(maze, numbers, availableNumbers, num);

            if (randNum == -1) {
                break;
            }

            int dir = rand.nextInt(4);
            coord = getValues(numbers, randNum);

            switch (dir) {
                case 0: // haut
                if (coord[0] - 2 >= 0 && maze[coord[0] - 2][coord[1]] != 1 && maze[coord[0] - 2][coord[1]] != 0 && maze[coord[0] - 2][coord[1]] != randNum && randNum != 1) {
                    buff = maze[coord[0] - 2][coord[1]];

                    if (availableNumbers.contains(buff)) {
                        availableNumbers.remove(availableNumbers.indexOf(buff));
                    }
                    replaceInNumbers(numbers, buff, randNum, maze);
                    maze[coord[0] - 1][coord[1]] = randNum;
                    maze[coord[0] - 2][coord[1]] = randNum;
                }
                break;
            case 1: // droite
                if (coord[1] + 2 < length && maze[coord[0]][coord[1] + 2] != 1 && maze[coord[0]][coord[1] + 2] != 0 && maze[coord[0]][coord[1] + 2] != randNum && randNum != 1) {
                    buff = maze[coord[0]][coord[1] + 2];

                    if (availableNumbers.contains(buff)) {
                        availableNumbers.remove(availableNumbers.indexOf(buff));
                    }
                    replaceInNumbers(numbers, buff, randNum, maze);
                    maze[coord[0]][coord[1] + 1] = randNum;
                    maze[coord[0]][coord[1] + 2] = randNum;
                }
                break;
            case 2: // bas
                if (coord[0] + 2 < width && maze[coord[0] + 2][coord[1]] != 1 && maze[coord[0] + 2][coord[1]] != 0 && maze[coord[0] + 2][coord[1]] != randNum && randNum != 1) {
                    buff = maze[coord[0] + 2][coord[1]];

                    if (availableNumbers.contains(buff)) {
                        availableNumbers.remove(availableNumbers.indexOf(buff));
                    }
                    replaceInNumbers(numbers, buff, randNum, maze);
                    maze[coord[0] + 1][coord[1]] = randNum;
                    maze[coord[0] + 2][coord[1]] = randNum;
                }
                break;
            case 3: // gauche
                if (coord[1] - 2 >= 0 && maze[coord[0]][coord[1] - 2] != 1 && maze[coord[0]][coord[1] - 2] != 0 && maze[coord[0]][coord[1] - 2] != randNum && randNum != 1) {
                    buff = maze[coord[0]][coord[1] - 2];

                    if (availableNumbers.contains(buff)) {
                        availableNumbers.remove(availableNumbers.indexOf(buff));
                    }
                    replaceInNumbers(numbers, buff, randNum, maze);
                    maze[coord[0]][coord[1] - 1] = randNum;
                    maze[coord[0]][coord[1] - 2] = randNum;
                }
                break;
            }
        }
    }









    public boolean isOkay(int[][] numbers, int[][] maze) {
        Set<Integer> uniqueNumbers = new HashSet<>();

        for (int i = 0; i < numbers.length; i++) {
            uniqueNumbers.add(numbers[i][0]);
            if (uniqueNumbers.size() > 1) {
                return false;
            }
        }
        return true;
    }
    


    public static void replaceInNumbers(int[][] numbers, int buff, int num, int[][] maze) {
        int i = 0;

        while (i < numbers.length) {
            if (numbers[i][0] == buff) {
                numbers[i][0] = num;
            }
            i++;
        }
        replaceInMaze(maze, buff, num);
    }


    public static void replaceInMaze(int[][] maze, int buff, int num) {
        int i = 0;
        int j = 0;

        while (i < maze.length) {
            while (j < maze[i].length) {
                if (maze[i][j] == buff) {
                    maze[i][j] = num;
                }
                j++;
            }
            j = 0;
            i++;
        }
    }


    public static int[] getValues(int[][] numbers, int num) {
        int[] value = new int[2];
        int i = 0;

        while (i < numbers.length) {
            if (numbers[i][0] == num) {
                value[0] = numbers[i][1];
                value[1] = numbers[i][2];
                break;
            } else {
                i++;
            }
        }
        return value;
    }
    
    public void initializeAvailableNumbers(int[][] numbers, List<Integer> availableNumbers) {
        int i = 0;
        while (i < numbers.length) {
            availableNumbers.add(numbers[i][0]);
            i++;
        }
    }


    public int getNextAvailableNumber(int[][] maze, int[][] numbers, List<Integer> availableNumbers, int num) {
        if (!availableNumbers.isEmpty()) {
            int max = num / 2;
            Random rand = new Random();
            int index = rand.nextInt(availableNumbers.size());
            int randNum = availableNumbers.get(index);
            int countInNumbers = 0;
            int i = 0;

            while (i < numbers.length) {
                if (numbers[i][0] == randNum) {
                    countInNumbers++;
                }
                i++;
            }
    
            if (countInNumbers > max) {
                availableNumbers.remove(index);
                return getNextAvailableNumber(maze, numbers, availableNumbers, num);  // Récursion pour tirer un autre nombre
            }
    
            return randNum;
        } else {
            return -1;
        }
    }
}