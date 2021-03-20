package product.transform;

import product.model.ProductInputData;
import product.model.ProductParseException;
import product.model.ProductPrices;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class DefaultProductPricesTransformer implements ProductPricesTransformer {
    
    public static DefaultProductPricesTransformer construct() {
        return new DefaultProductPricesTransformer();
    }
    
    private DefaultProductPricesTransformer() {
        
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

        if(inputData.getEachPrice() != 0) {
            final double eachPrice = inputData.getEachPrice() / 100.00;

            displayPrice = formatEaches(eachPrice);
            calculatorPrice = eachPrice;
        } else if(inputData.getRegularSplitPrice() != 0) {
            final double splitPrice = inputData.getRegularSplitPrice() / 100.00;
            final double calculatedSplitPrice = round(splitPrice / inputData.getRegularSplitQuantity(), 4);

            displayPrice = formatSplit(splitPrice, inputData.getRegularSplitQuantity());
            calculatorPrice = calculatedSplitPrice;
        } else {
            throw new ProductParseException();
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
        } else {
            throw new ProductParseException();
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
