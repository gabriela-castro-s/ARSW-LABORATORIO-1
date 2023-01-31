package edu.eci.arsw.threads;

import edu.eci.arsw.math.PiDigits;

public class ThreadDigits extends Thread{
    private final int numeroInicial;
    private final int cantidad;
    private byte[] respuesta;

    public ThreadDigits(int numA, int numB){
        numeroInicial = numA;
        cantidad = numB;
    }

    /**
     * corre el hilo necesario para encontrar los digitos de pi requeridos
     */
    public void run(){
        respuesta = PiDigits.getDigits(numeroInicial, cantidad);
    }

    public byte[] traerResultado(){
        return respuesta;
    }

}
