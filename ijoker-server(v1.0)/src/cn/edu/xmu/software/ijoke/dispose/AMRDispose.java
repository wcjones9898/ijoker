package cn.edu.xmu.software.ijoke.dispose;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.junit.Test;

public class AMRDispose {

    static int caculateTime(int size,int AvgBytesPerSec)
    {
    	return size/AvgBytesPerSec;
    }
    public static int[] getFileResults(String filePath) throws IOException
    {
        File file= new File(filePath);
        FileInputStream fis = new FileInputStream(file);
        byte b[] = new byte[1];
        int time = 0;
        while(fis.read(b,0,1)!=-1)
        {
        	if(b[0]==60)
        		time++;
        }
        int[] results = new int[2];
        results[0] = (int) file.length();
        System.out.println(time);
        results[1] = (int) (time*20/1000);
        return results;
    }
    @Test
    public void testGetFileResults() throws IOException
    {
    	int[] results = getFileResults("d:/record6.amr");
    	System.out.println(results[0]);
    	System.out.println(results[1]);
    }
}
