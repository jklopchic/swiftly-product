package product;

import com.google.common.collect.Lists;
import product.model.ProductInputData;
import product.model.ProductRecord;
import product.parse.ProductInputParser;
import product.transform.ProductRecordTransformer;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ProductRecordIngester {
    
    public static final double DEFAULT_TAX_RATE = 7.775;
    public static final int DEFAULT_PRICE_ROUNDING_DECIMALS = 4;

    private final ProductInputParser inputParser;
    private final ProductRecordTransformer recordParser;

    public static ProductRecordIngester construct(final ProductInputParser parser,
                                                  final ProductRecordTransformer recordParser) {
        return new ProductRecordIngester(parser, recordParser);
    }

    private ProductRecordIngester(final ProductInputParser inputParser,
                                  final ProductRecordTransformer recordParser) {
        this.inputParser = inputParser;
        this.recordParser = recordParser;
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
