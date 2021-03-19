package product.parse;

import product.model.ProductRecord;

public class DefaultProductRecordParser implements ProductRecordParser {

    private final StringParser stringParser;

    public static DefaultProductRecordParser construct(final StringParser stringParser) {
        return new DefaultProductRecordParser(stringParser);
    }

    private DefaultProductRecordParser(final StringParser stringParser) {

        this.stringParser = stringParser;
    }

    @Override
    public ProductRecord parse(final String input) {
        ProductRecord.Builder builder = new ProductRecord.Builder();

        builder.description(stringParser.parse(input.substring(10,69)));

        return builder.build();
    }
}
