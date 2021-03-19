package product.parse;

public class StringParser {

    public static StringParser construct() {
        return new StringParser();
    }

    private StringParser() {

    }

    public String parse(final String s) {
        return s.trim();
    }
}
