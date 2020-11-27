package vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import controlador.GestorEventos;

public class VentanaPrincipal extends JFrame{
	
	//Creamos los componentes (etiquetas, campos y botones)
	private JLabel nombreApp, logo, operando1, operando2, etiquetaResultado;
	private JTextField caja1, caja2;
	private JButton sumar, restar, multiplicar, dividir, resetear, raizCuadrada, raizCubica;
	
	//Constructor de la ventana
	public VentanaPrincipal () {
		setSize(500, 600);
		setLocationRelativeTo(null); //setBounds (x,y,w,h) este m�todo implementa setSize+setLocation
		setDefaultCloseOperation(EXIT_ON_CLOSE);  //Cierra la app por defecto
		setResizable(false); //No permite redimensionar la ventana
		setTitle("Calculadora"); //Titulo de ventana
		setIconImage(Toolkit.getDefaultToolkit().getImage("images/bat_cabezawhite.jpg")); //Icono de calculadora
		setLayout(null);
		inicializarComponentes();
		setVisible(true);		
	}

	//Método que inicializa todos los componentes (etiquetas, campos...)
	private void inicializarComponentes() {
		
		//Fondo de color negro
		getContentPane().setBackground(new Color (0, 0, 0));
				
		//AÑADIR CABEZA
		Image img = new ImageIcon("images/bat_cabezablack.jpg").getImage();
		logo=new JLabel (new ImageIcon(img.getScaledInstance(80, 80, Image.SCALE_SMOOTH)));
		logo.setBounds(50, 20, 80, 80);
		add(logo);
		
		try {
			Font font = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("voice.otf"));
			
			nombreApp=new JLabel ("BATCALCULATOR");
			nombreApp.setHorizontalAlignment(SwingConstants.CENTER);
			nombreApp.setFont(font.deriveFont(Font.BOLD, 40f));
			nombreApp.setBounds(120, 20, 350, 80);
			nombreApp.setForeground(Color.WHITE);
			add(nombreApp);
		
			//Label Operando 1
			operando1=new JLabel("Operando 1");
			operando1.setBounds(120, 110, 80, 40);
			operando1.setFont(font.deriveFont(Font.BOLD, 13f));
			operando1.setForeground(Color.WHITE);
			add(operando1);
			
			//Campo Operando2
			caja1=new JTextField();
			caja1.setBounds(270, 110, 70, 40);
			caja1.setFont(new Font ("Dialog", Font.BOLD, 14));
			add(caja1);
			
			//Label Operando 2
			operando2=new JLabel("Operando 2");
			operando2.setBounds(120, 160, 80, 40);
			operando2.setFont(font.deriveFont(Font.BOLD, 13f));
			operando2.setForeground(Color.WHITE);
			add(operando2);
			
			//Campo Operando 2
			caja2=new JTextField();
			caja2.setBounds(270, 160, 70, 40);
			caja2.setFont(new Font ("Dialog", Font.BOLD, 14));
			add(caja2);
			
			//Boton suma
			sumar=new JButton("+");
			sumar.setBounds(120, 230, 70, 40);
			sumar.setFont(new Font ("Dialog", Font.BOLD, 19));
			sumar.setBackground(new Color (255, 214, 0));
			//sumar.setBackground(Color.WHITE);
			add(sumar);
			
			//Boton resta
			restar=new JButton("-");
			restar.setBounds(200, 230, 70, 40);
			restar.setFont(new Font ("Dialog", Font.BOLD, 19));
			restar.setBackground(new Color (255, 214, 0));
			//restar.setBackground(Color.WHITE);
			add(restar);
			
			//Boton raiz cuadrada
			raizCuadrada=new JButton("√");
			raizCuadrada.setBounds(280, 230, 70, 40);
			raizCuadrada.setFont(new Font ("Dialog", Font.BOLD, 19));
			raizCuadrada.setBackground(new Color (255, 214, 0));
			//raizCuadrada.setBackground(Color.WHITE);
			add(raizCuadrada);
			
			//Boton multiplicar
			multiplicar=new JButton("x");
			multiplicar.setBounds(120, 310, 70, 40);
			multiplicar.setFont(new Font ("Dialog", Font.BOLD, 19));
			multiplicar.setBackground(new Color (255, 214, 0));
			//multiplicar.setBackground(Color.WHITE);
			add(multiplicar);
			
			//Boton division
			dividir=new JButton("/");
			dividir.setBounds(200, 310, 70, 40);
			dividir.setFont(new Font ("Dialog", Font.BOLD, 19));
			dividir.setBackground(new Color (255, 214, 0));
			//dividir.setBackground(Color.WHITE);
			add(dividir);
			
			//Boton raiz cuadrada
			raizCubica=new JButton("∛");
			raizCubica.setBounds(280, 310, 70, 40);
			raizCubica.setFont(new Font ("Dialog", Font.BOLD, 19));
			raizCubica.setBackground(new Color (255, 214, 0));
			//raizCubica.setBackground(Color.WHITE);
			add(raizCubica);
			
			//Label resultado
			etiquetaResultado=new JLabel();
			etiquetaResultado.setBounds(100, 370, 270, 40);
			etiquetaResultado.setFont(font.deriveFont(Font.BOLD, 15f));
			etiquetaResultado.setForeground(Color.WHITE);
			etiquetaResultado.setHorizontalAlignment(SwingConstants.CENTER);
			add(etiquetaResultado);
			
			//Boton reset (CE)
			resetear=new JButton("CE");
			resetear.setBounds(210, 460, 50, 40);
			resetear.setFont(font.deriveFont(Font.BOLD, 13f));
			resetear.setBackground(new Color (255, 214, 0));
			resetear.setEnabled(false);
			add(resetear);
		
		} catch (FontFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//Añadir acciones al gestor de eventos
	public void establecerGestor(GestorEventos gestor) {
		sumar.addActionListener(gestor);
		restar.addActionListener(gestor);
		multiplicar.addActionListener(gestor);
		dividir.addActionListener(gestor);
		resetear.addActionListener(gestor);
		raizCuadrada.addActionListener(gestor);
		raizCubica.addActionListener(gestor);
	}
	
	//Getters
	public JTextField getCaja1() {
		return caja1;
	}

	public JTextField getCaja2() {
		return caja2;
	}
		
	public JButton getSumar() {
		return sumar;
	}

	public JButton getRestar() {
		return restar;
	}

	public JButton getMultiplicar() {
		return multiplicar;
	}

	public JButton getDividir() {
		return dividir;
	}

	public JButton getRaizCuadrada() {
		return raizCuadrada;
	}

	public JButton getRaizCubica() {
		return raizCubica;
	}

	public JButton getResetear() {
		return resetear;
	}

	public JLabel getEtiquetaResultado() {
		return etiquetaResultado;
	}
}
