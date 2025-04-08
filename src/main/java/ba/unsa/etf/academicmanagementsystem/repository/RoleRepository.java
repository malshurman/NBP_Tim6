package ba.unsa.etf.academicmanagementsystem.repository;

import ba.unsa.etf.academicmanagementsystem.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    @Query(value = "SELECT * FROM NBP_ROLE", nativeQuery = true)
    List<Role> findAllRoles();

    @Query(value = "SELECT * FROM NBP_ROLE WHERE NAME = ?1", nativeQuery = true)
    Role findByName(String name);

    @Query(value = "SELECT * FROM NBP_ROLE WHERE ID = ?1", nativeQuery = true)
    Role findRoleById(Long id);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM NBP_ROLE WHERE ID = ?1", nativeQuery = true)
    void deleteRoleById(Long id);
}