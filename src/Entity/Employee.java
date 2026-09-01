
package Entity;

import company.Tools;
import db.go;
import javax.swing.JTable;

public class Employee implements mainData{
    private int emp_no;
    private String emp_name;
    private String address;
    private double salary;
    private String hiring_date;
    private String birth_date;
    private int dept_no;

    public int getEmp_no() {
        return emp_no;
    }

    public void setEmp_no(int emp_no) {
        this.emp_no = emp_no;
    }

    public String getEmp_name() {
        return emp_name;
    }

    public void setEmp_name(String emp_name) {
        this.emp_name = emp_name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getHiring_date() {
        return hiring_date;
    }

    public void setHiring_date(String hiring_date) {
        this.hiring_date = hiring_date;
    }

    public String getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(String birth_date) {
        this.birth_date = birth_date;
    }

    public int getDept_no() {
        return dept_no;
    }

    public void setDept_no(int dept_no) {
        this.dept_no = dept_no;
    }

    @Override
    public void add() {
        String strAdd = "insert into employee values ("
                + emp_no+",'"+ emp_name + "','"+ address +"',"
                + salary + ",'" +hiring_date +"','" + birth_date+"',"
                +dept_no +")";
        boolean isAdded = go.runNonQuery(strAdd);
        if(isAdded)
            Tools.msgBox("Employee Is Added....");
        else
            Tools.msgBox("Employee Is Not Add....\n"
                    + "pleas try again to add...");
    }

    @Override
    public void update() {
        String strUpdate = "update employee set "
                + "emp_name = '"+emp_name +"',"
                + "address = '" + address + "',"
                + "salary = " + salary +","
                +"hiring_date = '" + hiring_date +"',"
                + "birth_date = '" + birth_date +"',"
                + "dept_no = "+dept_no
                +" where emp_no = "+ emp_no;
        boolean isUpdate = go.runNonQuery(strUpdate);
        if(isUpdate)
            Tools.msgBox("Employee Is Updated...");
        else 
            Tools.msgBox("employee Is Not Updated....\n"
                    + "pleas try again to update....");
        
    }
//delete from employee where emp_no =1;
    @Override
    public void delete() {
        String strDelete = "delete from employee where emp_no = "+emp_no;
        boolean isDelete = go.runNonQuery(strDelete);
        if(isDelete)
            Tools.msgBox("Employee Is Deleted....");
        else
            Tools.msgBox("Employee Is Not Deleted....\n"
                    + "pleas try again to delete....");
    }

    @Override
    public String getAutoNumber() {
        return go.getAutoNumber("employee","emp_no");
    }

    @Override
    public void getAllRows(JTable table) {
        go.fillToJTable("employee_Data", table);
    }

    @Override
    public void getOneRow(JTable table) {
        String strSelect = "select * from employee_data where number = " + emp_no;
        go.fillToJTable(strSelect, table);
    }

    @Override
    public void getCustomRows(String statment, JTable table) {
        go.fillToJTable(statment, table);
    }

    @Override
    public String getValueByName(String name) {
        String strSelect = "select emp_no from employee where "
                + "emp_name = '" +name + "'";
        String strName =(String) go.getTableData(strSelect).Items[0][0];
        return strName;
    }

    @Override
    public String getNameByValue(String value) {
        String strselect = "select emp_name from employee where "
                + "emp_no = " + value;
        String strVlaue =(String) go.getTableData(strselect).Items[0][0];
        return strVlaue;
    }
    
    
}
