package AdventOfCode;

import Greedy.Node;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.*;

public class DayThree {
    public int puzzle1() {
        InputStream input = getClass().getResourceAsStream("trees.txt");
        Scanner sc = new Scanner(input);

        List<List<String>> map = new ArrayList<>();

        int count = 0;
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            String[] split = line.split("");
            List<String> currentLine = new ArrayList<>();

            for(String space: split)
            {
                currentLine.add(space);

            }
            map.add(currentLine);
        }

        int lengthOfALine = map.get(0).size();
        int xCoordinate = 0;
        int yCoordinate = 0;
        int treeCount = 0;

        while(yCoordinate < map.size() - 1) {
            xCoordinate += 1;
            yCoordinate += 2;
            int xCoordinateFromTheMap = xCoordinate % lengthOfALine;
            if (map.get(yCoordinate).get(xCoordinateFromTheMap).equals("#"))
            {
                treeCount++;
            }
        }

        return treeCount;
    }


    public static void main(String[] args) {
        System.out.println(new DayThree().puzzle1());
        //puzzle 2 is just reusing the code another 4 times
    }
}
