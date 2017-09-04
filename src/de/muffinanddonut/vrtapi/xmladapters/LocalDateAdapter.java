package de.muffinanddonut.vrtapi.xmladapters;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class LocalDateAdapter extends XmlAdapter<String, LocalDate> {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.M.yyyy").withLocale(Locale.GERMAN);
    @Override
    public LocalDate unmarshal(String s) throws Exception {
        return LocalDate.parse(s, formatter);
    }

    @Override
    public String marshal(LocalDate localDate) throws Exception {
        return localDate.toString();
    }
}
