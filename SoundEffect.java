import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

public class SoundEffect {

	public static void play(String sf) throws Exception{
		File soundFile = new File(sf);
		if (soundFile.exists()) {
			InputStream in = new FileInputStream(sf);
			AudioStream as = new AudioStream(in);
			AudioPlayer.player.start(as);
		}
	}
}
