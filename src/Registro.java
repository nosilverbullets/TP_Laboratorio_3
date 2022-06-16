public class Registro { // [Matias de Andrade]

    private String tareasCompletadas;
    boolean completoRegistro;

    public Registro() {
        this.tareasCompletadas = "VACIO";
        this.completoRegistro = false;
    }

    public Registro(String sintomasCompletados, boolean completoRegistro) {
        this.tareasCompletadas = sintomasCompletados;
        this.completoRegistro = completoRegistro;
    }

    public String getTareasCompletadas() {
        return tareasCompletadas;
    }

    public void setTareasCompletadas(String tareasCompletadas) {
        this.tareasCompletadas = tareasCompletadas;
    }

    public boolean isCompletoRegistro() {
        return completoRegistro;
    }

    public void setCompletoRegistro(boolean completoRegistro) {
        this.completoRegistro = completoRegistro;
    }

}
