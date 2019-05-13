package ev3;

import lejos.hardware.Button;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.I2CSensor;

public class EV3_I2C {

    static int I2CSlaveAddress = 0x08;

    static byte[] buffReadResponse = new byte[8];

    public static void main(String[] args) {

        System.out.println("Arduino Connection Test");

        I2CSensor arduino = new I2CSensor(SensorPort.S1, I2CSlaveAddress);

        while (Button.ESCAPE.isUp()) {
            int id = Button.waitForAnyPress();
            if (id == Button.ID_ENTER) {
                arduino.getData(1, buffReadResponse, buffReadResponse.length);
                System.out.println(new String(buffReadResponse));
            }

            if (id == Button.ID_DOWN) {
                String str = "OK";
                buffReadResponse = str.getBytes();
                arduino.sendData(1, buffReadResponse, buffReadResponse.length);
                System.out.println("Sent!");
            }
        }
        arduino.close();
    }
}