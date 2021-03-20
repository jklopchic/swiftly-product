package product.transform;

import product.model.ProductInputData;
import product.model.ProductPrices;
import product.model.ProductRecord;
import product.model.UnitOfMeasure;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static product.model.ProductInputField.*;

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
        final ProductRecord.Builder record = new ProductRecord.Builder();

        record.productId(input.getIntegerValue(ProductId));

        record.description(input.getStringValue(ProductDescription));

        record.prices(getPrices(input));

        record.unitOfMeasure(getUnitOfMeasure(input.getFlags()));

        record.productSize(input.getStringValue(ProductSize));

        record.taxRate(getTaxRate(input.getFlags()));

        return record.build();
    }

    private ProductPrices getPrices(final ProductInputData inputData) {
        String displayPrice = "";
        double calculatorPrice = 0;
        String saleDisplayPrice = "";
        double saleCalculatorPrice = 0;
        
        if(inputData.getEachPrice() != 0) {
            final double eachPrice = inputData.getEachPrice() / 100.00;

            displayPrice = formatEaches(eachPrice);
            calculatorPrice = eachPrice;
        } else if(inputData.getRegularSplitPrice() != 0) {
            final double splitPrice = inputData.getRegularSplitPrice() / 100.00;
            final double calculatedSplitPrice = round(splitPrice / inputData.getRegularSplitQuantity(), 4);

            displayPrice = formatSplit(splitPrice, inputData.getRegularSplitQuantity());
            calculatorPrice = calculatedSplitPrice;
        }

        if(inputData.getSaleEachPrice() != 0) {
            final double eachPrice = inputData.getSaleEachPrice() / 100.00;

            saleDisplayPrice = formatEaches(eachPrice);
            saleCalculatorPrice = eachPrice;
        } else if(inputData.getSaleSplitPrice() != 0) {
            final double splitPrice = inputData.getSaleSplitPrice() / 100.00;
            final double calculatedSplitPrice = round(splitPrice / inputData.getSaleSplitQuantity(), 4);

            saleDisplayPrice = formatSplit(splitPrice, inputData.getSaleSplitQuantity());
            saleCalculatorPrice = calculatedSplitPrice;
        }
        
        return new ProductPrices(displayPrice, calculatorPrice, saleDisplayPrice, saleCalculatorPrice);
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
