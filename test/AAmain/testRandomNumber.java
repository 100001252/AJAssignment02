/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AAmain;

import java.security.SecureRandom;

/**
 *
 * @author feiyeyuji
 */
public class testRandomNumber {
    public static void main(String[] args){
        SecureRandom randomNumber = new SecureRandom();
        for(int i=0;i<100;i++){
            int carNumber = 2 + randomNumber.nextInt(4);
            System.out.println(carNumber);
        }
            
    }
}
