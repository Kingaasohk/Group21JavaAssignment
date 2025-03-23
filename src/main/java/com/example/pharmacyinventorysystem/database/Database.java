package com.example.pharmacyinventorysystem.database;

import java.sql.Connection; // Manage the connection between application and database
import java.sql.DriverManager; // Initilize a connection between the database
import java.sql.PreparedStatement; // Execute SQL queries
import  java.sql.SQLException; // SQL related exception handling



public class Database {
        private static final String URL = "jdbc:sqlite:pharmacy_inventory.db";

        public static Connection connect(){
            Connection conn = null; // Connection variable

            try{
                // Load JDBC driver as it's needed to communicate with the SQlite Database
                Class.forName("org.sqlite.JDBC");

                conn = DriverManager.getConnection(URL); // Driver to connect to SQlite
                System.out.println("You've now entered SQLite!");

            } catch (ClassNotFoundException e){
                System.out.println("Something went wrong with the SQlite  Driver..."); // error if JDBC is missing or corrupted
            } catch (SQLException e){
                System.out.println("Can't communicate with SQlite..."); // error if connection to the database fails
                System.out.println(e.getMessage());
            }

            return conn; // return database connection

        }

        public static void createTables(){
            // String that contains the SQL commands needed for a user table.
            String usersTable = "CREATE TABLE IF NOT EXISTS users (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " + // unique id, used for primary key
                    "username TEXT NOT NULL UNIQUE, " +  // Username must be unique and not empty/null
                    "password TEXT NOT NULL, " + // Password absolutely must not be null, can't hash something if it doesn't exist.
                    "role TEXT NOT NULL" + // Role to determine levels of access, can't be null because you always have a role to play!
                    ");";

                try (Connection conn = connect();
                PreparedStatement prepStat = conn.prepareStatement(usersTable)){
                    prepStat.execute();
                    System.out.println("Table successfully made!");
                } catch (SQLException e){
                    System.out.println("Error making the table 'cause of:" + e.getMessage());
                }


        }

        public static void main(String[] args){
            createTables(); // Makes sure that a database must be present!
        }


}
