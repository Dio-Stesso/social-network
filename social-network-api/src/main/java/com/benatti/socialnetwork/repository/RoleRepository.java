package com.benatti.socialnetwork.repository;

import com.benatti.socialnetwork.model.Role;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RoleRepository extends JpaRepository<Role, Long> {
    @Query("FROM Role r WHERE r.roleName=?1")
    Optional<Role> findRoleByName(String roleName);
}
