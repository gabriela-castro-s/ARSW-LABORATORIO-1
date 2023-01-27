package edu.eci.arsw.threads;

import edu.eci.arsw.math.PiDigits;

public class ThreadDigits extends Thread{
    public int start;
    public int count;
    public int start1;
    public int start2;

    public ThreadDigits(int start, int count, int start1){
        this.start2 = start;
        this.start = start;
        this.start1 = start;
        this.count = count;
    }

    public void run(){
        double sum = 0;
        for(int i = 0; i < count; i++){
            if(i% PiDigits.DigitsPerSum == 0){
                sum = 4 * PiDigits.sum(1, start);
                double v = 2 * PiDigits.sum(4, start);
                double v1 = PiDigits.sum(5, start);
                double v2 = PiDigits.sum(6, start);
                start += PiDigits.DigitsPerSum;

            }
            
            sum = 16 * (sum - Math.floor(sum));
            PiDigits.digits[start2-start1+1] = (byte)sum;
        }
    }

}
