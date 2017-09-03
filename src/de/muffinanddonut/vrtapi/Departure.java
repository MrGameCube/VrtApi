package de.muffinanddonut.vrtapi;

import javax.xml.bind.annotation.XmlElement;

public class Departure {
    @XmlElement(name="n")
    public String stopName;
}
