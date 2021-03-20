package controladores;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;
import org.xmlpull.v1.XmlSerializer;

import dominio.Metadatos;
import dominio.NodoArbolReferencias;

public class GeneradorManifiesto {
    
    public static final String BIBLIOTECA_CARPETA =   "output/resources";
    
	private String listadoRecursos;
    private int idRecurso;
    private ArrayList<NodoArbolReferencias> listaRecursos;

    public GeneradorManifiesto() {
        listadoRecursos = "";
        idRecurso = 0;
        listaRecursos = new ArrayList<NodoArbolReferencias>();
    }

    public void generarXML() {

        //create a new file called "new.xml" in the SD card


        File biblioteca = new File(BIBLIOTECA_CARPETA, "BibliotecaObjetosAprendizaje");
        if (!biblioteca.exists()) {
            biblioteca.mkdirs();
        }
        NodoArbolReferencias nodoRaiz = ControladorArbolReferencias.obtenerInstancia().obtenerNodoRaiz();

        File carpetaObjetoParticular = new File(BIBLIOTECA_CARPETA + "/BibliotecaObjetosAprendizaje/", nodoRaiz.obtenerListaNodos().get(0).obtenerNombre());
        if (!carpetaObjetoParticular.exists()) {
            carpetaObjetoParticular.mkdirs();
        }

        //Crear archivo en memoria externa
        File newxmlfile = new File(BIBLIOTECA_CARPETA + "/BibliotecaObjetosAprendizaje/" + nodoRaiz.obtenerListaNodos().get(0).obtenerNombre() + "/imsmanifest.xml");
        //File newObjectFile = new File(Environment.getExternalStorageDirectory()+"/BibliotecaObjetosAprendizaje/"+nodoRaiz.obtenerNombre()+"/"+nodoRaiz.obtenerNombre()+".ser");

        try {
            newxmlfile.createNewFile();
            // newObjectFile.createNewFile();

        } catch (IOException e) {
            //this is android: Log.e("IOException", "exception in createNewFile() method");
        }
        //we have to bind the new file with a FileOutputStream
        FileOutputStream fileos = null;
        try {
            fileos = new FileOutputStream(newxmlfile);
        } catch (FileNotFoundException e) {
            //this is android: Log.e("FileNotFoundException", "can't create FileOutputStream");
        }

        //Crea el archivo de configuracion del objeto que sera leido en otra ocasion

        /*  try {
         FileOutputStream canalEscrituraArchivo = new FileOutputStream(newObjectFile);
         try {
         ObjectOutputStream canalEscrituraObjeto = new ObjectOutputStream(canalEscrituraArchivo);
         canalEscrituraObjeto.writeObject(nodoRaiz);
         canalEscrituraObjeto.close();
         } catch (IOException ex) {
         Logger.getLogger(GeneradorManifiesto.class.getName()).log(Level.SEVERE, null, ex);
         }
         } catch (FileNotFoundException ex) {
         Logger.getLogger(GeneradorManifiesto.class.getName()).log(Level.SEVERE, null, ex);
         }
         */


        //we create a XmlSerializer in order to write xml data
        XmlSerializer archivoXML = XML.newSerializer();



        try {
            //we set the FileOutputStream as output for the serializer, using UTF-8 encoding
            archivoXML.setOutput(fileos, "UTF-8");

            //Write <?xml declaration with encoding (if encoding not null) and standalone flag (if standalone not null) 
            archivoXML.startDocument(null, Boolean.valueOf(true));

            //set indentation option
            //archivoXML.setFeature("http://xmlpull.org/v1/doc/features.html#indent-output", true); 
            //start a tag called "root"

            /*
             * 
             * <manifest identifier="com.scorm.golfsamples.contentpackaging.multioscosinglefile.12" version="1"
             xmlns="http://www.imsproject.org/xsd/imscp_rootv1p1p2"
             xmlns:adlcp="http://www.adlnet.org/xsd/adlcp_rootv1p2"
             xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
             xsi:schemaLocation="http://www.imsproject.org/xsd/imscp_rootv1p1p2 imscp_rootv1p1p2.xsd
             http://www.imsglobal.org/xsd/imsmd_rootv1p2p1 imsmd_rootv1p2p1.xsd
             http://www.adlnet.org/xsd/adlcp_rootv1p2 adlcp_rootv1p2.xsd">
  
             <!--The metadata node simply declares which SCORM version this course operates under.-->
             <metadata>
             <schema>ADL SCORM</schema>
             <schemaversion>1.2</schemaversion>
             </metadata>
             */


            archivoXML.startTag(null, "manifest");
            archivoXML.attribute(null, "identifier", nodoRaiz.obtenerListaNodos().get(0).obtenerNombre() + "_manifest");
            archivoXML.attribute(null, "version", "1");
            archivoXML.attribute(null, "xmlns", "http://www.imsproject.org/xsd/imscp_rootv1p1p2");
            archivoXML.attribute(null, "xmlns:adlcp", "http://www.adlnet.org/xsd/adlcp_rootv1p2");
            archivoXML.attribute(null, "xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
            archivoXML.attribute(null, "xsi:schemaLocation", "http://www.imsproject.org/xsd/imscp_rootv1p1p2 imscp_rootv1p1p2.xsd"
                    + " http://www.imsglobal.org/xsd/imsmd_rootv1p2p1 imsmd_rootv1p2p1.xsd"
                    + " http://www.adlnet.org/xsd/adlcp_rootv1p2 adlcp_rootv1p2.xsd");
            archivoXML.startTag(null, "metadata");
            archivoXML.startTag(null, "schema");
            archivoXML.text("ADL SCORM");
            archivoXML.endTag(null, "schema");
            archivoXML.startTag(null, "schemaversion");
            archivoXML.text("1.2");
            archivoXML.endTag(null, "schemaversion");
            archivoXML.endTag(null, "metadata");


            //<organizations default="golf_sample_default_org">
            //<organization identifier="golf_sample_default_org">

            archivoXML.startTag(null, "organizations");
            archivoXML.attribute(null, "default", "orgs_"+nodoRaiz.obtenerListaNodos().get(0).obtenerNombre());
            archivoXML.startTag(null, "organization");
            archivoXML.attribute(null, "identifier", "org_"+nodoRaiz.obtenerListaNodos().get(0).obtenerNombre());


            archivoXML.startTag(null, "title");
            archivoXML.text(nodoRaiz.obtenerListaNodos().get(0).obtenerNombre());
            archivoXML.endTag(null, "title");

            archivoXML = insertarItems(archivoXML, nodoRaiz);

            archivoXML.endTag(null, "organization");

            archivoXML.endTag(null, "organizations");
            archivoXML = insertarRecursos(archivoXML, nodoRaiz);
            archivoXML.endTag(null, "manifest");


            archivoXML.endDocument();
            //write xml data into the FileOutputStream
            archivoXML.flush();
            //finally we close the file stream
            fileos.close();
            /* 
             String[] archivos=new String[0];
             String path=Environment.getExternalStorageDirectory()+"/BibliotecaObjetosAprendizaje/"+nodoRaiz.obtenerNombre();
             archivos[0]= path+"/imsmanifest.xml";
                          
             //zip(archivos,path+"/objeto.zip");
             Compress comprimir = new Compress(archivos,path+"/objeto.zip");
             comprimir.zip();
             */
            //Toast.makeText(
                    //getBaseContext(),
                    //"Objeto guardado con exito",
                    //Toast.LENGTH_SHORT).show();
            //  TextView tv = (TextView)this.findViewById(R.id.result);
            //	tv.setText("file has been created on SD card");
        } catch (Exception e) {
            //Log.e("Exception", "error occurred while creating xml file");
        }

        String rutaManifiesto = BIBLIOTECA_CARPETA + "/BibliotecaObjetosAprendizaje/" + nodoRaiz.obtenerListaNodos().get(0).obtenerNombre() + "/imsmanifest.xml";
        String rutaZip = BIBLIOTECA_CARPETA + "/BibliotecaObjetosAprendizaje/" + nodoRaiz.obtenerListaNodos().get(0).obtenerNombre() + "/" + nodoRaiz.obtenerListaNodos().get(0).obtenerNombre() + ".zip";
        CompresorArchivos compresor = new CompresorArchivos(rutaManifiesto, rutaZip);
        compresor.zip();

    }

    private XmlSerializer insertarItems(XmlSerializer archivoXML, NodoArbolReferencias nodo) {

        NodoArbolReferencias nodoActual;
        if (nodo.obtenerNumeroNodosHijos() > 0) {
            for (int i = 0; i < nodo.obtenerNumeroNodosHijos(); i++) {
                nodoActual = nodo.obtenerListaNodos().get(i);

                Metadatos metadatos = nodoActual.obtenerMetadatos();
                if (nodoActual.obtenerTipo() == true) {
                    //es estructura
                    try {
                        archivoXML.startTag(null, "item");
                        archivoXML.attribute(null, "identifier", nodoActual.obtenerNombre());
                        archivoXML.startTag(null, "title");
                        archivoXML.text(nodoActual.obtenerNombre());
                        archivoXML.endTag(null, "title");
                        archivoXML = insertarItems(archivoXML, nodoActual);
                        insertarMetadatosDelItem(archivoXML, metadatos);
                        archivoXML.endTag(null, "item");
                    } catch (IOException ex) {
                        //Logger.getLogger(GeneradorManifiesto.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IllegalArgumentException ex) {
                        //Logger.getLogger(GeneradorManifiesto.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IllegalStateException ex) {
                        //Logger.getLogger(GeneradorManifiesto.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    //es recurso
                    try {
                        archivoXML.startTag(null, "item");
                        archivoXML.attribute(null, "identifier", "identificador0".concat(String.valueOf(idRecurso)));

                        // archivoXML.attribute(null, "identifier", nodoActual.obtenerNombre());
                        if (idRecurso < 10) {
                            archivoXML.attribute(null, "identifierref", "identificador0".concat(String.valueOf(idRecurso)) + "_recurso");
                        } else {
                            archivoXML.attribute(null, "identifierref", "identificador".concat(String.valueOf(idRecurso)) + "_recurso");
                        }

                        // archivoXML.attribute(null, "identifierref", "recurso_no_".concat(String.valueOf(idRecurso)));
                        archivoXML.startTag(null, "title");
                        archivoXML.text(nodoActual.obtenerNombre());
                        archivoXML.endTag(null, "title");
                        archivoXML = insertarItems(archivoXML, nodoActual);
                        insertarMetadatosDelItem(archivoXML, metadatos);
                        archivoXML.endTag(null, "item");

                        listaRecursos.add(nodoActual);
                        idRecurso++;

                    } catch (IOException ex) {
                        //Logger.getLogger(GeneradorManifiesto.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IllegalArgumentException ex) {
                        //Logger.getLogger(GeneradorManifiesto.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IllegalStateException ex) {
                        //Logger.getLogger(GeneradorManifiesto.class.getName()).log(Level.SEVERE, null, ex);
                    }





                }


            }


        }

        return archivoXML;
    }

    private XmlSerializer insertarRecursos(XmlSerializer archivoXML, NodoArbolReferencias nodo) {
        try {
            archivoXML.startTag(null, "resources");
            for (int i = 0; i < listaRecursos.size(); i++) {

                archivoXML.startTag(null, "resource");
                if (i < 10) {
                    archivoXML.attribute(null, "identifier", "identificador0".concat(String.valueOf(i)) + "_recurso");
                } else {
                    archivoXML.attribute(null, "identifier", "identificador".concat(String.valueOf(i)) + "_recurso");
                }

                archivoXML.attribute(null, "type", "webcontent");
                archivoXML.attribute(null, "adlcp:scormtype", "asset");
                archivoXML.attribute(null, "href", listaRecursos.get(i).obtenerURL());
                archivoXML.endTag(null, "resource");
            }
            archivoXML.endTag(null, "resources");

        } catch (IOException ex) {
            //Logger.getLogger(GeneradorManifiesto.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            //Logger.getLogger(GeneradorManifiesto.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalStateException ex) {
            //Logger.getLogger(GeneradorManifiesto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return archivoXML;
    }
    /*
     public void zip(String[] files, String zipFile) throws IOException {
     BufferedInputStream origin = null;
     ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(zipFile)));
     try { 
     int BUFFER_SIZE=2048;
     byte data[] = new byte[BUFFER_SIZE];

     for (int i = 0; i < files.length; i++) {
     FileInputStream fi = new FileInputStream(files[i]);    
     origin = new BufferedInputStream(fi, BUFFER_SIZE);
     try {
     ZipEntry entry = new ZipEntry(files[i].substring(files[i].lastIndexOf("/") + 1));
     out.putNextEntry(entry);
     int count;
     while ((count = origin.read(data, 0, BUFFER_SIZE)) != -1) {
     out.write(data, 0, count);
     }
     }
     finally {
     origin.close();
     }
     }
     }
     finally {
     out.close();
     }
     }

    
     
     */

    public void generarArchivoArbolSerializable() {
        NodoArbolReferencias nodoRaiz = ControladorArbolReferencias.obtenerInstancia().obtenerNodoRaiz();

        File carpetaObjetoParticular = new File(BIBLIOTECA_CARPETA + "/BibliotecaObjetosAprendizaje/", nodoRaiz.obtenerListaNodos().get(0).obtenerNombre());
        if (!carpetaObjetoParticular.exists()) {
            carpetaObjetoParticular.mkdirs();
        }

        //Crear archivo en memoria externa
        File nuevoArchivoArbolSerializable = new File(BIBLIOTECA_CARPETA + "/BibliotecaObjetosAprendizaje/" + nodoRaiz.obtenerListaNodos().get(0).obtenerNombre() + "/" + nodoRaiz.obtenerListaNodos().get(0).obtenerNombre() + ".ser");
        try {

            nuevoArchivoArbolSerializable.createNewFile();

        } catch (IOException e) {
            //Log.e("IOException", "exception in createNewFile() method");
        }

        try {
            FileOutputStream canalEscrituraArchivo = new FileOutputStream(nuevoArchivoArbolSerializable);
            try {
                ObjectOutputStream canalEscrituraObjeto = new ObjectOutputStream(canalEscrituraArchivo);
                canalEscrituraObjeto.writeObject(nodoRaiz);
                canalEscrituraObjeto.close();
            } catch (IOException ex) {
                //Logger.getLogger(GeneradorManifiesto.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (FileNotFoundException ex) {
            //Logger.getLogger(GeneradorManifiesto.class.getName()).log(Level.SEVERE, null, ex);
        }


    }

    public NodoArbolReferencias obtenerArbolDeArchivo(String nombreArbol) {
        File archivo = new File(BIBLIOTECA_CARPETA + "/BibliotecaObjetosAprendizaje/" + nombreArbol + "/" + nombreArbol + ".ser");
        //Log.e(Environment.getExternalStorageDirectory() + "/BibliotecaObjetosAprendizaje/" + nombreArbol + "/" + nombreArbol + ".ser", Environment.getExternalStorageDirectory() + "/BibliotecaObjetosAprendizaje/" + nombreArbol + "/" + nombreArbol + ".ser");
        NodoArbolReferencias nodoRaizObtenido = null;
        try {
            FileInputStream canalLecturaArchivo = null;
            canalLecturaArchivo = new FileInputStream(archivo);
            ObjectInputStream canalLecturaObjeto;
            try {

                canalLecturaObjeto = new ObjectInputStream(canalLecturaArchivo);

                try {

                    nodoRaizObtenido = (NodoArbolReferencias) canalLecturaObjeto.readObject();
                    return nodoRaizObtenido;
                    //System.out.println(nodoRaizObtenido.obtenerListaNodos().get(0).obtenerNombre());

                } catch (ClassNotFoundException ex) {
                    // Logger.getLogger(SerializacionArboles.class.getName()).log(Level.SEVERE, null, ex);
                }
                canalLecturaObjeto.close();
            } catch (IOException ex) {
                //Logger.getLogger(SerializacionArboles.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (FileNotFoundException ex) {
            //Logger.getLogger(GeneradorManifiesto.class.getName()).log(Level.SEVERE, null, ex);
            //Logger.getLogger(SerializacionArboles.class.getName()).log(Level.SEVERE, null, ex);
        }

        return nodoRaizObtenido;



    }

    private XmlSerializer insertarMetadatosDelItem(XmlSerializer archivoXML, Metadatos metadatos) {
        try {
            archivoXML.startTag(null, "metadata");
            archivoXML.startTag(null, "lom");
            archivoXML.attribute(null, "xmlns", "http://ltsc.ieee.org/xsd/LOM");
            archivoXML.attribute(null, "xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
            archivoXML.attribute(null, "xsi:schemaLocation", "http://ltsc.ieee.org/xsd/LOM lom.xsd");

            archivoXML.startTag(null, "general");

            archivoXML.startTag(null, "title");
            archivoXML.text(metadatos.getGenerales().get("title"));
            archivoXML.endTag(null, "title");

            archivoXML.startTag(null, "description");
            archivoXML.text(metadatos.getGenerales().get("description"));
            archivoXML.endTag(null, "description");

            archivoXML.startTag(null, "keyword");
            archivoXML.text(metadatos.getGenerales().get("keyword"));
            archivoXML.endTag(null, "keyword");
            
            
            archivoXML.startTag(null, "coverage");
            archivoXML.text(metadatos.getGenerales().get("coverage"));
            archivoXML.endTag(null, "coverage");


            archivoXML.endTag(null, "general");



            archivoXML.startTag(null, "technical");

            archivoXML.startTag(null, "size");
            archivoXML.text(metadatos.getTecnicos().get("size"));
            archivoXML.endTag(null, "size");

            archivoXML.startTag(null, "location");
            archivoXML.text(metadatos.getTecnicos().get("location"));
            archivoXML.endTag(null, "location");

            archivoXML.startTag(null, "requirement");

                    archivoXML.startTag(null, "type");
                    archivoXML.text(metadatos.getTecnicos().get("type"));
                    archivoXML.endTag(null, "type");

            archivoXML.endTag(null, "requirement");

            archivoXML.endTag(null, "technical");
            
            
            archivoXML.startTag(null, "lifeCycle");
                archivoXML.startTag(null, "version");
                archivoXML.text(metadatos.getCiclicos().get("version"));
                archivoXML.endTag(null, "version");

                archivoXML.startTag(null, "status");
                archivoXML.text(metadatos.getCiclicos().get("status"));
                archivoXML.endTag(null, "status");

                archivoXML.startTag(null, "contribute");
                    
                                archivoXML.startTag(null, "rol");
                                archivoXML.text(metadatos.getCiclicos().get("rol"));
                                archivoXML.endTag(null, "rol");

                                archivoXML.startTag(null, "entity");
                                archivoXML.text(metadatos.getCiclicos().get("entity"));
                                archivoXML.endTag(null, "entity");

                                archivoXML.startTag(null, "date");
                                archivoXML.text(metadatos.getCiclicos().get("date"));
                                archivoXML.endTag(null, "date");
                
                archivoXML.endTag(null, "contribute");
            
            archivoXML.endTag(null, "lifeCycle");
            
            archivoXML.endTag(null, "lom");
            archivoXML.endTag(null, "metadata");


        } catch (IOException ex) {
            //Logger.getLogger(GeneradorManifiesto.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            //Logger.getLogger(GeneradorManifiesto.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalStateException ex) {
            //Logger.getLogger(GeneradorManifiesto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return archivoXML;
    }
    
    
    static class XmlSerializerFactory {
        static final String TYPE
                = "org.kxml2.io.KXmlParser,org.kxml2.io.KXmlSerializer";
        static final XmlPullParserFactory instance;
        static {
            try {
                instance = XmlPullParserFactory.newInstance(TYPE, null);
            } catch (XmlPullParserException e) {
                throw new AssertionError(e);
            }
        }
    }
    
    private static class XML{
    	public static XmlSerializer newSerializer() {
            try {
                return XmlSerializerFactory.instance.newSerializer();
            } catch (XmlPullParserException e) {
                throw new AssertionError(e);
            }
        }
    }
    
}
