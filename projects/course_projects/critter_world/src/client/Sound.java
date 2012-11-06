package client;

import java.io.FileInputStream;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import javax.sound.sampled.*;

public class Sound {
	
	FloatControl volume;


	public static void playAmbient() {

		try {

			Clip clip = AudioSystem.getClip();
			AudioInputStream stream = AudioSystem.getAudioInputStream(new FileInputStream("sounds/ambient.wav"));
			
			clip.open(stream);
			clip.loop(Integer.MAX_VALUE);
			
		} catch (Exception e) {
			System.out.println("Error Playing Sound");
			e.printStackTrace();

		}
	}
	
	public static void clickSound() {

		try {

			Clip clip = AudioSystem.getClip();
			AudioInputStream stream = AudioSystem.getAudioInputStream(new FileInputStream("sounds/click.wav"));
			
			clip.open(stream);
			clip.loop(1);
			
		} catch (Exception e) {
			System.out.println("Error Playing Sound");
			e.printStackTrace();

		}
	}

}
