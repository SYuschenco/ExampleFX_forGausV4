package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class AddMatrixController {

//    private Stage mainStage;
//
//    public void setMainStage(Stage mainStage) {
//        this.mainStage = mainStage;
//    }


    public void btnActionRunWithInputtingCoefficients(ActionEvent actionEvent) throws IOException {

        try {

            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("matrixForInputtingCoefficients"));
            stage.setTitle("Input matrix coefficients of system equation:");
            stage.setMinHeight(400);
            stage.setMinWidth(900);
            stage.setResizable(false);
            stage.setScene(new Scene(root));
            stage.initModality(Modality.WINDOW_MODAL);
            //get parent window
            stage.initOwner(((Node) actionEvent.getSource()).getScene().getWindow());
            stage.show();


//            GridPane grid = new GridPane();
//            grid.boundsInParentProperty();
//            grid.setLayoutX(32);
//            grid.setLayoutY(304);
//            //grid.setAlignment((32,304));
//            grid.setHgap(10);
//            grid.setVgap(10);
//            grid.setPadding(new Insets(25, 25, 25, 25));
//            //primaryStage.setScene(new Scene(fxmlMain, 500, 500));
//            Scene scene = new Scene(grid, 739, 443);
//            mainStage.setAlwaysOnTop(true);
//            mainStage.setScene(scene);
//            Label labelInput = new Label("Input matrix coefficients of system equation:");
//
//            labelInput.setMaxSize(200, 20);
//            grid.add(labelInput, 0, 0);
//            TextField matrix = new TextField();
//            TextField matrix1 = new TextField();
//            grid.add(matrix, 1, 5);
//            grid.add(matrix1, 5, 5);
//            System.out.println("Add field   " + matrix);
//            mainStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
