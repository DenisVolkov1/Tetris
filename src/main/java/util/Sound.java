package util;

import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Sound {
   private static boolean isSoundON = true;
   private static Sound sound;
   private static AudioClip landed = new AudioClip(Sound.class.getResource("/landed.mp3").toExternalForm());
   private static AudioClip shiftAndRotation = new AudioClip(Sound.class.getResource("/shift.mp3").toExternalForm());
   private static AudioClip deleteRow = new AudioClip(Sound.class.getResource("/deleteRow.mp3").toExternalForm());
   private static AudioClip loseGame = new AudioClip(Sound.class.getResource("/loseGame.mp3").toExternalForm());
   private static AudioClip buttonClick = new AudioClip(Sound.class.getResource("/buttonClick.mp3").toExternalForm());
   private static AudioClip buttonHover = new AudioClip(Sound.class.getResource("/buttonHover.mp3").toExternalForm());
   private static Media media = new Media(Sound.class.getResource("/fon.mp3").toExternalForm());
   private static MediaPlayer mediaPlayer = new MediaPlayer(media);

   static {
       mediaPlayer.setAutoPlay(true);
      mediaPlayer.setVolume(0.07d);
      mediaPlayer.setCycleCount(1_000_000);
   }
   private Sound() {}
   public static Sound getInstance() {
       if (sound != null) {
           return sound;
       } else {
           sound = new Sound();
           return sound;
       }
   }
    public void landed() {
       if (isSoundON)
        landed.play();
    }
    public void shiftOrRotation() {
        if (isSoundON)
        shiftAndRotation.play();
    }
    public void deleteRow() {
        if (isSoundON)
        deleteRow.play();
    }
    public void loseGame() {
        if (isSoundON)
        loseGame.play();
    }
    public void buttonHover() {
        if (isSoundON)
        buttonHover.play();
    }
    public void buttonClick() {
        if (isSoundON)
        buttonClick.play();
    }
    public void soundSwitch() {
        isSoundON = !isSoundON;
    }
    public void musicSwitch() {
        if (mediaPlayer.isMute()) mediaPlayer.setMute(false);
        else mediaPlayer.setMute(true);
    }
}
