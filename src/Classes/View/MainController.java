package Classes.View;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    public Scene getScene() {
        return scene;
    }

    public void setScenetoAddPartPage(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getClassLoader().getResource("AddPartPage.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    public void setScenetoMainPage(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getClassLoader().getResource("MainPage.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void setScenetoModifyPartPage(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getClassLoader().getResource("ModifyPartPage.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void deleteSelectedPart(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getClassLoader().getResource("ModifyPartPage.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void setScenetoAddProductPage(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getClassLoader().getResource("ModifyPartPage.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void setScenetoModifyProductPage(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getClassLoader().getResource("ModifyPartPage.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}

