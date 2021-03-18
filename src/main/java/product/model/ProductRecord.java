package product.model;

public class ProductRecord {

    private final int productId;
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
}
