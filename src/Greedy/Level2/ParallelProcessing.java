package Greedy.Level2;

import java.util.Arrays;

public class ParallelProcessing {

    /*
    We have m processors and n jobs. Each job i in {1,…,n} has a processing time of exactly 1 hour. Furthermore, each job i has
    an integer deadline di in hours. The aim is to find a start time si and processor pi for each job such that no jobs are run
    at the same processor at the same time and such that the maximum lateness over all jobs is minimized. The lateness is the time
    a job finishes compared to its deadline, defined here by si + 1 - di. The objective thus is to minimize maxi{si+1−di}.

    Create a greedy algorithm to determine a schedule that has the smallest maximum lateness. Given 2 processors and the following 5
    deadlines: 3, 1, 1, 1, 2 your algorithm should return the minimized maximum lateness, in this case: 1.
     */

    /**
     * @param n the number of jobs
     * @param m the number of processors
     * @param deadlines the deadlines of the jobs 1 through n. NB: you should ignore deadlines[0]
     * @return the minimised maximum lateness.
     */
    public static int solve(int n, int m, int[] deadlines)
    {
        //1. Sort deadlines
        Arrays.sort(deadlines);

        //2. Initialize variables to keep track of the current deadline index, the current processor index
        //and current time at each processor
        int[] processorTimes = new int[m];
        int currentProcessor = 0;
        int currentJobIndex = 1;
        int maxLateness = 0;

        //3. Loop through all the jobs and assign each job to the next processor (in increaasing order of deadlines)
        //as the deadlines array is sorted
        while (currentJobIndex <= n)
        {
            //Calculate the lateness after the processor is done with processing this job
            int currentLateness = processorTimes[currentProcessor] + 1 - deadlines[currentJobIndex];

            maxLateness = Integer.max(currentLateness,maxLateness);

            //Assign the correct time to the current processor
            processorTimes[currentProcessor] += 1;

            //Move on to the next processor if there are still more processors to go through
            //or go back to the first processor and start assigning from there
            if(currentProcessor < processorTimes.length - 1)
            {
                currentProcessor += 1;
            } else {
                currentProcessor = 0;
            }

            //move on to the next job
            currentJobIndex += 1;
        }

        return maxLateness;
    }

    public static void main(String[] args) {
        int n = 5;
        int m = 2;
        int[] deadlines = { 0, 3, 1, 1, 1, 2 };
        System.out.println(solve(n,m,deadlines));
    }


}
