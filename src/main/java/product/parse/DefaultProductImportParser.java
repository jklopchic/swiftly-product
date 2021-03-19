package product.parse;

import product.model.ProductInputData;
import product.parse.unit.IntegerParser;
import product.parse.unit.StringParser;

public class DefaultProductImportParser implements ProductInputParser {

    private final StringParser stringParser;
    private final IntegerParser integerParser;

    public static DefaultProductImportParser construct(final StringParser stringParser,
                                                       final IntegerParser integerParser) {
        return new DefaultProductImportParser(stringParser, integerParser);
    }

    private DefaultProductImportParser(final StringParser stringParser,
                                       final IntegerParser integerParser) {

        this.stringParser = stringParser;
        this.integerParser = integerParser;
    }

    @Override
    public ProductInputData parse(final String input) {

        /*
        * to use java String.substring, you need to use substring(Start-1, End)
        * These are Inclusive, Inclusive, but based on an index that starts at one
        * That means the true index of the start is Start-1
        * The true index of the end is End-1, but this is inclusive, and java uses exclusive,
        * so use End-1+1 = End
        * Start	End [Inclusive]	Name	    Type
            1	8	Product ID	            Number
            10	68	Product Description	    String
            70	77	Regular Each Price	    Currency
            79	86	Sale Each Price	        Currency
            88	95	Regular Split Price	    Currency
            97	104	Sale Split Price	    Currency
            106	113	Regular Split Quantity	Number
            115	122	Sale Split Quantity	    Number
            124	132	Flags	                Flags
            134	142	Product Size	        String
        *
        * */

        final int productId = integerParser.parse(input.substring(0, 8));

        final String productDescription = stringParser.parse(input.substring(9, 68));

        final int regularEachPrice = integerParser.parse(input.substring(69, 77));

        final int saleEachPrice = integerParser.parse(input.substring(78, 86));

        final int regularSplitPrice = integerParser.parse(input.substring(87, 95));

        final int saleSplitPrice = integerParser.parse(input.substring(96, 104));

        final int regularSplitQuantity = integerParser.parse(input.substring(105, 113));

        final int saleSplitQuantity = integerParser.parse(input.substring(114, 122));

        final String productSize = stringParser.parse(input.substring(133, 142));

        return new ProductInputData(productId,
                                    productDescription,
                                    regularEachPrice,
                                    saleEachPrice,
                                    regularSplitPrice,
                                    saleSplitPrice,
                                    regularSplitQuantity,
                                    saleSplitQuantity,
                                    null,
                                    productSize);
    }
}
