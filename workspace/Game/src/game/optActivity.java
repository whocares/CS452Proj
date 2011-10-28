package game;

import Test.game.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;

public class optActivity extends Activity {

	private Game mainGame;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.options);
		
		String[] difficulty = new String[] {"Hard", "Medium", "Easy"};

		Spinner diffSpin = (Spinner) findViewById(R.id.diffSpin);
		ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item,difficulty);
		adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		diffSpin.setAdapter(adapter1);
		diffSpin.setOnItemSelectedListener(new OnItemSelectedListener() {
		    public void onItemSelected(AdapterView<?> parentView, View selectedItemView,
		    		int position, long id) {
		        mainGame.setDifficulty(selectedItemView.toString());
		    }

		    public void onNothingSelected(AdapterView<?> parentView) {
		        mainGame.setDifficulty("Medium");
		    }
		});	
	}
}