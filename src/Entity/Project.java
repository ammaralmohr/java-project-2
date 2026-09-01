
package Entity;

import company.Tools;
import db.go;
import javax.swing.JTable;

public class Project implements mainData{
    private int project_no;
    private String project_name;
    private String location;
    private int dept_no;

    public int getProject_no() {
        return project_no;
    }

    public void setProject_no(int project_no) {
        this.project_no = project_no;
    }

    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getDept_no() {
        return dept_no;
    }

    public void setDept_no(int dept_no) {
        this.dept_no = dept_no;
    }

    @Override
    public void add() {
        String add = "insert into project values ("
                + project_no + ","
                + "'" +project_name +"',"
                + "'" + location + "',"
                + dept_no + ")";
        if (go.runNonQuery(add))
            Tools.msgBox("Project Is Added....");
        else
            Tools.msgBox("Project Is Not Added....\n"
                    + "Please try again to add....");
    }

    @Override
    public void update() {
        String update = "update Project set "
                +"project_name = '" + project_name +"',"
                + "location = '" + location + "',"
                + "dept_no = " + dept_no +
                " where project_no = " + getProject_no();
        if(go.runNonQuery(update))
            Tools.msgBox("Project Is Updated .....");
        else
            Tools.msgBox("Project Is Not Updated .....\n"
                    + "Please try agine to update.....");
    }

    @Override
    public void delete() {
        String delete = "delete from project where project_no ="+ getProject_no();
        if(go.runNonQuery(delete))
            Tools.msgBox("Project Is Deleted....");
        else 
            Tools.msgBox("Project Is Not Deleted....\n"
                    + "Please try agine to delete....");
    }

    @Override
    public String getAutoNumber() {
        return go.getAutoNumber("project", "project_no");
    }

    @Override
    public void getAllRows(JTable table) {
        String strSelect = "select * from project_data";
        go.fillToJTable(strSelect, table);
    }

    @Override
    public void getOneRow(JTable table) {
        String strSelect = "select * from project_data where project_no = "+getProject_no();
        go.fillToJTable(strSelect, table);
    }

    @Override
    public void getCustomRows(String statment, JTable table) {
        go.fillToJTable(statment, table);
    }

    @Override
    public String getValueByName(String name) {
        String strSelect = "select project_no from project where "
                + "project_name = '" + name +"'";
        String projNo = (String)go.getTableData(strSelect).Items[0][0];
        return projNo;
    }

    @Override
    public String getNameByValue(String value) {
        String strSelect = "select project_name from project where"
                + " project_no = " + value;
        return (String) go.getTableData(strSelect).Items[0][0];
        
    }
    
}
