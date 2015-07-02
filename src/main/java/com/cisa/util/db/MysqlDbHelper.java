package com.cisa.util.db;

public class MysqlDbHelper {
	
	//TODO 能够执行dml和ddl语句
	//返回的result set结果集遍历之后转成list或者是二维数组进行返回
	//注意控制好事务处理和非事务处理，事务处理批量执行，失败需要回滚撤销
	//要能获取到数据库的结构信息DatabaseMetaData还有结果集的metadata的使用
	//这样就可以动态的获取需要的结果集对象，当对象结果集过大时候，几十万几百万时候可以考虑在拼接sql时候进行进行分页处理
	public static void exeTransactionSql(String[] sql){
		
	}
	
	public static void exeNoneTransactionSql(String[] sql){
		
	}
	
	public static void exeProcedure(String statement){
		
	}
	
	public static void exeSelectSql(String sql){
		
	}
}
