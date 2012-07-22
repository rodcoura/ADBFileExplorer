package models.util;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;

import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

public class AndroidFileTreeModel implements TreeModel {
	protected AndroidDebugBridge adb;
	
	public AndroidFileTreeModel(AndroidDebugBridge adb) {
		this.adb = adb;
	}

	@Override
	public void addTreeModelListener(TreeModelListener arg0) {
		return;
	}

	@Override
	public Object getChild(final Object parent, int index) {
		String[] children = this.adb.list(parent.toString());
		String nextPath = adb.cleanPath(parent.toString() + adb.separator + children[index]);
		return nextPath;
	}

	@Override
	public int getChildCount(Object parent) {
		String[] children = this.adb.list(parent.toString());
		if(children == null)
			return 0;
		return children.length;
	}

	@Override
	public int getIndexOfChild(Object parent, Object child) {
		String[] children = this.adb.list(parent.toString());
		if (children == null)
			return -1;
		String childname = child.toString();
		for (int i = 0; i < children.length; i++) {
			if (childname.equals(children[i]))
				return i;
		}
		return -1;
	}

	@Override
	public Object getRoot() {
		return new String("/");
	}

	@Override
	public boolean isLeaf(Object node) {
		boolean oii = adb.isFile(node.toString());
		return oii;
	}

	@Override
	public void removeTreeModelListener(TreeModelListener arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void valueForPathChanged(TreePath arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}

	private class TreeString {
		String treeString;
		public TreeString(String name) {
			this.treeString = treeString;
		}

		@Override
		public String toString() {
			return this.treeString.split("/")[this.treeString.split("/").length - 1];
		}
	}
}
