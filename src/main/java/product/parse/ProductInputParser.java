package product.parse;

import product.model.ProductInputData;
import product.model.ProductParseException;

import java.util.ArrayList;
import java.util.List;

public interface ProductInputParser {

    ProductInputData parse(final String input);

    default List<ProductInputData> bulkParse(final List<String> input) {
        final List<ProductInputData> list = new ArrayList<>();

        for (final String line : input) {
            try {
                list.add(parse(line));
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
