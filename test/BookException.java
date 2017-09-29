/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.edu.swin.waa;

/** that is an bookException class to return any exception related to book db or book class
 * with all common functions
 *
 * @author XC8184
 */
public class BookException extends Exception {
    
       public BookException(String mess) { super(mess); }
  public BookException(String message, Throwable cause) { super(message, cause); }
  public BookException(Throwable cause) { super(cause); }

    
}
