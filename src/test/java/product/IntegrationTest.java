package product;

import com.google.common.collect.Lists;
import org.junit.Test;
import product.factory.ProductRecordIngestorFactory;
import product.model.ProductPrices;
import product.model.ProductRecord;
import product.model.UnitOfMeasure;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.io.File;
import java.util.List;

public class IntegrationTest {
    
    private final ProductRecordIngester ingester = ProductRecordIngestorFactory.construct().create();
    
    @Test
    public void testIngest() {
        final ProductRecord kimchiRice = new ProductRecord(80000001,
                "Kimchi-flavored white rice",
                new ProductPrices("$5.67", 5.67, "$0.00", 0.0),
                UnitOfMeasure.Each,
        "18oz",
        0.0);

        final ProductRecord soda = new ProductRecord(14963801,
                "Generic Soda 12-pack",
                new ProductPrices("2 for $13.00", 6.5, "$5.49", 5.49),
                UnitOfMeasure.Each,
                "12x12oz",
                7.775);

        final ProductRecord cigarettes = new ProductRecord(40123401,
                "Marlboro Cigarettes",
                new ProductPrices("$10.00", 10.0, "$5.49", 5.49),
                UnitOfMeasure.Each,
                "",
                0.0);

        final ProductRecord apples = new ProductRecord(50133333,
                "Fuji Apples (Organic)",
                new ProductPrices("$3.49", 3.49, "$0.00", 0.0),
                UnitOfMeasure.Pound,
                "lb",
                0.0);
        
        final List<ProductRecord> expected = Lists.newArrayList(kimchiRice, soda, cigarettes, apples);
        
        final File file = new File("src/test/java/resources/input.txt");
        
        final List<ProductRecord> actual = ingester.ingestFile(file);

        assertThat(actual, is(equalTo(expected)));
    }
    
}
