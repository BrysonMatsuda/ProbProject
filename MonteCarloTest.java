import java.text.DecimalFormat;
import java.util.ArrayList;

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
        mean = sum / sortedArray.size();
        median = sortedArray.get(sortedArray.size() / 2) + sortedArray.get((sortedArray.size() + 1) / 2);
        System.out.println(sortedArray);
        System.out.println(mean + " " + median);
    }
}
