package Client;


import gui.Login;

import java.io.IOException;
import java.net.InetAddress;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {
        Scanner in = new Scanner(System.in);
        InetAddress host = InetAddress.getLocalHost();
        int port=1338;
        ClientTools ct = new ClientTools(in, host, port);
        Login lg = new Login(ct);
        lg.pack();
        lg.setVisible(true);


    }
}
