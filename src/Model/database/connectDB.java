/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**
 *
 * @author Carlos
 */
public class connectDB {
    
    private final String _user;
    private final String _pass;
    private final String _url;
    private Connection _conn;
    
    public connectDB() {
        this._user = "postgres";
        //this._pass = "charlie6141";
        this._pass = "root";
        //this._url = "mybd.ceb3kybuklco.us-east-2.rds.amazonaws.com";
        this._url = "jdbc:postgresql://localhost:5433/postgres";
    }
    
    public Connection getConnection() {                
        try {
                _conn = DriverManager.getConnection(_url, _user, _pass);                
        } catch (SQLException e) {
                System.out.println("Connection Failed! Check output console");
                e.printStackTrace();                
        }        
        return _conn;
    }
    
    
    public void closeConnection(){
        try {
                _conn.close();               
        } catch (SQLException e) {
                System.out.println("Can't close connection! Check output console");
                e.printStackTrace();                
        }            
    }  
    
}
