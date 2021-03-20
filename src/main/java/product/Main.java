package product;

import product.factory.ProductRecordIngestorFactory;
import product.model.ProductRecord;
import java.io.File;
import java.util.List;

public class Main {

    public static void main(String [] args) {
        final ProductRecordIngester ingester = ProductRecordIngestorFactory.construct().create();

        final File file = new File("src/test/java/resources/input.txt");

        final List<ProductRecord> records = ingester.ingestFile(file);

        System.out.println(String.format("Ingested %d records", records.size()));
    }

}
