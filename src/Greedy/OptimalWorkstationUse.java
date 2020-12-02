package Greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* Problem:
        A cloud service offers the opportunity for n researchers to submit jobs from workstations in a nearby office
    building. Access to these workstations is controlled by an operator. This operator needs to unlock the
    workstations for the researchers who come to run their computations at the cloud service. However, this
    operator is very lazy. She can unlock the machines remotely from her desk, but does not feel that this menial
    task matches her qualifications. She therefore decides to ignore the security guidelines and to simply ask the
    researchers not to lock their workstations when they leave, and then assign new researchers to workstations
    that are not used any more but that are still unlocked. That way, she only needs to unlock each workstation
    for the first researcher using it. Unfortunately, unused workstations lock themselves automatically if they are
    unused for more than m minutes. After a workstation has locked itself, the operator has to unlock it again
    for the next researcher using it. Given the exact schedule of arriving and leaving researchers, can you tell the
    operator how many unlocks she may save by asking the researchers not to lock their workstations when
    they leave and assigning arriving researchers to workstations in an optimal way? You may assume that there
    are always enough workstations available.

    You are given start times s_i
    and duration d_i for all 1≤i≤n.
    Given the following research times and a lock time of 10 minutes:
    2 6
    1 2
    17 7
    3 9
    15 6

    the output should be 3.
 */

public class OptimalWorkstationUse {
    /**
     * @param n        number of researchers
     * @param m        number of minutes after which workstations lock themselves
     * @param start    start times of jobs 1 through n. NB: you should ignore start[0]
     * @param duration duration of jobs 1 through n. NB: you should ignore duration[0]
     * @return the number of unlocks that can be saved.
     */
    public static int solve(int n, int m, int[] start, int[] duration) {

        //1. Initialize all the intervals for easy sorting by start time
        Interval[] intervals = new Interval[n];
        for (int i = 1; i <= n; i++) {
            intervals[i - 1] = new Interval(start[i], start[i] + duration[i]);
        }
        Arrays.sort(intervals);

        //2. Initialize an empty workstation to keep track of how many unlocks are needed
        List<Integer> workstations = new ArrayList<>();

        //3. Go through each interval, i.e. each job, and assign it to a workstation if they are compatible
        // and the workstation is not locked
        for (Interval interval : intervals) {
            for (int i = 0; i < workstations.size(); i++) {
                if (interval.start >= workstations.get(i) && interval.start - workstations.get(i) <= m) {
                    workstations.set(i, interval.end);
                    interval.labeled = true;
                    break;
                }
            }
            //If no workstations are available/compatible then a new workstation must be unlocked
            if (!interval.labeled) {
                workstations.add(interval.end);
            }
        }

        //4. Return the number of work stations saved by subtracting the number of workstations unlocked from
        // the total which we should have unlocked for
        return n - workstations.size();
    }

    public static void main(String[] args) {
        int n = 5;
        int m = 10;
        int[] start = { 0, 2, 1, 17, 3, 15 };
        int[] duration = { 0, 6, 2, 7, 9, 6 };
        System.out.println(new OptimalWorkstationUse().solve(n,m,start,duration));
    }
}


