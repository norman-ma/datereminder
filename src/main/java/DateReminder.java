/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import com.google.api.services.calendar.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import datereminder.ui.DateReminderUI;
import datereminder.sys.SystemPreferences;
import datereminder.util.Authorization;
import java.io.IOException;

/**
 *
 * @author namro_000
 */
public class DateReminder {
    public static void main(String[] args) {
        
        try {          
      
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());  
            
            final SystemPreferences system = new SystemPreferences();
            final Calendar calendar = Authorization.getCalendarService();
            /* Create and display the form */
            java.awt.EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    DateReminderUI frame = new DateReminderUI(system,calendar);
                    frame.setTitle("DateReminder");
                    frame.setVisible(true);
                }
            });
        } catch (IOException ex) {
            Logger.getLogger(DateReminder.class.getName()).log(Level.SEVERE, null, ex);
        }   catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(DateReminder.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
