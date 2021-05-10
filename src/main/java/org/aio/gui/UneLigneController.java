package org.aio.gui;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.sql.*;


public class UneLigneController {
    public TextField txtField_nom;
    public TextField textField_adresse;
    public TextField txtField_cp;
    public TextField txtField_ville;
    public TextField txtField_contact;
    public TextField txtField_code;
    public Button btn_rechercher;

    public void rechercherFournisseur(ActionEvent actionEvent) {
        try {

            String url = "jdbc:mysql://localhost:3306/papyrus";
            String user = "dev";
            String password = "azertY23456";
            String code = txtField_code.getText();
            Connection con = DriverManager.getConnection(url, user, password);
            PreparedStatement stm = con.prepareStatement("SELECT * FROM FOURNIS WHERE numfou = ?");
            stm.setInt(1, Integer.parseInt(code));

            ResultSet result = stm.executeQuery();

            while (result.next()) {
                txtField_nom.setText(result.getString("nomfou"));
                textField_adresse.setText(result.getString("ruefou"));
                txtField_cp.setText(result.getString("posfou"));
                txtField_ville.setText(result.getString("vilfou"));
                txtField_contact.setText(result.getString("confou"));

            }

            stm.close();
            result.close();
            con.close();

        } catch (Exception e) {
            System.out.println("Error");
            System.out.println(e.getMessage());
        }

    }
}
