package com.sds.module.jwt;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtTokenUtil implements Serializable {


    public static SecretKey generalKey() {
        String encodedKey = "monkeySecret";
        byte [] encodeKeyChar = encodedKey.getBytes();
        SecretKey key = new SecretKeySpec(encodeKeyChar, 0, encodeKeyChar.length, "AES");
        return key;
    }

    /**
     * 密钥
     */
    public static final SecretKey SECRET= generalKey();

    /**
     * token有效时长/秒
     */
    public static final String EXPIRATION= "1000";

    /**
     * 从数据声明生成令牌
     *
     * @param claims 数据声明
     * @return 令牌
     */
    public String generateTokenX(Map<String, Object> claims) {
        //过期时间
        Date expirationDate = new Date(System.currentTimeMillis() + Long.parseLong(EXPIRATION) * 1000);
        return Jwts.builder().setClaims(claims).setExpiration(expirationDate).signWith(SignatureAlgorithm.HS512, SECRET).compact();
    }

    /**
     * 从令牌中获取数据声明
     *
     * @param token 令牌
     * @return 数据声明
     */
    private Claims getClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }

    /**
     * 生成令牌
     *
     * @param userDetails 用户
     * @return 令牌
     */
    public String generateToken(Map userDetails) {
        Map<String, Object> claims = new HashMap<String,Object>(2);
        claims.put("sub", userDetails.get("username"));
        claims.put("openId",userDetails.get("openid"));
        claims.put("created", new Date());
        return generateTokenX(claims);
    }

    /**
     * 从令牌中获取用户名
     *
     * @param token 令牌
     * @return 用户名
     */
    public String getUsernameFromToken(String token) {
        String username;
        try {
            Claims claims = getClaimsFromToken(token);
            username = claims.getSubject();
        } catch (Exception e) {
            username = null;
        }
        return username;
    }

    /**
     * 从令牌中获取openId
     *
     * @param token 令牌
     * @return 用户名
     */
    public String getOpenIdFromToken(String token) {
        String openId;
        try {
            Claims claims = getClaimsFromToken(token);
            openId = (String) claims.get("openId");
        } catch (Exception e) {
            openId = null;
        }
        return openId;
    }

    /**
     * 判断令牌是否过期
     *
     * @param token 令牌
     * @return 是否过期
     */
    public Boolean isTokenExpired(String token) {
        try {
            Claims claims = getClaimsFromToken(token);
            Date expiration = claims.getExpiration();
            return expiration.before(new Date());
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 刷新令牌
     *
     * @param token 原令牌
     * @return 新令牌
     */
    public String refreshToken(String token) {
        String refreshedToken;
        try {
            Claims claims = getClaimsFromToken(token);
            claims.put("created", new Date());
            refreshedToken = generateToken(claims);
        } catch (Exception e) {
            refreshedToken = null;
        }
        return refreshedToken;
    }

}

