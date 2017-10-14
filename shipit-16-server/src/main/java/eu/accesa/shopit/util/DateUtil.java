package eu.accesa.shopit.util;

import org.springframework.util.ObjectUtils;

public class DateUtil {

    public static java.sql.Date convertUtilToSql(java.util.Date utilDate) {
        if(ObjectUtils.isEmpty(utilDate)){
            return null;
        }
        return new java.sql.Date(utilDate.getTime());
    }

    public static java.util.Date convertSqlToUtil(java.sql.Date sqlDate) {
        if(ObjectUtils.isEmpty(sqlDate)){
            return null;
        }
        return new java.util.Date(sqlDate.getTime());
    }
}
