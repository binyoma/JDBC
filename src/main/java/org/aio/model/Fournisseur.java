package org.aio.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Fournisseur {
    private int numfou;
    private String nomfou;
    private String ruefou;
    private String posfou;
    private String vilfou;
    private String confou;
    private int satisf ;

    public Fournisseur(int numfou, String nomfou, String ruefou, String posfou, String vilfou, String confou, int satisf) {
        this.numfou = numfou;
        this.nomfou = nomfou;
        this.ruefou = ruefou;
        this.posfou = posfou;
        this.vilfou = vilfou;
        this.confou = confou;
        this.satisf = satisf;
    }

    public int getNumfou() {
        return numfou;
    }

    public String getNomfou() {
        return nomfou;
    }

    public String getRuefou() {
        return ruefou;
    }

    public String getPosfou() {
        return posfou;
    }

    public String getVilfou() {
        return vilfou;
    }

    public String getConfou() {
        return confou;
    }

    public int getSatisf() {
        return satisf;
    }

    public void setNumfou(int numfou) {
        this.numfou = numfou;
    }

    public void setNomfou(String nomfou) {
        this.nomfou = nomfou;
    }

    public void setRuefou(String ruefou) {
        this.ruefou = ruefou;
    }

    public void setPosfou(String posfou) {
        this.posfou = posfou;
    }

    public void setVilfou(String vilfou) {
        this.vilfou = vilfou;
    }

    public void setConfou(String confou) {
        this.confou = confou;
    }

    public void setSatisf(int satisf) {
        this.satisf = satisf;
    }


}
