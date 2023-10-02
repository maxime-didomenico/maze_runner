import java.util.*;

public class SimpleImperfectMazeGenerator {

    private int width;
    private int length;
    private byte[][] maze;
    private boolean[][] visited;
    private int[][] dirs = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}};

    public SimpleImperfectMazeGenerator(int width, int length) {
        this.width = width;
        this.length = length;
        this.maze = new byte[width][length];
        this.visited = new boolean[width][length];
    }

    public byte[][] generateMaze() {
        // Initialize all cells to walls
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < length; j++) {
                maze[i][j] = 0;
            }
        }

        // Start DFS from [0][1]
        dfs(0, 1);

        return maze;
    }

    private void dfs(int x, int y) {
        visited[x][y] = true;
        maze[x][y] = 1;

        // Shuffle directions
        List<int[]> dirsList = Arrays.asList(dirs);
        Collections.shuffle(dirsList);

        for (int[] dir : dirsList) {
            int nx = x + dir[0];
            int ny = y + dir[1];

            if (nx >= 0 && ny >= 0 && nx < width && ny < length && !visited[nx][ny]) {
                dfs(nx, ny);
            }
        }
    }
}
