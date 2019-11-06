/**
 * 
 */
package com.yiran.member.utils;

import java.sql.SQLException;

/**
 * <p>异常处理工具</p>
 */
public class SQLExceptionUtil {
    
    /**
     * 主键冲突或唯一约束异常
     * @param ex
     * @return
     */
    public static boolean isUniqueException(Exception ex) {
        //ORACLE主键冲突异常代码,ORA-00001
        Throwable throwable = ex.getCause();
        if (throwable != null && throwable.getCause() instanceof SQLException) {
            SQLException sqlE = (SQLException) throwable.getCause();
            return sqlE.getErrorCode() == 1;
        }
        return false;
    }

}
