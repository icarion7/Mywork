package net.otp.savingcard;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import net.otp.SecurityCard_S.R;

public class InputcodesActivity extends Activity {
	
	private static final int MAX_CARD_NUM = 4;
	private static final int VISIBLE = 0;
	private static final int INVISIVLE = 4;
	private static final int GONE = 8;
	private static final int NONE = 1;
	private static final int NUMBER = 2;
	
	CharSequence text = "완료!";
	int duration = Toast.LENGTH_SHORT;
	int firstnum;
	int secondnum;
	String cardname;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_inputcodes);

		final ArrayList<Integer> cardinfo_arr = new ArrayList<Integer>();
		final TextView num_to_input = (TextView) findViewById(R.id.num_to_Input);
		final TextView introduce = (TextView) findViewById(R.id.introduce);
		final TextView infoItem = (TextView) findViewById(R.id.infoItem);
		Button submit_btn = (Button) findViewById(R.id.submit_btn);
		Button cancel_btn = (Button) findViewById(R.id.cancel_btn);
		final EditText firstText = (EditText) findViewById(R.id.firstText);
		final EditText secondText = (EditText) findViewById(R.id.secondText);
		firstText.setFilters(new InputFilter[] { new InputFilter.LengthFilter(10) });
		secondText.setFilters(new InputFilter[] { new InputFilter.LengthFilter(2) });

		submit_btn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				if (secondText.getVisibility() == GONE) {
					cardname = firstText.getText().toString();
					introduce.setText("카드의 해당 번호를 입력하세요");
					infoItem.setText("각각 두자리씩 입력하세요");
					firstText.setText(null);
					num_to_input.setVisibility(VISIBLE);
					secondText.setVisibility(VISIBLE);
					firstText.setFilters(new InputFilter[] { new InputFilter.LengthFilter(2) });
					firstText.setInputType(NUMBER);
					return;
				}
				int inputnum = Integer.parseInt((String) num_to_input.getText());
				try {
					firstnum = Integer.parseInt(firstText.getText().toString());
					secondnum = Integer.parseInt(secondText.getText()
							.toString());
				} catch (Exception e) {
					firstnum = 1;
					secondnum = 1;
				}
				cardinfo_arr.add(inputnum);
				cardinfo_arr.add(firstnum);
				cardinfo_arr.add(secondnum);
				firstText.setText(null);
				secondText.setText(null);

				if (inputnum > MAX_CARD_NUM) {
					Context context = getApplicationContext();
					Intent resultIntent = new Intent();
					resultIntent.putExtra("card", cardinfo_arr);
					resultIntent.putExtra("name", cardname);
					setResult(1, resultIntent);
					Toast toast = Toast.makeText(context, text, duration);
					toast.show();
					InputcodesActivity.this.finish();
				} else {
					inputnum++;

					num_to_input.setText(Integer.toString(inputnum));
				}
			}

		});

		cancel_btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Context context = getApplicationContext();
				CharSequence text = "취소!";
				int duration = Toast.LENGTH_SHORT;
				Toast toast = Toast.makeText(context, text, duration);
				toast.show();
				finish();

			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
