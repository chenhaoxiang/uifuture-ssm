package com.uifuture.ssm.util;


import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * AES加密解密类 加密解密IP地址
 *
 * @author chenhaoxiang
 */
public class AES {
    public static final String SECRET_KEY = "CB824891C3CFB2A08136E3A60EA4AA18";

    public static void main(String args[]) throws UnsupportedEncodingException, InvalidAlgorithmParameterException {
        System.out.println(System.getProperty("file.encoding"));
        //加密内容
        String content = "1";
        //String content = "test123456";
        //为与Delphi编码统一，将字符转为UTF8编码（其他语言也相同）
        //String ss=new String(content.getBytes(),"UTF-8");
        //密钥
        String password = "CB824891C3CFB2A08136E3A60EA4AA18";//密钥是chenhaoxiang加密后的，密钥也是chenhaoxiang
        System.out.println("加密前：" + content);
        byte[] encryptResult = encrypt(content, password, 16); //16位密钥长度128位、24位密钥长度192、32位密钥长度256（在delphi中对应kb128、kb192、kb256）
        System.out.println("encryptResult:" + encryptResult);
        String str = parseByte2HexStr(encryptResult);
        System.out.println("加密后：" + str);//将加密后编码转为16进制编码


        byte[] str2 = parseHexStr2Byte(str);
        byte[] decryptResult = decrypt(str2, password, 16);
        System.out.println("解密后：" + new String(decryptResult));

        System.out.println("解密:" + decrypt(str));

        System.out.println(encrypt("root"));
        System.out.println(encrypt("12345678"));
        System.out.println(decrypt("4B1F1099FE3DCDEE0C4B5ED079322488"));
        System.out.println(decrypt("F48A25E5C6CFCE140FEB01FBB941AE93"));
    }

    /**
     * 解密
     */
    public static String decrypt(String content) {
        try {
            return new String(decrypt(parseHexStr2Byte(content), SECRET_KEY, 16));
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 加密
     */
    public static String encrypt(String content) throws UnsupportedEncodingException, InvalidAlgorithmParameterException {
        return parseByte2HexStr(encrypt(content, SECRET_KEY, 16));
    }

    /**
     * 加密
     *
     * @param content  需要加密的内容
     * @param password 加密密码
     * @param keySize  密钥长度16,24,32
     * @return
     * @throws UnsupportedEncodingException
     * @throws InvalidAlgorithmParameterException
     */

    public static byte[] encrypt(String content, String password, int keySize) throws UnsupportedEncodingException, InvalidAlgorithmParameterException {
        try {
            //密钥长度不够用0补齐。
            SecretKeySpec key = new SecretKeySpec(ZeroPadding(password.getBytes("UTF-8"), keySize), "AES");
            //定义加密算法AES、算法模式ECB、补码方式PKCS5Padding
            //Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            //定义加密算法AES 算法模式CBC、补码方式PKCS5Padding
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            //CBC模式模式下初始向量 不足16位用0补齐
            IvParameterSpec iv = new IvParameterSpec(ZeroPadding(password.getBytes("UTF-8"), 16));
            byte[] byteContent = content.getBytes();
            //初始化加密
            //ECB
            //cipher.init(Cipher.ENCRYPT_MODE, key);
            //CBC
            cipher.init(Cipher.ENCRYPT_MODE, key, iv);
            byte[] result = cipher.doFinal(byteContent);
            return result;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 解密
     *
     * @param content  待解密内容
     * @param password 解密密钥
     * @param keySize  密钥长度16,24,32
     * @return
     * @throws InvalidAlgorithmParameterException
     * @throws UnsupportedEncodingException
     */
    public static byte[] decrypt(byte[] content, String password, int keySize) throws InvalidAlgorithmParameterException, UnsupportedEncodingException {
        try {
            //密钥长度不够用0补齐。
            SecretKeySpec key = new SecretKeySpec(ZeroPadding(password.getBytes("UTF-8"), keySize), "AES");
            //定义加密算法AES、算法模式ECB、补码方式PKCS5Padding
            //Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            //定义加密算法AES 算法模式CBC、补码方式PKCS5Padding
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            //CBC模式模式下初始向量 不足16位用0补齐
            IvParameterSpec iv = new IvParameterSpec(ZeroPadding(password.getBytes("UTF-8"), 16));
            // 初始化解密
            //ECB
            //cipher.init(Cipher.DECRYPT_MODE, key);
            //CBC
            cipher.init(Cipher.DECRYPT_MODE, key, iv);

            byte[] result = cipher.doFinal(content);
            return result;

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将二进制转换成16进制
     *
     * @param buf
     * @return
     */
    public static String parseByte2HexStr(byte buf[]) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < buf.length; i++) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }

    /**
     * 将16进制转换为二进制
     *
     * @param hexStr
     * @return
     */
    public static byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.length() < 1) {
            return null;
        }
        byte[] result = new byte[hexStr.length() / 2];
        for (int i = 0; i < hexStr.length() / 2; i++) {
            int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }

    public static byte[] ZeroPadding(byte[] in, Integer blockSize) {
        Integer copyLen = in.length;
        if (copyLen > blockSize) {
            copyLen = blockSize;
        }
        byte[] out = new byte[blockSize];
        System.arraycopy(in, 0, out, 0, copyLen);
        return out;
    }

}
