package exceptions;

public class ElementNotFound extends RuntimeException {

    public ElementNotFound(String element) {
        super(String.format("Элемент %s не найден", element));
    }
}
