package product;

import com.google.common.collect.Lists;
import org.junit.Test;
import product.model.ProductRecord;
import product.parse.ProductRecordParser;
import product.publish.ProductRecordPublisher;

import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class ProductCatalogIntegrationServiceTest {

    private final ProductRecordParser mockParser = mock(ProductRecordParser.class);
    private final ProductRecordPublisher mockPublisher = mock(ProductRecordPublisher.class);

    private ProductCatalogIntegrationService service = ProductCatalogIntegrationService.construct(mockParser, mockPublisher);

    @Test
    public void ingestShouldCallBulkParse() {
        service.ingestFile("");

        verify(mockParser).bulkParse(any());
    }

    @Test
    public void ingestShouldReturnValuesFromBulkParse() {
        final List<String> expectedLines = Lists.newArrayList("line one", "line two", "line three");
        final List<ProductRecord> expected = Lists.newArrayList();

        given(mockParser.bulkParse(expectedLines)).willReturn(expected);

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
        final List<ProductRecord> expected = Lists.newArrayList();

        given(mockParser.bulkParse(expectedLines)).willReturn(expected);

        service.ingestFile(expectedLines.stream().collect(Collectors.joining("\n")));

        verify(mockPublisher).publishProducts(expected);
    }



}