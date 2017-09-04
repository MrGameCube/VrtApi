package de.muffinanddonut.vrtapi;

import de.muffinanddonut.vrtapi.xmladapters.LocalDateAdapter;
import de.muffinanddonut.vrtapi.xmladapters.LocalTimeAdapter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;
import java.time.LocalTime;

public class Departure {
    @XmlElement(name="n")
    public String stopName;

    @XmlElement(name = "dt")
    public DepartureTime departureTime;

    @XmlElement(name="m")
    public DepartureMetadata departureMetadata;
}
class DepartureTime {

    @XmlElement(name = "da")
    @XmlJavaTypeAdapter(value = LocalDateAdapter.class)
    public LocalDate plannedDate;

    @XmlElement(name = "t")
    @XmlJavaTypeAdapter(value = LocalTimeAdapter.class)
    public LocalTime plannedTime;

    @XmlElement(name = "rda")
    @XmlJavaTypeAdapter(value = LocalDateAdapter.class)
    public LocalDate realDate;

    @XmlElement(name = "rt")
    @XmlJavaTypeAdapter(value = LocalTimeAdapter.class)
    public LocalTime realTime;

}
class DepartureMetadata {
    @XmlElement(name = "nu")
    public int lineNumber;

    @XmlElement(name = "des")
    public String destination;
}
