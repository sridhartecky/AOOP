public class JSONImporter extends DataImporter {
    @Override
    protected void readData() {
        System.out.println("Reading JSON file...");
    }

    @Override
    protected void parseData() {
        System.out.println("Parsing JSON data...");
    }

    @Override
    protected void saveData() {
        System.out.println("Saving JSON data...");
    }
}
