/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.eci.arsw.threads;
import java.lang.Thread;
/**
 *
 * @author hcadavid
 */
public class CountThread extends Thread{
    private int num1;
    private int num2;
    public CountThread(int num1, int num2){
        this.num1 = num1;
        this.num2 = num2;
    }
    public void run(){
        System.out.println("thread is running...");
        for(int i = num1+1; i < num2; i++){
            System.out.println(i);
        }
    }


    
}
