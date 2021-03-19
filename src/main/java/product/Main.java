package product;

import product.parse.ProductRecordParser;
import product.publish.ProductRecordPublisher;

public class Main {

    public static void main(String [] args) {
        final ProductRecordParser parser = null;
        final ProductRecordPublisher publisher = null;

        final ProductCatalogIntegrationService service = ProductCatalogIntegrationService.construct(parser, publisher);

        service.ingestFile("This definitely won't work!");
    }
}
