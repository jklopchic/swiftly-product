package product.model;

public class ProductPrices {

    private final String displayPrice;
    private final Double calculatorPrice;
    private final String saleDisplayPrice;
    private final Double saleCalculatorPrice;


    public ProductPrices(final String displayPrice, final Double calculatorPrice, final String saleDisplayPrice, final Double saleCalculatorPrice) {
        this.displayPrice = displayPrice;
        this.calculatorPrice = calculatorPrice;
        this.saleDisplayPrice = saleDisplayPrice;
        this.saleCalculatorPrice = saleCalculatorPrice;
    }

    public String getDisplayPrice() {
        return displayPrice;
    }

    public Double getCalculatorPrice() {
        return calculatorPrice;
    }

    public String getSaleDisplayPrice() {
        return saleDisplayPrice;
    }

    public Double getSaleCalculatorPrice() {
        return saleCalculatorPrice;
    }
}
