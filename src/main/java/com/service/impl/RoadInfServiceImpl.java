package com.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mapper.LEDRoadInfMapper;
import com.pojo.LEDBackgroundImage;
import com.pojo.LEDRoadInf;
import com.service.LEDBackgroundImageService;
import com.service.LEDRoadInfService;
import com.service.RoadInfService;

@Service
@Transactional
public class RoadInfServiceImpl implements RoadInfService {

	@Autowired
	private LEDBackgroundImageService ledBackgroundImageService;
	@Autowired
	private LEDRoadInfService ledRoadInfService;
	@Autowired
	private LEDRoadInfMapper ledRoadInfMapper;
	
	@Override
	public Map<String, Object> insertLEDBackgroundImgInf(String LEDNo, String backgroundImage,int pixelWidth,int pixelHeight) {
		
		Map<String, Object> map = new HashMap<>();
		LEDBackgroundImage ledBackgroundImage = new LEDBackgroundImage();
		
		ledBackgroundImage.setLEDNo(LEDNo);
		ledBackgroundImage.setBackgroundImage(backgroundImage);
		ledBackgroundImage.setPixelWidth(pixelWidth);
		ledBackgroundImage.setPixelHeight(pixelHeight);
		int result = this.ledBackgroundImageService.insertLEDBackgroundImage(ledBackgroundImage);
		if (result > 0) {
			map.put("result", "succes");
		} else {
			map.put("result", "error");
			
		}
		return map;
	}

	@Override
	public Map<String, Object> insertRoadGPSInf(String LEDNo,String roadId, String startLon, String startLat, String endLon,
			String endLat) {
		
		int result;
		Map<String, Object> map = new HashMap<>();
		LEDRoadInf ledRoadInf = new LEDRoadInf();
		if( roadId==null || roadId.equals(null) || roadId=="" || roadId.equals("")) {
			//新增
			int num;
			num = this.ledRoadInfMapper.selectRoadNoNum(LEDNo);
			num++;
			ledRoadInf.setLEDNo(LEDNo);
			ledRoadInf.setRoadId(num+"");
			ledRoadInf.setStartLonGPS(startLon);
			ledRoadInf.setStartLatGPS(startLat);
			ledRoadInf.setEndLonGPS(endLon);
			ledRoadInf.setEndLatGPS(endLat);
			result= this.ledRoadInfService.insertLEDRoadInf(ledRoadInf);
		}else {
			//修改
			ledRoadInf.setLEDNo(LEDNo);
			ledRoadInf.setRoadId(roadId);
			ledRoadInf.setStartLonGPS(startLon);
			ledRoadInf.setStartLatGPS(startLat);
			ledRoadInf.setEndLonGPS(endLon);
			ledRoadInf.setEndLatGPS(endLat);
			result = this.ledRoadInfService.updateLEDRoadInf(ledRoadInf);
		}
		if(result>0) {
			map.put("result", "succes");
		}else{
			map.put("result", "error");
		}
		return map;
	}

	@Override
	public Map<String, Object> insertRoadTimeShowCoordinate(String LEDNo, String roadId, int x, int y) {
		
		Map<String, Object> map = new HashMap<>();
		LEDRoadInf ledRoadInf = new LEDRoadInf();
		
		ledRoadInf.setLEDNo(LEDNo);
		ledRoadInf.setRoadId(roadId);
		ledRoadInf.setTimeShowX(x);
		ledRoadInf.setTimeShowY(y);
		
		int result = this.ledRoadInfService.updateLEDRoadInf(ledRoadInf);
		if(result>0) {
			map.put("result", "succes");
		}else{
			map.put("result", "error");
		}
		return map;
	}

	@Override
	public int insertRoadTime(String LEDNo, String roadId, int twoPointTime) {
		
		LEDRoadInf ledRoadInf = new LEDRoadInf();
		
		ledRoadInf.setLEDNo(LEDNo);
		ledRoadInf.setRoadId(roadId);
		ledRoadInf.setTwoPointTime(twoPointTime);
		
		return this.ledRoadInfService.updateLEDRoadInf(ledRoadInf);
	}


	@Override
	public Map<String, Object> updataLEDBackgroundImgInf(String LEDNo, String backgroundImage, int pixelWidth, int pixelHeight) {
		
		Map<String, Object> map = new HashMap<>();
		LEDBackgroundImage ledBackgroundImage = new LEDBackgroundImage();
		
		ledBackgroundImage.setLEDNo(LEDNo);
		ledBackgroundImage.setBackgroundImage(backgroundImage);
		ledBackgroundImage.setPixelWidth(pixelWidth);
		ledBackgroundImage.setPixelHeight(pixelHeight);
		int result = this.ledBackgroundImageService.updateLEDBackgroundImage(ledBackgroundImage);
		if (result > 0) {
			map.put("result", "succes");
		} else {
			map.put("result", "error");
		}
		return map;
	}

	
}
