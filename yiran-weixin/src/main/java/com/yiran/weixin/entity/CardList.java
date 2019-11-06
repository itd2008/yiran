package com.yiran.weixin.entity;

import java.io.Serializable;

public class CardList implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8730074992847968663L;

	private String cardId;
	
	private String cardExt;

	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	public String getCardExt() {
		return cardExt;
	}

	public void setCardExt(String cardExt) {
		this.cardExt = cardExt;
	}

	@Override
	public String toString() {
		return "CardList [cardId=" + cardId + ", cardExt=" + cardExt + "]";
	}
	
	
}
