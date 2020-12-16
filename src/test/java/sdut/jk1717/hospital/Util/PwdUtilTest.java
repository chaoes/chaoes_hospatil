package sdut.jk1717.hospital.Util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PwdUtilTest {

    @Test
    void md5() {
        System.out.println(PwdUtil.md5("8888"));
    }
}