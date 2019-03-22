import java.util.Arrays;

/**
 * @author Leonhard Gahr
 */
class Visit {
    private Field[] fields;

    Visit(Field[] fields) {
        if (fields.length == 0 || fields.length > 3)
            throw new IllegalArgumentException(String.format("Invalid field number '%d' for visit", fields.length));
        this.fields = fields;
    }

    Field[] getFields() {
        return fields;
    }

    /**
     * return the sum of the values of the hit fields
     *
     * @return the sum
     */
    int getValue() {
        return Arrays.stream(fields).mapToInt(Field::getValue).sum();
    }

    Field getLastField() {
        return fields[fields.length - 1];
    }
}
