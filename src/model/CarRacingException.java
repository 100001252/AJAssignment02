/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author XC8184
 */
public class CarRacingException extends Exception {

    public CarRacingException(String mess) {
        super(mess);
    }

    public CarRacingException(String message, Throwable cause) {
        super(message, cause);
    }

    public CarRacingException(Throwable cause) {
        super(cause);
    }

}
