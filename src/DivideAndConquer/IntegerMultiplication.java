package DivideAndConquer;

public class IntegerMultiplication {

    public static Integer integerMultiplication(Integer i1, Integer i2) {

        String i1String = i1.toString();
        String i2String  = i2.toString();

        Integer i1FirstHalf = (int) Math.ceil((double) i1.toString().length()/2);
        Integer i1SecondHalf =  (int) Math.floor((double) i1.toString().length()/2);
        Integer i2FirstHalf = (int) Math.ceil((double) i2.toString().length()/2);
        Integer i2SecondHalf = (int) Math.floor((double) i2.toString().length()/2);


        return 0;
    }
    public static void main(String[] args) {
        Integer i = 10102;
        System.out.println(Math.floor((double)i.toString().length()/2));
    }
}
