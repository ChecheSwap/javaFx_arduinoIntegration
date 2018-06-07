
package mail;

import bloodbanks.Main;
import java.util.Properties;
import javafx.util.Duration;
import javax.swing.JOptionPane;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;
import com.sun.mail.smtp.SMTPTransport;
import java.util.Properties;
import javafx.application.Platform;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import tray.animations.AnimationType;

public class fxmail {
      
    private String to = "chechenb19972@hotmail.com";
    private String from = "a301429@uach.mx";          
    private Properties props;    
    private Session xss;    
    private String pass = "master33";
    
    private NotificationType n;
    private String title;
    private String message;

    public fxmail(){
        
        this.props = System.getProperties();
        
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");      
                
                
        this.xss = Session.getDefaultInstance(this.props, new Authenticator(){
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(from,pass);                    
                }
        });
                
    }
    
    public void execute(boolean xcase){
        
        boolean flag = false;
        
        new Thread(new Runnable(){
            @Override
            public void run() {
                boolean f = xcase;
                
                boolean res = sendMail(f);
                
                Platform.runLater(new Runnable(){                        
                    @Override
                        public void run() {
                            
                            if(res){
                                displayNotify(true);
                            }
                            else{
                                displayNotify(false);
                            }
                        }
                    });
                }                                                    
        }).start();
        

    }
    
    private boolean sendMail(boolean xcase){
        
        boolean flag = false;
        
        try{
            MimeMessage msg = new MimeMessage(this.xss);
            
            msg.setFrom(new InternetAddress(this.from));
            
            msg.addRecipient(Message.RecipientType.TO, new InternetAddress(this.to));
            
            msg.setSubject("Sistema Gestor de Bancos de Sangre (S.G.B.S) Jesús José Navarrete Baca");
            
            if(xcase){
            
                msg.setText("***Inicio de Sesion Exitoso***\n"+ "Aviso: "+Main.usuario.nombre + " " +Main.usuario.apellido + " ha iniciado Sesion Satisfactoriamente en el Sistema.");
            }
            else{
                msg.setText("***Intento de Inicio de Sesion Fallido***\n Aviso: Intruso ha intentado acceder al sistema de informacion.");
            }
            
            Transport.send(msg);
            
            flag = true;            
            
        }
        catch(MessagingException ex){            
            System.out.println(ex.getMessage());
        }
        
        return flag;
    }
                    
    
    private void displayNotify(boolean flag){
        
        this.title = "Notificación de Inicio de Sesión";
        
        if(!flag){
            n = NotificationType.WARNING;
            this.message = "Ha ocurrido un Error al Enviar Correo de Notificacion.";              
        }
        else{
            n = NotificationType.SUCCESS;
            this.message = "Mensaje de Notificacion Enviado a Correo(s) Notificable(s)";            
        }
                                                                   
        TrayNotification tray = new TrayNotification();        
        tray.setTitle(title);
        tray.setMessage(this.message);
        tray.setAnimationType(AnimationType.SLIDE);
        tray.setNotificationType(n);
        tray.showAndDismiss(Duration.seconds(15));
    }
}
