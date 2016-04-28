package com.cbi.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.cbi.dao.CommunityGardenDAO;
import com.cbi.dao.DonationDAO;
import com.cbi.dao.PlantsGuideDAO;
import com.cbi.entity.CommunityGarden;
import com.cbi.entity.Donation;
import com.cbi.entity.PlantsGuide;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class DonationAction extends ActionSupport {
	
	private static final String SECCESS = "success";
	private static final String ERROR = "error";
	
	private ArrayList<CommunityGarden> cglist;
	private ArrayList<PlantsGuide> plist;
	private ArrayList<Donation> dlist;
	
	private Donation d;

	public ArrayList<CommunityGarden> getCglist() {
		return cglist;
	}

	public void setCglist(ArrayList<CommunityGarden> cglist) {
		this.cglist = cglist;
	}

	public ArrayList<PlantsGuide> getPlist() {
		return plist;
	}

	public void setPlist(ArrayList<PlantsGuide> plist) {
		this.plist = plist;
	}

	public Donation getD() {
		return d;
	}

	public void setD(Donation d) {
		this.d = d;
	}

	public ArrayList<Donation> getDlist() {
		return dlist;
	}

	public void setDlist(ArrayList<Donation> dlist) {
		this.dlist = dlist;
	}

	public String initForm() throws Exception {
		CommunityGardenDAO cgdao = new CommunityGardenDAO();
		cglist = (ArrayList<CommunityGarden>) cgdao.findAllCommunityGarden();
		PlantsGuideDAO pdao = new PlantsGuideDAO();
		plist = (ArrayList<PlantsGuide>) pdao.findAllPlantsGuide();
		return SUCCESS;
	}
	
	public String storeDonationForm(){
		ActionContext actionContext = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) actionContext.get(ServletActionContext.HTTP_REQUEST);
		
		String fullName = request.getParameter("fullName");
		String commGardenName = request.getParameter("commGardenName");
		String email = request.getParameter("email");
		String street = request.getParameter("street");
		String suburb = request.getParameter("suburb");
		String postCode = request.getParameter("postCode");
		String product = request.getParameter("product");
		double amount = Double.parseDouble(request.getParameter("amount"));
		String unit = request.getParameter("unit");
		String date = request.getParameter("date");
		String timeRange = request.getParameter("timeRange");
		String status = "Waiting for collect";
		
		d = new Donation(0, fullName, commGardenName, email, street, suburb, postCode, product, amount, unit, date, timeRange, status);
		DonationDAO ddao = new DonationDAO();
		ddao.insertDonationRecord(d);
		
		return "donationInfo";
	}
	
	public String getDonatioList(){
		DonationDAO ddao = new DonationDAO();
		dlist = (ArrayList<Donation>) ddao.getAllDonation();
		
		return "list";
	}
	
}
