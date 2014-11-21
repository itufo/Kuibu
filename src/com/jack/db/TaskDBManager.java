package com.jack.db;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class TaskDBManager {
	private TaskDBHelper helper;
	private SQLiteDatabase db;
	
	public TaskDBManager(Context context) {
		helper = new TaskDBHelper(context);
		//因为getWritableDatabase内部调用了mContext.openOrCreateDatabase(mName, 0, mFactory);
		//所以要确保context已初始化,我们可以把实例化DBManager的步骤放在Activity的onCreate里
		db = helper.getWritableDatabase();
	}
	
	public void add(Task task)
	{
        db.beginTransaction();	//开始事务
        try {
       		db.execSQL("INSERT INTO task VALUES(null, ?, ?, ?)", new Object[]{task.mTitle, task.mContent, task.mPlanT});
        	db.setTransactionSuccessful();	//设置事务成功完成
        } finally {
        	db.endTransaction();	//结束事务
        }		
	}
	
	/**
	 * add tasks
	 * @param tasks
	 */
	public void add(List<Task> tasks) {
        db.beginTransaction();	//开始事务
        try {
        	for (Task task : tasks) {
        		db.execSQL("INSERT INTO task VALUES(null, ?, ?, ?)", new Object[]{task.mTitle, task.mContent, task.mPlanT});
        	}
        	db.setTransactionSuccessful();	//设置事务成功完成
        } finally {
        	db.endTransaction();	//结束事务
        }
	}
	
	/**
	 * update person's age
	 * @param person
	 */
	public void updateTitle(Task task) {
		ContentValues cv = new ContentValues();
		cv.put("title", task.mTitle);
		db.update("task", cv, "_id = ?", new String[]{String.valueOf(task.mId)});
	}
	
	/**
	 * delete task
	 * @param task
	 */
	public void deleteTask(Task task) {
		db.delete("task", "_id = ?", new String[]{String.valueOf(task.mId)});
	}
	
	
	/**
	 * query all tasks, return list
	 * @return List<Task>
	 */
	public List<Task> query() {
		ArrayList<Task> tasks = new ArrayList<Task>();
		Cursor c = queryTheCursor();
        while (c.moveToNext()) {
        	Task task = new Task();
        	task.mId = c.getInt(c.getColumnIndex("_id"));
        	task.mTitle = c.getString(c.getColumnIndex("title"));
        	task.mContent = c.getString(c.getColumnIndex("content"));
        	task.mPlanT = c.getInt(c.getColumnIndex("planT"));
        	tasks.add(task);
        }
        c.close();
        return tasks;
	}
	
	/**
	 * query all persons, return cursor
	 * @return	Cursor
	 */
	public Cursor queryTheCursor() {
        Cursor c = db.rawQuery("SELECT * FROM task", null);
        return c;
	}
	
	/**
	 * close database
	 */
	public void closeDB() {
		db.close();
	}
}
