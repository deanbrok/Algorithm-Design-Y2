package DivideAndConquer;

public class Sorting {
    public static void merge(double[] list1, double[] list2, double[] originalList) {
        int i=0, j = 0;
        while (i + j < originalList.length) {
            if(j >= list2.length || (i < list1.length && (list1[i] - list2[j]) < 0)){
                originalList[i+j] = list1[i++];
            } else {
                originalList[i+j] = list2[j++];
            }
        }
    }

    public static void mergeSort(double[] originalList) {
        int length = originalList.length;
        if(length < 2) return;
        int middle = originalList.length/2;
        double[] list1 = new double[middle];
        double[] list2;
        if(originalList.length % 2 == 0) {
            list2 = new double[middle];
        } else {
            list2 = new double[middle + 1];
        }
        //add elements of list 1 to the array
        for (int i = 0; i < middle; i++) {
            list1[i] = originalList[i];
        }
        for(int i = middle; i < originalList.length; i++) {
            list2[i - middle] = originalList[i];
        }
        System.out.println("List1:");
        for(double i: list1) {
            System.out.println(i);
        }
        System.out.println("List2:");
        for(double i: list2) {
            System.out.println(i);
        }
        System.out.println();
        System.out.println();
        mergeSort(list1);
        mergeSort(list2);
        merge(list1,list2,originalList);
    }

    public static void quickSort(double[] list, int a, int b) {
        if (a >= b) return;
        int left = a;
        int right = b - 1;
        int pivot = b;
        double temp;

        while (left <= right) {
            while (left <= right && list[left] < list[pivot]) left++;
            while (left <= right && list[right] > list[pivot]) right--;
            if (left <= right) {
                temp = list[left];
                list[left] = list[right];
                list[right] = temp;
                left++;
                right--;
            }

            //Put the pivot in its rightful place
            temp = list[left];
            list[left] = list[pivot];
            list[pivot] = temp;

            quickSort(list, a, left - 1);
            quickSort(list, left + 1, b);
        }
    }

    public static void main(String[] args) {
        // write your code here
        double[] list1 = new double[]{1,3,10,4};
        double[] list2 = new double[]{3,4,7};
        double[] original = new double[list1.length + list2.length];

        mergeSort(list1);
        for(double i: list1) {
            System.out.println(i);
        }


    }

}
