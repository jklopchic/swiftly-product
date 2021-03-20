package product.factory;

import product.ProductRecordIngester;
import product.parse.DefaultProductInputParser;
import product.parse.ProductInputParser;
import product.parse.unit.FlagsParser;
import product.parse.unit.IntegerParser;
import product.parse.unit.StringParser;
import product.transform.DefaultProductPricesTransformer;
import product.transform.DefaultProductRecordTransformer;
import product.transform.ProductRecordTransformer;

public class ProductRecordIngestorFactory {
    
    public static ProductRecordIngestorFactory construct() {
        return new ProductRecordIngestorFactory();
    }
    
    private ProductRecordIngestorFactory() {
        
    }    
    
    public ProductRecordIngester create() {
        final StringParser stringParser = StringParser.construct();
        final IntegerParser integerParser = IntegerParser.construct();
        final FlagsParser flagsParser = FlagsParser.construct();
        final DefaultProductPricesTransformer productPricesTransformer = DefaultProductPricesTransformer.construct(ProductRecordIngester.DEFAULT_PRICE_ROUNDING_DECIMALS);

        final ProductInputParser inputParser = DefaultProductInputParser.construct(stringParser, integerParser, flagsParser);
        final ProductRecordTransformer recordParser = DefaultProductRecordTransformer.construct(ProductRecordIngester.DEFAULT_TAX_RATE, productPricesTransformer);

        return ProductRecordIngester.construct(inputParser, recordParser);
    }
}
