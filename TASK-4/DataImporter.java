public abstract class DataImporter {
    public final void importData() {
        readData();
        parseData();
        saveData();
    }

    protected abstract void readData();
    protected abstract void parseData();
    protected abstract void saveData();
}
