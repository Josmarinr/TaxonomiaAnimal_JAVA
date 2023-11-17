
package Modelo;

// Atributos del animal
public class Animal {
    String filum;
    String subfilum;
    String clase;
    String orden;
    String foto;
    String familia;
    String genero;
    String especie;
    String nombre;
    String sonido;
   //clase definiendo los valores de los atributos del animal
    public Animal(){
       this.filum=""; 
       this.subfilum="";
       this.clase=""; 
       this.orden=""; 
       this.foto=""; 
       this.familia=""; 
       this.genero=""; 
       this.especie="";
       this.nombre=""; 
       this.sonido=""; 
    }
    //metodos getter y setters
    public String getFilum() {
        return filum;
    }

    public void setFilum(String filum) {
        this.filum = filum;
    }
    
    public String getSubfilum() {
        return subfilum;
    }

    public void setSubfilum(String subfilum) {
        this.subfilum = subfilum;
    }
    
    public String getClase() {
        return clase;
    }

    public void setClase(String clase) {
        this.clase = clase;
    }
    
    public String getOrden() {
        return orden;
    }

    public void setOrden(String orden) {
        this.orden = orden;
    }
    
    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
    
    public String getFamilia() {
        return familia;
    }

    public void setFamilia(String familia) {
        this.familia = familia;
    }
    
    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }
    
    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getSonido() {
        return sonido;
    }

    public void setSonido(String sonido) {
        this.sonido = sonido;
    }
}
