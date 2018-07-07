/*************************************************************************
 *
 *  Integrantes:
 *      Leonardo Daneu Lopes        8516816
 *      Lucas Sung Jun Hong         8124329
 *
 *  Compilation:  javac-algs4 EP4.java
 *  Execution:    java-algs4 EP4 map.osm-LOCATION.xml LOCATION.adjlist
 *  Dependencies: Digraph.java DijkstraSP.java EdgeWeightedDigraph.java GeoInfo.java Location.java ST.java 
 *                  SymbolGeoEWDigraph.java SymbolEWDigraph.java gistfile1.py xmltoadj.py
 *  
 *  % javac-algs4 EP4.java && java-algs4 EP4 map.osm-USP.xml USP.adjlist
 *
 *************************************************************************/

public class EP4 { 

    public static void main(String[] args) {

        SymbolGeoEWDigraph symbolGeo = new SymbolGeoEWDigraph(args[1]);
        symbolGeo.geoInfo.runScript(args[0], symbolGeo.geoInfo.st);

        double  mouse_x, mouse_y;
        double  zoom_x,  zoom_y;

        // impressao do mapa inteiro
        double y0 = symbolGeo.geoInfo.findMinLat(symbolGeo.geoInfo.st);
        double x0 = symbolGeo.geoInfo.findMinLongt(symbolGeo.geoInfo.st);       
        double y1 = symbolGeo.geoInfo.findMaxLat(symbolGeo.geoInfo.st);
        double x1 = symbolGeo.geoInfo.findMaxLongt(symbolGeo.geoInfo.st);

        // correção para escala da imagem
        if ( (x1 - x0) > (y1 - y0) )        y1 = y0 + (x1 - x0);
        else                                x1 = x0 + (y1 - y0);
        
        // impressao em janela
        StdDraw.setXscale(x0, x1);
        StdDraw.setYscale(y0, y1);

        for (String s : symbolGeo.geoInfo.st.keys()) {
            Location localizacao = symbolGeo.geoInfo.st.get(s);
            StdDraw.point(localizacao.longitude, localizacao.latitude);
        }

        while (true) {

            StdOut.println("--------------------------------------------------------------------");
            StdOut.println(" Opções:");
            StdOut.println("    (1) drawRegion      - Definir região a ser desenhado;");
            StdOut.println("    (2) update          - Atualizar imagem;");
            StdOut.println("    (30) zoom           - Zoom IN;");
            StdOut.println("    (31) zoom           - Zoom OUT;");
            StdOut.println("    (4) arestas ON/OFF  - Desenhar arestas;");
            StdOut.println("    (5) searchPath      - Encontrar caminho entre origem/destino;");
            StdOut.println("    (6) clean           - Limpar o mapa;");
            StdOut.println("    (0) exit            - Terminar o programa.");
            StdOut.println("--------------------------------------------------------------------");
            int input = StdIn.readInt();

            switch(input) {

                case 1:
                    StdOut.println("opção: (1) drawRegion - Definir região a ser desenhado\n");
                    
                    StdOut.println("Entre com os dois pares de pontos para o enquadramento da imagem:");
                    StdOut.println("Formato requerido: latitude longitude (entre com o par separado por um espaço)");
                    StdOut.println("Entre com o primeiro ponto >> ");
                    x0 = StdIn.readDouble(); y0 = StdIn.readDouble();
                    StdOut.println("Entre com o segundo ponto >> ");
                    x1 = StdIn.readDouble(); y1 = StdIn.readDouble();

                    StdDraw.clear();
                    // impressao em janela
                    StdDraw.setXscale(x0, x1);
                    StdDraw.setYscale(y0, y1);

                    for (String s : symbolGeo.geoInfo.st.keys()) {
                        Location localizacao = symbolGeo.geoInfo.st.get(s);
                        StdDraw.point(localizacao.longitude, localizacao.latitude);
                    }
                    break;
            
                case 2:
                    StdOut.println("opção: (2) update - Atualizar imagem\n");
                    
                    StdDraw.clear();

                    for (String s : symbolGeo.geoInfo.st.keys()) {
                        Location localizacao = symbolGeo.geoInfo.st.get(s);
                        StdDraw.point(localizacao.longitude, localizacao.latitude);
                    }
                    break;

                // ZOOM IN
                case 30:
                    // correção para escala da imagem
                    if ( (x1 - x0) > (y1 - y0) )        y1 = y0 + (x1 - x0);
                    else                                x1 = x0 + (y1 - y0); 

                    StdOut.println("Indique com o mouse o local que deseja fazer ZOOM IN");
                    while (true) {
                        mouse_x = StdDraw.mouseX();
                        mouse_y = StdDraw.mouseY();
                        // mouse click
                        if (StdDraw.mousePressed()) {
                            zoom_x = mouse_x; zoom_y = mouse_y;
                            double d = 0.35 * (x1 - x0);
                            // atualizacao
                            x1 = zoom_x + d; x0 = zoom_x - d;
                            y1 = zoom_y + d; y0 = zoom_y - d;
                            break;
                        }
                    }

                    StdOut.println("local zoom (latitude/longitude): " + zoom_y + " / " + zoom_x);
                    StdDraw.clear();
                    // impressao em janela
                    StdDraw.setXscale(x0, x1);
                    StdDraw.setYscale(y0, y1);

                    for (String s : symbolGeo.geoInfo.st.keys()) {
                        Location localizacao = symbolGeo.geoInfo.st.get(s);
                        StdDraw.point(localizacao.longitude, localizacao.latitude);
                    }

                    break;

                // ZOOM OUT
                case 31:
                    // correção para escala da imagem
                    if ( (x1 - x0) > (y1 - y0) )        y1 = y0 + (x1 - x0);
                    else                                x1 = x0 + (y1 - y0); 

                    StdOut.println("Indique com o mouse o local que deseja fazer ZOOM OUT");
                    while (true) {
                        mouse_x = StdDraw.mouseX();
                        mouse_y = StdDraw.mouseY();
                        // mouse click
                        if (StdDraw.mousePressed()) {
                            zoom_x = mouse_x; zoom_y = mouse_y;
                            double d = 0.85 * (x1 - x0);
                            // atualizacao
                            x1 = zoom_x + d; x0 = zoom_x - d;
                            y1 = zoom_y + d; y0 = zoom_y - d;
                            break;
                        }
                    }
                    StdOut.println("local zoom (latitude/longitude): " + zoom_y + " / " + zoom_x);
                    StdDraw.clear();
                    // impressao em janela
                    StdDraw.setXscale(x0, x1);
                    StdDraw.setYscale(y0, y1);

                    for (String s : symbolGeo.geoInfo.st.keys()) {
                        Location localizacao = symbolGeo.geoInfo.st.get(s);
                        StdDraw.point(localizacao.longitude, localizacao.latitude);
                    }

                    break;

                case 4:
                    StdOut.println("opção: (4) arestas ON/OFF  - Desenhar arestas");

                    In in = new In(args[1]); 
                    //while (!in.isEmpty()) {
                    while (in.hasNextLine()) {
                        // ignorando comentarios em arquivo file.adjlist
                        String[] a = in.readLine().split("[ ]+|#|([A-Z]+)|([a-z].+)");
                        int a_size = a.length;
                        for (int i = 0; i < a_size; i++) {
                            if ( a_size > 0 && i > 0) {
                                Location localizacao_0 = symbolGeo.geoInfo.st.get( a[0] );
                                Location localizacao_other = symbolGeo.geoInfo.st.get( a[i] );
                                StdDraw.line( localizacao_0.longitude, localizacao_0.latitude, localizacao_other.longitude, localizacao_other.latitude );
                            }
                        }
                    }

                    break;

                case 5:
                    StdOut.println("opção: (5) searchPath - Encontrar caminho entre origem/destino");
                    StdOut.println("Opção secundário: MOUSE");
                    StdOut.println("    OFF (0)");                    
                    StdOut.println("    ON  (1)");
                    boolean mouse_input = StdIn.readBoolean();
                    // se mouse ON
                    if (mouse_input == true) {
                        double  origem_longt,   origem_lat;
                        double  destino_longt,  destino_lat;
                        // origem
                        while (true) {
                            mouse_x = StdDraw.mouseX();
                            mouse_y = StdDraw.mouseY();
                            // mouse click origem
                            if (StdDraw.mousePressed()) {
                                StdDraw.setPenColor(StdDraw.CYAN);
                                origem_longt = mouse_x; origem_lat = mouse_y;
                                StdDraw.filledCircle(origem_longt, origem_lat, .0001);
                                break;
                            }
                        }
                        StdOut.println(origem_longt + " " + origem_lat);

                        // intervalo de tempo entre um clique e outro
                        try {
                            Thread.sleep(100);
                            //Thread.sleep(3000);
                        }catch(InterruptedException ex) {
                            Thread.currentThread().interrupt();
                        }
                        // destino
                        while (true) {
                            mouse_x = StdDraw.mouseX();
                            mouse_y = StdDraw.mouseY();
                            // mouse click destino
                            if (StdDraw.mousePressed()) {
                                StdDraw.setPenColor(StdDraw.RED);
                                destino_longt = mouse_x; destino_lat = mouse_y;
                                StdDraw.filledCircle(destino_longt, destino_lat, .0001);
                                break;
                            }
                        }
                        StdOut.println(destino_longt + " " + destino_lat);

                    }
                    // se mouse OFF
                    else {
                        StdOut.println("ORIGEM (latitude/longitude) >> ");
                        double origem_longt = StdIn.readDouble();   double origem_lat = StdIn.readDouble();
                        StdOut.println("DESTINO (latitude/longitude) >> ");
                        double destino_longt = StdIn.readDouble();  double destino_lat = StdIn.readDouble();
                    }
                    break;

                case 6:
                    StdOut.println("opção: (6) clean - Limpar o mapa");

                    StdDraw.clear();
                    for (String s : symbolGeo.geoInfo.st.keys()) {
                        Location localizacao = symbolGeo.geoInfo.st.get(s);
                        StdDraw.point(localizacao.longitude, localizacao.latitude);
                    }

                    break;

                case 0:
                    StdOut.println("opção: (0) exit - Terminar o programa");
                    System.exit(0);

                default:
                    StdOut.println("Entrada inválida.");
                    break;
            }
        }
    }
}
