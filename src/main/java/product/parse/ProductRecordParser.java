package product.parse;

import product.model.ProductRecord;

import java.util.List;
import java.util.stream.Collectors;

public class ProductRecordParser {

    public ProductRecord parse(final String input) {
        return null;
    }

    public List<ProductRecord> bulkParse(final List<String> input) {
        return input.stream()
                .map(line -> parse(line))
                .collect(Collectors.toList());
    }
}
