package cn.edu.xmu.software.ijoke.dispose;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Test;

public class WAVDispose {

    private static byte[]  RIFF = new byte[4]; 
    private static byte[] RIFF_SIZE = new byte[4]; 
    private static byte[] RIFF_TYPE = new byte[4];  
    
    private static byte[]  FMT = new byte[4];
    private static byte[]  FMT_SIZE = new byte[4];
    private static byte[]  FMT_FormatTag = new byte [2]; 
    private static byte[]  FMT_Channels = new byte [2]; //声道数目
    private static byte[]  FMT_SamplesPerSec = new byte [4]; //采样频率 
    private static byte[]  FMT_AvgBytesPerSec = new byte [4];//每秒所需字节数 
    private static byte[]  FMT_BlockAlign = new byte [2];//每个采样需要的bit数 
    private static byte[]  FMT_BitsPerSample = new byte [2];
    private static byte[]  FMT_ADD = new byte[2];

	/**
	 * @param args
	 * @throws IOException 
	 */
    static int caculateTime(int size,int AvgBytesPerSec)
    {
    	return size/AvgBytesPerSec;
    }


    static int bytes4ToInt(byte[] b) throws IOException
    {
       
        byte bLoop;
        int size = 0;
        for ( int i =0; i<4 ; i++) {
            bLoop = b[i];
            size+= (bLoop & 0xFF) << (8 * i);
          
        }
    	
    	return size;
    }
    public static int[] getFileResults(String filePath) throws IOException
    {
        File file= new File(filePath);
        FileInputStream fis = new FileInputStream(file);
		fis.read(RIFF,0,4);
		fis.read(FMT_SIZE,0,4);
		int size = bytes4ToInt(FMT_SIZE);
		System.out.println(size);
		fis.read(new byte[20],0,20);
		fis.read(FMT_AvgBytesPerSec,0,4);
		int avgBytesPerSec = bytes4ToInt(FMT_AvgBytesPerSec);
		int time = 0;
		time=caculateTime(size,avgBytesPerSec);
		fis.close();
		int[] results = new int[2];
		results[0] = size;
		results[1] = time;
		System.out.println(time);
		return results;
    }
    @Test
    public void testGetFileTime() throws IOException
    {
    	System.out.println(getFileResults("D:/test.wav"));
    }

}
