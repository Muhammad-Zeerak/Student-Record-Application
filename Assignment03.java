// Name: Muhammad Zeerak
// Class: BSCS-8A
// CMS ID: 267328
/* Characteristics:
 * The JTable basically creates rows after filling data.
 * The file is created in desktop in which data from JTable is being saved.
 * If the data is clicked then text gets populated and update button updates it in JTable and in the file
*/
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.table.*;
import java.io.*;

@SuppressWarnings("serial")
public class assignment extends JFrame implements ActionListener
{	
	// declaration and assignment of all the labels, textFields, and other things that are to be used
	JLabel stdInfo = new JLabel ("Student Information");
	JLabel stdInfo2 = new JLabel ("Student Information");
	JLabel stdId = new JLabel ("Student_ID");
	JLabel stdName = new JLabel ("Student_Name");
	JLabel stdRegNo = new JLabel ("Student_RegNo.");
	JLabel sem = new JLabel ("Semester");
	JLabel semGpa = new JLabel ("Semester_GPA");
	JLabel semCgpa = new JLabel ("CGPA");
	
	JTextField field1 = new JTextField(20);
	JTextField field2 = new JTextField(20);
	JTextField field3 = new JTextField(20);
	JTextField field4 = new JTextField(20);
	JTextField field5 = new JTextField(20);
	JTextField field6 = new JTextField(20);
	
	JButton save = new JButton("Save");
	JButton update = new JButton("Update");
	
	JPanel panel0 = new JPanel(); // these panels are used in such a way that the design, i.e the layout is same as the one provided in assignment
	JPanel panel1 = new JPanel();
	JPanel panel2 = new JPanel();
	JPanel panel3 = new JPanel();
	JPanel panel4 = new JPanel();
	JPanel panel5 = new JPanel();	
	JPanel panel6 = new JPanel();	
	
	DefaultTableModel model = new DefaultTableModel(); // table model to enter rows and columns through model not by default
	JTable table1 = new JTable(model); 
	JScrollPane scrollPane;
	
	int i = 0; // counter for the number of rows in table
	String id, name, regNo, seme, semeGpa, Cgpa, value, value2; // to save the values of all the fields
	int row, col; // row and column number
	
	assignment() // constructor
	{
		panel0.setLayout(new FlowLayout()); // every stuff for the designing purpose only
		panel0.add(stdInfo);
		
		panel1.setLayout(new GridLayout(6 ,1 , 15, 15));
		panel1.add(stdId);
		panel1.add(field1);
		panel1.add(stdName);
		panel1.add(field2);
		panel1.add(stdRegNo);
		panel1.add(field3);
		panel1.add(sem);
		panel1.add(field4);
		panel1.add(semGpa);
		panel1.add(field5);
		panel1.add(semCgpa);
		panel1.add(field6);

		panel5.add(save);
		panel5.add(update);
		
		panel2.setLayout(new BorderLayout());
		panel2.add(panel0, BorderLayout.NORTH);
		panel2.add(panel1, BorderLayout.CENTER);
		panel2.add(panel5, BorderLayout.SOUTH);
		
		model.addColumn("Student_ID"); // adding column to the table
		model.addColumn("Student_Name");
		model.addColumn("Student_RegNo");
		model.addColumn("Semester");
		model.addColumn("Semester_GPA");
		model.addColumn("CGPA");
		
		scrollPane = new JScrollPane(table1);
		panel3.setLayout(new BorderLayout());
		panel3.add(stdInfo2, BorderLayout.NORTH);
		panel3.add(scrollPane, BorderLayout.CENTER);
		
		panel6.add(panel3);

		panel4.add(panel2);
		panel4.add(panel6);
		
		add(panel4);
		
		save.addActionListener(this); // adding action Listeners to the buttons
		update.addActionListener(this);
		
		table1.addMouseListener( new MouseAdapter() // mouse adapter for the table data that is to be clicked
		{
		public void mousePressed(MouseEvent me)
		{

				if(me.getClickCount() == 2)
				{
					try // exception to be added because code works for one click not double
					{
					    throw new IllegalArgumentException("Works only when Clicked once not twice. So click on another field to try that.");
					} 
					catch(IllegalArgumentException e) 
					{
						JOptionPane.showMessageDialog(null, "Exception Occured! " + e.getMessage());
					}
					
					field1.setText(""); // now setting the text fields to null after so that the user try again on another field
					field2.setText("");
					field3.setText("");
					field4.setText("");
					field5.setText("");
					field6.setText("");
				}

				else if (me.getClickCount() == 1)
				{
					row= table1.getSelectedRow(); // corresponding row and column and the data in it
					col= table1.getSelectedColumn();
					value = table1.getModel().getValueAt(row, col).toString();
					
					switch(col) // to fill the corresponding text field according to the column selected
					{
						case 0:
							field1.setText(value);
							break;
						case 1:
							field2.setText(value);
							break;
						case 2:
							field3.setText(value);
							break;
						case 3:
							field4.setText(value);
							break;
						case 4:
							field5.setText(value);
							break;
						case 5:
							field6.setText(value);
							break;
					}
				}
		}
		});
		
		setTitle("Student Record Application"); // all the setting features of the frame
		setSize(500,700);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	public static void main(String[] args) // main start
	{
		new assignment(); // constructor called
	} // main ends

	@Override
	public void actionPerformed(ActionEvent e) // action handler
	{
		if (e.getSource() == save)
		{	
			id = field1.getText(); // get text from the text fields
			name = field2.getText();
			regNo = field3.getText();
			seme = field4.getText();
			semeGpa = field5.getText();
			Cgpa = field6.getText();
			
			// if any field is left unfilled then exception thrown
			// field1.getText().isEmpty() returns true if Text field is empty 
			if(field1.getText().isEmpty() || field2.getText().isEmpty() || field3.getText().isEmpty() || field4.getText().isEmpty() || field5.getText().isEmpty() || field6.getText().isEmpty())
			{
				try // exception to be added when user does't enter in one of the field or more of them
				{
				    throw new IllegalArgumentException("You forget to fill in the data!");
				} 
				catch(IllegalArgumentException ae) 
				{
					JOptionPane.showMessageDialog(null, "Exception Occured! " + ae.getMessage());
				}
			}
			// when all the fields are filled in then new row will be added
			else if(field1.getText().isEmpty() !=true && field2.getText().isEmpty() !=true && field3.getText().isEmpty() !=true && field4.getText().isEmpty() !=true && field5.getText().isEmpty() || field6.getText().isEmpty() !=true)
			{
				model.insertRow(i, new Object[] { id, name, regNo, seme, semeGpa, Cgpa }); // insert row according to the data in fields
				i++; // increment row number so that the next entered data does't gets added as first row again
				
	              try // creates a JTableFile.txt on desktop and writes the information of JTable in the file
	                {
	                    DefaultTableModel model2 = (DefaultTableModel)table1.getModel();
	                    ObjectOutputStream fileOut = new ObjectOutputStream(new FileOutputStream("C:/Users/muham/Desktop/JTableFile.txt"));
	                    fileOut.writeObject(model2.getDataVector());
	                    fileOut.close();
	                } 
	              catch (IOException ex)
	                {
	                    ex.printStackTrace();
	                } 
				
				field1.setText(""); // now setting the text fields to null after addition of data
				field2.setText("");
				field3.setText("");
				field4.setText("");
				field5.setText("");
				field6.setText("");
			}
		}
		
		else if(e.getSource() == update)
		{
			if(table1.getRowCount() == 0) // if we press update and no row has been created then exception thrown
			{
				try 
				{
					throw new ArrayIndexOutOfBoundsException("No row has been created yet!"); // array index out of bound exception thrown
				} 
				catch (RuntimeException ex) // as this is runtime exception so it gets handled by this
				{
					JOptionPane.showMessageDialog(null, "Exception Occured! " + ex.getMessage()); // // message dialogue for handled exception
				}
			}
			switch(col) // to update the corresponding data in the column i used this
			{
				case 0: // the data in the column 0 will be updated because its data was pressed
					if (field1.getText().isEmpty()) // this statement is for when you select a column and update data then if you press again the data in table is again updated to null
					{ // to avoid that i did this
						break;
					}
					else if(field1.getText().isEmpty() != true) // only when the field is entered with some data in it then update button will work
					{
						value2 = field1.getText(); // get the text from field
						table1.getModel().setValueAt(value2, row, col); // set the value at specified location
						field1.setText(""); // set field back to normal
			            try // will update data in the JTableFile created
			              	{
			            		DefaultTableModel model2 = (DefaultTableModel)table1.getModel();
			            		ObjectOutputStream fileOut = new ObjectOutputStream(new FileOutputStream("C:/Users/muham/Desktop/JTableFile.txt"));
			            		fileOut.writeObject(model2.getDataVector());
			            		fileOut.close();
			              	} 
			            catch (IOException ex)
			            	{
			            		JOptionPane.showMessageDialog(null, "Exception Occured! " + ex.getMessage());
			                }
						break;	
					}
				case 1:
					if (field2.getText().isEmpty())
					{
						break;
					}
					else if(field2.getText().isEmpty() != true)
					{
						value2 = field2.getText();
						table1.getModel().setValueAt(value2, row, col);
						field2.setText("");
			            try // will update data in the JTableFile created
		              	{
		            		DefaultTableModel model2 = (DefaultTableModel)table1.getModel();
		            		ObjectOutputStream fileOut = new ObjectOutputStream(new FileOutputStream("C:/Users/muham/Desktop/JTableFile.txt"));
		            		fileOut.writeObject(model2.getDataVector());
		            		fileOut.close();
		              	} 
		            catch (IOException ex)
		            	{
		            		JOptionPane.showMessageDialog(null, "Exception Occured! " + ex.getMessage());
		                }
						break;	
					}
				case 2:
					if (field3.getText().isEmpty())
					{
						break;
					}
					else if(field3.getText().isEmpty() != true)
					{
						value2 = field3.getText();
						table1.getModel().setValueAt(value2, row, col);
						field3.setText("");
			            try // will update data in the JTableFile created
		              	{
		            		DefaultTableModel model2 = (DefaultTableModel)table1.getModel();
		            		ObjectOutputStream fileOut = new ObjectOutputStream(new FileOutputStream("C:/Users/muham/Desktop/JTableFile.txt"));
		            		fileOut.writeObject(model2.getDataVector());
		            		fileOut.close();
		              	} 
		            catch (IOException ex)
		            	{
		            		JOptionPane.showMessageDialog(null, "Exception Occured! " + ex.getMessage());
		                }
						break;	
					}
				case 3:
					if (field4.getText().isEmpty())
					{
						break;
					}
					else if(field4.getText().isEmpty() != true)
					{
						value2 = field4.getText();
						table1.getModel().setValueAt(value2, row, col);
						field4.setText("");
			            try // will update data in the JTableFile created
		              	{
		            		DefaultTableModel model2 = (DefaultTableModel)table1.getModel();
		            		ObjectOutputStream fileOut = new ObjectOutputStream(new FileOutputStream("C:/Users/muham/Desktop/JTableFile.txt"));
		            		fileOut.writeObject(model2.getDataVector());
		            		fileOut.close();
		              	} 
		            catch (IOException ex)
		            	{
		            		JOptionPane.showMessageDialog(null, "Exception Occured! " + ex.getMessage());
		                }
						break;	
					}
				case 4:
					if (field5.getText().isEmpty())
					{
						break;
					}
					else if(field5.getText().isEmpty() != true)
					{
						value2 = field5.getText();
						table1.getModel().setValueAt(value2, row, col);
						field5.setText("");
			            try // will update data in the JTableFile created
		              	{
		            		DefaultTableModel model2 = (DefaultTableModel)table1.getModel();
		            		ObjectOutputStream fileOut = new ObjectOutputStream(new FileOutputStream("C:/Users/muham/Desktop/JTableFile.txt"));
		            		fileOut.writeObject(model2.getDataVector());
		            		fileOut.close();
		              	} 
		            catch (IOException ex)
		            	{
	            			JOptionPane.showMessageDialog(null, "Exception Occured! " + ex.getMessage());
		                }
						break;	
					}
				case 5:
					if (field6.getText().isEmpty())
					{
						break;
					}
					else if(field6.getText().isEmpty() != true)
					{
						value2 = field6.getText();
						table1.getModel().setValueAt(value2, row, col);
						field6.setText("");
			            try // will update data in the JTableFile created
		              	{
		            		DefaultTableModel model2 = (DefaultTableModel)table1.getModel();
		            		ObjectOutputStream fileOut = new ObjectOutputStream(new FileOutputStream("C:/Users/muham/Desktop/JTableFile.txt"));
		            		fileOut.writeObject(model2.getDataVector());
		            		fileOut.close();
		              	} 
		            catch (IOException ex)
		            	{
	            			JOptionPane.showMessageDialog(null, "Exception Occured! " + ex.getMessage());
		                }
						break;	
					}
				default :
					break;
			} // end switch
		} 
	} // end event handler
} //end class