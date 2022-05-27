package com.globalin.model;

import java.util.ArrayList;
import java.util.List;

public class SampleList {
	private List<Sample> list;

	public SampleList() {
		list = new ArrayList<>();
	}

	public List<Sample> getList() {
		return list;
	}

	@Override
	public String toString() {
		return "SampleList [list=" + list + "]";
	}

	public void setList(List<Sample> list) {
		this.list = list;
	}

}
