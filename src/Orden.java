import java.time.LocalDate;

public class Orden {// [Braian D'Aleo]
    private int idProfesional; // ID del medico que gestiono el tratamiento
    protected Control control; // Tratamiento a seguir
    private Enfermedad enfermedad; // Enfermedad asignada
    private LocalDate fechaInicio;
    private LocalDate fechaFin;

    public Orden() {
    }

    public int getIdProfesional() {
        return idProfesional;
    }

    public void setIdProfesional(int idProfesional) {
        this.idProfesional = idProfesional;
    }

    public Control getControl() {
        return control;
    }

    public void setControl(Control control) {
        this.control = control;
    }

    public Enfermedad getEnfermedad() {
        return enfermedad;
    }

    public void setEnfermedad(Enfermedad enfermedad) {
        this.enfermedad = enfermedad;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    @Override
    public String toString() {
        return "Tratamiento: " + control +
                "\nFecha de inicio: " + fechaInicio + " - Fecha de finalizacion: " + fechaFin +
                "\nProfesional responsable: " + idProfesional;
    }
}
