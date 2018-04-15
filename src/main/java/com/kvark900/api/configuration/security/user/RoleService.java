package com.kvark900.api.configuration.security.user;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {
    private RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }


    public List<Role> findAll(){return roleRepository.findAll();}

    public Role findByName(RoleName name) {
        return roleRepository.findByName(name);
    }

    public boolean existsRoleByName (RoleName name){
        return roleRepository.existsRoleByName(name);
    }

    public Role findById(Long id) {return roleRepository.findById(id);}

    public void save(Role role) {
    }



}
