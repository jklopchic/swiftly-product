package product.model;

public class ProductRecord {

    private final Integer productId;
    private final String description;
    private final String displayPrice;
    private final Double calculatorPrice;
    private final String saleDisplayPrice;
    private final Double saleCalculatorPrice;
    private final UnitOfMeasure unitOfMeasure;
    private final String productSize;
    private final Double taxRate;

    public ProductRecord(final int productId,
                         final String description,
                         final String displayPrice,
                         final Double calculatorPrice,
                         final String saleDisplayPrice,
                         final Double saleCalculatorPrice,
                         final UnitOfMeasure unitOfMeasure,
                         final String productSize,
                         final Double taxRate) {
        this.productId = productId;
        this.description = description;
        this.displayPrice = displayPrice;
        this.calculatorPrice = calculatorPrice;
        this.saleDisplayPrice = saleDisplayPrice;
        this.saleCalculatorPrice = saleCalculatorPrice;
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

    public UnitOfMeasure getUnitOfMeasure() {
        return unitOfMeasure;
    }

    public String getProductSize() {
        return productSize;
    }

    public Double getTaxRate() {
        return taxRate;
    }

    class Builder {
        Integer productId;
        String description;
        String displayPrice;
        Double calculatorPrice;
        String saleDisplayPrice;
        Double saleCalculatorPrice;
        UnitOfMeasure unitOfMeasure;
        String productSize;
        Double taxRate;

        public ProductRecord build() {
            return new ProductRecord(productId,
                    description,
                    displayPrice,
                    calculatorPrice,
                    saleDisplayPrice,
                    saleCalculatorPrice,
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

        public Builder displayPrice(final String displayPrice) {
            this.displayPrice = displayPrice;
            return this;
        }

        public Builder calculatorPrice(final Double calculatorPrice) {
            this.calculatorPrice = calculatorPrice;
            return this;
        }

        public Builder saleDisplayPrice(final String saleDisplayPrice) {
            this.saleDisplayPrice = saleDisplayPrice;
            return this;
        }

        public Builder saleCalculatorPrice(final Double saleCalculatorPrice) {
            this.saleCalculatorPrice = saleCalculatorPrice;
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
