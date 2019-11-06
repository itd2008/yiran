package com.yiran.member.exception;

/**
 * 
 * <p>MA 参数检验异常</p>
 */
public class MaIllegalArgumentException extends IllegalArgumentException {

    private static final long serialVersionUID = 4164067046709770426L;

    public MaIllegalArgumentException(String s) {
        super(s);
    }
    
    public MaIllegalArgumentException( String s, Exception cause ) {
        super(s);
        this.initCause(cause);
    }

}
