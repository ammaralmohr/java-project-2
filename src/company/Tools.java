/* this tools explaineing in
{ 119,126,134,138,140,141,142,143,144,145
    146,147,148,149}
*/
package company;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Tools {
    //صندوق عرض الرسائل
    public static void msgBox(String messag){
        JOptionPane.showMessageDialog(null,messag);
    }
    //صندوق ادخال 
     public static Object InputBox(String title){
        Object obj = JOptionPane.showInputDialog(title);
        return obj;
    }
    //صندوق التحقق من العملية
    public static boolean confirmMsg(String message){
        int confirm = JOptionPane.showConfirmDialog(null, message);
        if(confirm == JOptionPane.YES_OPTION) return true;
        else return false;
    }
    //انشاء ملف
    public static void CreateFolder(String FolderName, String path){
        File f = new File(path+"/"+FolderName);
        f.mkdir();
    }
    //انشاء ملف
    public static void CreateFolder(String FolderName){
        File f = new File(FolderName);
        f.mkdir();
    }
    // تهيئة شاشة البرنامج
    public static void openForm(JFrame form,String IconName){
        try {
            form.setLocationRelativeTo(null);
            Image img = ImageIO.read(Tools.class.getResource(IconName));
            form.setIconImage(img);
            form.setDefaultCloseOperation(2);
            form.getContentPane().setBackground(Color.white);
            form.setVisible(true);
        } catch (IOException ex) {
            Logger.getLogger(Tools.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static void openForm(JFrame form){
        try {
            form.setLocationRelativeTo(null);
            Image img = ImageIO.read(Tools.class.getResource("emp.png"));
            form.setIconImage(img);
            form.setDefaultCloseOperation(2);
            form.getContentPane().setBackground(Color.white);
            form.setVisible(true);
        } catch (IOException ex) {
            Logger.getLogger(Tools.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //مسح محتوى جميع الحقول النصية في المشروع
    public static void ClearText(Container form){
       for(Component c: form.getComponents()){
           if(c instanceof JTextField){
               JTextField J= (JTextField) c;
               J.setText("");
           }
           else if(c instanceof Container){
               ClearText((Container)c);
           }
       } 
    }
    // انشاء ملف فارغ
    public static void createEptyFile(String fileName){
        try {
            File f = new File(fileName+".txt");
            f.createNewFile();
        } catch (IOException ex) {
            Logger.getLogger(Tools.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static void createEptyFiles(String fileName[]){
      for(String str : fileName){
          createEptyFile(str);
      }  
    }
    // انشاء ملف ببيانات
    public static void createFile(String fileName, Object Data[]){
        try {
            PrintWriter p = new PrintWriter(fileName+".txt");
            for(Object obj : Data){
                p.println(obj);
            }
            p.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Tools.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static void creatFiles(String fileName[],Object allData[][]){
            for(int x=0;x<fileName.length;x++){
               createFile(fileName[x],allData[x]); 
            
        }
    }
    //استخراج الارقام من نص
    public static String getNumber(String txt){
        String val ="";
        for(char c:txt.toCharArray()){
            if(c=='0'|| c=='1'|| c=='2'|| c=='3'|| c=='4'|| c=='5'|| c=='6'|| c=='7'|| c=='8'|| c=='9')
             val +=c;   
        }
        return val;
    }
    public static int getNumberToInteger(String txt){
        String val ="";
        for(char c:txt.toCharArray()){
            if(c=='0'|| c=='1'|| c=='2'|| c=='3'|| c=='4'|| c=='5'|| c=='6'|| c=='7'|| c=='8'|| c=='9')
             val +=c;   
        }
        return Integer.parseInt(val);
    }
     public static String removeNumber(String txt){
        String val ="";
        for(char c:txt.toCharArray()){
            if(!(c=='0'|| c=='1'|| c=='2'|| c=='3'|| c=='4'|| c=='5'|| c=='6'|| c=='7'|| c=='8'|| c=='9'))
             val +=c;   
        }
        return val;
    }
    public static void printScreen(String imageName,JFrame form){
        try {
            form.setState(1);
            Robot r = new Robot();
            Rectangle rec =new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
            BufferedImage img = r.createScreenCapture(rec);
            ImageIO.write(img, "jpg",new File( imageName+".jpg"));
            form.setState(0);
        } catch (Exception ex) {
            Logger.getLogger(Tools.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }

    
    // create nested class using tables
    public class Table {
    public int columns;
    public Object [][] Items;
    
    // constractor table
    public Table (int columns){
       this.columns = columns;
       Items = new Object [0][columns];
    }
    
    public void addNewRow(Object row[]){
        //الإحتفاظ بالعناصر القديمة داخل متغير مؤقت
        Object temp [][] = Items;
        // إضافة سطر جديد للجدول
        Items = new Object [Items.length+1][columns];
        // تعبئة العناصر القديمة في العنصر الأساسي
        for (int x=0;x<temp.length;x++){
            Items[x]=temp[x];
        }
        //إضافة السطر الجديد للجدول 
        Items[Items.length-1]= row;
    }
    
    public void printTable(){
        for(Object [] item:Items){
            for (Object itm:item){
                System.out.print(itm + " ; ");
            }
            System.out.println();
        }
    }
    
    public void editRow(int rowIndex,int columnIndex,Object newData){
        Items[rowIndex -1][columnIndex -1]= newData;
    }
    
    public void deleteRow(int rowIndex){
        Object temp [][]=Items;
        Items = new Object [Items.length-1][columns];
        int y=0;
       /* this code is correct but it is longer than the next
        for(int x =0; x<Items.length;x++){
            if(x>=rowIndex-1){
                Items[x]=temp[x+1];
            }
            else{
                Items[x]=temp[x];
            }
        }*/
       for(int x =0; x<temp.length;x++){
           if(x!=rowIndex-1){
               Items[y]=temp[x];
               y++;
           }   
       }
    }
    
    public Object getValue(int rowindex,int columnIndex){
       return Items[rowindex-1][columnIndex-1];
    }
    
    public Object[] getRow(int rowIndex){
        return Items[rowIndex];
    }
    public int getRowsCount (Table table){
        return table.Items.length;
    }
    
    // get row as string
    public String getRowAsString(int row){
        Object[] table = getRow(row);
        String str = "";
        for(Object item:table)
            str += ";" + item;
        str += ";";
        return str;
    }
    
  } 
   // the end of class table
    
    

}
