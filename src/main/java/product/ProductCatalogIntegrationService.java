package product;

import com.google.common.collect.Lists;
import product.model.ProductInputData;
import product.model.ProductRecord;
import product.parse.ProductInputParser;
import product.transform.ProductRecordTransformer;
import product.publish.ProductRecordPublisher;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ProductCatalogIntegrationService {

    private final ProductInputParser inputParser;
    private final ProductRecordTransformer recordParser;
    private final ProductRecordPublisher publisher;

    public static ProductCatalogIntegrationService construct(final ProductInputParser parser,
                                                             final ProductRecordTransformer recordParser,
                                                             final ProductRecordPublisher publisher) {
        return new ProductCatalogIntegrationService(parser, recordParser, publisher);
    }

    private ProductCatalogIntegrationService(final ProductInputParser inputParser,
                                             final ProductRecordTransformer recordParser,
                                             final ProductRecordPublisher publisher) {
        this.inputParser = inputParser;
        this.recordParser = recordParser;
        this.publisher = publisher;
    }

    public List<ProductRecord> ingestFile(final String asciiFile) {
        final List<String> lines = getLines(asciiFile);

        return ingestInternal(lines);
    }

    public List<ProductRecord> ingestFile(final File file) {
        final List<String> lines = getLines(file);

        return ingestInternal(lines);
    }

    private List<ProductRecord> ingestInternal(final List<String> lines) {
        final List<ProductInputData> inputData = inputParser.bulkParse(lines);

        final List<ProductRecord> records = recordParser.bulkTransform(inputData);

        publisher.publishProducts(records);

        return records;
    }

    private List<String> getLines(final String asciiFile) {
        return Arrays.stream(asciiFile.split("\n")).collect(Collectors.toList());
    }

    private List<String> getLines(final File file) {
        final List<String> lines = Lists.newArrayList();

        Scanner fileInput;

        try {
            fileInput = new Scanner(file);
        } catch (final FileNotFoundException e) {
            throw new RuntimeException("Something is very wrong with this file.");
        }

        while(fileInput.hasNextLine()) {
            lines.add(fileInput.nextLine());
        }

        return lines;
    }
}
