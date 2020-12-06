package Exam.ResitMidterm2018;

public class Greedy2 {

    /**
     *  You should implement this method.
     *  @param n the number of jobs.
     *  @param t an array of size n+1, containing the initialization times t_1 through t_n. You should ignore t[0].
     *  @param p an array of size n+1, containing the computation times p_1 through p_n. You should ignore p[0].
     *  @param S an array of size n+1, containing at position S[i] the start time of the ith job that will run. I.e. S[1] contains the start time of the first job that will run.
     *  @param I an array of size n+1, containing at position I[i] the index j of the ith job that will run. I.e. If I[4] = 3, then the fourth job to run is the job with initialization time t[3] and processing time p[3].
     *  @return The latest end time given this schedule.
     */
    public static int solve(int n, int[] t, int[] p, int[] S, int[] I) {
        // TODO
        int latestEndTime = 0;
        for(int i = 1; i <= n; i ++)
        {
            int indexOfCurrentJob = I[i];
            int currentEndTime = S[i] + t[indexOfCurrentJob] + p[indexOfCurrentJob];
            if(latestEndTime < currentEndTime)
            {
                latestEndTime = currentEndTime;
            }
        }

        return latestEndTime;
    }

    public static void main(String[] args) {
        int n = 2;
        int[] t = { 0, 1, 3 };
        int[] p = { 0, 5, 2 };
        int[] S = { 0, 0, 20 };
        int[] I = { 0, 2, 1 };
        System.out.println(solve(n, t, p, S, I));
    }
}
