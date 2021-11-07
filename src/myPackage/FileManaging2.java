package myPackage;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextPane;
import java.awt.Color;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.awt.event.ActionEvent;
// importación de clase
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileReader;

public class FileManaging2 extends JFrame {

	private JPanel contentPane;
	private JTextField txtDescription;
	private JTextField txtPrize;
	private JButton btnAlta;
	private JLabel lblResult;
	private JButton btnShowProd;
	private JTextArea txtAreaResult;
	private JLabel lblFavorable;
	private JLabel lblDesfavorable;
	StringBuilder sb = new StringBuilder();
	String strLine = "";
	String str_data = "";

	public static void main(String[] args) {
		FileManaging frame = new FileManaging();
	}

	public FileManaging2() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 968, 616);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(153, 204, 204));
		contentPane.setForeground(new Color(0, 102, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblartDescription = new JLabel("Descripci\u00F3n del art\u00EDculo:");
		lblartDescription.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblartDescription.setBounds(60, 82, 213, 31);
		contentPane.add(lblartDescription);

		JLabel lblPrecio = new JLabel("Precio:");
		lblPrecio.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblPrecio.setBounds(60, 169, 213, 31);
		contentPane.add(lblPrecio);

		txtDescription = new JTextField();
		txtDescription.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtDescription.setBounds(283, 80, 184, 31);
		contentPane.add(txtDescription);
		txtDescription.setColumns(10);

		txtPrize = new JTextField();
		txtPrize.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtPrize.setColumns(10);
		txtPrize.setBounds(283, 167, 184, 31);
		contentPane.add(txtPrize);

		btnAlta = new JButton("Alta");
		btnAlta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
//Obtenemos el contenido de los JTextField
				String desc = txtDescription.getText();
				String prize = txtPrize.getText();
				createFile(); // Llamamos al método para crear el fichero
//Insertamos en el fichero los datos de los JTextField
				try {
					FileWriter myFile = new FileWriter("Fichero1.txt", true);
					myFile.write(desc + "  ");
					myFile.write(prize + "  " + "\r\n");
					lblFavorable.setText("FAVORABLE");
					myFile.close();
				} catch (IOException e) {
					lblDesfavorable.setText("DESFAVORABLE");
					e.printStackTrace();
				}
			}
		});
		btnAlta.setBackground(new Color(204, 204, 0));
		btnAlta.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnAlta.setBounds(549, 109, 118, 40);
		contentPane.add(btnAlta);

		lblResult = new JLabel("resultado: ");
		lblResult.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblResult.setBounds(677, 115, 97, 29);
		contentPane.add(lblResult);

		btnShowProd = new JButton("Visualizar productos");
		btnShowProd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//Llamamos al método para mostrar el contenido del fichero
				showInfo();
			}
		});
		btnShowProd.setBackground(new Color(102, 153, 153));
		btnShowProd.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnShowProd.setBounds(26, 423, 213, 31);
		contentPane.add(btnShowProd);

		txtAreaResult = new JTextArea();
		txtAreaResult.setBounds(264, 306, 655, 263);
		contentPane.add(txtAreaResult);
		lblFavorable = new JLabel("");
		lblFavorable.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblFavorable.setForeground(new Color(0, 153, 102));
		lblFavorable.setBounds(794, 115, 125, 29);
		contentPane.add(lblFavorable);
		lblDesfavorable = new JLabel("");
		lblDesfavorable.setForeground(Color.RED);
		lblDesfavorable.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblDesfavorable.setBounds(784, 119, 125, 24);
		contentPane.add(lblDesfavorable);
		setVisible(true);
	}

//Este método creará el fichero cuando se le llame desde el btnAlta
	private static void createFile() {
		try {
			File myFile1 = new File("Fichero1.txt");
			if (myFile1.createNewFile()) {
				System.out.println("El fichero no existia y se ha creado con el nombre de: " + myFile1.getName());
			} else {
				System.out.println("El fichero fue creado previamente, no se ha vuelto a crear");
			}
		} catch (IOException e) {
			System.out.println("Error, no se ha podido crear el archivo");
			e.printStackTrace();
		}
	}

//Este método imprimirá todo aquello guardado en el fichero en la textArea cuando se le llame desde el btnShowProd
	private void showInfo() {
		try {
			BufferedReader br = new BufferedReader(new FileReader("Fichero1.txt"));
			while (strLine != null) {
				if (strLine == null)
					break;
				str_data += strLine;
				strLine = br.readLine();

			}
			System.out.println(str_data);
			txtAreaResult.setText(str_data);
			br.close();
		} catch (IOException e) {
			System.out.println("Error");
			e.printStackTrace();
		}
	}
}
