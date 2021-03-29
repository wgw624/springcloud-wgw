package org.weiyada.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.digester.Digester;
import org.springframework.security.crypto.codec.Hex;
import org.springframework.util.ObjectUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

/**
 * @description
 * @author：weiguangwei
 * @email: weiguangwei@codemao.cn
 * @time：2021/3/29 6:10 下午
 */
@Slf4j
public class EncryptUtil {

    public static String getSaltMd5(String password,String salt){
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digest;
            if(!ObjectUtils.isEmpty(salt)){
                digest = md.digest((password+salt).getBytes());
            }else{
                digest = md.digest(password.getBytes());
            }
            return new String(Hex.encode(digest));
        } catch (NoSuchAlgorithmException e) {
            log.error("加密异常");
            e.printStackTrace();
        }
        return null;
    }

    public static String getSalt(){
        Random random = new Random();
        StringBuilder sBuilder= new StringBuilder();
        sBuilder.append(random.nextInt(99999999)).append(99999999);
        if(sBuilder.length()<16){
            sBuilder.append(0);
        }
        return sBuilder.toString();
    }
}
