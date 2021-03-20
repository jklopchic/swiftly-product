package product.model;

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

    public String getDisplayPrice() {
        return prices.getDisplayPrice();
    }

    public Double getCalculatorPrice() {
        return prices.getCalculatorPrice();
    }

    public String getSaleDisplayPrice() {
        return prices.getSaleDisplayPrice();
    }

    public Double getSaleCalculatorPrice() {
        return prices.getSaleCalculatorPrice();
    }

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

}
