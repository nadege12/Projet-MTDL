/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connexionmysql {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/graphique_help";
    private static final String USER = "root";
    private static final String PASS = "Monavenir1998!";

    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connexion réussie");
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Connexion échouée");
            ex.printStackTrace();
        }
        return conn;
    }
}
