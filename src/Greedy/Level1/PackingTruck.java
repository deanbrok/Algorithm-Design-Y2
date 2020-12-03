package Greedy.Level1;

public class PackingTruck {

    /*
    As the owner of a shipping company, you won a large contract requiring you to ship a certain amount of boxes n
to a certain destination. The contract gives the weight of the boxes in order of their arrival. This is also the order
in which the boxes must be shipped. To maximize profit, you want to minimize the number of trucks used. Each truck can
carry boxes up to a total of weight W. Every box i with 1≤i≤n has a weight wi ≤ W .

Implement an algorithm that determines the minimal amount of trucks needed to ship the boxes to the destination.

If we can carry at most 48 weight units in one truck (W=48) then the following shipment of boxes should result in
an output of 3.
41 29 12 26

     */
    public static /**
     * @param n the number of packages
     * @param weights the weights of all packages 1 through n. Note that weights[0] should be ignored!
     * @param maxWeight the maximum weight a truck can carry
     * @return the minimal number of trucks required to ship the packages _in the given order_.
     */
    int minAmountOfTrucks(int n, int[] weights, int maxWeight)
    {

        if(n == 0) return 0;

        int currentPackage = 1;
        int currentTruckWeight = 0;
        int numberOfTrucks = 1;

        while (currentPackage <= n)
        {
            if(currentTruckWeight + weights[currentPackage] <= maxWeight)
            {
                currentTruckWeight += weights[currentPackage];
            } else
            {
                numberOfTrucks += 1;
                currentTruckWeight = weights[currentPackage];
            }
            currentPackage += 1;
        }
        return  numberOfTrucks;

    }

    public static void main(String[] args) {
        int n = 4;
        int[] weights = { 0, 41, 29, 12, 26 };
        int maxWeight = 48;
        System.out.println(PackingTruck.minAmountOfTrucks(n,weights,maxWeight));
    }
}
