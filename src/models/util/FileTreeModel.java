package models.util;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;

import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

public class FileTreeModel implements TreeModel {
	protected File root;

	public FileTreeModel(File root) {
		this.root = root;
	}

	@Override
	public Object getRoot() {
		return root;
	}

	@Override
	public boolean isLeaf(Object node) {
		return ((File) node).isFile();
	}

	@Override
	public int getChildCount(Object parent) {
		String[] children = ((File) parent).list();
		if (children == null)
			return 0;
		return children.length;
	}

	@Override
	public Object getChild(final Object parent, int index) {
		String[] children = ((File) parent).list();
		Arrays.sort(children, new Comparator<String>() {
			public int compare(String obj1, String obj2) {
				File info = new File(((File) parent).getPath() + File.separator + obj1);
				File info2 = new File(((File) parent).getPath() + File.separator + obj2);
				if (info.isDirectory() && info2.isFile())
					return -1;
				return 1;
			}
		});
		if ((children == null) || (index >= children.length))
			return null;
		return new TreeFile((File) parent, children[index]);
	}

	@Override
	public int getIndexOfChild(Object parent, Object child) {
		String[] children = ((File) parent).list();
		if (children == null)
			return -1;
		String childname = ((File) child).getName();
		for (int i = 0; i < children.length; i++) {
			if (childname.equals(children[i]))
				return i;
		}
		return -1;
	}

	@Override
	public void valueForPathChanged(TreePath path, Object newvalue) {
	}

	@Override
	public void addTreeModelListener(TreeModelListener l) {
	}

	@Override
	public void removeTreeModelListener(TreeModelListener l) {
	}

	@SuppressWarnings("serial")
	private class TreeFile extends File {
		public TreeFile(File parent, String child) {
			super(parent, child);
		}

		@Override
		public String toString() {
			return getName();
		}
	}

}
