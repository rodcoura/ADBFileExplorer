package models.util;

import java.io.File;
import java.util.Properties;

public class SystemInfo {
	
	private Properties prop;
	
	public SystemInfo(Properties prop) {
		this.prop = prop;
	}

	public String getPlantaform() {
		return this.prop.getProperty("sun.desktop");
	}
	
	public String getSeperator() {
		return this.prop.getProperty("file.separator");
	}
	
	public String[] getLogicalDrivers() {
		File[] fileRoots = File.listRoots();
		String[] drivers = new String[fileRoots.length];
		for(int i = 0 ; i < drivers.length ; i++)
			drivers[i] = fileRoots[i].getPath();
		return drivers;
	}
}
