module calculator.algorithm.add {
    requires calculator.algorithm.api;

    provides nl.frisodobber.java9.jigsaw.calculator.algorithm.api.Algorithm
            with nl.frisodobber.java9.jigsaw.calculator.algorithm.add.Add;
}