import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

public class UserAccount {

    private final UUID id;

    private EmailAddress email;

    private VerificationState verification;

    private Optional<PhoneNumber> phone;

    private Optional<PostalAddress> address;

    private AccountStatus status;

    public UserAccount(
        EmailAddress email,
        Optional<PhoneNumber> phone,
        Optional<PostalAddress> address
    ) {

        this.id = UUID.randomUUID();

        this.email = email;

        this.phone = phone;
        this.address = address;

        this.verification =
            new Unverified(new VerificationToken());

        this.status = AccountStatus.PENDING;
    }

    // Verify email address
    public void verifyEmail(String token) {

        if (!(verification instanceof Unverified u)) {
            throw new IllegalStateException(
                "Email is already verified");
        }

        if (!u.token().matches(token) ||
            u.token().isExpired()) {

            throw new IllegalArgumentException(
                "Invalid or expired verification token");
        }

        verification = new Verified(Instant.now());

        status = AccountStatus.ACTIVE;

        System.out.println("Email verified successfully!");
    }

    // Login validation
    public void login() {

        if (status != AccountStatus.ACTIVE) {
            throw new IllegalStateException(
                "Account is not active");
        }

        if (!(verification instanceof Verified)) {
            throw new IllegalStateException(
                "Email is not verified");
        }

        System.out.println("Login successful!");
    }

    // Suspend account
    public void suspendAccount() {

        if (status == AccountStatus.DELETED) {
            throw new IllegalStateException(
                "Deleted account cannot be suspended");
        }

        status = AccountStatus.SUSPENDED;
    }

    // Delete account
    public void deleteAccount() {
        status = AccountStatus.DELETED;
    }

    // Update phone number
    public void updatePhone(PhoneNumber number) {
        phone = Optional.of(number);
    }

    // Update postal address
    public void updateAddress(PostalAddress newAddress) {
        address = Optional.of(newAddress);
    }

    // Get verification token for registration email
    public String getVerificationToken() {

        if (verification instanceof Unverified u) {
            return u.token().getValue();
        }

        throw new IllegalStateException(
            "Email is already verified");
    }

    public UUID getId() {
        return id;
    }

    public EmailAddress getEmail() {
        return email;
    }

    public AccountStatus getStatus() {
        return status;
    }

    public Optional<PhoneNumber> getPhone() {
        return phone;
    }

    public Optional<PostalAddress> getAddress() {
        return address;
    }
}