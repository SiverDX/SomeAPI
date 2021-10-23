package de.cadentem.security;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public enum SecurityRole {
    ADMIN(new HashSet<>(List.of(SecurityPermission.CARDS_READ, SecurityPermission.ACTUATOR_READ))),
    CONSUMER(new HashSet<>(List.of(SecurityPermission.CARDS_READ)));

    @Getter
    private final Set<SecurityPermission> permissions;

    SecurityRole(final Set<SecurityPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<GrantedAuthority> getGrantedAuthorities() {
        return getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.name()))
                .collect(Collectors.toSet());
    }
}
