package product.parse.unit;

import product.model.ProductParseException;

public class FlagsParser {

    public static FlagsParser construct() {
        return new FlagsParser();
    }

    private FlagsParser() {

    }

    public boolean[] parse(final String input, final int expectedFlags) {
        final String trimmedInput = input.trim();

        if(trimmedInput.length() != expectedFlags) {
            throw new ProductParseException();
        }

        boolean [] result = new boolean [expectedFlags];

        final char[] inputCharacters = trimmedInput.toCharArray();
        for (int i = 0, charArrayLength = inputCharacters.length; i < charArrayLength; i++) {

            switch (inputCharacters[i]) {
                case 'N' : result[i] = false;
                            break;

                case 'Y' : result[i] = true;
                            break;

                default: throw new ProductParseException();
            }
        }

        return result;
    }

}
