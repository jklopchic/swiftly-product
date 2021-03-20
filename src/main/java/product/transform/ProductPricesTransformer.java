package product.transform;

import product.model.ProductInputData;
import product.model.ProductPrices;

public interface ProductPricesTransformer {

    ProductPrices transformPrices(final ProductInputData inputData);
    
}
