/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.math;
import java.io.IOException;

/**
 *
 * @author hcadavid
 */
public class Main {

    public static void main(String[] args) throws InterruptedException, IOException {
        //System.out.println(bytesToHex(PiDigits.getDigits(0, 10, 3)));
        //System.out.println(bytesToHex(PiDigits.getDigits(1, 100, 5)));
        //Hallar cantidad de núcleos
        //int nucleos = Runtime.getRuntime().availableProcessors();
        //Con 1 hilo
        //System.out.println(bytesToHex(PiDigits.getDigits(1, 100000, 1)));
        //Con tantos hilos como núcleos
        //System.out.println(bytesToHex(PiDigits.getDigits(1, 100000, nucleos)));
        //Con tantos hilos como el doble de núcleos
        //System.out.println(bytesToHex(PiDigits.getDigits(1, 100000, nucleos)));
        //Con tantos 200 hilos
        //System.out.println(bytesToHex(PiDigits.getDigits(1, 100000, 200)));
        //Con tantos 500 hilos
        System.out.println(bytesToHex(PiDigits.getDigits(1, 100000, 500)));


    }

    private final static char[] hexArray = "0123456789ABCDEF".toCharArray();

    public static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        StringBuilder sb=new StringBuilder();
        for (int i=0;i<hexChars.length;i=i+2){
            //sb.append(hexChars[i]);
            sb.append(hexChars[i+1]);            
        }
        return sb.toString();
    }

}
