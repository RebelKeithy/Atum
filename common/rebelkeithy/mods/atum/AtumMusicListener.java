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
        
        String path = "/mods/Atum/music/";
        musics.add(event.manager.soundPoolSounds.addSound("Atum/ALongJourney.ogg", Atum.class.getResource(path + "ALongJourney.ogg")));
        musics.add(event.manager.soundPoolSounds.addSound("Atum/Hostiles.ogg", Atum.class.getResource(path + "Hostiles.ogg")));
        musics.add(event.manager.soundPoolSounds.addSound("Atum/ScorchingSand.ogg", Atum.class.getResource(path + "ScorchingSand.ogg")));
        musics.add(event.manager.soundPoolSounds.addSound("Atum/TempleTales.ogg", Atum.class.getResource(path + "TempleTales.ogg")));
        musics.add(event.manager.soundPoolSounds.addSound("Atum/TheWanderer.ogg", Atum.class.getResource(path + "TheWanderer.ogg")));
        

        path = "/mods/Atum/sounds/";
        //file = new File(Minecraft.getMinecraft().mcDataDir, Atum.class.getResource("resources/Atum/sounds/pharaohspawn.ogg"));
        //System.out.println(file.exists() + " Does File Exist " + file.getAbsolutePath());
        event.manager.soundPoolSounds.addSound("Atum/pharaohspawn.ogg", Atum.class.getResource(path + "pharaohspawn.ogg"));
    }

    @ForgeSubscribe
    public void onBackgroundMusic(PlayBackgroundMusicEvent event)
    {
        if(Minecraft.getMinecraft().thePlayer.worldObj.provider.dimensionId == AtumConfig.dimensionID)
            event.result = musics.get((int) (Math.random() * musics.size()));
    }
}
