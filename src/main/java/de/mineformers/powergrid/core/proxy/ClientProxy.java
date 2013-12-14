package de.mineformers.powergrid.core.proxy;

import cpw.mods.fml.client.registry.ClientRegistry;
import de.mineformers.powergrid.client.renderer.tileentity.TileCableRenderer;
import de.mineformers.powergrid.tileentity.TileCable;

public class ClientProxy extends CommonProxy {

	@Override
	public void registerRendering() {
		super.registerRendering();

		ClientRegistry.bindTileEntitySpecialRenderer(TileCable.class,
				new TileCableRenderer());
	}

}
