package com.ms.analise_tecnica_po.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.ms.analise_tecnica_po.domain.user.models.UserModel;
import com.ms.analise_tecnica_po.domain.user.models.UserRole;
import com.ms.analise_tecnica_po.domain.user.repositories.UserRepository;

@Component
@Profile("test")
public class TestDataInitializer implements CommandLineRunner {

  private final Logger logger = LoggerFactory.getLogger(TestDataInitializer.class);

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  @Value("${ADMIN_PASS}")
  private String adminPassword;

  @Autowired
  public TestDataInitializer(UserRepository userRepository, PasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
  }

  @Override
  public void run(String... args) throws Exception {
    logger.info("run TestDataInitializer");
    createAdminUserIfNotExists();
  }

  private void createAdminUserIfNotExists() {
    logger.info("Checking if admin user exists...");
    logger.info("Admin Password: {}", adminPassword);

    if (!userRepository.existsByEmailIgnoreCase("admin@admin.com.br")) {
      UserModel adminUser = new UserModel();
      adminUser.setEmail("admin@admin.com.br");
      adminUser.setName("admin");
      adminUser.setPassword(passwordEncoder.encode(adminPassword));
      adminUser.setRole(UserRole.ADMIN);

      userRepository.save(adminUser);
      logger.info("Admin user created.");
    } else {
      logger.info("Admin user already exists.");
    }
  }
}
