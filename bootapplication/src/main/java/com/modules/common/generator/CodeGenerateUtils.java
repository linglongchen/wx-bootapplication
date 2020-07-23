package com.modules.common.generator;

import com.modules.common.generator.utils.DateUtil;
import freemarker.template.Template;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.io.*;
import java.math.BigDecimal;
import java.sql.*;
import java.util.Date;
import java.util.*;

public class CodeGenerateUtils {

	private final String AUTHOR = "chenTom";
	private final String packageName = "com.modules.system";
	private final String URL = "jdbc:mysql://127.0.0.1:3306/questionnaire?useUnicode=true&characterEncoding=UTF8&serverTimezone=Asia/Shanghai";
	private final String USER = "root";
	private final String PASSWORD = "123456";
	private final String DRIVER = "com.mysql.jdbc.Driver";
	private final String diskPath = "E:\\ideaProject\\wx-bootapplication\\bootapplication\\src\\main\\java\\com\\modules\\system";
	private final String date = DateUtil.getTime(new Date());
	private List<ColumnClass> columnClassList;
	private final String primaryKey = "id";
	private final String primaryKeyJDBCType = "BIGINT";

	public Connection getConnection() throws Exception {
		Class.forName(DRIVER);
		Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
		return connection;
	}

	public synchronized void generate() throws Exception {
		try {
			Connection connection = getConnection();
			List<String> tables = this.getTables(connection);
			DatabaseMetaData databaseMetaData = connection.getMetaData();
			for (String tablename : tables) {
				this.generater(tablename, databaseMetaData);
			}
			connection.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
		}
	}

	private synchronized void generater(String tablename, DatabaseMetaData databaseMetaData) throws Exception {
		ResultSet resultSet = databaseMetaData.getColumns(null, "%", tablename, "%");
		columnClassList = this.getColumnClassList(resultSet);
		// 生成Model文件
		this.generateModelFile(tablename);
		//生成excel导入的模板
		this.generateDataModelFile(tablename);
		//生成excel导入的listenner模板代码
		this.generateDataListenerFile(tablename);
		// 生成Form文件
		this.generateFormFile(tablename);
		// 生成Vo文件
		this.generateVOFile(tablename);
		// 生成Query文件
		this.generateQueryFile(tablename);
		// 生成dao接口
		this.generateMapperFile(tablename);
		// 生成mapper.xml文件
		this.generateMapperXMLFile(tablename);
		// 生成service文件
		this.generateServiceFile(tablename);
		// 生成serviceImpl文件
		this.generateServiceImplFile(tablename);
		// 生成Controller层文件
		this.generateControllerFile(tablename);

	}
	private void generateModelFile(String tablename) throws Exception {
		String changeTableName = getClassName(tablename);
		String modelFilePath = diskPath + "\\entity\\";
		final String suffix = ".java";
		File diskPathFile = new File(modelFilePath);
		if (!diskPathFile.isDirectory()) {
			diskPathFile.mkdirs();
		}
		final String path = modelFilePath + changeTableName + suffix;
		final String templateName = "model.ftl";
		File mapperFile = new File(path);
		Map<String, Object> dataMap = new HashMap<>();
		dataMap.put("model_columns", columnClassList);
		this.generateFileByTemplate(tablename, changeTableName, templateName, mapperFile, dataMap);

	}

	private void generateDataModelFile(String tablename) throws Exception {
		String changeTableName = getClassName(tablename);
		String modelFilePath = diskPath + "\\excelparse\\";
		final String suffix = "Data.java";
		File diskPathFile = new File(modelFilePath);
		if (!diskPathFile.isDirectory()) {
			diskPathFile.mkdirs();
		}
		final String path = modelFilePath + changeTableName + suffix;
		final String templateName = "dataModel.ftl";
		File mapperFile = new File(path);
		Map<String, Object> dataMap = new HashMap<>();
		dataMap.put("model_columns", columnClassList);
		this.generateFileByTemplate(tablename, changeTableName, templateName, mapperFile, dataMap);

	}
	private void generateDataListenerFile(String tablename) throws Exception {
		String changeTableName = getClassName(tablename);
		String modelFilePath = diskPath + "\\excelparse\\";
		final String suffix = "DataListener.java";
		File diskPathFile = new File(modelFilePath);
		if (!diskPathFile.isDirectory()) {
			diskPathFile.mkdirs();
		}
		final String path = modelFilePath + changeTableName + suffix;
		final String templateName = "listener.ftl";
		File mapperFile = new File(path);
		Map<String, Object> dataMap = new HashMap<>();
		dataMap.put("model_columns", columnClassList);
		this.generateFileByTemplate(tablename, changeTableName, templateName, mapperFile, dataMap);

	}

	private void generateControllerFile(String tablename) throws Exception {
		String changeTableName = getClassName(tablename);
		String controllerFilePath = diskPath + "\\api\\";
		File diskPathFile = new File(controllerFilePath);
		if (!diskPathFile.isDirectory()) {
			diskPathFile.mkdirs();
		}
		final String suffix = "Controller.java";
		final String path = controllerFilePath + changeTableName + suffix;
		final String templateName = "controller.ftl";
		File mapperFile = new File(path);
		Map<String, Object> dataMap = new HashMap<>();
		this.generateFileByTemplate(tablename, changeTableName, templateName, mapperFile, dataMap);
	}

	private void generateServiceFile(String tablename) throws Exception {
		String changeTableName = getClassName(tablename);
		String serviceFilePath = diskPath + "\\service\\";
		final String suffix = "Service.java";
		final String path = serviceFilePath + changeTableName + suffix;
		final String templateName = "service.ftl";
		File mapperFile = new File(path);
		Map<String, Object> dataMap = new HashMap<>();
		this.generateFileByTemplate(tablename, changeTableName, templateName, mapperFile, dataMap);
	}

	private void generateServiceImplFile(String tablename) throws Exception {
		String changeTableName = getClassName(tablename);
		String serviceFilePath = diskPath + "\\service\\impl\\";
		final String suffix = "ServiceImpl.java";
		final String path = serviceFilePath + changeTableName + suffix;
		final String templateName = "serviceImpl.ftl";
		File mapperFile = new File(path);
		Map<String, Object> dataMap = new HashMap<>();
		this.generateFileByTemplate(tablename, changeTableName, templateName, mapperFile, dataMap);
	}

	/**
	 * 生成Mapper实现类
	 *
	 * @throws Exception
	 */
	private void generateMapperFile(String tablename) throws Exception {
		String changeTableName = getClassName(tablename);
		String mapprFilePath = diskPath + "\\dao\\";
		final String suffix = "Dao.java";
		final String path = mapprFilePath + changeTableName + suffix;
		final String templateName = "mapper.ftl";
		File mapperFile = new File(path);
		Map<String, Object> dataMap = new HashMap<>();
		this.generateFileByTemplate(tablename, changeTableName, templateName, mapperFile, dataMap);

	}

	private void generateMapperXMLFile(String tablename) throws Exception {
		String changeTableName = getClassName(tablename);
		String mapprFilePath = diskPath+"\\mapper\\";
		final String suffix = "Dao.xml";
		final String path = mapprFilePath + changeTableName + suffix;
		final String templateName = "mapperxml.ftl";
		File mapperFile = new File(path);
		Map<String, Object> dataMap = new HashMap<>();
		dataMap.put("model_columns", columnClassList);
		dataMap.put("primary_key", primaryKey);
		dataMap.put("primary_key_JDBCType", primaryKeyJDBCType);
		this.generateFileByTemplate(tablename, changeTableName, templateName, mapperFile, dataMap);

	}

	/**
	 * 生成daoImpl实现类
	 * @throws Exception
	 */
	@SuppressWarnings("unused")
	private void generateDaoImplFile(String tablename) throws Exception {
		String changeTableName = getClassName(tablename);
		final String suffix = "DAOImpl.java";
		final String path = diskPath + changeTableName + suffix;
		final String templateName = "daoImpl.ftl";
		File mapperFile = new File(path);
		Map<String, Object> dataMap = new HashMap<>();
		this.generateFileByTemplate(tablename, changeTableName, templateName, mapperFile, dataMap);

	}

	/**
	 * 生成form
	 *
	 * @throws Exception
	 */
	private void generateFormFile(String tablename) throws Exception {
		String changeTableName = getClassName(tablename);
		String formFilePath = diskPath + "\\form\\";
		final String suffix = "Form.java";
		final String path = formFilePath + changeTableName + suffix;
		final String templateName = "form.ftl";
		File mapperFile = new File(path);
		Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("model_columns", columnClassList);
		this.generateFileByTemplate(tablename, changeTableName, templateName, mapperFile, dataMap);
	}

	/**
	 * 生成vo
	 *
	 * @throws Exception
	 */
	private void generateVOFile(String tablename) throws Exception {
		String changeTableName = getClassName(tablename);
		String voFilePath = diskPath + "\\vo\\";
		final String suffix = "VO.java";
		final String path = voFilePath + changeTableName + suffix;
		final String templateName = "vo.ftl";
		File mapperFile = new File(path);
		Map<String, Object> dataMap = new HashMap<>();
		dataMap.put("model_columns", columnClassList);
		this.generateFileByTemplate(tablename, changeTableName, templateName, mapperFile, dataMap);

	}

	/**
	 * 生成vo
	 *
	 * @throws Exception
	 */
	private void generateQueryFile(String tablename) throws Exception {
		String changeTableName = getClassName(tablename);
		String voFilePath = diskPath + "\\query\\";
		final String suffix = "Query.java";
		final String path = voFilePath + changeTableName + suffix;
		final String templateName = "query.ftl";
		File mapperFile = new File(path);
		Map<String, Object> dataMap = new HashMap<>();
		dataMap.put("model_columns", columnClassList);
		this.generateFileByTemplate(tablename, changeTableName, templateName, mapperFile, dataMap);

	}

	/**
	 * 生成包装的vo类
	 *
	 * @throws Exception
	 */
	@SuppressWarnings("unused")
	private void generateWrapperVOFile(String tablename) throws Exception {
		String changeTableName = getClassName(tablename);
		final String suffix = "WrapperVO.java";
		final String path = diskPath + changeTableName + suffix;
		final String templateName = "wrapperVO.ftl";
		File mapperFile = new File(path);
		Map<String, Object> dataMap = new HashMap<>();
		this.generateFileByTemplate(tablename, changeTableName, templateName, mapperFile, dataMap);

	}

	/**
	 * 生成文件的主方法入口
	 *
	 * @param templateName
	 * @param file
	 * @param dataMap
	 * @throws Exception
	 */
	private void generateFileByTemplate(String tableName, String changeTableName, final String templateName, File file,
			Map<String, Object> dataMap) throws Exception {
		Template template = TemplateUtils.getTemplate(templateName);
		FileOutputStream fos = new FileOutputStream(file);
		dataMap.put("table_name_small", tableName);
		dataMap.put("table_name", changeTableName);
		dataMap.put("author", AUTHOR);
		dataMap.put("date", date);
		dataMap.put("a_tag_start", "#{");
		dataMap.put("a_tag_end", "}");
		dataMap.put("package_name", packageName);
		Writer out = new BufferedWriter(new OutputStreamWriter(fos, "utf-8"), 10240);
		template.process(dataMap, out);
	}

	// 将通过下滑线分割的字符转换为首字母大写字符串
	public String replaceUnderLineAndUpperCase(String str) {
		StringBuffer sb = new StringBuffer();
		sb.append(str);
		int count = sb.indexOf("_");
		while (count != 0) {
			int num = sb.indexOf("_", count);
			count = num + 1;
			if (num != -1) {
				char ss = sb.charAt(count);
				char ia = (char) (ss - 32);
				sb.replace(count, count + 1, ia + "");
			}
		}
		String result = sb.toString().replaceAll("_", "");
		return StringUtils.capitalize(result);
	}

	// 将通过下滑线分割的字符转换为驼峰形式,如create_user_id-->createUserId
	public String replaceUnderLineToHump(String str) {
		StringBuffer sb = new StringBuffer();
		sb.append(str);
		int count = sb.indexOf("_");
		while (count != 0) {
			int num = sb.indexOf("_", count);
			count = num + 1;
			if (num != -1) {
				char ss = sb.charAt(count);
				char ia = (char) (ss - 32);
				sb.replace(count, count + 1, ia + "");
			}
		}
		String result = sb.toString().replaceAll("_", "");
		return result;
	}

	public String getClassName(String tableName) {
		//将表名去掉系统名称并截取出来
		//tableName = tableName.substring(tableName.indexOf("_") + 1);
		return this.replaceUnderLineAndUpperCase(tableName);
	}

	/**
	 * 获取字符串型的类型名
	 *
	 * @param type
	 * @return
	 */
	private String getTypeName(int type) {
		String typeName = "";
		switch (type) {
		case Types.VARCHAR:
			case Types.LONGVARCHAR:
			case Types.CLOB:
			typeName = String.class.getSimpleName();
			break;
		case Types.SMALLINT:
			typeName = Short.class.getSimpleName();
			break;
		case Types.INTEGER:
			typeName = Integer.class.getSimpleName();
			break;
		case Types.NUMERIC:
			typeName = BigDecimal.class.getSimpleName();
			break;
		case Types.DATE:
			case Types.TIMESTAMP:
			case Types.TIME:
			typeName = Date.class.getSimpleName();
			break;
		case Types.TINYINT:
		case Types.BIT:
		case Types.BOOLEAN:
			typeName = boolean.class.getSimpleName();
			break;
		case Types.FLOAT:
			typeName = Float.class.getSimpleName();
			break;
		case Types.DOUBLE:
			typeName = Double.class.getSimpleName();
			break;
		case Types.DECIMAL:
			typeName = BigDecimal.class.getSimpleName();
			break;
		case Types.BIGINT:
			typeName = Long.class.getSimpleName();
			break;
		default:
			break;
		}
		return typeName;
	}

	/**
	 * 获取jdbc类型
	 *
	 * @param type
	 * @return
	 */
	private String getJDBCTypeName(int type) {
		String typeName = "";
		switch (type) {
		case Types.VARCHAR:
			typeName = "VARCHAR";
			break;
			case Types.LONGVARCHAR:
				typeName = "LONGVARCHAR";
				break;
		case Types.BLOB:
			typeName = "BLOB";
			break;
		case Types.SMALLINT:
			typeName = "SMALLINT";
			break;
		case Types.INTEGER:
			typeName = "INTEGER";
			break;
		case Types.NUMERIC:
			typeName = "NUMERIC";
			break;
		case Types.DATE:
			typeName = "DATE";
			break;
		case Types.TIMESTAMP:
			typeName = "DATE";
			break;
		case Types.TINYINT:
			typeName = "TINYINT";
			break;
		case Types.BIT:
			typeName = "BIT";
			break;
		case Types.BOOLEAN:
			typeName = "BOOLEAN";
			break;
		case Types.FLOAT:
			typeName = "FLOAT";
			break;
		case Types.BIGINT:
			typeName = "BIGINT";
			break;
		case Types.DOUBLE:
			typeName = "DOUBLE";
			break;
		case Types.DECIMAL:
			typeName = "DECIMAL";
			break;
		default:
			break;
		}
		return typeName;
	}

	// 获取数据表的列字段信息
	private List<ColumnClass> getColumnClassList(ResultSet resultSet) throws SQLException {
		List<ColumnClass> columnClassList = new ArrayList<>();
		while (resultSet.next()) {
			// id字段略过
			ColumnClass columnClass = new ColumnClass();
			// 获取字段名称
			columnClass.setColumnName(resultSet.getString("COLUMN_NAME"));
			// 获取字段类型
			String type = resultSet.getString("DATA_TYPE");
			columnClass.setJdbcType(getJDBCTypeName(NumberUtils.toInt(type)));
			columnClass.setColumnType(getTypeName(NumberUtils.toInt(type)));
			System.out.println("columnName=>" + columnClass.getColumnName() + ",type=>" + type);
			// 转换字段名称，如 sys_name 变成 SysName
			columnClass.setChangeColumnName(replaceUnderLineToHump(resultSet.getString("COLUMN_NAME")));
			//columnClass.setChangeColumnName(resultSet.getString("COLUMN_NAME"));
			// 字段在数据库的注释
			columnClass.setColumnComment(resultSet.getString("REMARKS"));
			columnClassList.add(columnClass);
		}
		return columnClassList;
	}

	private List<String> getTables(Connection conn) throws SQLException {
		List<String> tablenames = new ArrayList<String>();
		DatabaseMetaData dbMetData = conn.getMetaData();
		ResultSet rs = dbMetData.getTables(null, convertDatabaseCharsetType("root", "mysql"), null,
				new String[] { "TABLE" });
		while (rs.next()) {
			if (rs.getString(4) != null && (rs.getString(4).equalsIgnoreCase("TABLE"))) {
				String tableName = rs.getString(3).toLowerCase();
				tablenames.add(tableName);
			}
		}
		return tablenames;

	}

	public static String convertDatabaseCharsetType(String in, String type) {
		String dbUser;
		if (in != null) {
			if (type.equals("oracle")) {
				dbUser = in.toUpperCase();
			} else if (type.equals("postgresql")) {
				dbUser = "public";
			} else if (type.equals("mysql")) {
				dbUser = null;
			} else if (type.equals("mssqlserver")) {
				dbUser = null;
			} else if (type.equals("db2")) {
				dbUser = in.toUpperCase();
			} else {
				dbUser = in;
			}
		} else {
			dbUser = "public";
		}
		return dbUser;
	}

}
