package de.mineformers.powergrid.core.proxy;

import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.registry.GameRegistry;
import de.mineformers.powergrid.item.ModItems;
import de.mineformers.powergrid.tileentity.TileCable;

public class CommonProxy {

	public void registerTileEntities() {
		GameRegistry.registerTileEntity(TileCable.class,
				TileCable.class.getName());
	}

	public void registerRendering() {

	}

	public void registerEventHandlers() {
		MinecraftForge.EVENT_BUS.register(ModItems.energonBucket);
	}

}
