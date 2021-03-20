package product.transform;

import product.model.ProductInputData;
import product.model.ProductRecord;
import product.model.ProductParseException;

import java.util.ArrayList;
import java.util.List;

public interface ProductRecordTransformer {

    ProductRecord transform(final ProductInputData input);

    default List<ProductRecord> bulkTransform(final List<ProductInputData> input) {
        final List<ProductRecord> list = new ArrayList<>();

        for (final ProductInputData line : input) {
            try {
                list.add(transform(line));
            } catch (final ProductParseException e) {
                //todo: add logging or something to deal with bad lines
                continue;
            } catch (final Exception e) {
                //something really bad happened. Should deal with this eventually.
                continue;
            }
        }

        return list;
    }
}
