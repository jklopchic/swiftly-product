package product.transform;

import org.junit.Before;
import org.junit.Test;
import product.model.ProductInputData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static product.model.ProductInputField.*;

public class ProductPricesTransformerTest {
    
    private int roundingDecimals = 4;
    
    private final DefaultProductPricesTransformer transformer = DefaultProductPricesTransformer.construct(roundingDecimals);

    private ProductInputData inputData = mock(ProductInputData.class);
    
    @Before
    public void before() {
        reset(inputData);
    }
    

    @Test
    public void shouldTransformRegularEachPrice() {
        final String expected = "$1.23";

        given(inputData.getIntegerValue(RegularEachPrice)).willReturn(123);

        final String actual = transformer.transformPrices(inputData).getDisplayPrice();

        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void shouldTransformRegularCalculatorPrice() {
        final double expected = 1.23;

        given(inputData.getIntegerValue(RegularEachPrice)).willReturn(123);

        final double actual = transformer.transformPrices(inputData).getCalculatorPrice();

        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void shouldTransformSaleEachPrice() {
        final String expected = "$1.23";

        given(inputData.getIntegerValue(SaleEachPrice)).willReturn(123);

        final String actual = transformer.transformPrices(inputData).getSaleDisplayPrice();

        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void shouldTransformSaleCalculatorPrice() {
        final double expected = 1.23;

        given(inputData.getIntegerValue(SaleEachPrice)).willReturn(123);

        final double actual = transformer.transformPrices(inputData).getSaleCalculatorPrice();

        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void shouldTransformRegularSplitPrice() {
        final String expected = "2 for $2.46";

        given(inputData.getIntegerValue(RegularSplitPrice)).willReturn(246);
        given(inputData.getIntegerValue(RegularSplitQuantity)).willReturn(2);

        final String actual = transformer.transformPrices(inputData).getDisplayPrice();

        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void shouldTransformRegularSplitPriceWithLargeQuantity() {
        final String expected = "458 for $999999.99";

        given(inputData.getIntegerValue(RegularSplitPrice)).willReturn(99999999);
        given(inputData.getIntegerValue(RegularSplitQuantity)).willReturn(458);

        final String actual = transformer.transformPrices(inputData).getDisplayPrice();

        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void shouldTransformRegularSplitCalculatorPrice() {
        final double expected = 1.23;

        given(inputData.getIntegerValue(RegularSplitPrice)).willReturn(246);
        given(inputData.getIntegerValue(RegularSplitQuantity)).willReturn(2);

        final double actual = transformer.transformPrices(inputData).getCalculatorPrice();

        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void shouldTransformRegularSplitCalculatorPriceWithLargeQuantity() {
        //rounds to 4 decimals
        final double expected = 2183.4061;

        given(inputData.getIntegerValue(RegularSplitPrice)).willReturn(99999999);
        given(inputData.getIntegerValue(RegularSplitQuantity)).willReturn(458);

        final double actual = transformer.transformPrices(inputData).getCalculatorPrice();

        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void shouldTransformSaleSplitPrice() {
        final String expected = "2 for $2.46";

        given(inputData.getIntegerValue(SaleSplitPrice)).willReturn(246);
        given(inputData.getIntegerValue(SaleSplitQuantity)).willReturn(2);

        final String actual = transformer.transformPrices(inputData).getSaleDisplayPrice();

        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void shouldTransformSaleSplitCalculatorPrice() {
        final double expected = 1.23;

        given(inputData.getIntegerValue(SaleSplitPrice)).willReturn(246);
        given(inputData.getIntegerValue(SaleSplitQuantity)).willReturn(2);

        final double actual = transformer.transformPrices(inputData).getSaleCalculatorPrice();

        assertThat(actual, is(equalTo(expected)));
    }

}