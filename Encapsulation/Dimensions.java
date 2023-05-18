public class Dimensions {
    private final double height;
    private final double width;
    private final double length;

    public Dimensions(double height, double width, double length) {
        this.height = height;
        this.width = width;
        this.length = length;
    }

    public double getHeight() {
        return height;
    }

    public double getWidth() {
        return width;
    }

    public double getLength() {
        return length;
    }

    public Dimensions setHeight(double height) {
        return new Dimensions(height, getWidth(), getLength());
    }

    public Dimensions setWidth(double width) {
        return new Dimensions(getHeight(), width, getLength());
    }

    public Dimensions setLength(double length){
        return new Dimensions(getHeight(), getWidth(), length);
    }

    @Override
    public String toString() {
        return "Dimensions{" +
                "height=" + height +
                ", width=" + width +
                ", length=" + length +
                '}';
    }



    public double calcVolume() {
        return getHeight() * getWidth() * getLength();
    }



}
