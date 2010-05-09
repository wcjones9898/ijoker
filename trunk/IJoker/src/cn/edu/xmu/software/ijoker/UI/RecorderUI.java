package cn.edu.xmu.software.ijoker.UI;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import cn.edu.xmu.software.ijoker.R;

public class RecorderUI extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.recorder);
		this
				.stopService(new Intent(
						"cn.edu.xmu.software.ijoker.REMOTE_SERVICE"));
	}

}
