package connectfour.ui.welcome;

import connectfour.ki.KI;
import connectfour.model.DefaultConfiguration;
import connectfour.model.Player;
import connectfour.model.RandomStarter;
import connectfour.networking.UDPServer;
import connectfour.networking.UDPClient;
import connectfour.networking.UDPGetIp;
import connectfour.ui.game.GameController;
import connectfour.ui.searchServer.SearchServerController;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

/**
 * Diese Klasse kontrolliert das Einstiegs GUI
 * @author markus.birrer.01@stud.hslu.ch
 */
public class WelcomeController {

    private WelcomeModel model;
    private WelcomeView view;

    /**
     * Konstruktor des WelcomeControllers
     */
    public WelcomeController() {
        this.model = new WelcomeModel(); // Erzeugen des WelcomeModel
        this.view = new WelcomeView(); // Erzeugen der WelcomeView

        this.view.addActionListenerSinglePlayer(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    
                    // Daten setzen
                    model.setNumberOfColumns(view.getColumns());
                    model.setNumberOfRows(view.getRows());
                    model.setPlayComputer(true);
                    model.setPlayHuman(false);
                    
                    KI ki = view.getStrength();
                    
                    // View schliessen
                    view.close();
                    
                    // Spieler erzeugen
                    Player startPlayer = Player.createLocalPlayer();
                    Player otherPlayer = Player.createComputerPlayer(ki, model.getNumberOfColumns(), model.getNumberOfRows());

                    // Random wer anfängt im Single Player Mode.
                    RandomStarter starter = new RandomStarter(startPlayer, otherPlayer);
                    
                    // Game starten
                    GameController controller = new GameController(starter.getFirstPlayer(), starter.getSecondPlayer(), model.getNumberOfColumns(), model.getNumberOfRows());
                    controller.showView();
            }
        });

        this.view.addActionListenerMultiPlayerOpen(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // UDPServer instanziieren mit max. Anzahl versuchen 50
                // TODO Ist diese Anzahl ok?
                UDPServer uServer = new UDPServer(50);
                uServer.start();
                
                // Auf IP Adresse meines gegenüber warten
                UDPGetIp udpGetIp = new UDPGetIp();
                String ip = udpGetIp.getIp();
                System.out.println("Gegner verbindet sich - " + ip);
                
                // UDP Server stoppen
                //uServer.setStoppThread(true);
                
                // View ausblenden
                view.close();
                
                // Player erzeugen 
                Player otherPlayer = Player.createNetworkPlayer(ip);
                Player startPlayer = Player.createLocalPlayer();
                
                // Spiel starten
                GameController controller = new GameController(startPlayer, otherPlayer, DefaultConfiguration.COLUMNS, DefaultConfiguration.ROWS);
                controller.showView();
            }
        });

        this.view.addActionListenerMultiPlayerJoin(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // UDPClient starten
                UDPClient uClient = new UDPClient();
                uClient.start();
                
                // View schliessen
                view.close();
                
                // Controller starten
                SearchServerController controller = new SearchServerController(uClient);
                controller.showView();
            }
        });

    }

    public void showView() {
        this.view.show();
    }
}