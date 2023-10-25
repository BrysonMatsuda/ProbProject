import java.sql.Array;
import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;

public class MonteCarloTest {
    public static void main(String [] args){
        MonteCarlo monteCarlo = new MonteCarlo();
        System.out.println(monteCarlo.randomNumberGenerator(1000, 500));
        ArrayList<Double> sortedArray = monteCarlo.toSortedArraylistOfWValues(monteCarlo.getWValueList());
        double mean = 0;
        double median = 0;
        double sum = 0;
        for (Double aDouble : sortedArray) {
            sum += aDouble;
        }
        System.out.println(Arrays.toString(monteCarlo.getUValues()));
        mean = sum / sortedArray.size();
        median = (sortedArray.get(sortedArray.size() / 2) + sortedArray.get((sortedArray.size() + 1) / 2)) / 2;
        int arr[] = {1};
        sortedArray.stream().
                sorted()
                .forEach(x -> System.out.println("Trial " +  arr[0]++ +": " +  x));

        System.out.println("Mean: " + mean + " " + "Median: " + median);

    }
}
