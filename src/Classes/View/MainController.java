package Classes.View;

import Classes.Model.Inventory;
import Classes.Model.Part;
import Classes.Model.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;
    /**
     * The part object selected in the table view by the user.
     */
    private static Part partToModify;

    /**
     * The product object selected in the table view by the user.
     */
    private static Product productToModify;

    /**
     * The text field for the parts search.
     */
    @FXML
    private TextField partSearch;

    /**
     * Table view for the parts.
     */
    @FXML
    private TableView<Part> partsTableView;

    /**
     * The ID column for the parts table.
     */
    @FXML
    private TableColumn<Part, Integer> partsIDColumn;

    /**
     * The part name column for the parts table.
     */
    @FXML
    private TableColumn<Part, String> partNameColumn;

    /**
     * The inventory column for the parts table.
     */
    @FXML
    private TableColumn<Part, Integer> partInvColumn;

    /**
     * The price column for parts table.
     */
    @FXML
    private TableColumn<Part, Double> partPriceColumn;

    /**
     * Table text field for the product search.
     */
    @FXML
    private TextField productSearch;

    /**
     * Table view for the products.
     */
    @FXML
    private TableView<Product> productsTableView;

    /**
     * The ID column for the product table.
     */
    @FXML
    private TableColumn<Product, Integer> productIDColumn;

    /**
     * The name column for the product table.
     */
    @FXML
    private TableColumn<Product, String> productNameColumn;

    /**
     * The inventory column for the product table.
     */
    @FXML
    private TableColumn<Product, Integer> productInvColumn;

    /**
     * The price column for the product table.
     */
    @FXML
    private TableColumn<Product, Double> productPriceColumn;

    /**
     * Gets the part object selected by the user in the part table.
     *
     * @return A part object, null if no part selected.
     */
    public static Part getPartToModify() {
        return partToModify;
    }

    /**
     * Gets the product object selected by the user in the product table.
     *
     * @return A product object, null if no product selected.
     */
    public static Product getProductToModify() {
        return productToModify;
    }

    /**
     * Exits the program.
     *
     * @param event Exit button action.
     */
    @FXML
    void exitButtonAction(ActionEvent event) {

        System.exit(0);
    }

    /**
     * Loads the AddPartController.
     *
     * @param event Add button action.
     * @throws IOException From FXMLLoader.
     */
    @FXML
    void setScenetoAddPartPage(ActionEvent event) throws IOException {

        Parent parent = FXMLLoader.load(getClass().getResource("../../AddPartPage.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Deletes the part selected by the user in the part table.
     *
     * The method displays an error message if no part is selected and a confirmation
     * dialog before deleting the selected part.
     *
     * @param event Part delete button action.
     */
    @FXML
    void partDeleteAction(ActionEvent event) {

        Part selectedPart = partsTableView.getSelectionModel().getSelectedItem();

        if (selectedPart == null) {
            displayAlert(3);
        } else {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Alert");
            alert.setContentText("Do you want to delete the selected part?");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {
                Inventory.deletePart(selectedPart);
            }
        }
    }

    /**
     * Loads the ModifyPartController.
     *
     * The method displays an error message if no part is selected.
     *
     * @param event Part modify button action.
     * @throws IOException From FXMLLoader.
     */
    @FXML
    void setScenetoModifyPartPage(ActionEvent event) throws IOException {

        partToModify = partsTableView.getSelectionModel().getSelectedItem();

        //Example of correcting a runtime error by preventing null from being passed
        // to the ModifyPartController.
        if (partToModify == null) {
            displayAlert(3);
        } else {
            Parent parent = FXMLLoader.load(getClass().getResource("../../ModifyPartPage.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }
    }

    /**
     * Initiates a search based on value in parts search text field and refreshes the parts
     * table view with search results.
     *
     * Parts can be searched for by ID or name.
     *
     * @param event Part search button action.
     */
    @FXML
    void partSearchBtnAction(ActionEvent event) {

        ObservableList<Part> allParts = Inventory.getAllParts();
        ObservableList<Part> partsFound = FXCollections.observableArrayList();
        String searchString = partSearch.getText();

        for (Part part : allParts) {
            if (String.valueOf(part.getID()).contains(searchString) ||
                    part.getName().contains(searchString)) {
                partsFound.add(part);
            }
        }

        partsTableView.setItems(partsFound);

        if (partsFound.size() == 0) {
            displayAlert(1);
        }
    }

    /**
     * Refreshes part table view to show all parts when parts search text field is empty.
     *
     * @param event Parts search text field key pressed.
     */
    @FXML
    void partSearchTextKeyPressed(KeyEvent event) {

        if (partSearch.getText().isEmpty()) {
            partsTableView.setItems(Inventory.getAllParts());
        }

    }

    /**
     * Loads AddProductController.
     *
     * @param event Add product button action.
     * @throws IOException From FXMLLoader.
     */
    @FXML
    void productAddButton(ActionEvent event) throws IOException {

        Parent parent = FXMLLoader.load(getClass().getResource("Classes/View/AddProductPage.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Deletes the product selected by the user in the product table.
     *
     * The method displays an error message if no product is selected and a confirmation
     * dialog before deleting the selected product. Also prevents user from deleting
     * a product with one or more associated parts.
     *
     * @param event Product delete button action.
     */
    @FXML
    void productDeleteAction(ActionEvent event) {

        Product selectedProduct = productsTableView.getSelectionModel().getSelectedItem();

        if (selectedProduct == null) {
            displayAlert(4);
        } else {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Alert");
            alert.setContentText("Do you want to delete the selected product?");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {

                ObservableList<Part> assocParts = selectedProduct.getAllAssociatedParts();

                if (assocParts.size() >= 1) {
                    displayAlert(5);
                } else {
                    Inventory.deleteProduct(selectedProduct);
                }
            }
        }
    }

    /**
     * Loads the ModifyProductController.
     *
     * The method displays an error message if no product is selected.
     *
     * @param event Product modify button action.
     * @throws IOException From FXMLLoader.
     */
    @FXML
    void setScenetoModifyProductPage(ActionEvent event) throws IOException {

        productToModify = productsTableView.getSelectionModel().getSelectedItem();

        if (productToModify == null) {
            displayAlert(4);
        } else {
            Parent parent = FXMLLoader.load(getClass().getResource("../../ModifyProductPage.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }
    }

    @FXML
    void handleProductSearch(ActionEvent event) {

        ObservableList<Product> allProducts = Inventory.getAllProducts();
        ObservableList<Product> productsFound = FXCollections.observableArrayList();
        String searchString = productSearch.getText();

        for (Product product : allProducts) {
            if (String.valueOf(product.getId()).contains(searchString) ||
                    product.getName().contains(searchString)) {
                productsFound.add(product);
            }
        }

        productsTableView.setItems(productsFound);

        if (productsFound.size() == 0) {
            displayAlert(2);
        }
    }

    @FXML
    void productSearchTextKeyPressed(KeyEvent event) {

        if (productSearch.getText().isEmpty()) {
            productsTableView.setItems(Inventory.getAllProducts());
        }
    }

    private void displayAlert(int alertType) {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        Alert alertError = new Alert(Alert.AlertType.ERROR);

        switch (alertType) {
            case 1:
                alert.setTitle("Information");
                alert.setHeaderText("Part not found");
                alert.showAndWait();
                break;
            case 2:
                alert.setTitle("Information");
                alert.setHeaderText("Product not found");
                alert.showAndWait();
                break;
            case 3:
                alertError.setTitle("Error");
                alertError.setHeaderText("Part not selected");
                alertError.showAndWait();
                break;
            case 4:
                alertError.setTitle("Error");
                alertError.setHeaderText("Product not selected");
                alertError.showAndWait();
                break;
            case 5:
                alertError.setTitle("Error");
                alertError.setHeaderText("Parts Associated");
                alertError.setContentText("All parts must be removed from product before deletion.");
                alertError.showAndWait();
                break;
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //Populate parts table view
        partsTableView.setItems(Inventory.getAllParts());
        partsIDColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInvColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        //Populate products table view
        productsTableView.setItems(Inventory.getAllProducts());
        productIDColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        productNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        productInvColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        productPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
    }

    public Scene getScene() {
        return scene;
    }

    public void deleteSelectedPart(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getClassLoader().getResource("ModifyPartPage.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}

