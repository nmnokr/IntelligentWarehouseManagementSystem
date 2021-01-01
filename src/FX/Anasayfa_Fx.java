package FX;

import dao.RfidDAO;
import dto.Product;
import dto.shelf;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.animation.KeyFrame;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import javafx.animation.Animation;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Anasayfa_Fx extends Application {
	Map<String, List> mapList1 = new HashMap<String, List>();
	int staticx = 10;
	int status5change = 0;
	ArrayList<Integer> temp = new ArrayList<Integer>();
	int countStok;
	int tempy = 0;
	int statussx, statusy = 10;
	RfidDAO dao = new RfidDAO();
	Pane root = new Pane();
	Pane root3 = new Pane();
	Pane root2 = new Pane();
	int asddsa = 123;
	ListView<String>[] listViews = new ListView[0];

	@Override

	public void start(Stage primaryStage) throws Exception {
 
		Rectangle depoCercevesi = new Rectangle();
		depoCercevesi.setX(30);
		depoCercevesi.setY(30);
		depoCercevesi.setWidth(800);
		depoCercevesi.setHeight(600);
		depoCercevesi.setArcWidth(50);
		depoCercevesi.setArcHeight(50);
		depoCercevesi.setStroke(Color.WHITE);

		depoCercevesi.setFill(Color.BLACK);

		root.setStyle("-fx-background-color:BLACK;");
		Scene scene = new Scene(root, Color.WHITE);
		root2.setStyle("-fx-background-color:BLACK;fx");
		root.getChildren().addAll(depoCercevesi);
		root.getChildren().addAll(root2);
		Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
		primaryStage.setScene(scene);
		primaryStage.setX(primaryScreenBounds.getMinX());
		primaryStage.setY(primaryScreenBounds.getMinY());
		primaryStage.setWidth(primaryScreenBounds.getWidth());
		primaryStage.setHeight(primaryScreenBounds.getHeight());
		primaryStage.show();
		int gridx = 0, gridy = 1;
		
		ArrayList<Integer> gridxy = new ArrayList<Integer>();
		gridxy.add(gridx);
		gridxy.add(gridy);
		GridPane bilgilendirme = new GridPane();
		bilgilendirme.setPadding(new Insets(1));
		bilgilendirme.setHgap(5);
		bilgilendirme.setVgap(5);
		int tempx = 10;
		int tempy = 130;
		Label d1 = new Label("Bekleme Alaninda Olan Ürünler");
		Label d2 = new Label("Yolda Olan Ürünler");
		Label d3 = new Label("Depoya Giriþ Yapan Ürünler");
		Label d4 = new Label("Raf'a Yerleþen Ürün");
		Label d5 = new Label("Çikiþ Ýþlemi Verilen Ürün");
		Label d6 = new Label("Personel Tarafaindan Alýnan Ürün");
		d1.setStyle("-fx-background-color: black;" + "-fx-pref-height: 150;" + "-fx-pref-width :220;"
				+ "-fx-text-fill: white;");
		d2.setStyle("-fx-background-color: black;" + "-fx-pref-x	: 150;" + "-fx-pref-width :150;"
				+ "-fx-text-fill: white;");
		d3.setStyle("-fx-background-color: black;" + "-fx-pref-height: 150;" + "-fx-pref-width :220;"
				+ "-fx-text-fill: white;");
		d4.setStyle("-fx-background-color: black;" + "-fx-pref-height: 150;" + "-fx-pref-width :150;"
				+ "-fx-text-fill: white;");
		d5.setStyle("-fx-background-color: black;" + "-fx-pref-x	: 150;" + "-fx-pref-width :150;"
				+ "-fx-text-fill: white;");
		d6.setStyle("-fx-background-color: black;" + "-fx-pref-height: 150;" + "-fx-pref-width :220;"
				+ "-fx-text-fill: white;");
		GridPane.setHalignment(d1, HPos.LEFT);
		Button bDurum1 = new Button("1");
		Button bDurum2 = new Button("2");
		Button bDurum3 = new Button("3");
		Button bDurum4 = new Button("4");
		Button bDurum5 = new Button("5");
		Button bDurum6 = new Button("6");
		bDurum1.setStyle("-fx-background-color: Blue;" + "-fx-pref-height: 30;" + "-fx-pref-width :30;"
				+ "-fx-text-fill: white;");

		bDurum2.setStyle("-fx-background-color: Blue;" + "-fx-pref-height: 30;" + "-fx-pref-width :30;"
				+ "-fx-text-fill: white;" + "-fx-border-color: white;");
		bDurum3.setStyle("-fx-background-color: Blue;" + "-fx-pref-height: 30;" + "-fx-pref-width :30;"
				+ "-fx-text-fill: white;" + "-fx-border-color: white;");
		bDurum4.setStyle("-fx-background-color: Green;" + "-fx-pref-height: 50;" + "-fx-pref-width :50;"
				+ "-fx-text-fill: white;" + "-fx-border-color: white;");
		bDurum5.setStyle("-fx-background-color: Orange;" + "-fx-pref-height: 50;" + "-fx-pref-width :50;"
				+ "-fx-text-fill: white;" + "-fx-border-color: white;");
		bDurum6.setStyle("-fx-background-color: Yellow;" + "-fx-pref-height: 50;" + "-fx-pref-width :50;"
				+ "-fx-text-fill: white;" + "-fx-border-color: white;");
		bilgilendirme.add(d1, tempx + 1, tempy);
		bilgilendirme.add(bDurum1, tempx, tempy);
		bilgilendirme.add(bDurum4, tempx, tempy + 20);
		bilgilendirme.add(d4, tempx + 1, tempy + 20);
		tempx = 15;
		bilgilendirme.add(bDurum2, tempx, tempy);
		bilgilendirme.add(d2, tempx + 1, tempy);
		bilgilendirme.add(bDurum5, tempx, tempy + 20);
		bilgilendirme.add(d5, tempx + 1, tempy + 20);
		tempx = 25;
		bilgilendirme.add(bDurum3, tempx, tempy);
		bilgilendirme.add(d3, tempx + 1, tempy);
		bilgilendirme.add(bDurum6, tempx, tempy + 20);
		bilgilendirme.add(d6, tempx + 1, tempy + 20);
		root.getChildren().add(bilgilendirme);
		countStok = dao.getCountStok();

		Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0), new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent actionEvent) {

		 
				statussx = 10;
				statusy = 150;
				try {

					root.getChildren().remove("tempgrid");
					root.getChildren().remove("bilgilendirme");
					root.getChildren().remove("list");

					bilgilendirmelistesi();
					uyariListesi();
					mapList1 = dao.getAllStatus1();
					staticx = 0;
				} catch (Exception e1) {

					e1.printStackTrace();
				}
				try {
					for (int i = 0; i < countStok; i++) {

						staticx = olustururun(i + 1, staticx);
 					}
					listViews = new ListView[4];

					for (int i = 1; i < 4; i++) {
						listViews[i] = new ListView<String>();
						statuss(i, statussx, listViews[i]);
						statussx += 50;

					}
					staticx = 20;

				} catch (Exception e) {

					e.printStackTrace();
				}

			}
		}

		), new KeyFrame(Duration.seconds(10)));
		timeline.setCycleCount(Animation.INDEFINITE);
		timeline.play(); // timeline.stop()

	}

	@SuppressWarnings("resource")
	public void bilgilendirmelistesi() throws IOException {

		GridPane list = new GridPane();
		list.setPadding(new Insets(1));
		list.setHgap(5);
		list.setVgap(5);
		int tempx = 180;
		int tempy = 80;
		ObservableList<String> data = FXCollections.observableArrayList();

		ListView<String> listView = new ListView<String>(data);
		listView.setStyle("-fx-control-inner-background: black;" + "-fx-pref-height: 600;" + "-fx-pref-width :900");
		listView.setPrefSize(100, 80);
		String tmp;
		List<String> listLog = new ArrayList<String>();
		File file = new File("C:\\Users\\Numan\\git\\depoDenetlemesistemi\\WarehouseManagementSystem\\ProductLog.txt");
		BufferedReader reader = null;
		reader = new BufferedReader(new FileReader(file));

		while ((tmp = reader.readLine()) != null) {

			listLog.add(tmp);
		}

		if (listLog.size() > 20) {
			for (int i = listLog.size() - 1; i >= (listLog.size() - 20); i--) {
				System.out.println(listLog.get(i));
				data.add(listLog.get(i));

			}
		} else {
			for (int i = 0; i < 10; i++) {
				data.add(listLog.get(i));
			}
		}

		listView.setItems(data);

		listView.getSelectionModel().selectedItemProperty()
				.addListener((ObservableValue<? extends String> ov, String old_val, String new_val) -> {
					System.out.println(new_val);

				});
		list.add(listView, tempx, tempy);
		root.getChildren().add(list);
	}

	@SuppressWarnings("resource")
	public void uyariListesi() throws IOException {

		GridPane list2 = new GridPane();
		list2.setPadding(new Insets(1));
		list2.setHgap(5);
		list2.setVgap(5);
		int tempx = 180;
		int tempy = 30;
		ObservableList<String> data = FXCollections.observableArrayList();

		ListView<String> listView3 = new ListView<String>(data);
		listView3.setStyle("-fx-control-inner-background: black;" + "-fx-pref-height: 150;" + "-fx-pref-width :900");
		listView3.setPrefSize(180, 80);
		String tmp;
		List<String> listLog = new ArrayList<String>();
		File file = new File("C:\\Users\\Numan\\git\\depoDenetlemesistemi\\WarehouseManagementSystem\\uyariLog.txt");
		BufferedReader reader = null;
		reader = new BufferedReader(new FileReader(file));

		while ((tmp = reader.readLine()) != null) {
			// strLine =tmp+"\n"+strLine;
			listLog.add(tmp);
		}

		if (listLog.size() > 5) {
			for (int i = listLog.size() - 1; i >= (listLog.size() - 5); i--) {
				System.out.println(listLog.get(i));
				data.add(listLog.get(i));

			}
		} else {
			for (int i = 0; i < 5; i++) {
				data.add(listLog.get(i));
			}
		}

		listView3.setItems(data);

		listView3.getSelectionModel().selectedItemProperty()
				.addListener((ObservableValue<? extends String> ov, String old_val, String new_val) -> {
					System.out.println(new_val);

				});
		list2.add(listView3, tempx, tempy);
		root.getChildren().add(list2);
	}

	@SuppressWarnings("unlikely-arg-type")
	public void bilgilendirme(String a) {
		root.getChildren().remove("bilgilendirme");
		GridPane bilgilendirme = new GridPane();
		bilgilendirme.setPadding(new Insets(1));
		bilgilendirme.setHgap(5);
		bilgilendirme.setVgap(5);
		int tempx = 180;
		int tempy = 30;

		Button a1 = new Button();
		a1.setDisable(false);
		a1.setStyle("-fx-background-color: black;" + "-fx-pref-height: 300;" + "-fx-pref-width :500;"
				+ "-fx-text-fill: white;");
		GridPane.setHalignment(a1, HPos.LEFT);
		a1.setText("Bilgilendirme" + a);
		bilgilendirme.add(a1, tempx, tempy);
		root.getChildren().add(bilgilendirme);
	}

	public void statuss(int countStatusId, int tempx, ListView<String> listViews2) {
		GridPane gridpanestatuss = new GridPane();
		gridpanestatuss.setPadding(new Insets(1));
		gridpanestatuss.setHgap(5);
		gridpanestatuss.setVgap(5);

		ArrayList<Product> products = null;
		try {
			products = dao.getStatusId(countStatusId);

		} catch (Exception e) {

			e.printStackTrace();
		}

		ObservableList<String> items = FXCollections.observableArrayList();
		listViews2 = new ListView<String>();

		listViews2.setStyle("-fx-control-inner-background: black;" + "-fx-pref-height: 120;" + "-fx-pref-width :300");
		for (int i = 1; i <= products.size(); i++) {

			items.add(products.get(i - 1).getRfId() + " " + products.get(i - 1).getName());

		}
		listViews2.setItems(items);
		gridpanestatuss.add(listViews2, tempx, statusy);
		root.getChildren().add(gridpanestatuss);
	}

	public int olustururun(int index, int staticxx) throws Exception {
		Label d1 = new Label("Bekleme Alaninda Olan Ürünler");

		d1.setStyle("-fx-background-color: black;" + "-fx-pref-height: 10;" + "-fx-pref-width :10;"
				+ "-fx-text-fill: white;");

		GridPane tempgrid = new GridPane();
		tempgrid.setPadding(new Insets(50));
		tempgrid.setHgap(5);
		tempgrid.setVgap(5);
		ArrayList<shelf> shelfs = new ArrayList<shelf>();

		shelfs = dao.getShelf(index);
		int whousesize = shelfs.size() / 2;

		int gridx = staticx + 5;
		int gridy = 1;
		Button depoIsimleri = new Button();
		depoIsimleri.setStyle("-fx-background-color: black;" + "-fx-pref-height: 30;" + "-fx-pref-width :80;"
				+ "-fx-text-fill: white;" + "-fx-border-color: white;");
		depoIsimleri.setText("Depo " + index);
		tempgrid.add(depoIsimleri, gridx, 0);
		for (int i = 0; i < whousesize; i++) {

			Button x = new Button(((String) shelfs.get(i).getRfId()));
			x.setAccessibleText(shelfs.get(i).getRfId());

			x.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {

				}
			});

			x.setStyle("-fx-font-size: 48px;" + "-fx-font-weight: bold;" + "-fx-background-color: lightgreen;"
					+ "-fx-border-style: solid inside;" + "-fx-border-width: 2;" + "-fx-border-insets: 5;"
					+ "-fx-border-radius: 5;" + "-fx-border-color: blue;");
			/*
			 * int max = 1; int min = 0; int range = max - min + 1; int rand =
			 * (int)(Math.random() * range) + min; if(rand==1) { r.setFill(Color.BROWN);
			 * }else { r.setFill(Color.YELLOW); }
			 */
			if (mapList1.get(shelfs.get(i).getRfId()) != null) {
				Object value = mapList1.get(shelfs.get(i).getRfId()).get(1);

				x.setFont(new Font("Microsoft New Tai Lue", 15));
 				x.setText(value.toString().substring(0, 1));
				if (Integer.valueOf((String) value) < 3) {
					x.setStyle("-fx-background-color: BLUE;" + "-fx-font-size: 28px;");
				}

				else if (Integer.valueOf((String) value) == 3) {
 					x.setStyle("-fx-background-color: MAGENTA;" + "-fx-font-size: 28px; ");

				} else if (Integer.valueOf((String) value) == 4) {
					System.out.println("var" + "asdddddddddddddddddd");
					x.setStyle("-fx-background-color: GREEN;" + "-fx-font-size: 28px;");

				} else if (Integer.valueOf((String) value) == 5) {
					status5change++;
					if (status5change % 10 == 1) {
						x.setStyle("-fx-background-color: orange;" + "-fx-font-size: 28px;");

					} else {
						x.setStyle("-fx-background-color: yellow;" + "-fx-font-size: 28px;");

					}

				} else if (Integer.valueOf((String) value) == 6) {
					x.setStyle("-fx-background-colorred;;" + "-fx-font-size: 28px;");

				} else {
					x.setStyle("-fx-background-color: color:red;;; " + "-fx-font-size: 28px");
					x.setText("B");
				}

			} else {
				x.setStyle("-fx-background-color: color:red;;; " + "-fx-font-size: 28px");
				x.setText("B");

			}

			// Save button
			GridPane.setHalignment(x, HPos.LEFT);
	 

			tempgrid.add(x, gridx, gridy);
			gridy++;
		}
		gridy--;
		gridx++;
		/////////////////////////
		Button orta = new Button();

		orta.setStyle("-fx-background-color: white	;" + "-fx-font-size: 28px;" + "-fx-pref-width:50;");

		GridPane.setHalignment(orta, HPos.LEFT);
		tempgrid.add(orta, gridx, gridy);

		gridx++;
		/////////////
		for (int i = whousesize + 1; i < shelfs.size(); i++) {
			Button r2 = new Button(((String) shelfs.get(i).getRfId()));
			r2.setStyle("-fx-font-size: 30px;" + "-fx-font-weight: bold;" + "-fx-background-color: lightgreen;"
					+ "-fx-border-style: solid inside;" + "-fx-border-width: 2;" + "-fx-border-insets: 5;"
					+ "-fx-border-radius: 5;" + "-fx-borde" + "r-color: blue;");
			r2.setAccessibleText(shelfs.get(i).getRfId());

			r2.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					String a = r2.getAccessibleText();

					bilgilendirme(a);
				}
			});

			if (mapList1.get(shelfs.get(i).getRfId()) != null) {
				Object value = mapList1.get(shelfs.get(i).getRfId()).get(1);

				r2.setFont(new Font("Microsoft New Tai Lue", 15));
				System.out.println(value);
				r2.setText(value.toString().substring(0, 1));
				if (Integer.valueOf((String) value) < 3) {
					r2.setStyle("-fx-background-color: BLUE;" + "-fx-font-size: 28px;");
				}

				else if (Integer.valueOf((String) value) == 3) {
					r2.setStyle("-fx-background-color: MAGENTA;" + "-fx-font-size: 28px; ");

				} else if (Integer.valueOf((String) value) == 4) {
					r2.setStyle("-fx-background-color: GREEN;" + "-fx-font-size: 28px;");

				} else if (Integer.valueOf((String) value) == 5) {
					r2.setStyle("-fx-background-color: orange;" + "-fx-font-size: 28px;");

				} else if (Integer.valueOf((String) value) == 6) {
					r2.setStyle("-fx-background-color: color:red;" + "-fx-font-size: 28px;");

					r2.setText("B");
				}

			} else {
				r2.setStyle("-fx-background-color:color:red;; " + "-fx-font-size: 28px");
				r2.setText("B");

			}
			// Save button
			GridPane.setHalignment(r2, HPos.LEFT);
			tempgrid.add(r2, gridx, gridy);
	 

			gridy--;
		}

		gridx++;
		gridy++;
		ArrayList<Integer> gridxy = new ArrayList<Integer>();
		gridxy.add(gridx);
		gridxy.add(gridy);
		staticx = gridx;
		Date date = new Date();

		// display time and date
		String str = String.format("Sistem Son Günceleme Saati : %tc", date);

		Button saat = new Button();
		saat.setDisable(false);
		saat.setStyle("-fx-font-size: 30px;" + "-fx-font-weight: bold;" + "-fx-background-color: white;"
				+ "-fx-border-style: solid inside;" + "-fx-border-color: red;");
		GridPane.setHalignment(saat, HPos.LEFT);
		saat.setText(str);

		tempgrid.add(saat, 130, 0);
		root.getChildren().add(tempgrid);
		return staticx + 40;
	}

	public static void main(String[] args) {
		launch(args);
	}
}