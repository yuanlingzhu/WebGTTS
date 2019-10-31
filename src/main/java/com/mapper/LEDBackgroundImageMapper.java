package com.mapper;

import com.pojo.LEDBackgroundImage;

public interface LEDBackgroundImageMapper {
	
	//添加LED背景图
	int insertLEDBackgroundImage(LEDBackgroundImage ledBackgroundImage);
	
	//根据LED编号查询这块屏下的背景图
	LEDBackgroundImage selectLEDBackgroundImageById(String ledNo);
	
	//修改LED背景图
	int updateLEDBackgroundImage(LEDBackgroundImage ledBackgroundImage);

	//删除此LED下的说有信息
	int deleteLEDAllInf(String ledNo);
}
