import java.text.DecimalFormat;

public class MonteCarlo{
    private final int MULTIPLIER = 24693;
    private final int INCREMENT = 3517;
    private final int MODULUS = 131072;
    public MonteCarlo(){

    }
    public int randomNumberGenerator(int seed, int trials){
        if(trials < 0){
            return 0;
        }else {
            int x = ((MULTIPLIER * seed + INCREMENT) % MODULUS);
            double val = (double) x / MODULUS;
            DecimalFormat df = new DecimalFormat("#.###");
            System.out.println(df.format(val));
            return randomNumberGenerator(x, trials - 1);
        }
    }

    public double timeToGetToSwitchBoard(int val){
        return 3 + ((double) 9/(1024* val * val));
    }
}