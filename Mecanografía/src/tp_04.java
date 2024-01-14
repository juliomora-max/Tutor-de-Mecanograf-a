
public class tp_04 {

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
		import java.util.List;
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
		    private JLabel limpiarLabel;
		    private JLabel enterLabel;

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

		        agregarPangramas();
		        cargarPangramas();

		        JPanel keyboardPanel = new JPanel(new GridLayout(4, 10));
		        String[] buttonLabels = {"1", "2", "3", "4", "5", "6", "7", "8", "9",
		                "0", "q", "w", "e", "r", "t", "y", "u", "i",
		                "o", "p", "a", "s", "d", "f", "g", "h", "j",
		                "k", "l", "ñ", "z", "x", "c", "v", "b", "n",
		                "m", "Limpiar", "Enter", ""};

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

		        addWindowListener(new WindowAdapter() {
		            @Override
		            public void windowClosing(WindowEvent e) {
		                guardarPangramas();
		                dispose();
		            }
		        });

		        // Solicitar el foco para la ventana principal
		        addComponentListener(new ComponentAdapter() {
		            @Override
		            public void componentShown(ComponentEvent e) {
		                textArea.requestFocusInWindow();
		            }
		        });

		        setVisible(true);

		        cambiarPangramaAleatorio();

		        // Agregar un DocumentListener al JTextArea para rastrear cambios
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

		            // Obtener el estado de la tecla Shift presionada usando KeyEvent
		            boolean isShiftPressed = (e.getModifiers() & ActionEvent.SHIFT_MASK) != 0;

		            // Manejar la tecla "Enter" y "Limpiar"
		            if ("Enter".equals(buttonText)) {
		                textArea.append("\n"); // Añadir un salto de línea en lugar de la letra
		            } else if ("Limpiar".equals(buttonText)) {
		                textArea.setText(""); // Limpiar el área de texto
		            } else {
		                // Agregar la letra en mayúscula o minúscula según la tecla Shift
		                textArea.append(isShiftPressed ? buttonText.toUpperCase() : buttonText);
		            }
		        }
		    }
		    private void agregarPangramas() {
		        pangramas.add("Un jugoso zumo de piña y kiwi bien frío es exquisito y no lleva alcohol. Benjamín pidió una bebida de kiwi y fresa. Noé, sin vergüenza, la más exquisita champaña del menú.");
		        pangramas.add("Benjamín pidió una bebida de kiwi y fresa. Noé, sin vergüenza, la más exquisita champaña del menú.");
		        pangramas.add("Jovencillo emponzoñado de whisky, ¡qué mala figurota exhibes!");
		        pangramas.add("El veloz murciélago hindú comía feliz cardillo y kiwi. La cigüeña tocaba el saxofón detrás del palenque de paja.");
		        pangramas.add("Sphinx of black quartz, judge my vow.");
		        pangramas.add("Pack my box with five dozen liquor jugs.");
		        pangramas.add("Mr Jock, TV quiz PhD, bags few lynx.");
		        pangramas.add("Cwm fjord bank glyphs vext quiz.");
		        pangramas.add("Foxy parsons quiz and cajole the lovably dim wiki girl.");
		        pangramas.add("Vamp fox held quartz duck just by wing.");
		        pangramas.add("Bawds jog, flick quartz, vex nymphs.");
		        pangramas.add("Waltz, bad nymph, for quick jigs vex.");
		        pangramas.add("Quick wafting zephyrs vex bold Jim.");
		        pangramas.add("Sphinx of black quartz, judge my vow.");
		        pangramas.add("How razorback-jumping frogs can level six piqued gymnasts!");
		        pangramas.add("Blowzy night-frumps vex'd Jack Q.");
		        pangramas.add("Jinxed wizards pluck ivy from the big quilt.");
		        pangramas.add("Big July earthquakes confound zany experimental vow.");
		        pangramas.add("Pack my box with five dozen liquor jugs.");
		        pangramas.add("The five boxing wizards jump quickly.");

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
		                e.printStackTrace(); // Manejo de errores, puedes personalizar según tus necesidades
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

		        public static void main(String[] args) {
		            SwingUtilities.invokeLater(() -> {
		                new pangramasWriter();
		            });
		        }
		    }
	}

}
