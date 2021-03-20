package product.model;

import com.google.common.base.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductPrices)) return false;
        ProductPrices that = (ProductPrices) o;
        return Objects.equal(getDisplayPrice(), that.getDisplayPrice()) && 
                Objects.equal(getCalculatorPrice(), that.getCalculatorPrice()) && 
                Objects.equal(getSaleDisplayPrice(), that.getSaleDisplayPrice()) && 
                Objects.equal(getSaleCalculatorPrice(), that.getSaleCalculatorPrice());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getDisplayPrice(), getCalculatorPrice(), getSaleDisplayPrice(), getSaleCalculatorPrice());
    }
}
