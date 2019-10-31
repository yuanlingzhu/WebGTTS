package com.pojo;

public class LEDBackgroundImage {

	private int id;
	private String LEDNo;
	private String backgroundImage;
	private int pixelWidth;
	private int pixelHeight;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLEDNo() {
		return LEDNo;
	}

	public void setLEDNo(String lEDNo) {
		LEDNo = lEDNo;
	}

	public String getBackgroundImage() {
		return backgroundImage;
	}

	public void setBackgroundImage(String backgroundImage) {
		this.backgroundImage = backgroundImage;
	}

	public int getPixelWidth() {
		return pixelWidth;
	}

	public void setPixelWidth(int pixelWidth) {
		this.pixelWidth = pixelWidth;
	}

	public int getPixelHeight() {
		return pixelHeight;
	}

	public void setPixelHeight(int pixelHeight) {
		this.pixelHeight = pixelHeight;
	}

	public LEDBackgroundImage() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LEDBackgroundImage(int id, String lEDNo, String backgroundImage, int pixelWidth, int pixelHeight) {
		super();
		this.id = id;
		LEDNo = lEDNo;
		this.backgroundImage = backgroundImage;
		this.pixelWidth = pixelWidth;
		this.pixelHeight = pixelHeight;
	}

	@Override
	public String toString() {
		return "LEDBackgroundImage [id=" + id + ", LEDNo=" + LEDNo + ", backgroundImage=" + backgroundImage
				+ ", pixelWidth=" + pixelWidth + ", pixelHeight=" + pixelHeight + "]";
	}

}
