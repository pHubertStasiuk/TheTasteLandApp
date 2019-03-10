package com.tasteland.app.Tasteland.repository;

import com.tasteland.app.Tasteland.model.Role;

public interface RoleDAO {

    Role findRoleByName(String roleName);
}
