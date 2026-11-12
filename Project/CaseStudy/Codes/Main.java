import java.util.Optional;

public class Main {

    public static void main(String[] args) {

        UserAccountRepository repository =
            new UserAccountRepository();

        // Create email
        EmailAddress email =
            new EmailAddress("Surendra@gmail.com");

        // Create phone number
        PhoneNumber phone =
            new PhoneNumber("+919876543210");

        // Create postal address
        PostalAddress address =
            new PostalAddress(
                "Hyderabad",
                "Hyderabad",
                "500072",
                "India"
            );

        // Create user account
        UserAccount user = new UserAccount(
            email,
            Optional.of(phone),
            Optional.of(address)
        );

        // Register user
        repository.register(user);

        // Display user ID
        System.out.println(
            "User ID: " + user.getId()
        );

        // Verify email
        String token = user.getVerificationToken();

        user.verifyEmail(token);

        // Login
        user.login();

        // Suspend account
        user.suspendAccount();

        System.out.println(
            "Account Status: " + user.getStatus()
        );

        // Try login after suspension
        try {
            user.login();
        }
        catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        }

        // Delete account
        user.deleteAccount();

        System.out.println(
            "Final Status: " + user.getStatus()
        );
    }
}