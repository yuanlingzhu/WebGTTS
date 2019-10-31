package com.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mapper.LEDBackgroundImageMapper;
import com.pojo.LEDBackgroundImage;
import com.service.LEDBackgroundImageService;

@Service
@Transactional
public class LEDBackgroundImageServiceImpl implements LEDBackgroundImageService {

	@Autowired
	private LEDBackgroundImageMapper ledBackgroundImageMapper;
	
	@Override
	public int insertLEDBackgroundImage(LEDBackgroundImage ledBackgroundImage) {
		return this.ledBackgroundImageMapper.insertLEDBackgroundImage(ledBackgroundImage);
	}

	@Override
	public LEDBackgroundImage selectLEDBackgroundImageById(String ledNo) {
		return this.ledBackgroundImageMapper.selectLEDBackgroundImageById(ledNo);
	}

	@Override
	public int updateLEDBackgroundImage(LEDBackgroundImage ledBackgroundImage) {
		return this.ledBackgroundImageMapper.updateLEDBackgroundImage(ledBackgroundImage);
	}

	@Override
	public int deleteLEDAllInf(String ledNo) {
		return this.ledBackgroundImageMapper.deleteLEDAllInf(ledNo);
	}

}
