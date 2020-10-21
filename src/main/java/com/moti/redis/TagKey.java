package com.moti.redis;

public class TagKey extends BasePrefix{

	private TagKey(String prefix) {
		super(prefix);
	}
	public static TagKey getById = new TagKey("id");
	public static TagKey getByTitle = new TagKey("name");
	public static TagKey getIndex = new TagKey("index");
}
