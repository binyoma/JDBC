package org.aio.gui;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import org.aio.model.Fournisseur;


import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ListeCommandeController implements Initializable {
    public ChoiceBox choixFourniseur;
    public TextArea listeComande;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {

            String url = "jdbc:mysql://localhost:3306/papyrus";
            String user = "dev";
            String password = "azertY23456";
            Connection con = DriverManager.getConnection(url, user, password);
            PreparedStatement stm = con.prepareStatement("SELECT * FROM FOURNIS");
            ResultSet result = stm.executeQuery();
            ArrayList<Fournisseur> listeFournisseur =new ArrayList<Fournisseur>();
            while (result.next()) {
                Fournisseur fournisseur = new Fournisseur(
                        result.getInt("numfou"),
                        result.getString("nomfou"),
                        result.getString("ruefou"),
                        result.getString("posfou"),
                        result.getString("vilfou"),
                        result.getString("confou"),
                        result.getInt("satisf"));
                listeFournisseur.add(fournisseur);
            }
            ArrayList<Integer> list =new ArrayList<Integer>();

            for (Fournisseur fournisseur:listeFournisseur){
               list.add(fournisseur.getNumfou());
            }

            choixFourniseur.setItems(FXCollections.observableList(list));

            choixFourniseur.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
                @Override
                public void changed(ObservableValue observable, Object oldValue, Object newValue) {

                   String numfou =newValue.toString();
                    try {

                        PreparedStatement stm = con.prepareStatement("SELECT * FROM entcom WHERE numfou = ?");
                        stm.setInt(1, Integer.parseInt(numfou));

                        ResultSet result = stm.executeQuery();
                        while (result.next()) {
                            listeComande.appendText(String.valueOf(result.getInt("numcom")));
                            listeComande.appendText(result.getString("datcom"));
                            listeComande.appendText(result.getString("obscom")+"\n");
                        }
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }


                }
            });

            stm.close();
            result.close();
            con.close();

        } catch (Exception e) {
            System.out.println("Error");
            System.out.println(e.getMessage());
        }
    }

}
