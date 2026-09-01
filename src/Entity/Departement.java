
package Entity;

import company.Tools;
import javax.swing.JTable;

public class Departement implements mainData{
    private int dept_no;
    private String dept_name;
    private String Location;

    public int getDeptNo() {
        return dept_no;
    }

    public void setDeptNo(int dept_No) {
        this.dept_no = dept_No;
    }

    public String getDeptName() {
        return dept_name;
    }

    public void setDeptName(String dept_Name) {
        this.dept_name = dept_Name;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String Location) {
        this.Location = Location;
    }
    @Override
    public void add() {
        String strInsert = "insert into department values("
                +getDeptNo() + ",'" + getDeptName() + "','"
                + getLocation() + "');";
        boolean isAdd = db.go.runNonQuery(strInsert);
        
        if(isAdd){
            Tools.msgBox("Department Is Added...");
        }
        
        if(!isAdd){
            Tools.msgBox("Department Is Not Added...\n"
                    + "pleas try again to add...");
        }
    }

    @Override
    public void update() {
        String strUpdate = "update department set "
                + "dept_name = '"+ dept_name + "',"
                + "location = '" + Location + "' "
                + "where dept_No = " + dept_no ;
         boolean isUpdate = db.go.runNonQuery(strUpdate);
        
        if(isUpdate){
            Tools.msgBox("Department Is Updated...");
        }
        
        if(!isUpdate){
            Tools.msgBox("Department Is Not Updated...\n"
                    + "pleas try again to Update...");
        }
        
    }
//delete from employee where emp_no =1;
    @Override
    public void delete() {
       String strDelete = "delete from department where dept_No = " + dept_no;
       boolean isDelete = db.go.runNonQuery(strDelete);
       
       if(isDelete){
           Tools.msgBox("The Row Is Deleted From Department...");
       }
       else{
           Tools.msgBox("The Row Is Not Deleted From Department...\n"
                   + "Pleas Try again to delete....");
       }
    }

    @Override
    public String getAutoNumber() {
        return db.go.getAutoNumber("department", "dept_no");
    }

    @Override
    public void getAllRows(JTable table) {
        db.go.fillToJTable("department_data", table);
    }

    @Override
    public void getOneRow(JTable table) {
        String strRow = "select * from department_data where department_no = "+dept_no; 
        db.go.fillToJTable(strRow, table);
    }

    @Override
    public void getCustomRows(String statment, JTable table) {
        db.go.fillToJTable(statment, table);
    }

    @Override
    public String getValueByName(String name) {
        String strSelect = "select dept_no from department where dept_name = '" 
                + name + "'";
        String strValue =(String) db.go.getTableData(strSelect).Items[0][0];
        return strValue;
    }

    @Override
    public String getNameByValue(String value) {
        String strSelect = "select dept_name from department where dept_no =" 
                + value;
        String strName =(String) db.go.getTableData(strSelect).Items[0][0];
        return strName;
    }
    
    
}
