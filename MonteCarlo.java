import java.lang.reflect.Array;
import java.text.DecimalFormat;
import java.util.*;

public class MonteCarlo{
    private final int MULTIPLIER = 24693;
    private final int INCREMENT = 3517;
    private final int MODULUS = 131072;
    public ArrayList<Double> wValueList = new ArrayList<>();
    public MonteCarlo(){

    }

    public ArrayList<Double> getWValueList() {
        return wValueList;
    }

    public long randomNumberGenerator(long seed, int trials){
        double totalTime = 0;
        if(trials < 0){
            return 0;
        }else {
            int numCalls = 0; //starts at 0;
            double uValue = 0;
            long xValue = 0;
            while (numCalls < 3) {
                xValue = ((MULTIPLIER * seed + INCREMENT) % MODULUS);
                uValue = (double) xValue / MODULUS;
//              System.out.println(trials + ": " + uValue);
                boolean isSuccessful = isSuccessful();
                if (!isSuccessful) {
                    var timeToSwitchBoard = 90.0;
                    totalTime += timeToSwitchBoard;
                }

                if(isSuccessful){
                    var timeToSwitchBoard = timeToGetToSwitchBoard(uValue);
                    totalTime += timeToSwitchBoard;
                    break;
                }
                numCalls++;
            }
            if(numCalls < 3) {
                totalTime += numCalls * 3; //num * 3 is the time of first call given number of times through switchboard
                totalTime += 2 * (numCalls-1); //hangup time for failed calls
                totalTime += 5; //+5 is for time after success to get into contact with representative
                int representativeTime = assignRepresentativeTotalTime();
                totalTime += representativeTime; //total time on phone with representative
                DecimalFormat df = new DecimalFormat("#.###");
                System.out.println("Total Time: " + df.format(toMinutes(totalTime)));
                wValueList.add(totalTime);
            }else{
                totalTime += 9; //3 first calls
                totalTime += 6; //3 failed calls

                DecimalFormat df = new DecimalFormat("#.###");
                System.out.println("Total Time: " + df.format(toMinutes(totalTime)));
                wValueList.add(totalTime);
            }


            DecimalFormat df = new DecimalFormat("#.###");
//          System.out.println(df.format(uValue));
            return randomNumberGenerator(xValue, trials - 1);
        }
    }

    public double timeToGetToSwitchBoard(double val){
        return ((double) 9/(1024* val * val));
    }

    public int assignRepresentativeTotalTime(){
        String representative = "";
        int randomNumber = (int) (Math.random() * 10 + 1);
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

    public boolean isSuccessful(){
        Random rand = new Random();
        int n = rand.nextInt(10000)+1;
        if(n<=2296){

            return true;
        }
        return false;
    }

    public double toMinutes(double seconds){
        return seconds / 60;
    }

    public ArrayList<Double> toSortedArraylistOfWValues(ArrayList<Double> wValueList){
        Collections.sort(wValueList);
        return wValueList;
    }
}