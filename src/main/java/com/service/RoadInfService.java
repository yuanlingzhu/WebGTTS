package com.service;

import java.util.Map;

public interface RoadInfService {

	// 添加初始化信息
	Map<String, Object> insertLEDBackgroundImgInf(String LEDNo, String backgroundImage,int pixelWidth,int pixelHeight);
	
	//添加某一段路初始信息 也是修改方法
	Map<String, Object> insertRoadGPSInf(String LEDNo,String roadId,String startLon, String startLat,String endLon, String endLat);
	
	//添加某一路段时间显示的坐标
	Map<String, Object> insertRoadTimeShowCoordinate(String LEDNo, String roadId, int x, int y);
	
	int insertRoadTime(String LEDNo, String roadId,int twoPointTime);
	
	//修改图片信息
	Map<String, Object> updataLEDBackgroundImgInf(String LEDNo, String backgroundImage,int pixelWidth,int pixelHeight);
	
}
