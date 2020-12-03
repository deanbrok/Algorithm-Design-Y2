package Greedy.Level1;

import Greedy.Interval;

import java.io.InputStream;
import java.util.*;

public class BikeRepair {

    /*
Mike runs a bike repair service for students. Students can plan a time to bring in their bike on his website, where they
also enter a description of what is broken. Mike is very good at his job and he can flawlessly predict how long each repair
will cost. To cater the need of students to have a working bike at all time, he promises his customers to finish the repair
as soon as possible after the bike is brought in.

Now Mike wants to know how many of his employees to schedule for a day. He has the list of repairs for this day and he wants
you to develop an algorithm to tell him how many people he needs.

You get the list in the following format: On the first line is the amount of repairs.
The next lines list the repairs with on each line the time the repair starts followed by the time the repair will take.
     */
    public String solve() {
        // TODO
        InputStream input = getClass().getResourceAsStream("bike.in");
        Scanner sc = new Scanner(input);

        int n = sc.nextInt();
        int[] starts = new int[n];
        int[] ends = new int[n];

        Interval[] intervals = new Interval[n];

        //Keep the labeling of the intervals and the end time
        List<Integer> labelsAndEndTime = new ArrayList<>();
        labelsAndEndTime.add(Integer.MIN_VALUE);

        for(int i = 0; i < n; i++)
        {
            starts[i] = sc.nextInt();
            ends[i] = starts[i] + sc.nextInt();
            intervals[i] = new Interval(starts[i],ends[i]);
        }

        System.out.println("Old Intervals");
        for(Interval interval: intervals)
        {
            System.out.println(interval.start + ":" + interval.end);
        }

        Arrays.sort(intervals);
        //Arrays.sort(ends);
        System.out.println();
        System.out.println("Sorted Intervals");
        for(Interval interval: intervals)
        {
            System.out.println(interval.start + ":" + interval.end);
        }

        for (Interval interval: intervals)
        {
            for(int i = 0; i < labelsAndEndTime.size(); i++)
            {
                if(interval.start >=  labelsAndEndTime.get(i))
                {
                    labelsAndEndTime.set(i,interval.end);
                    interval.labeled = true;
                    break;
                }
            }
            if(!interval.labeled)
            {
                labelsAndEndTime.add(interval.end);
            }
        }


        return String.valueOf(labelsAndEndTime.size());
    }

    public static void main(String[] args) {
        System.out.println(new BikeRepair().solve());
    }
}

