package sample;

/**
 * Copyright (c) 2008, 2012 Oracle and/or its affiliates.
 * All rights reserved. Use is subject to license terms.
 */
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.LabelBuilder;
import javafx.stage.Stage;

/**
 * A sample that shows styling of text through CSS.
 *
 * @see javafx.geometry.Insets
 * @see javafx.scene.control.LabelBuilder
 * @resource InsetText.css
 *
 */
public class InsetTextSample extends Application {

    private void init(Stage primaryStage) {
        Group root = new Group();
        primaryStage.setScene(new Scene(root));

        String insetTextCss = InsetTextSample.class.getResource("InsetText.css").toExternalForm();
        Label label = LabelBuilder.create().text("Label styled as a bar").id("label1").build();
        label.getStylesheets().add(insetTextCss);
        root.getChildren().add(label);
    }

    @Override public void start(Stage primaryStage) throws Exception {
        init(primaryStage);
        primaryStage.show();
    }
    public static void main(String[] args) { launch(args); }
}
