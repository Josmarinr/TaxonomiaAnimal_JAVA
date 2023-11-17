/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Control.Logica;

import Control.DAO.AnimalDAO;
import Modelo.Animal;
import Vista.Interfaz;
import Vista.CrearAnimal;
import Vista.ModificarAnimal;
import java.applet.AudioClip;
import java.awt.Image;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Properties;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.Icon;
import javax.swing.ImageIcon;
// creamos la clase controlador, la cual maneja todos los listener de los botones

public final class Controlador implements ActionListener {

    private Interfaz vista;
    private CrearAnimal crearAnimal;
    private ModificarAnimal modificarAnimal;
    private Animal nuevo;
    private int contador;
    private int total;
    public ArrayList<Animal> modelos;
    private AnimalDAO miAnimalDAO;
    //creamos nuestro properties 
    Properties misPropiedades;
    Clip clip;
    RandomAccessFile exportar;
    File f;
    private String ubiFoto;
    private String ubiSonido;
    //constructores de nuestros botones y la instanciación del arraylist y el properties

    public Controlador(Interfaz vista, CrearAnimal crear, ModificarAnimal modificar) {
        modelos = new ArrayList();
        contador = 0;
        this.vista = vista;
        this.crearAnimal = crear;
        this.modificarAnimal = modificar;
        misPropiedades = new Properties();
        misPropiedades = cargarDatos();
        montarDatos();
        this.vista.btnSalir.addActionListener(this);
        this.vista.btnNuevo.addActionListener(this);
        this.vista.btnModificar.addActionListener(this);
        this.vista.btnEliminar.addActionListener(this);
        this.vista.btnAnterior.addActionListener(this);
        this.vista.btnSiguiente.addActionListener(this);
        this.vista.btnSSonido.addActionListener(this);
        this.vista.btnPSonido.addActionListener(this);

        this.crearAnimal.btnAgregar.addActionListener(this);
        this.crearAnimal.btnCancelar.addActionListener(this);
        this.crearAnimal.btnFoto.addActionListener(this);
        this.crearAnimal.btnSonido.addActionListener(this);

        this.modificarAnimal.btnModificar.addActionListener(this);
        this.modificarAnimal.btnCancelar.addActionListener(this);
        this.modificarAnimal.btnFoto.addActionListener(this);
        this.modificarAnimal.btnSonido.addActionListener(this);

        this.vista.setVisible(true);
    }

    //carga de nuestro properties
    public Properties cargarDatos() {
        try {
            FileInputStream archivo = new FileInputStream(vista.ubicacionArchivo());
            Properties propiedades = new Properties();
            propiedades.load(archivo);
            archivo.close();
            if (!propiedades.isEmpty()) {
                return propiedades;
            }

        } catch (Exception e) {
            vista.errorCargaDatos();
        }
        return null;
    }

    // método para añadir los datos de nuestro archivo precargado a nuestro arraylist
    public void montarDatos() {
        try {
            total = Integer.parseInt(misPropiedades.getProperty("TotalSer"));
            String datos = "";
            String[] parts;
            for (int i = 0; i < total; i++) {
                datos = misPropiedades.getProperty("Ser" + i);
                parts = datos.split(",");
                nuevo = new Animal();
                nuevo.setFilum(parts[0]);
                nuevo.setSubfilum(parts[1]);
                nuevo.setClase(parts[2]);
                nuevo.setOrden(parts[3]);
                nuevo.setFamilia(parts[4]);
                nuevo.setGenero(parts[5]);
                nuevo.setEspecie(parts[6]);
                nuevo.setFoto(parts[7]);
                nuevo.setSonido(parts[8]);
                nuevo.setNombre(parts[9]);
                modelos.add(nuevo);
                datos = "";
            }
            actualizar();
        } catch (Exception e) {
            vista.errorCargaDatos();
        }
    }

    //método para guardar los campos del animal a agregar
    public void crearAnimal() {
        try {
            nuevo = new Animal();
            miAnimalDAO = new AnimalDAO();
            nuevo.setFilum(crearAnimal.txtFilum.getText());
            nuevo.setSubfilum(crearAnimal.txtSubfilum.getText());
            nuevo.setClase(crearAnimal.txtClase.getText());
            nuevo.setOrden(crearAnimal.txtFilum.getText());
            nuevo.setFamilia(crearAnimal.txtFamilia.getText());
            nuevo.setGenero(crearAnimal.txtGenero.getText());
            nuevo.setEspecie(crearAnimal.txtEspecie.getText());
            nuevo.setNombre(crearAnimal.txtNombre.getText());
            nuevo.setFoto(ubiFoto);
            nuevo.setSonido(ubiSonido);
            modelos.add(nuevo);
            this.miAnimalDAO.insertarDatos(nuevo);
            actualizar();
            ubiFoto = "";
            ubiSonido = "";
            this.crearAnimal.CrearAnimal();
            total++;
        } catch (Exception e) {
            crearAnimal.errorCrearAnimal();
        }
    }

    //metodo que muestra los datos añadidos o precargados de un animal
    public void mostrarAnimal() {
        try {
            ubiFoto = "";
            ubiSonido = "";
            this.modificarAnimal.txtFilum.setText(modelos.get(contador).getFilum());
            this.modificarAnimal.txtSubfilum.setText(modelos.get(contador).getSubfilum());
            this.modificarAnimal.txtClase.setText(modelos.get(contador).getClase());
            this.modificarAnimal.txtOrden.setText(modelos.get(contador).getOrden());
            this.modificarAnimal.txtFamilia.setText(modelos.get(contador).getFamilia());
            this.modificarAnimal.txtGenero.setText(modelos.get(contador).getGenero());
            this.modificarAnimal.txtEspecie.setText(modelos.get(contador).getEspecie());
            this.modificarAnimal.txtNombre.setText(modelos.get(contador).getNombre());
            ubiFoto = modelos.get(contador).getFoto();
            ubiSonido = modelos.get(contador).getSonido();
        } catch (Exception e) {
            modificarAnimal.errorModificarAnimal();
        }
    }

    //metodo que permite modificar los datos ingresados de un animal 
    public void modificarAnimal() {
        try {
            miAnimalDAO = new AnimalDAO();
            this.modelos.get(contador).setFilum(this.modificarAnimal.txtFilum.getText());
            this.modelos.get(contador).setSubfilum(this.modificarAnimal.txtSubfilum.getText());
            this.modelos.get(contador).setClase(this.modificarAnimal.txtClase.getText());
            this.modelos.get(contador).setOrden(this.modificarAnimal.txtOrden.getText());
            this.modelos.get(contador).setFamilia(this.modificarAnimal.txtFamilia.getText());
            this.modelos.get(contador).setGenero(this.modificarAnimal.txtGenero.getText());
            this.modelos.get(contador).setEspecie(this.modificarAnimal.txtEspecie.getText());
            this.modelos.get(contador).setNombre(this.modificarAnimal.txtNombre.getText());
            this.modelos.get(contador).setFoto(ubiFoto);
            this.modelos.get(contador).setSonido(ubiSonido);
            this.miAnimalDAO.modificarAnimal(this.modelos.get(contador));
            this.modificarAnimal.ModificarAnimal();
            actualizar();
        } catch (Exception e) {
            modificarAnimal.errorModificarAnimal();
        }
    }

    //metodo que actualiza la interfaz con los datos del animal guardado
    public void actualizar() {
        this.vista.txtFilum.setText(modelos.get(contador).getFilum());
        this.vista.txtSubfilum.setText(modelos.get(contador).getSubfilum());
        this.vista.txtClase.setText(modelos.get(contador).getClase());
        this.vista.txtOrden.setText(modelos.get(contador).getOrden());
        this.vista.txtFamilia.setText(modelos.get(contador).getFamilia());
        this.vista.txtGenero.setText(modelos.get(contador).getGenero());
        this.vista.txtEspecie.setText(modelos.get(contador).getEspecie());
        this.vista.txtNombre.setText(modelos.get(contador).getNombre());
        ImageIcon iconLogo = new ImageIcon(modelos.get(contador).getFoto());
        Icon icono = new ImageIcon(iconLogo.getImage().getScaledInstance(this.vista.lblFoto.getWidth(), this.vista.lblFoto.getHeight(), Image.SCALE_DEFAULT));
        this.vista.lblFoto.setIcon(icono);
    }

    //metodo que reproduce el sonido guardado para cada animal 
    public void reproducirSonido(String nombreSonido) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(nombreSonido).getAbsoluteFile());
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
            this.vista.errorSonido();
        }
    }
    //metodo que detiene el sonido guardado para cada animal

    public void stopSonido() {
        clip.stop();
    }

    //metodo que elimina un animal 
    public void eliminarAnimal() {
        try {
            miAnimalDAO = new AnimalDAO();
            this.miAnimalDAO.eliminarMascota(this.modelos.get(contador));
            this.modelos.remove(contador);
            contador = 0;
            total--;
            actualizar();
            this.vista.eliminarAnimal();
        } catch (Exception e) {
            vista.errorEliminarAnimal();
        }
    }

    //metodo que finaliza el programa
    public void salir() {
        System.exit(0);

    }

    //condiciones cuando se presionan cada uno de los botones y sus funcionalidades
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.crearAnimal.btnAgregar) {
            crearAnimal();
            this.crearAnimal.setVisible(false);
        }
        if (e.getSource() == this.crearAnimal.btnCancelar) {
            this.crearAnimal.Limpiar();
            this.crearAnimal.setVisible(false);
        }
        if (e.getSource() == this.crearAnimal.btnFoto) {
            ubiFoto = vista.ubicacionArchivo();
        }
        if (e.getSource() == this.crearAnimal.btnSonido) {
            ubiSonido = vista.ubicacionArchivo();
        }

        if (e.getSource() == this.modificarAnimal.btnModificar) {
            modificarAnimal();
            this.modificarAnimal.Limpiar();
            this.modificarAnimal.setVisible(false);
        }
        if (e.getSource() == this.modificarAnimal.btnCancelar) {
            this.modificarAnimal.Limpiar();
            this.modificarAnimal.setVisible(false);
        }
        if (e.getSource() == this.modificarAnimal.btnFoto) {
            ubiFoto = vista.ubicacionArchivo();
        }
        if (e.getSource() == this.modificarAnimal.btnSonido) {
            ubiSonido = vista.ubicacionArchivo();
        }

        if (e.getSource() == this.vista.btnModificar) {
            this.mostrarAnimal();
            this.modificarAnimal.setVisible(true);
        }
        if (e.getSource() == this.vista.btnNuevo) {
            this.crearAnimal.setVisible(true);
        }
        if (e.getSource() == this.vista.btnEliminar) {
            eliminarAnimal();
        }
        if (e.getSource() == this.vista.btnSalir) {
            salir();
        }
        if (e.getSource() == this.vista.btnPSonido) {
            reproducirSonido(this.modelos.get(contador).getSonido());
        }
        if (e.getSource() == this.vista.btnSSonido) {
            stopSonido();
        }
        if (e.getSource() == this.vista.btnSiguiente) {
            contador++;
            if (contador >= total) {
                this.vista.noAnimales();
                contador--;
            } else {
                actualizar();
            }
        }
        if (e.getSource() == this.vista.btnAnterior) {
            if (contador <= 0) {
                this.vista.noAnimales();
            } else {
                contador--;
                actualizar();
            }

        }
    }
}
