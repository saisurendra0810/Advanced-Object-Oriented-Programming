import java.time.Instant;

public record Verified(
    Instant verifiedAt
) implements VerificationState {

    public Verified {
        if (verifiedAt == null) {
            throw new IllegalArgumentException(
                "Verification date is required");
        }
    }
}