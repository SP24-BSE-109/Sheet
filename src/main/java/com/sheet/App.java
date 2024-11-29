package com.sheet;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class App extends Application {
    @Override
    public void start(Stage stage) {
        GridPane grid = new GridPane();
        
        Utils.SetPanePadding(grid, 20);
        grid.setVgap(20);
        grid.setHgap(20);

        Label titleLabel = new Label("Sheet");
        
        Utils.SetFontStyle(titleLabel, STYLESHEET_MODENA, Utils.GetFontWeight(Weight.Bold), 24);
        


        grid.add(titleLabel, 0, 0, 2, 1);
        AnimationController.FadeInAnimate(titleLabel);

        Label nameLabel = new Label("Name:");
        TextField nameField = new TextField();
        grid.add(nameLabel, 0, 1);
        grid.add(nameField, 1, 1);
        AnimationController.FadeInAnimate(nameLabel);
        AnimationController.FadeInAnimate(nameField);
        Label dobLabel = new Label("Date of Birth:");
        DatePicker dobPicker = new DatePicker();
        grid.add(dobLabel, 0, 2);
        grid.add(dobPicker, 1, 2);
        AnimationController.FadeInAnimate(dobLabel);
        AnimationController.FadeInAnimate(dobPicker);
        Label IDLabel = new Label("ID:");
        TextField IDField = new TextField();
        grid.add(IDLabel, 0, 3);
        grid.add(IDField, 1, 3);
        AnimationController.FadeInAnimate(IDLabel);
        AnimationController.FadeInAnimate(IDField);
        Label genderLabel = new Label("Gender");
        ComboBox<String> genderComboBox = new ComboBox<>();
        genderComboBox.getItems().addAll("Male", "Female", "Other");
        genderComboBox.setPromptText("Select Gender");
        grid.add(genderLabel, 0, 4);
        grid.add(genderComboBox, 1, 4);
        AnimationController.FadeInAnimate(genderLabel);
        AnimationController.FadeInAnimate(genderComboBox);
        Label provinceLabel = new Label("Province");
        TextField provinceField = new TextField();
        grid.add(provinceLabel, 0, 5);
        grid.add(provinceField, 1, 5);
        AnimationController.FadeInAnimate(provinceLabel);
        AnimationController.FadeInAnimate(provinceField);
        Button submitButton = new Button("Submit");
        Utils.SetFontStyle(submitButton, STYLESHEET_MODENA, Utils.GetFontWeight(Weight.Bold), 14);
        Button searchButton = new Button("Search");
        Utils.SetFontStyle(searchButton, STYLESHEET_MODENA, Utils.GetFontWeight(Weight.Bold), 14);

        grid.add(submitButton, 1, 6);

        HBox buttonBox = new HBox(45);
        buttonBox.getChildren().addAll(submitButton, searchButton);
        grid.add(buttonBox, 1, 6);
        AnimationController.FadeInAnimate(searchButton);
        AnimationController.FadeInAnimate(submitButton);
        Label successLabel = new Label();
        Utils.SetFontStyle(successLabel, STYLESHEET_MODENA, Utils.GetFontWeight(Weight.Bold), 14);
        grid.add(successLabel, 0, 7, 2, 1);

        searchButton.setOnAction(e -> {
            String searchID = IDField.getText();
            boolean isFound = false;
        
            try (BufferedReader reader = new BufferedReader(new FileReader("user_data.txt"))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    if (line.startsWith("ID: ") && line.substring(4).equals(searchID)) {
                        isFound = true;
                        break;
                    }
                }
            } catch (java.io.IOException ex) {
                ex.printStackTrace();
                successLabel.setText("Error searching user data.");
                return;
            }
            if (isFound) {
                successLabel.setText("Record found!");
            } else {
                successLabel.setText("Record not found!");
            }
        
            AnimationController.FadeOutAnimate(successLabel);
        });
        
        submitButton.setOnAction(e -> {
            
            Map<String, String> user = new HashMap<>();
            user.put("ID", IDField.getText());
            user.put("Name", nameField.getText());
            user.put("Date of Birth", dobPicker.getValue() != null ? dobPicker.getValue().toString() : "");
            user.put("Gender", genderComboBox.getValue());
            user.put("Province", provinceField.getText());

            AddDataInFile(user, successLabel);

            Utils.ClearFields(IDField, dobPicker, nameField, genderComboBox, provinceField);
            successLabel.setText("User data saved successfully!");
            AnimationController.FadeOutAnimate(successLabel);
        });
        stage.setTitle("User Form");
        Scene scene = new Scene(grid,400,400);
        stage.setScene(scene);

        stage.setResizable(false);

        stage.show();
    }
    private void AddDataInFile(Map<String, String> user, Label successLabel) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("user_data.txt", true))) {
            writer.write("ID: " + user.get("ID") + "\n");
            writer.write("Name: " + user.get("Name") + "\n");
            writer.write("Date of Birth: " + user.get("Date of Birth") + "\n");
            writer.write("Gender: " + user.get("Gender") + "\n");
            writer.write("Province: " + user.get("Province") + "\n");
            writer.write("===================================\n");
        } catch (IOException ex) {
            ex.printStackTrace();
            successLabel.setText("Error saving user data.");
            return;
        }
    }

    public static void main(String[] args) {
        launch();
    }

}
