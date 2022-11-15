package com.mycoffee.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycoffee.domain.DriverInfo;
import com.mycoffee.domain.DriverOrderDTO;
import com.mycoffee.domain.DriverVO;
import com.mycoffee.domain.OrderOfTodyVO;
import com.mycoffee.domain.OrderrInfo;
import com.mycoffee.mapper.DriverMapper;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@AllArgsConstructor
@Log4j
@Service
public class DriverServiceImpl implements DriverService{

	@Autowired
	private DriverMapper drivermapper;
	
	@Override
	public void memberJoin(DriverVO driver) throws Exception {

		drivermapper.insert(driver);
		
	}

	@Override
	public int idCheck(String did) throws Exception{
		
		return drivermapper.idCheck(did);
	}

	@Override
	public int getDriveronWork() {
		
		return drivermapper.getDriveronWork();
	}

	@Override
	public boolean startDriver(String did) {
		
		log.info("startDriver");
		DriverVO dvo = new DriverVO();
		dvo.setDid(did);
		dvo.setOnwork(1);
		drivermapper.updateStartDriver(dvo);
		return workDriver(did);
	}

	@Override
	public boolean endDriver(String did) {
		
		log.info("endDriver");
		DriverVO dvo = new DriverVO();
		dvo.setDid(did);
		dvo.setOnwork(0);
		drivermapper.updateStartDriver(dvo);
		
		return workDriver(did);
	}

	@Override
	public boolean workDriver(String did) {
		
		DriverVO driver = drivermapper.select(did);
		return (driver.getOnwork() == 1);
	}

	@Override
	public List<DriverInfo> getOrder(String did) {
		List<DriverInfo> oList = new ArrayList<>();
		
		SimpleDateFormat formatter = new SimpleDateFormat("hh:mm");
		String oid = "";
		DriverInfo temp = null;
		for (DriverOrderDTO data : drivermapper.getOrder(did)) {
			if (oid.equals(data.getOid()) == false) {
				temp = new DriverInfo();
				temp.setOid(data.getOid());
				temp.setOrdertime(formatter.format(data.getOrderdate()));
				temp.setAddress(data.getAddress());
				temp.setStatus(data.getStatus());
				temp.setStatusdisp(data.getStatusdisp());
				temp.setOrderdetail(getOrderSummary(data));
				temp.setTotalprice(data.getTotalprice());
				temp.setUserinfo(data.getName().substring(0, 1) + "OO / " + data.getMobile());
				oList.add(temp);
				oid = data.getOid();
			} else {
				temp.setOrderdetail(temp.getOrderdetail() + "<br>" + getOrderSummary(data));
			}
			
		}
		return oList;
	}
	
	private String getOrderSummary(DriverOrderDTO data) {
		
		String summary = "[";	// [2] 카라멜 마키아또
		summary += data.getPieces() + "]";
		
		if (data.getPtype() == 0) {
			summary += data.getTemperaturedisp() + "_";
		}
		summary += data.getPname();
		
		return summary;
		
	}

	@Override
	public DriverVO driverLogin(DriverVO driver) throws Exception {
		
		return drivermapper.driverLogin(driver);	
	}
	
	@Override
	public DriverVO getDriver(String did) {
		log.info("getDriver: [pid=" + did + "]");
		return drivermapper.selectDriver(did);
	}

	@Override
	public void approveRegist(DriverVO driver) {
		driver.setPermitted(1);  // 1:등록 승인
		drivermapper.updatePermition(driver);
	}

	@Override
	public void rejectRegist(DriverVO driver) {
		driver.setPermitted(2);  // 2:승인 거부
		drivermapper.updatePermition(driver);
	}

	@Override
	public DriverVO driverLogin(String did, String password) {

		return drivermapper.driverLogin(did, password);
	}

	@Override
	public boolean modify(DriverVO driver) {
		log.info(driver);
		return drivermapper.updateDriver(driver);
	}

	@Override
	public boolean delete(String did) {
		log.info(did);
		return drivermapper.deleteDriver(did);
	}

//	@Override
//	public boolean driverStatus(DriverInfo driver) {
//		
//		if(driver.getStatus() == 3) {
//			driver.setStatus(4);
//			drivermapper.updateStatus(driver);
//		}else {
//			driver.setStatus(5);
//			drivermapper.updateStatus(driver);
//		}
//		return ;
//	}

}
