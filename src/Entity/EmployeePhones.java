
package Entity;

import company.Tools;
import db.go;
import javax.swing.JTable;

public class EmployeePhones implements mainData{
    private int emp_no;
    private String phone;

    public int getEmp_no() {
        return emp_no;
    }

    public void setEmp_no(int emp_no) {
        this.emp_no = emp_no;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public void add() {
        String strAdd = "insert into employee_phones values ("
                +getEmp_no()+",'"
                + getPhone()+"')";
        boolean isAdded = go.runNonQuery(strAdd);
        if(isAdded)
            Tools.msgBox("Phone Is Added....");
        else
            Tools.msgBox("Phone Is Not Added..../n"
                    + "pleas try again to add...");
    }

    @Override
    public void update() {
       Tools.msgBox("Update method in class employy_phones Not working!!!");
    }

    @Override
    public void delete() {
        String strDelete = "delete from employee_phones where phone = '"+getPhone()+"';";
        boolean isDelete = go.runNonQuery(strDelete);
        if(isDelete)
            Tools.msgBox("Phone Is Deleted....");
    }
    
    public void deleteAllPhones(){
        String strDelete = "delete from employee_phones where emp_no = "
                +getEmp_no();
        go.runNonQuery(strDelete);
    }

    @Override
    public String getAutoNumber() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void getAllRows(JTable table) {
        String strSelect = "select phone from employee_phones where emp_no = "+getEmp_no();
        go.fillToJTable(strSelect, table);
    }

    @Override
    public void getOneRow(JTable table) {
        String strSelect = "select phone from employee_phones_data where emp_no = "+getEmp_no();
        go.fillToJTable(strSelect, table);
    }

    @Override
    public void getCustomRows(String statment, JTable table) {
        go.fillToJTable(statment, table);
    }

    @Override
    public String getValueByName(String name) {
        String strSelect = "select emp_no from employee_phones where phone = "+getPhone();
        String strNo =(String) go.getTableData(strSelect).Items[0][0];
        return strNo;
    }

    @Override
    public String getNameByValue(String value) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
