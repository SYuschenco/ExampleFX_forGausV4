package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Controller <N extends Number, T extends Gauss<N, T>> implements Initializable {
    static Integer systemSize;

    static int DEFAULT_EQUATIONS_NUMBER;

    @FXML
    public Button buttonRunRand;

    @FXML
    public Button btnRunWithInputtingCoefficients;

    @FXML
    public Button btnGetResult;

    @FXML
    public Button okButton;

    @FXML
    public AnchorPane matrixPaneInInputMethod;
    @FXML
    public AnchorPane inputMatrix;

    @FXML
    private GridPane matrixInputGrid;

    @FXML
    private TextField inputtingInDialogSystemSizeFieldInInputMethod;

    @FXML
    private TextField inputtingInDialogSystemSizeField;

    @FXML
    private TextField inputSizeForRandon;

    @FXML
    private Label lblInputMatrix;

    @FXML
    private Label lblCheckInput;

    private Stage mainStage;

    List<TextField> matrix;


    public void setMainStage(Stage mainStage) {
        this.mainStage = mainStage;
    }

    Scene mainScene;

    public void getMainScene(Scene mainScene) {
        this.mainScene = Main.mainScene;
    }

    Stage inputStage = new Stage();
    Scene inputScene;

    public LinearSystem<Float, MyEquation> list;

    public List<Float> equation;

    @FXML
    private void initialize() {
    }


    public void getResult(ActionEvent event) throws IOException {
        inputCoefficientsInGUI();
        chekInputCoefficientsInGUI();


        //matrixPaneInInputMethod.getChildren().add(new Label(matrix.toString()));
        mainStage.show();
    }

    public void general() {

        DEFAULT_EQUATIONS_NUMBER = (Integer.parseInt(String.valueOf(inputtingInDialogSystemSizeFieldInInputMethod)));

        printSystem(list);

        int i, j;
        Algorithm<Float, MyEquation> alg = new Algorithm<Float, MyEquation>(list);
        try {
            alg.calculate();
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
            System.exit(0);
        } catch (ArithmeticException e) {
            System.out.println(e.getMessage());
            System.exit(0);
        }
        Float[] x = new Float[DEFAULT_EQUATIONS_NUMBER];
        for (i = list.size() - 1; i >= 0; i--) {
            Float sum = 0.0f;
            for (j = list.size() - 1; j > i; j--) {
                sum += list.itemAt(i, j) * x[j];
            }
            x[i] = (list.itemAt(i, list.size()) - sum) / list.itemAt(i, j);
        }
        printSystem(list);
        printVector(x);
    }

    public static LinearSystem<Float, MyEquation> generateSystem(String methodForGeneratingSystemCoefficients) {
        LinearSystem<Float, MyEquation> list = new LinearSystem<Float, MyEquation>();
        for (int i = 0; i < DEFAULT_EQUATIONS_NUMBER; i++) {
            MyEquation eq = new MyEquation();
            eq.generate(DEFAULT_EQUATIONS_NUMBER + 1, methodForGeneratingSystemCoefficients);
            list.push(eq);
        }
        return list;
    }

    public static void printSystem(LinearSystem<Float, MyEquation> system) {
        for (int i = 0; i < system.size(); i++) {
            MyEquation temp = system.get(i);
            String s = "";
            for (int j = 0; j < temp.size(); j++) {
                s += String.format("%f; %s", system.itemAt(i, j), "\t");
            }
            System.out.println(s);
        }
        System.out.println("");
    }

    public static void printVector(Float[] x) {
        String s = "";
        for (int i = 0; i < x.length; i++) {
            s += String.format("x%d = %f; ", i, x[i]);
        }
        System.out.println(s);
    }




    private void chekInputCoefficientsInGUI() {
        for (TextField textField : matrix) {
            isDigit(textField.getText());
            textField.requestFocus();
//            String tmp = textField.getText();
//            //tmp.toCharArray();
//            for (char c : tmp.toCharArray()) {
//                if ((textField.getText() != null && !textField.getText().isEmpty())) {
//
//                    if (Character.isAlphabetic(c)) {
//                        textField.requestFocus();
//                        lblCheckInput.setVisible(true);
//                        //System.out.println("INVALID");
//                        break;
//                    } else {
//                        lblCheckInput.setVisible(false);
//                    }
//                }
//            }
        }
    }

    private void isDigit(String s) throws NumberFormatException {
        try {
            Double.parseDouble(s);

            lblCheckInput.setVisible(false);
        } catch (NumberFormatException e) {
            lblCheckInput.setVisible(true);
        }
    }

    private void inputCoefficientsInGUI() {
        systemSize = Integer.valueOf(inputtingInDialogSystemSizeFieldInInputMethod.getText());
        int systemSizeInNumber = systemSize;
        int equationSize = systemSizeInNumber + 1;
        ArrayList tempArrayCoefficients = new ArrayList();
        List<Float> equation = new ArrayList<>();
        List<T> list = new ArrayList<>();

        for (TextField textField : matrix) {
            tempArrayCoefficients.add(Float.parseFloat(textField.getText()));
        }
        System.out.println("tempArrayCoefficients.size()= " + tempArrayCoefficients.size());

        int numberSubArrays = tempArrayCoefficients.size() / (systemSize + 1);

//        System.out.println("numberSubArrays= " + numberSubArrays);
        int lower = 0;
        int upper = 0;

        for (int i = 0; i < numberSubArrays; i++) {
            upper += numberSubArrays + 1;
            MyEquation eq = new MyEquation();
            lower = upper;
            for (int j =0 ; j<equation.size(); j++) {
                equation.add(tempArrayCoefficients.get(j));
                list.add(i, equation.get(i));
            }
            System.out.println(equation.get(i));
        }
//        System.out.println("equation.size() = " + equation.size());
//        System.out.println("list.size() = " + list.size());

        System.out.println("equation.toString() = " + equation.toString());
        System.out.println("list.toString() = " + list.toString());
    }


    public void btnActionRunWithInputtingCoefficients(ActionEvent event) throws IOException {
        lblInputMatrix.setVisible(true);
        matrixPaneInInputMethod.getChildren().clear();
        mainStage.show();
        systemSize = Integer.valueOf(inputtingInDialogSystemSizeFieldInInputMethod.getText());
        int systemSizeInNumber = systemSize;
        Float systemSizeInNumberFloat = Float.valueOf(systemSizeInNumber);
        matrix = new ArrayList<TextField>();

        for (int i = 0; i < systemSizeInNumber; i++)
            for (int j = 0; j < systemSizeInNumber + 1; j++) {
                String index = "" + (i + 1) + "," + (j + 1);
                TextField cells = new TextField();
                cells.setPromptText(index);
                cells.setId(index);
                cells.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent e) {
                        int j = 0;
                        if ((cells.getText() != null && !cells.getText().isEmpty())) {
                            String cellsNumber = cells.getText();
                            //j = (cells.getId());
                            //equation[0].add(cellsNumber);
                            //System.out.print();
                            System.out.println(cells.getId() + "=" + cellsNumber);
                            j++;
                        } else {

                        }
                    }
                });
                cells.setOnKeyPressed(new EventHandler<KeyEvent>() {
                    @Override
                    public void handle(KeyEvent ke) {
                        if (ke.getCode().equals(KeyCode.ENTER)) {
                            //System.out.println(cells+ "|" + equation+ "|132");
                            int d = 0;
                        }
                    }
                });
                matrix.add(cells);
            }
        GridPane grid = new GridPane();
        grid.setVgap(0);
        grid.setHgap(0);
        grid.setMaxWidth(700);
        grid.setMaxHeight(200);

        int k = 0;
        for (int i = 1; i < systemSizeInNumber + 1; i++) {
            for (int j = 1; j < (systemSizeInNumber + 2); j++) {
                k = k + 1;
                GridPane.setConstraints(matrix.get(k - 1), j, i);
                grid.getChildren().add(matrix.get(k - 1));
            }
        }
//        System.out.println("\nPrinted cells--" + k);
        matrixPaneInInputMethod.getChildren().add(grid);
        mainStage.show();
    }

    public void ButtonClicked(ActionEvent e) {
        if (e.getSource() == btnRunWithInputtingCoefficients)
            inputStage.setScene(inputScene);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void onEnter(ActionEvent event) {
        btnRunWithInputtingCoefficients.requestFocus();
    }
}
