package com.moti.redis;

public class ArticleKey extends BasePrefix{

	private ArticleKey(String prefix) {
		super(prefix);
	}
	public static ArticleKey getById = new ArticleKey("id");
	public static ArticleKey getByTitle = new ArticleKey("title");
	public static ArticleKey getIndex = new ArticleKey("index");
	public static ArticleKey getfamousArticles = new ArticleKey("fa");
	public static ArticleKey getByReadCount = new ArticleKey("read");
	public static ArticleKey getEsCount = new ArticleKey("es");

}
