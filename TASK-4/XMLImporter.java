public class XMLImporter extends DataImporter {
    @Override
    protected void readData() {
        System.out.println("Reading XML file...");
    }

    @Override
    protected void parseData() {
        System.out.println("Parsing XML data...");
    }

    @Override
    protected void saveData() {
        System.out.println("Saving XML data...");
    }
}
