package product.parse.unit;

import org.junit.Test;
import product.model.ProductParseException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class IntegerParserTest {

    private final IntegerParser parser = IntegerParser.construct();

    @Test
    public void parserShouldParseInteger() {
        final int expected = 4;

        final int actual = parser.parse("4");

        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void parserShouldParseIntegerWithLeadingZeroes() {
        final int expected = 4;

        final int actual = parser.parse("000004");

        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void parserShouldParseIntegerWithTrailingZeroes() {
        final int expected = 4000;

        final int actual = parser.parse("0004000");

        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void parserShouldParseNegative() {
        final int expected = -4000;

        final int actual = parser.parse("-004000");

        assertThat(actual, is(equalTo(expected)));
    }

    @Test(expected = ProductParseException.class)
    public void parserShouldThrowForUnparsableToInteger() {
        parser.parse("Product Description");
    }

}