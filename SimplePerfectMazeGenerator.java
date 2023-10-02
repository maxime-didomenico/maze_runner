import java.util.Random;
import java.util.ArrayList;
import java.util.List;

public class SimplePerfectMazeGenerator {
    private List<Integer> availableNumbers = new ArrayList<>();

    public void generate(int[][] maze, int[][] numbers, int num, int width, int length) {
        Random rand = new Random();

        if (numbers.length == 0 || maze.length == 0) {
            System.out.println("Le tableau est vide.");
            return;
        }

        initializeAvailableNumbers(numbers);

        while (!isOkay(numbers, maze)) {
            int[] coord = new int[2];
            int buff = 0;

            int randNum = getNextAvailableNumber();

            if (randNum == -1) {
                break;
            }

            int dir = rand.nextInt(4);
            coord = getValues(numbers, randNum);

            switch (dir) {
                case 0: // haut
                    if (coord[0] - 2 >= 0 && maze[coord[0] - 2][coord[1]] != 1) {
                        buff = maze[coord[0] - 2][coord[1]];

                        maze[coord[0] - 1][coord[1]] = randNum;
                        maze[coord[0] - 2][coord[1]] = randNum;

                        replaceNumbers(numbers, randNum, buff);
                        checkOccurence(numbers, maze, coord, randNum, buff);
                    }
                    break;
                case 1: // droite
                    if (coord[1] + 2 < length && maze[coord[0]][coord[1] + 2] != 1) {
                        buff = maze[coord[0]][coord[1] + 2];

                        maze[coord[0]][coord[1] + 1] = randNum;
                        maze[coord[0]][coord[1] + 2] = randNum;

                        replaceNumbers(numbers, randNum, buff);
                        checkOccurence(numbers, maze, coord, randNum, buff);
                    }
                    break;
                case 2: // bas
                    if (coord[0] + 2 < width && maze[coord[0] + 2][coord[1]] != 1) {
                        buff = maze[coord[0] + 2][coord[1]];
                        maze[coord[0] + 1][coord[1]] = randNum;
                        maze[coord[0] + 2][coord[1]] = randNum;

                        replaceNumbers(numbers, randNum, buff);
                        checkOccurence(numbers, maze, coord, randNum, buff);
                    }
                    break;
                case 3: // gauche
                    if (coord[1] - 2 >= 0 && maze[coord[0]][coord[1] - 2] != 1) {
                        buff = maze[coord[0]][coord[1] - 2];
                        maze[coord[0]][coord[1] - 1] = randNum;
                        maze[coord[0]][coord[1] - 2] = randNum;

                        replaceNumbers(numbers, randNum, buff);
                        checkOccurence(numbers, maze, coord, randNum, buff);
                    }
                    break;
            }
        }
    }

    public boolean containsNumber(int[][] numbers, int num) {
        if (numbers.length == 0) {
            System.out.println("Le tableau est vide.");
            return false;
        }

        int i = 0;
        while (i < numbers.length) {
            if (numbers[i].length == 0) {
                System.out.println("Le tableau à l'indice " + i + " est vide.");
                i++;
                continue;
            }

            if (numbers[i][0] == num) {
                return true;
            }
            i++;
        }
        return false;
    }

    public boolean isOkay(int[][] numbers, int[][] maze) {
        int i = 0;
        int num = 0;
        int[] buff = new int[2];

        if (numbers.length == 0) {
            System.out.println("Le tableau est vide.");
            return true;
        }

        while (i < numbers.length - 1) {
            if (numbers[i].length == 0 || numbers[i + 1].length == 0) {
                System.out.println("Le tableau à l'indice " + i + " ou " + (i + 1) + " est vide.");
                i++;
                continue;
            }

            if (numbers[i][0] != numbers[i + 1][0] && numbers[i][0] != buff[0] && numbers[i][0] != buff[1]) {
                buff[num] = numbers[i][0];
                num++;
            }
            if (num > 1) {
                return false;
            }
            i++;
        }
        return true;
    }

    public static int[] getValues(int[][] numbers, int num) {
        int[] value = new int[2];
        int i = 0;

        if (numbers.length == 0) {
            System.out.println("Le tableau est vide.");
            return value;
        }

        while (i < numbers.length) {
            if (numbers[i].length < 3) {
                System.out.println("Le tableau à l'indice " + i + " a moins de 3 éléments.");
                i++;
                continue;
            }

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

    public void replaceNumbers(int[][] numbers, int num, int replace) {
        int i = 0;
        while (i < numbers.length) {
            if (numbers[i][0] == replace) {
                numbers[i][0] = num;
            }
            i++;
        }
    }

    public void replaceWalls(int[][] maze) {
        int i = 0;
        int j = 0;

        while (i < maze.length) {
            while (j < maze[i].length) {
                if (maze[i][j] != 1) {
                    maze[i][j] = 0;
                }
                j++;
            }
            j = 0;
            i++;
        }
    }

    public void checkOccurence(int[][] numbers, int[][] maze, int[] coord, int num, int buff) {
        int i = 0;
        int j = 0;

        while (i < maze.length) {
            while (j < maze[i].length) {
                if (maze[i][j] == buff) {
                    maze[i][j] = num;
                    replaceNumbers(numbers, num, buff);
                }
                j++;
            }
            j = 0;
            i++;
        }
    }

    public void initializeAvailableNumbers(int[][] numbers) {
        int i = 0;
        while (i < numbers.length) {
            availableNumbers.add(numbers[i][0]);
            i++;
        }
    }

    public int getNextAvailableNumber() {
        if (!availableNumbers.isEmpty()) {
            Random rand = new Random();

            int index = rand.nextInt(availableNumbers.size());
            int randNum = availableNumbers.get(index);
            availableNumbers.remove(index);
            return randNum;
        }
        return -1;
    }
}
