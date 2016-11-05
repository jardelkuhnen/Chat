package br.univel.chat.view;

import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Display extends JFrame {

	private JPanel contentPane;
	private JTextField txtDestinatario;
	private JTextField txtMensagem;
	private JList<String> lstMensagem = new JList();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Display frame = new Display();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Display() {
		setTitle("Chat TADS");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 411);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 0, 0, 0 };
		gbl_contentPane.rowHeights = new int[] { 0, 0, 0, 0 };
		gbl_contentPane.columnWeights = new double[] { 0.0, 1.0,
				Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 1.0, 0.0, 0.0,
				Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);

		lstMensagem.setModel(new MensagemListModel(new ArrayList<>()));
		GridBagConstraints gbc_lstMensagem = new GridBagConstraints();
		gbc_lstMensagem.gridwidth = 2;
		gbc_lstMensagem.insets = new Insets(0, 0, 5, 0);
		gbc_lstMensagem.fill = GridBagConstraints.BOTH;
		gbc_lstMensagem.gridx = 0;
		gbc_lstMensagem.gridy = 0;
		contentPane.add(lstMensagem, gbc_lstMensagem);

		JLabel lblNewLabel = new JLabel("Enviar para:");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 1;
		contentPane.add(lblNewLabel, gbc_lblNewLabel);

		txtDestinatario = new JTextField();
		GridBagConstraints gbc_txtDestinatario = new GridBagConstraints();
		gbc_txtDestinatario.insets = new Insets(0, 0, 5, 0);
		gbc_txtDestinatario.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtDestinatario.gridx = 1;
		gbc_txtDestinatario.gridy = 1;
		contentPane.add(txtDestinatario, gbc_txtDestinatario);
		txtDestinatario.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Mensagem");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 2;
		contentPane.add(lblNewLabel_1, gbc_lblNewLabel_1);

		txtMensagem = new JTextField();
		GridBagConstraints gbc_txtMensagem = new GridBagConstraints();
		gbc_txtMensagem.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtMensagem.gridx = 1;
		gbc_txtMensagem.gridy = 2;
		contentPane.add(txtMensagem, gbc_txtMensagem);
		txtMensagem.setColumns(10);
		txtMensagem.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {

					DisplayController.getInstancia().enviarMensagem();

				}

			}

		});
	}

	public JList<String> getLstMensagem() {
		return lstMensagem;
	}

	public JTextField getTxtDestinatario() {
		return txtDestinatario;
	}

	public JTextField getTxtMensagem() {
		return txtMensagem;
	}

}
