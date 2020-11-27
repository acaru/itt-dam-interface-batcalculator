package controlador;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import vista.VentanaPrincipal;

public class GestorEventos implements ActionListener {
	
	//Instanciamos un objeto VentanaPrincipal y los valores de las cajas
	private VentanaPrincipal ventana;
	private double caja1, caja2;
	
	//Al constructor le pasamos por parámetro la ventana creada
	public GestorEventos(VentanaPrincipal ventana) {
		this.ventana = ventana;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {

			//Activamos el botón CE
			ventana.getResetear().setEnabled(true);
						
			try {				
				//Método del botón sumar
				if(e.getSource()==ventana.getSumar() && !esVacio(e)) {
					caja1 = Double.parseDouble(ventana.getCaja1().getText());
					caja2 = Double.parseDouble(ventana.getCaja2().getText());
					double suma = caja1+caja2;
					ventana.getEtiquetaResultado().setText("Resultado: " + String.valueOf(suma));
					ReproducirSonido("batman-ringtones.wav");
				}
				
				//Método del botón restar
				if(e.getSource()==ventana.getRestar() && !esVacio(e)){
					caja1 = Double.parseDouble(ventana.getCaja1().getText());
					caja2 = Double.parseDouble(ventana.getCaja2().getText());
					double resta = caja1-caja2;
					ventana.getEtiquetaResultado().setText("Resultado: " + String.valueOf(resta));
					ReproducirSonido("batman-ringtones.wav");
				}
				
				//Método del botón multiplicar
				if(e.getSource()==ventana.getMultiplicar() && !esVacio(e)) {
					caja1 = Double.parseDouble(ventana.getCaja1().getText());
					caja2 = Double.parseDouble(ventana.getCaja2().getText());
					double multiplicacion = caja1*caja2;
					ventana.getEtiquetaResultado().setText("Resultado: " + String.valueOf(multiplicacion));
					ReproducirSonido("batman-ringtones.wav");
				}
				
				//Método del botón dividir
				if(e.getSource()== ventana.getDividir() && !esVacio(e)) {
					caja1 = Double.parseDouble(ventana.getCaja1().getText());
					caja2 = Double.parseDouble(ventana.getCaja2().getText());

					//Si no dividimos por 0 entonces realiza la división si no nos avisa de que es un error
					if (caja2 != 0) {
						double division = caja1/caja2;
						ventana.getEtiquetaResultado().setText("Resultado: " + String.valueOf(division));
						ReproducirSonido("batman-ringtones.wav");
					}else {
						ventana.getEtiquetaResultado().setText("Error al dividir por 0");
						ventana.getEtiquetaResultado().setForeground(Color.RED);
						ventana.getCaja2().requestFocus();
					}
				}
				
				//Método del botón raíz cuadrada
				if(e.getSource()==ventana.getRaizCuadrada()) {
					ReproducirSonido("joker.wav");
					JOptionPane.showMessageDialog(null, "Funcionalidad no disponible", "Alert!", JOptionPane.ERROR_MESSAGE);
					ventana.getCaja1().requestFocus();
				}
				
				//Método del botón raíz cúbica
				if(e.getSource()==ventana.getRaizCubica() && !esVacio(e)) {
					caja1 = Double.parseDouble(ventana.getCaja1().getText());
					ReproducirSonido("joker.wav");
					
					//Panel de contraseña
					JPanel panel = new JPanel();
									
					JLabel etiqueta = new JLabel("Contraseña:");
					panel.add(etiqueta);
					
					//Campo de contraseña
					JPasswordField cajaPass = new JPasswordField(10);
					panel.add(cajaPass);
					
					//Contraseña de la función de raíz cúbica
					String pass = "batmovil";
					
					String[] opciones = new String[]{"OK", "Cancel"};
					
					int opcion = JOptionPane.showOptionDialog(null, panel, "Password!", JOptionPane.NO_OPTION, JOptionPane.WARNING_MESSAGE, null, opciones, opciones[1]);
					
					if(opcion == 0){ // botón OK 
					    char[] password = cajaPass.getPassword();
					    if (pass.equals(String.valueOf(password))){
							ventana.setTitle("PASSWORD CORRECTA");
							double cubica = Math.cbrt(caja1);
							ventana.getEtiquetaResultado().setText("Resultado: " + cubica);
							ReproducirSonido("batman-ringtones.wav");
					    } else {
					    	ventana.setTitle("VUELVE A INTENTARLO");
							ventana.getEtiquetaResultado().setText("Password incorrecta");
							ventana.getEtiquetaResultado().setForeground(Color.RED);
							ReproducirSonido("joker.wav");
					    }
					    ventana.getCaja1().requestFocus();
					}else {
						ventana.getCaja1().requestFocus();
					}
				}
							
			//Capturamos si es null o cero y que obligatoriamente sean números, no letras
			} catch (NumberFormatException ex) {
				ventana.getEtiquetaResultado().setText("Por favor, indica datos correctos");
				ventana.getEtiquetaResultado().setForeground(Color.RED);
				ventana.getCaja1().requestFocus();
				
				if (esNumerico(ventana.getCaja1().getText())) {
					ventana.getCaja2().requestFocus();
				} else {
					ventana.getCaja1().requestFocus();
				}				
				// ex.printStackTrace();
			}
					
			//Método del botón resetear
			if(e.getSource()==ventana.getResetear()) {
				ventana.getCaja1().setText("");
				ventana.getCaja2().setText("");
				ventana.getCaja1().requestFocus();
				ventana.getCaja2().setVisible(true);
				ventana.getResetear().setEnabled(false);
				ventana.getEtiquetaResultado().setText("");
				ReproducirSonido("batman-sound.wav");
			}		
	}	
	
	//Método que comprueba que los campos no estén vacíos
	private boolean esVacio (ActionEvent e) {
		if (e.getSource() == ventana.getRaizCubica()) {
			if(ventana.getCaja1().getText().isEmpty()) {
				ventana.getEtiquetaResultado().setText("Rellena operando 1");
				ventana.getEtiquetaResultado().setForeground(Color.RED);
				ventana.getCaja2().setText("");
				ventana.getCaja1().requestFocus();
				return true;
			}
			
		} else {
			if(ventana.getCaja1().getText().isEmpty()) {
				ventana.getEtiquetaResultado().setText("Operandos vacios");
				ventana.getEtiquetaResultado().setForeground(Color.RED);
				ventana.getCaja1().requestFocus();
				return true;
			}else if (ventana.getCaja2().getText().isEmpty()) {
				ventana.getEtiquetaResultado().setText("Operandos vacios");
				ventana.getEtiquetaResultado().setForeground(Color.RED);
				ventana.getCaja2().requestFocus();
				return true;
			}			
		}
		return false;
	}
	
	
	
	//Método booleano que comprueba que sea númerica la cadena
	private boolean esNumerico(String cadena){
		try {
			Double.parseDouble(cadena);
			return true;
		} catch (NumberFormatException e){
			return false;
		}
	}
	
	//Método para reproducir sonidos
	public void ReproducirSonido(String nombreSonido){
	    try {
		    AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(nombreSonido).getAbsoluteFile());
		    Clip clip = AudioSystem.getClip();
		    clip.open(audioInputStream);
		    clip.start();
	    } catch(UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
	    	ventana.getEtiquetaResultado().setText("Error al reproducir el sonido.");
	    	ventana.getCaja1().requestFocus();
	    }
	}
}
