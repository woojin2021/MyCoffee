package com.mycoffee.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	public int memberJoin(DriverDTO driver) {
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
		if (driver.getPassword() != null && driver.getPassword().trim().length() == 0) {
			driver.setPassword(null);
		}
		return drivermapper.update(driver) > 0;
	}

	@Override
	public boolean delete(String did) {
		log.info(did);
		return drivermapper.delete(did) > 0;
	}

	@Override
	public int idCheck(String did) {
		return drivermapper.idCheck(did);
	}

	@Override
	public DriverVO driverLogin(DriverDTO driver) {
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
	public List<DriverInfo> getOrder(String did, Model model) {
		List<DriverOrderVO> datas = deliverymapper.selectDriverOrderList(did);
		List<DriverInfo> oList = getOrder(datas);
		
		model.addAttribute("order", oList);
		return oList;
	}
	
	/**
	 * tbl_order_detail부분을 압축해서 주문정보를 1줄로 표시
	 * @param datas
	 * @return
	 */
	private List<DriverInfo> getOrder(List<DriverOrderVO> datas) {
		List<DriverInfo> oList = new ArrayList<>();
		
		SimpleDateFormat formatter = new SimpleDateFormat("hh:mm");
		String oid = "";
		DriverInfo temp = null;
		for (DriverOrderVO data : datas) {
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
	
	/**
	 * tbl_order_detail >> [수량] 제품명
	 * ex) [2] 아이스_카라멜 마키아또
	 * @param data
	 * @return
	 */
	private String getOrderSummary(DriverOrderVO data) {
		
		String summary = "[";	// [2] 카라멜 마키아또
		summary += data.getPieces() + "]";
		
		if (data.getPtype() == 0) {
			summary += data.getTemperaturedisp() + "_";
		}
		summary += data.getPname();
		
		return summary;
		
	}

	@Override
	public void checkoutOrder(String oid, String did, RedirectAttributes rttr, Model model) {
		if (deliverymapper.updateOrderToCheckout(oid, did) == 0) {
			rttr.addFlashAttribute("checkout", "다른 배달원이 접수한 주문입니다.");
		}
		getOrder(did, model);
	}

	@Override
	public void completeOrder(String oid, String did, RedirectAttributes rttr, Model model) {
		if (deliverymapper.updateOrderToComplete(oid, did) == 0) {
			rttr.addFlashAttribute("checkout", "취소가 된 주문입니다..");
		}
		getOrder(did, model);
	}

	@Override
	public List<DriverInfo> getUncheckedOrder() {
		List<DriverOrderVO> datas = deliverymapper.selectUncheckedOrderList();
		List<DriverInfo> oList = getOrder(datas);
		
		return oList;
	}

	
}
