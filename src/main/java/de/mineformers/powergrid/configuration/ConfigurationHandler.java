package de.mineformers.powergrid.configuration;

import java.io.File;
import java.util.logging.Level;

import net.minecraftforge.common.Configuration;
import cpw.mods.fml.common.FMLLog;
import de.mineformers.powergrid.PowerGrid;
import de.mineformers.powergrid.lib.BlockIds;
import de.mineformers.powergrid.lib.ItemIds;
import de.mineformers.powergrid.lib.Strings;

/**
 * Kybology
 * 
 * ConfigurationHandler
 * 
 * @author PaleoCrafter, Weneg
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public class ConfigurationHandler {

	public static Configuration configuration;

	public static void init(File configFile) {

		configuration = new Configuration(configFile);

		try {
			configuration.load();

			/* Block configs */
			BlockIds.CABLE = configuration.getBlock(Strings.CABLE_NAME,
					BlockIds.CABLE_DEFAULT).getInt(BlockIds.CABLE_DEFAULT);
			BlockIds.ENERGON = configuration.getBlock(Strings.ENERGON_NAME,
					BlockIds.ENERGON_DEFAULT).getInt(BlockIds.ENERGON_DEFAULT);

			BlockIds.IC2_INTERFACE = configuration.getBlock(
					Strings.IC2_INTERFACE_NAME, BlockIds.IC2_INTERFACE_DEFAULT)
					.getInt(BlockIds.IC2_INTERFACE_DEFAULT);

			/* Item configs */
			ItemIds.BUCKET_ENERGON = configuration
					.getItem(Strings.ENERGON_BUCKET_NAME,
							ItemIds.BUCKET_ENERGON_DEFAULT).getInt(
							ItemIds.BUCKET_ENERGON_DEFAULT);
		} catch (Exception e) {
			FMLLog.log(Level.SEVERE, e, PowerGrid.MOD_NAME
					+ " has had a problem loading its configuration");
		} finally {
			configuration.save();
		}
	}

	public static void set(String categoryName, String propertyName,
			String newValue) {

		configuration.load();
		if (configuration.getCategoryNames().contains(categoryName)) {
			if (configuration.getCategory(categoryName).containsKey(
					propertyName)) {
				configuration.getCategory(categoryName).get(propertyName)
						.set(newValue);
			}
		}
		configuration.save();
	}

}
