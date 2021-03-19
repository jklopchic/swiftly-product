package product;

import com.google.common.collect.Lists;
import org.junit.Test;
import product.model.ProductInputData;
import product.model.ProductRecord;
import product.parse.ProductInputParser;
import product.parse.ProductRecordParser;
import product.publish.ProductRecordPublisher;

import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class ProductCatalogIntegrationServiceTest {

    private final ProductInputParser mockInputParser = mock(ProductInputParser.class);
    private final ProductRecordParser mockRecordParser = mock(ProductRecordParser.class);
    private final ProductRecordPublisher mockPublisher = mock(ProductRecordPublisher.class);

    private ProductCatalogIntegrationService service = ProductCatalogIntegrationService.construct(mockInputParser, mockRecordParser, mockPublisher);

    @Test
    public void ingestShouldCallBulkParse() {
        service.ingestFile("");

        verify(mockInputParser).bulkParse(any());
    }

    @Test
    public void ingestShouldReturnValuesFromBulkParseCalls() {
        final List<String> expectedLines = Lists.newArrayList("line one", "line two", "line three");
        final List<ProductInputData> expectedInputDatas = Lists.newArrayList();
        final List<ProductRecord> expected = Lists.newArrayList();

        given(mockInputParser.bulkParse(expectedLines)).willReturn(expectedInputDatas);

        given(mockRecordParser.bulkParse(expectedInputDatas)).willReturn(expected);

        final List<ProductRecord> actual = service.ingestFile(expectedLines.stream().collect(Collectors.joining("\n")));

        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void ingestShouldCallPublish() {
        service.ingestFile("");

        verify(mockPublisher).publishProducts(any());
    }

    @Test
    public void ingestShouldCallPublishWithResultFromParse() {
        final List<String> expectedLines = Lists.newArrayList("line one", "line two", "line three");
        final List<ProductInputData> expectedInputDatas = Lists.newArrayList();
        final List<ProductRecord> expected = Lists.newArrayList();

        given(mockInputParser.bulkParse(expectedLines)).willReturn(expectedInputDatas);

        given(mockRecordParser.bulkParse(expectedInputDatas)).willReturn(expected);

        service.ingestFile(expectedLines.stream().collect(Collectors.joining("\n")));

        verify(mockPublisher).publishProducts(expected);
    }



}