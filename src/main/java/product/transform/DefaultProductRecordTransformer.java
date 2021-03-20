package product.transform;

import product.model.ProductInputData;
import product.model.ProductRecord;
import product.model.UnitOfMeasure;

public class DefaultProductRecordTransformer implements ProductRecordTransformer {

    private final double taxRate;

    public static DefaultProductRecordTransformer construct(final double taxRate) {
        return new DefaultProductRecordTransformer(taxRate);
    }

    private DefaultProductRecordTransformer(final double taxRate) {

        this.taxRate = taxRate;
    }

    @Override
    public ProductRecord transform(final ProductInputData input) {
        final ProductRecord.Builder builder = new ProductRecord.Builder();

        //1:1
        builder.productId(input.getProductId());

        //1:1
        builder.description(input.getProductDescription());


        /*
                Prices can either be an each price (e.g. $1.00 each) or a split price (e.g. 2 for $0.99).
                Only one price (each or split) per price level (regular or sale) will exist.
                The price data for an undefined price will be all 0's. #this has been parsed to be zero.
                If a price is split pricing, the Calculator Price is Split Price / Split Quantity
         */
        builder.displayPrice(null);
        builder.saleDisplayPrice(null);

        builder.calculatorPrice(null);
        builder.saleCalculatorPrice(null);

        builder.unitOfMeasure(getUnitOfMeasure(input.getFlags()));

        builder.productSize(input.getProductSize());

        builder.taxRate(getTaxRate(input.getFlags()));

        return builder.build();
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
