package product.model;

public enum ProductInputField {
   
    ProductId ("Product ID", ProductInputFieldType.Integer, 1, 8),
    ProductDescription ("Product Description", ProductInputFieldType.String, 10, 68),
    RegularEachPrice ("Regular Each Price", ProductInputFieldType.Integer, 70, 77),
    SaleEachPrice ("Sale Each Price", ProductInputFieldType.Integer, 79, 86),
    RegularSplitPrice ("Regular Split Price", ProductInputFieldType.Integer, 88, 95),
    SaleSplitPrice ("Sale Split Price", ProductInputFieldType.Integer, 97, 104),
    RegularSplitQuantity ("Regular Split Quantity", ProductInputFieldType.Integer, 106, 113),
    SaleSplitQuantity ("Sale Split Quantity", ProductInputFieldType.Integer, 115, 122),
    Flags ("Flags", ProductInputFieldType.Flags, 124, 132),
    ProductSize ("Product Size", ProductInputFieldType.String, 134, 142)    
    ;

    ProductInputField(final String name, final ProductInputFieldType fieldType, final int start, final int end) {
        this.name = name;
        this.fieldType = fieldType;
        this.start = start;
        this.end = end;
    }
    
    private String name;
    private ProductInputFieldType fieldType;
    private int start;
    private int end;

    public String getName() {
        return name;
    }

    public ProductInputFieldType getFieldType() {
        return fieldType;
    }
    
    @Deprecated
    public int getStart() {
        return start;
    }

    //The start values are based on the spec, need to adjust for zero-based indexing
    public int getZeroIndexedStart() {
        return start-1;
    }
    
    public int getEnd() {
        return end;
    }
    
    
}
