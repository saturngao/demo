package com.example.demo.config;

import lombok.extern.slf4j.Slf4j;
import net.sf.jsqlparser.parser.CCJSqlParserManager;
import net.sf.jsqlparser.statement.select.Select;
import net.sf.jsqlparser.statement.select.SelectBody;
import net.sf.jsqlparser.statement.select.SelectVisitorAdapter;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.session.ResultHandler;

import java.io.StringReader;
import java.sql.Statement;
import java.util.Properties;

@Intercepts({@Signature(type = StatementHandler.class, method = "query", args = {Statement.class, ResultHandler.class}),
		@Signature(type = StatementHandler.class, method = "update", args = {Statement.class}),
		@Signature(type = StatementHandler.class, method = "batch", args = { Statement.class })})
@Slf4j
public class MyInterceptor implements Interceptor {
	CCJSqlParserManager parserManager = new CCJSqlParserManager();

	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		log.info("entry the intercept");
		StatementHandler handler = (StatementHandler)invocation.getTarget();
		//由于mappedStatement为protected的，所以要通过反射获取
		MetaObject statementHandler = SystemMetaObject.forObject(handler);
		//mappedStatement中有我们需要的方法id
		MappedStatement mappedStatement = (MappedStatement) statementHandler.getValue("delegate.mappedStatement");
		//获取sql
		BoundSql boundSql = handler.getBoundSql();
		String sql = boundSql.getSql();
		//获取方法id
		String id = mappedStatement.getId();
		//获得方法类型
		SqlCommandType sqlCommandType = mappedStatement.getSqlCommandType();

		//增强sql代码块
		if ("SELECT".equals(sqlCommandType.name())) {
			//如果是select就将sql转成SELECT对象
			Select select = (Select) parserManager.parse(new StringReader(sql));
			SelectBody selectBody = select.getSelectBody();
			select.getWithItemsList();
//			selectBody
			//访问各个visitor
			select.getSelectBody().accept(new SelectVisitorAdapter());
			//将增强后的sql放回
			statementHandler.setValue("delegate.boundSql.sql", select.toString());

		}
		return invocation.proceed();
	}

	@Override
	public Object plugin(Object target) {
		//生成代理对象
		return Plugin.wrap(target, this);
	}

	@Override
	public void setProperties(Properties properties) {
	}
}