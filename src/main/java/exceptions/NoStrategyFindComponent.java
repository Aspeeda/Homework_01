package exceptions;

public class NoStrategyFindComponent extends RuntimeException {

    public NoStrategyFindComponent() {
        super(String.format("Отсутствует стратегия поиска элементов"));
    }
}
