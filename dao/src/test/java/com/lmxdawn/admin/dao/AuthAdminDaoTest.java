package com.lmxdawn.admin.dao;

import com.lmxdawn.admin.entity.AuthAdmin;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AuthAdminDaoTest {
    
    @Autowired
    private AuthAdminDao authAdminDao;
    
    @Test
    public void queryAuthAdmin() {
        List<AuthAdmin> authAdmins = authAdminDao.queryAuthAdmin();
        assertNotNull(authAdmins);
    }
    
    @Test
    @Ignore
    public void queryAuthAdminById() {
    }
    
    @Test
    @Ignore
    public void insertAuthAdmin() {
    }
    
    @Test
    @Ignore
    public void updateAuthAdmin() {
    }
    
    @Test
    @Ignore
    public void deleteAuthAdminById() {
    }
}