package net.otp.savingcard;

import java.util.ArrayList;

import net.otp.SecurityCard_S.R;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends Activity {
	final String DB_NAME = "scDb";
	public static final int REQUEST_CODE =1001;
	private static final int MAX_CARD_NUM = 4;
	String aa;
	ListView list;
	SQLiteDatabase db;
	ArrayList<String> cardinfo_arr = new ArrayList<String>();
	ArrayList<String> cardname = new ArrayList<String>();
	ArrayAdapter<String> adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
			
		Button addnew_btn = (Button) findViewById(R.id.addnew_btn);
		list = (ListView)findViewById(R.id.cardlist);
	
        db = openOrCreateDatabase(DB_NAME, MODE_WORLD_WRITEABLE, null);
	//	updateDb();
		
		


		addnew_btn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getBaseContext(),InputcodesActivity.class);
				startActivityForResult(intent,REQUEST_CODE);
			}
			
			
		});
	}
	

	protected void onActivityResult(int requestCode,int resultCode, Intent Data){
		super.onActivityResult(requestCode, resultCode, Data);
		if(resultCode==1){
			cardinfo_arr=(ArrayList<String>)Data.getExtras().getStringArrayList("card");
			cardname.add(cardinfo_arr.get(0));
			
			db.execSQL("CREATE TABLE " + cardinfo_arr.get(0) + "("
									+ " _id integer PRIMARY KEY AUTOINCREMENT, "
									+ " num_to_input TEXT,"
									+ " firstnum TEXT,"
									+ " secondnum TEXT);");
			for(int i=1;i<MAX_CARD_NUM ;i=i+3)
			{
				String setdata_db="INSERT INTO "+cardinfo_arr.get(0)+"(_id, num_to_input, firstnum, secondnum) VALUES (null,'"
			+cardinfo_arr.get(i)+"' ,'"+cardinfo_arr.get(i+1)+ "', '"+cardinfo_arr.get(i+2)+"');";
				db.execSQL(setdata_db);
			}
			adapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,cardname);
			list.setAdapter(adapter);
			//list.setOnItemClickListener(this);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	

}
