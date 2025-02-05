public class Main {
    public static void main(String[] args) {
        DataImporter csvImporter = new CSVImporter();
        DataImporter xmlImporter = new XMLImporter();
        DataImporter jsonImporter = new JSONImporter();

        System.out.println("== Importing CSV Data ==");
        csvImporter.importData();

        System.out.println("\n== Importing XML Data ==");
        xmlImporter.importData();

        System.out.println("\n== Importing JSON Data ==");
        jsonImporter.importData();
    }
}
