public class Storage {
    private final StorageType storageType;
    private final int value;
    private final int weight;

    public Storage(StorageType storageType, int value, int weight) {
        this.storageType = storageType;
        this.value = value;
        this.weight = weight;
    }

    public StorageType getStorageType() {
        return storageType;
    }

    public int getValue() {
        return value;
    }

    public int getWeight() {
        return weight;
    }

    public Storage copyingStorage (StorageType storageType, int value, int weight){
        return new Storage(storageType, value, weight);
    }

    @Override
    public String toString() {
        return "Тип накопителя - " + storageType +
                ", Объем - " + value + " TB" +
                ", Вес - " + weight + " гр.";
    }
}
