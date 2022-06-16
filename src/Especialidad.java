public class Especialidad {// [Braian D'Aleo]
    private static int contadorid = 0;
    private int id;
    private String nombre;

    public Especialidad(String nombre) {
        this.id = contadorid++;
        this.nombre = nombre;
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

    @Override
    public String toString() {
        return "Especialidad [" + id + "] " + nombre;
    }
}
