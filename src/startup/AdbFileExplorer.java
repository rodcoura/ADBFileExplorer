package startup;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import models.Device;
import views.MainView;
import controllers.ADBFileExplorerController;

public class AdbFileExplorer {
	/**
	 * Launch the application.
	 * Check compute arch then tell if swing will be global menu on MAC or not 
	 * @throws UnsupportedLookAndFeelException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		String vers = System.getProperty("os.name").toLowerCase();
		if(vers.indexOf("mac") != -1)
			System.setProperty("apple.laf.useScreenMenuBar", "true");
		
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		ADBFileExplorerController controller = new ADBFileExplorerController();
		Device device = new Device();
		device.setCode("emulator-5554");
		controller.setCurrentDevice(device);
		MainView mainWindow = new MainView(controller);
		mainWindow.setLocationRelativeTo(null);		
		mainWindow.setVisible(true); 	
	}
}

/*
 * Name: Rodrigo Rodrigues Coura 
 * 
 * ADB USEFULL COMMANDS 
 * 
 * Mark directories with a trailing slash (/), doors with a trailing greater-than sign (>), executable
 * files with a trailing asterisk (*), FIFOs with a trailing vertical bar (|),
 * symbolic links with a trailing at-sign (@), and AF_Unix address family
 * sockets with a trailing equals sign (=). 
 * 
 * COMAND: adb -s DEVICE_ID shell ls -A1XF /FOLDER/ 
 * 
 * PULL Files 
 * 
 * COMAND: adb -s DEVICE_ID pull FILE_FROM_ANDROID FILE_DESTINATION_PC 
 * 
 * PUSH Files 
 * 
 * COMAND: adb -s DEVICE_ID pull FILE_FROM_PC FILE_DESTINATION_ANDROID
 * 
 */