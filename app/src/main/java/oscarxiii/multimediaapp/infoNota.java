package oscarxiii.multimediaapp;

/**
 * Created by oscarXIII on 27/01/2016.
 */
public class infoNota {
    private String titulo;
    private String nota;
    private String longitud;
    private String latitud;

    public infoNota(String titulo, String nota, String longitud, String latitud) {
        this.titulo = titulo;
        this.latitud = latitud;
        this.longitud = longitud;
        this.nota = nota;
    }

    public infoNota() {
        // empty default constructor, necessary for Firebase to be able to deserialize blog posts
    }

    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public String getNota() {
        return nota;
    }
    public void setNota(String nota) {
        this.nota = nota;
    }
    public String getLongitud() {
        return longitud;
    }
    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }
    public String getLatitud() {
        return latitud;
    }
    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

}
