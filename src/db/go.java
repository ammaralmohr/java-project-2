// easy to use data base
package db;

import company.Tools;
import company.Tools.Table;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class go {
        
    private static String url ="";
    private static String dbName = "company";
    private static Connection connection;
    private static void setURL(){
        url = "jdbc:mysql://localhost:3306/"+ dbName // acsess to data base
               + "?useUnicode=true&characterEncoding=UTF-8";// make data base accept arabic 
    }
    
    private static void setConnection(){
        try {
            setURL();
            connection = DriverManager.getConnection(url,"root","");
        } catch (SQLException ex) {
           Tools.msgBox(ex.getMessage()+"\n setconnection error"); 
        }
    }
    
    public static boolean checkUserAndPass(String user,String pass){
        try{
            setConnection();
            Statement stmt = connection.createStatement();
            String strCheck = "select * from users where "+
                    "username='" + user +"' and "+
                    "pass='" + pass +"';";
            stmt.executeQuery(strCheck);
            while(stmt.getResultSet().next()){
                connection.close();
                return true;
            }
        connection.close();
        }
        catch (SQLException ex ){
            Tools.msgBox(ex.getMessage()+ "\n checkUserAndPass error");
        }
        
        return false;
    }
    // running insert & update & delete from data base
    public static boolean runNonQuery(String sqlStatement){
        try{
            setConnection();
            Statement stmt = connection.createStatement();
            stmt.execute(sqlStatement);
            connection.close();
            return true;
        }
        catch(SQLException ex){
            Tools.msgBox(ex.getMessage() + "\n run rerror"); 
            try {
                connection.close();
            } catch (SQLException ex1) {
                Tools.msgBox(ex1.getMessage());
            }
            return false;
        }  
        
    }
    public static String getAutoNumber(String tableName,String columenName){
        try{
            setConnection();
            Statement stmt = connection.createStatement();
            String strAuto = "select max(" + columenName + ")+1 as autoNum"+
                    " from "+ tableName;
            stmt.executeQuery(strAuto);
            String num = "";
            while(stmt.getResultSet().next()){
                num = stmt.getResultSet().getString("autoNum");
            }
            connection.close();
            if(num == null|| "".equals(num))return "1";
            else return num;
        }
        catch(SQLException ex){
            Tools.msgBox(ex.getMessage());
            return "0";
        }
    }
    
    public static Table getTableData(String statement){
        Tools tools = new Tools();
        try{
            setConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs;
            rs = stmt.executeQuery(statement);
            ResultSetMetaData rsmd =rs.getMetaData();
            
            int columen = rsmd.getColumnCount();
            
            Table table =tools.new Table(columen);
            while(rs.next()){
                Object row [] = new Object [columen];
                for(int x =0 ; x<columen;x++){
                    row[x]= rs.getString(x+1);
                }
                table.addNewRow(row);
            }
            connection.close();
            return table;
              
        }
        catch(SQLException ex){
            Tools.msgBox(ex.getMessage());
            return tools.new Table(0);
        }
    }
    
    public static void fillCombo(String tableName, String columenName,JComboBox combo){
        try{
            setConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs;
            String strSelect = "select "+columenName + " from "+ tableName;
            rs = stmt.executeQuery(strSelect);
            rs.last();// make rs pointer on last row
            int rows = rs.getRow();
            rs.beforeFirst(); // make rs pointer before the start of rows
            
            String values[] = new String [rows] ;
            int x= 0;
            while(rs.next()){
                values[x] = rs.getString(1);
                x++;
            }
            connection.close();
            // fill comboBox with returend values
            combo.setModel(new DefaultComboBoxModel(values));
        }
        catch(SQLException ex){
            Tools.msgBox(ex.getMessage());
                   
        }
    }
    
    // fill JTable witch take select statement <<<OR>>> table name
    
    public static void fillToJTable (String tableNameOrSelectStatement , JTable table){
        try{
            setConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs;
            String SPart = tableNameOrSelectStatement.substring(0, 7).toLowerCase();
            String strSelect ;
            if("select ".equals(SPart) ){
                strSelect = tableNameOrSelectStatement;
            }
            else{
                strSelect = "select * from "+ tableNameOrSelectStatement;                
            }
            rs = stmt.executeQuery(strSelect);
            ResultSetMetaData rsmd = rs.getMetaData();
            int columens = rsmd.getColumnCount();
            // get the model of table witch sended
            DefaultTableModel model =(DefaultTableModel) table.getModel();
            // create vector to fill the table with it
            Vector row = new Vector();
            model.setRowCount(0);
            
            while(rs.next()){
                row = new Vector(columens);
                for(int i =1;i<= columens;i++){
                    row.add(rs.getString(i));
                }
                model.addRow(row);                
            }
            if(table.getColumnCount() != columens){
                Tools.msgBox("JTable columens count not equal Query columens count\n"
                +"JTable columens count Is: "+ table.getColumnCount()+
                        "\nQuery columens count Is: "+ columens);
            }
            connection.close();
        }
        catch(SQLException ex){
            Tools.msgBox(ex.getMessage());
        }
    }
    
}
