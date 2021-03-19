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
    private final IntegerParser mockIntegerParser = mock(IntegerParser.class);

    private final ProductRecordParser parser = DefaultProductRecordParser.construct(mockStringParser, mockIntegerParser);

    private final String exampleInput = "80000001 Kimchi-flavored white rice                                  00000567 00000000 00000000 00000000 00000000 00000000 NNNNNNNNN      18oz";

    @Test
    public void parserShouldSetProductDescription() {
        final String expected = "Kimchi-flavored white rice";

        final String paddedProductDescription = exampleInput.substring(9,68);

        given(mockStringParser.parse(paddedProductDescription)).willReturn(expected);

        final ProductRecord actualProductRecord = parser.parse(exampleInput);

        final String actual = actualProductRecord.getDescription();

        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void parserShouldSetProductId() {
        final int expected = 80000001;

        final String unparsedProductId = exampleInput.substring(0,8);

        given(mockIntegerParser.parse(unparsedProductId)).willReturn(expected);

        final ProductRecord actualProductRecord = parser.parse(exampleInput);

        final int actual = actualProductRecord.getProductId();

        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void parserShouldSetProductSize() {
        final String expected = "18oz";

        final String paddedProductSize = exampleInput.substring(133, 142);

        given(mockStringParser.parse(paddedProductSize)).willReturn(expected);

        final ProductRecord actualProductRecord = parser.parse(exampleInput);

        final String actual = actualProductRecord.getProductSize();

        assertThat(actual, is(equalTo(expected)));
    }

}