package product.parse;

import product.model.ProductRecord;

public class DefaultProductRecordParser implements ProductRecordParser {

    private final StringParser stringParser;
    private final IntegerParser integerParser;

    public static DefaultProductRecordParser construct(final StringParser stringParser,
                                                       final IntegerParser integerParser) {
        return new DefaultProductRecordParser(stringParser, integerParser);
    }

    private DefaultProductRecordParser(final StringParser stringParser,
                                       final IntegerParser integerParser) {

        this.stringParser = stringParser;
        this.integerParser = integerParser;
    }

    @Override
    public ProductRecord parse(final String input) {
        ProductRecord.Builder builder = new ProductRecord.Builder();

        /*
        *
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

        final String unparsedProductId = input.substring(0,8);
        builder.productId(integerParser.parse(unparsedProductId));

        final String unparsedDescription = input.substring(9,68);
        builder.description(stringParser.parse(unparsedDescription));

        final String unparsedProductSize = input.substring(133, 142);
        builder.productSize(stringParser.parse(unparsedProductSize));


        return builder.build();
    }
}
