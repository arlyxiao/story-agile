package com.android.story;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;



public class StoryAgile extends Activity {

	private SQLiteAdapter mySQLiteAdapter;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        listData();
        
    }
    
    public void createNewProduct(View v) {
    	AlertDialog.Builder alert = new AlertDialog.Builder(this);

    	alert.setTitle("创建新产品");
    	// alert.setMessage("Message");

    	// Set an EditText view to get user input 
    	final EditText input = new EditText(this);
    	alert.setView(input);

    	alert.setPositiveButton("确定", new DialogInterface.OnClickListener() {
    		public void onClick(DialogInterface dialog, int whichButton) {
    			String value = input.getText().toString();
    			
    			// mySQLiteAdapter = new SQLiteAdapter(this);
    		    mySQLiteAdapter.openToWrite();
    		    mySQLiteAdapter.insert(value);
    		    mySQLiteAdapter.close();
    		    
    		    listData();
    		}
    	});

    	alert.setNegativeButton("取消", new DialogInterface.OnClickListener() {
    		public void onClick(DialogInterface dialog, int whichButton) {
    			
    		}
    	});

    	alert.show();

    }
    
    public void delProduct(View v) {
    	AlertDialog.Builder alert = new AlertDialog.Builder(this);

    	alert.setTitle("是否删除");

    	alert.setPositiveButton("确定", new DialogInterface.OnClickListener() {
    		public void onClick(DialogInterface dialog, int whichButton) {
 
    		    listData();
    		}
    	});

    	alert.setNegativeButton("取消", new DialogInterface.OnClickListener() {
    		public void onClick(DialogInterface dialog, int whichButton) {
    			
    		}
    	});

    	alert.show();
    }
    
    
    
    public void listData() {
    	/*
         *  Open the same SQLite database
         *  and read all it's content.
         */
        mySQLiteAdapter = new SQLiteAdapter(this);
        mySQLiteAdapter.openToRead();

        Cursor cursor = mySQLiteAdapter.queueAll();
        startManagingCursor(cursor);

        String[] from = new String[]{SQLiteAdapter.KEY_CONTENT};
        int[] to = new int[]{R.id.text};

        SimpleCursorAdapter cursorAdapter =
         new SimpleCursorAdapter(this, R.layout.row, cursor, from, to);
        
        ListView listContent = (ListView)this.findViewById(R.id.contentlist);
        listContent.setAdapter(cursorAdapter);
      
        mySQLiteAdapter.close();
    }
    
    
}
