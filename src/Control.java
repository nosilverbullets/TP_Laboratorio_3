import java.util.ArrayList;
import java.util.Scanner;

public class Control { // [Matias de Andrade & Braian D'Aleo]
    private static int contadorid = 0;
    private int id;
    private String nombre;
    protected ArrayList<TareaControl> listadoTareas; // La copia de las tareas a completar

    public Control(){
        this.id = -1; ///EL -1 INDICA ID CUSTOM QUE NO SE PERSISTE EN EL ARCHIVO DE CONTROLES OFICIALES.
        this.listadoTareas = new ArrayList<>();
    }
    public Control(String nombre) {
        this.nombre = nombre;
        this.id = contadorid++;
        this.listadoTareas = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<TareaControl> getListadoTareas() {
        return listadoTareas;
    }

    public void agregarTarea(TareaControl tarea){
        listadoTareas.add(tarea);
    }

    @Override
    public String toString() {
        return "\n>> Control id [" + id + "] " + nombre + "\n"
                + listadoTareas;
    }
}
