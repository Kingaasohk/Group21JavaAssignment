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
                    "first_name TEXT NOT NULL," + // First Name
                    "last_name TEXT NOT NULL," + // First Name
                    "email TEXT NOT NULL UNIQUE, " +  // e-mail must be unique and not empty/null
                    "gender TEXT NOT NULL," + // Gender
                    "password TEXT NOT NULL, " + // Password absolutely must not be null, can't hash something if it doesn't exist.
                    "role TEXT DEFAULT 'Pharmacist'," + // Role to determine levels of access, can't be null because you always have a role to play!
                    "created_at TEXT NOT NULL " + // Timestamp when account was created
                    ");";

            String perscriptionTable = "CREATE TABLE IF NOT EXISTS perscriptions (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " + // unique id, used for primary key
                    "first_name TEXT NOT NULL," + // First Name
                    "last_name TEXT NOT NULL," + // First Name
                    "email TEXT NOT NULL UNIQUE, " +  // e-mail must be unique and not empty/null
                    "gender TEXT NOT NULL," + // Gender
                    "Medical condition TEXT NOT NULL, " + // Medical condition of the customer
                    "created_at TEXT NOT NULL " + // Timestamp when account was created
                    ");";

            String inventoryTable = "CREATE TABLE IF NOT EXISTS inventory (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " + // unique id, used for primary key
                    "drug_name TEXT NOT NULL," + // First Name
                    "stock INTEGER NOT NULL" +
                    ");";

                try (Connection conn = connect()){
                conn.prepareStatement(usersTable).execute();
                conn.prepareStatement(perscriptionTable).execute();
                conn.prepareStatement(inventoryTable).execute();
                    System.out.println("Table successfully made!");
                } catch (SQLException e){
                    System.out.println("Error making the table 'cause of:" + e.getMessage());
                }


        }

        public static void main(String[] args){
            createTables(); // Makes sure that a database must be present!
        }


}
