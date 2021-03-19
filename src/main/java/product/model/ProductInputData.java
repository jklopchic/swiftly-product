package product.model;

public class ProductInputData {

    private final int productId;
    private final String productDescription;
    private final int eachPrice;
    private final int saleEachPrice;
    private final int regularSplitPrice;
    private final int saleSplitPrice;
    private final int regularSplitQuantity;
    private final int saleSplitQuantity;
    private final boolean[] flags;
    private final String productSize;

    public ProductInputData(final int productId,
                            final String productDescription,
                            final int eachPrice,
                            final int saleEachPrice,
                            final int regularSplitPrice,
                            final int saleSplitPrice,
                            final int regularSplitQuantity,
                            final int saleSplitQuantity,
                            final boolean[] flags,
                            final String productSize) {
        this.productId = productId;
        this.productDescription = productDescription;
        this.eachPrice = eachPrice;
        this.saleEachPrice = saleEachPrice;
        this.regularSplitPrice = regularSplitPrice;
        this.saleSplitPrice = saleSplitPrice;
        this.regularSplitQuantity = regularSplitQuantity;
        this.saleSplitQuantity = saleSplitQuantity;
        this.flags = flags;
        this.productSize = productSize;
    }

    public int getProductId() {
        return productId;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public int getEachPrice() {
        return eachPrice;
    }

    public int getSaleEachPrice() {
        return saleEachPrice;
    }

    public int getRegularSplitPrice() {
        return regularSplitPrice;
    }

    public int getSaleSplitPrice() {
        return saleSplitPrice;
    }

    public int getRegularSplitQuantity() {
        return regularSplitQuantity;
    }

    public int getSaleSplitQuantity() {
        return saleSplitQuantity;
    }

    public boolean[] getFlags() {
        return flags;
    }

    public String getProductSize() {
        return productSize;
    }
}
