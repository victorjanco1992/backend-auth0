package org.example.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccessController {

    @GetMapping("/api/public")
    public String publicEndpoint() {
        return "Este endpoint no requiere autenticación y está disponible para cualquier usuario.";
    }

    @GetMapping("/api/protected")
    @PreAuthorize("hasAnyRole('user', 'admin')")
    public String protectedEndpoint() {
        return "Este endpoint está protegido; puedes verlo porque estás autenticado como usuario o administrador.";
    }

    @GetMapping("/api/admin")
    @PreAuthorize("hasRole('admin')")
    public String adminEndpoint() {
        return "Este endpoint está disponible para ti porque tienes permisos de administrador.";
    }
}
