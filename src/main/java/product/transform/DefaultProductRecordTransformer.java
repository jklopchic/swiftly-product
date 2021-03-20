package product.transform;

import product.model.ProductInputData;
import product.model.ProductRecord;
import product.model.UnitOfMeasure;

import static product.model.ProductInputField.*;

public class DefaultProductRecordTransformer implements ProductRecordTransformer {

    private final double taxRate;
    private final DefaultProductPricesTransformer pricesTransformer;

    public static DefaultProductRecordTransformer construct(final double taxRate,
                                                            final DefaultProductPricesTransformer pricesTransformer) {
        return new DefaultProductRecordTransformer(taxRate, pricesTransformer);
    }

    private DefaultProductRecordTransformer(final double taxRate, 
                                            final DefaultProductPricesTransformer pricesTransformer) {

        this.taxRate = taxRate;
        this.pricesTransformer = pricesTransformer;
    }

    @Override
    public ProductRecord transform(final ProductInputData input) {
        final ProductRecord.Builder record = new ProductRecord.Builder();

        record.productId(input.getIntegerValue(ProductId));

        record.description(input.getStringValue(ProductDescription));

        record.prices(pricesTransformer.transformPrices(input));

        record.unitOfMeasure(getUnitOfMeasure(input.getFlags()));

        record.productSize(input.getStringValue(ProductSize));

        record.taxRate(getTaxRate(input.getFlags()));

        return record.build();
    }

    private double getTaxRate(boolean[] flags) {
        if(flags[4]) {
            return taxRate;
        }
        return 0.0;
    }

    private UnitOfMeasure getUnitOfMeasure(boolean[] flags) {
        if(flags[2]) {
            return UnitOfMeasure.Pound;
        }
        return UnitOfMeasure.Each;
    }

}
