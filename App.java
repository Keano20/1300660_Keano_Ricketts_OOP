package com.mycompany.vetassignment;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


/**
 * JavaFX App
 */
public class App extends Application {


    private enum AppState {
        HOME, CREATE_CLINIC, ADD_PET, VIEW_PETS
    }

    private AppState currentState = AppState.HOME;
    private Clinic currentClinic = null;
    private int currentIndex = 0;

    private Label titleLabel;
    
    @Override
    public void start(Stage primaryStage) {
        // Create UI components
        this.titleLabel = new Label("Veterinary Application");
        Button createClinicButton = new Button("Create Clinic");
        Button addPetButton = new Button("Add Pet");
        Button viewPetsButton = new Button("View Pets");

        // Set actions for buttons
        createClinicButton.setOnAction(event -> setCurrentState(AppState.CREATE_CLINIC));
        addPetButton.setOnAction(event -> setCurrentState(AppState.ADD_PET));
        viewPetsButton.setOnAction(event -> setCurrentState(AppState.VIEW_PETS));

        // Create VBox for HOME state
        VBox homeLayout = new VBox(10);
        homeLayout.setPadding(new Insets(10));
        homeLayout.getChildren().addAll(titleLabel, createClinicButton, addPetButton, viewPetsButton);

        // Create scene for HOME state
        Scene homeScene = new Scene(homeLayout, 300, 200);

        // Set initial scene
        primaryStage.setScene(homeScene);
        primaryStage.setTitle("Veterinary App");
        primaryStage.show();
    }

    private void setCurrentState(AppState newState) {
        currentState = newState;
        switch (currentState) {
            case HOME:
                if (currentClinic != null) {
                    this.titleLabel.setText("Current Clinic: " + currentClinic.getName());
                } else {
                    this.titleLabel.setText("Veterinary Application");
                }
                break;
            case CREATE_CLINIC:
                // Update UI for CREATE_CLINIC state
                displayCreateClinicPage();
                break;
            case ADD_PET:
                // Update UI for ADD_PET state
                if (currentClinic != null) {
                   displayAddPetPage();
                } else {
                    System.out.println("OPEN A CLINIC BEFORE YOU TRY TO ADD A PET");
                }
                break;
            case VIEW_PETS:
                if (currentClinic != null && !(currentClinic.getPetList().isEmpty())) {
                   displayViewPetsPage();
                } else {
                    System.out.println("OPEN A CLINIC AND ADD A PET!");
                }
                break;
            default:
                break;
        }
    }

    private void displayCreateClinicPage() {
        Stage stage = new Stage();
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10));

        TextField clinicNameField = new TextField();
        clinicNameField.setPromptText("Enter Clinic Name");

        Button submitButton = new Button("Submit");
        submitButton.setOnAction(event -> {
            Clinic newClinic = new Clinic(clinicNameField.getText());
            currentClinic = newClinic;
            setCurrentState(AppState.HOME);
            stage.close();
        });

        layout.getChildren().addAll(clinicNameField, submitButton);

        Scene scene = new Scene(layout, 300, 100);
        stage.setScene(scene);
        stage.setTitle("Create Clinic");
        stage.show();
    }

    private void displayAddPetPage() {
        Stage stage = new Stage();
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10));

        TextField nameField = new TextField();
        nameField.setPromptText("Pet Name");
        TextField speciesField = new TextField();
        speciesField.setPromptText("Species");
        TextField colourField = new TextField();
        colourField.setPromptText("Colour");
        TextField ageField = new TextField();
        ageField.setPromptText("Age");
        TextField ownerField = new TextField();
        ownerField.setPromptText("Owner Name");

        Button submitButton = new Button("Submit");
        submitButton.setOnAction(event -> {
            Pet newPet = new Pet(nameField.getText(), speciesField.getText(), colourField.getText(), Integer.parseInt(ageField.getText()), ownerField.getText());
            currentClinic.addPet(newPet);
            System.out.println("Added Pet " + nameField.getText() + " to the clinic");
            setCurrentState(AppState.HOME);
            stage.close();
        });

        layout.getChildren().addAll(nameField, speciesField, colourField, ageField, ownerField, submitButton);

        Scene scene = new Scene(layout, 300, 250);
        stage.setScene(scene);
        stage.setTitle("Add Pet");
        stage.show();
    }

    private void displayViewPetsPage() {
        Stage stage = new Stage();
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10));
        
        Animal pet = currentClinic.getPet(0);

        Label nameLabel = new Label("Pet Name: " + pet.getName());
        Label speciesLabel = new Label("Species: " + pet.getSpecies());
        Label colourLabel = new Label("Color: " + pet.getColour());
        Label ageLabel = new Label("Age: " + pet.getAge());
        Label ownerLabel = new Label("Owner Name: " + pet.getOwnerName());

        Button previousButton = new Button("Previous Pet");
        previousButton.setOnAction(event -> {
            if (currentIndex > 0) {
                currentIndex -= 1;
                Animal newPet = currentClinic.getPet(currentIndex);
                nameLabel.setText("Pet Name: " + newPet.getName());
                speciesLabel.setText("Pet Species: " + newPet.getSpecies());
                colourLabel.setText("Pet Colour: " + newPet.getColour());
                ageLabel.setText("Pet Age: " +String.valueOf(newPet.getAge()));
                ownerLabel.setText("Pet Owner: " + newPet.getOwnerName());
            } else {
                System.out.println("Already At the First Value");
            }
        });
        
        Button nextButton = new Button("Next Pet");
        nextButton.setOnAction(event -> {
            currentIndex += 1;
            if (currentIndex > currentClinic.getPetList().size() - 1) {
                currentIndex = 0;
            }
            Animal newPet = currentClinic.getPet(currentIndex);
            nameLabel.setText("Pet Name: " + newPet.getName());
            speciesLabel.setText("Pet Species: " + newPet.getSpecies());
            colourLabel.setText("Pet Colour: " + newPet.getColour());
            ageLabel.setText("Pet Age: " +String.valueOf(newPet.getAge()));
            ownerLabel.setText("Pet Owner: " + newPet.getOwnerName());
        });

        layout.getChildren().addAll(nameLabel, speciesLabel, colourLabel, ageLabel, ownerLabel, previousButton, nextButton);

        Scene scene = new Scene(layout, 300, 250);
        stage.setScene(scene);
        stage.setTitle("View Pets");
        stage.show();
    }
     public static void main(String[] args) {
        launch();
    }

}

