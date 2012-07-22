package views;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultTreeCellRenderer;

import models.util.AndroidDebugBridge;
import models.util.AndroidFileTreeModel;
import models.util.FileTreeModel;
import controllers.ADBFileExplorerController;

@SuppressWarnings("serial")
public class MainView extends JFrame {
	private JTree fileSystemTree;
	private JTree androidFileSystemTree;
	private JComboBox cmbLogicalDrivers;
	private ADBFileExplorerController controller;
	private Icon leafIcon = new ImageIcon(MainView.class.getResource("/views/images/Document-icon.png"));
	private Icon folderIcon = new ImageIcon(MainView.class.getResource("/views/images/Folder-icon.png"));
	private Icon folderAltIcon = new ImageIcon(MainView.class.getResource("/views/images/FolderAlt-icon.png"));

	/**
	 * Create the application.
	 */
	public MainView(ADBFileExplorerController controller) {
		setTitle("Android Debug Bridge File Explorer - v0.1");
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainView.class.getResource("/views/images/Folder.png")));
		setResizable(false);
		this.controller = controller;

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnConfiguration = new JMenu("Configuration");
		menuBar.add(mnConfiguration);
		
		JMenuItem mntmSelectAdbPath = new JMenuItem("Select ADB Path");
		mntmSelectAdbPath.setIcon(new ImageIcon(MainView.class.getResource("/com/sun/java/swing/plaf/windows/icons/DetailsView.gif")));
		mnConfiguration.add(mntmSelectAdbPath);

		JMenu mnAbout = new JMenu("About");
		menuBar.add(mnAbout);
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		this.setBounds(100, 100, 670, 670);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		DefaultTreeCellRenderer renderer = new DefaultTreeCellRenderer();
		renderer.setOpenIcon(folderIcon);
		renderer.setClosedIcon(folderAltIcon);
		renderer.setLeafIcon(leafIcon);
		getContentPane().setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 34, 300, 406);
		getContentPane().add(scrollPane);

		fileSystemTree = new JTree();
		scrollPane.setViewportView(fileSystemTree);
		fileSystemTree.setCellRenderer(renderer);
		fileSystemTree.addTreeSelectionListener(new TreeSelectionListener() {
			public void valueChanged(TreeSelectionEvent evt) {
				JOptionPane.showMessageDialog(null, evt.getPath().toString());
			}
		});

		JScrollPane scrollPaneAndroid = new JScrollPane();
		scrollPaneAndroid.setBounds(364, 34, 300, 406);
		getContentPane().add(scrollPaneAndroid);

		androidFileSystemTree = new JTree();
		androidFileSystemTree.setCellRenderer(renderer);
		scrollPaneAndroid.setViewportView(androidFileSystemTree);

		JButton button = new JButton("");
		button.setIcon(new ImageIcon(MainView.class.getResource("/views/images/Right.png")));
		button.setBounds(310, 41, 44, 44);
		getContentPane().add(button);

		cmbLogicalDrivers = new JComboBox();
		cmbLogicalDrivers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				String drive = MainView.this.cmbLogicalDrivers.getSelectedItem().toString();
				MainView.this.fileSystemTree.setModel(new FileTreeModel(new File(drive)));
			}
		});
		cmbLogicalDrivers.setModel(new DefaultComboBoxModel(this.controller.getPCFileSystemRoots()));
		cmbLogicalDrivers.setSelectedIndex(0);
		cmbLogicalDrivers.setBounds(97, 7, 203, 20);
		getContentPane().add(cmbLogicalDrivers);

		JLabel lblSelectRootDriver = new JLabel("Select root driver:");
		lblSelectRootDriver.setBounds(8, 10, 88, 14);
		getContentPane().add(lblSelectRootDriver);

		JComboBox cmbAndroidDevice = new JComboBox();
		cmbAndroidDevice.setModel(new DefaultComboBoxModel(this.controller.getAvailableDevices()));
		cmbAndroidDevice.setBounds(460, 7, 196, 20);		
		getContentPane().add(cmbAndroidDevice);

		JLabel lblSelectYourDevice = new JLabel("Select your device:");
		lblSelectYourDevice.setBounds(364, 10, 98, 14);
		getContentPane().add(lblSelectYourDevice);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(0, 491, 664, 130);
		getContentPane().add(scrollPane_1);
		
		JList logList = new JList();
		scrollPane_1.setViewportView(logList);
				
		this.updateTree();
	}

	public void updateTree() {
		this.fileSystemTree.setModel(new FileTreeModel(new File(this.cmbLogicalDrivers.getSelectedItem().toString())));
		this.androidFileSystemTree.setModel(new AndroidFileTreeModel(new AndroidDebugBridge(ADBFileExplorerController.adbPath, this.controller.getCurrentDevice())));
	}
	
	public JTree getFileSystemTree() {
		return this.fileSystemTree;
	}
}
