package sdut.jk1717.hospital.Util;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


/**
 * @auther:chaoe
 * @date:2020/12/15
 **/


public class DateUtil {
    public static Date getTodayDate(){
        java.sql.Date date = new java.sql.Date(System.currentTimeMillis());
        return date;
    }
}
