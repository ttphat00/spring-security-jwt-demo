package com.example.jwtdemo.repository;

import com.example.jwtdemo.entity.RoleEntity;
import com.example.jwtdemo.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class RoleRepositoryTest {
    @Autowired
    private RoleRepository repo;

    @Test
    public void testCreateRoles() {
        RoleEntity admin = new RoleEntity("ROLE_ADMIN");
        RoleEntity editor = new RoleEntity("ROLE_EDITOR");
        RoleEntity customer = new RoleEntity("ROLE_CUSTOMER");

        repo.save(admin);
        repo.save(editor);
        repo.save(customer);

        long count = repo.count();
        assertEquals(3, count);
    }
}
