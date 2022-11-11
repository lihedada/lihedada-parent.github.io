package com.common.utils;/*
@author shkstart
@Date2022-11-08-12:00
*/

import io.jsonwebtoken.*;
import org.springframework.util.StringUtils;
import java.util.Date;
/**
 * 生成JSON Web令牌的工具类
 */
public class JwtHelper {

    //过期时间
        private static long tokenExpiration = 365 * 24 * 60 * 60 * 1000;
        //加密秘钥
        private static String tokenSignKey = "123456";
        //根据用户 id和用户名称生成token字符串
        public static String createToken(String userId,String username) {
            String token = Jwts.builder()
                    // 设置分组
                    .setSubject("AUTH-USER")
                    //过期时间
                    .setExpiration(new Date(System.currentTimeMillis() + tokenExpiration))
                    .claim("userId",userId)
                    .claim("username",username)
                    //编码操作
                    .signWith(SignatureAlgorithm.HS512,tokenSignKey)
                    .compressWith(CompressionCodecs.GZIP)
                    .compact();
            return token;
        }

        //从 token 字符串获取userid
        public static String getUserId(String token) {
            try {
                if(StringUtils.isEmpty(token)) return null;
                Jws<Claims> claimsJws = Jwts.parser().setSigningKey(tokenSignKey).parseClaimsJws(token);
                Claims claims = claimsJws.getBody();
                return (String)claims.get("userId");
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        //从 token 字符串获取username
        public static String getUsername(String token) {
            try {
                if (StringUtils.isEmpty(token)) return "";

                Jws<Claims> claimsJws = Jwts.parser().setSigningKey(tokenSignKey).parseClaimsJws(token);
                Claims claims = claimsJws.getBody();
                return (String)claims.get("username");
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        public static void main(String[] args) {
            String token = JwtHelper.createToken("1","admin");
            System.out.println(token);

            String userId = JwtHelper.getUserId(token);
            System.out.println(userId);

            String username = JwtHelper.getUsername(token);
            System.out.println(username);
        }
}
