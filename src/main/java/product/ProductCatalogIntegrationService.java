package product;

import product.model.ProductRecord;
import product.parse.ProductRecordParser;
import product.publish.ProductRecordPublisher;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ProductCatalogIntegrationService {

    private final ProductRecordParser parser;
    private final ProductRecordPublisher publisher;

    public static ProductCatalogIntegrationService construct(final ProductRecordParser parser,
                                                             final ProductRecordPublisher publisher) {
        return new ProductCatalogIntegrationService(parser, publisher);
    }

    private ProductCatalogIntegrationService(final ProductRecordParser parser,
                                             final ProductRecordPublisher publisher) {
        this.parser = parser;
        this.publisher = publisher;
    }

    public List<ProductRecord> ingestFile(final String asciiFile) {
        final List<String> lines = getLines(asciiFile);

        final List<ProductRecord> records = parser.bulkParse(lines);

        publisher.publishProducts(records);

        return records;
    }

    private List<String> getLines(String asciiFile) {
        return Arrays.stream(asciiFile.split("\n")).collect(Collectors.toList());
    }
}
