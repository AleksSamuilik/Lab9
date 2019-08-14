package it.alex.lab9;

@AvailableOperation
public interface Operation {

    String operate(String... number);

    int getNumberOfOperands();

    String getOperationSymbol();

    String getName();

}
