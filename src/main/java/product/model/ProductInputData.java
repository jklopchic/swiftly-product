package product.model;

import com.google.common.collect.Maps;

import java.util.Map;

import static product.model.ProductInputField.*;

public class ProductInputData {
    
    /*
    
        I've chosen to only implement strings, integers and flags. There isn't any difference between currency and number 
        in the current specification, on both the parse side, and the storage side. Just treat currency values as "USD in cents"
        
        If more granular currency requirements arise, adding a fourth map to store them isn't terribly difficult. 
        
     */
    
    private final Map<ProductInputField, String> stringFieldValues = Maps.newHashMap();
    private final Map<ProductInputField, Integer> integerFieldValues = Maps.newHashMap();
    private final Map<ProductInputField, boolean[]> flagsFieldValues = Maps.newHashMap();
        
    public static ProductInputData construct() {
        return new ProductInputData();
    }
    private ProductInputData() {
        
    }
    
    public int getIntegerValue(final ProductInputField key) {
        return integerFieldValues.get(key);
    }

    public String getStringValue(final ProductInputField key) {
        return stringFieldValues.get(key);
    }

    public boolean[] getFlagsValue(final ProductInputField key) {
        return flagsFieldValues.get(key);
    }
    
    public void setIntegerValue(final ProductInputField field, final int value) {
        integerFieldValues.put(field, value);
    }

    public void setStringValue(final ProductInputField field, final String value) {
        stringFieldValues.put(field, value);
    }

    public void setFlagsValue(final ProductInputField field, final boolean[] value) {
        flagsFieldValues.put(field, value);
    }
}
