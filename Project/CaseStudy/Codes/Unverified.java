public record Unverified(
    VerificationToken token
) implements VerificationState {
}