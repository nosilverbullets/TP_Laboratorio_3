import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class Paciente extends Usuario { // [Matias de Andrade]
    private static int contadorid = 0;
    private int id;
    private int especialidadID; // Se ingresa bajo especialidad, se determina enfermedad via profesional
    protected LinkedList<Registro> registroTratamiento; //POSIBILIDAD DE CAMBIO
    private boolean activo;
    protected Orden ordenAsignada;

    public Paciente(String nombre, String email, String clave, String dni, int especialidadID) {
        super(nombre, email, clave, dni);
        this.id = contadorid++;
        this.tipoUsuario = TipoUsuario.paciente;
        this.especialidadID = especialidadID;
        this.activo = true;
        this.registroTratamiento = new LinkedList<>();
        this.ordenAsignada = new Orden();
    }

    public void limpiarLista() {
        try {
            registroTratamiento.removeAll(registroTratamiento);
            this.registroTratamiento = new LinkedList<>();
        } catch (UnsupportedOperationException a) {
            System.out.println("No se pudo eliminar la lista");
        }
    }

    public static int getContadorid() {
        return contadorid;
    }

    public static void setContadorid(int contadorid) {
        Paciente.contadorid = contadorid;
    }


    public int getEspecialidadID() {
        return especialidadID;
    }

    public void setEspecialidadID(int especialidadID) {
        this.especialidadID = especialidadID;
    }

    public LinkedList<Registro> getRegistroTratamiento() {
        return registroTratamiento;
    }

    public void setRegistroTratamiento(LinkedList<Registro> registroTratamiento) {
        this.registroTratamiento = registroTratamiento;
    }

    public boolean isActivo() {
        return activo;
    }

    public Orden getOrdenAsignada() {
        return ordenAsignada;
    }

    public void setOrdenAsignada(Orden ordenAsignada) {
        this.ordenAsignada = ordenAsignada;
    }

    public int getID() {
        return id;
    }

    public boolean estaActivo() {
        return activo;
    }

    public void setActivo(boolean estado) {
        this.activo = estado;
    }

    public String estadoActivo() {
        if (this.activo) {
            return "Activo";
        } else {
            return "Inactivo";
        }
    }

    public int getEspecialidad() {
        return this.especialidadID;
    }


    public boolean notificacion(LocalDate FECHAHOY) {
        boolean auxBoolean = false;
        if (registroTratamiento == null) {
            System.out.println("Usted no posee tratamientos activos");
            auxBoolean = false;
        } else {
            if (FECHAHOY.isBefore(ordenAsignada.getFechaFin())) {
                Registro aux = registroTratamiento.getLast();
                if (aux.isCompletoRegistro()) {
                    System.out.println("Usted ya completo el registro el dia de hoy");
                    auxBoolean = true;
                } else {
                    System.out.println("Usted tiene tareas para el dia de hoy");
                    auxBoolean = false;
                }
            }
        }
        return auxBoolean;
    }

    public void verOrdenInfo() { //SE PUEDE SACAR

        System.out.println(ordenAsignada.toString());
    }

    public void completarTareaHoy(LocalDate FECHAHOY) {
        if (ordenAsignada != null) {
            StringBuilder respuesta = new StringBuilder();
            Registro auxRegistro = registroTratamiento.getLast();
            int contador = 0;
            Scanner scan = new Scanner(System.in);
            System.out.println("Completando plan de control:");
            if (FECHAHOY.isBefore(ordenAsignada.getFechaFin().plusDays(1))) {
                for (TareaControl x : ordenAsignada.control.getListadoTareas()) {
                    System.out.println("Tarea: " + x.descripcion);
                    System.out.println("[1] Ingresar respuesta [0] Salir");
                    while (true) {
                        short continuar = scan.nextShort();
                        try {
                            if (continuar != 0) {
                                respuesta.append(x.completarTarea());
                                contador++;
                                break;
                            } else {
                                break;
                            }
                        } catch (NumberFormatException a) {
                            System.out.println("No ingreso numero volver a intentar");
                        }
                    }
                }
            }
            if (contador != ordenAsignada.control.listadoTareas.size()) {
                auxRegistro.setCompletoRegistro(false);
                auxRegistro.setTareasCompletadas(respuesta.toString());
            } else {
                auxRegistro.setCompletoRegistro(true);
                auxRegistro.setTareasCompletadas(respuesta.toString());
            }
        }
    }

    @Override
    public String toString() {
        return "\nID paciente [" + id + "] " +
                "\nPaciente: " + nombre +
                "\nEspecialidad requerida: " + especialidadID +
                "\nEstado: " + estadoActivo() +
                "\nEmail: " + email +
                "\nDNI: " + dni;
    }
}