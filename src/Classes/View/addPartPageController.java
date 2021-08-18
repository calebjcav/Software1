
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
    @FXML
    private Label MachineCompanyLabel;


    @FXML
    private RadioButton inhouseRadioButton;

    @FXML
    private ToggleGroup AddPartTypeToggleGroup;

    @FXML
    private RadioButton outsourcedRadioButton;

    @FXML
    private TextField IDText;

    @FXML
    private TextField NameText;

    @FXML
    private TextField InvText;

    @FXML
    private TextField PriceText;

    @FXML
    private TextField MaxText;

    @FXML
    private TextField MachineCompanyText;

    @FXML
    private TextField MinText;


    private boolean isOutsourced = false;
    private String errorMsg = new String();

    @FXML
    void handlePartTextToInhouse(ActionEvent event) {
        isOutsourced = false;
        MachineCompanyLabel.setText("Machine ID");
        inhouseRadioButton.setSelected(true);
    }

    @FXML
    void handlePartTextToOutsourced(ActionEvent event) {
        isOutsourced = true;
        MachineCompanyLabel.setText("Company Name");
        outsourcedRadioButton.setSelected(true);
    }

    @FXML
    public void handleSave(ActionEvent event) throws IOException {
        try {
            int id = 0;
            String name = NameText.getText();
            Double price = Double.parseDouble(PriceText.getText());
            int stock = Integer.parseInt(InvText.getText());
            int min = Integer.parseInt(MinText.getText());
            int max = Integer.parseInt(MaxText.getText());
            int machineId;
            String companyName;
            boolean partAddSuccessful = false;

            if (name.isEmpty()) {
                showAlert(5);
            } else {
                if (minValid(min, max) && inventoryValid(min, max, stock)) {

                    if (inhouseRadioButton.isSelected()) {
                        try {
                            machineId = Integer.parseInt(MachineCompanyText.getText());
                            InHousePart newInHousePart = new InHousePart(id, name, price, stock, min, max, machineId);
                            newInHousePart.setID(Inventory.getNewPartID());
                            Inventory.addPart(newInHousePart);
                            partAddSuccessful = true;
                        } catch (Exception e) {
                            showAlert(2);
                        }
                    }

                    if (outsourcedRadioButton.isSelected()) {
                        companyName = MachineCompanyText.getText();
                        OutsourcedPart newOutsourcedPart = new OutsourcedPart(id, name, price, stock, min, max,
                                companyName);
                        newOutsourcedPart.setID(Inventory.getNewPartID());
                        Inventory.addPart(newOutsourcedPart);
                        partAddSuccessful = true;
                    }

                    if (partAddSuccessful) {
                        returnToMainScreen(event);
                    }
                }
            }
        } catch (Exception e) {
            showAlert(1);
        }


    }


    private void returnToMainScreen(ActionEvent event) throws IOException {

        Parent parent = FXMLLoader.load(getClass().getResource("../../MainPage.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void handleCancelAddPart(ActionEvent event) throws IOException {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.initModality(Modality.NONE);
        alert.setTitle("Confirm Cancellation");
        alert.setHeaderText("Confirm");
        alert.setContentText("Are you sure you want to cancel?");
        Optional<ButtonType> outcome = alert.showAndWait();


        if (outcome.get() == ButtonType.OK) {
            Parent addPartsScreen = FXMLLoader.load(getClass().getResource("../../MainPage.fxml"));
            Scene addPartsScene = new Scene(addPartsScreen);
            //next line is getting stage information
            Stage addPartsStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            addPartsStage.setScene((addPartsScene));
            addPartsStage.show();
        }

    }

    private boolean minValid(int min, int max) {

        boolean isValid = true;

        if (min <= 0 || min >= max) {
            isValid = false;
            showAlert(3);
        }

        return isValid;
    }


    private boolean inventoryValid(int min, int max, int stock) {

        boolean isValid = true;

        if (stock < min || stock > max) {
            isValid = false;
            showAlert(4);
        }

        return isValid;
    }


    private void showAlert(int alertType) {

        Alert alert = new Alert(Alert.AlertType.ERROR);

        switch (alertType) {
            case 1:
                alert.setTitle("Error");
                alert.setHeaderText("Error Adding Part");
                alert.setContentText("Form contains blank fields or invalid values.");
                alert.showAndWait();
                break;
            case 2:
                alert.setTitle("Error");
                alert.setHeaderText("Invalid value for Machine ID");
                alert.setContentText("Machine ID may only contain numbers.");
                alert.showAndWait();
                break;
            case 3:
                alert.setTitle("Error");
                alert.setHeaderText("Invalid value for Min");
                alert.setContentText("Min must be a number greater than 0 and less than Max.");
                alert.showAndWait();
                break;
            case 4:
                alert.setTitle("Error");
                alert.setHeaderText("Invalid value for Inventory");
                alert.setContentText("Inventory must be a number equal to or between Min and Max.");
                alert.showAndWait();
                break;
            case 5:
                alert.setTitle("Error");
                alert.setHeaderText("Name Empty");
                alert.setContentText("Name cannot be empty.");
                alert.showAndWait();
                break;
        }
    }

}





