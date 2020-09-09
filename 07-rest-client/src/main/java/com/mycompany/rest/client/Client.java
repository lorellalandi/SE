/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.rest.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

/**
 *
 * @author studente
 */
public class Client {
    
    private static final String BASE_URL = "http://localhost:8080/movies/";
    private static CloseableHttpClient client;
    public static void main(String[] args) throws IOException {
        client = HttpClients.createDefault();
        List<Integer> movies = new LinkedList<>();
        ObjectMapper mapper2 = new ObjectMapper();
        URL url2 = new URL(BASE_URL);
        
        InputStream input2 = url2.openStream();
        movies = mapper2.readValue(input2, List.class);        

        
        for(int i = 0; i < movies.size(); i++) {
            ObjectMapper mapper = new ObjectMapper();
            URL url = new URL(BASE_URL + movies.get(i));
        
            InputStream input = url.openStream();
            Movie m = (Movie) mapper.readValue(input, Movie.class);        
            System.out.println(m.getID() + " " + m.getTitle());



        }
        // Example GET
        /*
        ObjectMapper mapper = new ObjectMapper();
        URL url = new URL(BASE_URL + "2");
        
        InputStream input = url.openStream();
        Movie m = (Movie) mapper.readValue(input, Movie.class);        
        System.out.println(m.getTitle());
        */

    }
}
