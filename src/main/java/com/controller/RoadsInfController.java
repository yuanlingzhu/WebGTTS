package com.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pojo.LEDBackgroundImage;
import com.pojo.LEDRoadInf;
import com.service.LEDBackgroundImageService;
import com.service.LEDRoadInfService;
import com.service.RoadInfService;

@RestController
public class RoadsInfController {

	@Autowired
	private LEDBackgroundImageService ledBackgroundImageService;
	@Autowired
	private LEDRoadInfService ledRoadInfService;
	@Autowired
	private RoadInfService roadInfService;
	
	/******添加部分******/
	
	@RequestMapping(value = "/addLEDBackgroundImg", method = RequestMethod.POST)
	public Map<String, Object> addLEDBckgroundImg(String LEDNo, String backgroundImage, int pixelWidth,
			int pixelHeight) {
		Map<String, Object> map = this.roadInfService.insertLEDBackgroundImgInf(LEDNo, backgroundImage, pixelWidth,
				pixelHeight);
		return map;
	}

	@RequestMapping(value = "/addRoadStartAndEndGPS", method = RequestMethod.POST)
	public Map<String, Object> addRoadStartAndEndGPS(String LEDNo,String roadId, String startLon, String startLat, String endLon,
			String endLat) {
		Map<String, Object> map = this.roadInfService.insertRoadGPSInf(LEDNo,roadId, startLon, startLat, endLon, endLat);
		return map;
	}

	@RequestMapping(value = "/addRoadTimeShowCoordinate", method = RequestMethod.POST)
	public Map<String, Object> addRoadTimeShowCoordinate(String LEDNo, String roadId, int x, int y) {
		Map<String, Object> map = this.roadInfService.insertRoadTimeShowCoordinate(LEDNo, roadId, x, y);
		return map;
	}

	
	/******查询部分******/
	
	@RequestMapping(value = "/findRoadBackgroundImg", method = RequestMethod.POST)
	public List<LEDBackgroundImage> findRoadBackgroundImg(String LEDNo) {
		List<LEDBackgroundImage> list = new ArrayList<LEDBackgroundImage>();
		LEDBackgroundImage inf = this.ledBackgroundImageService.selectLEDBackgroundImageById(LEDNo);
		list.add(inf);
		return list;
	}

	@RequestMapping(value = "/isLEDNoExist", method = RequestMethod.POST)
	public Map<String, Object> isLEDNoExist(String LEDNo) {
		Map<String, Object> map = new HashMap<>();
		LEDBackgroundImage inf = this.ledBackgroundImageService.selectLEDBackgroundImageById(LEDNo);
		if (inf != null) {
			map.put("result", "true");
		} else {
			map.put("result", "false");
		}
		return map;
	}
	
	@RequestMapping(value = "/findRoadInf", method = RequestMethod.POST)
	public List<LEDRoadInf> findRoadInf(String LEDNo) {
		List<LEDRoadInf> list = this.ledRoadInfService.selectLEDRoadInfById(LEDNo);
		return list;
	}

	/******修改部分******/
	
	@RequestMapping(value = "/updataLEDBackgroundImg", method = RequestMethod.POST)
	public Map<String, Object> updataLEDBackgroundImg(String LEDNo, String backgroundImage, int pixelWidth,
			int pixelHeight) {
		Map<String, Object> map = this.roadInfService.updataLEDBackgroundImgInf(LEDNo, backgroundImage, pixelWidth, pixelHeight);
		return map;
	}
	
	
	/******删除部分******/
	
	@RequestMapping(value = "/deleteLEDAllInf", method = RequestMethod.POST)
	public Map<String, Object> deleteLEDAllInf(String LEDNo) {
		Map<String, Object> map = new HashMap<>();
		int result = this.ledBackgroundImageService.deleteLEDAllInf(LEDNo);
		if (result > 0) {
			map.put("result", "succes");
		} else {
			map.put("result", "error");
		}
		return map;
	}

	@RequestMapping(value = "/deleteRoadInf", method = RequestMethod.POST)
	public Map<String, Object> deleteRoadInf(String LEDNo,String roadId) {
		Map<String, Object> map = new HashMap<>();
		LEDRoadInf ledRoadInf = new LEDRoadInf();
		
		ledRoadInf.setLEDNo(LEDNo);
		ledRoadInf.setRoadId(roadId);
		int result = this.ledRoadInfService.deleteRoadInf(ledRoadInf);
		if (result > 0) {
			map.put("result", "succes");
		} else {
			map.put("result", "error");
		}
		return map;
	}
}
