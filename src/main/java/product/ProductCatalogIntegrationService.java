package product;

import product.model.ProductInputData;
import product.model.ProductRecord;
import product.parse.ProductInputParser;
import product.parse.ProductRecordTransformer;
import product.publish.ProductRecordPublisher;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ProductCatalogIntegrationService {

    private final ProductInputParser inputParser;
    private final ProductRecordTransformer recordParser;
    private final ProductRecordPublisher publisher;

    public static ProductCatalogIntegrationService construct(final ProductInputParser parser,
                                                             final ProductRecordTransformer recordParser,
                                                             final ProductRecordPublisher publisher) {
        return new ProductCatalogIntegrationService(parser, recordParser, publisher);
    }

    private ProductCatalogIntegrationService(final ProductInputParser inputParser,
                                             final ProductRecordTransformer recordParser,
                                             final ProductRecordPublisher publisher) {
        this.inputParser = inputParser;
        this.recordParser = recordParser;
        this.publisher = publisher;
    }

    public List<ProductRecord> ingestFile(final String asciiFile) {
        final List<String> lines = getLines(asciiFile);

        final List<ProductInputData> inputData = inputParser.bulkParse(lines);

        final List<ProductRecord> records = recordParser.bulkTransform(inputData);

        publisher.publishProducts(records);

        return records;
    }

    private List<String> getLines(String asciiFile) {
        return Arrays.stream(asciiFile.split("\n")).collect(Collectors.toList());
    }
}
