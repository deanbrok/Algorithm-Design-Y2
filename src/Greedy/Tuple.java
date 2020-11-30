package Greedy;

public class Tuple implements Comparable<Tuple> {

    public int id;

    public int cost;

    public Tuple(int id, int cost) {
        this.id = id;
        this.cost = cost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Tuple tuple = (Tuple) o;
        return id == tuple.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public int compareTo(Tuple o) {
        int res = Integer.signum(this.cost - o.cost);
        if (res == 0) {
            return Integer.signum(this.id - o.id);
        }
        return res;
    }
}