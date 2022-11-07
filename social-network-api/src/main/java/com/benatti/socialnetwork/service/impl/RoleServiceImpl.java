package com.benatti.socialnetwork.service.impl;

import com.benatti.socialnetwork.model.Role;
import com.benatti.socialnetwork.repository.RoleRepository;
import com.benatti.socialnetwork.service.RoleService;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository repository;

    public RoleServiceImpl(RoleRepository repository) {
        this.repository = repository;
    }

    @Override
    public Role save(Role role) {
        return repository.save(role);
    }

    @Override
    public Role getRoleByName(String roleName) {
        return repository.findRoleByName(roleName)
                .orElseThrow(() -> new RuntimeException("Can`t find Role by name " + roleName));
    }

    @Override
    public List<Role> findAll() {
        return repository.findAll();
    }
}
