package com.jack.kuibu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class TaskActivity extends Activity {

	public static final String EXTRA_ITEM_POSITION =
			"com.jack.kuibu.item_position";
	
	public static final String EXTRA_SAVE_CONTENT =
			"com.jack.kuibu.task_content";

	private Button mStartBtn;
	private Button mPauseBtn;
	private Button mCompleteBtn;
	private TextView mTitle;
	private TextView mContent;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_task);
		
		mTitle = (TextView)findViewById(R.id.taskTitle);
		mContent = (TextView)findViewById(R.id.taskContent);
		mStartBtn = (Button)findViewById(R.id.taskStartBtn);
		mPauseBtn = (Button)findViewById(R.id.taskPauseBtn);
		mCompleteBtn = (Button)findViewById(R.id.taskCompleteBtn);
		int p = getIntent().getIntExtra(EXTRA_ITEM_POSITION, -1);
		mTitle.setText(String.valueOf(p));
/*		
		mSaveBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				setResult(true);
				TaskActivity.this.finish();
			}
		});
*/		
	}
/*	
	private void setResult(boolean isSave)
	{
		if(isSave)
		{
			Intent data = new Intent();
			data.putExtra(EXTRA_SAVE_TITLE, mTitle.getText().toString());
			data.putExtra(EXTRA_SAVE_CONTENT, mContent.getText().toString());
			setResult(RESULT_OK, data);
		}
		else{
			setResult(RESULT_CANCELED);
		}
	}
*/	
}
