package com.lmxdawn.admin.utils;

import io.jsonwebtoken.Claims;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JwtUtilTest {
    @Test
    public void createToken() {
        Map<String, Object> claims = new HashMap<>();
        claims.put("admin_id", 12222222);
        String token = JwtUtil.createToken(claims, 86400L);
        System.out.println(token);
        System.out.println(token.length());
        assertNotNull(token);
    }

    @Test
    public void createToken1() {
        Map<String, Object> claims = new HashMap<>();
        String token = JwtUtil.createToken(claims);
        System.out.println(token);
        assertNotNull(token);
    }

    @Test
    public void parse() {
        Map<String, Object> claims = new HashMap<>();
        claims.put("admin_id", 1);
        String token = JwtUtil.createToken(claims, 1000L);
        System.out.println(token);
        System.out.println(token.length());
        Claims claim = JwtUtil.parse(token);
        System.out.println(claim);
        assertNotNull(claim);
        System.out.println(claim.get("admin_id"));
    }
}
