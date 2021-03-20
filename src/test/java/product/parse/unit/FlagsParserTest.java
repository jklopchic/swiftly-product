package product.parse.unit;

import org.junit.Test;
import product.parse.ProductParseException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class FlagsParserTest {

    private final FlagsParser parser = FlagsParser.construct();

    @Test
    public void shouldReturnEmptyArray() {
        final boolean [] expected = {};

        final String input = "";

        test(expected, input);
    }

    @Test
    public void shouldReturnTrues() {
        final boolean [] expected = {true, true};

        final String input = "YY";

        test(expected, input);
    }

    @Test
    public void shouldReturnFalses() {
        final boolean [] expected = {false, false};

        final String input = "NN";

        test(expected, input);
    }

    @Test
    public void shouldReturnBothTruesAndFalses() {
        final boolean [] expected = {false, false, true, false};

        final String input = "NNYN";

        test(expected, input);
    }

    @Test
    public void shouldTrim() {
        final boolean [] expected = {false, false};

        final String input = " NN ";

        test(expected, input);
    }

    @Test(expected = ProductParseException.class)
    public void shouldThrowForTooLongInput() {
        final boolean [] expected = {false, false};

        final String input = " NNN";

        test(expected, input);
    }

    @Test(expected = ProductParseException.class)
    public void shouldThrowForTooShortInput() {
        final boolean [] expected = {false, false};

        final String input = "N";

        test(expected, input);
    }

    @Test(expected = ProductParseException.class)
    public void shouldThrowForInvalidInput() {
        final boolean [] expected = {false, false};

        final String input = "FF";

        test(expected, input);
    }

    private void test(boolean[] expected, String input) {
        final boolean [] actual = parser.parse(input, expected.length);

        assertThat(actual, is(equalTo(expected)));
    }

}