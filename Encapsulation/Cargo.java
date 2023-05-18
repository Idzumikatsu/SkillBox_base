public class Cargo {
    private Dimensions dimensions;
    private final double mass;
    private final String deliveryAddress;
    private final boolean canFlip;
    private final String regNum;
    private final boolean isFragile;

    public Cargo(
            Dimensions dimensions,
            double mass,
            String deliveryAddress,
            boolean canFlip,
            String regNum,
            boolean isFragile) {
        this.dimensions = dimensions;
        this.mass = mass;
        this.deliveryAddress = deliveryAddress;
        this.canFlip = canFlip;
        this.regNum = regNum;
        this.isFragile = isFragile;
    }

    public Dimensions getDimensions() {
        return dimensions;
    }

    public double getMass() {
        return mass;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public boolean isCanFlip() {
        return canFlip;
    }

    public String getRegNum() {
        return regNum;
    }

    public boolean isFragile() {
        return isFragile;
    }

    public Cargo setDeliveryAddress(String deliveryAddress) {
        return new Cargo(getDimensions(), getMass(), deliveryAddress, isCanFlip(), getRegNum(), isFragile());
    }

    public Cargo setDimensionsHeight(double height) {
        Dimensions copy = dimensions.setHeight(height);
        return new Cargo(copy, getMass(), getDeliveryAddress(), isCanFlip(), getRegNum(), isFragile());
    }

    public Cargo setDimensionsWidth(double width) {
        Dimensions copy = dimensions.setWidth(width);
        return new Cargo(copy, getMass(), getDeliveryAddress(), isCanFlip(), getRegNum(), isFragile());
    }

    public Cargo setDimensionsLength(double length) {
        Dimensions copy = dimensions.setLength(length);
        return new Cargo(copy, getMass(), getDeliveryAddress(), isCanFlip(), getRegNum(), isFragile());
    }

    public Cargo setDimensions(double height, double width, double length){
        Dimensions copy = new Dimensions(height, width, length);
        return new Cargo(copy, getMass(), getDeliveryAddress(), isCanFlip(), getRegNum(), isFragile());
    }

    public Cargo setMass(double mass){
        return new Cargo(getDimensions(), mass, getDeliveryAddress(), isCanFlip(), getRegNum(), isFragile());
    }

    @Override
    public String toString() {
        return "Cargo{" +
                "dimensions=" + dimensions +
                ", mass=" + mass +
                ", deliveryAddress='" + deliveryAddress + '\'' +
                ", canFlip=" + canFlip +
                ", regNum='" + regNum + '\'' +
                ", isFragile=" + isFragile +
                '}';
    }
}
