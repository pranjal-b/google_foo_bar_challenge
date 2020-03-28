package com.company;
 
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
 
public class Main {
 
    static int[][] gridArr = {{0, 1, 2, 3, 4, 5, 6, 7},
            {8, 9, 10, 11, 12, 13, 14, 15},
            {16, 17, 18, 19, 20, 21, 22, 23},
            {24, 25, 26, 27, 28, 29, 30, 31},
            {32, 33, 34, 35, 36, 37, 38, 39},
            {40, 41, 42, 43, 44, 45, 46, 47},
            {48, 49, 50, 51, 52, 53, 54, 55},
            {56, 57, 58, 59, 60, 61, 62, 63}};
 
 
    static Grid grid = new Grid(gridArr);
 
    public static void main(String[] args) {
 
 
        System.out.println(findShortestKnightPath(19, 36));
    }
 
    public static int findShortestKnightPath(int src, int dest) {
        if (src == dest)
            return 0;
 
        int[][] offset = {{-1, -2}, {1, -2}, {2, -1}, {2, 1}, {1, 2}, {-1, 2}, {-2, 1}, {-2, -1}};
        int currentMoves = 0;
 
        HashSet<Integer> visitedSpaces = new HashSet<>();
        Queue<int[]> moveQueue = new LinkedList<>();
 
        //queue holds location and number of moves
        int[] move = {src, 0};
        moveQueue.add(move);
 
        while (!moveQueue.isEmpty()) {
            int[] queuePoll = moveQueue.poll();
            int current = queuePoll[0];
            currentMoves = queuePoll[1];
            currentMoves++;
 
            visitedSpaces.add(current);
 
            int[] currentCoords = grid.getCoords(current);
 
            for (int i = 0; i < grid.gridSize(); i++) {
                int newX = currentCoords[0] + offset[i][0];
                int newY = currentCoords[1] + offset[i][1];
                int newLocation = 0;
                if (grid.isWithinGrid(newX, newY))
                    newLocation = grid.getLocation(newX, newY);
 
                if (newLocation == dest) {
                    return currentMoves;
                }
 
                if (!visitedSpaces.contains(newLocation)) {
                    int[] queueArr = {newLocation, currentMoves};
                    moveQueue.add(queueArr);
                }
                visitedSpaces.add(newLocation);
            }
        }
 
        return currentMoves;
    }
}
 
class Grid {
    int[][] gArr;
 
    public Grid(int[][] gArr) {
        this.gArr = gArr;
    }
 
 
    //gets coords for a point with data that equals location
    public int[] getCoords(int location) {
        //REMINDER: Array looks like: {x, y}
 
        int y = -1;
        int x = location % gArr.length;
        int[] coords = {-1, -1};
 
        //Checks each row for location, i corresponds to each y value
        for (int i = 0; i < gArr.length; i++) {
            if (gArr[i][0] <= location && (gArr[i][0] + 7) >= location)
                y = i;
        }
 
        //if the value exists, fill the array.
        if (y != -1) {
            coords[0] = x;
            coords[1] = y;
        }
 
        return coords;
    }
 
 
    public int getLocation(int x, int y) {
        return gArr[y][x];
    }
 
 
    //A method to check whether values will be inside the grid
    public boolean isWithinGrid(int x, int y) {
        if (x >= 0 && y >= 0 && x < gArr.length && y < gArr.length) {
            return true;
        }
        return false;
    }
 
    public int gridSize() {
        return gArr.length;
    }
}
