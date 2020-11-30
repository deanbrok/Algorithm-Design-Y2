package DivideAndConquer;

abstract class Equation {

    public abstract long evaluate(long x);
}

class QuadraticEquation extends Equation {

    private long secondPolynomial;

    private long firstPolynomial;

    private long constant;

    /**
     * Constructs a quadratic equation in the form of:
     * f(x) = secondPolynomial * x^2 + firstPolynomial * x + constant
     *
     * @param secondPolynomial the parameter for the second degree polynomial
     * @param firstPolynomial the parameter for the first degree polynomial
     * @param constant the parameter for the constant
     */
    public QuadraticEquation(long secondPolynomial, long firstPolynomial, long constant) {
        this.secondPolynomial = secondPolynomial;
        this.firstPolynomial = firstPolynomial;
        this.constant = constant;
    }

    /**
     * Evaluates the equation with the given x.
     * @param x value used to evaluate
     * @return the result of the equation1
     */
    public long evaluate(long x) {
        return secondPolynomial * x * x + firstPolynomial * x + constant;
    }
}
public class Smol {
    /**
     * Finds the x coordinate with the smallest distance between two linear equations, by recursively evaluating the median of the range and that integer + 1.
     * Depending on the value, a new evaluation is made with a smaller range to find the x coordinate with the smallest distance.
     * @param e1 the first equation to evaluate
     * @param e2 the second equation to evaluate
     * @param low the lower boundary of the range
     * @param high the upper boundary of the range
     * @return the x coordinate with the minimum difference between e1 and e2
     */
    public static long findMin(Equation e1, Equation e2, long low, long high) {
        if(high - low <= 0) return high;
        long median = (high + low)/2 ;
        System.out.println(low);
        System.out.println(median);
        System.out.println(high);

        long medianDifference = e2.evaluate(median) -  e1.evaluate(median);
        long medianDifference1 = e2.evaluate(median + 1) - e1.evaluate(median + 1) ;

        if(medianDifference < 0) medianDifference = - medianDifference;
        if(medianDifference1 < 0) medianDifference1 = - medianDifference1;

        System.out.println(medianDifference);
        System.out.println(medianDifference1);
        System.out.println();

        if(medianDifference < medianDifference1) {
            return findMin(e1,e2,low,median);
        } else {
            return findMin(e1,e2,median + 1, high);
        }
    }

    public static void main(String[] args) {
        Equation eq1 = new QuadraticEquation(0, 8, -240);
        Equation eq2 = new QuadraticEquation(0, 6, -156);
        System.out.println(findMin(eq1, eq2, 0, 100));
    }
}












/*
 if(e1 == null || e2 == null) return
         long result = low;
         for(long i = low; i <= high; i++) {
         long resultEva = e1.evaluate(result) - e2.evaluate(result);
         long currentEva = e1.evaluate(i) - e2.evaluate(i);

         if(resultEva < 0) resultEva = - resultEva;
        if(currentEva <0) currentEva = - currentEva;

        if(currentEva < resultEva) result = i;
        }

        return result;

 */