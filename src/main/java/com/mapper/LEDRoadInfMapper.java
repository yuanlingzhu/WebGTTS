package com.mapper;

import java.util.List;

import com.pojo.LEDRoadInf;

public interface LEDRoadInfMapper {

	//添加路况信息
	int insertLEDRoadInf(LEDRoadInf ledRoadInf);
	
	//修改路况信息
	int updateLEDRoadInf(LEDRoadInf ledRoadInf);
		
	//查询所有数据
	List<LEDRoadInf> selectLEDRoadInfAll();
	
	//根据LED编号查询这块屏下的所有信息
	List<LEDRoadInf> selectLEDRoadInfById(String ledNo);
	
	//根据LED编号查询这块屏下的路段数量
	int selectRoadNoNum(String ledNo);
	
	//删除 某条路段的信息
	int deleteRoadInf(LEDRoadInf ledRoadInf);
	
	
}
