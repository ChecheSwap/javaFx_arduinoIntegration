package bloodbanks;

import arduino.ardoSerial;
import arduino.arduinable;
import bloodbanks.central.modeloCentral;
import db.dbop;
import entities.bancosangre;
import entities.usuario;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;
import javax.swing.Timer;
import mail.fxmail;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

import utilerias.msg;

public class modelo_inicio implements arduinable {

    private Controller_Inicio base;
    private dbop db = null;

    private Stage st;

    public ardoSerial ardo;

    private modelo_inicio gendowsBase;

    private boolean normalLogin;

    private fxmail mail;

    public Thread secondaryThread;

    private Timer t;

    public modelo_inicio() {
        this.base = Main.manager.openFXML("/bloodbanks/FXMLinicio.fxml", "SGBS INICIO", true, false).getController();

        this.gendowsBase = this;

        this.rxtx();

        this.st = (Stage) this.base.txtcontrol.getScene().getWindow();

        this.db = new dbop();

        this.base.modelo = this;

        this.base.txtcontrol.requestFocus();

        this.normalLogin = true;

        this.mail = new fxmail();

        this.t = new Timer(2000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Platform.runLater(new Runnable() {

                    @Override
                    public void run() {
                        ardo.sendToArdo("@");
                        t.stop();
                    }
                });
            }
        });

        t.start();

        this.st.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {

                if (msg.yesno("Desea Salir?")) {
                    
                    ardoCloseStage();
                    Platform.exit();
                    System.exit(0);
                } else {
                    event.consume();
                }
            }

        });
    }

    public boolean verifica() {

        boolean flag = false;

        usuario usr;

        if (this.normalLogin) {

            usr = this.db.getUsuario(this.base.txtcontrol.getText(), this.base.txtpassword.getText());
        } else {
            usr = this.db.getUsuario(this.base.txtpassword.getText());
        }

        if (!(usr == null)) {

            flag = true;

            usuario.copy(Main.usuario, usr);

            bancosangre banco = this.db.getBancoLocal();

            if (!(banco == null)) {
                bancosangre.copy(Main.banco, banco);
            }

            modeloCentral c = new modeloCentral(this.ardo);

            Main.mail.execute(true);

            this.ardo.sendToArdo("1");

            this.st.hide();

        } else {

            Main.mail.execute(false);

            this.ardo.sendToArdo("0");
            msg.error("Credenciales Incorrectas!");

            this.ardo.sendToArdo("D");
            this.clearcontrols();
            this.base.txtcontrol.requestFocus();
        }

        return flag;

    }

    public void ardoCloseStage() {
 
        ardo.sendToArdo("z");            
    }

    private void clearcontrols() {
        this.base.txtcontrol.setText("");
        this.base.txtpassword.setText("");
    }

    public void btnaliaskp(KeyEvent e) {
        if (e.getCode() == KeyCode.ENTER) {
            this.base.txtpassword.requestFocus();
        }
    }

    public void btnpasskp(KeyEvent e) {
        if (e.getCode() == KeyCode.ENTER) {
            this.verifica();
        }
    }

    public void close() {
        this.st.close();
    }

    public void ardoLogin(boolean stmt) {

        if (!stmt) {
            this.base.txtardo.setVisible(true);
            this.base.txtalias.setVisible(false);
            this.base.txtcontrol.setVisible(false);
        } else {
            this.base.txtardo.setVisible(false);
            this.base.txtalias.setVisible(true);
            this.base.txtcontrol.setVisible(true);
            this.base.txtalias.requestFocus();
        }

    }

    @Override
    public void behavior(String a) {

        if (this.normalLogin) {
            this.btnarduinokp();
        }

        try {
            switch (a) {
                case ("#"): {

                    if (this.base.txtpassword.getText().length() > 0) {
                        char[] arr = this.base.txtpassword.getText().toCharArray();

                        String trunc = new String(arr, 0, arr.length - 1);
                        this.base.txtpassword.setText(trunc);
                    }
                    break;
                }
                case ("*"): {
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            gendowsBase.verifica();
                        }
                    });
                    break;
                }
                default: {
                    this.base.txtpassword.setText(this.base.txtpassword.getText() + a);
                    break;
                }
            }
        } catch (Exception ex) {
            msg.error(ex.getMessage());
        }
    }

    public void btnarduinokp() {
        this.normalLogin = !this.normalLogin;
        this.clearcontrols();
        this.ardoLogin(normalLogin);

    }

    private void rxtx() {
        this.secondaryThread = new Thread() {
            @Override
            public void run() {
                ardo = new ardoSerial(gendowsBase);
            }
        };
        this.secondaryThread.start();
    }

}
