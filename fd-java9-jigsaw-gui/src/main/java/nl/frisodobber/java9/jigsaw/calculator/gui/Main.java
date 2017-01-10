package nl.frisodobber.java9.jigsaw.calculator.gui;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import nl.frisodobber.java9.jigsaw.calculator.algorithm.api.Algorithm;

import java.util.ServiceLoader;

/**
 * Created by dobber on 5-1-17.
 */
public class Main extends Application {
    private ServiceLoader<Algorithm> algorithms;

    @Override
    public void init() {
        loadAlgorithms();
    }

    private void loadAlgorithms() {
        algorithms = ServiceLoader.load(Algorithm.class);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Calculator");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Text scenetitle = new Text("Calculate");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 0, 0, 2, 1);

        Label input1Label = new Label("Input 1:");
        grid.add(input1Label, 0, 1);

        final TextField input1Field = new TextField();
        grid.add(input1Field, 1, 1);

        Label algorithmLabel = new Label("Algorithm");
        grid.add(algorithmLabel, 0, 2);

        ObservableList<Algorithm> options = FXCollections.observableArrayList();
        algorithms.forEach(v -> {
            options.add(v);
        });
        final ComboBox comboBox = new ComboBox(options);
        grid.add(comboBox, 1, 2);

        // Define rendering of the list of values in ComboBox drop down.
        comboBox.setCellFactory((cb) -> {
            return new ListCell<Algorithm>() {
                @Override
                protected void updateItem(Algorithm item, boolean empty) {
                    super.updateItem(item, empty);

                    if (item == null || empty) {
                        setText(null);
                    } else {
                        setText(item.getClass().getSimpleName());
                    }
                }
            };
        });

        // Define rendering of selected value shown in ComboBox.
        comboBox.setConverter(new StringConverter<Algorithm>() {
            @Override
            public String toString(Algorithm al) {
                if (al == null) {
                    return null;
                } else {
                    return al.getClass().getSimpleName();
                }
            }

            @Override
            public Algorithm fromString(String alString) {
                return null; // No conversion fromString needed.
            }
        });

        Label input2Label = new Label("Input 2:");
        grid.add(input2Label, 0, 3);

        final TextField input2Field = new TextField();
        grid.add(input2Field, 1, 3);

        final Text actiontarget = new Text();
        grid.add(actiontarget, 1, 5);

        Button btn = new Button("Calculate");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btn);
        grid.add(hbBtn, 1, 4);

        btn.setOnAction((event) -> {
            Algorithm al = (Algorithm)comboBox.getValue();
            actiontarget.setFill(Color.FIREBRICK);
            Integer input1 = Integer.valueOf(input1Field.getText());
            Integer input2 = Integer.valueOf(input2Field.getText());

            actiontarget.setText(al.calculate(input1,input2).toString());
        });

        Scene scene = new Scene(grid, 300, 275);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
