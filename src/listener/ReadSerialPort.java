package listener;

import java.io.*;
import java.util.*;

import dto.Rfid;
import gnu.io.*; // for rxtxSerial library

public class ReadSerialPort implements SerialPortEventListener {
	static CommPortIdentifier portId;
	static CommPortIdentifier saveportId;
	static Enumeration portList;
	InputStream inputStream;
	SerialPort serialPort;
	int closenumber = 0;
	static String rfidNo = null;
Rfid rfid=new Rfid();
	public static void main(String[] args) throws InterruptedException {
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
 

	}

	public void init() {
		CommPortIdentifier ports = null; // to browse through each port identified
		Enumeration portEnum = CommPortIdentifier.getPortIdentifiers(); // store all available ports
		while (portEnum.hasMoreElements()) { // browse through available ports
			ports = (CommPortIdentifier) portEnum.nextElement();
			// following line checks whether there is the port i am looking for and whether
			// it is serial
			if (ports.getPortType() == CommPortIdentifier.PORT_SERIAL && ports.getName().equals("COM3")) {

				System.out.println("COM port found:COM3");
				portId = ports; // initialize my port
				break;
			}

		}
		// if serial port am looking for is not found
		if (portId == null) {
			System.out.println("COM port not found");
			System.exit(1);
		}

	}

	public void ReadRFIDPort() {

		// initalize serial port
		try {
			serialPort = (SerialPort) portId.open("SimpleReadApp", 9600);
		} catch (PortInUseException e) {
		}

		try {
			inputStream = serialPort.getInputStream();
		} catch (IOException e) {
		}

		try {
			serialPort.addEventListener(this);
		} catch (TooManyListenersException e) {
		}

		// activate the DATA_AVAILABLE notifier
		serialPort.notifyOnDataAvailable(true);

		try {
			// set port parameters
			serialPort.setSerialPortParams(9600, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
		} catch (UnsupportedCommOperationException e) {
		}

	}

	public String close() {
		if (serialPort != null) {
			serialPort.close(); // close serial port
			System.out.println("port kapandi");
		}
		inputStream = null; // close input and output streams
		System.out.println("<<<" + rfidNo);

		int temp = 0;
		String sTemp = "";
		StringBuilder r = new StringBuilder();

		for (int i = 0; i < rfidNo.length(); i++) {
			if (rfidNo.charAt(i) > 47 && rfidNo.charAt(i) < 58) {
				r.append(rfidNo.charAt(i));
			}

		}
		String rString = r.toString();
	 
		return rString;

	}

	public void serialEvent(SerialPortEvent event) {

		switch (event.getEventType()) {
		case SerialPortEvent.BI:
		case SerialPortEvent.OE:
		case SerialPortEvent.FE:
		case SerialPortEvent.PE:
		case SerialPortEvent.CD:
		case SerialPortEvent.CTS:
		case SerialPortEvent.DSR:
		case SerialPortEvent.RI:
		case SerialPortEvent.OUTPUT_BUFFER_EMPTY:
			break;
		case SerialPortEvent.DATA_AVAILABLE:
			// we get here if data has been received
			byte[] readBuffer = new byte[40];
			try {

				// read data
				while (inputStream.available() > 0) {
					int numBytes = inputStream.read(readBuffer);
				}
				// print data
				String result = new String(readBuffer);
				System.out.println(result);

				rfidNo = result;
			} catch (IOException e) {
			}

			break;
		}

	}

}