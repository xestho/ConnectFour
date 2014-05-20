/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package connectfour.networking;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 *
 * @author Suter Raphael <raphael.suter@stud.hslu.ch>
 */
public class TCPCommunicator {
    
    private InetAddress opponentAddr;
    
    public TCPCommunicator(String ipAddressOponent) {
        String[] stringArray = ipAddressOponent.split("/");
        try{
            opponentAddr = InetAddress.getByName(stringArray[1]);
        }catch(UnknownHostException e){
            e.printStackTrace();
        }   
    }
    
    public void sendThrow(int column){
        
    }
    
    public int receiveThrow(){
        return 0;
    }

    public InetAddress getOpponentAddr() {
        return opponentAddr;
    }

    public void setOpponentAddr(InetAddress opponentAddr) {
        this.opponentAddr = opponentAddr;
    }       
}
