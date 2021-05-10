package org.aio.gui;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

import java.sql.*;

public class AjoutFournisseurController {
    public TextField nom;
    public TextField rue;
    public TextField cp;
    public TextField ville;
    public TextField contact;
    public Button ajout;
    public Button annulation;
    String style ="-fx-border-color: red";
    String styleForValidData = "-fx-border-color: green";
    String lettres ="^[a-zA-Z -_]+$";
    String lettresChiffres ="^[a-zA-Z0-9 ]+$";
    String cinqChiffres="^[0-9]{5}$";
    String lettresChiffresSignesSpeciaux ="^[a-zA-Z0-9 .-_@]+$";


    public void checkNom(KeyEvent keyEvent) {

        if(! nom.getText().matches(lettres)){
            nom.setStyle(style);
            System.out.println("not allowed :"+nom.getText());
        }else {
            nom.setStyle(styleForValidData);
        }
    }

    public void checkRue(KeyEvent keyEvent) {
        if(! rue.getText().matches(lettresChiffres)){
            rue.setStyle(style);
            System.out.println("no allowed :"+rue.getText());

        }else {
            rue.setStyle(styleForValidData);
        }
    }

    public void checkCP(KeyEvent keyEvent) {
        if (! cp.getText().matches(cinqChiffres)){
            cp.setStyle(style);
            System.out.println("no allowed :"+cp.getText());
        }else {
            cp.setStyle(styleForValidData);
        }
    }

    public void checkVille(KeyEvent keyEvent) {
        if (! ville.getText().matches(lettres)){
            ville.setStyle(style);
            System.out.println("no allowed :"+ville.getText());

        }else {
            ville.setStyle(styleForValidData);
        }
    }

    public void checkContact(KeyEvent keyEvent) {
        if (!contact.getText().matches("[a-zA-Z0-9 .-_@]+")){
            contact.setStyle(style);
            System.out.println("no allowed :"+contact.getText());

        }else {
            contact.setStyle(styleForValidData);
        }
    }

    public void ajouter(ActionEvent actionEvent) {
        if (nom.getText().matches(lettres) && rue.getText().matches(lettresChiffres)
                && cp.getText().matches(cinqChiffres) && ville.getText().matches(lettres)
                && contact.getText().matches(lettresChiffresSignesSpeciaux)
                ){
            System.out.println("yes general");

            try {
                String url = "jdbc:mysql://localhost:3306/papyrus";
                String user = "dev";
                String password = "azertY23456";
                Connection con = DriverManager.getConnection(url, user, password);
                PreparedStatement retrieveId = con.prepareStatement("SELECT numfou FROM FOURNIS order by numfou DESC LIMIT 1");
                ResultSet retrievedId = retrieveId.executeQuery();
                int numfou=0;
                while (retrievedId.next()){
                    numfou = retrievedId.getInt("numfou")+1;
                }

                PreparedStatement stm = con.prepareStatement("INSERT INTO FOURNIS (numfou,nomfou,ruefou,posfou,vilfou,confou) VALUES (?,?,?,?,?,?) ");

                stm.setInt(1, numfou);
                stm.setString(2, nom.getText());
                stm.setString(3, rue.getText());
                stm.setString(4, cp.getText());
                stm.setString(5, ville.getText());
                stm.setString(6, contact.getText());
                stm.execute();

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Message");
                alert.setContentText("Le fournisseur est ajouté correctement");
                alert.showAndWait();

                stm.close();
                con.close();
            } catch (Exception e) {
                System.out.println("Error");
                System.out.println(e.getMessage());
            }

        }else {
            if (!nom.getText().matches(lettres)){
                nom.setStyle(style);
            }
            if (!rue.getText().matches(lettresChiffres)){
                rue.setStyle(style);
            }
            if (! cp.getText().matches(cinqChiffres)){
                cp.setStyle(style);
            }
            if (! ville.getText().matches(lettres)){
                ville.setStyle(style);
            }
            if (!contact.getText().matches(lettresChiffresSignesSpeciaux)){
                contact.setStyle(style);
            }

        }
    }

    public void annuler(ActionEvent actionEvent) {
        nom.clear();
        rue.clear();
        cp.clear();
        ville.clear();
        contact.clear();
    }
}
