public final class EmailAddress {

    private final String value;

    public EmailAddress(String email) {

        if (email == null ||
            !email.matches("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$")) {

            throw new IllegalArgumentException(
                "Invalid email address");
        }

        this.value = email.trim().toLowerCase();
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof EmailAddress other)) {
            return false;
        }
        return value.equals(other.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public String toString() {
        return value;
    }
}