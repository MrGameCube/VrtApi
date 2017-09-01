package de.muffinanddonut.vrtapi;

import javax.xml.bind.annotation.XmlElement;

public class BusStop {

    @XmlElement(name="de")
    public String stopName;

    @XmlElement(name = "ty")
    public String type;

    @XmlElement(name = "id")
    public String stopId;

    public String omc;
    public String pid;
    public String locality;
    public String layer;
    public String gisID;
    public String ds;
    public String stateless;

}
