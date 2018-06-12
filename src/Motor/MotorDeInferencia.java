/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Motor;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author Carlos
 */
public class MotorDeInferencia {
    
    public void leer(){
        //Variables
        String nombrePuesto = "Programador(practicante)";
        int PuntajePsico = 60;
        String nivelPsico = String.valueOf(PuntajePsico);
        int PuntajeCono = 60;
        String nivelCono = String.valueOf(PuntajeCono);
        int PuntajeEdu = 2;
        String nivelEdu = String.valueOf(PuntajeEdu);
        int PuntajeHabla = 0;
        String nivelHabla = String.valueOf(PuntajeHabla);
        int PuntajeLectura = 0;
        String nivelLectura = String.valueOf(PuntajeLectura);
        int PuntajeEscritura = 0;
        String nivelEscritura = String.valueOf(PuntajeEscritura);

        Software software = new Software();
        List<Software> listSoftwarePostulante = new ArrayList<>();
        Software software1 = new Software("Android", 0);
        Software software2 = new Software("C", 0);
        Software software3 = new Software("Visual Basic", 0);

        listSoftwarePostulante.add(software1);
        listSoftwarePostulante.add(software2);
        listSoftwarePostulante.add(software3);
        
        Competencia competencia = new Competencia();
        List<Competencia> listCompetenciaPostulante = new ArrayList<>();
        Competencia competencia1 = new Competencia("Analisis y sintesis",0);
        Competencia competencia2 = new Competencia("Mejora continua",1);
        Competencia competencia3 = new Competencia("Metodologia para la calidad",0);
        Competencia competencia4 = new Competencia("Trabajo en equipo",0);
        
        listCompetenciaPostulante.add(competencia1);
        listCompetenciaPostulante.add(competencia2);
        listCompetenciaPostulante.add(competencia3);
        listCompetenciaPostulante.add(competencia4);

        try {
            File archivo = new File("test/Reglas 4.xml");
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = dbf.newDocumentBuilder();
            Document document = documentBuilder.parse(archivo);
            document.getDocumentElement().normalize();
            System.out.println("Elemento raiz:" + document.getDocumentElement().getNodeName()); //Elemento raiz : Reglas
            NodeList listaReglas = document.getElementsByTagName("regla");
            for (int tempRegla = 0; tempRegla < listaReglas.getLength(); tempRegla++) {
                Node nodoRegla = listaReglas.item(tempRegla);
                System.out.println("Elemento:" + nodoRegla.getNodeName()); //Elemento : regla
                Element elementRegla = (Element) nodoRegla;
                NodeList listPuesto = elementRegla.getElementsByTagName("Puesto");
                for (int tempPuesto = 0; tempPuesto < listPuesto.getLength(); tempPuesto++) {
                    Node nodoPuesto = listPuesto.item(tempPuesto);
                    Element elementPuesto = (Element) nodoPuesto;
                    System.out.println("nombre Puesto: " + elementPuesto.getAttribute("nombre"));
                    if (elementPuesto.getAttribute("nombre").equals(nombrePuesto)) {
                        NodeList listTestPsico = elementPuesto.getElementsByTagName("test_psicologico");
                        for (int tempPsico = 0; tempPsico < listTestPsico.getLength(); tempPsico++) {
                            Node nodoPsico = listTestPsico.item(tempPsico);
                            Element elementPsico = (Element) nodoPsico;
                            System.out.println("test Psico: " + elementPsico.getAttribute("nivel"));
                            System.out.println("Numero de conclusiones Psico: " + elementPsico.getElementsByTagName("Conclusion").getLength());
                            if (elementPsico.getAttribute("nivel").equals(nivelPsico)) {
                                if (obtenerPuntaje(elementPsico) != -1) {
                                    System.out.println("Puntaje Psico: " + obtenerPuntaje(elementPsico));
                                } else {
                                    //Test Conocimiento
                                    NodeList listTestCono = elementPsico.getElementsByTagName("test_conocimiento");
                                    for (int tempCono = 0; tempCono < listTestCono.getLength(); tempCono++) {
                                        Node nodoCono = listTestCono.item(tempCono);
                                        Element elementCono = (Element) nodoCono;
                                        System.out.println("test Cono: " + elementCono.getAttribute("nivel"));
                                        System.out.println("Numero de conclusiones Cono: " + elementPsico.getElementsByTagName("Conclusion").getLength());
                                        if (elementCono.getAttribute("nivel").equals(nivelCono)) {
                                            if (obtenerPuntaje(elementCono) != -1) {
                                                System.out.println("Puntaje Cono: " + obtenerPuntaje(elementCono));
                                            } else {
                                                //Educacion
                                                NodeList listEducacion = elementCono.getElementsByTagName("Educacion");
                                                for (int tempEdu = 0; tempEdu < listEducacion.getLength(); tempEdu++) {
                                                    Node nodoEdu = listEducacion.item(tempEdu);
                                                    Element elementEdu = (Element) nodoEdu;
                                                    System.out.println("Educacion: " + elementEdu.getAttribute("nivel"));
                                                    System.out.println("Numero de conclusiones Edu: " + elementEdu.getElementsByTagName("Conclusion").getLength());
                                                    if (elementEdu.getAttribute("nivel").equals(nivelEdu)) {
                                                        if (obtenerPuntaje(elementEdu) != -1) {
                                                            System.out.println("Puntaje Edu: " + obtenerPuntaje(elementEdu));
                                                        } else {
                                                            //Idioma
                                                            NodeList listIdioma = elementEdu.getElementsByTagName("Idioma");
                                                            for (int tempIdioma = 0; tempIdioma < listIdioma.getLength(); tempIdioma++) {
                                                                Node nodoIdioma = listIdioma.item(tempIdioma);
                                                                Element elementIdioma = (Element) nodoIdioma;
                                                                System.out.println("Educacion: " + elementIdioma.getAttribute("habla") + " " + elementIdioma.getAttribute("lectura") + " " + elementIdioma.getAttribute("escritura"));
                                                                System.out.println("Numero de conclusiones Idioma: " + elementIdioma.getElementsByTagName("Conclusion").getLength());
                                                                if ((elementIdioma.getAttribute("habla").equals(nivelHabla)) && (elementIdioma.getAttribute("lectura").equals(nivelLectura)) && (elementIdioma.getAttribute("escritura").equals(nivelEscritura))) {
                                                                    //Premisas
                                                                    NodeList listPremisa = elementIdioma.getElementsByTagName("Premisas");
                                                                    for (int tempPremisa = 0; tempPremisa < listPremisa.getLength(); tempPremisa++) {
                                                                        //Softwares
                                                                        Node nodoPremisa = listPremisa.item(tempPremisa);
                                                                        Element elementPremisa = (Element) nodoPremisa;
                                                                        System.out.println("Premisas id: " + elementPremisa.getAttribute("id"));
                                                                        NodeList listTodosSoftware = elementPremisa.getElementsByTagName("Softwares");
                                                                        for (int tempTodosSoft = 0; tempTodosSoft < listTodosSoftware.getLength(); tempTodosSoft++) {
                                                                            Node nodoTodosSoft = listTodosSoftware.item(tempTodosSoft);
                                                                            Element elementTodosSoft = (Element) nodoTodosSoft;
                                                                            //Software
                                                                            System.out.println("Softwares id: " + elementTodosSoft.getAttribute("id"));
                                                                            NodeList listSoft = elementTodosSoft.getElementsByTagName("Software");
                                                                            int encontrado = -1;
                                                                            for (int tempSoft = 0; tempSoft < listSoft.getLength(); tempSoft++) {
                                                                                Node nodoSoft = listSoft.item(tempSoft);
                                                                                Element elementSoft = (Element) nodoSoft;
                                                                                System.out.println("Software Titulo: " + elementSoft.getElementsByTagName("Titulo").item(0).getTextContent());
                                                                                System.out.println("Software Nivel: " + elementSoft.getElementsByTagName("Nivel").item(0).getTextContent());
                                                                                String tituloSoft = elementSoft.getElementsByTagName("Titulo").item(0).getTextContent();
                                                                                String nivelSoft = elementSoft.getElementsByTagName("Nivel").item(0).getTextContent();
                                                                                encontrado = -1;
                                                                                for (int i = 0; i < listSoftwarePostulante.size(); i++) {
                                                                                    String PostulanteNivelS = String.valueOf(listSoftwarePostulante.get(i).getNivel());
                                                                                    if ((tituloSoft.equals(listSoftwarePostulante.get(i).getTitulo()) && (nivelSoft.equals(PostulanteNivelS)))) {
                                                                                        encontrado = i;
                                                                                        break;
                                                                                    }
                                                                                }
                                                                                if (encontrado == -1) break;                                                                               
                                                                            }
                                                                            if (encontrado != -1) {
                                                                                //Competencias
                                                                                NodeList listTodasComp = elementTodosSoft.getElementsByTagName("Competencias");
                                                                                for (int tempTodasComp = 0; tempTodasComp < listTodasComp.getLength(); tempTodasComp++) {
                                                                                    Node nodoTodasComp = listTodasComp.item(tempTodasComp);
                                                                                    Element elementTodasComp = (Element) nodoTodasComp;
                                                                                    //Competencia
                                                                                    System.out.println("Competencia id: " + elementTodasComp.getAttribute("id"));
                                                                                    NodeList listComp = elementTodasComp.getElementsByTagName("Competencia");
                                                                                    int encontradoComp = -1;
                                                                                    for(int tempComp = 0; tempComp < listComp.getLength(); tempComp++){
                                                                                        Node nodoComp = listComp.item(tempComp);
                                                                                        Element elementComp = (Element) nodoComp;
                                                                                        System.out.println("Competencia Titulo: " + elementComp.getElementsByTagName("Titulo").item(0).getTextContent());
                                                                                        System.out.println("Competencia Nivel: " + elementComp.getElementsByTagName("Nivel").item(0).getTextContent());
                                                                                        String tituloComp = elementComp.getElementsByTagName("Titulo").item(0).getTextContent();
                                                                                        String nivelComp = elementComp.getElementsByTagName("Nivel").item(0).getTextContent();
                                                                                        encontradoComp = -1;
                                                                                        for(int i = 0; i < listCompetenciaPostulante.size(); i++){
                                                                                            String PostulanteNivelC = String.valueOf(listCompetenciaPostulante.get(i).getNivel());
                                                                                            if( (tituloComp.equals(listCompetenciaPostulante.get(i).getTitulo())) && (nivelComp.equals(PostulanteNivelC)) ){
                                                                                                encontradoComp = i;
                                                                                                break;
                                                                                            }
                                                                                        }
                                                                                        if(encontradoComp == -1) break;
                                                                                    }
                                                                                    if(encontradoComp != -1){
                                                                                        //Resultados
                                                                                        System.out.println("Puntaje Final: " + obtenerPuntaje(elementTodasComp));
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                    }

                                                                }
                                                            }

                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }
    
    public static double obtenerPuntaje(Element elemento) {
        if (elemento.getElementsByTagName("Conclusion").getLength() == 1) {
            NodeList listPuntaje = elemento.getElementsByTagName("Conclusion");
            Node nodoPuntaje = listPuntaje.item(0);
            Element elementPuntaje = (Element) nodoPuntaje;
            //System.out.println("Puntaje: " + elementPuntaje.getAttribute("Puntaje"));
            return Double.parseDouble(elementPuntaje.getAttribute("Puntaje"));
        } else {
            return -1;
        }
    }

    public static class Software {

        private String titulo;
        private int nivel;

        public Software() {

        }

        public Software(String titulo, int nivel) {
            this.titulo = titulo;
            this.nivel = nivel;
        }

        public String getTitulo() {
            return titulo;
        }

        public int getNivel() {
            return nivel;
        }
    }
    
    public static class Competencia {

        private String titulo;
        private int nivel;

        public Competencia() {

        }

        public Competencia(String titulo, int nivel) {
            this.titulo = titulo;
            this.nivel = nivel;
        }

        public String getTitulo() {
            return titulo;
        }

        public int getNivel() {
            return nivel;
        }
    }
    
}
