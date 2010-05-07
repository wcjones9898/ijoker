package cn.edu.xmu.software.ijoker.UI;

import android.app.Activity;
import android.os.Bundle;
import cn.edu.xmu.software.ijoker.R;

import com.admob.android.ads.AdListener;
import com.admob.android.ads.AdView;

public class About extends Activity implements AdListener {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.about);
	}

	@Override
	public void onFailedToReceiveAd(AdView arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onFailedToReceiveRefreshedAd(AdView arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onReceiveAd(AdView arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onReceiveRefreshedAd(AdView arg0) {
		// TODO Auto-generated method stub

	}

}
