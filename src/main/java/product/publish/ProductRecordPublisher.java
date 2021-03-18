package product.publish;

import product.model.ProductRecord;

import java.util.List;

public interface ProductRecordPublisher {

    void publishProducts(final List<ProductRecord> records);
}
