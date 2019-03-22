/**
 * @author Leonhard Gahr
 */
public class Field {
    private String label;
    private int value;
    private boolean doubleField;

    /**
     * Constructs a new field with parameters
     *
     * @param label the fields label
     * @param value the numeric value of the field
     * @param doubleField whether the field is a double field
     */
    Field(String label, int value, boolean doubleField) {
        this.label = label;
        this.value = value;
        this.doubleField = doubleField;
    }

    @Override
    public String toString() {
        return this.label;
    }

    String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    boolean isDoubleField() {
        return doubleField;
    }

    public void setDoubleField(boolean doubleField) {
        this.doubleField = doubleField;
    }
}
