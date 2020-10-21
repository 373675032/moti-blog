package com.moti.redis;

public class StatisticsKey extends BasePrefix{

	private StatisticsKey(String prefix) {
		super(prefix);
	}
	public static StatisticsKey getById = new StatisticsKey("id");
	public static StatisticsKey todayVisitor = new StatisticsKey("visitor");
	public static StatisticsKey todayRequest = new StatisticsKey("request");
}
