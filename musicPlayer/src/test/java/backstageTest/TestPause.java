package backstageTest;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class TestPause {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		BufferedInputStream buffer;
		try {
			buffer = new BufferedInputStream(new FileInputStream("/home/sky/java-course/java-projects/exercises/musicplayer/musicPlayer/data/musics/S&M.mp3"));
			Player player=new Player(buffer);
			Thread thread=new Thread() {
				public void run() {
					
					try {
						player.play();
					} catch (JavaLayerException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			};
			
			
			thread.start();
			Scanner scanner=new Scanner(System.in);
			System.out.println("call wait!");
			scanner.nextLine();
			thread.wait();
			System.out.println("call notify");
			scanner.nextLine();
			thread.notifyAll();
		
			
			
		} catch (JavaLayerException | FileNotFoundException | InterruptedException e) {
			e.printStackTrace();
		}

	}

}
