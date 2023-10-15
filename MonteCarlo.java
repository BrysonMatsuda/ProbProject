import java.text.DecimalFormat;

public class MonteCarlo{
    private final int multiplier = 24693;
    private final int increment = 3517;
    private final int modulus = 131072;
    public MonteCarlo(){

    }
    public int randomNumberGenerator(int seed, int trials){
        if(trials < 0){
            return 0;
        }else {
            int x = ((multiplier * seed + increment) % modulus);
            double val = (double) x / modulus;
            DecimalFormat df = new DecimalFormat("#.###");
            System.out.println(df.format(val));
            return randomNumberGenerator(x, trials - 1);
        }
    }

    public int randomVariableGenerator(){
        return 0;
    }
}