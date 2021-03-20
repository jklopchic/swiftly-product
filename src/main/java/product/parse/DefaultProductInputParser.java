package product.parse;

import product.model.ProductInputData;
import product.model.ProductInputField;
import product.parse.unit.FlagsParser;
import product.parse.unit.IntegerParser;
import product.parse.unit.StringParser;

public class DefaultProductInputParser implements ProductInputParser {

    private final StringParser stringParser;
    private final IntegerParser integerParser;
    private final FlagsParser flagsParser;

    public static DefaultProductInputParser construct(final StringParser stringParser,
                                                      final IntegerParser integerParser,
                                                      final FlagsParser flagsParser) {
        return new DefaultProductInputParser(stringParser, integerParser, flagsParser);
    }

    private DefaultProductInputParser(final StringParser stringParser,
                                      final IntegerParser integerParser,
                                      final FlagsParser flagsParser) {

        this.stringParser = stringParser;
        this.integerParser = integerParser;
        this.flagsParser = flagsParser;
    }

    @Override
    public ProductInputData parse(final String input) {
        
        final ProductInputData result = ProductInputData.construct();

        for(final ProductInputField field: ProductInputField.values()) {
            final String unparsed = input.substring(field.getZeroIndexedStart(), field.getEnd());
            switch(field.getFieldType()) {

                case String:
                    result.setStringValue(field, stringParser.parse(unparsed));
                    break;

                case Integer:
                    result.setIntegerValue(field, integerParser.parse(unparsed));
                    break;

                case Flags:
                    result.setFlagsValue(field, flagsParser.parse(unparsed, field.getEnd() - field.getZeroIndexedStart()));
                    break;
            }
        }

        return result;
    }
}
