/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connectfour.networking;

/**
 *
 * @author Suter Raphael <raphael.suter@stud.hslu.ch>
 *
 * In dieser abstrakten Klasse werden fixe Werte für den Netzwerkverkehr
 * abgelegt. Weil diese Klasse abstrakt ist, kann auf sie und ihre Attribute
 * direkt zugegriffen werden
 */
public abstract class NetworkHelper {

    public static final String SEARCH_SERVER = "Are you a connect4 open server?";
    public static final String CONNECT_TO_SERVER = "I want to play a game";
    public static final int Port = 12345;
}
