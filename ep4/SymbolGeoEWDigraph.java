public class SymbolGeoEWDigraph {
    public GeoInfo geoInfo;
    public SymbolEWDigraph g;

    public SymbolGeoEWDigraph(String filename) {
        geoInfo = new GeoInfo();
        g = new SymbolEWDigraph(filename, " ");
    }
}
