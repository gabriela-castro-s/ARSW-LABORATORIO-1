package edu.eci.arsw.math;

import edu.eci.arsw.threads.ThreadDigits;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;

///  <summary>
///  An implementation of the Bailey-Borwein-Plouffe formula for calculating hexadecimal
///  digits of pi.
///  https://en.wikipedia.org/wiki/Bailey%E2%80%93Borwein%E2%80%93Plouffe_formula
///  *** Translated from C# code: https://github.com/mmoroney/DigitsOfPi ***
///  </summary>
public class PiDigits {

    public static int DigitsPerSum = 8;
    private static final double Epsilon = 1e-17;

    public static byte[] digits;

    public static byte[] getDigits(int start, int count, int cantidadHilos) throws InterruptedException, IOException {
        int cantidadIndividual = count/cantidadHilos;
        System.out.println("Cantidad Individual"+cantidadIndividual);
        int cantidadPendiente = count - (cantidadIndividual*cantidadHilos);
        System.out.println("Cantidad Pendiente"+cantidadPendiente);
        ArrayList<ThreadDigits> arreglohilos = new ArrayList<>();
        for(int i=0; i<cantidadHilos; i++){
            if(i==0 && cantidadPendiente!=0){
                arreglohilos.add(new ThreadDigits(start, cantidadIndividual+cantidadPendiente));
                start = start + cantidadIndividual+cantidadPendiente;
            }
            else{
                arreglohilos.add(new ThreadDigits(start, cantidadIndividual));
                start = start + cantidadIndividual;
            }
        }

        //Iniciar Hilos
        for(int i=0; i<arreglohilos.size(); i++){
            arreglohilos.get(i).start();
        }
        //Esperar a que todos terminene
        for(int i=0; i<arreglohilos.size(); i++){
            arreglohilos.get(i).join();
        }
        //Generar el numero
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream( );
        for(int i=0; i<arreglohilos.size(); i++){
            outputStream.write(arreglohilos.get(i).traerResultado());
        }
        return outputStream.toByteArray( );
    }

    
    /**
     * Returns a range of hexadecimal digits of pi.
     * @param start The starting location of the range.
     * @param count The number of digits to return
     * @return An array containing the hexadecimal digits.
     */
    public static byte[] getDigits(int start, int count) {
        if (start < 0) {
            throw new RuntimeException("Invalid Interval");
        }

        if (count < 0) {
            throw new RuntimeException("Invalid Interval");
        }

        byte[] digits = new byte[count];
        double sum = 0;

        for (int i = 0; i < count; i++) {
            if (i % DigitsPerSum == 0) {
                sum = 4 * sum(1, start)
                        - 2 * sum(4, start)
                        - sum(5, start)
                        - sum(6, start);

                start += DigitsPerSum;
            }

            sum = 16 * (sum - Math.floor(sum));
            digits[i] = (byte) sum;
        }

        return digits;
    }

    /// <summary>
    /// Returns the sum of 16^(n - k)/(8 * k + m) from 0 to k.
    /// </summary>
    /// <param name="m"></param>
    /// <param name="n"></param>
    /// <returns></returns>
    private static double sum(int m, int n) {
        double sum = 0;
        int d = m;
        int power = n;

        while (true) {
            double term;

            if (power > 0) {
                term = (double) hexExponentModulo(power, d) / d;
            } else {
                term = Math.pow(16, power) / d;
                if (term < Epsilon) {
                    break;
                }
            }

            sum += term;
            power--;
            d += 8;
        }

        return sum;
    }

    /// <summary>
    /// Return 16^p mod m.
    /// </summary>
    /// <param name="p"></param>
    /// <param name="m"></param>
    /// <returns></returns>
    private static int hexExponentModulo(int p, int m) {
        int power = 1;
        while (power * 2 <= p) {
            power *= 2;
        }
        int result = 1;

        while (power > 0) {
            if (p >= power) {
                result *= 16;
                result %= m;
                p -= power;
            }

            power /= 2;

            if (power > 0) {
                result *= result;
                result %= m;
            }
        }

        return result;
    }

}
