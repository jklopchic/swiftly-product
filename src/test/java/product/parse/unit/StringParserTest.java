package product.parse.unit;

import org.junit.Test;
import product.parse.unit.StringParser;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class StringParserTest {

    final StringParser parser = StringParser.construct();

    @Test
    public void parseShouldReturnUntrimmableString() {
        final String expected = "This is an untrimmable string.";
        final String actual = parser.parse(expected);

        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void parseShouldTrim() {
        final String input = "    This is an trimmable string.    ";
        final String actual = parser.parse(input);

        final String expected = "This is an trimmable string.";

        assertThat(actual, is(equalTo(expected)));
    }

}