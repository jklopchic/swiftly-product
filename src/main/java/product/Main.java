package product;

public class Main {

    public static void main(String [] args) {
        final ProductCatalogIntegrationService service = new ProductCatalogIntegrationService();

        service.ingestFile("This definitely won't work!");
    }
}
