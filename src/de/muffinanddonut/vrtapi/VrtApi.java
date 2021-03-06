package de.muffinanddonut.vrtapi;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;

/**
 *   This class represents the Web API used by the VRT-App
 */
public class VrtApi {

    /**
     * The base Url used by the API
     */
    private static final String baseUrl = "http://efa9-5.vrn.de/vrt_ultra/";

    /**
     * The API Endpoint used for a Coord-Request
     */
    private static final String COORD_REQUEST_ENDPOINT = "XML_COORD_REQUEST";

    /**
     * The API Endpoint user for a DM Request
     */
    private static final String DM_REQUEST_ENDPOINT = "XML_DM_REQUEST";

    /**
     * This Method queries the VRT API for 5 closest Bus Stops in a given radius
     * @param longitude The longitude to be queried
     * @param latitude The latitude to be queried
     * @param radius The radius in which to query
     * @return The API Response not {@code null}
     * @throws IOException Error in the connection to the API
     * @throws JAXBException Error in parsing of the response
     */
    public static CoordRequestResponse queryBusStopsByCoordinates(double longitude, double latitude, int radius) throws IOException, JAXBException {
        String coord = formatCoordinates(longitude, latitude);
        String query = String.format("coord=%s&max=%d&inclFilter=1&radius_1=%d&type_1=STOP", coord, 5, radius);

        BufferedReader responseReader = queryUrl(query, COORD_REQUEST_ENDPOINT);

        JAXBContext jaxbContext = JAXBContext.newInstance(CoordRequestResponse.class);
        Unmarshaller jaxUnmarshaller = jaxbContext.createUnmarshaller();
        CoordRequestResponse response = (CoordRequestResponse) jaxUnmarshaller.unmarshal(responseReader);
        responseReader.close();
        return response;
    }



    /**
     * This Method queries the VRT API for 5 closest Bus Stops in a radius of 1 kilometer
     * @param longitude The longitude to be queried
     * @param latitude The latitude to be queried
     * @return The API Response not {@code null}
     * @throws IOException Error in the connection to the API
     * @throws JAXBException Error in parsing of the response
     */
    public static CoordRequestResponse queryBusStopsByCoordinates(double longitude, double latitude) throws IOException, JAXBException {
        return queryBusStopsByCoordinates(longitude, latitude, 1000);
    }

    /**
     * This Method queries the VRT API for the next 10 departures at a given bus stop.
     * @param busStopId The id of the bus stop
     * @return The next 10 departures at the stop not {@code null}
     * @throws IOException Error in the connection to the API
     * @throws JAXBException Error in parsing of the response
     */
    public static DmRequestResponse queryDeparturesByBusStop(String busStopId) throws IOException, JAXBException {
        int limit =10;
        String query = String.format("mode=direct&coordOutputFormat=wgs84&mergeDep=1&useAllStops=1&maxTimeLoop=1&" +
                "canChangeMOT=0&useRealtime=1&locationServerActive=1&depType=stopEvents&includeCompleteStopSeq=1&" +
                "name_dm=%s&type_dm=stop&changeSpeed=normal&deleteAssignedStops=1&lineRestriction=400&excludedMeans=checkbox&" +
                "imparedOptionsActive=1&ptOptionsActive=1&trITMOTvalue100=10&limit=%d&useProxFootSearch=0&itOptionsActive=1&routeType=LEASTTIME",
        busStopId, limit);

        BufferedReader responseReader = queryUrl(query, DM_REQUEST_ENDPOINT);
        JAXBContext jaxbContext = JAXBContext.newInstance(DmRequestResponse.class);

        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        DmRequestResponse dmRequestResponse = (DmRequestResponse) unmarshaller.unmarshal(responseReader);
        responseReader.close();
        return dmRequestResponse;
    }

    /**
     * This Method sends a query to the VRT API
     * @param query the query string to be sent
     * @param endpoint the API endpoint to use (see Constants)
     * @return {@code {@link BufferedReader}} with the response not {@code null}
     * @throws IOException Error in the connection to the API
     */
    private static BufferedReader queryUrl(String query, String endpoint) throws IOException {
        String url = baseUrl+endpoint+"?"+query;
        URLConnection connection = new URL(url).openConnection();
        connection.setRequestProperty("Accept-Charset", StandardCharsets.UTF_8.name());

        return new BufferedReader(new InputStreamReader(connection.getInputStream()));

    }

    /**
     * This Method formats the given coordinates according to the format used by the VRT API.
     * That means it removes the decimal separators and combines them in the string longitude:latitude:wgs84
     * @param longitude The longitude to be formatted
     * @param latitude The latitude to be formatted
     * @return longitude:latitude:wgs84 not {@code null}
     */
    private  static String formatCoordinates(double longitude, double latitude) {
        String longitudeString = String.valueOf(longitude).replaceFirst("[.]","");
        String latitudeString = String.valueOf(latitude).replaceFirst("[.]","");



        return String.format("%s:%s:wgs84",longitude, latitude);
    }

}
