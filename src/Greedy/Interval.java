package Greedy;

public class Interval implements Comparable<Interval> {

    public int start;

    public int end;

    public boolean labeled;

    public Interval(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Interval)) return false;
        Interval interval = (Interval) o;
        return start == interval.start &&
                end == interval.end;
    }


    @Override
    public int compareTo(Interval o) {
        int res = Integer.signum(this.start - o.start);
        if (res == 0) {
            return Integer.signum(this.end - o.end);
        }
        return res;
    }

}
