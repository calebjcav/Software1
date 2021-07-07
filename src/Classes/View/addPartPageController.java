
package Classes.View;

import Classes.Model.InHousePart;
import Classes.Model.Inventory;
import Classes.Model.OutsourcedPart;
import Classes.Model.Part;
import Classes.View.MainController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;



import java.io.IOException;
import java.util.Optional;



public class addPartPageController {

    private boolean isOutsourced = false;
    private String errorMsg = new String();

    public void handleSave(ActionEvent event) throws IOException {
        try {

            errorMsg = Part.isValidPart(NameText.getText(), Integer.parseInt(MinText.getText()), Integer.parseInt(MaxText.getText()),
                    Integer.parseInt(InvText.getText()), Double.parseDouble(PriceText.getText()), errorMsg);
            if (errorMsg.length() > 0) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Error");
                alert.setHeaderText("Error adding current part");
                alert.setContentText(errorMsg);
                alert.showAndWait();
                errorMsg = "";
            } else {
                if (isOutsourced == false) {
                    InHousePart partIH = new InHousePart(Integer.parseInt(IDText.getText()),
                    NameText.getText(), Double.parseDouble(PriceText.getText()), Integer.parseInt(InvText.getText()), Integer.parseInt(MinText.getText()),
                    Integer.parseInt(MaxText.getText()), Integer.parseInt(MachineText.getText()));

                    Inventory.addPart(partIH);
                } else if (isOutsourced == true) {
                    OutsourcedPart partOS = new OutsourcedPart();
                    partOS.setID(Integer.parseInt(IDText.getText()));
                    partOS.setName(NameText.getText());
                    partOS.setStock(Integer.parseInt(InvText.getText()));
                    partOS.setPrice(Double.parseDouble(PriceText.getText()));
                    partOS.setMin(Integer.parseInt(MinText.getText()));
                    partOS.setMax(Integer.parseInt(MaxText.getText()));
                    partOS.setCompanyName(CompanyText.getText());
                    Inventory.addPart(partOS);

                }

                //switch to home screen
                Main.getStage().setScene(mainScene());

            }
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText("Error adding part");
            alert.setContentText("Blank fields are not allowed");
            alert.showAndWait();
        }
    }


        void handleCancelAddPart(ActionEvent event) throws IOException {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.initModality(Modality.NONE);
        alert.setTitle("Confirm Cancellation");
        alert.setHeaderText("Confirm");
        alert.setContentText("Are you sure you want to cancel?");
        Optional<ButtonType> outcome = alert.showAndWait();


        if(outcome.get() == ButtonType.OK) {
            Parent addPartsScreen = FXMLLoader.load(getClass().getResource("MainPage.fxml"));
            Scene addPartsScene = new Scene(addPartsScreen);
            //next line is getting stage information
            Stage addPartsStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            addPartsStage.setScene((addPartsScene));
            addPartsStage.show();
        }

    }

    void handleRadioInHouse(ActionEvent event) {
        isOutsourced = false;

        radioInHouse.setSelected(true);
    }

    @FXML
    void handleRadioOutSourced(ActionEvent event) {
        isOutsourced = true;

        radioOutSource.setSelected(true);
    }



    }
/*
        inhouseRadioButton.setOnAction(event ->
        {
        fieldInputs.getChildren().removeAll(MachineID, MachineText, CompanyText, CompanyName);

        fieldInputs.add(MachineID,0,6);
        fieldInputs.add(MachineText,1,6);

        });

        outsourcedRadioButton.setOnAction(event ->
        {
        fieldInputs.getChildren().removeAll(MachineID, MachineText, CompanyText, CompanyName);
        fieldInputs.add(CompanyName,0,6);
        fieldInputs.add(CompanyText,1,6);

        });
