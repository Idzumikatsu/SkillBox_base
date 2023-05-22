public class Monitor {
    private final double diagonal;
    private final MonitorType monitorType;
    private final double weight;

    public Monitor(double diagonal, MonitorType monitorType, double weight) {
        this.diagonal = diagonal;
        this.monitorType = monitorType;
        this.weight = weight;
    }

    public double getDiagonal() {
        return diagonal;
    }

    public MonitorType getMonitorType() {
        return monitorType;
    }

    public double getWeight() {
        return weight;
    }

    public Monitor copyingMonitor(double diagonal, MonitorType monitorType, double weight) {
        return new Monitor(diagonal, monitorType, weight);
    }

    @Override
    public String toString() {
        return "Диагональ - " + diagonal + "\"" +
                ", Тип монитора - " + monitorType +
                ", Вес - " + weight + " гр.";
    }
}
