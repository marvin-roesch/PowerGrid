package de.mineformers.powergrid.integration.ic2.tileentity;

import ic2.api.energy.event.EnergyTileLoadEvent;
import ic2.api.energy.event.EnergyTileUnloadEvent;
import ic2.api.energy.tile.IEnergySink;
import ic2.api.energy.tile.IEnergySource;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.common.MinecraftForge;

/**
 * 
 * PowerGrid
 * 
 * TileEnergyInterface
 * 
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public class TileEnergyInterface extends TileEntity implements IEnergySource,
		IEnergySink {

	private boolean initialized;
	private double storedEnergy;

	@Override
	public void updateEntity() {
		if (!worldObj.isRemote)
			if (!initialized) {
				initialized = true;
				MinecraftForge.EVENT_BUS.post(new EnergyTileLoadEvent(this));
			}
	}

	@Override
	public void invalidate() {
		if (!worldObj.isRemote)
			MinecraftForge.EVENT_BUS.post(new EnergyTileUnloadEvent(this));
	}

	@Override
	public boolean emitsEnergyTo(TileEntity receiver, ForgeDirection direction) {
		return true;
	}

	@Override
	public double getOfferedEnergy() {
		return (storedEnergy > 0) ? 10 : 0;
	}

	@Override
	public void drawEnergy(double amount) {
		storedEnergy -= amount;
		if (storedEnergy < 0) {
			storedEnergy = 0;
		}
	}

	@Override
	public boolean acceptsEnergyFrom(TileEntity emitter,
			ForgeDirection direction) {
		return true;
	}

	@Override
	public double demandedEnergyUnits() {
		return 32;
	}

	@Override
	public double injectEnergyUnits(ForgeDirection directionFrom, double amount) {
		storedEnergy += amount;
		return 0;
	}

	@Override
	public int getMaxSafeInput() {
		return Integer.MAX_VALUE;
	}
}
