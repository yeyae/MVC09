package com.globalin.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class Todo {
	private String title;

	@DateTimeFormat(pattern = "yyyy/mm/dd")
	private Date dueDate;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	@Override
	public String toString() {
		return "Todo [title=" + title + ", dueDate=" + dueDate + "]";
	}
}
