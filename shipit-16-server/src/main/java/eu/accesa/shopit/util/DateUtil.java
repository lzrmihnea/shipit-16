package eu.accesa.shopit.util;

public class DateUtil {

    public static java.sql.Date convertUtilToSql(java.util.Date utilDate) {
        return new java.sql.Date(utilDate.getTime());
    }

    public static java.util.Date convertSqlToUtil(java.sql.Date sqlDate) {
        return new java.util.Date(sqlDate.getTime());
    }
}
