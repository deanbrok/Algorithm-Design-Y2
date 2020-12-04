package AdventOfCode;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DayTwo {
    int puzzle1()
    {
        InputStream input = getClass().getResourceAsStream("password.txt");
        Scanner sc = new Scanner(input);
        int numberOfValidPasswords = 0;

        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            String[] split = line.split(" ");

            int min = Integer.parseInt(String.valueOf(split[0].split("-")[0]));
            int max = Integer.parseInt(String.valueOf(split[0].split("-")[1]));
            char character = split[1].charAt(0);
            String password = split[2];

            int count = 0;

            for(int i = 0; i < password.length(); i++)
            {
                if(password.charAt(i) == character)
                {
                    count += 1;
                }
            }

            if(count >= min && count <= max)
            {
                numberOfValidPasswords++;
            }
        }

        return numberOfValidPasswords;
    }

    int puzzle2()
    {
        InputStream input = getClass().getResourceAsStream("password.txt");
        Scanner sc = new Scanner(input);
        int numberOfValidPasswords = 0;

        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            String[] split = line.split(" ");

            int position1 = Integer.parseInt(String.valueOf(split[0].split("-")[0]));
            int position2 = Integer.parseInt(String.valueOf(split[0].split("-")[1]));
            char character = split[1].charAt(0);
            String password = split[2];

            if(password.charAt(position1 - 1) == character ^ password.charAt(position2 - 1) == character) numberOfValidPasswords += 1;

        }

        return numberOfValidPasswords;
    }

    public static void main(String[] args) {
        System.out.println(new DayTwo().puzzle1());
        System.out.println(new DayTwo().puzzle2());
    }
}
