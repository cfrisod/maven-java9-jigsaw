/**
 * Created by dobber on 31-12-16.
 */
module calculator.gui {
    exports nl.frisodobber.java9.jigsaw.calculator.gui to javafx.graphics;

    requires javafx.base;
    requires javafx.graphics;
    requires javafx.controls;
    requires calculator.algorithm.api;
    uses nl.frisodobber.java9.jigsaw.calculator.algorithm.api.Algorithm;
}
