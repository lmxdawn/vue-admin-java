package com.lmxdawn.api.admin.util;

import com.lmxdawn.api.BaseApiAdminApplicationTest;
import io.jsonwebtoken.Claims;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class JwtUtilsTest  extends BaseApiAdminApplicationTest {
    @Test
    public void createToken() {
        Map<String, Object> claims = new HashMap<>();
        claims.put("admin_id", 12222222);
        String token = JwtUtils.createToken(claims, 86400L);
        System.out.println(token);
        System.out.println(token.length());
        assertNotNull(token);
    }

    @Test
    public void createToken1() {
        Map<String, Object> claims = new HashMap<>();
        String token = JwtUtils.createToken(claims);
        System.out.println(token);
        assertNotNull(token);
    }

    @Test
    public void parse() {
        Map<String, Object> claims = new HashMap<>();
        claims.put("admin_id", 1);
        String token = JwtUtils.createToken(claims, 1000L);
        System.out.println(token);
        System.out.println(token.length());
        Claims claim = JwtUtils.parse(token);
        System.out.println(claim);
        assertNotNull(claim);
        System.out.println(claim.get("admin_id"));
    }
}
