public class Computer {
    private final String vendor;
    private final String name;

    private Processor processor;
    private Memory memory;
    private Storage storage;
    private Monitor monitor;
    private Keyboard keyboard;


    public Computer(Processor processor, Memory memory, Storage storage, Monitor monitor, Keyboard keyboard, String vendor, String name) {
        this.processor = processor;
        this.memory = memory;
        this.storage = storage;
        this.monitor = monitor;
        this.keyboard = keyboard;
        this.vendor = vendor;
        this.name = name;
    }

    public Processor getProcessor() {
        return processor;
    }

    public Memory getMemory() {
        return memory;
    }

    public Storage getStorage() {
        return storage;
    }

    public Monitor getMonitor() {
        return monitor;
    }

    public Keyboard getKeyboard() {
        return keyboard;
    }

    public String getVendor() {
        return vendor;
    }

    public String getName() {
        return name;
    }

    public Computer setProcessor(double frequency, int cores, Manufacturer manufacturer, double weight) {
        var copy = processor.copyingProcessor(frequency, cores, manufacturer, weight);
        return newComputer(ComputerPart.PROCESSOR, copy);
    }

    public Computer setMemory(MemoryType memoryType, int value, double weight) {
        var copy = memory.copyingMemory(memoryType, value, weight);
        return newComputer(ComputerPart.MEMORY, copy);
    }

    public Computer setStorage(StorageType storageType, int value, int weight) {
        var copy = storage.copyingStorage(storageType, value, weight);
        return newComputer(ComputerPart.STORAGE, copy);
    }

    public Computer setMonitor(double diagonal, MonitorType monitorType, double weight) {
        var copy = monitor.copyingMonitor(diagonal, monitorType, weight);
        return newComputer(ComputerPart.MONITOR, copy);
    }

    public Computer setKeyboard(KeyboardType keyboardType, Highlight highlight, double weight) {
        var copy = keyboard.copyingKeyboard(keyboardType, highlight, weight);
        return newComputer(ComputerPart.KEYBOARD, copy);
    }

    public Computer setName(String name) {
        return new Computer(processor, memory, storage, monitor, keyboard, vendor, name);
    }

    public Computer setVendor(String vendor) {
        return new Computer(processor, memory, storage, monitor, keyboard, vendor, name);
    }

    private Computer newComputer(ComputerPart computerPart, Object copy) {
        return switch (computerPart) {
            case PROCESSOR -> new Computer((Processor) copy, memory, storage, monitor, keyboard, vendor, name);
            case MEMORY -> new Computer(processor, (Memory) copy, storage, monitor, keyboard, vendor, name);
            case STORAGE -> new Computer(processor, memory, (Storage) copy, monitor, keyboard, vendor, name);
            case MONITOR -> new Computer(processor, memory, storage, (Monitor) copy, keyboard, vendor, name);
            case KEYBOARD -> new Computer(processor, memory, storage, monitor, (Keyboard) copy, vendor, name);
        };
    }

    public double calcWeight() {
        return getProcessor().getWeight() + getMemory().getWeight() + getStorage().getWeight() +
                getMonitor().getWeight() + getKeyboard().getWeight();
    }

    @Override
    public String toString() {
        return "Компьютер: " + "\n" +
                "Процессор: " + processor + "\n" +
                "Память: " + memory + "\n" +
                "Накопитель: " + storage + "\n" +
                "Монитор: " + monitor + "\n" +
                "Клавиатура: " + keyboard + "\n" +
                "Производитель: " + vendor + "\n" +
                "Название модели: " + name;
    }
}
