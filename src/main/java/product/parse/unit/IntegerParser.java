package product.parse.unit;

import product.model.ProductParseException;

public class IntegerParser {
    
    /*
    
           I've chosen to only implement 
    
     */

    public static IntegerParser construct() {
        return new IntegerParser();
    }

    private IntegerParser() {

    }

    public Integer parse(final String s) {
        try {
            return Integer.parseInt(s);
        } catch (Exception e) {
            throw new ProductParseException();
        }
    }
}
