import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class UserAccountRepository {

    private final Map<UUID, UserAccount> users =
        new HashMap<>();

    // Register user with unique email
    public synchronized void register(UserAccount user) {

        for (UserAccount existing : users.values()) {

            if (existing.getEmail().equals(user.getEmail()) &&
                existing.getStatus() != AccountStatus.DELETED) {

                throw new IllegalArgumentException(
                    "Email already registered");
            }
        }

        users.put(user.getId(), user);

        System.out.println("User registered successfully!");
    }

    public UserAccount findById(UUID id) {

        UserAccount user = users.get(id);

        if (user == null) {
            throw new IllegalArgumentException(
                "User not found");
        }

        return user;
    }

    public boolean existsByEmail(EmailAddress email) {

        return users.values().stream().anyMatch(
            user -> user.getEmail().equals(email) &&
                    user.getStatus() != AccountStatus.DELETED
        );
    }
}