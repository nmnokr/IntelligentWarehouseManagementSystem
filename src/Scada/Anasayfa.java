package Scada;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.Color;

import java.awt.event.ActionListener;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.event.ActionEvent;

import java.awt.Toolkit;

import dto.Product;
import dto.deneme;
import dto.shelf;

import dao.RfidDAO;
import java.awt.Font;
import java.awt.SystemColor;

public class Anasayfa extends java.applet.Applet {

	Thread runner;
	int xpos = 450;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JFrame frame;

	int i = 0;
	private int xSize, ySize;
	ArrayList<deneme> warehouse = new ArrayList<deneme>();
	RfidDAO dao = new RfidDAO();
	Map<String, List> mapList1 = new HashMap<String, List>();
	Map<String, List> mapList2 = new HashMap<String, List>();
	Map<String, List> mapList3 = new HashMap<String, List>();
	Map<String, List> mapList4 = new HashMap<String, List>();
	JPanel panelDetails;
	JLabel DetailsProductslabel;
	JPanel[] panels = new JPanel[0];;

	/**
	 * Launch the application.
	 */

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Anasayfa window = new Anasayfa();
					window.frame.setVisible(true);
					System.out.println("dassssssssssss");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * 
	 * @throws Exception
	 */
	public Anasayfa() throws Exception {

		initialize();

		/*
		 * while(true == true) {
		 * 
		 * double mouseX = MouseInfo.getPointerInfo().getLocation().getX(); double
		 * mouseY = MouseInfo.getPointerInfo().getLocation().getY();
		 * System.out.println("X:" + mouseX); System.out.println("Y:" + mouseY);
		 * Thread.sleep(2000);
		 */
		// make sure to import

	}

	/**
	 * Initialize the contents of the frame.
	 * 
	 * @throws Exception
	 */
	public void initialize() throws Exception {

		frame = new JFrame();

		Toolkit tk = Toolkit.getDefaultToolkit();
		xSize = ((int) tk.getScreenSize().getWidth());
		ySize = ((int) tk.getScreenSize().getHeight());
		setSize(xSize, ySize);
		frame.setBounds(0, 0, xSize, ySize);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setBackground(new Color(0, 0, 0));
		frame.getContentPane().setLayout(null);

		panelDetails = new JPanel();
		panelDetails.repaint();
		panelDetails.setBackground(Color.BLACK);
		panelDetails.setBounds(0, 0, 1800, 95);
		frame.getContentPane().add(panelDetails);
		panelDetails.setLayout(null);

		frame.setBackground(Color.BLACK);
		Timer myTimer = new Timer();
		TimerTask gorev = new TimerTask() {

			public void run() {

				DetailsProductslabel = new JLabel("");
				DetailsProductslabel.setBounds(144, 36, 526, 16);
				panelDetails.add(DetailsProductslabel);

				JButton btnNewButton = new JButton("Kapat");
				btnNewButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					}
				});

				// panel boyutu
				int countStok = 0;
				try {
					mapList1 = dao.getAllStatus1();
					countStok = dao.getCountStok();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				System.out.println("....." + countStok);
				int pxx = 300;
				for (int i = 0; i < panels.length; i++) {
					frame.getContentPane().remove(panels[i]);
				}

				panels = new JPanel[countStok];
				for (int i = 0; i < panels.length; i++) {
					panels[i] = new JPanel();

					panels[i].revalidate();
					panels[i].repaint();
					frame.repaint();

					panels[i].setBounds(pxx, 0, 300, 600);
					panels[i].setBackground(new Color(163, 159, 141));
					frame.getContentPane().add(panels[i]);
					pxx += 300;
					try {
						System.out.println("adsffffffffffffffffffff" + "<<<<<<<<<<<<<<" + "silindi");
						getWarehouse(panels[i], 0, 0, i + 1);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				// durum gösterme dongusu

				int countStatusId = 4;

				int temp = 0;
				try {
					temp = pendigproducts(frame, 1);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				for (int i = 2; i < countStatusId; i++) {
					temp = status2(frame, temp, i);
				}
				frame.repaint();

			}
		};

		myTimer.schedule(gorev, 0, 10000);
	}

	public void getWarehouse(JPanel panel, int xbas, int ybas, int index) throws Exception {
		ArrayList<shelf> shelfs = new ArrayList<shelf>();

		shelfs = dao.getShelf(index);

		int xx1 = 50 + xbas;
		int whousesize = shelfs.size() / 2;

		for (int i = 0; i < whousesize; i++) {

			xx1 += 65;

			JButton x = new JButton((String) shelfs.get(i).getRfId());
			x.setBounds(xbas, xx1, 60, 70);
			x.setName(shelfs.get(i).getRfId());
			x.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					System.out.println(x.getName());

					DetailsProductslabel.setText("Ürün Bilgisi" + x.getName());

				}
			});
			x.getName();

			if (mapList1.get(shelfs.get(i).getRfId()) != null) {
				Object value = mapList1.get(shelfs.get(i).getRfId()).get(1);

				x.setForeground(SystemColor.black);
				x.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 15));
				System.out.println(value);
				x.setText(value.toString().substring(0, 1));
				if (Integer.valueOf((String) value) < 3) {
					x.setBackground(Color.blue);
				}

				else if (Integer.valueOf((String) value) == 3) {
					x.setBackground(Color.MAGENTA);
				} else if (Integer.valueOf((String) value) == 4) {
					x.setBackground(Color.GREEN);
				} else if (Integer.valueOf((String) value) == 5) {
					x.setBackground(Color.orange);
				} else if (Integer.valueOf((String) value) == 6) {
					x.setBackground(new Color(252, 49, 80));
					x.setText("X");
				}

			} else {
				x.setBackground(new Color(252, 49, 80));
				x.setText("X");
				x.setForeground(SystemColor.black);
				x.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 15));
			}

			x.setOpaque(true);
			panel.add(x);

			// yatay

			JLabel label_2 = new JLabel("");
			label_2.setOpaque(true);
			label_2.setBackground(Color.BLACK);
			label_2.setBounds(28 + xbas, xx1, 78, 1);
			panel.add(label_2);
			// dikey
			JLabel lblNewLabel = new JLabel("New label");
			lblNewLabel.setBackground(Color.BLACK);
			lblNewLabel.setOpaque(true);

			lblNewLabel.setBounds(xbas - 10, xx1, 8, 110);
			panel.add(lblNewLabel);

		}

		int xx2 = xx1;
		panel.setLayout(null);

		JButton btnNewButton_1 = new JButton("New button");
		btnNewButton_1.setBounds(130 + xbas, xx2, 60, 70);
		btnNewButton_1.setText(shelfs.get(whousesize).getRfId());
		if (shelfs.get(whousesize).isStatus() == true) {
			btnNewButton_1.setBackground(new Color(111, 197, 108));

		} else {
			btnNewButton_1.setBackground(new Color(252, 49, 80));

		}

		btnNewButton_1.setOpaque(true);
		panel.add(btnNewButton_1);
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setBounds(0, 90, 335, 20);
		lblNewLabel_1.setBackground(new Color(0, 0, 0));
		lblNewLabel_1.setOpaque(true);
		panel.add(lblNewLabel_1);

		List<JButton> jlabelList = new ArrayList<JButton>();
		JButton x = null;

		for (int i = whousesize + 1; i < shelfs.size(); i++) {
			x = new JButton("label_" + i);
			x.setName(Integer.toString(i));
			jlabelList.add(x);

			x.setBounds(240, xx2, 60, 70);

			x.setText(shelfs.get(i).getRfId());
			if (mapList1.get(shelfs.get(i).getRfId()) != null) {
				Object value = mapList1.get(shelfs.get(i).getRfId()).get(1);
				x.setBackground(new Color(111, 197, 108));

				System.out.println(value);
				x.setText(value.toString().substring(0, 1));
				x.setForeground(SystemColor.black);
				x.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 15));
			} else {
				x.setBackground(new Color(252, 49, 80));
				x.setText("X");
				x.setForeground(SystemColor.black);
				x.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 15));
			}

			x.setOpaque(true);
			panel.add(x);
			JLabel label_2 = new JLabel("");
			label_2.setOpaque(true);
			label_2.setBackground(Color.BLACK);
			label_2.setBounds(200 + xbas, xx2, 78, 1);
			panel.add(label_2);
			JLabel lblNewLabel = new JLabel("");
			lblNewLabel.setBackground(Color.BLACK);
			lblNewLabel.setOpaque(true);
			lblNewLabel.setBounds(296 + xbas, xx2, 8, 110);
			panel.add(lblNewLabel);
			xx2 -= 65;
			// dikey

		}

	}

	public int pendigproducts(JFrame frame, int countStatusId) throws Exception {
		RfidDAO dao = new RfidDAO();

		ArrayList<Product> products = dao.getStatusId(countStatusId);
		int xpendingproductssize = xSize;
		int ypendingproductssize = ySize;
		int temp = 0;

		for (int i = 1; i <= products.size(); i++) {

			temp++;
			JButton x = new JButton("New label");
			x.setBounds(xpendingproductssize - 100, ypendingproductssize - 200, 50, 60);
			x.setText("" + i);
			x.setOpaque(true);
			x.setBackground(Color.blue);
			x.setForeground(SystemColor.black);
			x.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 15));
			frame.getContentPane().add(x);
			xpendingproductssize -= 50;

			if (temp == 9) {

				xpendingproductssize = xSize;
				ypendingproductssize = ySize - 60;
				temp = 0;
			}

		}
		JLabel lblNewLabel_2 = new JLabel("Depoda Beklemede Olan Urunler");
		lblNewLabel_2.setForeground(new Color(161, 161, 86));

		lblNewLabel_2.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 18));
		lblNewLabel_2.setBounds(xpendingproductssize - 200, ypendingproductssize - 260, xpendingproductssize - 100, 60);
		frame.getContentPane().add(lblNewLabel_2);
		return ypendingproductssize - 120;
	}

	public int status2(JFrame panel, int status2ySize, int countStatusId) {

		ArrayList<Product> products = null;
		try {
			products = dao.getStatusId(countStatusId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		int xpendingproductssize = xSize;
		int ypendingproductssize = status2ySize;
		int temp = 0;
		for (int i = 1; i <= products.size(); i++) {
			temp++;
			JButton x = new JButton("New label");
			x.setBounds(xpendingproductssize - 100, ypendingproductssize - 200, 50, 60);

			x.setText("" + i);
			x.setBackground(Color.blue);
			x.setForeground(SystemColor.black);
			x.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 15));
			x.setOpaque(true);
			panel.getContentPane().add(x);
			xpendingproductssize -= 50;
			if (temp == 9) {
				xpendingproductssize = xSize;
				ypendingproductssize = status2ySize - 60;
				temp = 0;
			}

		}

		JLabel lblNewLabel_2 = new JLabel("DURUM " + countStatusId + "\n" + " Olan Urunler");
		lblNewLabel_2.setForeground(new Color(161, 161, 86));

		lblNewLabel_2.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 15));
		lblNewLabel_2.setBounds(xpendingproductssize - 140, ypendingproductssize - 260, xpendingproductssize - 100, 60);
		frame.getContentPane().add(lblNewLabel_2);
		return ypendingproductssize - 120;
	}

}
