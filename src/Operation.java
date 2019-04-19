import java.util.Map;

public interface Operation {

    String operate(String... number);

    int getNumberOfOperands();

    Map getSymbolOperation();

    String getName();

}
