package product.parse;

import org.junit.Test;
import product.model.ProductInputData;
import product.parse.unit.FlagsParser;
import product.parse.unit.IntegerParser;
import product.parse.unit.StringParser;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static product.model.ProductInputField.*;

public class DefaultProductInputParserTest {

    private final StringParser mockStringParser = mock(StringParser.class);
    private final IntegerParser mockIntegerParser = mock(IntegerParser.class);
    private final FlagsParser mockFlagsParser = mock(FlagsParser.class);

    private final ProductInputParser parser = DefaultProductInputParser.construct(mockStringParser, mockIntegerParser, mockFlagsParser);

    private final String exampleInput = "80000001 Kimchi-flavored white rice                                  00000567 00000001 00000002 00000003 00000004 00000005 NNNNNNNNN      18oz";

    @Test
    public void parserShouldSetProductDescription() {
        final String expected = "Kimchi-flavored white rice";

        final String paddedProductDescription = exampleInput.substring(9,68);

        given(mockStringParser.parse(paddedProductDescription)).willReturn(expected);

        final ProductInputData actualProductRecord = parser.parse(exampleInput);

        final String actual = actualProductRecord.getStringValue(ProductDescription);

        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void parserShouldSetProductId() {
        final int expected = 80000001;

        final String unparsed = exampleInput.substring(0,8);

        given(mockIntegerParser.parse(unparsed)).willReturn(expected);

        final ProductInputData actualProductRecord = parser.parse(exampleInput);

        final int actual = actualProductRecord.getIntegerValue(ProductId);

        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void parserShouldSetRegularEachPrice() {
        final int expected = 567;

        final String unparsed = exampleInput.substring(69, 77);

        given(mockIntegerParser.parse(unparsed)).willReturn(expected);

        final ProductInputData actualProductRecord = parser.parse(exampleInput);

        final int actual = actualProductRecord.getIntegerValue(RegularEachPrice);

        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void parserShouldSetSaleEachPrice() {
        final int expected = 1;

        final String unparsed = exampleInput.substring(78, 86);

        given(mockIntegerParser.parse(unparsed)).willReturn(expected);

        final ProductInputData actualProductRecord = parser.parse(exampleInput);

        final int actual = actualProductRecord.getIntegerValue(SaleEachPrice);

        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void parserShouldSetRegularSplitPrice() {
        final int expected = 2;

        final String unparsed = exampleInput.substring(87, 95);

        given(mockIntegerParser.parse(unparsed)).willReturn(expected);

        final ProductInputData actualProductRecord = parser.parse(exampleInput);

        final int actual = actualProductRecord.getIntegerValue(RegularSplitPrice);

        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void parserShouldSetSaleSplitPrice() {
        final int expected = 3;

        final String unparsed = exampleInput.substring(96, 104);

        given(mockIntegerParser.parse(unparsed)).willReturn(expected);

        final ProductInputData actualProductRecord = parser.parse(exampleInput);

        final int actual = actualProductRecord.getIntegerValue(SaleSplitPrice);

        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void parserShouldSetRegularSplitQuantity() {
        final int expected = 4;

        final String unparsed = exampleInput.substring(105, 113);

        given(mockIntegerParser.parse(unparsed)).willReturn(expected);

        final ProductInputData actualProductRecord = parser.parse(exampleInput);

        final int actual = actualProductRecord.getIntegerValue(RegularSplitQuantity);

        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void parserShouldSetSaleSplitQuantity() {
        final int expected = 5;

        final String unparsed = exampleInput.substring(114, 122);

        given(mockIntegerParser.parse(unparsed)).willReturn(expected);

        final ProductInputData actualProductRecord = parser.parse(exampleInput);

        final int actual = actualProductRecord.getIntegerValue(SaleSplitQuantity);

        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void parserShouldSetFlags() {
        final boolean[]  expected = {false, false, false, false, false, false, false, false, false};

        final String unparsed = exampleInput.substring(123, 132);

        given(mockFlagsParser.parse(unparsed, expected.length)).willReturn(expected);

        final ProductInputData actualProductRecord = parser.parse(exampleInput);

        final boolean[] actual = actualProductRecord.getFlagsValue(Flags);

        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void parserShouldSetProductSize() {
        final String expected = "18oz";

        final String paddedProductSize = exampleInput.substring(133, 142);

        given(mockStringParser.parse(paddedProductSize)).willReturn(expected);

        final ProductInputData actualProductRecord = parser.parse(exampleInput);

        final String actual = actualProductRecord.getStringValue(ProductSize);

        assertThat(actual, is(equalTo(expected)));
    }

}