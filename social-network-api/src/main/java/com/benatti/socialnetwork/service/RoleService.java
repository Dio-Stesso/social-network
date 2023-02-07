package com.benatti.socialnetwork.service;

import com.benatti.socialnetwork.model.Role;
import java.util.List;

public interface RoleService {
    Role save(Role role);

    Role getRoleByName(Role.RoleName roleName);

    List<Role> findAll();
}
