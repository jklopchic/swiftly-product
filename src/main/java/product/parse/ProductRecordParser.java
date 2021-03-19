package product.parse;

import product.model.ProductInputData;
import product.model.ProductRecord;

import java.util.ArrayList;
import java.util.List;

public interface ProductRecordParser {

    ProductRecord parse(final ProductInputData input);

    default List<ProductRecord> bulkParse(final List<ProductInputData> input) {
        final List<ProductRecord> list = new ArrayList<>();

        for (final ProductInputData line : input) {
            try {
                list.add(parse(line));
            } catch (final ProductParseException e) {
                //todo: add logging or something to deal with bad lines
                continue;
            }
        }

        return list;
    }
}
