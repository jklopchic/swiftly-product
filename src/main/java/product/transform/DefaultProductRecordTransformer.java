package product.transform;

import product.model.ProductInputData;
import product.model.ProductRecord;
import product.model.UnitOfMeasure;

import java.math.BigDecimal;
import java.math.RoundingMode;

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

        builder.productId(input.getProductId());

        builder.description(input.getProductDescription());

        setRegularPrice(builder, input);

        setSalePrice(builder, input);

        builder.unitOfMeasure(getUnitOfMeasure(input.getFlags()));

        builder.productSize(input.getProductSize());

        builder.taxRate(getTaxRate(input.getFlags()));

        return builder.build();
    }

    private void setRegularPrice(final ProductRecord.Builder builder, final ProductInputData inputData) {
        if(inputData.getEachPrice() != 0) {
            final double eachPrice = inputData.getEachPrice() / 100.00;

            builder.displayPrice(formatEaches(eachPrice));
            builder.calculatorPrice(eachPrice);
        } else if(inputData.getRegularSplitPrice() != 0) {
            final double splitPrice = inputData.getRegularSplitPrice() / 100.00;
            final double calculatedSplitPrice = round(splitPrice / inputData.getRegularSplitQuantity(), 4);

            builder.displayPrice(formatSplit(splitPrice, inputData.getRegularSplitQuantity()));
            builder.calculatorPrice(calculatedSplitPrice);
        }
    }

    private void setSalePrice(final ProductRecord.Builder builder, final ProductInputData inputData) {
        if(inputData.getSaleEachPrice() != 0) {
            final double eachPrice = inputData.getSaleEachPrice() / 100.00;

            builder.saleDisplayPrice(formatEaches(eachPrice));
            builder.saleCalculatorPrice(eachPrice);
        } else if(inputData.getSaleSplitPrice() != 0) {
            final double splitPrice = inputData.getSaleSplitPrice() / 100.00;
            final double calculatedSplitPrice = round(splitPrice / inputData.getSaleSplitQuantity(), 4);

            builder.saleDisplayPrice(formatSplit(splitPrice, inputData.getSaleSplitQuantity()));
            builder.saleCalculatorPrice(calculatedSplitPrice);
        }
    }

    private String formatEaches(final double eachPrice) {
        return String.format("$%.2f", eachPrice);
    }

    private String formatSplit(final double splitPrice, final int splitQuantity) {
        return String.format("%d for $%.2f", splitQuantity, splitPrice);
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

    private double round(double input, int decimalPlaces) {
        //We can find a more elegant way of doing this if we like.

        return new BigDecimal(input).setScale(decimalPlaces, RoundingMode.HALF_UP)
                                    .doubleValue();
    }


}
