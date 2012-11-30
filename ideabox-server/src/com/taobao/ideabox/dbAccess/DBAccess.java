package com.taobao.ideabox.dbAccess;

import org.apache.tomcat.dbcp.dbcp.BasicDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: shufj
 * Date: 11/28/12 7:42 下午
 */
public class DBAccess {

    static String driver = "com.mysql.jdbc.Driver";
    static String url = "jdbc:mysql://10.13.37.29:3306/tms";
    static String user = "tms";
    static String pwd = "tms";
    static Map<String, Map<String, String>> tableMap = new HashMap<String, Map<String, String>>();
    private static Connection connection;
    private static ResultSet resultSet;

    /**
     * 注册驱动，形成连接
     */
    public static void getConnection(){
        try{
            javax.sql.DataSource ds = getDataSource();
            connection = ds.getConnection();
        } catch (Exception ignored){
        }
    }

    /**
     * 插入一条数据
     * @param sql 执行语句
     * @return 是否成功
     */
    public static boolean insertData(String sql){
        try{
            javax.sql.DataSource ds = getDataSource();
            Connection connection = ds.getConnection();
            Statement statement = connection.createStatement();
            boolean flag = statement.execute(sql);
            connection.close();
            return flag;
        }catch (Exception e){
            return false;
        }
    }

    /**
     * 更新一条数据
     * @param sql 执行语句
     * @return 是否成功
     */
    public static boolean updateData(String sql){
        try{
            javax.sql.DataSource ds = getDataSource();
            Connection connection = ds.getConnection();
            Statement statement = connection.createStatement();
            boolean flag = statement.executeUpdate(sql) > 0;
            connection.close();
            return flag;
        }catch (Exception e){
            return false;
        }
    }


    public static void closeConnection(){
        try{
            resultSet.close();
            connection.close();
        } catch (Exception ignored){
        }
    }

    public static ResultSet Query(String sql){
        try{
            List<Object> e = new ArrayList<Object>();
            getConnection();
            Statement statement = connection.createStatement();
            resultSet =  statement.executeQuery(sql);
            return resultSet;
        }catch (Exception e){
            return null;
        }
    }

    public  static DataSource getDataSource(){
        BasicDataSource ds = new BasicDataSource();
        ds.setUrl(url);
        ds.setUsername(user);
        ds.setPassword(pwd);
        ds.setDriverClassName(driver);
        ds.setMaxActive(8);
        ds.setConnectionProperties("clientEncoding=gbk");
        return ds;
    }

//    private static void getTableMap(){
//        try{
//            DocumentBuilderFactory domfac=DocumentBuilderFactory.newInstance();
//            DocumentBuilder dombuilder=domfac.newDocumentBuilder();
//            InputStream is=new FileInputStream("conf/configDO.xml");
//            Document doc=dombuilder.parse(is);
//            Element root  = doc.getDocumentElement();
//            NodeList tables=root.getChildNodes();
//            for(int i = 0; i < tables.getLength() ; i++){
//                Node table = tables.item(i);
//                if(table.getNodeType()==Node.ELEMENT_NODE){
//                    Map<String, String> fieldMap = new HashMap<String, String>();
//                    NodeList fields = table.getChildNodes();
//                    for(int j = 0; j < fields.getLength(); j++){
//                        Node field = fields.item(i);
//                        String value = field.getAttributes().getNamedItem("name").getNodeValue();
//                        fieldMap.put(value, value);
//                    }
//                    String tableName = table.getAttributes().getNamedItem("name").getNodeName();
//                    tableMap.put(tableName, fieldMap);
//                }
//            }
//        }catch (Exception e){
//            return;
//        }
//    }



}
