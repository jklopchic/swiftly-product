package product.transform;

import product.model.ProductInputData;
import product.model.ProductRecord;
import product.model.UnitOfMeasure;

public class DefaultProductRecordTransformer implements ProductRecordTransformer {


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

        //if flag 3 is set, Pound, otherwise, Each.
        builder.unitOfMeasure(UnitOfMeasure.Each);

        //1:1
        builder.productSize(input.getProductSize());

        //always 7.775, if flag 5 is set, otherwize zero (not taxable)
        builder.taxRate(0.0);

        return null;
    }
}
