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
        double totalTime = 0;
        if(trials < 0){
            return 0;
        }else {
            int x = ((MULTIPLIER * seed + INCREMENT) % MODULUS);
            double val = (double) x / MODULUS;

            var timeToSwitchBoard = timeToGetToSwitchBoard(val);
            totalTime += timeToSwitchBoard;
            int representativeTime = assignRepresentativeTotalTime();
            totalTime += representativeTime;


            DecimalFormat df = new DecimalFormat("#.###");
//          System.out.println(df.format(val));
            System.out.println(totalTime);
            return randomNumberGenerator(x, trials - 1);
        }
    }

    public double timeToGetToSwitchBoard(double val){
        return 3 + ((double) 9/(1024* val * val));
    }

    public int assignRepresentativeTotalTime(){
        String representative = "";
        int randomNumber = (int) (Math.random() * 10);
        System.out.println(randomNumber);
        for(int i = 1; i < 11; i++){
            if(randomNumber == 1 || randomNumber == 2){
                representative = "A";
            }
            if(randomNumber == 3 || randomNumber == 4 || randomNumber == 5){
                representative = "B";
            }
            if(randomNumber == 6){
                representative = "C";
            }
            if(randomNumber == 7 || randomNumber == 8 || randomNumber == 9 || randomNumber == 10){
                representative = "D";
            }
        }
        if(representative.equals("A")){
            return 72;
        }
        if(representative.equals("B")){
            return 96;
        }
        if(representative.equals("C")){
            return 81;
        }
        if(representative.equals("D")){
            return 114;
        }
        return 0;
    }

}