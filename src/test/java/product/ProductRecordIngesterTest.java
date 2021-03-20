package product;

import com.google.common.collect.Lists;
import org.junit.Test;
import product.model.ProductInputData;
import product.model.ProductRecord;
import product.parse.ProductInputParser;
import product.transform.ProductRecordTransformer;

import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class ProductRecordIngesterTest {

    private final ProductInputParser mockInputParser = mock(ProductInputParser.class);
    private final ProductRecordTransformer mockRecordParser = mock(ProductRecordTransformer.class);

    private ProductRecordIngester service = ProductRecordIngester.construct(mockInputParser, mockRecordParser);

    @Test
    public void ingestShouldCallBulkParse() {
        service.ingestFile("");

        verify(mockInputParser).bulkParse(any());
    }

    @Test
    public void ingestShouldCallBulkTransform() {
        service.ingestFile("");

        verify(mockRecordParser).bulkTransform(any());
    }

    @Test
    public void ingestShouldReturnValuesFromBulkParseCalls() {
        final List<String> expectedLines = Lists.newArrayList("line one", "line two", "line three");
        final List<ProductInputData> expectedInputDatas = Lists.newArrayList();
        final List<ProductRecord> expected = Lists.newArrayList();

        given(mockInputParser.bulkParse(expectedLines)).willReturn(expectedInputDatas);

        given(mockRecordParser.bulkTransform(expectedInputDatas)).willReturn(expected);

        final List<ProductRecord> actual = service.ingestFile(expectedLines.stream().collect(Collectors.joining("\n")));

        assertThat(actual, is(equalTo(expected)));
    }
}