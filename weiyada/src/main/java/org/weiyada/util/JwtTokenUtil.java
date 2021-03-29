package org.weiyada.util;

import com.mysql.cj.util.Base64Decoder;
import com.sun.org.apache.xml.internal.security.utils.Base64;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Base64Util;
import org.springframework.util.Base64Utils;
import org.weiyada.entity.Audience;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;

@Slf4j
public class JwtTokenUtil {
    public static final String AUTH_HEADER_KEY = "Authorization";

    public static final String TOKEN_PREFIX = "Bearer ";
    /**
     * 解析jwt
     *
     * @param jsonWebToken
     * @param base64Security
     * @return
     */
    public static Claims parseJWT(String jsonWebToken, String base64Security) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(DatatypeConverter.parseBase64Binary(base64Security))
                    .parseClaimsJws(jsonWebToken).getBody();
            return claims;
        } catch (ExpiredJwtException eje) {
            log.error("===== Token过期 =====", eje);
            throw new RuntimeException(eje.getCause());
//            throw new CustomException(ResultCode.PERMISSION_TOKEN_EXPIRED);
        } catch (Exception e) {
            log.error("===== token解析异常 =====", e);
//            throw new CustomException(ResultCode.PERMISSION_TOKEN_INVALID);
            throw new RuntimeException(e.getCause());
        }
    }

    /**
     * 构建jwt
     *
     * @param userId
     * @param username
     * @param role
     * @param audience
     * @return
     */
    public static String createJWT(String userId, String username, String role, Audience audience) {
        try {
            // 使用HS256加密算法
            SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

            long nowMillis = System.currentTimeMillis();
            Date now = new Date(nowMillis);

            //生成签名密钥
            byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(audience.getBase64Secret());
            Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

            //添加构成JWT的参数
            JwtBuilder builder = Jwts.builder().setHeaderParam("typ", "JWT")
                    // 可以将基本不重要的对象信息放到claims
                    .claim("role", role)
                    .claim("userId", userId)
                    .setSubject(username)           // 代表这个JWT的主体，即它的所有人
                    .setIssuer(audience.getIss())              // 代表这个JWT的签发主体；
                    .setIssuedAt(new Date())        // 是一个时间戳，代表这个JWT的签发时间；
                    .setAudience(audience.getAud())          // 代表这个JWT的接收对象；
                    .signWith(signatureAlgorithm, signingKey);
            //添加Token过期时间
            int TTLMillis = audience.getExpiresSecond();
            if (TTLMillis >= 0) {
                long expMillis = nowMillis + TTLMillis;
                Date exp = new Date(expMillis);
                builder.setExpiration(exp)  // 是一个时间戳，代表这个JWT的过期时间；
                        .setNotBefore(now); // 是一个时间戳，代表这个JWT生效的开始时间，意味着在这个时间之前验证JWT是会失败的
            }

            //生成JWT
            return builder.compact();
        } catch (Exception e) {
            log.error("签名失败", e);
//            throw new CustomException(ResultCode.PERMISSION_SIGNATURE_ERROR);
            throw new RuntimeException(e.getMessage());
        }
    }


    /**
     * 从token中获取用户名
     *
     * @param token
     * @param base64Security
     * @return
     */
    public static String getUsername(String token, String base64Security) {
        return parseJWT(token, base64Security).getSubject();
    }

    /**
     * 从token中获取用户ID
     *
     * @param token
     * @param base64Security
     * @return
     */
    public static String getUserId(String token, String base64Security) {
        String userId = parseJWT(token, base64Security).get("userId", String.class);
        return userId;

    }
    /**
     * 是否已过期
     *
     * @param token
     * @param base64Security
     * @return
     */
    public static boolean isExpiration(String token, String base64Security) {
        return parseJWT(token, base64Security).getExpiration().before(new Date());
    }
}
