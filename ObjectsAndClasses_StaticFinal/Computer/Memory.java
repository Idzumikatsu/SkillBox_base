public class Memory {
    private final MemoryType memoryType;
    private final int value;
    private final double weight;

    public Memory(MemoryType memoryType, int value, double weight) {
        this.memoryType = memoryType;
        this.value = value;
        this.weight = weight;
    }

    public MemoryType getMemoryType() {
        return memoryType;
    }

    public int getValue() {
        return value;
    }

    public double getWeight() {
        return weight;
    }

    public Memory copyingMemory(MemoryType memoryType, int value, double weight) {
        return new Memory(memoryType, value, weight);
    }

    @Override
    public String toString() {
        return "Тип памяти - " + memoryType +
                ", Объем - " + value + " GB " +
                ", Вес - " + weight + " гр.";
    }
}
