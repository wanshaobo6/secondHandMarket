package com.pxxysecondhand.tempPojo;


public class Mymessage {
	
	//来自谁的留言
	private String fromUsername;
	
	//留言 的内容
	private String message;
	
	//留言的日期
	private String messageDate;
	//对那件东西的留言
	private String  itemName;
	
	//东西的价格
	private Integer currentPrice;
	
	//图片
	private String image;

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getFromUsername() {
		return fromUsername;
	}

	public void setFromUsername(String fromUsername) {
		this.fromUsername = fromUsername;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMessageDate() {
		return messageDate;
	}

	public void setMessageDate(String messageDate) {
		this.messageDate = messageDate;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public Integer getCurrentPrice() {
		return currentPrice;
	}

	public void setCurrentPrice(Integer currentPrice) {
		this.currentPrice = currentPrice;
	}

	

	public Mymessage(String fromUsername, String message, String messageDate, String itemName, Integer currentPrice,
			String image) {
		super();
		this.fromUsername = fromUsername;
		this.message = message;
		this.messageDate = messageDate;
		this.itemName = itemName;
		this.currentPrice = currentPrice;
		this.image = image;
	}

	@Override
	public String toString() {
		return "Mymessage [fromUsername=" + fromUsername + ", message=" + message + ", messageDate=" + messageDate
				+ ", itemName=" + itemName + ", currentPrice=" + currentPrice + ", image=" + image + "]";
	}

	public Mymessage() {
		super();
		// TODO Auto-generated constructor stub
	}

	

}
