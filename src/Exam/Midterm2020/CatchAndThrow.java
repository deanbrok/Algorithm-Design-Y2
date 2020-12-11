package Exam.Midterm2020;

import java.util.*;

class Catch implements Comparable<Catch> {

    int friend1;

    int friend2;

    double catchRate;

    public Catch(int friend1, int friend2, double catchRate) {
        this.friend1 = friend1;
        this.friend2 = friend2;
        this.catchRate = catchRate;
    }

    @Override
    public int compareTo(Catch o) {
        if(o.catchRate - this.catchRate < 0) return -1;
        else if(o.catchRate - this.catchRate > 0) return 1;
        else return 0;
    }
}

public class CatchAndThrow {
    public static double minimalLargestDropChance(int n, Set<Catch> catches) {
        // TODO
        List<List<Catch>> eachCatchList = new ArrayList<>(n);



        double[] currentHighestProbability = new double[n+1];

        for(Catch cat: catches)
        {

        }
        return  0;
    }

    public static void main(String[] args) {
        int n = 4;
        Set<Catch> catches = new HashSet<>();
        catches.add(new Catch(1, 2, 0.6));
        catches.add(new Catch(1, 3, 0.4));
        catches.add(new Catch(2, 3, 0.7));
        catches.add(new Catch(1, 4, 0.8));
        // For Friend 2 a catch rate of 0.6
        // For Friend 3 a catch rate of 0.4 if we go directly, but we can go via friend 2 for a catch rate of 0.42
        // For Friend 4 a catch rate of 0.8
        // So the hardest to reach is 3 with 0.42
    }
}
