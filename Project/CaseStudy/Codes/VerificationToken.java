import java.security.SecureRandom;
import java.time.Instant;
import java.util.Base64;

public final class VerificationToken {

    private final String value;
    private final Instant expiry;

    private static final SecureRandom RANDOM =
        new SecureRandom();

    public VerificationToken() {

        byte[] bytes = new byte[32];
        RANDOM.nextBytes(bytes);

        this.value = Base64.getUrlEncoder()
                .withoutPadding()
                .encodeToString(bytes);

        this.expiry = Instant.now().plusSeconds(3600);
    }

    public boolean matches(String token) {
        return value.equals(token);
    }

    public boolean isExpired() {
        return !Instant.now().isBefore(expiry);
    }

    public String getValue() {
        return value;
    }

    public Instant getExpiry() {
        return expiry;
    }
}