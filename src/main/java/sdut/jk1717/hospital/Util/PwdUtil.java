package sdut.jk1717.hospital.Util;

import org.springframework.util.DigestUtils;

/**
 * @auther:chaoe
 * @date:2020/12/16
 **/


public class PwdUtil {
    private final static char[] HEX = "0123456789abcdef".toCharArray();
    public static String md5(String password){
        return bytes2Hex(DigestUtils.md5Digest(password.getBytes()));
    }
    public static String bytes2Hex(byte[] bys) {
        char[] chs = new char[bys.length * 2];
        for(int i = 0, offset = 0; i < bys.length; i++) {
            chs[offset++] = HEX[bys[i] >> 4 & 0xf];
            chs[offset++] = HEX[bys[i] & 0xf];
        }
        return new String(chs);
    }


}
