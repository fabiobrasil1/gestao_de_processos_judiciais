package com.ms.analise_tecnica_po.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.ms.analise_tecnica_po.domain.user.models.UserModel;
import com.ms.analise_tecnica_po.domain.user.models.UserRole;
import com.ms.analise_tecnica_po.domain.user.repositories.UserRepository;

@Component
public class DataInitializer implements CommandLineRunner {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  @Autowired
  public DataInitializer(UserRepository userRepository, PasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
  }

  @Override
  public void run(String... args) throws Exception {
    // Verifique se o usuário admin já existe
    if (!userRepository.existsByEmailIgnoreCase("admin@admin.com.br")) {
      // Crie o usuário admin
      UserModel adminUser = new UserModel();
      adminUser.setEmail("admin@admin.com.br");
      adminUser.setName("admin");
      adminUser.setPassword(passwordEncoder.encode("12345"));
      adminUser.setRole(UserRole.ADMIN);

      userRepository.save(adminUser);
    }
  }
}
