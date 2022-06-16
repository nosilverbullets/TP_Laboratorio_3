import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.lang.reflect.Modifier;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;


public class Sistema {
    public LocalDate FECHAHOY;
    protected Persistencia persistencia = new Persistencia();
    protected ArrayList<Admin> listadoAdmin;
    protected ArrayList<Paciente> listadoPaciente;
    protected ArrayList<Profesional> listadoProfesional;
    protected ArrayList<TareaControl> listadoTarea;
    protected ArrayList<Control> listadoControl;
    protected ArrayList<Enfermedad> listadoEnfermedad;
    protected ArrayList<Especialidad> listadoEspecialidad;

    public Sistema() {
        this.listadoAdmin = new ArrayList<>();
        this.listadoPaciente = new ArrayList<>();
        this.listadoProfesional = new ArrayList<>();
        this.listadoTarea = new ArrayList<>();
        this.listadoControl = new ArrayList<>();
        this.listadoEnfermedad = new ArrayList<>();
        this.listadoEspecialidad = new ArrayList<>();
    }

    public void cargarSistema() {
        this.listadoAdmin = persistencia.cargarAdmin();
        this.listadoPaciente = persistencia.cargarPaciente();
        this.listadoProfesional = persistencia.cargarProfesional();
        this.listadoTarea = persistencia.cargarTarea();
        this.listadoControl = persistencia.cargarControl();
        this.listadoEnfermedad = persistencia.cargarEnfermedad();
        this.listadoEspecialidad = persistencia.cargarEspecialidad();
        FECHAHOY = LocalDate.now();
    }

    public void actualizarSistema() {
        persistencia.actualizarAdmin(listadoAdmin);
        persistencia.actualizarPaciente(listadoPaciente);
        persistencia.actualizarProfesional(listadoProfesional);
        persistencia.actualizarTarea(listadoTarea);
        persistencia.actualizarEnfermedad(listadoEnfermedad);
        persistencia.actualizarEspecialidad(listadoEspecialidad);
        persistencia.actualizarControl(listadoControl);
    }

    public void actualizarAdmin(){
        persistencia.actualizarAdmin(listadoAdmin);
    }

    public void actualizarPaciente(){
        persistencia.actualizarPaciente(listadoPaciente);
    }

    public void actualizarProfesional(){
        persistencia.actualizarProfesional(listadoProfesional);
    }

    public void actualizarTarea(){
        persistencia.actualizarTarea(listadoTarea);
    }

    public void actualizarEnfermedad(){
        persistencia.actualizarEnfermedad(listadoEnfermedad);
    }

    public void actualizarEspecialidad(){
        persistencia.actualizarEspecialidad(listadoEspecialidad);
    }

    public void actualizarControl(){
        persistencia.actualizarControl(listadoControl);
    }
    public void generarDatosDefault() {

        // Especialidades
        listadoEspecialidad.add(new Especialidad("Odontologia"));
        listadoEspecialidad.add(new Especialidad("Pediatria"));
        listadoEspecialidad.add(new Especialidad("Infectologia"));
        listadoEspecialidad.add(new Especialidad("Urologia"));
        listadoEspecialidad.add(new Especialidad("Cardiologia"));
        listadoEspecialidad.add(new Especialidad("Cirugia"));
        listadoEspecialidad.add(new Especialidad("Clinica medica"));
        listadoEspecialidad.add(new Especialidad("Ginecologia"));
        listadoEspecialidad.add(new Especialidad("Oftalmologia"));

        // Enfermedad
        listadoEnfermedad.add(new Enfermedad("Caries"));
        listadoEnfermedad.add(new Enfermedad("Bronquitis"));
        listadoEnfermedad.add(new Enfermedad("Covid"));
        listadoEnfermedad.add(new Enfermedad("Infeccion urinaria"));
        listadoEnfermedad.add(new Enfermedad("Arritmia"));
        listadoEnfermedad.add(new Enfermedad("Apendicitis"));
        listadoEnfermedad.add(new Enfermedad("Cancer"));
        listadoEnfermedad.add(new Enfermedad("Hongos vaginales"));
        listadoEnfermedad.add(new Enfermedad("Infeccion ocular"));

        // Tarea control
        listadoTarea.add(new TareaBoolean("Enjuague bucal", "Realizar enjuague bucal"));
        listadoTarea.add(new TareaBoolean("Cepillado profundo", "Realizar cepillado dental"));

        listadoTarea.add(new TareaBoolean("Tomar jarabe", "Tomar jarabe recetado"));
        listadoTarea.add(new TareaBoolean("Baños de vapor", "Realizar baño de vapor"));

        listadoTarea.add(new TareaBoolean("Tomar ibuprofeno", "Tomar medicacion"));
        listadoTarea.add(new TareaBoolean("Tomar te", "Tomo te recetado"));

        listadoTarea.add(new TareaNumerica("Tomar temperatura", "Ingrese su temperatura corporal"));
        listadoTarea.add(new TareaBoolean("Aplicar crema", "Aplicar topico recetado"));

        listadoTarea.add(new TareaBoolean("Tomar Bayaspirina Prevent", "Tomo su medicacion preventiva?"));
        listadoTarea.add(new TareaNumerica("Medir presion", "Ingrese su presion"));

        listadoTarea.add(new TareaBoolean("Tomar descongestivo", "Tomar descongestivo recetado"));
        listadoTarea.add(new TareaBoolean("Tomar agua", "Tomar 3 litros de agua"));

        listadoTarea.add(new TareaBoolean("Realizar quimioterapia", "Realizar quimioterapia diaria"));

        listadoTarea.add(new TareaBoolean("Tomar antiinflamatorio", "Tomar medicacion recetada"));
        listadoTarea.add(new TareaBoolean("Aplicar topico especifico", "Aplicar topico recetado"));

        listadoTarea.add(new TareaBoolean("Gotas oftalmologicas", "Aplicar gotas en los ojos"));

        // Controles preestablecidos
        Control caries = new Control("Caries");
        caries.agregarTarea(listadoTarea.get(0));
        caries.agregarTarea(listadoTarea.get(1));
        listadoControl.add(caries);

        Control bronquitis = new Control("Bronquitis");
        bronquitis.agregarTarea(listadoTarea.get(2));
        bronquitis.agregarTarea(listadoTarea.get(3));
        listadoControl.add(bronquitis);

        Control covid = new Control("Covid");
        covid.agregarTarea(listadoTarea.get(4));
        covid.agregarTarea(listadoTarea.get(5));
        listadoControl.add(covid);

        Control infeccionUrinaria = new Control("Infeccion Urinaria");
        infeccionUrinaria.agregarTarea(listadoTarea.get(6));
        infeccionUrinaria.agregarTarea(listadoTarea.get(7));
        listadoControl.add(infeccionUrinaria);

        Control arritmia = new Control("Arritmia");
        arritmia.agregarTarea(listadoTarea.get(8));
        arritmia.agregarTarea(listadoTarea.get(9));
        listadoControl.add(arritmia);

        Control apendicitis = new Control("Apendicitis");
        apendicitis.agregarTarea(listadoTarea.get(10));
        apendicitis.agregarTarea(listadoTarea.get(11));
        listadoControl.add(apendicitis);

        Control cancer = new Control("Cancer");
        cancer.agregarTarea(listadoTarea.get(12));
        listadoControl.add(cancer);

        Control hongosVaginales = new Control("Hongos vaginales");
        hongosVaginales.agregarTarea(listadoTarea.get(13));
        hongosVaginales.agregarTarea(listadoTarea.get(14));
        listadoControl.add(hongosVaginales);

        Control infeccionOcular = new Control("Infeccion ocular");
        infeccionOcular.agregarTarea(listadoTarea.get(15));
        listadoControl.add(infeccionOcular);

        // Usuarios
        listadoAdmin.add(new Admin("Juan Administrador", "admin", "123456", "38001002"));

        listadoProfesional.add(new Profesional("Doctor Bianchi", "profesional", "123456", "78912385", 0));
        listadoProfesional.add(new Profesional("Doctora Gimenez", "profesional1@clinica.com", "123456", "85459632", 0));
        listadoProfesional.add(new Profesional("Doctora Lorenzo", "profesional2@clinica.com", "123456", "10123456", 1));
        listadoProfesional.add(new Profesional("Doctora Gutierrez", "profesional3@clinica.com", "123456", "1015456", 1));
        listadoProfesional.add(new Profesional("Doctora Calvo", "profesional4@clinica.com", "123456", "10123654", 2));
        listadoProfesional.add(new Profesional("Doctora Schutz", "profesional5@clinica.com", "123456", "10134556", 2));
        listadoProfesional.add(new Profesional("Doctora Di Tomasso", "profesional6@clinica.com", "123456", "12343456", 3));
        listadoProfesional.add(new Profesional("Doctor Gimenez", "profesional7@clinica.com", "123456", "10123463", 3));
        listadoProfesional.add(new Profesional("Doctor Badaraco", "profesional8@clinica.com", "123456", "10123896", 4));
        listadoProfesional.add(new Profesional("Doctor Franco", "profesional9@clinica.com", "123456", "10123789", 4));
        listadoProfesional.add(new Profesional("Doctora Achaval", "profesional10@clinica.com", "123456", "10123632", 5));
        listadoProfesional.add(new Profesional("Doctora Gimenez", "profesional11@clinica.com", "123456", "10123544", 5));
        listadoProfesional.add(new Profesional("Doctora Armani", "profesional12@clinica.com", "123456", "10123888", 6));
        listadoProfesional.add(new Profesional("Doctora Gimenez", "profesional13@clinica.com", "123456", "90124566", 6));
        listadoProfesional.add(new Profesional("Doctora Roviralta", "profesional14@clinica.com", "123456", "67123856", 7));
        listadoProfesional.add(new Profesional("Doctor Justo", "profesional15@clinica.com", "123456", "10993456", 7));
        listadoProfesional.add(new Profesional("Doctor Liz", "profesional16@clinica.com", "123456", "10199956", 8));
        listadoProfesional.add(new Profesional("Doctora Bertollini", "profesional17@clinica.com", "123456", "10553456", 8));
        listadoProfesional.add(new Profesional("Doctora Ullua", "profesional18@clinica.com", "123456", "30123122", 9));
        listadoProfesional.add(new Profesional("Doctor Pettinato", "profesional19@clinica.com", "123456", "25666456", 9));
        listadoProfesional.add(new Profesional("Doctor Gaggini", "profesional20@clinica.com", "123456", "29123766", 9));

        listadoPaciente.add(new Paciente("Paciente Silvia", "paciente", "123456", "26456789", 0));
        listadoPaciente.add(new Paciente("Paciente Carlos", "paciente2@gmail.com", "123456", "45612385", 1));
        listadoPaciente.add(new Paciente("Paciente Romina", "paciente3@gmail.com", "123456", "96325874", 2));
        listadoPaciente.add(new Paciente("Paciente Juan", "paciente4@gmail.com", "123456", "45696541", 3));
        listadoPaciente.add(new Paciente("Paciente Carla", "paciente5@gmail.com", "123456", "15985236", 4));

    } // Carga datos de prueba [D'Aleo Braian]

    public Usuario login(String email, String clave) {
        for (Usuario c : listadoAdmin) {
            if (c.getEmail().equalsIgnoreCase(email) && c.getClave().equalsIgnoreCase(clave)) {
                return c;
            }
        }
        for (Usuario c : listadoProfesional) {
            if (c.getEmail().equalsIgnoreCase(email) && c.getClave().equalsIgnoreCase(clave)) {
                return c;
            }
        }
        for (Usuario c : listadoPaciente) {
            if (c.getEmail().equalsIgnoreCase(email) && c.getClave().equalsIgnoreCase(clave)) {
                return c;
            }
        }
        return null;
    }

    public void verTareasControl() {
        for (TareaControl c : listadoTarea) {
            System.out.println(c);
        }
    } // [D'Aleo Braian]

    public int contadorTareasControl() {
        int i = -1;
        for (TareaControl c : listadoTarea) {
            i++;
        }
        return i;
    } // [D'Aleo Braian]

    public boolean agregarTarea(String tarea) {
        boolean aux = true;
        for (TareaControl c : listadoTarea) {
            if (c.getNombre().equalsIgnoreCase(tarea)) {
                aux = false;
            }
        }
        Scanner scan = new Scanner(System.in);
        System.out.println("Ingrese descripcion de la tarea");
        String descripcion = scan.nextLine();
        try {
            System.out.println("Ingrese \n 1-Si es una tarea numerica\n 2-Si es tarea con respuesta SI NO");
            System.out.println("0- Si desea cancelar la creacion de la tarea");
            short answer = scan.nextShort();
            switch (answer) {
                case 1:
                    this.listadoTarea.add(new TareaNumerica(tarea, descripcion));
                    break;
                case 2:
                    this.listadoTarea.add(new TareaBoolean(tarea, descripcion));
                    break;
                case 0:
                    System.out.println("Cancelando creacion de Tarea");
                    break;
                default:
                    System.out.println("Valor erroneo");
            }
        } catch (NumberFormatException a) {
            System.out.println("Usted no ingreso numero por favor volver  a intentar");
            agregarTarea(tarea);
        }
        //  persistencia.actualizarTarea(listadoTarea); TESTEAR PERSISTENCIA
        // TODO ACTUALIZAR ACA
        return aux; // Si tarea ya existe no se agrega
    } // [D'Aleo Braian]

    public boolean borrarTareaID(int id) {
        boolean respuesta = false;
        TareaControl aux = buscarTareaID(id);
        if (listadoTarea.remove(aux)) {
            respuesta = true;
        }
        return respuesta;
    } // [D'Aleo Braian]

    public TareaControl buscarTareaID(int idTarea) {
        TareaControl aux = null;
        for (TareaControl c : listadoTarea) {
            if (c.getId() == idTarea) {
                aux = c;
            }
        }
        return aux;
    } // Busca un idTarea y retorna el objeto  // [D'Aleo Braian]

    public boolean buscarTarea(int id) {
        for (TareaControl c : listadoTarea) {
            if (c.getId() == id) {
                return true;
            }
        }
        return false;
    } // [D'Aleo Braian]

    public void verEnfermedades() {
        for (Enfermedad c : listadoEnfermedad) {
            System.out.println(c);
        }
    } // [D'Aleo Braian]

    public Enfermedad buscarEnfermedad(int idEnfermedad) {
        Enfermedad enfermedad = null;
        for (Enfermedad c : listadoEnfermedad) {
            if (c.getId() == idEnfermedad) {
                enfermedad = c;
            }
        }
        return enfermedad;
    } // [D'Aleo Braian]

    public boolean agregarEnfermedad(String enfermedad) {
        for (Enfermedad c : listadoEnfermedad) {
            if (c.getNombre().equalsIgnoreCase(enfermedad)) {
                return false;
            }
        }
        this.listadoEnfermedad.add(new Enfermedad(enfermedad));
        // persistencia.actualizarEnfermedad(); //todo test ok? ACA VA PERSISTENCIA
        return true;
    } // Si la enfermedad ya se encuentra no se agrega // [D'Aleo Braian]


    public boolean borrarEnfermedadID(int id) {
        for (Enfermedad c : listadoEnfermedad) {
            if (c.getId() == id) {
                this.listadoEnfermedad.remove(c);
                // actualizarEnfermedad(); //PERSISTENCIA
                return true;
            }
        }
        return false;
    } // [D'Aleo Braian]

    public void verEspecialidades() {
        for (Especialidad c : listadoEspecialidad) {
            System.out.println(c);
        }
    } // [D'Aleo Braian]

    public int contarEspecialidad() {
        int i = 0;
        for (Especialidad c : listadoEspecialidad) {
            i++;
        }
        return i;
    } // [D'Aleo Braian]

    public boolean buscarEspecialidad(int idEspecialidad) {
        for (Especialidad c : listadoEspecialidad) {
            if (c.getId() == idEspecialidad) {
                return true;
            }
        }
        return false;
    } // [D'Aleo Braian]

    public boolean agregarEspecialidad(String especialidad) {
        for (Especialidad c : listadoEspecialidad) {
            if (c.getNombre().equalsIgnoreCase(especialidad)) {
                return false;
            }
        }
        this.listadoEspecialidad.add(new Especialidad(especialidad));
        // actualizarEspecialidad(); //TODO TEST? TESTEAR PERSISTENCIA
        return true;
    } // [D'Aleo Braian]

    public boolean borrarEspecialidadID(int id) {
        for (Especialidad c : listadoEspecialidad) {
            if (c.getId() == id) {
                this.listadoEspecialidad.remove(c);
                // ualizarEspecialidad(); //PERSISTENCIA
                return true;
            }
        }
        return false;
    } // [D'Aleo Braian]

    public boolean buscarMail(String email) {
        for (Admin c : listadoAdmin) {
            if (c.getEmail().equalsIgnoreCase(email)) {
                return true;
            }
        }
        for (Profesional c : listadoProfesional) {
            if (c.getEmail().equalsIgnoreCase(email)) {
                return true;
            }
        }
        for (Paciente c : listadoPaciente) {
            if (c.getEmail().equalsIgnoreCase(email)) {
                return true;
            }
        }
        return false;
    } // [D'Aleo Braian]

    public boolean buscarDNI(String dni) {
        for (Admin c : listadoAdmin) {
            if (c.getDni().equalsIgnoreCase(dni)) {
                return true;
            }
        }
        for (Profesional c : listadoProfesional) {
            if (c.getDni().equalsIgnoreCase(dni)) {
                return true;
            }
        }
        for (Paciente c : listadoPaciente) {
            if (c.getDni().equalsIgnoreCase(dni)) {
                return true;
            }
        }
        return false;
    } // [D'Aleo Braian]

    public void verProfesionales() {
        for (Profesional c : listadoProfesional) {
            System.out.println(c);
        }
    } // [D'Aleo Braian]

    public void verProfesionalesActivos() {
        for (Profesional c : listadoProfesional) {
            if (c.estaActivo()) {
                System.out.println(c);
            }
        }
    } // [D'Aleo Braian]

    public void verProfesionalesEspecialidad(int id) {
        for (Profesional c : listadoProfesional) {
            if (c.estaActivo() && c.getEspecialidad() == id) {
                System.out.println(c);
            }
        }
    } // Muestra los profesionales activos segun especialidad // [D'Aleo Braian]

    public boolean agregarProfesional(Profesional profesional) {
        if (!buscarDNI(profesional.getDni())) {
            this.listadoProfesional.add(profesional);
            return true;
        }
        return false;

    } // Agrega profesional si su DNI no existe en la lista // [D'Aleo Braian]

    public Profesional buscarProfesionalID(int id) {
        for (Profesional c : listadoProfesional) {
            if (c.getID() == id) {
                return c;
            }
        }
        return null;
    } // [D'Aleo Braian]

    public boolean borrarProfesionalID(int id) {
        Profesional aux = buscarProfesionalID(id);
        if (aux != null) {
            listadoProfesional.remove(aux);
            return true;
        }
        return false;
    } // [D'Aleo Braian]

    public void verPacientesActivos() {
        for (Paciente c : listadoPaciente) {
            if (c.isActivo()) {
                System.out.println(c);
            }
        }
    } // [D'Aleo Braian]

    public void verPacientes() {
        for (Paciente c : listadoPaciente) {
            System.out.println(c);
        }
    } // [D'Aleo Braian]

    public Paciente buscarPacienteID(int id) {
        for (Paciente c : listadoPaciente) {
            if (c.getID() == id) {
                return c;
            }
        }
        return null;
    } // [D'Aleo Braian]

    public boolean agregarPaciente(Paciente paciente) {
        if (!buscarDNI(paciente.getDni())) {
            this.listadoPaciente.add(paciente);
            return true;
        }
        return false;
    } // [D'Aleo Braian]

    public boolean agregarPacienteProfesional(Paciente paciente, int idProfesional) {
        Profesional aux = buscarProfesionalID(idProfesional);
        if (aux != null) {
            aux.agregarPacientesProfesionalID(paciente.getID());
            return true;
        }
        return false;
    } // [D'Aleo Braian]

    public boolean borrarPacienteID(int id) {
        Paciente aux = buscarPacienteID(id);
        if (aux != null) {
            listadoPaciente.remove(aux);
            return true;
        }
        return false;
    } // [D'Aleo Braian]

    public void verListadoPacientesProfNuevos(Profesional profesional) {
        for (Integer c : profesional.getListaPacientesNuevosID()) {
            Paciente aux = buscarPacienteID(c);
            System.out.println(aux);
        }
    } // [D'Aleo Braian]

    public void verListadoPacientesProfTratados(Profesional profesional) {
        for (Integer c : profesional.getListaPacientesEnTratamiendoID()) {
            Paciente aux = buscarPacienteID(c);
            System.out.println(aux);
        }
    } // [D'Aleo Braian]

    public int contadorPacientesNuevos(Profesional profesional) {
        return profesional.getListaPacientesNuevosID().size();
    } // [D'Aleo Braian]

    public int contadorPacientesTratamiento(Profesional profesional) {
        return profesional.getListaPacientesEnTratamiendoID().size();
    } // [D'Aleo Braian]

    public void verListadoControles() {
        for (Control c : listadoControl) {
            System.out.println(c);
        }
    } // [D'Aleo Braian]

    public Control buscarControlID(int idControl) {
        for (Control c : listadoControl) {
            if (c.getId() == idControl) {
                return c;
            }
        }
        return null;
    } // [D'Aleo Braian]

    // ----------------------------------------------------------------------------------
    public void cargarControlPacientes(Profesional profesional, int idPaciente, Control aux, int duracion, Enfermedad enfermedad) {
        if (aux != null) {
            Paciente pacienteAux = buscarPacienteID(idPaciente);
            if(pacienteAux.ordenAsignada==null){
                pacienteAux.setOrdenAsignada(new Orden());
            }
            if(pacienteAux.registroTratamiento==null){
                pacienteAux.setRegistroTratamiento(new LinkedList<Registro>());
            }
            pacienteAux.registroTratamiento.add(new Registro());
            int index;
            pacienteAux.ordenAsignada.setControl(aux);
            pacienteAux.ordenAsignada.setFechaInicio(FECHAHOY);
            pacienteAux.ordenAsignada.setFechaFin(FECHAHOY.plusDays(duracion));
            pacienteAux.ordenAsignada.setIdProfesional(profesional.getID());
            pacienteAux.ordenAsignada.setEnfermedad(enfermedad); ///CAMBIEMOS LISTA DE PACIENTES NUEVOS A ASIGNADOS
            profesional.listaPacientesEnTratamiendoID.add(idPaciente);
            index = profesional.listaPacientesNuevosID.indexOf(idPaciente);
            profesional.listaPacientesNuevosID.remove(index);

        } else {
            System.out.println("ID INCORRECTO");
        }
    } // [Matias de Andrade] Crea un control por cada dia de duracion del tratamiento elegido

    public boolean profesionalNotificacion(Profesional profesional) {
        boolean tieneRespuestas = true;
        for (Integer c : profesional.getListaPacientesEnTratamiendoID()) {
            Paciente aux = buscarPacienteID(c);
            if(aux!=null) {
                int index = aux.registroTratamiento.size();
                if (index > 1) {
                    Registro auxRegistro = aux.registroTratamiento.get(index - 1);
                    if (!auxRegistro.completoRegistro) {
                        tieneRespuestas = false;
                    }
                }
            }
        }
        return tieneRespuestas;
    } // [Matias de Andrade]

    public void profesionalNotifTareasIncompletas(Usuario profesional, ArrayList<Paciente> pacientes) {
        short contadorNoCompletados = 0;
        Profesional profesionalAux = (Profesional) profesional;
        for (Integer c : profesionalAux.listaPacientesEnTratamiendoID) {
            Paciente x = buscarPacienteID(c);
            if (x != null) {
                if (x.registroTratamiento.size() > 1) {
                    int size = x.registroTratamiento.size();
                    Registro checkeoRegistro = x.registroTratamiento.get(size-1);
                    if (!checkeoRegistro.isCompletoRegistro()) {
                        System.out.println("No completo el registro el paciente: " + x.getEmail());
                        contadorNoCompletados++;
                    }
                }
            }
        }
        System.out.println("Un total de " + contadorNoCompletados + " NO completaron su registro diario");
    } // [Matias de Andrade]

    public LocalDate getFECHAHOY() {
        return FECHAHOY;
    } // [Matias de Andrade]

    public void setFECHAHOY(LocalDate FECHAHOY) {
        this.FECHAHOY = FECHAHOY;
    } // [Matias de Andrade]

    public void darAltaPaciente(Paciente p) {
        p.setOrdenAsignada(null);
        p.limpiarLista();
        p.registroTratamiento = new LinkedList<>();
        p.setEspecialidadID(-1);
        System.out.println("Paciente dado de alta");

    } // [Matias de Andrade]

    public void extenderTratamiento(Paciente paciente, int dias, LocalDate FECHAHOY) {
        paciente.ordenAsignada.setFechaFin(FECHAHOY.plusDays(dias));
    } // [Matias de Andrade]

    public void verPacientesFinalizadoTratamiemto(Profesional profesional, LocalDate FECHAHOY) {
        for (Integer id :
                profesional.listaPacientesEnTratamiendoID) {
            Paciente aux = buscarPacienteID(id);
            if (aux.ordenAsignada.getFechaFin().isBefore(FECHAHOY) || aux.ordenAsignada.getFechaFin().equals(FECHAHOY)) {
                System.out.println("El paciente: " + aux.getNombre() + " ID " + aux.getID() + " termino su tratamiento");
            }

        }
    } // [Matias de Andrade]

    public void simularDIA(ArrayList<Profesional> profesionales){
        for (Profesional x:profesionales) {
            for (Integer id:x.listaPacientesEnTratamiendoID) {
                Paciente paciente = buscarPacienteID(id);
                if(FECHAHOY.isBefore(paciente.ordenAsignada.getFechaFin())) {
                    paciente.registroTratamiento.add(new Registro());
                }
            }
        }
        setFECHAHOY(FECHAHOY.plusDays(1));
    } // [Matias de Andrade]

    public void verRegistroPaciente(Profesional profesional) {
        if(profesional.listaPacientesEnTratamiendoID.size()!=0) {
            verListadoPacientesProfTratados(profesional);
            System.out.println("\nIngrese ID paciente a ver Registro");
            Scanner scan = new Scanner(System.in);
            int id = scan.nextInt();
            Paciente aux = buscarPacienteID(id);
            if (aux != null) {
                if (aux.ordenAsignada.control != null) {
                    if (profesional.getID() != aux.ordenAsignada.getIdProfesional()) {
                        System.out.println("Ese paciente no se encuentra a su servicio");
                    }
                    {
                        LocalDate fechaAux = aux.ordenAsignada.getFechaInicio();
                        if (aux.registroTratamiento.size() != 0) {
                            for (Registro x : aux.registroTratamiento) {
                                System.out.println("Dia: " + fechaAux);
                                System.out.println(x.getTareasCompletadas());
                                fechaAux = fechaAux.plusDays(1);
                            }
                        }
                    }
                } else {
                    System.out.println("El paciente no tiene una orden de tratamiento activa");
                }
            } else {
                System.out.println("ID erroneo e paciente");
            }
        }else {
            System.out.println("\nNo dispone de pacientes activos");
        }
    } // [Matias de Andrade]
    public void eliminarDatos(){
        listadoAdmin.removeAll(listadoAdmin);
        System.out.println(" " + listadoAdmin.toString());
        listadoPaciente.removeAll(listadoPaciente);
        listadoProfesional.removeAll(listadoProfesional);
        listadoTarea.removeAll(listadoTarea);
        listadoControl.removeAll(listadoControl);
        listadoEnfermedad.removeAll(listadoEnfermedad);
        listadoEspecialidad.removeAll(listadoEspecialidad);
    } // [Matias de Andrade]

}
