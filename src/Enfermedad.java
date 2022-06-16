public class Enfermedad {// [Braian D'Aleo]
    private static int contadorid = 0;
    private int id;
    private String nombre;

    public Enfermedad() {
    }

    public Enfermedad(String nombre) {
        this.nombre = nombre;
        this.id = contadorid ++;
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
        return "Enfermedad [" + id + "] " + nombre;
    }
}