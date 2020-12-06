package Exam.Midterm2018;

import java.io.InputStream;
import java.util.*;

public class PuttingItAllTogether {


    /**
     *  You should implement this method.
     *  @param S the list of solutions to get the pareto optima from.
     *  @return the list of pareto optimal solutions.
     */
    public static List<Solution> getParetoOptima(List<Solution> S)
    {
        // TODO
        Collections.sort(S, Comparator.comparingInt(x -> x.numberOfHours));
        return recursivelySolve(S);
    }

    public static List<Solution> recursivelySolve(List<Solution> S)
    {
        if(S.size() == 1) return S;

        int middle = S.size()/2;

        //Divide and recurse into the solution
        List<Solution> left = recursivelySolve(new ArrayList<>(S.subList(0,middle)));

        List<Solution> right = recursivelySolve(new ArrayList<>(S.subList(middle,S.size())));


        Solution highestQualityLeft = left.get(left.size() - 1);
        List<Solution> undominatedRight = undominatedBy(highestQualityLeft, right);
        left.addAll(undominatedRight);

        List<Solution> result = left;

        return result;

    }

    public static List<Solution> undominatedBy(Solution sol, List<Solution> S) {
        // TODO
        List<Solution> result = new ArrayList<>();
        for(Solution s: S)
        {
            if(!(sol.numberOfHours < s.numberOfHours && sol.quality >= s.quality))
            {
                result.add(s);
            }
        }
        return result;
    }

    static class Solution {

        int numberOfHours;

        int quality;

        public Solution(int numberOfHours, int quality) {
            this.numberOfHours = numberOfHours;
            this.quality = quality;
        }

        @Override
        public String toString() {
            return "Solution{" +
                    "numberOfHours=" + numberOfHours +
                    ", quality=" + quality +
                    '}';
        }

        /*
                        You should not need the equals and hashcode method below, we just use them in the test.
                     */
        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;
            if (o == null || getClass() != o.getClass())
                return false;
            Solution that = (Solution) o;
            return numberOfHours == that.numberOfHours && quality == that.quality;
        }

        @Override
        public int hashCode() {
            return Objects.hash(numberOfHours, quality);
        }
    }

    public static void main(String[] args) {
        List<Solution> list = new ArrayList<>();
//        list.add(new Solution(1, 5));
//        list.add(new Solution(6, 4));
//        list.add(new Solution(3, 3));
//        list.add(new Solution(4, 7));
//        list.add(new Solution(5,10));
//        list.add(new Solution(2,1));

        list.add(new Solution(10,1));
        list.add(new Solution(9, 7));
        list.add(new Solution(1, 6));
        list.add(new Solution(5, 3));
        List<Solution> result = getParetoOptima(list);
        System.out.println(result);
    }


}
