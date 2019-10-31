package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.pojo.LEDRoadInf;
import com.service.LEDRoadInfService;
import com.util.ServersHelper;

@Component
@Configuration  //1.主要用于标记配置类，兼备Component的效果。
@EnableScheduling  // 2.开启定时任务
public class GetRoadTimeThread  {


	@Autowired 
	private LEDRoadInfService ledRoadInfService;
	
	 //3.添加定时任务
    @Scheduled(cron = "0 0/59 * * * ? ")
    private void configureTasks() {
    	List<LEDRoadInf> roadInfAll = this.ledRoadInfService.selectLEDRoadInfAll();
    	for (int i = 0; i < roadInfAll.size(); i++) {
    		if (roadInfAll.get(i).getStartLonGPS()!=null && roadInfAll.get(i).getStartLatGPS()!=null && roadInfAll.get(i).getEndLonGPS()!=null && roadInfAll.get(i).getEndLatGPS()!=null) {
    			LEDRoadInf ledRoadInf = new LEDRoadInf();
    			ledRoadInf.setLEDNo(roadInfAll.get(i).getLEDNo());
    			ledRoadInf.setRoadId(roadInfAll.get(i).getRoadId());
    			int roodTime = ServersHelper.getRoadTime(roadInfAll.get(i).getStartLonGPS(), roadInfAll.get(i).getStartLatGPS(), roadInfAll.get(i).getEndLonGPS(), roadInfAll.get(i).getEndLatGPS());
    			ledRoadInf.setTwoPointTime(roodTime);
        		this.ledRoadInfService.updateLEDRoadInf(ledRoadInf);
			}
            System.err.println("roadinf:"+roadInfAll.get(i));
		}


    }
}
