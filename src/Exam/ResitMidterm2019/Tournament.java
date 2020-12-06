package Exam.ResitMidterm2019;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Tournament {

    /**
     *  You should implement this method.
     *  @param n the number of participants.
     *  @param a an array of size n+1, containing the account creation a_1 through a_n. You should ignore a[0].
     *  @param b an array of size n+1, containing the interview times b_1 through b_n. You should ignore b[0].
     *  @return The minimum latest end time.
     */
    public static int boardgameTime(int n, int[] a, int[] b) {
        // TODO
        List<Job> jobs = new ArrayList<>();

        for(int i = 1; i <=n; i++)
        {
            jobs.add(new Job(a[i],b[i]));
        }

        int currentEndTime = 0;
        //Sort according to ascending creation time
        Collections.sort(jobs);

        for (Job job: jobs) {
            if (job.createTime > currentEndTime)
            {
                currentEndTime = job.createTime + job.interviewTime;
            }
            else
            {
                currentEndTime = currentEndTime + job.interviewTime;
            }
        }

        System.out.println(jobs);

        return currentEndTime;
    }

    public static void main(String[] args) {
        int n = 2;
        int[] a = { 0, 3, 1 };
        int[] b = { 0, 2, 5 };
        System.out.println(boardgameTime(n, a, b));
    }

    static class Job implements Comparable<Job>
    {
        int createTime;
        int interviewTime;

        public Job(int createTime, int interviewTime) {
            this.createTime = createTime;
            this.interviewTime = interviewTime;
        }

        @Override
        public String toString() {
            return "Job{" +
                    "createTime=" + createTime +
                    ", interviewTime=" + interviewTime +
                    '}';
        }

        @Override
        public int compareTo(Job o) {
            return this.createTime - o.createTime;
        }
    }

}
