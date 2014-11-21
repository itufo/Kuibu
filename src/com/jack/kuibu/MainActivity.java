package com.jack.kuibu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.jack.db.Task;
import com.jack.db.TaskDBManager;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.os.Build;

public class MainActivity extends ActionBarActivity {

	private static final String TAG = "MainActivity";
	private TaskDBManager mDBMgr; 
	private Button mAddBtn;
	
	SimpleAdapter mlistAdapter;  // ListView的适配器
	ArrayList<HashMap<String, Object>> mlistItem;  // ListView的数据源，这里是一个HashMap的列表
	ListView mList;  // ListView控件
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mlistItem = new ArrayList<HashMap<String, Object>>();
		mlistAdapter = new SimpleAdapter(this, mlistItem, R.layout.list_item, 
		new String[]{"image", "title", "text"},
		new int[]{R.id.ItemImage, R.id.ItemTitle, R.id.ItemText});
		
		mList = (ListView)findViewById(R.id.taskList);	
		mList.setAdapter(mlistAdapter);
		
		mAddBtn = (Button)findViewById(R.id.add);
		mAddBtn.setOnClickListener(new View.OnClickListener() {
		
			@Override
			public void onClick(View v) {
				Log.d(TAG, "add click");
				Intent i = new Intent(MainActivity.this, AddActivity.class);

				startActivityForResult(i,0);

			}
		});
		
		mDBMgr = new TaskDBManager(this);

	}
	
	protected void loadTasks()
	{
		
		List<Task> tasks = mDBMgr.query();
		for(Task task: tasks)
		{
			
		}
	}
	
	protected void addListItem(Task task)
	{
		String title = 	task.mTitle;
		String content = task.mContent;
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("image", R.drawable.ic_launcher);
		map.put("title", title);
		map.put("text", content);
		mlistItem.add(map);
		
		mlistAdapter.notifyDataSetChanged();
		
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (data == null) {
			return;
		}
		if(resultCode == RESULT_CANCELED)
		{
			return;
		}
		String title = 	data.getStringExtra(AddActivity.EXTRA_SAVE_TITLE);
		String content = data.getStringExtra(AddActivity.EXTRA_SAVE_CONTENT);
		
		Task task = new Task(title,content,15);
		
		mDBMgr.add(task);
		addListItem(task);
	}

	@Override
	public void onDestroy(){
		super.onDestroy();

		mDBMgr.closeDB();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			return rootView;
		}
	}

}
