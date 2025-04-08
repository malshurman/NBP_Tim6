package ba.unsa.etf.academicmanagementsystem.repository;

import ba.unsa.etf.academicmanagementsystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "SELECT * FROM NBP_USER", nativeQuery = true)
    List<User> findAllUsers();

    @Query(value = "SELECT * FROM NBP_USER WHERE EMAIL = ?1", nativeQuery = true)
    User findByEmail(String email);

    @Query(value = "SELECT * FROM NBP_USER WHERE USERNAME = ?1", nativeQuery = true)
    User findByUsername(String username);

    @Query(value = "SELECT * FROM NBP_USER WHERE ID = ?1", nativeQuery = true)
    User findUserById(Long id);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM NBP_USER WHERE ID = ?1", nativeQuery = true)
    void deleteUserById(Long id);
}