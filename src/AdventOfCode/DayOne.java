package AdventOfCode;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DayOne {

    int solve()
    {

        InputStream input = getClass().getResourceAsStream("input.txt");
        Scanner sc = new Scanner(input);
        List<Integer> integerList = new ArrayList<>();

        while (sc.hasNext())
        {
            integerList.add(sc.nextInt());
        }

        for(int i = 0; i < integerList.size() - 2; i++)
        {
            for (int j = i + 1; j < integerList.size() - 1;j++)
            {
                for(int k = j + 1; k < integerList.size() - 2; k++)
                {
                    if(integerList.get(i) + integerList.get(j) + integerList.get(k)== 2020)
                    {
                        return integerList.get(i)*integerList.get(j)*integerList.get(k);
                    }
                }
            }
        }
        return  0;
    }

    public static void main(String[] args) {
        System.out.println(new DayOne().solve());
    }
}
