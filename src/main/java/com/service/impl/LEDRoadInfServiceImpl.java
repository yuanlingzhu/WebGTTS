package com.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mapper.LEDRoadInfMapper;
import com.pojo.LEDRoadInf;
import com.service.LEDRoadInfService;

@Service
@Transactional
public class LEDRoadInfServiceImpl implements LEDRoadInfService {

	@Autowired
	private LEDRoadInfMapper ledRoadInfMapper;
	
	@Override
	public int insertLEDRoadInf(LEDRoadInf ledRoadInf) {
		return this.ledRoadInfMapper.insertLEDRoadInf(ledRoadInf);
	}
	
	@Override
	public List<LEDRoadInf> selectLEDRoadInfAll() {
		
		return this.ledRoadInfMapper.selectLEDRoadInfAll();
	}

	@Override
	public List<LEDRoadInf> selectLEDRoadInfById(String ledNo) {
		
		return this.ledRoadInfMapper.selectLEDRoadInfById(ledNo);
	}

	@Override
	public int updateLEDRoadInf(LEDRoadInf ledRoadInf) {
		return this.ledRoadInfMapper.updateLEDRoadInf(ledRoadInf);

	}

	@Override
	public int deleteRoadInf(LEDRoadInf ledRoadInf) {
		
		return this.ledRoadInfMapper.deleteRoadInf(ledRoadInf);
	}

	

}
