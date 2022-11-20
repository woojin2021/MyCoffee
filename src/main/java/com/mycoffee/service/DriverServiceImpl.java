package com.mycoffee.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.mycoffee.domain.DriverDTO;
import com.mycoffee.domain.DriverInfo;
import com.mycoffee.domain.DriverOrderVO;
import com.mycoffee.domain.DriverVO;
import com.mycoffee.mapper.DeliveryMapper;
import com.mycoffee.mapper.DriverMapper;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@AllArgsConstructor
@Log4j
@Service
public class DriverServiceImpl implements DriverService{

	private DriverMapper drivermapper;
	private DeliveryMapper deliverymapper;

	
	@Override
	public int memberJoin(DriverDTO driver) throws Exception {
		return drivermapper.insert(driver);
	}

	@Override
	public DriverVO getDriver(String did) {
		log.info("getDriver: [pid=" + did + "]");
		return drivermapper.selectDriver(did);
	}

	@Override
	public boolean modify(DriverDTO driver) {
		log.info(driver);
		return drivermapper.update(driver) > 0;
	}

	@Override
	public boolean delete(String did) {
		log.info(did);
		return drivermapper.delete(did) > 0;
	}

	@Override
	public int idCheck(String did) throws Exception{
		return drivermapper.idCheck(did);
	}

	@Override
	public DriverVO driverLogin(DriverDTO driver) throws Exception {
		return drivermapper.driverLogin(driver);	
	}

	@Override
	public boolean workDriver(String did) {
		
		DriverVO driver = drivermapper.select(did);
		return (driver.getOnwork() == 1);
	}

	@Override
	public boolean startDriver(String did) {
		log.info("startDriver");
		DriverDTO dvo = new DriverDTO();
		dvo.setDid(did);
		dvo.setOnwork(1);
		drivermapper.updateOnWork(dvo);
		return workDriver(did);
	}

	@Override
	public boolean endDriver(String did) {
		log.info("endDriver");
		DriverDTO dvo = new DriverDTO();
		dvo.setDid(did);
		dvo.setOnwork(0);
		drivermapper.updateOnWork(dvo);
		
		return workDriver(did);
	}

	@Override
	public List<DriverInfo> getOrder(String did) {
		List<DriverInfo> oList = new ArrayList<>();
		
		SimpleDateFormat formatter = new SimpleDateFormat("hh:mm");
		String oid = "";
		DriverInfo temp = null;
		for (DriverOrderVO data : deliverymapper.selectDriverOrderList(did)) {
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
	
	private String getOrderSummary(DriverOrderVO data) {
		
		String summary = "[";	// [2] 카라멜 마키아또
		summary += data.getPieces() + "]";
		
		if (data.getPtype() == 0) {
			summary += data.getTemperaturedisp() + "_";
		}
		summary += data.getPname();
		
		return summary;
		
	}

//	@Override
//	public DriverVO driverLogin(String did, String password) {
//		DriverVO vo = new DriverVO();
//		vo.setDid(did);
//		vo.setPassword(password);
//		return drivermapper.driverLogin(did, password);
//	}

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
