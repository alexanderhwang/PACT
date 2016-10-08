package com.alexhwang;

import java.awt.event.MouseAdapter;

public class MegaMouseAdapter extends MouseAdapter{
	private int savedValue;

	public MegaMouseAdapter(int savedValue) {
		this.savedValue = savedValue;
	}
	
	public int getSavedValue() {
		return savedValue;
	}
}
