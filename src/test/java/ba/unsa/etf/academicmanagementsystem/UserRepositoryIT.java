package ba.unsa.etf.academicmanagementsystem;

import ba.unsa.etf.academicmanagementsystem.model.User;
import ba.unsa.etf.academicmanagementsystem.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ActiveProfiles("test")
@Transactional
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
        User user = User.builder()
                .birthDate(LocalDateTime.of(1990, 1, 1, 0, 0))
                .email("dummyuser@example.com")
                .firstName("John")
                .lastName("Doe")
                .passwordHashed("securepassword")
                .phoneNumber("1234567890")
                .username("johndoe")
                .roleId(1L)
                .build();
        userRepository.save(user);
        User foundUser = userRepository.findByEmail("dummyuser@example.com");
        assertThat(foundUser).isNotNull();
        assertThat(foundUser.getUsername()).isEqualTo("johndoe");
    }

    @Test
    public void testFindByUsername() {
        User user = User.builder()
                .birthDate(LocalDateTime.of(1990, 1, 1, 0, 0))
                .email("dummyuser@example.com")
                .firstName("John")
                .lastName("Doe")
                .passwordHashed("securepassword")
                .phoneNumber("1234567890")
                .username("johndoe")
                .roleId(1L)
                .build();
        userRepository.save(user);
        User foundUser = userRepository.findByUsername("johndoe");
        assertThat(foundUser).isNotNull();
        assertThat(foundUser.getEmail()).isEqualTo("dummyuser@example.com");
    }

    @Test
    public void testFindUserById() {
        User user = User.builder()
                .birthDate(LocalDateTime.of(1990, 1, 1, 0, 0))
                .email("dummyuser@example.com")
                .firstName("John")
                .lastName("Doe")
                .passwordHashed("securepassword")
                .phoneNumber("1234567890")
                .username("johndoe")
                .roleId(1L)
                .build();
        User savedUser = userRepository.save(user);
        Long userId = savedUser.getId();
        User foundUser = userRepository.findUserById(userId);
        assertThat(foundUser).isNotNull();
        assertThat(foundUser.getUsername()).isEqualTo("johndoe");
    }

    @Test
    public void testDeleteUserById() {
        User user = User.builder()
                .birthDate(LocalDateTime.of(1990, 1, 1, 0, 0))
                .email("dummyuser@example.com")
                .firstName("John")
                .lastName("Doe")
                .passwordHashed("securepassword")
                .phoneNumber("1234567890")
                .username("johndoe")
                .roleId(1L)
                .build();
        User savedUser = userRepository.save(user);
        Long userId = savedUser.getId();
        userRepository.deleteUserById(userId);

        // Verify user was deleted - handle exception for this test
        try {
            User deletedUser = userRepository.findUserById(userId);
            assertThat(deletedUser).isNull();
        } catch (org.springframework.dao.EmptyResultDataAccessException e) {
            // This is expected after deletion
            // Test passes if exception is thrown
        }
    }
}