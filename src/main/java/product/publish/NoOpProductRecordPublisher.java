package product.publish;

import product.model.ProductRecord;

import java.util.List;

public class NoOpProductRecordPublisher implements ProductRecordPublisher {

    public static NoOpProductRecordPublisher construct() {
        return new NoOpProductRecordPublisher();
    }

    private NoOpProductRecordPublisher() {

    }

    @Override
    public void publishProducts(final List<ProductRecord> records) {
        //do nothing
    }
}
