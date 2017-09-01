package de.muffinanddonut.vrtapi;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "efa")
public class CoordRequestResponse {

    @XmlElement(name="ci")
    public XmlReplyBlock replyBlock = null;

}
class XmlReplyBlock {
    @XmlElement(name="pis")
    public XmlBusStopBlock busStopsBlock = null;

}
class XmlBusStopBlock {
    @XmlElement(name="pi")
    public List<BusStop> busStops = null;
}
