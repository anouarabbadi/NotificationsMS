package com.example.notificationsMS.service;

import org.keycloak.KeycloakSecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class KeycloakUserInfo {
    public String getEmail() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() instanceof KeycloakSecurityContext) {
            KeycloakSecurityContext keycloakSecurityContext = (KeycloakSecurityContext) authentication.getPrincipal();
            return keycloakSecurityContext.getToken().getEmail();  // Récupère l'email du token Keycloak
        }

        return null;
    }
}
