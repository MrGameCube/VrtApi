package de.muffinanddonut.vrtapi;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name="efa")
public class DmRequestResponse {


    @XmlElement(name="dps")
    public XmlDeparturesBlock departuresBlock = null;

}

class XmlDeparturesBlock {

    @XmlElement(name="dp")
    public List<Departure> departureList = null;
}

