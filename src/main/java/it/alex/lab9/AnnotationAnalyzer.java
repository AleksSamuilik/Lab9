package it.alex.lab9;

import org.reflections.Reflections;

import java.util.Set;

public class AnnotationAnalyzer {

    public Set reflectionsScan() {
        Reflections reflections = new Reflections("it.alex.lab9");
        return reflections.getTypesAnnotatedWith(AvailableOperation.class);
    }
}