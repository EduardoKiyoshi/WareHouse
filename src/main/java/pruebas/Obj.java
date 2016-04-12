/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pruebas;

import java.util.Vector;
import static sun.dc.pr.PathFiller.MAX_PATH;

    class Punto
    {
        float x;
        float y;
        float z;
    };
    class Coord
    {
        float i;
        float j;
    };
    class Cara
    {
        int vi;
        int vt;
        int vn;
        int puntos;
    };
    class Textura
    {
        int dataPos;     // Position in the file where the actual data begins
        int width, height;
        int imageSize;   // = width*height*3
        int points;
        // Actual RGB data
        char data;
    };
    class Material
    {
        String nombre;

        Textura textura;
        int idTexture;

        float[] difusa=new float[3];
        float[] especular=new float[3];
        float[] ambiente=new float[3];
        float shininess;
        Vector<Cara> caras;
    };
public class Obj {

}
