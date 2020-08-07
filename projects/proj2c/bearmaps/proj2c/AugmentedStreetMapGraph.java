package bearmaps.proj2c;

import bearmaps.hw4.streetmap.Node;
import bearmaps.hw4.streetmap.StreetMapGraph;
import bearmaps.lab9.MyTrieSet;
import bearmaps.proj2ab.Point;
import bearmaps.proj2ab.WeirdPointSet;

import java.util.*;

/**
 * An augmented graph that is more powerful that a standard StreetMapGraph.
 * Specifically, it supports the following additional operations:
 *
 *
 * @author Alan Yao, Josh Hug, ________
 */
public class AugmentedStreetMapGraph extends StreetMapGraph {
    private Map<Point, Long> PointToNode;
    private Map<String, String> cleanToUnclean;
    private WeirdPointSet wps;
    private MyTrieSet names;
    private Map<String, List<Node>> nameToNodes;

    public AugmentedStreetMapGraph(String dbPath) {
        super(dbPath);
        List<Node> nodes = this.getNodes();
        PointToNode = new HashMap<>();
        cleanToUnclean = new HashMap<>();
        nameToNodes = new HashMap<>();
        List<Point> points = new LinkedList<>();
        names = new MyTrieSet();
        for (Node n : nodes) {
            if (n.name() != null) {
                String cleaned = cleanString(n.name());
                if (!names.contains(cleaned)) {
                    names.add(cleaned);
                    cleanToUnclean.put(cleaned, n.name());
                    List<Node> sameName = new LinkedList<>();
                    sameName.add(n);
                    nameToNodes.put(cleaned, sameName);
                } else {
                    nameToNodes.get(cleaned).add(n);
                }
            }
            if (neighbors(n.id()).size() != 0) {
                PointToNode.put(new Point(n.lon(), n.lat()), n.id());
                points.add(new Point(n.lon(), n.lat()));
            }
        }
        wps = new WeirdPointSet(points);
    }


    /**
     * For Project Part II
     * Returns the vertex closest to the given longitude and latitude.
     * @param lon The target longitude.
     * @param lat The target latitude.
     * @return The id of the node in the graph closest to the target.
     */
    public long closest(double lon, double lat) {
        return PointToNode.get(wps.nearest(lon, lat));
    }


    /**
     * For Project Part III (gold points)
     * In linear time, collect all the names of OSM locations that prefix-match the query string.
     * @param prefix Prefix string to be searched for. Could be any case, with our without
     *               punctuation.
     * @return A <code>List</code> of the full names of locations whose cleaned name matches the
     * cleaned <code>prefix</code>.
     */
    public List<String> getLocationsByPrefix(String prefix) {
        List<String> clean = names.keysWithPrefix(cleanString(prefix));
        List<String> unclean = new LinkedList<>();
        for (String s : clean) {
            unclean.add(cleanToUnclean.get(s));
        }
        return unclean;
    }

    /**
     * For Project Part III (gold points)
     * Collect all locations that match a cleaned <code>locationName</code>, and return
     * information about each node that matches.
     * @param locationName A full name of a location searched for.
     * @return A list of locations whose cleaned name matches the
     * cleaned <code>locationName</code>, and each location is a map of parameters for the Json
     * response as specified: <br>
     * "lat" -> Number, The latitude of the node. <br>
     * "lon" -> Number, The longitude of the node. <br>
     * "name" -> String, The actual name of the node. <br>
     * "id" -> Number, The id of the node. <br>
     */
    public List<Map<String, Object>> getLocations(String locationName) {
        List<Node> sameName = nameToNodes.get(cleanString(locationName));
        List<Map<String, Object>> results = new LinkedList<>();
        for (Node n : sameName) {
            Map<String, Object> result = new HashMap<>();
            result.put("lat", n.lat());
            result.put("lon", n.lon());
            result.put("name", n.name());
            result.put("id", n.id());
            results.add(result);
        }
        return results;
    }


    /**
     * Useful for Part III. Do not modify.
     * Helper to process strings into their "cleaned" form, ignoring punctuation and capitalization.
     * @param s Input string.
     * @return Cleaned string.
     */
    private static String cleanString(String s) {
        return s.replaceAll("[^a-zA-Z ]", "").toLowerCase();
    }

}
