
public class tp_02 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		import javax.swing.*;
		import java.awt.*;
		import java.awt.event.ActionEvent;
		import java.awt.event.ActionListener;
		import java.util.ArrayList;
		import java.util.List;

		public class pangramasWriter extends JFrame {

		    private final JTextArea textArea;

		    public pangramasWriter() {
		        // Configurar la ventana principal
		        setTitle("PangramasWriter");
		        setSize(600, 400);
		        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		        setLayout(new BorderLayout());

		        // Crear el área de texto
		        textArea = new JTextArea();
		        textArea.setEditable(true);
		        add(new JScrollPane(textArea), BorderLayout.CENTER);

		        // Crear el panel de teclado virtual
		        JPanel tecladoPanel = new JPanel(new GridLayout(4, 3));
		        String[] teclas = {"1", "2", "3", "4", "5", "6", "7", "8", "9",
		                "0", "q", "w", "e", "r", "t", "y", "u", "i",
		                "o", "p", "a", "s", "d", "f", "g", "h", "j",
		                "k", "l", "ñ", "z", "x", "c", "v", "b", "n",
		                "m", "Limpiar", "Enter"};

		        // Configurar el ActionListener para las teclas del teclado virtual
		        for (String tecla : teclas) {
		            JButton button = new JButton(tecla);
		            button.addActionListener(new TeclaActionListener());
		            tecladoPanel.add(button);
		        }

		        // Agregar el panel de teclado virtual a la ventana
		        add(tecladoPanel, BorderLayout.SOUTH);

		        // Agregar los pangramas al área de texto
		        List<String> pangramas = obtenerPangramas();
		        for (String pangrama : pangramas) {
		            textArea.append(pangrama + "\n");
		        }

		        // Hacer visible la ventana
		        setVisible(true);
		    }

		    // ActionListener para las teclas del teclado virtual
		    private class TeclaActionListener implements ActionListener {
		        @Override
		        public void actionPerformed(ActionEvent e) {
		            JButton source = (JButton) e.getSource();
		            String textoTecla = source.getText();

		            if (textoTecla.equals("Limpiar")) {
		                textArea.setText("");
		            } else if (textoTecla.equals("Enter")) {
		                textArea.append("\n");
		            } else {
		                textArea.append(textoTecla);
		            }
		        }
		    }

		    // Método para obtener los pangramas directamente desde el código
		    private List<String> obtenerPangramas() {
		        List<String> pangramas = new ArrayList<>();
		        pangramas.add("Un jugoso zumo de piña y kiwi bien frío es exquisito y no lleva alcohol Benjamín pidió una bebida de kiwi y fresa. Noé, sin vergüenza, la más exquisita champaña del menú.");
		        pangramas.add("Benjamín pidió una bebida de kiwi y fresa. Noé, sin vergüenza, la más exquisita champaña del menú.");
		        pangramas.add("Jovencillo emponzoñado de whisky: ¡qué figurota exhibe!");
		        pangramas.add("José compró en Perú una vieja zampoña. Excusándose, Sofía tiró su whisky al desagüe de la banqueta.");
		        pangramas.add("El veloz murciélago hindú comía feliz cardillo y kiwi. La cigüeña tocaba el saxofón detrás del palenque de paja.");
		        pangramas.add("El pingüino Wenceslao hizo kilómetros bajo exhaustiva lluvia y frío, añoraba a su querido cachorro.");
		        pangramas.add("Exhíbanse politiquillos zafios, con orejas kilométricas y uñas de gavilán");
		        pangramas.add("¡Exijo hablar de un pequeño y fugaz armisticio en Kuwait! ¿Vale?");
		        pangramas.add("Le gustaba cenar un exquisito sándwich de jamón con zumo de piña y vodka fría.");
		        pangramas.add("El jefe buscó el éxtasis en un imprevisto baño de whisky y gozó como un duque. ");
		        pangramas.add("El viejo Señor Gómez pedía queso, kiwi y habas, pero le ha tocado un saxofón.");
		        pangramas.add("La cigüeña tocaba cada vez mejor el saxofón y el búho pedía kiwi y queso.");
		        pangramas.add("El jefe que goza con un imprevisto busca el éxtasis en un baño de whisky. ");
		        pangramas.add("Borja quiso el extraño menú de gazpacho, kiwi, uva y flan.");
		        pangramas.add("El extraño whisky quemó como fuego la boca del joven López.");
		        pangramas.add("Fidel exporta gazpacho, jamón, kiwi, viñas y buques.");
		        pangramas.add("La vieja cigüeña fóbica quiso empezar hoy un éxodo a Kuwait.");
		        pangramas.add("Queda gazpacho, fibra, látex, jamón, kiwi y viñas.");
		        pangramas.add("El cadáver de Wamba, rey godo de España, fue exhumado y trasladado en una caja de zinc que pesó un kilo.");
		        pangramas.add("Tengo un libro de papiroflexia sobre las hazañas y aventuras de Don Quijote de la Mancha en Kuwait.");
		        pangramas.add("El viejo juez interpretó hoy «La cabalgata de las Walkirias» tocando un xilófono con el meñique.");

		        return pangramas;
		    }

		    public static void main(String[] args) {
		        // Crear una instancia de la aplicación
		        SwingUtilities.invokeLater(pangramasWriter::new);
		    }
		}
	}

}
