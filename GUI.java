import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.print.DocFlavor.STRING;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.InputEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GUI extends Application {
    @Override
    public void start(Stage stage) {
        stage.setTitle("CRUD cursist"); // Komt bovenin
        // Scene mainView = new Scene(label);

        Label Email = new Label("Email");
        Label Naam = new Label("Naam");
        Label Geboortedatum = new Label("Geboortedatum");
        Label Geslacht = new Label("Geslacht");
        Label Adres = new Label("Adres");
        Label Woonplaats = new Label("Woonplaats");
        Label Land = new Label("Land");

        Button button1 = new Button("Create");
        Button button2 = new Button("Read");
        Button button3 = new Button("Update");
        Button button4 = new Button("Delete");

        TextField TextFieldEmail = new TextField();
        TextField TextFieldNaam = new TextField();
        TextField TextFieldGeboortedatum = new TextField();
        TextField TextFieldGeslacht = new TextField();
        TextField TextFieldAdres = new TextField();
        TextField TextFieldWoonplaats = new TextField();
        TextField TextFieldLand = new TextField();

        TextFieldEmail.setPrefWidth(250);
        TextFieldNaam.setPrefWidth(250);
        TextFieldGeboortedatum.setPrefWidth(250);
        TextFieldGeslacht.setPrefWidth(250);
        TextFieldAdres.setPrefWidth(250);
        TextFieldWoonplaats.setPrefWidth(250);
        TextFieldLand.setPrefWidth(250);

        HBox hb1 = new HBox();
        hb1.getChildren().addAll(Email, TextFieldEmail);
        hb1.setSpacing(10);

        HBox hb2 = new HBox();
        hb2.getChildren().addAll(Naam, TextFieldNaam);
        hb2.setSpacing(10);

        HBox hb3 = new HBox();
        hb3.getChildren().addAll(Geboortedatum, TextFieldGeboortedatum);
        hb3.setSpacing(10);

        HBox hb4 = new HBox();
        hb4.getChildren().addAll(Geslacht, TextFieldGeslacht);
        hb4.setSpacing(10);

        HBox hb5 = new HBox();
        hb5.getChildren().addAll(Adres, TextFieldAdres);
        hb5.setSpacing(10);

        HBox hb6 = new HBox();
        hb6.getChildren().addAll(Woonplaats, TextFieldWoonplaats);
        hb6.setSpacing(10);

        HBox hb7 = new HBox();
        hb7.getChildren().addAll(Land, TextFieldLand);
        hb7.setSpacing(10);

        HBox buttonPane = new HBox();
        buttonPane.setSpacing(5);
        buttonPane.getChildren().addAll(
                button1,
                button2,
                button3,
                button4);

        button1.setOnAction((event) -> {
            String email = TextFieldEmail.getText();
            String naam = TextFieldNaam.getText();
            String geboorteDatum = TextFieldGeboortedatum.getText();
            Integer geslacht = Integer.valueOf(TextFieldGeslacht.getText());
            String adres = TextFieldAdres.getText();
            String woonplaats = TextFieldWoonplaats.getText();
            String land = TextFieldLand.getText();

            setDataInDatabase(email, naam, geboorteDatum, geslacht, adres, woonplaats, land);

        });

        button2.setOnAction((event) -> {
            String email = TextFieldEmail.getText();
            setDataFromDatabase(TextFieldEmail, "Email", email);
            setDataFromDatabase(TextFieldNaam, "Naam", email);
            setDataFromDatabase(TextFieldGeboortedatum, "GeboorteDatum", email);
            setDataFromDatabase(TextFieldGeslacht, "Geslacht", email);
            setDataFromDatabase(TextFieldAdres, "Adres", email);
            setDataFromDatabase(TextFieldWoonplaats, "Woonplaats", email);
            setDataFromDatabase(TextFieldLand, "Land", email);

        });

        // button3.setOnAction((event) -> {
        // Integer value1 = Integer.valueOf(textField.getText());
        // Integer value2 = Integer.valueOf(textField1.getText());
        // Integer r = value1 - value2;
        // textField2.setText(r.toString());
        // });

        // button4.setOnAction((event) -> {
        // Integer value1 = Integer.valueOf(textField.getText());
        // Integer value2 = Integer.valueOf(textField1.getText());
        // Integer r = value1 * value2;
        // textField2.setText(r.toString());
        // });

        VBox vb = new VBox();
        vb.getChildren().addAll(hb1, hb2, hb3, hb4, hb5, hb6, hb7);
        vb.setSpacing(20);

        BorderPane mainPane = new BorderPane();
        mainPane.setPadding(new Insets(5));
        mainPane.setBottom(buttonPane);
        mainPane.setCenter(vb);
        Scene mainView = new Scene(mainPane);

        stage.setScene(mainView);
        stage.show();
    }

    private void setDataFromDatabase(TextField textField, String culumName, String email) {
        try {
            Database Database = new Database();

            Connection con = Database.getConnection();

            String SQL = "SELECT * FROM cursist WHERE Email = ?";

            PreparedStatement preparedStatement = con.prepareStatement(SQL);

            preparedStatement.setString(1, email);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                textField.setText(resultSet.getString(culumName));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void setDataInDatabase(String email, String naam, String geboorteDatum, Integer geslacht, String adres,
            String woonplaats, String land) {
        try {
            Database Database = new Database();

            Connection con = Database.getConnection();

            String SQL = "INSERT INTO cursist values(?,?,?,?,?,?,?)";

            PreparedStatement preparedStatement = con.prepareStatement(SQL);

            preparedStatement.setString(1, email);
            preparedStatement.setString(2, naam);
            preparedStatement.setString(3, geboorteDatum);
            preparedStatement.setInt(4, geslacht);
            preparedStatement.setString(5, adres);
            preparedStatement.setString(6, woonplaats);
            preparedStatement.setString(7, land);

            int r = preparedStatement.executeUpdate();

            System.out.println(r + " records inserted");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
