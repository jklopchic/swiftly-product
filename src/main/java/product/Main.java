package product;

import product.parse.ProductInputParser;
import product.parse.ProductRecordParser;
import product.publish.ProductRecordPublisher;

public class Main {

    public static void main(String [] args) {
        final ProductInputParser inputParser = null;
        final ProductRecordParser recordParser = null;
        final ProductRecordPublisher publisher = null;

        final ProductCatalogIntegrationService service = ProductCatalogIntegrationService.construct(inputParser, recordParser, publisher);

        service.ingestFile("This definitely won't work!");
    }
}
