package Exam.Midterm2018;

import java.io.InputStream;
import java.util.*;

public class SchedulingCourtCases {
    // Implement the solve method to return the answer to the problem posed by the inputstream.
    public static String run(InputStream in) {
        return new SchedulingCourtCases().solve(in);
    }

    private int numCases;

    private int numCourtRooms;

    private long[] caseTimes;

    public String solve(InputStream in) {
        parseInput(in);
        return Long.toString(computeLastFinish());
    }

    /**
     *  You should implement this method to compute when the last court case will be finished.
     */
    private long computeLastFinish() {
        // TODO

        PriorityQueue<Long> rooms = new PriorityQueue<>();
        //List<Long> roomTimes = new ArrayList<>();

        long max = 0;

        for(int i = 0; i < numCourtRooms; i++) {
            //.add((long) 0);
            rooms.add((long) 0);
        }

        for(long courtTime: caseTimes)
        {
            long minTime = rooms.poll();
            rooms.add(courtTime + minTime + 1);
            //roomTimes.set(roomTimes.indexOf(minTime), courtTime + minTime + 1);

            if(max < courtTime + minTime + 1) max = courtTime + minTime + 1;
        }

        return max - 1;
    }

    /**
     *  This method parses the input from an inputstream. You should not need to modify this method.
     */
    private void parseInput(InputStream in) {
        Scanner sc = new Scanner(in);
        this.numCases = sc.nextInt();
        this.numCourtRooms = sc.nextInt();
        this.caseTimes = new long[this.numCases];
        for (int i = 0; i < this.numCases; i++) {
            sc.next();
            this.caseTimes[i] = sc.nextLong();
        }
        sc.close();
    }
}
