package cn.edu.xmu.software.ijoker.UI;



import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import cn.edu.xmu.software.ijoker.R;


public class CartoonGallery extends Activity {
	private int cartoonImages[] = { R.drawable.gallery1,
			R.drawable.gallery2, R.drawable.gallery3,
			R.drawable.gallery4 };
	private int cartoonIndex;
	private int cartoonNum;	
	private ImageButton left_btn, right_btn;
	private ImageView cartoon_img;
    @Override
	protected void onCreate(Bundle savedInstanceState) {
        // Be sure to call the super class.
        super.onCreate(savedInstanceState);
        WindowManager.LayoutParams lp=this.getWindow().getAttributes();
        lp.alpha=0.9f;
        this.getWindow().setAttributes(lp);
        
        setContentView(R.layout.cartoon_gallery);        
        find();
    }
    
    void find(){
    	cartoonNum=4;
    	cartoonIndex = 0;
    	left_btn = (ImageButton) findViewById(R.id.left_btn);
		right_btn = (ImageButton) findViewById(R.id.right_btn);
		left_btn.setOnClickListener(leftCartoon);
		right_btn.setOnClickListener(rightCartoon);
		cartoon_img = (ImageView) findViewById(R.id.cartoon_img);
    }
    
    private ImageButton.OnClickListener leftCartoon = new ImageButton.OnClickListener() {
		@Override
		public void onClick(View v) {

			cartoonIndex--;
			if (cartoonIndex < 0)
				cartoonIndex = cartoonNum - 1;
			
			cartoon_img.setImageResource(cartoonImages[cartoonIndex]);
		}
	};

	private ImageButton.OnClickListener rightCartoon = new ImageButton.OnClickListener() {
		@Override
		public void onClick(View v) {
			cartoonIndex++;
			if (cartoonIndex == cartoonNum)
				cartoonIndex = 0;
			
			cartoon_img.setImageResource(cartoonImages[cartoonIndex]);
		}
	};
}
