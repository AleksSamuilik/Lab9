package it.alex.lab9;

import java.util.*;

public class CalculatorApp {


    public static void main(String[] args) throws Exception {
        AnnotationAnalyzer analyzer = new AnnotationAnalyzer();
        Set<Class<?>> listClass = analyzer.reflectionsScan();
        Collection<Operation> collection = new ArrayList<>();
        for (Class clazz : listClass) {
            try {
                if (clazz.newInstance() != null) {
                    collection.add((Operation) clazz.newInstance());
                }
            } catch (InstantiationException e) {
                continue;
            }
        }
        Calculator calculator = new Calculator(collection);
        calculator.calculate();
    }
}
