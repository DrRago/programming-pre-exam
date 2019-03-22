/**
 * @author Leonhard Gahr
 */
class Board {
    private final Field[] fields;

    Board() {
        fields = createFields();
    }

    /**
     * parse a label to a field (case insensitive)
     *
     * @param label the label to be parsed
     * @return the field
     */
    Field parseField(String label) {
        for (Field field : fields) {
            if (field.getLabel().equalsIgnoreCase(label)) {
                return field;
            }
        }

        return null;
    }

    /**
     * generate all fields from 1 to 20 (each has double and triple option) including Single Bull and Bullseye,
     * as well as a zero-field with the label x
     *
     * @return the generated fields, always a length of 63
     */
    private static Field[] createFields() {
        Field[] fields = new Field[63];

        // create numeric fields
        for (int i = 1; i <= 20; i++) {
            fields[(i * 3) - 3] = new Field(String.valueOf(i), i, false);
            fields[(i * 3) - 2] = new Field("D" + i, i * 2, true);
            fields[(i * 3) - 1] = new Field("T" + i, i * 3, false);
        }

        // create non-numeric fields
        fields[60] = new Field("25", 25, false);
        fields[61] = new Field("BULL", 50, true);
        fields[62] = new Field("x", 0, false);

        return fields;
    }
}
