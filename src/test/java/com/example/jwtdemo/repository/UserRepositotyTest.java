package com.example.jwtdemo.repository;

import com.example.jwtdemo.entity.RoleEntity;
import com.example.jwtdemo.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class UserRepositotyTest {
    @Autowired
    private UserRepository repo;

    @Test
    public void testCreateUser() {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String password = passwordEncoder.encode("nam2020");

        UserEntity newUser = new UserEntity("nam@codejava.net", password);
        UserEntity savedUser = repo.save(newUser);

        assertThat(savedUser).isNotNull();
        assertThat(savedUser.getId()).isGreaterThan(0);
    }

    @Test
    public void testAssignRoleToUser() {
        UserEntity user = repo.findById(3).get();
        user.addRole(new RoleEntity(3));

        UserEntity updatedUser = repo.save(user);
        assertThat(updatedUser.getRoles()).hasSize(1);
    }
}
