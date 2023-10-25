import java.sql.Array;
import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;

public class MonteCarloTest {
    public static void main(String [] args){
        MonteCarlo monteCarlo = new MonteCarlo();
        System.out.println(monteCarlo.randomNumberGenerator(1000, 0));
        ArrayList<Double> sortedArray = monteCarlo.toSortedArraylistOfWValues(monteCarlo.getWValueList());
        double mean = 0;
        double median = 0;
        double sum = 0;
        System.out.println(Arrays.toString(monteCarlo.getUValues()));
        for (Double aDouble : sortedArray) {
            sum += aDouble;
        }
        mean = sum / sortedArray.size();
        int size = sortedArray.size();
        double[] doubleArray = new double[size];

        for (int i = 0; i < size; i++) {
            doubleArray[i] = sortedArray.get(i);
        }
        median = median(doubleArray);
        int arr[] = {1};
        sortedArray.stream().
                sorted()
                .forEach(x -> System.out.println("Trial " +  arr[0]++ +": " +  x));

        System.out.println("Mean: " + mean + " " + "Median: " + median);

    }

    public static double median(double[] m) {
        int middle = m.length/2;
        if (m.length%2 == 1) {
            return m[middle];
        } else {
            return (m[middle-1] + m[middle]) / 2.0;
        }
    }
}
