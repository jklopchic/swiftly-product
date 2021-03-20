package product.transform;

import org.junit.Test;
import product.model.ProductInputData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class ProductPricesTransformerTest {
    
    private final DefaultProductPricesTransformer transformer = DefaultProductPricesTransformer.construct();

    private ProductInputData inputData = mock(ProductInputData.class);

    @Test
    public void shouldTransformRegularEachPrice() {
        final String expected = "$1.23";

        given(inputData.getEachPrice()).willReturn(123);

        final String actual = transformer.transformPrices(inputData).getDisplayPrice();

        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void shouldTransformRegularCalculatorPrice() {
        final double expected = 1.23;

        given(inputData.getEachPrice()).willReturn(123);

        final double actual = transformer.transformPrices(inputData).getCalculatorPrice();

        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void shouldTransformSaleEachPrice() {
        final String expected = "$1.23";

        given(inputData.getSaleEachPrice()).willReturn(123);

        final String actual = transformer.transformPrices(inputData).getSaleDisplayPrice();

        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void shouldTransformSaleCalculatorPrice() {
        final double expected = 1.23;

        given(inputData.getSaleEachPrice()).willReturn(123);

        final double actual = transformer.transformPrices(inputData).getSaleCalculatorPrice();

        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void shouldTransformRegularSplitPrice() {
        final String expected = "2 for $2.46";

        given(inputData.getRegularSplitPrice()).willReturn(246);
        given(inputData.getRegularSplitQuantity()).willReturn(2);

        final String actual = transformer.transformPrices(inputData).getDisplayPrice();

        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void shouldTransformRegularSplitPriceWithLargeQuantity() {
        final String expected = "458 for $999999.99";

        given(inputData.getRegularSplitPrice()).willReturn(99999999);
        given(inputData.getRegularSplitQuantity()).willReturn(458);

        final String actual = transformer.transformPrices(inputData).getDisplayPrice();

        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void shouldTransformRegularSplitCalculatorPrice() {
        final double expected = 1.23;

        given(inputData.getRegularSplitPrice()).willReturn(246);
        given(inputData.getRegularSplitQuantity()).willReturn(2);

        final double actual = transformer.transformPrices(inputData).getCalculatorPrice();

        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void shouldTransformRegularSplitCalculatorPriceWithLargeQuantity() {
        final double expected = 2183.4061;

        given(inputData.getRegularSplitPrice()).willReturn(99999999);
        given(inputData.getRegularSplitQuantity()).willReturn(458);

        final double actual = transformer.transformPrices(inputData).getCalculatorPrice();

        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void shouldTransformSaleSplitPrice() {
        final String expected = "2 for $2.46";

        given(inputData.getSaleSplitPrice()).willReturn(246);
        given(inputData.getSaleSplitQuantity()).willReturn(2);

        final String actual = transformer.transformPrices(inputData).getSaleDisplayPrice();

        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void shouldTransformSaleSplitCalculatorPrice() {
        final double expected = 1.23;

        given(inputData.getSaleSplitPrice()).willReturn(246);
        given(inputData.getSaleSplitQuantity()).willReturn(2);

        final double actual = transformer.transformPrices(inputData).getSaleCalculatorPrice();

        assertThat(actual, is(equalTo(expected)));
    }

}