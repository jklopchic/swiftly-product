package product;

import product.model.ProductRecord;
import product.parse.DefaultProductInputParser;
import product.parse.ProductInputParser;
import product.parse.unit.FlagsParser;
import product.parse.unit.IntegerParser;
import product.parse.unit.StringParser;
import product.publish.NoOpProductRecordPublisher;
import product.transform.DefaultProductRecordTransformer;
import product.transform.ProductRecordTransformer;
import product.publish.ProductRecordPublisher;

import java.io.File;
import java.util.List;

public class Main {

    public static void main(String [] args) {
        final double taxRate = 7.775;

        final StringParser stringParser = StringParser.construct();
        final IntegerParser integerParser = IntegerParser.construct();;
        final FlagsParser flagsParser = FlagsParser.construct();

        final ProductInputParser inputParser = DefaultProductInputParser.construct(stringParser, integerParser, flagsParser);
        final ProductRecordTransformer recordParser = DefaultProductRecordTransformer.construct(taxRate);
        final ProductRecordPublisher publisher = NoOpProductRecordPublisher.construct();

        final ProductCatalogIntegrationService service = ProductCatalogIntegrationService.construct(inputParser, recordParser, publisher);

        final File file = new File("src/test/java/resources/input.txt");

        final List<ProductRecord> records = service.ingestFile(file);

        System.out.println("done!");
    }

}
