/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utileria;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javafx.util.StringConverter;

/**
 *
 * @author YZ
 */
public class LocalDateStringConverter extends StringConverter<LocalDate> {

    private String pattern;
    private DateTimeFormatter dateFormatter;

    public LocalDateStringConverter(){
        this.pattern = "yyyy-MM-dd";
        setDateFormatter();
    }
    
    public LocalDateStringConverter(String pattern){
        this.pattern = pattern;
        setDateFormatter();
    }
    
    private void setDateFormatter(){
        this.dateFormatter = DateTimeFormatter.ofPattern(pattern);
    }
    @Override
    public String toString(LocalDate date) {
        if (date != null) {
            return dateFormatter.format(date);
        } else {
            return "";
        }
    }

    @Override
    public LocalDate fromString(String string) {
        if (string != null && !string.isEmpty()) {
            return LocalDate.parse(string, dateFormatter);
        } else {
            return null;
        }
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
        setDateFormatter();
    }
}
