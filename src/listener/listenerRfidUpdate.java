package listener;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;

import org.apache.log4j.Logger;

import dao.RfidDAO;
import dto.Product;
import dto.Rfid;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;

public class listenerRfidUpdate implements SerialPortEventListener {
	Logger logger = Logger.getLogger(listenerRfidUpdate.class);

	SerialPort serialPort;
	private static final String PORT_NAMES[] = { "COM4" };
	private BufferedReader input;
	private OutputStream output;
	private static final int TIME_OUT = 2000;
	private static final int DATA_RATE = 9600;
	RfidDAO dao = new RfidDAO();
	Rfid rfid;

	public void initialize() {
		CommPortIdentifier portId = null;
		Enumeration portEnum = CommPortIdentifier.getPortIdentifiers();

		while (portEnum.hasMoreElements()) {
			CommPortIdentifier currPortId = (CommPortIdentifier) portEnum.nextElement();
			for (String portName : PORT_NAMES) {
				if (currPortId.getName().equals(portName)) {
					portId = currPortId;
					break;
				}
			}
		}
		if (portId == null) {
			System.out.println("Could not find COM port.");
			return;
		}

		try {
			serialPort = (SerialPort) portId.open(this.getClass().getName(), TIME_OUT);
			serialPort.setSerialPortParams(DATA_RATE, SerialPort.DATABITS_8, SerialPort.STOPBITS_1,
					SerialPort.PARITY_NONE);

			input = new BufferedReader(new InputStreamReader(serialPort.getInputStream()));
			output = serialPort.getOutputStream();

			serialPort.addEventListener(this);
			serialPort.notifyOnDataAvailable(true);
		} catch (Exception e) {
			System.err.println(e.toString());
		}
	}

	public synchronized void close() {
		if (serialPort != null) {
			serialPort.removeEventListener();
			serialPort.close();
		}
	}

	public synchronized void serialEvent(SerialPortEvent oEvent) {
		boolean esit = false;
		int productStatus = 0;
		if (oEvent.getEventType() == SerialPortEvent.DATA_AVAILABLE) {
			try {
				String inputLine = null;
				if (input.ready()) {

					inputLine = input.readLine();
					System.out.println(inputLine);
					System.out.println();
					ArrayList<Product> products = new ArrayList<>();
					products = dao.getRfidStatus();

					for (Product product : products) {

						if (product.getRfId().equals(inputLine))
							esit = true;
					}

					if (esit == true) {
						int a = dao.productRfidStatus(inputLine);
						if (a == 2) {
							dao.UpdateRfidDoor(inputLine, 3);
						} else {
							dao.UpdateRfidDoor(inputLine, 7);
							;

						}
						Date date = new Date();

						// display time and date
						String str = String.format("  : %tc", date);

						String konum = "C:\\\\Users\\\\Numan\\\\git\\\\depoyonetimsistemi\\\\WarehouseManagementSystem\\\\ProductLog.txt";

						Writer output;
						output = new BufferedWriter(new FileWriter(konum, true));
						output.append(
								"\n" + inputLine + " Rfid nolu Ürün" + str + "'tarihinde   Rafa yerleþtirilmiþtirdi");

						output.close();
					} else {
						Date date = new Date();

						// display time and date
						String str = String.format("  : %tc", date);

						String konum = "C:\\Users\\Numan\\git\\depoDenetlemesistemi\\WarehouseManagementSystem\\uyariLog.txt";

						Writer output;
						output = new BufferedWriter(new FileWriter(konum, true));
						output.append("\n" + "UYARÝ" + inputLine + " Rfid nolu Ürün" + str
								+ "'tarihinde   izinsiz depoya giriþ/cikiþ yapmýþtir.");

						output.close();
						logger.debug("UYARÝ" + inputLine + " Rfid nolu Ürün" + str	+ "'tarihinde   izinsiz depoya giriþ/cikiþ yapmýþtir.");

						logger.trace("UYARÝ" + inputLine + " Rfid nolu Ürün" + str	+ "'tarihinde   izinsiz depoya giriþ/cikiþ yapmýþtir.");
					}

				}

			} catch (Exception e) {
				System.err.println(e.toString());
			}
		}

	}

	public static void main(String[] args) throws Exception {

		listenerRfidUpdate main = new listenerRfidUpdate();
		main.initialize();
		Thread t = new Thread() {
			public void run() {
				try {
					Thread.sleep(1000000);
				} catch (InterruptedException ie) {
				}
			}
		};
		t.start();
		System.out.println("Started");
	}
}
