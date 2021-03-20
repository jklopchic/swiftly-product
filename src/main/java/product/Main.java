package product;

import product.parse.DefaultProductImportParser;
import product.parse.ProductInputParser;
import product.parse.unit.FlagsParser;
import product.parse.unit.IntegerParser;
import product.parse.unit.StringParser;
import product.transform.DefaultProductRecordTransformer;
import product.transform.ProductRecordTransformer;
import product.publish.ProductRecordPublisher;

public class Main {

    public static void main(String [] args) {
        final double taxRate = 7.775;

        final StringParser stringParser = StringParser.construct();
        final IntegerParser integerParser = IntegerParser.construct();;
        final FlagsParser flagsParser = FlagsParser.construct();

        final ProductInputParser inputParser = DefaultProductImportParser.construct(stringParser, integerParser, flagsParser);
        final ProductRecordTransformer recordParser = DefaultProductRecordTransformer.construct(taxRate);
        final ProductRecordPublisher publisher = null;

        final ProductCatalogIntegrationService service = ProductCatalogIntegrationService.construct(inputParser, recordParser, publisher);

        service.ingestFile("This definitely won't work!");
    }
}
