package sample;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable{
    static Integer systemSize;

    @FXML
    public Button buttonRunRand;

    @FXML
    public Button btnRunWithInputtingCoefficients;

    @FXML
    public Button btn2;

    @FXML
    public Button okButton;

    @FXML
    public AnchorPane matrixPaneInInputMethod;

    @FXML
    private GridPane matrixInputGrid;

    @FXML
    private TextField inputtingInDialogSystemSizeFieldInInputMethod;

    @FXML
    private TextField inputtingInDialogSystemSizeField;

    private Stage mainStage;

    public void setMainStage(Stage mainStage) {
        this.mainStage = mainStage;
    }


    @FXML
    private void initialize(){
        inputtingInDialogSystemSizeFieldInInputMethod.getText();
        System.out.println(inputtingInDialogSystemSizeFieldInInputMethod.getText());
        inputtingInDialogSystemSizeField.getText();
        System.out.println(inputtingInDialogSystemSizeField.getText());
        //systemSize = inputtingInDialogSystemSizeFieldInInputMethod.getText();
        systemSize = Integer.valueOf(inputtingInDialogSystemSizeFieldInInputMethod.getText());
    }



    public void getSystemSizeFromDialogWindow(ActionEvent actionEvent) {
        Integer inputtingInDialogSystemSizeForMain = Integer.valueOf(inputtingInDialogSystemSizeField.getText());
        initialize();
    }

    public void inputtingInDialogSystemSizeFieldInInputMethod(Event event) {
        Integer inputtingInDialogSystemSizeForMain = Integer.valueOf(inputtingInDialogSystemSizeField.getText());
        //inputtingInDialogSystemSizeForMain = Integer.parseInt(inputtingInDialogSystemSizeFieldInInputMethod.getText());
    }

    private void addXZ() {

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("sample.fxml"));
        //Stage seconderyStage = new Stage();
        matrixInputGrid.autosize();
        matrixInputGrid.setGridLinesVisible(false);
    }




    public void btn2Act(ActionEvent event) {

    }

    public void btnActionRunWithInputtingCoefficients(ActionEvent event) throws IOException {

        Stage stage;
        Parent root;

        if (event.getSource() == btnRunWithInputtingCoefficients) {
            //get reference to the button's stage
            stage = (Stage) btnRunWithInputtingCoefficients.getScene().getWindow();
            //load up OTHER FXML document
            root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        } else {
            stage = (Stage) btn2.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("matrixForInputtingCoefficients"));
        }
        //create a new scene with root and set the stage
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    private void init2(Stage primaryStage) {

        TextField systemSizeInStringField = new TextField("7");
        GridPane.setConstraints(systemSizeInStringField,0,0);
        Integer systemSizeInNumber = Integer.valueOf(systemSizeInStringField.getText());
        List<TextField> matrix = new ArrayList<TextField>();

        for (int i = 0; i < systemSizeInNumber; i++) {
            for (int j = 0; j < systemSizeInNumber + 1; j++) {
                String index = "" + i + j;
                matrix.add(new TextField(index));
                System.out.print(index+"_");
            }
        }

        System.out.println("\nAdded cells--" + matrix.size());

        Group root2 = new Group();
        primaryStage.setScene(new Scene(root2));
        GridPane grid = new GridPane();
        grid.setVgap(0);
        grid.setHgap(0);
        grid.setMaxWidth(700);
        grid.setMaxHeight(200);
        root2.getChildren().add(grid);
        int k=0;
        for (int i=1; i < systemSizeInNumber+1; i++) {
            for (int j = 1; j < (systemSizeInNumber+2); j++) {
                k=k+1;
                GridPane.setConstraints(matrix.get(k-1), j, i);
                grid.getChildren().addAll(matrix.get(k-1));
            }
        }
        System.out.println("\nPrinted cells--"+k);
    }
}
