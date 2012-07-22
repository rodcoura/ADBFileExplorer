package models.util;

import java.io.IOException;

import models.Device;
import models.interfaces.BaseExplorer;

public class AndroidDebugBridge implements BaseExplorer {
	private Device currentDevice;
	private CommandShell cmdShell;
	public String separator = "/";

	private String baseCmd;
	private String llCmd = "shell ls -l ";
	private String lsCmd = "shell busybox ls -A1FX ";

	// private String pushCmd = "push ? ?";
	// private String pullCmd = "pull ? ?";

	public AndroidDebugBridge(String adbPath, Device currentDevice) {
		this.currentDevice = currentDevice;
		this.cmdShell = new CommandShell();
		cmdShell.setCommanderPath(adbPath);
		this.baseCmd = "adb -s " + this.currentDevice.getCode() + " ";
	}

	/*
	 * public boolean isDir(String path) { path = removeDoubleBars(path);
	 * String[] check = null; boolean clause = false; try {
	 * this.cmdShell.run(this.baseCmd + this.llCmd + path); check =
	 * this.cmdShell.getResponse(); if (check == null) clause = false; else if
	 * (check.length == 1) clause = check[0].substring(0, 1).equals("d"); else
	 * if (check.length == 0) clause = false; else { if (path.equals("/"))
	 * clause = true; else { String[] aux = path.split("/"); clause =
	 * !check[0].contains(aux[aux.length - 1]); } } } catch (IOException e) {
	 * clause = false; } return clause; }
	 */

	public boolean isDir(String path) {
		return false;
	}

	public String getParent(String path) {
		path = cleanPath(path);
		String[] explodePath = path.split("/");
		if (explodePath.length == 0 || explodePath.length == 1)
			return "/";
		else {
			String aux = "";
			for (int i = 0; i < explodePath.length - 2; i++)
				aux = "/" + explodePath[i] + this.separator;
			aux = aux.substring(0, aux.length() - 1);
			return aux;
		}
	}

	public boolean isFile(String path) {
		return !this.isDir(path);
	}

	public String cleanPath(String x) {
		x = x.replaceAll("//", "/");
		char[] aux = x.toCharArray();
		switch (aux[aux.length - 1]) {
		case '@':
			return x.substring(0, x.length() - 1);
		case '*':
			return x.substring(0, x.length() - 1);
		case '>':
			return x.substring(0, x.length() - 1);
		case '|':
			return x.substring(0, x.length() - 1);
		case '/':
			return x.substring(0, x.length() - 1);
		default:
			return x;
		}
	}

	public void walk(String path) {
		System.out.println(path);
		String[] list = this.list(path);
		if (list != null)
			for (int i = 0; i < list.length; i++) {
				String nextDir = cleanPath(path + this.separator + list[i]);
				if (this.isDir(nextDir))
					walk(nextDir);
			}
	}

	public String[] list(String path) {
		path = cleanPath(path);
		String[] list = null;
		try {
			this.cmdShell.run(this.baseCmd + this.lsCmd + path);
			list = this.cmdShell.getResponse();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public void copy(String from, String to) {
		// TODO Auto-generated method stub

	}

	@Override
	public void remFile(String path) {
		// TODO Auto-generated method stub

	}

	@Override
	public void remDir(String path) {
		// TODO Auto-generated method stub

	}

	@Override
	public void move(String from, String to) {
		// TODO Auto-generated method stub

	}
}
