package Exam.Midterm2019;

import java.util.*;

public class AttendingPresentations {
    /**
     *  You should implement this method.
     *  @param n the number of speakers
     *  @param presenterNames the names of the presenters p_1 through p_n. Note you should only use entries presenterNames[1] up to and including presenterNames[n].
     *  @param startTimes the start times of the presentations s_1 through s_n. Note you should only use entries startTimes[1] up to and including startTimes[n].
     *  @param endTimes the end times of the presentations e_1 through e_n. Note you should only use entries endTimes[1] up to and including endTimes[n].
     *  @return a largest possible set of presenters whose presentation we can attend.
     */
    public static Set<String> whatPresentations(int n, String[] presenterNames, int[] startTimes, int[] endTimes) {
        // TODO
        List<Presentation> presentations = new ArrayList<>();
        for(int i = 1; i <= n; i ++)
        {
            presentations.add(new Presentation(presenterNames[i],startTimes[i],endTimes[i]));
            System.out.println(presentations.get(i - 1));
        }

        Collections.sort(presentations);

        Set<String> result = new HashSet<>();
        int currentEndTime = 0;

        for(Presentation presentation: presentations)
        {
            if(presentation.startTime >= currentEndTime)
            {
                currentEndTime = presentation.endTime;
                result.add(presentation.presenter);
            }
        }
        
        return result;


    }

    public static void main(String[] args) {
        int n = 4;
        String[] presenters = { "", "Mia", "Phoenix", "Maya", "Miles" };
        int[] startTimes = { 0, 12, 1, 17, 13 };
        int[] endTimes = { 0, 18, 11, 20, 15 };
        Set<String> result = new HashSet<>();
        result.add("Phoenix");
        result.add("Maya");
        result.add("Miles");
        System.out.println(whatPresentations(n, presenters, startTimes, endTimes));
    }
}

class Presentation implements Comparable<Presentation>{
    String presenter;
    int startTime;
    int endTime;

    public Presentation(String presenter, int startTime, int endTime) {
        this.presenter = presenter;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "Presentation{" +
                "presenter='" + presenter + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }

    @Override
    public int compareTo(Presentation o) {
        return this.endTime - o.endTime;
    }
}