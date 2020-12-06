package Exam.Midterm2018;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UndominatedSolutions {
    /**
     *  You should implement this method.
     *  @param sol a solution to compare the others to.
     *  @param S a set of solutions
     *  @return all solutions in S that are _not_ dominated by sol.
     */
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

        /*
         * You should not need to change the equals and hashCode methods below. Our tests just use them.
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
}
