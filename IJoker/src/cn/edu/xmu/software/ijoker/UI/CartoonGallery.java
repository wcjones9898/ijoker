package cn.edu.xmu.software.ijoker.UI;



import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;
import cn.edu.xmu.software.ijoker.R;
import cn.edu.xmu.software.ijoker.engine.CartoonDownloader;
import cn.edu.xmu.software.ijoker.service.CartoonDownloadThread;
import cn.edu.xmu.software.ijoker.service.CartoonManageService;
import cn.edu.xmu.software.ijoker.util.Consts;


public class CartoonGallery extends Activity {
//	private int cartoonImages[] = { R.drawable.gallery1,
//			R.drawable.gallery2, R.drawable.gallery3,
//			R.drawable.gallery4 };
	private int cartoonIndex =0;
	private int cartoonNum;	
	private ImageButton left_btn, right_btn;
	private ImageView cartoon_img;
	private CartoonManageService cartoonManageService;
	private List<String> filesPath;
	private boolean nextIsReady = false;
	private boolean bufferIsReady = false;
	private int tempBufferIndex = 0;
	private List< byte[]> bufferList = new ArrayList< byte[]>();
	private byte[] buffer;
    private Integer port=5000;
	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
			case Consts.MSG_CARTOONLIST_READY:
				filesPath = msg.getData().getStringArrayList("data");
				if(filesPath!=null)
				{
					CartoonDownloadThread cdThread = new CartoonDownloadThread(handler,port++);
					cdThread.doStart(Consts.MEDIA_CENTER_BASE_URL+filesPath.get(cartoonIndex));
					cartoonIndex ++;
					CartoonDownloadThread cdThread1 = new CartoonDownloadThread(handler,port++);
					cdThread1.doStart(Consts.MEDIA_CENTER_BASE_URL+filesPath.get(cartoonIndex));
					find();
				}
				else
				{
					Toast.makeText(CartoonGallery.this, Consts.NETWORK_FAILED,
							Toast.LENGTH_SHORT).show();
					
				}
				Log.i("cartoonList", "filesPath: " + filesPath + "; size: "
						+ filesPath.size());
				break;
			case Consts.ERROR_CALLWEBSERVICE:
				Toast.makeText(CartoonGallery.this, Consts.NETWORK_FAILED,
						Toast.LENGTH_SHORT).show();
				break;
			case Consts.MSG_CARTOONPIC_DATA:
				if(bufferList.size()<2)
				bufferList.add(msg.getData().getByteArray("picData"));
				else
					bufferList.set(tempBufferIndex, msg.getData().getByteArray("picData"));
				buffer = bufferList.get(tempBufferIndex);
				tempBufferIndex = (1+tempBufferIndex)%2;
				nextIsReady = true;
				break;
			default:
			}
//			progressDialog.dismiss();

		}

	};
    @Override
	protected void onCreate(Bundle savedInstanceState) {
        // Be sure to call the super class.
        super.onCreate(savedInstanceState);
        WindowManager.LayoutParams lp=this.getWindow().getAttributes();
        lp.alpha=0.9f;
       
        this.getWindow().setAttributes(lp);
        cartoonManageService = new CartoonManageService(handler);
        setContentView(R.layout.cartoon_gallery);       
        cartoonManageService.getCartoonList(2);
       
    }
    
    void find(){
    	cartoonNum=filesPath.size();
    	//cartoonIndex = 0;
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
//			CartoonDownloadThread cdThread = new CartoonDownloadThread(handler,port++);
//			cdThread.doStart(Consts.MEDIA_CENTER_BASE_URL+filesPath.get(cartoonIndex));
			//byte[] imgData = CartoonDownloader.downLoad(Consts.MEDIA_CENTER_BASE_URL+filesPath.get(cartoonIndex));
			Toast.makeText(CartoonGallery.this, Consts.MEDIA_CENTER_BASE_URL+filesPath.get(cartoonIndex),
					Toast.LENGTH_SHORT).show();
			createImageView(buffer);
			CartoonDownloadThread cdThread = new CartoonDownloadThread(handler,port++);
			cdThread.doStart(Consts.MEDIA_CENTER_BASE_URL+filesPath.get(cartoonIndex));
			//Log.i("dowonload",String.valueOf(imgData));
			//createImageView(imgData);
			//cartoon_img.setImageResource(cartoonImages[cartoonIndex]);
		}
	};

	private ImageButton.OnClickListener rightCartoon = new ImageButton.OnClickListener() {
		@Override
		public void onClick(View v) {
			cartoonIndex++;
			if (cartoonIndex == cartoonNum)
				cartoonIndex = 0;
//			CartoonDownloadThread cdThread = new CartoonDownloadThread(handler,port++);
//			cdThread.doStart(Consts.MEDIA_CENTER_BASE_URL+filesPath.get(cartoonIndex));
		//	byte[] imgData = CartoonDownloader.downLoad(Consts.MEDIA_CENTER_BASE_URL+filesPath.get(cartoonIndex));
			createImageView(buffer);
			CartoonDownloadThread cdThread = new CartoonDownloadThread(handler,port++);
			cdThread.doStart(Consts.MEDIA_CENTER_BASE_URL+filesPath.get(cartoonIndex));
		}
	};
	private void createImageView(byte[] imageData)
	{
		Bitmap bitmap = BitmapFactory.decodeByteArray(imageData, 0,  
				imageData.length);  
		cartoon_img.setImageBitmap(bitmap);  
		cartoon_img.setVisibility(View.VISIBLE);  
	}
	
	private void getCartoonsPath(Integer cartoonId)
	{
		cartoonManageService.getCartoonList(cartoonId);
	}
	
}
