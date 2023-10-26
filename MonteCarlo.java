import java.lang.reflect.Array;
import java.text.DecimalFormat;
import java.util.*;

public class MonteCarlo{
    private final int MULTIPLIER = 24693;
    private final int INCREMENT = 3517;
    private final int MODULUS = 131072;
    public ArrayList<Double> wValueList = new ArrayList<>();
    private double[] uValues = new double[3];
    public MonteCarlo(){

    }

    public ArrayList<Double> getWValueList() {
        return wValueList;
    }
    public double[] getUValues(){
        return this.uValues;
    }
    public long randomNumberGenerator(long seed, int trials){
        double totalTime = 0;
        if(trials > 500){
            return 0;
        }else {
            int callNumber = 0; //starts at 0;
            double uValue = 0;
            long xValue = 0;
            xValue = ((MULTIPLIER * seed + INCREMENT) % MODULUS);
            uValue = (double) xValue / MODULUS;
            while (callNumber < 3) {
                if(trials == 50|| trials == 51 || trials == 52) {
                    if(trials == 50){
                        uValues[0] = uValue;
                    }if(trials == 51){
                        uValues[1] = uValue;
                    }else{
                        uValues[2] = uValue;
                    }
                }
                boolean isSuccessful = isSuccessful();
                if (!isSuccessful) {
                    totalTime += 90;
                }
                if(isSuccessful){
                    var timeToSwitchBoard = timeToGetToSwitchBoard(uValue * 60);
                    if(timeToSwitchBoard > 90){
                        timeToSwitchBoard = 90;
                    }
                    totalTime += timeToSwitchBoard;
                    break;
                }
                callNumber++;
            }
            if(callNumber < 3) {
                totalTime += (callNumber + 1) * 3; //num * 3 is the time of first call given number of times through switchboard
                totalTime += 2 * (callNumber); //hangup time for failed calls
                totalTime += 5; //+5 is for time after success to get into contact with representative
                int representativeTime = assignRepresentativeTotalTime();
                totalTime += representativeTime; //total time on phone with representative
                DecimalFormat df = new DecimalFormat("#.###");
                //System.out.println("Total Time: " + df.format(toMinutes(totalTime)));
                wValueList.add(totalTime);
            }else{
                totalTime += 9; //3 first calls
                totalTime += 6; //3 failed calls

                DecimalFormat df = new DecimalFormat("#.###");
                //System.out.println("Total Time: " + df.format(toMinutes(totalTime)));
                wValueList.add(totalTime);
            }


            DecimalFormat df = new DecimalFormat("#.###");
//          System.out.println(df.format(uValue));
            trials += 1;
            return randomNumberGenerator(xValue, trials);
        }
    }

    public double timeToGetToSwitchBoard(double val){
        System.out.println(val);
        System.out.println((8) * Math.pow(val, .666));
        return ((double) 8 * Math.pow(val, .66667));
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