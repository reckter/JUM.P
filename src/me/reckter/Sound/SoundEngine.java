package me.reckter.Sound;

import org.newdawn.slick.Sound;

import java.util.HashMap;

/**
 * Created by mediacenter on 16.01.14.
 */
public class SoundEngine {

    protected HashMap<String, Sound> sounds;

    public SoundEngine() {
        sounds = new HashMap<String, Sound>();
    }

    public boolean cointains(String name){
        return sounds.containsKey(name);
    }

    public boolean cointains(Sound sound){
        return sounds.containsValue(sound);
    }

    public void addSound(String name){

        /*
        String file = "res/Sounds/" + name + ".wav";
        if(!sounds.containsKey(name)){
            try {
                Log.note("loading: " + file);
                sounds.put(name, new Sound(file));
            } catch (SlickException e) {
                Log.error("couldn't load the Sound '" + file + "'");
            }
        }
        */
    }

    public Sound getSound(String name){
        return sounds.get(name);
    }

    public void playSound(String name){
        playSound(name, 1, 1);
    }

    public void playSound(String name, float pitch, float volume){
        sounds.get(name).play(pitch, volume);
    }

    public void playSoundAt(String name, float x, float y){
        playSoundAt(name, x, x, 1, 1);
    }


    public void playSoundAt(String name, float x, float y, float pitch, float volume){
        sounds.get(name).playAt(1, 1, x, y, 0);
    }


    public void loopSound(String name){
        loopSound(name, 1, 1);
    }

    public void loopSound(String name, float pitch, float volume){
        sounds.get(name).loop(pitch, volume);
    }
}
