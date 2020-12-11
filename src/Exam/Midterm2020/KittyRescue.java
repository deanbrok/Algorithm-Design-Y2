package Exam.Midterm2020;

public class KittyRescue {
    /**
     * Jump evaluation as given in the assignment description.
     * There should be no need to modify this function!
     */
    public static int evaluateClimb(int height1, int height2) {
        if (height2 <= height1) {
            return height1 - height2 + 1;
        } else {
            int diff = height2 - height1;
            return -(diff * diff);
        }
    }

    /**
     * You should implement this method.
     *
     * @param n the number of cats that are lost in the woods
     * @param h the height of the trees h_1 through h_n that the cats are in. Note you should only use entries h[1] up to and including h[n].
     * @return the score of the best possible rescue mission.
     */
    public static int findBestRescueMissionBruteForce(int n, int[] h) {
        // TODO
        int currentBestScore = Integer.MIN_VALUE;
        for(int i = 1; i <= n - 1; i++)
        {
            int currentScore = evaluateClimb(h[i],h[i+1]);
            if(currentScore > currentBestScore) currentBestScore = currentScore;
            for(int j = i + 1; j <= n - 1 ; j ++)
            {
                currentScore += evaluateClimb(h[j],h[j+1]);
                if(currentScore > currentBestScore) currentBestScore = currentScore;
            }
        }

        return currentBestScore;
    }

    /**
     * You should implement this method.
     *
     * @param n the number of cats that are lost in the woods
     * @param h the height of the trees h_1 through h_n that the cats are in. Note you should only use entries h[1] up to and including h[n].
     * @return the score of the best possible rescue mission.
     */
    public static int findBestRescueMissionDivideAndConquer(int n, int[] h) {
        // TODO

        int bestScore = Integer.MIN_VALUE;
        return helpFindBest(h,1,n, bestScore);
    }

    public static int helpFindBest(int[] h, int start,int end, int bestScore) {
        int middle = (start + end) / 2;

        if (start == end) return 0;

        int left = helpFindBest(h, start, middle, bestScore);
        int right = helpFindBest(h, middle + 1, end, bestScore);
        int scoreInTheMiddle = evaluateClimb(h[middle], h[middle + 1]);

        int currentScore = 0;

        if (left > 0) currentScore += left;
            currentScore += scoreInTheMiddle;
            currentScore += right;


        if(currentScore > bestScore) bestScore = currentScore;

        return bestScore;

    }

    public static void main(String[] args) {
        int n = 8;
        int[] h = { 0, 6, 5, 10, 12, 11, 13, 10, 2 };
        System.out.println(findBestRescueMissionBruteForce(n, h));
        System.out.println(findBestRescueMissionDivideAndConquer(n,h));
    }
}
