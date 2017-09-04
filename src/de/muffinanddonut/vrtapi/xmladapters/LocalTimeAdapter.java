package de.muffinanddonut.vrtapi.xmladapters;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalTime;

public class LocalTimeAdapter extends XmlAdapter<String, LocalTime>{
    @Override
    public LocalTime unmarshal(String s) throws Exception {
        return LocalTime.parse(s);
    }

    @Override
    public String marshal(LocalTime localTime) throws Exception {
        return localTime.toString();
    }
}
