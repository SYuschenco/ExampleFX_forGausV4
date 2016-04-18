package sample;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class Main extends Application {
    static Scene mainScene;
    @Override
    public void start(Stage primaryStage) throws Exception{

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("sample.fxml"));
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Parent fxmlMain = fxmlLoader.load();
        Controller mainController = fxmlLoader.getController();
        mainController.setMainStage(primaryStage);
        mainScene = new Scene(fxmlMain, 900, 900);
        primaryStage.setTitle("Application for solving a system of linear equations by Gauss");
        primaryStage.setMinHeight(900);
        primaryStage.setMinWidth(900);
        primaryStage.setScene(mainScene);


        //init2(primaryStage);
        primaryStage.show();
    }

    private void init(Stage primaryStage) {
        Group root = new Group();
        primaryStage.setScene(new Scene(root));
        // create label to show result of selected toggle button
        final Label label = new Label();
        label.setStyle("-fx-font-size: 2em;");
        // create 3 toggle buttons and a toogle group for them
        final ToggleButton tb1 = new ToggleButton("Cat");
        final ToggleButton tb2 = new ToggleButton("Dog");
        final ToggleButton tb3 = new ToggleButton("Horse");
        ToggleGroup group = new ToggleGroup();
        tb1.setToggleGroup(group);
        tb2.setToggleGroup(group);
        tb3.setToggleGroup(group);
        group.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle selectedToggle) {
                if(selectedToggle!=null) {
                    label.setText(((ToggleButton) selectedToggle).getText());
                }
                else {
                    label.setText("...");
                }
            }
        });
        // select the first button to start with
        group.selectToggle(tb1);
        // add buttons and label to grid and set their positions
        GridPane.setConstraints(tb1,0,0);
        GridPane.setConstraints(tb2,1,0);
        GridPane.setConstraints(tb3,2,0);
        GridPane.setConstraints(label,0,1,3,1);
        GridPane grid = new GridPane();
        grid.setVgap(20);
        grid.setHgap(10);
        root.getChildren().add(grid);
        grid.getChildren().addAll(tb1, tb2, tb3, label);
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

    public static void main(String[] args){
        launch(args);

        //System.out.println(inputtingInDialogSystemSizeForMain);
    }
}
