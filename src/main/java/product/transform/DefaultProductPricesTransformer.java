package product.transform;

import product.model.ProductInputData;
import product.model.ProductPrices;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static product.model.ProductInputField.*;

public class DefaultProductPricesTransformer implements ProductPricesTransformer {
    
    private final int roundingDecimalPlaces;
    
    public static DefaultProductPricesTransformer construct(final int roundingDecimalPlaces) {
        return new DefaultProductPricesTransformer(roundingDecimalPlaces);
    }
    
    private DefaultProductPricesTransformer(final int roundingDecimalPlaces) {

        this.roundingDecimalPlaces = roundingDecimalPlaces;
    }
    
    /*
          This is the most complicated method in the entire project, and there is a pretty strong argument to somehow
          break it up into multiple methods/classes, but as it stands it would be necessary to repeat some of the checks
          to break it up. If it remains exactly this complicated forever, that's not too bad. Once more requirements 
          are added, further abstraction will be required.
     */
    public ProductPrices transformPrices(final ProductInputData inputData) {
        String displayPrice;
        double calculatorPrice;
        String saleDisplayPrice;
        double saleCalculatorPrice;
        
        final int eachPriceInCents = inputData.getIntegerValue(RegularEachPrice);
        final int salePriceInCents = inputData.getIntegerValue(SaleEachPrice);
        final int regularSplitPriceInCents = inputData.getIntegerValue(RegularSplitPrice);
        final int saleSplitPriceInCents = inputData.getIntegerValue(SaleSplitPrice);
        
        final int regularSplitQuantity = inputData.getIntegerValue(RegularSplitQuantity);
        final int saleSplitQuantity = inputData.getIntegerValue(SaleSplitQuantity);

        if(eachPriceInCents != 0) {
            final double eachPrice = eachPriceInCents / 100.00;

            displayPrice = formatEaches(eachPrice);
            calculatorPrice = eachPrice;
        } else if(regularSplitPriceInCents != 0) {
            final double splitPrice = regularSplitPriceInCents / 100.00;
            final double calculatedSplitPrice = round(splitPrice / regularSplitQuantity, roundingDecimalPlaces;

            displayPrice = formatSplit(splitPrice, regularSplitQuantity);
            calculatorPrice = calculatedSplitPrice;
        } else {
            displayPrice = "$0.00";
            calculatorPrice = 0;
        }

        if(salePriceInCents != 0) {
            final double eachPrice = salePriceInCents / 100.00;

            saleDisplayPrice = formatEaches(eachPrice);
            saleCalculatorPrice = eachPrice;
        } else if(saleSplitPriceInCents != 0) {
            final double splitPrice = saleSplitPriceInCents / 100.00;
            final double calculatedSplitPrice = round(splitPrice / saleSplitQuantity, roundingDecimalPlaces);

            saleDisplayPrice = formatSplit(splitPrice, saleSplitQuantity);
            saleCalculatorPrice = calculatedSplitPrice;
        } else {
            saleDisplayPrice = "$0.00";
            saleCalculatorPrice = 0;
        }

        return new ProductPrices(displayPrice, calculatorPrice, saleDisplayPrice, saleCalculatorPrice);
    }

    private String formatEaches(final double eachPrice) {
        return String.format("$%.2f", eachPrice);
    }

    private String formatSplit(final double splitPrice, final int splitQuantity) {
        return String.format("%d for $%.2f", splitQuantity, splitPrice);
    }

    private double round(double input, int decimalPlaces) {
        //We can find a more elegant way of doing this if we like.

        return new BigDecimal(input).setScale(decimalPlaces, RoundingMode.HALF_UP).doubleValue();
    }
}
