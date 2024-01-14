
public class tp_06 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		import javax.swing.*;
		import javax.swing.event.DocumentEvent;
		import javax.swing.event.DocumentListener;
		import java.awt.*;
		import java.awt.event.*;
		import java.io.*;
		import java.nio.charset.StandardCharsets;
		import java.util.ArrayList;
		import java.util.HashMap;
		import java.util.List;
		import java.util.Map;
		import java.util.Random;

		public class pangramasWriter extends JFrame {
		    private JTextArea textArea;
		    private JTextArea pangramArea;
		    private JLabel pangramaSeleccionadoLabel;
		    private JLabel pulsacionesCorrectasLabel;
		    private JLabel pulsacionesIncorrectasLabel;
		    private List<String> pangramas;
		    private Random random;
		    private int pulsacionesCorrectas;
		    private int pulsacionesIncorrectas;
		    private String pangramaActual;
		    private int contadorEspaciosConsecutivos;
		    private boolean shiftPressed;
		    private Map<Character, Integer> correctasPorTecla;
		    private Map<Character, Integer> incorrectasPorTecla;

		    public pangramasWriter() {
		        super("pangramasWriter");
		        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		        pangramas = new ArrayList<>();
		        random = new Random();
		        pulsacionesCorrectas = 0;
		        pulsacionesIncorrectas = 0;
		        pangramaActual = "";
		        contadorEspaciosConsecutivos = 0;
		        shiftPressed = false;
		        correctasPorTecla = new HashMap<>();
		        incorrectasPorTecla = new HashMap<>();

		        agregarPangramas();
		        cargarPangramas();

		        JPanel keyboardPanel = new JPanel(new GridLayout(4, 10));
		        String[] buttonLabels = {
		                "1", "2", "3", "4", "5", "6", "7", "8", "9",
		                "0", "q", "w", "e", "r", "t", "y", "u", "i",
		                "o", "p", "a", "s", "d", "f", "g", "h", "j",
		                "k", "l", "ñ", "z", "x", "c", "v", "b", "n",
		                "m", " ", "Limpiar", "Enter"
		        };

		        for (String label : buttonLabels) {
		            JButton button = new JButton(label);
		            button.addActionListener(new KeyboardButtonListener());
		            keyboardPanel.add(button);
		        }

		        textArea = new JTextArea();
		        textArea.setEditable(true);

		        pangramArea = new JTextArea();
		        pangramArea.setEditable(true);
		        mostrarPangramas();

		        pangramaSeleccionadoLabel = new JLabel();
		        pangramaSeleccionadoLabel.setHorizontalAlignment(JLabel.CENTER);
		        pangramaSeleccionadoLabel.setFont(new Font("Arial", Font.BOLD, 16));

		        pulsacionesCorrectasLabel = new JLabel("Pulsaciones correctas: 0");
		        pulsacionesIncorrectasLabel = new JLabel("Pulsaciones incorrectas: 0");

		        setLayout(new BorderLayout());
		        add(keyboardPanel, BorderLayout.CENTER);
		        add(new JScrollPane(textArea), BorderLayout.SOUTH);
		        add(new JScrollPane(pangramArea), BorderLayout.NORTH);
		        add(pangramaSeleccionadoLabel, BorderLayout.NORTH);
		        add(pulsacionesCorrectasLabel, BorderLayout.WEST);
		        add(pulsacionesIncorrectasLabel, BorderLayout.EAST);

		        setSize(800, 400);
		        setLocationRelativeTo(null);

		        addWindowListener(new CustomWindowAdapter());

		        addComponentListener(new ComponentAdapter() {
		            @Override
		            public void componentShown(ComponentEvent e) {
		                textArea.requestFocusInWindow();
		            }
		        });

		        setVisible(true);

		        cambiarPangramaAleatorio();

		        textArea.getDocument().addDocumentListener(new DocumentListener() {
		            @Override
		            public void insertUpdate(DocumentEvent e) {
		                actualizarContadores();
		            }

		            @Override
		            public void removeUpdate(DocumentEvent e) {
		                actualizarContadores();
		            }

		            @Override
		            public void changedUpdate(DocumentEvent e) {
		                // No es relevante para un JTextArea simple
		            }
		        });
		    }

		    private class KeyboardButtonListener implements ActionListener {
		        @Override
		        public void actionPerformed(ActionEvent e) {
		            JButton sourceButton = (JButton) e.getSource();
		            String buttonText = sourceButton.getText();
		            char tecla = buttonText.charAt(0);

		            boolean isShiftPressed = (e.getModifiers() & ActionEvent.SHIFT_MASK) != 0;

		            if ("Limpiar".equals(buttonText)) {
		                textArea.setText("");
		                contadorEspaciosConsecutivos = 0;
		                return;
		            } else if ("Enter".equals(buttonText)) {
		                textArea.append("\n");
		                contadorEspaciosConsecutivos = 0;
		            } else {
		                textArea.append(isShiftPressed ? buttonText.toUpperCase() : buttonText);

		                if (" ".equals(buttonText)) {
		                    contadorEspaciosConsecutivos++;
		                } else {
		                    contadorEspaciosConsecutivos = 0;
		                }
		            }

		            if (contadorEspaciosConsecutivos > 3) {
		                cambiarPangramaAleatorio();
		            }

		            actualizarRecuentoTeclas(tecla, isTeclaCorrecta(tecla));
		        }

		        private boolean isTeclaCorrecta(char tecla) {
		            String textoIngresado = textArea.getText();
		            String pangramaSeleccionado = pangramaActual;
		            int posicion = textoIngresado.length() - 1;

		            return (posicion < pangramaSeleccionado.length() &&
		                    textoIngresado.charAt(posicion) == pangramaSeleccionado.charAt(posicion));
		        }

		        private void actualizarRecuentoTeclas(char tecla, boolean esCorrecta) {
		            if (esCorrecta) {
		                correctasPorTecla.put(tecla, correctasPorTecla.getOrDefault(tecla, 0) + 1);
		            } else {
		                incorrectasPorTecla.put(tecla, incorrectasPorTecla.getOrDefault(tecla, 0) + 1);
		            }
		        }
		    }

		    private class CustomWindowAdapter extends WindowAdapter {
		        @Override
		        public void windowClosing(WindowEvent e) {
		            guardarPangramas();
		            mostrarInformeFinal();
		            dispose();
		        }
		    }

		    private void agregarPangramas() {
		        pangramas.add("El veloz murciélago hindú comía feliz cardillo y kiwi.");
		        pangramas.add("La cigüeña tocaba el saxofón detrás del palenque de paja.");
		        pangramas.add("Joven alumno: ¿quiere estudiar este poema?");
		        // Agregar más pangramas según tus preferencias.
		    }

		    private void cargarPangramas() {
		        // Implementación opcional para cargar pangramas desde un archivo
		    }

		    private void mostrarPangramas() {
		        StringBuilder pangramasTexto = new StringBuilder();
		        for (String pangrama : pangramas) {
		            pangramasTexto.append(pangrama).append("\n");
		        }
		        pangramArea.setText(pangramasTexto.toString());
		    }

		    private void guardarPangramas() {
		        try (BufferedWriter writer = new BufferedWriter(new FileWriter("pangramas.txt", StandardCharsets.UTF_8))) {
		            for (String pangrama : pangramas) {
		                writer.write(pangrama);
		                writer.newLine();
		            }
		        } catch (IOException e) {
		            e.printStackTrace();
		        }
		    }

		    private void cambiarPangramaAleatorio() {
		        contadorEspaciosConsecutivos = 2;
		        pangramaActual = pangramas.get(random.nextInt(pangramas.size()));
		        pangramaSeleccionadoLabel.setText("Pangrama seleccionado: " + pangramaActual);
		    }

		    private void actualizarContadores() {
		        String textoIngresado = textArea.getText();
		        String pangramaSeleccionado = pangramaActual;

		        pulsacionesCorrectas = contarPulsacionesCorrectas(textoIngresado, pangramaSeleccionado);
		        pulsacionesIncorrectas = contarPulsacionesIncorrectas(textoIngresado, pangramaSeleccionado);

		        pulsacionesCorrectasLabel.setText("Pulsaciones correctas: " + pulsacionesCorrectas);
		        pulsacionesIncorrectasLabel.setText("Pulsaciones incorrectas: " + pulsacionesIncorrectas);
		    }

		    private int contarPulsacionesCorrectas(String textoIngresado, String pangramaSeleccionado) {
		        int minLen = Math.min(textoIngresado.length(), pangramaSeleccionado.length());
		        int correctas = 0;

		        for (int i = 0; i < minLen; i++) {
		            if (textoIngresado.charAt(i) == pangramaSeleccionado.charAt(i)) {
		                correctas++;
		            }
		        }

		        return correctas;
		    }

		    private int contarPulsacionesIncorrectas(String textoIngresado, String pangramaSeleccionado) {
		        int minLen = Math.min(textoIngresado.length(), pangramaSeleccionado.length());
		        int incorrectas = 0;

		        for (int i = 0; i < minLen; i++) {
		            if (textoIngresado.charAt(i) != pangramaSeleccionado.charAt(i)) {
		                incorrectas++;
		            }
		        }

		        return incorrectas;
		    }

		    private void mostrarInformeFinal() {
		        StringBuilder informe = new StringBuilder();
		        informe.append("----- Informe Final -----\n");
		        informe.append("Pulsaciones correctas: ").append(pulsacionesCorrectas).append("\n");
		        informe.append("Pulsaciones incorrectas: ").append(pulsacionesIncorrectas).append("\n");

		        informe.append("----- Recuento de Teclas -----\n");
		        informe.append("Teclas correctas:\n");
		        for (Map.Entry<Character, Integer> entry : correctasPorTecla.entrySet()) {
		            informe.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
		        }

		        informe.append("Teclas incorrectas:\n");
		        for (Map.Entry<Character, Integer> entry : incorrectasPorTecla.entrySet()) {
		            informe.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
		        }

		        JOptionPane.showMessageDialog(
		                this,
		                informe.toString(),
		                "Informe Final",
		                JOptionPane.INFORMATION_MESSAGE
		        );
		    }

		    public static void main(String[] args) {
		        SwingUtilities.invokeLater(() -> {
		            new pangramasWriter();
		        });
		    }
		}
	}

}
