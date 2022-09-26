package it.miucio.project_for_stentle.security.service;

import org.springframework.security.core.Authentication;

public interface AuthService {

    Authentication authenticateUser(String username, String password);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
}
