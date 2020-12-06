package Exam.ResitMidterm2018;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Greedy1 {
    /**
     *  You should implement this method.
     *  @param n the number of jobs.
     *  @param t an array of size n+1, containing the initialization times t_1 through t_n. You should ignore t[0].
     *  @param p an array of size n+1, containing the computation times p_1 through p_n. You should ignore p[0].
     *  @return The minimum latest start time.
     */
    public static int solve(int n, int[] t, int[] p) {

        List<Job> jobs = new ArrayList<>();
        int currentStartTime = 0;

        for(int i = 1; i <= n; i++)
        {
            jobs.add(new Job(t[i],p[i]));
        }


        Collections.sort(jobs);

        for(Job job: jobs)
        {
            currentStartTime += job.initializationTime;
        }

        System.out.println(jobs);

        return currentStartTime - jobs.get(jobs.size() - 1).initializationTime;
    }

    public static void main(String[] args) {
        int[] t = {0,1,3};
        int[] p = {0,2,5};
        int n = 2;

        System.out.println(solve(n,t,p));
    }

    static class Job implements Comparable<Job>
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

        @Override
        public int compareTo(Job o) {
            return o.computationTime - this.computationTime;
        }

    }


}
