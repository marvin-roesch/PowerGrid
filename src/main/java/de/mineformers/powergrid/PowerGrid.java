package de.mineformers.powergrid;

import java.io.File;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkMod;
import de.mineformers.powergrid.api.PowerGridPlugin;
import de.mineformers.powergrid.block.ModBlocks;
import de.mineformers.powergrid.configuration.ConfigurationHandler;
import de.mineformers.powergrid.core.CreativeTabPowerGrid;
import de.mineformers.powergrid.core.proxy.CommonProxy;
import de.mineformers.powergrid.fluid.ModFluids;
import de.mineformers.powergrid.item.ModItems;
import de.mineformers.powergrid.lib.Version;
import de.mineformers.powergrid.network.ModPackets;
import de.mineformers.powergrid.network.PacketHandler;
import de.mineformers.powergrid.plugin.PluginHandler;

/**
 * 
 * PowerGrid
 * 
 * PowerGrid
 * 
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
@Mod(modid = PowerGrid.MOD_ID,
		name = PowerGrid.MOD_NAME,
		version = Version.VERSION,
		dependencies = PowerGrid.DEPENDENCIES,
		certificateFingerprint = PowerGrid.FINGERPRINT)
@NetworkMod(channels = { PowerGrid.CHANNEL_NAME },
		clientSideRequired = true,
		serverSideRequired = false,
		packetHandler = PacketHandler.class)
public class PowerGrid {

	public static final String MOD_ID = "PowerGrid";
	public static final String MOD_NAME = "PowerGrid";
	public static final String CHANNEL_NAME = MOD_ID;
	public static final String FINGERPRINT = "";
	public static final String RESOURCE_PATH = "core" + File.separator;
	public static final String DEPENDENCIES = "required-after:Forge@[9.11.1.964,);after:IC2";
	public static final String SERVER_PROXY_CLASS = "de.mineformers.powergrid.core.proxy.CommonProxy";
	public static final String CLIENT_PROXY_CLASS = "de.mineformers.powergrid.core.proxy.ClientProxy";

	@Instance(PowerGrid.MOD_ID)
	public static PowerGrid instance;

	@SidedProxy(clientSide = PowerGrid.CLIENT_PROXY_CLASS,
			serverSide = PowerGrid.SERVER_PROXY_CLASS)
	public static CommonProxy proxy;

	public static CreativeTabs creativeTab = new CreativeTabPowerGrid(
			CreativeTabs.getNextID(), PowerGrid.MOD_ID);

	@EventHandler
	public void serverStarting(FMLServerStartingEvent event) {
	}

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		ModPackets.init();
		MinecraftForge.EVENT_BUS.register(PluginHandler.instance());
		PluginHandler.registerDefaults();
		ConfigurationHandler.init(new File(event.getModConfigurationDirectory()
				.getAbsolutePath()
				+ File.separator
				+ PowerGrid.CHANNEL_NAME
				+ File.separator + PowerGrid.MOD_ID + ".cfg"));

		ModFluids.init();
		ModBlocks.init();
		ModItems.init();

		proxy.registerTileEntities();

		for (PowerGridPlugin plugin : PluginHandler.plugins()) {
			plugin.onPreInit();
		}
	}

	@EventHandler
	public void load(FMLInitializationEvent event) {
		proxy.registerRendering();
		proxy.registerEventHandlers();

		for (PowerGridPlugin plugin : PluginHandler.plugins()) {
			plugin.onInit();
		}
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		for (PowerGridPlugin plugin : PluginHandler.plugins()) {
			plugin.onPostInit();
		}
	}
}
