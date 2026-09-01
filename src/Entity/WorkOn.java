
package Entity;

import company.Tools;
import db.go;
import javax.swing.JTable;

public class WorkOn implements mainData{
    private int emp_no;
    private int project_no;

    public int getEmp_no() {
        return emp_no;
    }

    public void setEmp_no(int emp_no) {
        this.emp_no = emp_no;
    }

    public int getProject_no() {
        return project_no;
    }

    public void setProject_no(int project_no) {
        this.project_no = project_no;
    }

    @Override
    public void add() {
        String strAdd = "insert into workon values ("
                + getEmp_no() + ","
                + getProject_no() + ")";
        if(go.runNonQuery(strAdd))
            Tools.msgBox("Employee Is Added in Project....");
        else 
            Tools.msgBox("Employee Is Not Added in Project....\n"
                    + "Pleas try again to add.....");
    }

    @Override
    public void update() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete() {
        String strDelete = "delete from workon where emp_no = "+getEmp_no()
                + " and project_no = "+ getProject_no();
        if(go.runNonQuery(strDelete))
            Tools.msgBox("Emloyee Is Deleted From Project....");
        else
            Tools.msgBox("Employee Is Not Deleted From Project....\n"
                    + "Pleas try again to delete.....");
    }

    @Override
    public String getAutoNumber() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void getAllRows(JTable table) {
        go.fillToJTable("work_on_data", table);
    }

    @Override
    public void getOneRow(JTable table) {
        String strSelect = "select * from work_on_data where project_no = "+getProject_no();
        go.fillToJTable(strSelect, table);
    }

    @Override
    public void getCustomRows(String statment, JTable table) {
        go.fillToJTable(statment, table);
    }

    @Override
    public String getValueByName(String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getNameByValue(String value) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
