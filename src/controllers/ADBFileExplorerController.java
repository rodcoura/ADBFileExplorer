package controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import models.Device;
import models.util.CommandShell;
import models.util.SystemInfo;

public class ADBFileExplorerController {
	private Device currentDevice;
	public static String adbPath = "D:\\dev\\sdks\\android\\platform-tools\\";
	
	public ADBFileExplorerController() {		
	}
	
	public Object[] getAvailableDevices() {
		String[] cmdResponse = null;
		List<String> outResponse = new ArrayList<String>();
		CommandShell commandShell = new CommandShell();
		commandShell.setCommanderPath(ADBFileExplorerController.adbPath);
		try {
			commandShell.run("adb devices");
			cmdResponse = commandShell.getResponse();
			for(int i = 1 ; i < cmdResponse.length ; i++) {
				outResponse.add(cmdResponse[i].split("\t")[0]);	
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return outResponse.toArray();
	}
	
	public String[] getPCFileSystemRoots() {
		SystemInfo systemInfo = new SystemInfo(System.getProperties());
		return systemInfo.getLogicalDrivers();
	}
	
	public Device getCurrentDevice() {
		return currentDevice;
	}

	public void setCurrentDevice(Device currentDevice) {
		this.currentDevice = currentDevice;
	}
	
}
