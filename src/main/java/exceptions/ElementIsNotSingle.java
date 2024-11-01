package exceptions;

public class ElementIsNotSingle extends RuntimeException {

    public ElementIsNotSingle(String element) {
        super(String.format("Найдено несколько элементов %s", element));
    }
}
