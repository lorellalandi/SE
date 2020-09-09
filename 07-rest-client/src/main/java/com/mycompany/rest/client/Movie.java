/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.rest.client;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

/**
 *
 * @author studente
 */
@JacksonXmlRootElement(localName = "Movie")
public class Movie {
        
    private int ID;
    private String title;
    private String year;
    private int directorID;


    public int getID() {
        return ID;
    }

    public int getDirectorID() {
        return directorID;
    }

    public String getTitle() {
        return title;
    }

    public String getYear() {
        return year;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setDirectorID(int directorID) {
        this.directorID = directorID;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setYear(String year) {
        this.year = year;
    }    
}
