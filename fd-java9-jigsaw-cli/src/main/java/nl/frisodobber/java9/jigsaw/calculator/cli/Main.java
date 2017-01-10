package nl.frisodobber.java9.jigsaw.calculator.cli;

import nl.frisodobber.java9.jigsaw.calculator.algorithm.api.Algorithm;

import java.io.Console;
import java.util.HashMap;
import java.util.Map;
import java.util.ServiceLoader;

/**
 * Created by dobber on 31-12-16.
 */
public class Main {
    private ServiceLoader<Algorithm> algorithms;
    private Map<String, Algorithm> algorithmMap;

    public Main() {
        loadAlgorithms();
    }

    private void loadAlgorithms() {
        algorithms = ServiceLoader.load(Algorithm.class);
        algorithmMap = new HashMap<>();
        algorithms.stream().forEach(al -> algorithmMap.put(al.get().getClass().getSimpleName(), al.get()));
    }

    public static void main(String[] args) {
        new Main().launch();
    }

    public void launch() {
        Console c = System.console();
        if (c == null) {
            System.err.println("No console.");
            System.exit(1);
        }

        c.printf("Welcome to the calculator CLI. ");
        Integer input1 = getValidInput(c, c.readLine("Enter your first input parameter: "));

        Algorithm algorithm = getAlgorithm(c);

        Integer input2 = getValidInput(c, c.readLine("Enter your second input parameter: "));
        c.format("The answer is: " + algorithm.calculate(input1, input2) + "\n");
    }

    private Integer getValidInput(Console c, String input) {
        Integer result = 0;
        boolean noValidInput;
        do {
            try {
                result = Integer.valueOf(input);
                noValidInput = false;
            } catch(NumberFormatException ex) {
                noValidInput = true;
                input = c.readLine("No valid number please try again: ");
            }
        } while (noValidInput);
        return result;
    }

    private Algorithm getAlgorithm(Console c) {
        final StringBuffer algorithmText = new StringBuffer("Please select which algorithm you want to use from the following list: ");
        algorithmMap.keySet().stream().forEach(aln -> {
            algorithmText.append(aln);
            algorithmText.append(", ");
        });
        algorithmText.append("\n");
        c.printf(algorithmText.toString());

        String algorithm;
        do {
            algorithm = c.readLine("Select the algorithm: " );
        } while(!algorithmMap.keySet().contains(algorithm));
        return algorithmMap.get(algorithm);
    }
}
