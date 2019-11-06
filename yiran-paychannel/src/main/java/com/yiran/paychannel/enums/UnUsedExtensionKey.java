package com.yiran.paychannel.enums;

public enum UnUsedExtensionKey {
	
	CVV2("CVV2",true,false,false);
	//CERKEY("CER_KEY",true,false,false);
	
	public final String key;
	public final boolean used;
	public final boolean saved;
	public final boolean logged;
	
	UnUsedExtensionKey(String key,boolean used,boolean saved,boolean logged){
        this.key = key;
        this.used = used;
        this.saved = saved;
        this.logged = logged;
    }
	
	public static UnUsedExtensionKey getByKey(String key) {
        for (UnUsedExtensionKey resultCode : UnUsedExtensionKey.values()) {
            if (resultCode.getKey().equals(key)) {
                return resultCode;
            }
        }
        return null;
    }
	
	public String getKey() {
		return key;
	}
	public boolean isUsed() {
		return used;
	}
	public boolean isSaved() {
		return saved;
	}
	public boolean isLogged() {
		return logged;
	}
	
	
}
