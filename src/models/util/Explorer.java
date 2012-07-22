package models.util;

import models.interfaces.BaseExplorer;

public class Explorer implements BaseExplorer {

	@Override
	public boolean isDir(String path) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isFile(String path) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String[] list(String path) {
		// TODO Auto-generated method stub
		return null;
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
