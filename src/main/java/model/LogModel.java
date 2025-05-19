package model;

import java.util.Date;

import interfaces.Model;
import util.DateUtil;

public class LogModel implements Model {
	
	private String id;
	private String log;
	private Date date;
	
	public LogModel(String log) {
		this.id = "1";
		this.log = log;
		this.date = DateUtil.today();
	}

	public String getLog() {
		return log;
	}

	public Date getDate() {
		return date;
	}

	public String getId() {
		return id;
	}

}
