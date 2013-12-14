package de.mineformers.powergrid.api.util;

import net.minecraft.util.ResourceLocation;

/**
 * Kybology
 * 
 * ResourceHelper
 * 
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public class ResourceHelper {

    public static ResourceLocation getResourceLocation(String modId, String path) {

        return new ResourceLocation(modId, path);
    }

}
