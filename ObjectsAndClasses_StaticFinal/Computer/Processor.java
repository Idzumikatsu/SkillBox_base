public class Processor {
    private final double frequency;
    private final int cores;
    private final Manufacturer manufacturer;
    private final double weight;

    public Processor(double frequency, int cores, Manufacturer manufacturer, double weight) {
        this.frequency = frequency;
        this.cores = cores;
        this.manufacturer = manufacturer;
        this.weight = weight;
    }

    public double getFrequency() {
        return frequency;
    }

    public int getCores() {
        return cores;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public double getWeight() {
        return weight;
    }

    public Processor copyingProcessor(double frequency, int cores, Manufacturer manufacturer, double weight) {
        return new Processor(frequency, cores, manufacturer, weight);
    }

    @Override
    public String toString() {
        return  "Частота - " + frequency + " MHz" +
                ", Ядер - " + cores +
                ", Производитель - " + manufacturer +
                ", Вес - " + weight + " гр.";
    }
}