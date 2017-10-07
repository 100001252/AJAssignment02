/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view2;
import helper.DebugLog;
import java.io.IOException;
/**
 *
 * @author feiyeyuji
 */
public class debugTest {
    public static void main(String[] args){
        DebugLog buglog = new DebugLog();
        try{
            buglog.appendData("First log");
        }catch(IOException ie){
            System.err.println("dubug log failed");
        }
            
    }
    
}
