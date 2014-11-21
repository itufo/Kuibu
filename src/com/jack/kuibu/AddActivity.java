package com.jack.kuibu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddActivity extends Activity {

	public static final String EXTRA_SAVE_TITLE =
			"com.jack.kuibu.save_title";
	
	public static final String EXTRA_SAVE_CONTENT =
			"com.jack.kuibu.save_content";

	private Button mSaveBtn;
	private EditText mTitle;
	private EditText mContent;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add);
		
		mTitle = (EditText)findViewById(R.id.saveEditTitle);
		mContent = (EditText)findViewById(R.id.saveEditContent);
		mSaveBtn = (Button)findViewById(R.id.save);
		
		mSaveBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				setResult(true);
				AddActivity.this.finish();
			}
		});
	}
	
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
}
