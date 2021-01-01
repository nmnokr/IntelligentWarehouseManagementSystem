package FX;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.scene.control.Alert.AlertType;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dao.RfidDAO;
import listener.ReadSerialPort;

import javafx.scene.Group;

public class Depo_Gorevlisi extends Application {
	Group root = new Group();
	RfidDAO dao = new RfidDAO();
	GridPane borderPane = new GridPane();
	Button girisButon = new Button();
	Button girisButon1 = new Button();
	Button girisButon2 = new Button();
	Button girisButon3 = new Button();
	Button cikisRafOkut = new Button();
	Button cikisUrunOku = new Button();
	Button CikisOnay = new Button();
	Button depoGirisButton = new Button();
	Button depoCikisButton = new Button();

	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("Depo Gorevlisi");
		Scene scene = new Scene(root, 400, 400, Color.WHITE);

		borderPane.prefHeightProperty().bind(scene.heightProperty());
		borderPane.prefWidthProperty().bind(scene.widthProperty());

		MenuBar menuBar = menuGeneration();
		borderPane.add(menuBar, 0, 0);
		root.getChildren().add(borderPane);
		primaryStage.setTitle("Personel El Terminal Sistemi");
		primaryStage.setResizable(false);

		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public MenuBar menuGeneration() {
		MenuBar menuBar = new MenuBar();
		EventHandler<ActionEvent> action = changeTabPlacement();

		Menu menu = new Menu("Bildirim");
		MenuItem left = new MenuItem();
		left.setText("Depo'ya Sevk Edilen Ürünler");
		left.setOnAction(action);
		menu.getItems().add(left);

		MenuItem right = new MenuItem();
		right.setOnAction(action);
		right.setText("Çikiþ Ýþlemi Verilen Ürünler");
		menu.getItems().add(right);

		Menu menu2 = new Menu("Islemler");
		menuBar.getMenus().add(menu);
		menuBar.getMenus().add(menu2);

		MenuItem g1 = new MenuItem("Depoya Ürün Giris Sevk Ýþlemi");
		g1.setOnAction(action);
		menu2.getItems().add(g1);

		MenuItem cikis1 = new MenuItem("Ürün Cikiþ Ýþlemi");
		cikis1.setOnAction(action);
		menu2.getItems().add(cikis1);

		MenuItem c1 = new MenuItem("Ürün Yerleþtirme Ýþlemi");
		c1.setOnAction(action);
		menu2.getItems().add(c1);
		return menuBar;
	}

	public void depoGirisList() throws Exception {
		try {
			borderPane.getChildren().remove(girisButon);
			borderPane.getChildren().remove(girisButon1);
			borderPane.getChildren().remove(girisButon2);
			borderPane.getChildren().remove(girisButon3);
			borderPane.getChildren().remove(cikisRafOkut);
			borderPane.getChildren().remove(cikisUrunOku);
			borderPane.getChildren().remove(CikisOnay);
			borderPane.getChildren().remove(depoGirisButton);
			borderPane.getChildren().remove(depoCikisButton);
		} catch (Exception e) {
			System.out.println(e);
		}
		Text text2 = new Text();
		text2.setText("This is my second Text Node");

		Map<String, List> maplist = new HashMap<String, List>();
		RfidDAO rfidDAO = new RfidDAO();
		maplist = rfidDAO.getAllWarehouseProducts(1);

		MenuBar menuBar = menuGeneration();
		borderPane.add(menuBar, 0, 0);
		ObservableList<String> data = FXCollections.observableArrayList();

		ListView<String> listView = new ListView<String>(data);
		listView.setStyle("-fx-control-inner-background: black;" + "-fx-pref-height: 800;" + "-fx-pref-width :650");
		listView.setPrefSize(450, 350);

		data.add("Rfid Numarasi" + "  Kategori" + "  Ýsim" + "  Raf Numarasi");

		for (int i = 0; i < maplist.size(); i++) {
			data.add((String) maplist.get("" + i).get(0) + "  " + maplist.get("" + i).get(2) + "  "
					+ maplist.get("" + i).get(1) + "  " + maplist.get("" + i).get(3));
		}

		borderPane.add(listView, 0, 1);

	}

	@SuppressWarnings("unlikely-arg-type")
	public void depoCikisList() throws Exception {
		try {
			borderPane.getChildren().remove(girisButon);
			borderPane.getChildren().remove(girisButon1);
			borderPane.getChildren().remove(girisButon2);
			borderPane.getChildren().remove(girisButon3);
			borderPane.getChildren().remove(cikisRafOkut);
			borderPane.getChildren().remove(cikisUrunOku);
			borderPane.getChildren().remove(CikisOnay);
			borderPane.getChildren().remove(depoGirisButton);
			borderPane.getChildren().remove(depoCikisButton);
		} catch (Exception e) {
			System.out.println("e");
		}

		Map<String, List> maplist2 = new HashMap<String, List>();
		RfidDAO rfidDAO = new RfidDAO();
		maplist2 = rfidDAO.getAllWarehouseProducts(5);

		MenuBar menuBar = menuGeneration();
		borderPane.add(menuBar, 0, 0);
		ObservableList<String> data = FXCollections.observableArrayList();

		ListView<String> listView = new ListView<String>(data);
		listView.setStyle("-fx-control-inner-background: black;" + "-fx-pref-height: 800;" + "-fx-pref-width :650");
		listView.setPrefSize(450, 650);
		data.add("Rfid Numarasi" + "  Kategori" + "  Ýsim" + "  Raf Numarasi");

		for (int i = 0; i < maplist2.size(); i++) {
			data.add((String) maplist2.get("" + i).get(0) + "  " + maplist2.get("" + i).get(2) + "  "
					+ maplist2.get("" + i).get(1) + "  " + maplist2.get("" + i).get(3));
		}
		borderPane.add(listView, 0, 1);

	}

	public void urunYerlestirmeIslemi() {
		try {
			borderPane.getChildren().remove(cikisRafOkut);
			borderPane.getChildren().remove(cikisUrunOku);
			borderPane.getChildren().remove(CikisOnay);
			borderPane.getChildren().remove(depoGirisButton);
			borderPane.getChildren().remove(depoCikisButton);

		} catch (Exception e) {
			errorLog("Ürün urunYerlestirmeIslemi kompenetleri silinirken" + e.getMessage() + " hatasi Olustu");
		}

		ObservableList<String> data = FXCollections.observableArrayList();

		ListView<String> listView = new ListView<String>(data);
		listView.setPrefSize(450, 250);
		data.add("Rfid Numarasi" + "  Kategori" + "  Ýsim" + "  Raf Numarasi");

		girisButon.setStyle("-fx-border-color: #ff0000; -fx-border-width: 2px;");
		girisButon.setStyle("-fx-background-color: #00ff00");
		girisButon.setStyle("-fx-font-size: 2em; ");
		girisButon.setStyle("-fx-text-fill: #0000ff" + " -fx-padding: 50;" + "   -fx-spacing: 10;"
				+ "-fx-pref-height: 100;" + "-fx-pref-width :500");
		girisButon.setText("Ürün Rfid Okut");

		girisButon2.setStyle("-fx-border-color: #ff0000; -fx-border-width: 2px;");
		girisButon2.setStyle("-fx-background-color: #00ff00");
		girisButon2.setStyle("-fx-font-size: 2em; ");
		girisButon2.setStyle("-fx-text-fill: #0000ff" + " -fx-padding: 25;" + "   -fx-spacing: 10;"
				+ "-fx-pref-height: 100;" + "-fx-pref-width :500");
		girisButon2.setText("Raf Rfid Okut");

		girisButon3.setStyle("-fx-border-color: #ff0000; -fx-border-width: 2px;");
		girisButon3.setStyle("-fx-background-color: #00ff00");
		girisButon3.setStyle("-fx-font-size: 2em; ");
		girisButon3.setStyle("-fx-text-fill: #0000ff" + " -fx-padding: 25;" + "   -fx-spacing: 10;"
				+ "-fx-pref-height: 100;" + "-fx-pref-width :500");
		girisButon3.setText("Tamamla");
		borderPane.add(listView, 0, 1);
		borderPane.add(girisButon, 0, 2);
		borderPane.add(girisButon2, 0, 3);
		borderPane.add(girisButon3, 0, 4);

		girisButon.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {

				ReadSerialPort port = new ReadSerialPort();

				port.init();

				port.ReadRFIDPort();
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				String asd = port.close();
				System.out.println(asd);
				System.out.println(asd);
				girisButon.setText("" + asd);
			}
		});

		girisButon2.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				System.out.println("Hello World!");
				ReadSerialPort port = new ReadSerialPort();

				port.init();

				port.ReadRFIDPort();
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				String asd = port.close();
				System.out.println(asd);
				girisButon2.setText("" + asd);
			}
		});
		girisButon3.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				String productRfid = null;
				String temp = girisButon.getText();
				System.out.println("ürün rfid" + temp);
				try {
					productRfid = dao.getProductRfid(temp);
					System.out.println(">>>>>>>>>>>>>>>>>>" + productRfid);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				String shelfId = girisButon2.getText();
				System.out.println(shelfId + "<<<<<<<<<<<<<<<<<<<<" + girisButon2.getText());

				if (productRfid.equals(shelfId)) {
					data.add(shelfId);
					try {
						dao.UpdateProduct4(temp);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {

				}
			}
		});

	}

	@SuppressWarnings("unlikely-arg-type")
	public void depoCikisSevk() {
		try {

			borderPane.getChildren().remove(girisButon);
			borderPane.getChildren().remove(girisButon1);
			borderPane.getChildren().remove(girisButon2);
			borderPane.getChildren().remove(girisButon3);
			borderPane.getChildren().remove(cikisRafOkut);
			borderPane.getChildren().remove(cikisUrunOku);
			borderPane.getChildren().remove(CikisOnay);
			borderPane.getChildren().remove(depoGirisButton);

		} catch (Exception e) {
			System.out.println(e);
		}

		ObservableList<String> data = FXCollections.observableArrayList();

		ListView<String> listView = new ListView<String>(data);
		listView.setPrefSize(450, 250);
		data.add("Rfid Numarasi" + "  Kategori" + "  Ýsim" + "  Raf Numarasi");

		depoCikisButton.setStyle("-fx-border-color: #ff0000; -fx-border-width: 5px;");
		depoCikisButton.setStyle("-fx-background-color: #00ff00");
		depoCikisButton.setStyle("-fx-font-size: 2em; ");
		depoCikisButton.setStyle("-fx-text-fill: #0000ff" + " -fx-padding: 50;" + "   -fx-spacing: 10;"
				+ "-fx-pref-height: 100;" + "-fx-pref-width :650");
		depoCikisButton.setText("Cikiþ Yapilacak Rfid Okut");
		borderPane.add(listView, 0, 1);
		borderPane.add(depoCikisButton, 0, 2);

		depoCikisButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {

				ReadSerialPort port = new ReadSerialPort();

				port.init();

				port.ReadRFIDPort();
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				String rfidId = port.close();
				System.out.println(rfidId);
				try {
					dao.UpdateProduc6(rfidId);
				} catch (Exception e) {
					errorLog("cýkýs rfid  okunurken hata meydana geldi" + e.getMessage() + "");
				}
				data.add(rfidId);
			}
		});

	}

	public void depoGirisSevk() {
		try {

			borderPane.getChildren().remove(girisButon);
			borderPane.getChildren().remove(girisButon1);
			borderPane.getChildren().remove(girisButon2);
			borderPane.getChildren().remove(girisButon3);
			borderPane.getChildren().remove(cikisRafOkut);
			borderPane.getChildren().remove(cikisUrunOku);
			borderPane.getChildren().remove(CikisOnay);

			borderPane.getChildren().remove(depoCikisButton);

		} catch (Exception e) {
			System.out.println(e);
		}

		ObservableList<String> data = FXCollections.observableArrayList();

		ListView<String> listView = new ListView<String>(data);
		listView.setPrefSize(500, 250);

		data.add("Rfid Numarasi" + "  Kategori" + "  Ýsim" + "  Raf Numarasi");

		depoGirisButton.setStyle("-fx-border-color: #ff0000; -fx-border-width: 5px;");
		depoGirisButton.setStyle("-fx-background-color: #00ff00");
		depoGirisButton.setStyle("-fx-font-size: 2em; ");
		depoGirisButton.setStyle("-fx-text-fill: #0000ff" + " -fx-padding: 50;" + "   -fx-spacing: 10;"
				+ "-fx-pref-height: 100;" + "-fx-pref-width :650");
		depoGirisButton.setText("Giriþ Yapilacak Rfid Okut");
		borderPane.add(listView, 0, 1);
		borderPane.add(depoGirisButton, 0, 2);

		depoGirisButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {

				ReadSerialPort port = new ReadSerialPort();

				port.init();

				port.ReadRFIDPort();
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				String rfidId = port.close();
				System.out.println(rfidId);
				try {
					dao.UpdateProduct2(rfidId);
					data.add(rfidId);
				} catch (Exception e) {
					System.out.println("giris yapilack ürün idsi okunurken " + e.getMessage() + " hatasi olustu");
				}

			}
		});

	}

	private EventHandler<ActionEvent> changeTabPlacement() {
		return new EventHandler<ActionEvent>() {

			public void handle(ActionEvent event) {
				MenuItem mItem = (MenuItem) event.getSource();
				String side = mItem.getText();
				if ("Depo'ya Sevk Edilen Ürünler".equalsIgnoreCase(side)) {
					try {
						depoGirisList();
					} catch (Exception e) {
						System.out.println(e.getMessage());
						e.printStackTrace();
					}
				} else if ("Çikiþ Ýþlemi Verilen Ürünler".equalsIgnoreCase(side)) {
					System.out.println("numan");
					try {
						depoCikisList();
					} catch (Exception e) {
						System.out.println(e.getMessage());
						;
					}
				} else if ("Depoya Ürün Giris Sevk Ýþlemi".equalsIgnoreCase(side)) {

					depoGirisSevk();

				} else if ("Ürün Cikiþ Ýþlemi".equalsIgnoreCase(side)) {
					depoCikisSevk();
				} else if ("Ürün Yerleþtirme Ýþlemi".equalsIgnoreCase(side)) {
					urunYerlestirmeIslemi();
				}
			}
		};
	}

	public void errorLog(String error) {
		Date date = new Date();
		String str = String.format("  : %tc", date);

		String location = "errorLog.txt";

		try {
			Writer output;
			output = new BufferedWriter(new FileWriter(location, true));
			output.append("\n" + str + " tarihinde " + error);
			output.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
