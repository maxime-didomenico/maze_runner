import java.util.ArrayList;

public class Maze {
    public static void main(String[] args) {

        // handle errors
        if (args.length != 4) {
            System.out.println("Invalid number of arguments.");
            System.exit(0);
        }

        try {
            int width = Integer.parseInt(args[0]);
            int length = Integer.parseInt(args[1]);
            if (width < 1 || length < 1 || width < 2 || length < 2) {
                System.out.println("Invalid maze dimensions.");
                System.exit(0);
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid arguments.");
            System.exit(0);
        }

        int width = Integer.parseInt(args[0])*3;
        int length = Integer.parseInt(args[1])*3;

        if (width % 2 == 0) {
            width++;
        }
        if (length % 2 == 0) {
            length++;
        }

        int num = ((((width - (width % 2)) / 2) * ((length - (length % 2)) / 2)));
        int[][] numbers = new int[num][3];

        String mazeType = args[2];
        String mazePerfection = args[3];

        int[][] maze = new int[width][length];
        
        //initialize maze by using initMaze.java
        initMaze.mazeSidesFill(maze);
        initMaze.gridFill(maze);
        initMaze.mazeRandCase(maze, numbers, num);
        //printMaze(maze);

        // generate maze by type and perfection
        switch (mazeType) {
            case "imperfect":
                switch (mazePerfection) {
                    case "simple":
                        //SimpleImperfectMazeGenerator
                        break;
                    case "graph":
                        //GraphBasedMazeGenerator

                        break;
                    case "optimized":
                        //OptimizedMazeGenerator
                        break;
                    default:
                        System.out.println("Invalid maze perfection");
                        System.exit(0);
                }
                break;
            case "perfect":
                switch (mazePerfection) {
                    case "simple":
                        //SimplePerfectMazeGenerator
                        SimplePerfectMazeGenerator mazeGenerator = new SimplePerfectMazeGenerator();
                        mazeGenerator.generate(maze, numbers, num, width, length);
                        break;
                    case "graph":
                        //GraphBasedMazeGenerator
                        break;
                    case "optimized":
                        //OptimizedMazeGenerator
                        break;
                    default:
                        System.out.println("Invalid maze perfection");
                        System.exit(0);
                }
                break;
            default:
                System.out.println("Invalid maze type");
                System.exit(0);
        }
        replaceNumbers(maze, width, length);
        printMaze(maze);
    }
    
    public static void replaceNumbers(int[][] maze, int width, int length) {
        int i = 0;
        int j = 0;

        while (i < width) {
            while (j < length) {
                if (maze[i][j] != 1) {
                    maze[i][j] = 0;
                }
                j++;
            }
            j = 0;
            i++;
        }
    }

    private static void printMaze(int[][] maze) {
        int i = 0;
        int j = 0;

        while (i < maze.length) {
            while (j < maze[i].length) {
                System.out.print(maze[i][j]);
                j++;
            }
            System.out.println();
            j = 0;
            i++;
        }
    } 
}