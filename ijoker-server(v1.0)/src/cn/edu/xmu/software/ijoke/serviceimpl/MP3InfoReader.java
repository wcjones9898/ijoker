package cn.edu.xmu.software.ijoke.serviceimpl;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;

import org.junit.Test;
public class MP3InfoReader {

	public int getMP3TotalTime(String filePath) throws UnsupportedAudioFileException, IOException
	{
		File file = new File(filePath);
		AudioFileFormat aff = AudioSystem.getAudioFileFormat(file);
		Long duration = (Long) aff.getProperty("duration");
		int total = (int) (duration.longValue()/1000);
         System.out.println(total);

		return total;
	}
	@Test
	public void testGetMP3TotalTime()
	{
		try {
			getMP3TotalTime("D:/Kalimba.mp3");
		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
