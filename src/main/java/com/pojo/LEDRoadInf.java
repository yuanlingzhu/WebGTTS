package com.pojo;

public class LEDRoadInf {

	private int id;
	private String LEDNo;
	private String roadId;
	private String startLonGPS;
	private String startLatGPS;
	private String endLonGPS;
	private String endLatGPS;
	private int twoPointTime;
	private int timeShowX;
	private int timeShowY;

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

	public String getRoadId() {
		return roadId;
	}

	public void setRoadId(String roadId) {
		this.roadId = roadId;
	}

	public String getStartLonGPS() {
		return startLonGPS;
	}

	public void setStartLonGPS(String startLonGPS) {
		this.startLonGPS = startLonGPS;
	}

	public String getStartLatGPS() {
		return startLatGPS;
	}

	public void setStartLatGPS(String startLatGPS) {
		this.startLatGPS = startLatGPS;
	}

	public String getEndLonGPS() {
		return endLonGPS;
	}

	public void setEndLonGPS(String endLonGPS) {
		this.endLonGPS = endLonGPS;
	}

	public String getEndLatGPS() {
		return endLatGPS;
	}

	public void setEndLatGPS(String endLatGPS) {
		this.endLatGPS = endLatGPS;
	}

	public int getTwoPointTime() {
		return twoPointTime;
	}

	public void setTwoPointTime(int twoPointTime) {
		this.twoPointTime = twoPointTime;
	}

	public int getTimeShowX() {
		return timeShowX;
	}

	public void setTimeShowX(int timeShowX) {
		this.timeShowX = timeShowX;
	}

	public int getTimeShowY() {
		return timeShowY;
	}

	public void setTimeShowY(int timeShowY) {
		this.timeShowY = timeShowY;
	}

	public LEDRoadInf() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public LEDRoadInf(int id, String lEDNo, String roadId, String startLonGPS, String startLatGPS, String endLonGPS,
			String endLatGPS, int twoPointTime, int timeShowX, int timeShowY) {
		super();
		this.id = id;
		LEDNo = lEDNo;
		this.roadId = roadId;
		this.startLonGPS = startLonGPS;
		this.startLatGPS = startLatGPS;
		this.endLonGPS = endLonGPS;
		this.endLatGPS = endLatGPS;
		this.twoPointTime = twoPointTime;
		this.timeShowX = timeShowX;
		this.timeShowY = timeShowY;
	}

	@Override
	public String toString() {
		return "LEDRoadInf [id=" + id + ", LEDNo=" + LEDNo + ", roadId=" + roadId + ", startLonGPS=" + startLonGPS
				+ ", startLatGPS=" + startLatGPS + ", endLonGPS=" + endLonGPS + ", endLatGPS=" + endLatGPS
				+ ", twoPointTime=" + twoPointTime + ", timeShowX=" + timeShowX + ", timeShowY=" + timeShowY + "]";
	}

	

}
