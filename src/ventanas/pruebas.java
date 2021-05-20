package ventanas;

import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

public class pruebas extends JFrame {

	
	    JTable table = null;
	    JScrollPane scrollPane=null;
	    class TABLE_MODEL extends DefaultTableModel{
	        public static final String INITIAL_VALUE = "DATO 1";
	        public TABLE_MODEL(){
	            this.addColumn(COLUMN1);
	            this.addColumn(COLUMN2);
	            this.addColumn(COLUMN3);
	            this.addColumn(COLUMN4);
	            this.addColumn(COLUMN5);	         
	        }
	        public static final String COLUMN1="Vencimiento";
	        public static final String COLUMN2="Fecha Pago";
	        public static final String COLUMN3="Moda Pago";
	        public static final String COLUMN4="Importe";
	        public static final String COLUMN5="Observaciones";
	        public void addData(String a,String b){
	            final Object rowData[]={a,b,INITIAL_VALUE,a,a};
	            this.addRow(rowData);
	        }
	    }
	    TABLE_MODEL model=new TABLE_MODEL();
	    final String[] DATA = { "Dato 1", "Dato 2", "Dato 3", "Dato 4" };
	    public pruebas(){
	        JComboBox comboBox = new JComboBox(DATA);
	        DefaultCellEditor defaultCellEditor=new DefaultCellEditor(comboBox);
	        table=new JTable(model);
	        table.getColumnModel().getColumn(2).setCellEditor(defaultCellEditor);
	        scrollPane=new JScrollPane(table);
	        this.getContentPane().add(scrollPane);
	        this.setSize(500,500);
	        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    
	        addData();
	    }
	    public void addData(){
	        model.addData("valor 1", "valor 2");
	        model.addData("valor 3", "valor 4");
	        model.addData("valor 5", "valor 6");
	    }
	    public static void main(String[] args) {
	    SwingUtilities.invokeLater(new Runnable() {
	        @Override
	        public void run() {
	        new pruebas().setVisible(true);
	            }
	        });
	    }




}
