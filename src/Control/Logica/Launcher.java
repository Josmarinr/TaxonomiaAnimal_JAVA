
package Control.Logica;

import Vista.Interfaz;
import Vista.CrearAnimal;
import Vista.ModificarAnimal;


public class Launcher {
    //inicio de la aplicacion
    public static void main(String[] args) {
        Interfaz vista = new Interfaz();
        CrearAnimal crear = new CrearAnimal();
        ModificarAnimal modificar = new ModificarAnimal();
        Controlador controlador = new Controlador (vista, crear, modificar);

    }
}
