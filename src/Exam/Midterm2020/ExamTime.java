package Exam.Midterm2020;

import Exam.ResitMidterm2019.Dean.Tournament;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ExamTime {
    /**
     *  You should implement this method.
     *  @param n the number of exercises
     *  @param t the time required to create an exercise t_1 through t_n. Note you should only use entries t[1] up to and including t[n].
     *  @param p the penalty per hour for an exercise p_1 through p_n. Note you should only use entries p[1] up to and including p[n].
     *  @return the smallest penalty for creating all n exercises.
     */
    public static int prioritisingExercises(int n, int[] t, int[] p) {
        // TODO
        List<Job> jobs = new ArrayList<>();

        for(int i = 1; i <=n; i++)
        {
            jobs.add(new Job(t[i],p[i]));
        }

        Collections.sort(jobs);

        int currentTime = 0;
        int currentPenalty = 0;

        for(Job job: jobs)
        {
            currentTime += job.createTime;
            currentPenalty += currentTime*job.penalty;
        }

        System.out.println(jobs);
        return currentPenalty;
    }

    public static void main(String[] args) {
        int n = 3;
        int[] t = { 0, 5, 4 ,3};
        int[] p = { 0, 1, 3 ,2};
        System.out.println(prioritisingExercises(n, t, p));
    }

    static class Job implements Comparable<Job>
    {
        int createTime;
        int penalty;

        public Job(int createTime, int penalty) {
            this.createTime = createTime;
            this.penalty = penalty;
        }


        @Override
        public String toString() {
            return "Job{" +
                    "createTime=" + createTime +
                    ", penalty=" + penalty +
                    '}';
        }

        @Override
        public int compareTo(Job o) {
            double thisPenaltyPerHour = (double)  this.penalty/ this.createTime;
            double otherPenaltyPerHour = (double) o.penalty/o.createTime;
            System.out.println(thisPenaltyPerHour);
            System.out.println(otherPenaltyPerHour);
            System.out.println((int) (otherPenaltyPerHour - thisPenaltyPerHour));

            if(otherPenaltyPerHour - thisPenaltyPerHour < 0) return -1;
            else return 1;
        }
    }

}
