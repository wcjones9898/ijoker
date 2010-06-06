package cn.edu.xmu.software.ijoker.UI;



import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.ProgressDialog;
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
	private int cartoonImages[] = { R.drawable.gallery1,
			R.drawable.gallery2, R.drawable.gallery3,
			R.drawable.gallery4 };
	private int cartoonIndex =0;
	private int cartoonNum;	
	private ImageButton left_btn, right_btn;
	private ImageView cartoon_img;
	private CartoonManageService cartoonManageService;
	private List<String> filesPath;
	private boolean isRightBufferReady = false;
	private boolean isLeftBufferReady = false;
	private boolean isBufferReady = false;
	private int tempBufferIndex = 0;
	private byte[]bufferLeft ;
	private byte[]buffer ;
	private byte[]bufferRight;
	private List< byte[]> bufferList = new ArrayList< byte[]>();
	private ProgressDialog progressDialog;
    private Integer port=5000;
	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
		
			switch (msg.what) {
			
			case Consts.MSG_CARTOONLIST_READY:
				filesPath = msg.getData().getStringArrayList("data");
				cartoonNum=filesPath.size();
				if(filesPath!=null)
				{
					progressDialog = ProgressDialog.show(CartoonGallery.this, "提示",
							"正在获取漫画，请稍候...", true);
					//buffer=CartoonDownloader.downLoad(Consts.MEDIA_CENTER_BASE_URL+filesPath.get(cartoonIndex++),port);
					if(filesPath.size()>1)
					{
					CartoonDownloadThread cdThread1 = new CartoonDownloadThread(handler,port++,1);
					cdThread1.doStart(Consts.MEDIA_CENTER_BASE_URL+filesPath.get(1));
					}
	//				progressDialog.dismiss();
					if(filesPath.size()>0)
					{
						//bufferLeft=CartoonDownloader.downLoad(Consts.MEDIA_CENTER_BASE_URL+filesPath.get(cartoonIndex++),port);
						
						CartoonDownloadThread cdThread = new CartoonDownloadThread(handler,port++,0);
						cdThread.doStart(Consts.MEDIA_CENTER_BASE_URL+filesPath.get(0));
					}
					if(filesPath.size()>2)
					{
					//	bufferRight=CartoonDownloader.downLoad(Consts.MEDIA_CENTER_BASE_URL+filesPath.get(cartoonIndex++),port);
					    CartoonDownloadThread cdThread2 = new CartoonDownloadThread(handler,port++,2);
					    cdThread2.doStart(Consts.MEDIA_CENTER_BASE_URL+filesPath.get(2));
					}
					progressDialog.dismiss();
					
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
				int bufferType = msg.getData().getInt("bufferType");
				switch(bufferType)
				{
				case 0:
					isLeftBufferReady = true;
					bufferLeft = msg.getData().getByteArray("picData");
					Toast.makeText(CartoonGallery.this, "向左缓冲完毕",
							Toast.LENGTH_SHORT).show();
					break;
				case 1:
					isBufferReady = true;
					buffer = msg.getData().getByteArray("picData");
                    break;
				case 2:
					isRightBufferReady = true;
					bufferRight = msg.getData().getByteArray("picData");
					Toast.makeText(CartoonGallery.this, "向右缓冲完毕",
							Toast.LENGTH_SHORT).show();
					break;
				}
				break;
			case Consts.CARTOONGALLERY_UPDATEUI:
				int operation = msg.getData().getInt("operation");
				switch(operation)
				{
				case 0:			
					leftOperation();
					break;
				case 1:		
					initOperation();
					break;
				case 2:
					rightOperation();
					break;
				}
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
		
        find();
    }
    
    void find(){
    	//cartoonIndex = 0;
    	left_btn = (ImageButton) findViewById(R.id.left_btn);
		right_btn = (ImageButton) findViewById(R.id.right_btn);
		left_btn.setOnClickListener(leftCartoon);
		right_btn.setOnClickListener(rightCartoon);
		cartoon_img = (ImageView) findViewById(R.id.cartoon_img);
//		progressDialog = ProgressDialog.show(CartoonGallery.this, "提示",
//				"正在获取漫画，请稍候...", true);

    }
    private void initOperation()
    {

    	createImageView(buffer);
    }
    private void leftOperation()
    {
    	if(bufferLeft==null)
    	{
    		Toast.makeText(CartoonGallery.this, Consts.NETWORK_FAILED,
					Toast.LENGTH_SHORT).show();
    		return;
    	}
    	cartoonIndex--;
		if (cartoonIndex < 0)
			cartoonIndex = cartoonNum - 1;	
		bufferRight = buffer;
		buffer = bufferLeft;
		createImageView(buffer);
		isLeftBufferReady = false;
		CartoonDownloadThread cdThread = new CartoonDownloadThread(handler,port++,0);
		cdThread.doStart(Consts.MEDIA_CENTER_BASE_URL+filesPath.get(cartoonIndex));
    }
    private void rightOperation()
    {
    	if(bufferRight==null)
    	{
    		Toast.makeText(CartoonGallery.this, Consts.NETWORK_FAILED,
					Toast.LENGTH_SHORT).show();
    		return;
    	}
    	cartoonIndex++;
		if (cartoonIndex == cartoonNum)
			cartoonIndex = 0;
		bufferLeft = buffer;
		buffer = bufferRight;
		createImageView(buffer);
		isRightBufferReady =false;
		CartoonDownloadThread cdThread = new CartoonDownloadThread(handler,port++,2);
		cdThread.doStart(Consts.MEDIA_CENTER_BASE_URL+filesPath.get(cartoonIndex));
    }
    public void updateUI(int operation)
    {
    	Message message = handler.obtainMessage(Consts.CARTOONGALLERY_UPDATEUI);
    	Bundle b = new Bundle();
    	b.putInt("operation", operation);
    	message.setData(b);
    	handler.sendMessage(message);
    }

    private ImageButton.OnClickListener leftCartoon = new ImageButton.OnClickListener() {
		@Override
		public void onClick(View v) {

			    
			updateUI(0);

		}
	};

	private ImageButton.OnClickListener rightCartoon = new ImageButton.OnClickListener() {
		@Override
		public void onClick(View v) {
			

			updateUI(2);
		}
	};
	private void createImageView(byte[] imageData)
	{
		if(imageData==null)
			return;
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
