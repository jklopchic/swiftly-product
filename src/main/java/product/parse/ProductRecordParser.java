package product.parse;

import product.model.ProductRecord;

import java.util.List;
import java.util.stream.Collectors;

public interface ProductRecordParser {

    ProductRecord parse(final String input);

    default List<ProductRecord> bulkParse(final List<String> input) {
        return input.stream()
                .map(line -> parse(line))
                .collect(Collectors.toList());
    }
}
