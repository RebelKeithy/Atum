package rebelkeithy.mods.atum;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.SoundPoolEntry;
import net.minecraftforge.client.event.sound.PlayBackgroundMusicEvent;
import net.minecraftforge.client.event.sound.SoundLoadEvent;
import net.minecraftforge.event.ForgeSubscribe;

public class AtumMusicListener
{
    List<SoundPoolEntry> musics;
    //SoundPoolEntry music;
    
    @ForgeSubscribe
    public void onSoundLoadEvent(SoundLoadEvent event) 
    {
        musics = new ArrayList<SoundPoolEntry>();
        
        String path = "C:/Users/Keithy/Documents/Atum 1.5.1/source/resources/mods/Atum/music/";
        File file = new File(path + "ALongJourney.ogg");
        musics.add(event.manager.soundPoolSounds.addSound("Atum/ALongJourney.ogg", file));
        file = new File(path + "Hostiles.ogg");
        musics.add(event.manager.soundPoolSounds.addSound("Atum/Hostiles.ogg", file));
        file = new File(path + "ScorchingSand.ogg");
        musics.add(event.manager.soundPoolSounds.addSound("Atum/ScorchingSand.ogg", file));
        file = new File(path + "TempleTales.ogg");
        musics.add(event.manager.soundPoolSounds.addSound("Atum/TempleTales.ogg", file));
        file = new File(path + "TheWanderer.ogg");
        musics.add(event.manager.soundPoolSounds.addSound("Atum/TheWanderer.ogg", file));
        

        path = "C:/Users/Keithy/Documents/Atum 1.5.1/source/resources/mods/Atum/sounds/";
        file = new File(path + "pharaohspawn.ogg");
        event.manager.soundPoolSounds.addSound("Atum/pharaohspawn.ogg", file);
    }

    @ForgeSubscribe
    public void onBackgroundMusic(PlayBackgroundMusicEvent event)
    {
        if(Minecraft.getMinecraft().thePlayer.worldObj.provider.dimensionId == AtumConfig.dimensionID)
            event.result = musics.get((int) (Math.random() * musics.size()));
    }
}
