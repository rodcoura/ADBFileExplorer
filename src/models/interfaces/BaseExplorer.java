package models.interfaces;

public interface BaseExplorer {
	public abstract boolean isDir(String path);
	public abstract boolean isFile(String path);
	public abstract String[] list(String path);
	public abstract void copy(String from, String to);
	public abstract void remFile(String path);
	public abstract void remDir(String path);
	public abstract void move(String from, String to);
}
