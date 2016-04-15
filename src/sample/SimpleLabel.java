package sample;

/**
 * Copyright (c) 2008, 2012 Oracle and/or its affiliates.
 * All rights reserved. Use is subject to license terms.
 */

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * A simple implementation of the Label control, with an image to the left of
 * the label.
 *
 * @see javafx.scene.control.Label
 * @related controls/text/AdvancedLabel
 * @related controls/buttons/GraphicButton
 * @resource icon-48x48.png
 *
 */
public class SimpleLabel extends Application {
    private static final Image ICON_48 = new Image(SimpleLabel.class.getResourceAsStream("icon-48x48.png"));
    private void init(Stage primaryStage) {
        Group root = new Group();
        primaryStage.setScene(new Scene(root));
       // ImageView imageView = new ImageView(ICON_48);
        Label label = new Label("A simple label with a graphic on the left.");
        //Label label = new Label("A simple label with a graphic on the left.", imageView);
        label.setContentDisplay(ContentDisplay.LEFT);
        root.getChildren().add(label);
    }

    @Override public void start(Stage primaryStage) throws Exception {
        init(primaryStage);
        primaryStage.show();
    }
    public static void main(String[] args) { launch(args); }
}