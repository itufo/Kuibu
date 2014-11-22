package com.jack.db;

public class Task {
	public int mId;
	public String mTitle;
	public String mContent;
	public int mPlanT;
	
	public Task(){
		
	}
	
	public Task(String title, String content, int planT)
	{
		this.mTitle = title;
		this.mContent = content;
		this.mPlanT = planT;
	}
}
