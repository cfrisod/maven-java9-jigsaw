package nl.frisodobber.java9.jigsaw.calculator.algorithm.substract;

import nl.frisodobber.java9.jigsaw.calculator.algorithm.api.Algorithm;
/**
 * Created by dobber on 31-12-16.
 */
public class Substract implements Algorithm {

    @Override
    public Integer calculate(Integer input, Integer input2) {
        return input - input2;
    }
}
