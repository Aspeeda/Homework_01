package exceptions;

public class PathNotValid extends RuntimeException {

    public PathNotValid() {
        super(String.format("Ваш путь ведет в тупик"));
    }
}
