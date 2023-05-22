public class Keyboard {
    private final KeyboardType keyboardType;
    private final Highlight highlight;
    private final double weight;

    public Keyboard(KeyboardType keyboardType, Highlight highlight, double weight) {
        this.keyboardType = keyboardType;
        this.highlight = highlight;
        this.weight = weight;
    }

    public KeyboardType getKeyboardType() {
        return keyboardType;
    }

    public Highlight getHighlight() {
        return highlight;
    }

    public double getWeight() {
        return weight;
    }

    public Keyboard copyingKeyboard(KeyboardType keyboardType, Highlight highlight, double weight) {
        return new Keyboard(keyboardType, highlight, weight);
    }

    @Override
    public String toString() {
        return "Тип клавиатуры - " + keyboardType +
                ", Подсветка - " + highlight +
                ", Вес - " + weight + " гр.";
    }
}
