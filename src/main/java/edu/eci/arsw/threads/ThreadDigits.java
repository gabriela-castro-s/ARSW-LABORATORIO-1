package edu.eci.arsw.threads;

import edu.eci.arsw.math.PiDigits;

public class ThreadDigits extends Thread{
    private int numeroInicial, cantidad;
    private byte[] respuesta;

    public ThreadDigits(int numA, int numB){
        numeroInicial = numA;
        cantidad = numB;
    }

    public void run(){
        respuesta = PiDigits.getDigits(numeroInicial, cantidad);
    }

    public byte[] traerResultado(){
        return respuesta;
    }

}
