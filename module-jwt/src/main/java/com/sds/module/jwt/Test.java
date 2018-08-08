package com.sds.module.jwt;

import io.jsonwebtoken.Jwts;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.HashMap;
import java.util.Map;

/**
 * @Project : best-practice-project
 * @Package Name : com.sds.module.jwt
 * @Description : TODO
 * @Author : tanchang
 * @Create Date : 2018年08月08日 下午4:21
 * @ModificationHistory Who   When     What
 * ------------    --------------    ---------------------------------
 */
public class Test {

    public static SecretKey generalKey() {
        String encodedKey = "monkeySecret";
        byte [] encodeKeyChar = encodedKey.getBytes();
        SecretKey key = new SecretKeySpec(encodeKeyChar, 0, encodeKeyChar.length, "AES");
        return key;
    }


    public static void main(String[] args) {
//        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJuYW1lIjoxMjMsImlhdCI6MTUzMzcxNzI5MiwiZXhwIjoxNTMzNzE3NjUyfQ.ub_6W276-t2Rf6ZjnsjseOUs6yfKm5DkuOAT4LafKFY";
//        Claims claims = Jwts.parser()  //得到DefaultJwtParser
//                .setSigningKey(generalKey())         //设置签名的秘钥
//                .parseClaimsJws(token).getBody();//设置需要解析的jwt
//        System.out.println(claims);
        Map user = new HashMap<String,Object>();
        user.put("username","aa");
        user.put("openid","bb");
        String x = new JwtTokenUtil().generateToken(user);
        System.out.println(x);

        System.out.println(Jwts.parser().setSigningKey(generalKey()).parseClaimsJws(x).getBody());


    }
}
