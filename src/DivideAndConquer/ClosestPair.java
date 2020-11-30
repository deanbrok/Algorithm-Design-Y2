package DivideAndConquer;


import java.util.*;

/**
 * Class representing a 2D point.
 */
class Point {

    public double x;

    public double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}

/**
 * Useful methods for this assignment.
 */
class Util {

    /**
     * Takes two points and computes the euclidean distance between the two points.
     *
     * @param point1 - first point.
     * @param point2 - second point.
     * @return euclidean distance between the two points.
     * @see <a href="https://en.wikipedia.org/wiki/Euclidean_distance">https://en.wikipedia.org/wiki/Euclidean_distance</a>
     */
    public static double distance(Point point1, Point point2) {
        return Math.sqrt(Math.pow(point1.x - point2.x, 2.0D) + Math.pow(point1.y - point2.y, 2.0D));
    }

    /**
     * Takes a list of points and sorts it on x (ascending).
     *
     * @param points - points that need to be sorted.
     */
    public static void sortByX(List<Point> points) {
        Collections.sort(points, Comparator.comparingDouble(point -> point.x));
    }

    /**
     * Takes a list of points and sorts it on y (ascending) .
     *
     * @param points - points that need to be sorted.
     */
    public static void sortByY(List<Point> points) {
        Collections.sort(points, Comparator.comparingDouble(point -> point.y));
    }

    /**
     * Takes a list of points and returns the distance between the closest pair.
     * This is done by brute forcing.
     *
     * @param points - list of points that need to be considered.
     * @return smallest pair-wise distance between points.
     */
    public static double bruteForce(List<Point> points) {
        int size = points.size();
        if (size <= 1)
            return Double.POSITIVE_INFINITY;
        double bestDist = Double.POSITIVE_INFINITY;
        for (int i = 0; i < size - 1; i++) {
            Point point1 = points.get(i);
            for (int j = i + 1; j < size; j++) {
                Point point2 = points.get(j);
                double distance = Util.distance(point1, point2);
                if (distance < bestDist)
                    bestDist = distance;
            }
        }
        return bestDist;
    }
}

public class ClosestPair {
    /**
     * Takes a list of points and returns the distance between the closest pair.
     * This is done with divide and conquer.
     *
     * @param points
     *     - list of points that need to be considered.
     * @return smallest pair-wise distance between points.
     */
    public static double closestPair(List<Point> points) {
        if(points.size() <= 1) return Double.POSITIVE_INFINITY;
        List<Point> pointsX = new ArrayList<>(points);
        Util.sortByX(pointsX);
        List<Point> pointsY = new ArrayList<>(points);
        Util.sortByY(pointsY);
        System.out.println("PointsX: " + pointsX.toString());
        System.out.println("PointsY: " + pointsY.toString());
        closestPairRec(pointsX,pointsY);

        return closestPairRec(pointsX,pointsY);
    }

    public static double closestPairRec(List<Point> pointsX, List<Point> pointsY) {
        if(pointsX.size() <= 3) {
            return Util.bruteForce(pointsX);
        }
        int size = pointsX.size();
        int middle = size/2;

        //Construct Q_x and R_x
        List<Point> q_x = pointsX.subList(0,middle);
        List<Point> r_x = pointsX.subList(middle,size);
        //Construct Q_y and R_y
        List<Point> q_y = new ArrayList<>();
        List<Point> r_y = new ArrayList<>();
        for(Point point: pointsY) {
            if(q_x.contains(point)) {
                q_y.add(point);
            }
            if(r_x.contains(point)) {
                r_y.add(point);
            }
        }

        System.out.println("Q_x: " + q_x.toString());
        System.out.println("Q_y: " + q_y.toString());
        System.out.println("R_x: " + r_x.toString());
        System.out.println("R_y: " + r_y.toString());

        double delta1 = closestPairRec(q_x,q_y);
        double delta2 = closestPairRec(r_x,r_y);
        double delta = Math.min(delta1,delta2);

        Point L = new Point(q_x.get(q_x.size() - 1).x,0);

        System.out.println("delta: " + delta);
        System.out.println("L: " + L);
        List<Point> pointsInMargin = new ArrayList<>();


        for(Point point: pointsY) {
            if(Util.distance(point,L) <= delta){
                pointsInMargin.add(point);
            }
        }

        System.out.println("pointsInMargin: " + pointsInMargin);
        double resultMargin = 0;
        if(pointsInMargin.size() < 11){
            resultMargin = Util.bruteForce(pointsInMargin.subList(0,pointsInMargin.size()));
        }
        else {
            resultMargin = Util.bruteForce(pointsInMargin.subList(0,11));
        }
        if (resultMargin < delta) return resultMargin;
        else return delta;
    }



    public static void main(String[] args) {
        List<Point> points = new ArrayList<>();
        points.add(new Point(2, 3));
        points.add(new Point(12, 30));
        points.add(new Point(40, 50));
        //points.add(new Point(4,100));
        points.add(new Point(5, 1));
        //points.add(new Point(6,3));
        points.add(new Point(12, 10));
        points.add(new Point(3, 4));
        System.out.println(ClosestPair.closestPair(points));
    }
}
