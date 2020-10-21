package com.moti.redis;

public class MenuKey extends BasePrefix{

	private MenuKey(String prefix) {
		super(prefix);
	}
	public static MenuKey getById = new MenuKey("id");
	public static MenuKey getByTitle = new MenuKey("name");
	public static MenuKey getLeftMenu = new MenuKey("lm");
	public static MenuKey getRightMenu = new MenuKey("rm");
}
