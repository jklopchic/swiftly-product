package product.model;

import com.google.common.base.Objects;

public class ProductRecord {

    private final Integer productId;
    private final String description;
    private final ProductPrices prices; 
    private final UnitOfMeasure unitOfMeasure;
    private final String productSize;
    private final Double taxRate;

    public ProductRecord(final Integer productId,
                         final String description,
                         final ProductPrices prices,
                         final UnitOfMeasure unitOfMeasure,
                         final String productSize,
                         final Double taxRate) {
        this.productId = productId;
        this.description = description;
        this.prices = prices;
        this.unitOfMeasure = unitOfMeasure;
        this.productSize = productSize;
        this.taxRate = taxRate;
    }

    public int getProductId() {
        return productId;
    }

    public String getDescription() {
        return description;
    }
    
    public ProductPrices getPrices() { return prices; }

    public UnitOfMeasure getUnitOfMeasure() {
        return unitOfMeasure;
    }

    public String getProductSize() {
        return productSize;
    }

    public Double getTaxRate() {
        return taxRate;
    }

    public static class Builder {
        Integer productId;
        String description;
        ProductPrices prices; 
        UnitOfMeasure unitOfMeasure;
        String productSize;
        Double taxRate;

        public ProductRecord build() {
            return new ProductRecord(productId,
                    description,
                    prices,
                    unitOfMeasure,
                    productSize,
                    taxRate);
        }

        public Builder productId(final Integer productId) {
            this.productId = productId;
            return this;
        }

        public Builder description(final String description) {
            this.description = description;
            return this;
        }

        public Builder prices(final ProductPrices prices) {
            this.prices = prices;
            return this;
        }

        public Builder unitOfMeasure(final UnitOfMeasure unitOfMeasure) {
            this.unitOfMeasure = unitOfMeasure;
            return this;
        }

        public Builder productSize(final String productSize) {
            this.productSize = productSize;
            return this;
        }

        public Builder taxRate(final Double taxRate) {
            this.taxRate = taxRate;
            return this;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductRecord)) return false;
        ProductRecord that = (ProductRecord) o;
        return Objects.equal(getProductId(), that.getProductId()) && 
                Objects.equal(getDescription(), that.getDescription()) && 
                Objects.equal(getPrices(), that.getPrices()) && 
                getUnitOfMeasure() == that.getUnitOfMeasure() && 
                Objects.equal(getProductSize(), that.getProductSize()) && 
                Objects.equal(getTaxRate(), that.getTaxRate());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getProductId(), getDescription(), getPrices(), getUnitOfMeasure(), getProductSize(), getTaxRate());
    }
}
