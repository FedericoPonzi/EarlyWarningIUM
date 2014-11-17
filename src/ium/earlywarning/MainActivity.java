package ium.earlywarning;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ToggleButton;


public class MainActivity extends ActionBarActivity
{
	@SuppressWarnings("unused")
	private static String LOG_TAG = MainActivity.class.getName();

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Button alChiusoBtn = (Button) findViewById(R.id.button_chiuso);
		alChiusoBtn.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				startActivity(new Intent(MainActivity.this, SecondActivity.class));
			}
		});
		Button alApertoBtn = (Button) findViewById(R.id.button_aperto);
		alApertoBtn.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				Intent intent = new Intent(MainActivity.this, SecondActivity.class);
				intent.putExtra(SecondActivity.APERTO, true);
				startActivity(intent);
			}
		});

		
	}


}
