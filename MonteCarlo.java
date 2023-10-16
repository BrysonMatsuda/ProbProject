import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

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
            var timeToSwitchBoard = timeToGetToSwitchBoard(val);
            DecimalFormat df = new DecimalFormat("#.###");
//            System.out.println(df.format(val));
            return randomNumberGenerator(x, trials - 1);
        }
    }

    public double timeToGetToSwitchBoard(double val){
        return 3 + ((double) 9/(1024* val * val));
    }

    public String assignRepresentative(){
        String representative = "";
        int randomNumber = (int) (Math.random() * 10);
        System.out.println(randomNumber);
        for(int i = 1; i < 11; i++){
            if(randomNumber == 1 || randomNumber == 2){
                return "A";
            }
            if(randomNumber == 3 || randomNumber == 4 || randomNumber == 5){
                return "B";
            }
            if(randomNumber == 6){
                return "C";
            }
            if(randomNumber == 7 || randomNumber == 8 || randomNumber == 9 || randomNumber == 10){
                return "D";
            }
        }
        return representative;
    }
}