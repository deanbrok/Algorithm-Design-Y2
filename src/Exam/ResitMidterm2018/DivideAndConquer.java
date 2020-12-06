package Exam.ResitMidterm2018;

public class DivideAndConquer {

    /**
     *  You should implement this method.
     *  @param n the number of jobs.
     *  @param t an array of size n+1, containing the initialization times t_1 through t_n. You should ignore t[0].
     *  @param p an array of size n+1, containing the computation times p_1 through p_n. You should ignore p[0].
     *  @param S an array of size n+1, containing at position S[i] the start time of the ith job that will run. I.e. S[1] contains the start time of the first job that will run.
     *  @param I an array of size n+1, containing at position I[i] the index j of the ith job that will run. I.e. If I[4] = 3, then the fourth job to run is the job with initialization time t[3] and processing time p[3].
     *  @param x the time at which the high priority job should be started.
     *  @return The number of jobs that are initialized at or later than x.
     */
    public static int solve(int n, int[] t, int[] p, int[] S, int[] I, int x)
    {
        // TODO
        return n - findPointInTheMiddle(x,0,n,S);
    }

    public static int findPointInTheMiddle(int x, int start, int end, int[] S) {
        int middle = (start + end) / 2;

        if (start == end) return start;
        if (S[middle] < x && S[middle + 1] >= x) {
            return middle;
        } else if (S[middle] >= x && S[middle + 1] >= x) {
            return findPointInTheMiddle(x, start, middle - 1, S);
        } else
        {
            return  findPointInTheMiddle(x, middle + 1, end, S);
        }
    }

    static class Job
    {
        int initializationTime;
        int computationTime;

        public Job(int initializationTime, int computationTime) {
            this.initializationTime = initializationTime;
            this.computationTime = computationTime;
        }

        @Override
        public String toString() {
            return "Job{" +
                    "initializationTime=" + initializationTime +
                    ", computationTime=" + computationTime +
                    '}';
        }


    }
}
