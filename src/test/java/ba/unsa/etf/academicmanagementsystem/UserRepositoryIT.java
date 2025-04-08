package ba.unsa.etf.academicmanagementsystem;

import static org.assertj.core.api.Assertions.assertThat;

import ba.unsa.etf.academicmanagementsystem.model.User;
import ba.unsa.etf.academicmanagementsystem.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ActiveProfiles("test")
public class UserRepositoryIT {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testFindAllUsers() {
        List<User> users = userRepository.findAllUsers();
        assertThat(users).isNotNull();
        assertThat(users.size()).isGreaterThanOrEqualTo(0);
    }

    @Test
    public void testSaveAndFindUserByEmail() {
        User user = new User();
        user.setEmail("test@example.com");
        user.setUsername("testuser");
        user.setFirstName("Test");
        user.setLastName("User");
        user.setPassword("password");

        userRepository.save(user);

        User foundUser = userRepository.findByEmail("test@example.com");
        assertThat(foundUser).isNotNull();
        assertThat(foundUser.getUsername()).isEqualTo("testuser");
    }

    @Test
    public void testFindByUsername() {
        // Create and save a user
        User user = new User();
        user.setEmail("username-test@example.com");
        user.setUsername("findByUsernameTest");
        user.setFirstName("Find");
        user.setLastName("ByUsername");
        user.setPassword("password");

        userRepository.save(user);

        // Test findByUsername method
        User foundUser = userRepository.findByUsername("findByUsernameTest");
        assertThat(foundUser).isNotNull();
        assertThat(foundUser.getEmail()).isEqualTo("username-test@example.com");
    }

    @Test
    public void testFindUserById() {
        // Create and save a user
        User user = new User();
        user.setEmail("findbyid@example.com");
        user.setUsername("findByIdTest");
        user.setFirstName("Find");
        user.setLastName("ById");
        user.setPassword("password");

        User savedUser = userRepository.save(user);
        Long userId = savedUser.getId();

        // Test findUserById method
        User foundUser = userRepository.findUserById(userId);
        assertThat(foundUser).isNotNull();
        assertThat(foundUser.getUsername()).isEqualTo("findByIdTest");
    }

    @Test
    public void testDeleteUserById() {
        // Create and save a user
        User user = new User();
        user.setEmail("delete@example.com");
        user.setUsername("deleteTest");
        user.setFirstName("Delete");
        user.setLastName("Test");
        user.setPassword("password");

        User savedUser = userRepository.save(user);
        Long userId = savedUser.getId();

        // Test deleteUserById method
        userRepository.deleteUserById(userId);

        // Verify user was deleted
        User deletedUser = userRepository.findUserById(userId);
        assertThat(deletedUser).isNull();
    }
}