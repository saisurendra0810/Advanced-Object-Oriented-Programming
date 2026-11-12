public record PostalAddress(
    String street,
    String city,
    String postalCode,
    String country
) {

    public PostalAddress {

        if (street == null || street.isBlank() ||
            city == null || city.isBlank() ||
            postalCode == null || postalCode.isBlank() ||
            country == null || country.isBlank()) {

            throw new IllegalArgumentException(
                "Incomplete postal address");
        }
    }
}