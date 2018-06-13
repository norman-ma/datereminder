/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datereminder.util;

import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventAttendee;
import com.google.api.services.calendar.model.EventDateTime;
import com.google.api.services.calendar.model.EventReminder;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

/**
 *
 * @author namro_000
 */
public class Reminder {
    
    public static Event set(String client, String desc, LocalDateTime date){
        Event event = new Event()
                .setSummary(desc)
                .setLocation("22G Old Hope Road, Kingston 5, St Andrew, Jamaica");
        
        DateTime startDate = new DateTime(date.format(DateTimeFormatter.ISO_DATE));
        EventDateTime start = new EventDateTime()
                .setDate(startDate)
                .setTimeZone("America/Jamaica");
        event.setStart(start);
        
        DateTime endDate = new DateTime(date.format(DateTimeFormatter.ISO_DATE));
        EventDateTime end = new EventDateTime()
                .setDate(endDate)
                .setTimeZone("America/Jamaica");
        event.setEnd(end);
        
        EventAttendee[] attendees = new EventAttendee[]{
            new EventAttendee().setEmail("philip@bernardlawja.com"),
            new EventAttendee().setEmail("info@bernardlawja.com"),
        };
        event.setAttendees(Arrays.asList(attendees));
        
        EventReminder[] reminderOverrides = new EventReminder[] {
            new EventReminder().setMethod("email").setMinutes(24 * 60),
            new EventReminder().setMethod("popup").setMinutes(10),
        };
        
        Event.Reminders reminders = new Event.Reminders()
            .setUseDefault(false)
            .setOverrides(Arrays.asList(reminderOverrides));
        event.setReminders(reminders);
       
        return event;
    }
}
