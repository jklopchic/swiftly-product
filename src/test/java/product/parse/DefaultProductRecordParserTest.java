package product.parse;

import org.junit.Test;
import product.model.ProductRecord;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class DefaultProductRecordParserTest {

    private final StringParser mockStringParser = mock(StringParser.class);

    private final ProductRecordParser parser = DefaultProductRecordParser.construct(mockStringParser);

    @Test
    public void parserShouldSetProductDescription() {
        final String expected = "Product Description";

        final String paddedProductDescription = padToLength(expected, 59);

        final String input = "012345678 " + paddedProductDescription + " 01234567890";

        given(mockStringParser.parse(paddedProductDescription)).willReturn(expected);

        final ProductRecord actualProductRecord = parser.parse(input);

        final String actual = actualProductRecord.getDescription();

        assertThat(actual, is(equalTo(expected)));
    }

    private String padToLength(String input, int desiredLength) {
        return String.format("%" + desiredLength + "s", input);
    }

}