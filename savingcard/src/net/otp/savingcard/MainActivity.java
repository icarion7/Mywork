package net.otp.savingcard;

import java.util.ArrayList;

import net.otp.SecurityCard_S.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends Activity {
	public static final int REQUEST_CODE =1001;
	ListView list;
	ArrayList<Integer> cardinfo_arr = new ArrayList<Integer>();
	ArrayList<String> cardname = new ArrayList<String>();
	ArrayAdapter<String> adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
			
		Button addnew_btn = (Button) findViewById(R.id.addnew_btn);
		list = (ListView)findViewById(R.id.cardlist);
	
		
		


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
			cardname.add((String)Data.getExtras().getString("name"));
			cardinfo_arr=(ArrayList<Integer>)Data.getExtras().getIntegerArrayList("card");
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
