package game;

import Test.game.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class play extends Activity {

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		Button newGame = (Button) findViewById(R.id.NewGame);
		newGame.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				Intent myIntent = new Intent(view.getContext(), 
						ngActivity.class);	
				startActivityForResult(myIntent, 0);
			}
		});
		
		Button loadGame = (Button) findViewById(R.id.loadGame);
		loadGame.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				Intent myIntent = new Intent(view.getContext(), 
						lgActivity.class);	
				startActivityForResult(myIntent, 0);
			}
		});
		
		Button options = (Button) findViewById(R.id.options);
		options.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				Intent myIntent = new Intent(view.getContext(),
						optActivity.class);
				startActivityForResult(myIntent, 0);
			}
		});
	}
}