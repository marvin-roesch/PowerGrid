package de.mineformers.powergrid.plugin;

import java.util.Collection;
import java.util.HashMap;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.ForgeSubscribe;
import cpw.mods.fml.common.Loader;
import de.mineformers.powergrid.api.PowerGridPlugin;
import de.mineformers.powergrid.api.event.RegisterPluginEvent;

/**
 * 
 * PowerGrid
 * 
 * PluginHandler
 * 
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public class PluginHandler {

	private static PluginHandler instance;
	private HashMap<String, PowerGridPlugin> plugins;

	public PluginHandler() {
		plugins = new HashMap<String, PowerGridPlugin>();
	}

	public static PluginHandler instance() {
		if (instance == null)
			instance = new PluginHandler();
		return instance;
	}

	public static PowerGridPlugin getPlugin(String id) {
		return instance().plugins.get(id);
	}

	@ForgeSubscribe
	public void onPluginRegistered(RegisterPluginEvent event) {
		this.plugins.put(event.getPlugin().getId(), event.getPlugin());
	}

	public static void registerDefaults() {
		if (Loader.isModLoaded("IC2")) {
			MinecraftForge.EVENT_BUS.post(new RegisterPluginEvent(
					new IC2Plugin()));
		}
	}

	public Collection<PowerGridPlugin> iterator() {
		return plugins.values();
	}

	public static Collection<PowerGridPlugin> plugins() {
		return instance().iterator();
	}

}
