package controladores;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class CompresorArchivos {
	private static final int BUFFER = 2048;
    private String[] rutasArchivos;
    private String rutaZipDestino;

    public CompresorArchivos(String rutaManifiesto, String rutaZipDestino) {
        //this.rutasArchivos = rutasArchivos; 
        this.rutaZipDestino = rutaZipDestino;
        rutasArchivos = new String[5];
        rutasArchivos[0] = "assets/" + "adlcp_rootv1p2.xsd";
        rutasArchivos[1] = "assets/" + "ims_xml.xsd";
        rutasArchivos[2] = "assets/" + "imscp_rootv1p1p2.xsd";
        rutasArchivos[3] = "assets/" + "imsmd_rootv1p2p1.xsd";
        rutasArchivos[4] = rutaManifiesto;
    }

    public void zip() {
        try {
            BufferedInputStream origin = null;
            FileOutputStream dest = new FileOutputStream(rutaZipDestino);

            ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(dest));

            byte data[] = new byte[BUFFER];

            for (int i = 0; i < rutasArchivos.length; i++) {
                //this is android: Log.e("Compress", "Adding: " + rutasArchivos[i]);
                FileInputStream fi = new FileInputStream(rutasArchivos[i]);
                origin = new BufferedInputStream(fi, BUFFER);
                ZipEntry entry = new ZipEntry(rutasArchivos[i].substring(rutasArchivos[i].lastIndexOf("/") + 1));
                out.putNextEntry(entry);
                int count;
                while ((count = origin.read(data, 0, BUFFER)) != -1) {
                    out.write(data, 0, count);
                }

                origin.close();
            }

            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
