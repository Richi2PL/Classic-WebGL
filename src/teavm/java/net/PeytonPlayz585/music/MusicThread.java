package net.PeytonPlayz585.music;

import java.util.Random;

import org.lwjgl.opengl.GL11;

public class MusicThread {
	
	static int currentSong = 0;
	static int lastSongPlayed = 0;
	static boolean justfinishedPlayingSong = false;
	
	public static void musicTick() {
		if(!GL11.isPlaying(currentSong)) {
			Random rand = new Random();
			int randomNum = rand.nextInt((3 - 1) + 1) + 1;
			if(randomNum == lastSongPlayed) {
				randomNum = rand.nextInt((3 - 1) + 1) + 1;
			}
			lastSongPlayed = randomNum;
		
			if(randomNum == 1) {
				currentSong = GL11.beginPlayback("/music/calm1.mp3", 1.0f, 1.0f, 1.0f, 1.0f, 1.0f);
			} else if(randomNum == 2) {
				currentSong = GL11.beginPlayback("/music/calm2.mp3", 1.0f, 1.0f, 1.0f, 1.0f, 1.0f);
			} else if(randomNum == 3) {
				currentSong = GL11.beginPlayback("/music/calm3.mp3", 1.0f, 1.0f, 1.0f, 1.0f, 1.0f);
			} else {
				throw new IndexOutOfBoundsException("Tried to play a sound that does not exist! :(");
			}
			justfinishedPlayingSong = true;
		}
	}
}
