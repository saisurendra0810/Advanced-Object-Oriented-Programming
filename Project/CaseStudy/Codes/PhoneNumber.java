public final class PhoneNumber {

    private final String value;

    public PhoneNumber(String number) {

        if (number == null ||
            !number.matches("^\\+[1-9]\\d{7,14}$")) {

            throw new IllegalArgumentException(
                "Invalid phone number");
        }

        this.value = number;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }
}