package product.parse;

import org.junit.Test;
import product.model.ProductInputData;
import product.model.ProductRecord;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class DefaultProductInputParserTest {

    private final StringParser mockStringParser = mock(StringParser.class);
    private final IntegerParser mockIntegerParser = mock(IntegerParser.class);

    private final ProductInputParser parser = DefaultProductImportParser.construct(mockStringParser, mockIntegerParser);

    private final String exampleInput = "80000001 Kimchi-flavored white rice                                  00000567 00000001 00000002 00000003 00000004 00000005 NNNNNNNNN      18oz";

    @Test
    public void parserShouldSetProductDescription() {
        final String expected = "Kimchi-flavored white rice";

        final String paddedProductDescription = exampleInput.substring(9,68);

        given(mockStringParser.parse(paddedProductDescription)).willReturn(expected);

        final ProductInputData actualProductRecord = parser.parse(exampleInput);

        final String actual = actualProductRecord.getProductDescription();

        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void parserShouldSetProductId() {
        final int expected = 80000001;

        final String unparsedProductId = exampleInput.substring(0,8);

        given(mockIntegerParser.parse(unparsedProductId)).willReturn(expected);

        final ProductInputData actualProductRecord = parser.parse(exampleInput);

        final int actual = actualProductRecord.getProductId();

        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void parserShouldSetRegularEachPrice() {
        final int expected = 567;

        final String unparsedProductId = exampleInput.substring(69, 77);

        given(mockIntegerParser.parse(unparsedProductId)).willReturn(expected);

        final ProductInputData actualProductRecord = parser.parse(exampleInput);

        final int actual = actualProductRecord.getEachPrice();

        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void parserShouldSetSaleEachPrice() {
        final int expected = 1;

        final String unparsedProductId = exampleInput.substring(78, 86);

        given(mockIntegerParser.parse(unparsedProductId)).willReturn(expected);

        final ProductInputData actualProductRecord = parser.parse(exampleInput);

        final int actual = actualProductRecord.getSaleEachPrice();

        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void parserShouldSetRegularSplitPrice() {
        final int expected = 2;

        final String unparsedProductId = exampleInput.substring(87, 95);

        given(mockIntegerParser.parse(unparsedProductId)).willReturn(expected);

        final ProductInputData actualProductRecord = parser.parse(exampleInput);

        final int actual = actualProductRecord.getRegularSplitPrice();

        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void parserShouldSetSaleSplitPrice() {
        final int expected = 3;

        final String unparsedProductId = exampleInput.substring(96, 104);

        given(mockIntegerParser.parse(unparsedProductId)).willReturn(expected);

        final ProductInputData actualProductRecord = parser.parse(exampleInput);

        final int actual = actualProductRecord.getSaleSplitPrice();

        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void parserShouldSetRegularSplitQuantity() {
        final int expected = 4;

        final String unparsedProductId = exampleInput.substring(105, 113);

        given(mockIntegerParser.parse(unparsedProductId)).willReturn(expected);

        final ProductInputData actualProductRecord = parser.parse(exampleInput);

        final int actual = actualProductRecord.getRegularSplitQuantity();

        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void parserShouldSetSaleSplitQuantity() {
        final int expected = 5;

        final String unparsedProductId = exampleInput.substring(114, 122);

        given(mockIntegerParser.parse(unparsedProductId)).willReturn(expected);

        final ProductInputData actualProductRecord = parser.parse(exampleInput);

        final int actual = actualProductRecord.getSaleSplitQuantity();

        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void parserShouldSetProductSize() {
        final String expected = "18oz";

        final String paddedProductSize = exampleInput.substring(133, 142);

        given(mockStringParser.parse(paddedProductSize)).willReturn(expected);

        final ProductInputData actualProductRecord = parser.parse(exampleInput);

        final String actual = actualProductRecord.getProductSize();

        assertThat(actual, is(equalTo(expected)));
    }

}