package com.sds.module.jwt;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.util.Base64;


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


    public static void main(String[] args) throws UnsupportedEncodingException {
//        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJuYW1lIjoxMjMsImlhdCI6MTUzMzcxNzI5MiwiZXhwIjoxNTMzNzE3NjUyfQ.ub_6W276-t2Rf6ZjnsjseOUs6yfKm5DkuOAT4LafKFY";
//        Claims claims = Jwts.parser()  //得到DefaultJwtParser
//                .setSigningKey(generalKey())         //设置签名的秘钥
//                .parseClaimsJws(token).getBody();//设置需要解析的jwt
//        System.out.println(claims);
//        Map user = new HashMap<String,Object>();
//        user.put("username","aa");
//        user.put("openid","bb");
//        String x = new JwtTokenUtil().generateToken(user);
//        System.out.println(x);
//
//        System.out.println(Jwts.parser().setSigningKey(generalKey()).parseClaimsJws(x).getBody());

        final Base64.Encoder encoder = Base64.getEncoder();
        final Base64.Decoder decoder = Base64.getDecoder();

        String xx = "eyJzdWIiOnsiaWQiOiIzIiwiZGVsZXRlZCI6ZmFsc2UsImNyZWF0ZURhdGUiOjE1MTY4ODQ0NzgwMDAsImxhc3RVcGRhdGVkRGF0ZSI6MTUzMzU0OTM3NTAwMCwidmVyc2lvbiI6IjAiLCJ1c2VybmFtZSI6InRhbmNoYW5nMDEiLCJwYXNzd29yZCI6IkUxMEFEQzM5NDlCQTU5QUJCRTU2RTA1N0YyMEY4ODNFIiwiZW1haWwiOiJ0YW5jaGFuZzAxQHN1bmxhbmRzLmNvbSIsImVtcGxveWVlTmFtZSI6IuiwreeVhSIsImVtcGxveWVlSWQiOiIxIiwib3JnRGVwdElkIjoiNTE4MCYyNjMiLCJkZXB0Ijoi5q2m5rGJ5Lqn5ZOB56CU5Y-R6YOoIiwicGhvbmUiOiIxMzk3MTA2ODY5MyIsImdlbmRlciI6bnVsbCwidGVhY2hlciI6dHJ1ZSwidG9rZW4iOm51bGwsInJvbGVMaXN0IjpbXX0sImNyZWF0ZWQiOjE1MzM3MTg0NDYxNzksImV4cCI6MTUzNDMyMzI0Nn0";
//        System.out.println(new String(Base64.getDecoder().decode(xx),"utf-8"));
        System.out.println(decoder.decode(xx));

    }
}
