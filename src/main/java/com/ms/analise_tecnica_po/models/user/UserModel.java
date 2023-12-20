package com.ms.analise_tecnica_po.models.user;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.ms.analise_tecnica_po.controllers.dtos.users.RegisterUserRecordDto;
import com.ms.analise_tecnica_po.controllers.dtos.users.UpdateUserRecordDto;
import com.ms.analise_tecnica_po.models.process.ProcessModel;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserModel implements UserDetails, Serializable {
    private static final long serialVersionUID = 1L;

    private static final Logger logger = LoggerFactory.getLogger(UserModel.class);

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String name;
    private String password;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    @NotNull
    private UserRole role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ProcessModel> processes = new HashSet<>();

    public UserModel(RegisterUserRecordDto user) {
        this.name = user.name();
        this.email = user.email();
        this.role = user.role();
        this.password = user.password();
    }

    public UserModel(String email, String password, UserRole role, String name) {
        this.email = email;
        this.password = password;
        this.role = role;
        this.name = name;
    }

    public void updateUser(@Valid UpdateUserRecordDto data) {
        if (data.name() != null) {
            this.name = data.name();
        }
        if (data.email() != null) {
            this.email = data.email();
        }
        if (data.role() != null) {
            this.role = data.role();
        }
    }

    public void addProcess(ProcessModel process) {
        this.processes.add(process);
        process.setUser(this);
    }

    public void removeProcess(ProcessModel process) {
        this.processes.remove(process);
        process.setUser(null);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.role == UserRole.ADMIN) {
            logger.info("User '{}' has authorities: ROLE_ADMIN, ROLE_USER", this.getUsername());
            return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
        } else {
            logger.info("User '{}' has authority: USER", this.getUsername());
            return List.of(new SimpleGrantedAuthority("ROLE_USER"));
        }
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
