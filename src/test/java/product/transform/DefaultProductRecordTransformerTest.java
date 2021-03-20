package product.transform;

import org.junit.Before;
import org.junit.Test;
import product.model.ProductInputData;
import product.model.UnitOfMeasure;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.reset;
import static product.model.ProductInputField.*;

public class DefaultProductRecordTransformerTest {

    private final double taxRate = 7.755;
    private final ProductPricesTransformer productPricesTransformer = ProductPricesTransformer.construct();

    private ProductRecordTransformer transformer = DefaultProductRecordTransformer.construct(taxRate, productPricesTransformer);

    private ProductInputData inputData = mock(ProductInputData.class);

    @Before
    public void before() {
        reset(inputData);
        final boolean [] flags = {false, false, false, false, false, false, false, false};
        given(inputData.getFlags()).willReturn(flags);
    }


    @Test
    public void transformsProductId() {
        final int expected = 1001;

        given(inputData.getIntegerValue(ProductId)).willReturn(expected);

        final int actual = transformer.transform(inputData).getProductId();

        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void transformsProductDescription() {
        final String expected = "productDescription";

        given(inputData.getStringValue(ProductDescription)).willReturn(expected);

        final String actual = transformer.transform(inputData).getDescription();

        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void transformsProductSize() {
        final String expected = "productSize";

        given(inputData.getStringValue(ProductSize)).willReturn(expected);

        final String actual = transformer.transform(inputData).getProductSize();

        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void appliesTaxRateWhenFlagIsTrue() {
        final double expected = taxRate;

        final boolean [] flags = {false, false, false, false, true, false, false, false};

        given(inputData.getFlags()).willReturn(flags);

        final double actual = transformer.transform(inputData).getTaxRate();

        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void zeroTaxWhenFlagIsFalse() {
        final double expected = 0.0;

        final boolean [] flags = {true, true, true, true, false, true, true, true};

        given(inputData.getFlags()).willReturn(flags);

        final double actual = transformer.transform(inputData).getTaxRate();

        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void unitOfMeasureIsPoundWhenFlagIsTrue() {
        final UnitOfMeasure expected = UnitOfMeasure.Pound;

        final boolean [] flags = {false, false, true, false, false, false, false, false};

        given(inputData.getFlags()).willReturn(flags);

        final UnitOfMeasure actual = transformer.transform(inputData).getUnitOfMeasure();

        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void unitOfMeasureIsEachWhenFlagIsFalse() {
        final UnitOfMeasure expected = UnitOfMeasure.Each;

        final boolean [] flags = {true, true, false, true, true, true, true, true};

        given(inputData.getFlags()).willReturn(flags);

        final UnitOfMeasure actual = transformer.transform(inputData).getUnitOfMeasure();

        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void shouldTransformRegularEachPrice() {
        final String expected = "$1.23";

        given(inputData.getEachPrice()).willReturn(123);

        final String actual = transformer.transform(inputData).getDisplayPrice();

        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void shouldTransformRegularCalculatorPrice() {
        final double expected = 1.23;

        given(inputData.getEachPrice()).willReturn(123);

        final double actual = transformer.transform(inputData).getCalculatorPrice();

        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void shouldTransformSaleEachPrice() {
        final String expected = "$1.23";

        given(inputData.getSaleEachPrice()).willReturn(123);

        final String actual = transformer.transform(inputData).getSaleDisplayPrice();

        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void shouldTransformSaleCalculatorPrice() {
        final double expected = 1.23;

        given(inputData.getSaleEachPrice()).willReturn(123);

        final double actual = transformer.transform(inputData).getSaleCalculatorPrice();

        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void shouldTransformRegularSplitPrice() {
        final String expected = "2 for $2.46";

        given(inputData.getRegularSplitPrice()).willReturn(246);
        given(inputData.getRegularSplitQuantity()).willReturn(2);

        final String actual = transformer.transform(inputData).getDisplayPrice();

        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void shouldTransformRegularSplitPriceWithLargeQuantity() {
        final String expected = "458 for $999999.99";

        given(inputData.getRegularSplitPrice()).willReturn(99999999);
        given(inputData.getRegularSplitQuantity()).willReturn(458);

        final String actual = transformer.transform(inputData).getDisplayPrice();

        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void shouldTransformRegularSplitCalculatorPrice() {
        final double expected = 1.23;

        given(inputData.getRegularSplitPrice()).willReturn(246);
        given(inputData.getRegularSplitQuantity()).willReturn(2);

        final double actual = transformer.transform(inputData).getCalculatorPrice();

        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void shouldTransformRegularSplitCalculatorPriceWithLargeQuantity() {
        final double expected = 2183.4061;

        given(inputData.getRegularSplitPrice()).willReturn(99999999);
        given(inputData.getRegularSplitQuantity()).willReturn(458);

        final double actual = transformer.transform(inputData).getCalculatorPrice();

        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void shouldTransformSaleSplitPrice() {
        final String expected = "2 for $2.46";

        given(inputData.getSaleSplitPrice()).willReturn(246);
        given(inputData.getSaleSplitQuantity()).willReturn(2);

        final String actual = transformer.transform(inputData).getSaleDisplayPrice();

        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void shouldTransformSaleSplitCalculatorPrice() {
        final double expected = 1.23;

        given(inputData.getSaleSplitPrice()).willReturn(246);
        given(inputData.getSaleSplitQuantity()).willReturn(2);

        final double actual = transformer.transform(inputData).getSaleCalculatorPrice();

        assertThat(actual, is(equalTo(expected)));
    }



}