package arduino;

import com.panamahitek.PanamaHitek_Arduino;
import javax.swing.JOptionPane;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import utilerias.msg;

public class ardoSerial {
    
    private PanamaHitek_Arduino ph;
    private int brate = 9600;
    private String portName = "COM3";
    
    private arduinable reactBase;
    
    SerialPortEventListener listener = new SerialPortEventListener() {
        @Override
        public void serialEvent(SerialPortEvent spe) {
            try {
                if (ph.isMessageAvailable()) {
                   reactBase.behavior(ph.printMessage());
                }
            } catch (Exception Ex) {
                msg.error(Ex.getMessage());
            }
        }
    };
    
    public ardoSerial(arduinable reactBase) {
        
        this.reactBase = reactBase;
        
        try {
            this.ph = new PanamaHitek_Arduino();            
            this.ph.arduinoRXTX("COM3", this.brate, this.listener);            
        } catch (Exception ex) {
            msg.error(ex.getMessage());
        }
    }
    public ardoSerial() {                        
        try {
            this.ph = new PanamaHitek_Arduino();            
            this.ph.arduinoRXTX("COM3", this.brate, this.listener);            
        } catch (Exception ex) {
            msg.error(ex.getMessage());
        }
    }    
    
    public void setArduinable(arduinable reactBase){
        this.reactBase = reactBase;
    }
    
    public void sendToArdo(String data) {
        
        try {
            this.ph.sendData(data);            
        } catch (Exception ex) {
            msg.error(ex.getMessage());
        }
    }
    
    public void exit() {
        try {
            this.ph.killArduinoConnection();
        } catch (Exception ex) {
            msg.error(ex.getMessage());
        }
    }
    
}
