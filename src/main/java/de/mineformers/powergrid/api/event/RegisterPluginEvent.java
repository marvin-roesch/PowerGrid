package de.mineformers.powergrid.api.event;

import net.minecraftforge.event.Event;
import de.mineformers.powergrid.api.PowerGridPlugin;

/**
 * 
 * PowerGrid
 * 
 * RegisterPluginEvent
 * 
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public class RegisterPluginEvent extends Event {

	private PowerGridPlugin plugin;

	public RegisterPluginEvent(PowerGridPlugin plugin) {
		this.plugin = plugin;
	}

	public PowerGridPlugin getPlugin() {
		return plugin;
	}

}
