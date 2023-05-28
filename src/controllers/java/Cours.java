package controllers.java;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author nhaba
 */
public class Cours {

    private int id;
    private String titre;
    private String prof;
    private String cours;

    public Cours(int id, String titre, String prof, String cours) {
        this.id = id;
        this.titre = titre;
        this.prof = prof;
        this.cours = cours;
    }

    public int getId() {
        return id;
    }

    public String getTitre() {
        return titre;
    }

    public String getProf() {
        return prof;
    }

    public String getCours() {
        return cours;
    }

    public String getCoursData() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
