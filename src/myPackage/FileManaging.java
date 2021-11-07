package myPackage;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class FileManaging extends JFrame {

	private JPanel contentPane;
	private JTextField txtDescription;
	private JTextField txtPrize;
	private JButton btnAlta;
	private JLabel lblResult;
	private JButton btnShowProd;
	private JTextArea txtAreaResult;
	private JLabel lblFavorable;
	private JLabel lblDesfavorable;

	public static void main(String[] args) {
		FileManaging frame = new FileManaging();
	}

	public FileManaging() {
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
				// Obtenemos el contenido de los JTextField
				String desc = txtDescription.getText();
				String prize = txtPrize.getText();
				createFile(); // Llamamos al método para crear el fichero

				// Insertamos en el fichero los datos de los JTextField
				try {
					FileWriter myFile = new FileWriter("Fichero1.txt", true);
					myFile.write(desc + "  ");
					myFile.write(prize + "  " + "\r\n");
					lblFavorable.setText("FAVORABLE");
					myFile.close();
					//Esta llamada al método clearTextField hará que los campos de texto se vacíen al pulsar
					//el botón Alta
					clearTextField();
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
				// Lllamamos al método para limpiar el Text Area
				cleaNTxtAreaResult(null);
				// Llamamos al método para mostrar el contenido del fichero
				showInfo();
			}
		});
		btnShowProd.setBackground(new Color(102, 153, 153));
		btnShowProd.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnShowProd.setBounds(26, 423, 213, 31);
		contentPane.add(btnShowProd);

		txtAreaResult = new JTextArea();
		txtAreaResult.setFont(new Font("Arial", Font.PLAIN, 20));
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

	// Este método creará el fichero cuando se le llame desde el btnAlta
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

	// Este método imprimirá todo aquello guardado en el fichero en la textArea
	// cuando se le llame desde el btnShowProd
	private void showInfo() {
		try {
			File myFile = new File("Fichero1.txt");
			BufferedReader br = new BufferedReader(new FileReader(myFile));
			String line;
			while ((line = br.readLine()) != null) {
				setTxtAreaResult(line);
				System.out.println(line);
			}
			br.close();
		} catch (IOException e) {
			System.out.println("Error");
			e.printStackTrace();
		}
	}

	public JTextField getTxtDescription() {
		return txtDescription;
	}

	public JTextField getTxtPrize() {
		return txtPrize;
	}
	
	//Este método vaciará los JTextField al ser llamado
	private void clearTextField() {
		txtDescription.setText("");
		txtPrize.setText("");
	}

	// Este método vaciará el Text Area al ser llamado
	public void cleaNTxtAreaResult(String txtAreaResult) {
		this.txtAreaResult.setText("");
	}

	// Este método imprimirá todas las líneas del fichero en el Text Area al ser
	// llamado
	public void setTxtAreaResult(String txtAreaResult) {
		this.txtAreaResult.append(txtAreaResult + "\n");
	}

}
