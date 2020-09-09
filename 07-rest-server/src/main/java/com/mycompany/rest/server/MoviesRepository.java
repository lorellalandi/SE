/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.rest.server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 *
 * @author studente
 */
@Path("/movies")
public class MoviesRepository {
    private Connection connection;
    
    public void setConnection() {
        try {
            try {
                Class.forName("org.sqlite.JDBC");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(MoviesRepository.class.getName()).log(Level.SEVERE, null, ex);
            }
            connection
                    = DriverManager.getConnection("jdbc:sqlite:/home/studente/Documents/db/database1prova.db");
        } catch (SQLException ex) {
            Logger.getLogger(MoviesRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @GET
    @Path("{movieId}")
    @Produces("application/json")

    public Movie getMovie(@PathParam("movieId") int movieId) {

        return findById(movieId);
    }
    
    @GET
    @Produces("application/json")

    public List<Integer> getMovies() {

        ArrayList<Integer> movies = new ArrayList<>();
        
        try {
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);
            ResultSet rs = statement.executeQuery("SELECT ID FROM MOVIES;");
            while(rs.next()) {
                movies.add(rs.getInt("ID"));
            }
        } catch(SQLException e) {
            System.err.println(e.getMessage());
        }

        return movies;
    }
    
    private Movie findById(int ID) {
        
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM MOVIES WHERE ID = ?;");
            statement.setInt(1, ID);
            statement.setQueryTimeout(30);
            Movie m = new Movie();

            ResultSet rs = statement.executeQuery();
            if(rs.next()) {
                m.setID(rs.getInt("ID"));
                m.setDirectorID(rs.getInt("directorID"));
                m.setTitle(rs.getString("title"));
                m.setYear(rs.getString("year"));
                return m;
            }
        } catch(SQLException e) {
            System.err.println(e.getMessage());
        }
         
        return null;   
    }
    
}
