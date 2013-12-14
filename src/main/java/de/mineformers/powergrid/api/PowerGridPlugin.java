package de.mineformers.powergrid.api;

/**
 * 
 * PowerGrid
 * 
 * PowerGridPlugin
 * 
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public abstract class PowerGridPlugin {

	public abstract String getId();

	public abstract Class<? extends IEnergyHandler> getEnergyHandler();

	public abstract double getConversionRate();

	public void onPreInit() {

	}

	public void onInit() {

	}

	public void onPostInit() {

	}

}
