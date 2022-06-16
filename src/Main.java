import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Sistema clinica = new Sistema();
        clinica.setFECHAHOY(LocalDate.now());

        String path = System.getProperty("user.dir") + "\\json\\";
        System.out.println("Ruta de archivos: " + path);

        clinica.cargarSistema();
        login(clinica);
    }

    // -----------------------------------------------------------------------------------------

    public static void login(Sistema clinica) {
        Usuario user = null;
        Scanner scan = new Scanner(System.in);
        int opcion = 0;
        boolean continua;
        do {
            try {
                continua = false;
                System.out.println("\n\n\t>> Sistema de gestion <<");
                System.out.println("\n*********************************");
                System.out.println("\n\t>> Credenciales de prueba:");
                System.out.println("\nadmin -> 123456");
                System.out.println("profesional -> 123456");
                System.out.println("paciente -> 123456");
                System.out.println("\n*********************************");
                System.out.println("\n[1] Ingresar [2] Simular dia [3] Reiniciar datos [0] Guardar y salir");
                opcion = scan.nextInt();
            } catch (InputMismatchException ex) {
                System.out.println("Debe ingresar una opcion");
                scan.next();
                continua = true;
            }
        } while (continua);
        String path = System.getProperty("user.dir") + "\\json\\";
        switch (opcion){
            case 1:
                do {
                    System.out.println("\nIngrese email: ");
                    scan.nextLine();
                    String email = scan.nextLine();
                    System.out.println("Ingrese clave");
                    String clave = scan.nextLine();
                    user = clinica.login(email, clave);
                    if (user != null) {
                        switch (user.getTipoUsuario()) {
                            case admin -> menuAdmin(clinica, (Admin) user);
                            case profesional -> menuProfesional(clinica, (Profesional) user);
                            case paciente -> menuPaciente(clinica, (Paciente) user);
                        }
                    } else {
                        System.out.println("Correo o clave incorrectos...");
                        pausa();
                    }
                } while (user == null);
            case 2:
                clinica.simularDIA(clinica.listadoProfesional);
                login(clinica);
            case 3:
                clinica.eliminarDatos();
                clinica.generarDatosDefault();
                clinica.actualizarSistema();
                System.out.println("\nReinicio de datos completado");
                System.out.println("\nDatos guardados con exito");
                login(clinica);
            case 0:
                clinica.actualizarSistema();
                System.out.println("Datos guardados con exito");
                System.exit(0);
            default:
                System.out.println("Valor invalido");
        }
    } // [Braian D'Aleo]

    public static boolean estaVacio(String nombre) {
        return nombre.length() == 0;
    } // Verifica si un string esta vacio [Braian D'Aleo]

    public static void pausa() {
        System.out.println("\n\tPresione ENTER para continuar...");
        try {
            int valor = System.in.read(new byte[2]);
        } catch (IOException e) {
            e.printStackTrace();
        }
    } // Nos obliga a presionar Enter para continuar [Braian D'Aleo]

    public static void menuAdmin(Sistema clinica, Admin admin) {
        int opcion = 0;
        Scanner scan = new Scanner(System.in);
        do {
            boolean continua;
            do {
                try {
                    continua = false;
                    System.out.println("\n\tBienvendo " + admin.getNombre());
                    System.out.println("\n*********************************");
                    System.out.println("\nPanel de administracion");
                    System.out.println("1 - Menu tareas de control");
                    System.out.println("2 - Menu enfermedades");
                    System.out.println("3 - Menu especialidades");
                    System.out.println("4 - Menu profesionales");
                    System.out.println("5 - Menu pacientes");
                    System.out.println("0 - Para salir");
                    System.out.println("\n\tIngrese opcion:");
                    opcion = scan.nextInt();
                } catch (InputMismatchException ex) {
                    System.out.println("Debe ingresar obligatoriamente una opcion");
                    scan.next();
                    continua = true;
                }
            } while (continua);
            switch (opcion) {
                case 1 -> {
                    menuAdminTareas(clinica, admin);
                    pausa();
                }
                case 2 -> {
                    menuAdminEnfermedades(clinica, admin);
                    pausa();
                }
                case 3 -> {
                    menuAdminEspecialidades(clinica, admin);
                    pausa();
                }
                case 4 -> {
                    menuAdminProfesional(clinica, admin);
                    pausa();
                }
                case 5 -> {
                    menuAdminPaciente(clinica, admin);
                    pausa();
                }
                default -> {
                    System.out.println("Opcion invalida!");
                    pausa();
                }
                case 0 -> {
                }
            }
        } while (opcion != 0);
        login(clinica); // Vuelve al menu de validacion de usuario
    } // Menu vista administrador [Braian D'Aleo]

    public static void menuAdminTareas(Sistema clinica, Admin admin) {
        int opcion = 0;
        Scanner scan = new Scanner(System.in);
        do {
            boolean continua;
            do {
                try {
                    continua = false;
                    System.out.println("\nPanel de tareas de control");
                    System.out.println("1 - Ver tareas de control");
                    System.out.println("2 - Agregar tarea de control");
                    System.out.println("3 - Eliminar tarea de control");
                    System.out.println("0 - Para volver al menu anterior");
                    System.out.println("\n\tIngrese opcion:");
                    opcion = scan.nextInt();
                } catch (InputMismatchException ex) {
                    System.out.println("Debe ingresar obligatoriamente una opcion");
                    scan.next();
                    continua = true;
                }
            } while (continua);
            switch (opcion) {
                case 1 -> {
                    clinica.verTareasControl();
                    pausa();
                }
                case 2 -> {
                    System.out.println("Ingrese nombre de la nueva tarea: ");
                    scan.nextLine(); // Limpia
                    String tarea = scan.nextLine();
                    if (!estaVacio(tarea) && clinica.agregarTarea(tarea)) {
                        System.out.println("Tarea agregada: " + tarea);
                        clinica.actualizarTarea();
                    } else {
                        System.out.println("Tarea duplicada o error al cargar");
                    }
                    pausa();
                }
                case 3 -> {
                    System.out.println("\nListado de tareas:");
                    clinica.verTareasControl();
                    int id = 0;
                    do {
                        try {
                            continua = false;
                            System.out.println("\nIngrese ID a borrar:");
                            id = scan.nextInt();
                        } catch (InputMismatchException ex) {
                            System.out.println("Debe ingresar obligatoriamente una opcion");
                            scan.next();
                            continua = true;
                        }
                    } while (continua);
                    if (clinica.borrarTareaID(id)) {
                        System.out.println("Elemento borrado con exito");
                        clinica.actualizarTarea();

                    } else {
                        System.out.println("ID incorrecto o no se pudo borrar el elemento");
                    }
                    pausa();
                }
                default -> {
                    System.out.println("Opcion invalida!");
                    pausa();
                }
                case 0 -> {
                }
            }
        } while (opcion != 0);
        menuAdmin(clinica, admin); // Vuelve al menu anterior
    } // Menu admin -> tareas [Braian D'Aleo]

    public static void menuAdminEnfermedades(Sistema clinica, Admin admin) {
        int opcion = 0;
        Scanner scan = new Scanner(System.in);
        do {
            boolean continua;
            do {
                try {
                    continua = false;
                    System.out.println("\nPanel de enfermedades");
                    System.out.println("1 - Ver lista de enfermedades");
                    System.out.println("2 - Agregar enfermedad");
                    System.out.println("3 - Eliminar enfermedad");
                    System.out.println("0 - Para volver al menu anterior");
                    System.out.println("\n\tIngrese opcion:");
                    opcion = scan.nextInt();
                } catch (InputMismatchException ex) {
                    System.out.println("Debe ingresar obligatoriamente una opcion");
                    scan.next();
                    continua = true;
                }
            } while (continua);
            switch (opcion) {
                case 1 -> {
                    clinica.verEnfermedades();
                    pausa();
                }
                case 2 -> {
                    System.out.println("Ingrese nombre de la enfermedad a agregar: ");
                    scan.nextLine(); // Limpia
                    String enfermedad = scan.nextLine();
                    if (!estaVacio(enfermedad) && clinica.agregarEnfermedad(enfermedad)) {
                        System.out.println("Enfermedad agregada: " + enfermedad);
                        clinica.actualizarEnfermedad();
                    } else {
                        System.out.println("Enfermedad duplicada o error al cargar");
                    }
                    pausa();
                }
                case 3 -> {
                    System.out.println("\nListado de enfermedades");
                    clinica.verEnfermedades();
                    int id = 0;
                    do {
                        try {
                            continua = false;
                            System.out.println("\nIngrese ID a borrar:");
                            id = scan.nextInt();
                        } catch (InputMismatchException ex) {
                            System.out.println("Debe ingresar obligatoriamente una opcion");
                            scan.next();
                            continua = true;
                        }
                    } while (continua);
                    if (clinica.borrarEnfermedadID(id)) {
                        System.out.println("Elemento borrado con exito");
                        clinica.actualizarEnfermedad();
                    } else {
                        System.out.println("ID incorrecto o no se pudo borrar el elemento");
                    }
                    pausa();
                }
                default -> {
                    System.out.println("Opcion invalida!");
                    pausa();
                }
                case 0 -> {
                }
            }
        } while (opcion != 0);
        menuAdmin(clinica, admin); // Vuelve al menu anterior
    } // Menu admin -> enfermedades [Braian D'Aleo]

    public static void menuAdminEspecialidades(Sistema clinica, Admin admin) {
        int opcion = 0;
        Scanner scan = new Scanner(System.in);
        do {
            boolean continua;
            do {
                try {
                    continua = false;
                    System.out.println("\nPanel de especialidades");
                    System.out.println("1 - Ver lista de especialidades");
                    System.out.println("2 - Agregar especialidad");
                    System.out.println("3 - Eliminar especialidad");
                    System.out.println("0 - Para volver al menu anterior");
                    System.out.println("\n\tIngrese opcion:");
                    opcion = scan.nextInt();
                } catch (InputMismatchException ex) {
                    System.out.println("Debe ingresar obligatoriamente una opcion");
                    scan.next();
                    continua = true;
                }
            } while (continua);
            switch (opcion) {
                case 1 -> {
                    clinica.verEspecialidades();
                    pausa();
                }
                case 2 -> {
                    System.out.println("Ingrese nombre de la especialidad a agregar: ");
                    scan.nextLine(); // Limpia
                    String especialidad = scan.nextLine();
                    if (!estaVacio(especialidad) && clinica.agregarEspecialidad(especialidad)) {
                        System.out.println("Especialidad agregada: " + especialidad);
                        clinica.actualizarEspecialidad();
                    } else {
                        System.out.println("Especialidad duplicada o error al cargar");
                    }
                    pausa();
                }
                case 3 -> {
                    System.out.println("\nListado de especialidades");
                    clinica.verEspecialidades();
                    int id = 0;
                    do {
                        try {
                            continua = false;
                            System.out.println("\nIngrese ID a borrar:");
                            id = scan.nextInt();
                        } catch (InputMismatchException ex) {
                            System.out.println("Debe ingresar obligatoriamente una opcion");
                            scan.next();
                            continua = true;
                        }
                    } while (continua);
                    if (clinica.borrarEspecialidadID(id)) {
                        System.out.println("Elemento borrado con exito");
                        clinica.actualizarEspecialidad();
                    } else {
                        System.out.println("ID incorrecto o no se pudo borrar el elemento");
                    }
                    pausa();
                }
                default -> {
                    System.out.println("Opcion invalida!");
                    pausa();
                }
                case 0 -> {
                }
            }
        } while (opcion != 0);
        menuAdmin(clinica, admin); // Vuelve al menu anterior
    } // Menu admin -> especialidades [Braian D'Aleo]

    public static void menuAdminProfesional(Sistema clinica, Admin admin) {
        int opcion = 0;
        Scanner scan = new Scanner(System.in);
        do {
            boolean continua;
            do {
                try {
                    continua = false;
                    System.out.println("\nPanel de profesionales");
                    System.out.println("1 - Ver lista de profesionales activos");
                    System.out.println("2 - Agregar profesional");
                    System.out.println("3 - Asignar paciente a profesional");
                    System.out.println("4 - Activar / desactivar profesional");
                    System.out.println("0 - Para volver al menu anterior");
                    System.out.println("\n\tIngrese opcion:");
                    opcion = scan.nextInt();
                } catch (InputMismatchException ex) {
                    System.out.println("Debe ingresar obligatoriamente una opcion");
                    scan.next();
                    continua = true;
                }
            } while (continua);
            switch (opcion) {
                case 1 -> {
                    System.out.println("\nLista de profesionales activos");
                    clinica.verProfesionalesActivos();
                    pausa();
                }
                case 2 -> {
                    System.out.println("\nIngrese nombre del profesional: ");
                    scan.nextLine();
                    String nombre = scan.nextLine();
                    if (!estaVacio(nombre)) {
                        System.out.println("\nIngrese correo:");
                        String email = scan.nextLine();
                        if (!estaVacio(email) && !clinica.buscarMail(email)) {
                            System.out.println("\nIngrese clave:");
                            String clave = scan.nextLine();
                            if (!estaVacio(clave)) {
                                System.out.println("\nIngrese DNI:");
                                String dni = scan.nextLine();
                                if (!estaVacio(dni) && !clinica.buscarDNI(dni)) {
                                    System.out.println("\nLista de especialidades:");
                                    clinica.verEspecialidades();
                                    int idEspecialidad = 0;
                                    do {
                                        try {
                                            continua = false;
                                            System.out.println("\nIngrese ID especialidad solicitada por el paciente:");
                                            idEspecialidad = scan.nextInt();
                                        } catch (InputMismatchException ex) {
                                            System.out.println("Debe ingresar obligatoriamente una opcion");
                                            scan.next();
                                            continua = true;
                                        }
                                    } while (continua);
                                    if (idEspecialidad < clinica.contarEspecialidad()) {
                                        Profesional aux = new Profesional(nombre, email, clave, dni, idEspecialidad);
                                        if (clinica.agregarProfesional(aux)) {
                                            System.out.println("\nProfesional agregado con exito");
                                            clinica.actualizarProfesional();
                                        } else {
                                            System.out.println("\nError al cargar el profesional");
                                        }
                                    } else {
                                        System.out.println("\nEspecialidad invalida!");
                                    }
                                } else {
                                    System.out.println("\nEl DNI ingresado no es valido, o ya fue ingresado!");
                                }
                            }
                        } else {
                            System.out.println("El correo ya se encuentra registrado!");
                        }
                    }
                    pausa();
                }
                case 3 -> {
                    System.out.println("\nLista de pacientes activos");
                    clinica.verPacientesActivos();
                    int idPaciente3 = 0;
                    do {
                        try {
                            continua = false;
                            System.out.println("\nIngrese ID paciente");
                            idPaciente3 = scan.nextInt();
                        } catch (InputMismatchException ex) {
                            System.out.println("Debe ingresar obligatoriamente una opcion");
                            scan.next();
                            continua = true;
                        }
                    } while (continua);
                    Paciente auxPaciente = clinica.buscarPacienteID(idPaciente3);
                    if (auxPaciente != null) {
                        System.out.println("\nListado de profesionales en esa especialidad: ");
                        clinica.verProfesionalesEspecialidad(auxPaciente.getEspecialidad());
                        int idProfesional2 = 0;
                        do {
                            try {
                                continua = false;
                                System.out.println("\nIngrese ID de profesional a asignar:");
                                idProfesional2 = scan.nextInt();
                            } catch (InputMismatchException ex) {
                                System.out.println("Debe ingresar obligatoriamente una opcion");
                                scan.next();
                                continua = true;
                            }
                        } while (continua);
                        if (clinica.agregarPacienteProfesional(auxPaciente, idProfesional2)) {
                            System.out.println("Paciente asignado correctamente");
                            clinica.actualizarProfesional();
                            clinica.actualizarPaciente();
                        } else {
                            System.out.println("Error al asignar el paciente");
                        }
                    } else {
                        System.out.println("Paciente no encontrado o incorrecto");
                    }
                    pausa();
                }
                case 4 -> {
                    System.out.println("\nListado de profesionales activos e inactivos");
                    clinica.verProfesionales();
                    int id = 0;
                    do {
                        try {
                            continua = false;
                            System.out.println("\nIngrese ID a cambiar estado:");
                            id = scan.nextInt();
                        } catch (InputMismatchException ex) {
                            System.out.println("Debe ingresar obligatoriamente una opcion");
                            scan.next();
                            continua = true;
                        }
                    } while (continua);
                    if (clinica.borrarProfesionalID(id)) {
                        System.out.println("Estado cambiado con exito");
                        clinica.actualizarProfesional();
                    } else {
                        System.out.println("ID incorrecto o no se pudo borrar el elemento");
                    }
                    pausa();
                }
                default -> {
                    System.out.println("Opcion invalida!");
                    pausa();
                }
                case 0 -> {
                }
            }
        } while (opcion != 0);
        menuAdmin(clinica, admin); // Vuelve al menu anterior
    } // Menu admin -> profesionales [Braian D'Aleo]

    public static void menuAdminPaciente(Sistema clinica, Admin admin) {
        int opcion = 0;
        Scanner scan = new Scanner(System.in);
        do {
            boolean continua;
            do {
                try {
                    continua = false;
                    System.out.println("\nPanel de pacientes");
                    System.out.println("1 - Ver lista de pacientes activos");
                    System.out.println("2 - Asignar paciente a profesional");
                    System.out.println("3 - Agregar paciente y asignar a un profesional");
                    System.out.println("4 - Activar / desactivar paciente");
                    System.out.println("0 - Para volver al menu anterior");
                    System.out.println("\n\tIngrese opcion:");
                    opcion = scan.nextInt();
                } catch (InputMismatchException ex) {
                    System.out.println("Debe ingresar obligatoriamente una opcion");
                    scan.next();
                    continua = true;
                }
            } while (continua);
            switch (opcion) {
                case 1 -> {
                    System.out.println("\nLista de pacientes activos");
                    clinica.verPacientesActivos();
                    pausa();
                }
                case 2 -> {
                    System.out.println("\nLista de pacientes activos");
                    clinica.verPacientesActivos();
                    int idPaciente3 = 0;
                    do {
                        try {
                            continua = false;
                            System.out.println("\nIngrese ID paciente");
                            idPaciente3 = scan.nextInt();
                        } catch (InputMismatchException ex) {
                            System.out.println("Debe ingresar obligatoriamente una opcion");
                            scan.next();
                            continua = true;
                        }
                    } while (continua);
                    Paciente auxPaciente = clinica.buscarPacienteID(idPaciente3);
                    if (auxPaciente != null) {
                        System.out.println("\nListado de profesionales en esa especialidad");
                        clinica.verProfesionalesEspecialidad(auxPaciente.getEspecialidad());
                        int idProfesional2 = 0;
                        do {
                            try {
                                continua = false;
                                System.out.println("\nIngrese ID de profesional a asignar:");
                                idProfesional2 = scan.nextInt();
                            } catch (InputMismatchException ex) {
                                System.out.println("Debe ingresar obligatoriamente una opcion");
                                scan.next();
                                continua = true;
                            }
                        } while (continua);
                        if (clinica.agregarPacienteProfesional(auxPaciente, idProfesional2)) {
                            System.out.println("Paciente asignado correctamente");
                            clinica.actualizarPaciente();
                            clinica.actualizarProfesional();
                        } else {
                            System.out.println("Error al asignar el paciente");
                        }
                    } else {
                        System.out.println("Paciente no encontrado o incorrecto");
                    }
                    pausa();
                }
                case 3 -> {
                    System.out.println("Ingrese nombre del paciente: ");
                    scan.nextLine();
                    String nombre = scan.nextLine();
                    if (!estaVacio(nombre)) {
                        System.out.println("Ingrese correo:");
                        String email = scan.nextLine();
                        if (!estaVacio(email) && !clinica.buscarMail(email)) {
                            System.out.println("Ingrese clave:");
                            String clave = scan.nextLine();
                            if (!estaVacio(clave)) {
                                System.out.println("Ingrese DNI:");
                                String dni = scan.nextLine();
                                if (!estaVacio(dni) && !clinica.buscarDNI(dni)) {
                                    System.out.println("\nLista de especialidades:");
                                    clinica.verEspecialidades();
                                    int idEspecialidad = 0;
                                    do {
                                        try {
                                            continua = false;
                                            System.out.println("Ingrese ID especialidad:");
                                            idEspecialidad = scan.nextInt();
                                        } catch (InputMismatchException ex) {
                                            System.out.println("Debe ingresar obligatoriamente una opcion");
                                            scan.next();
                                            continua = true;
                                        }
                                    } while (continua);
                                    if (clinica.buscarEspecialidad(idEspecialidad)) {
                                        Paciente aux = new Paciente(nombre, email, clave, dni, idEspecialidad);
                                        if (clinica.agregarPaciente(aux)) {
                                            System.out.println("\nPaciente agregado con exito");
                                            System.out.println("\nListado de profesionales en esa especialidad");
                                            clinica.verProfesionalesEspecialidad(idEspecialidad);
                                            int idProfesional = 0;
                                            do {
                                                try {
                                                    continua = false;
                                                    System.out.println("\nIngrese ID de profesional a asignar:");
                                                    idProfesional = scan.nextInt();
                                                } catch (InputMismatchException ex) {
                                                    System.out.println("Debe ingresar obligatoriamente una opcion");
                                                    scan.next();
                                                    continua = true;
                                                }
                                            } while (continua);
                                            if (clinica.agregarPacienteProfesional(aux, idProfesional)) {
                                                System.out.println("Paciente asignado correctamente");
                                                clinica.actualizarPaciente();
                                                clinica.actualizarProfesional();
                                            } else {
                                                System.out.println("Error al asignar el paciente");
                                            }
                                        } else {
                                            System.out.println("Error al cargar el paciente");
                                        }
                                    }
                                } else {
                                    System.out.println("\nEl DNI ingresado no es valido o ya se encuentra registrado!");
                                }
                            }
                        }
                    }
                    pausa();
                }
                case 4 -> {
                    System.out.println("\nListado de pacientes activos e inactivos");
                    clinica.verPacientes();
                    int id = 0;
                    do {
                        try {
                            continua = false;
                            System.out.println("\nIngrese ID a cambiar estado:");
                            id = scan.nextInt();
                        } catch (InputMismatchException ex) {
                            System.out.println("Debe ingresar obligatoriamente una opcion");
                            scan.next();
                            continua = true;
                        }
                    } while (continua);
                    if (clinica.borrarPacienteID(id)) {
                        System.out.println("Estado cambiado con exito");
                        clinica.actualizarPaciente();
                        clinica.actualizarProfesional();
                    } else {
                        System.out.println("ID incorrecto o no se pudo borrar el elemento");
                    }
                    pausa();
                }
                default -> {
                    System.out.println("Opcion invalida!");
                    pausa();
                }
                case 0 -> {
                }
            }
        } while (opcion != 0);
        menuAdmin(clinica, admin); // Vuelve al menu anterior
    } // Menu admin -> pacientes [Braian D'Aleo]

    // --------------------------------------------------------------------------------------
    public static void menuProfesional(Sistema clinica, Profesional profesional) { // -> Menu profesinal
        int opcion = 0;
        Scanner scan = new Scanner(System.in);
        do {
            boolean continua;
            do {
                System.out.println("\n\tBienvendo " + profesional.getNombre());
                System.out.println("\nPanel de administracion");
                System.out.println("\n*********************************");
                notificacionPaciente(clinica, profesional); // <- Notificacion paciente nuevo
                if (!clinica.profesionalNotificacion(profesional)) {
                    System.out.println("\n>> Dispone de pacientes que no completaron su tratamiento ayer");
                } else {
                    System.out.println("\n>> No dispone de tratamientos incompletos de ayer");
                }
                try {
                    continua = false;
                    System.out.println("\n*********************************");
                    System.out.println("\n1 - Ver pacientes nuevos");
                    System.out.println("2 - Ver pacientes en tratamiento");
                    System.out.println("3 - Ver listado de controles predeterminados");
                    System.out.println("4 - Asignar control a paciente");
                    System.out.println("5 - Dar de alta paciente");
                    System.out.println("6 - Ver pacientes que ayer no cumplieron con el tratamiento");
                    System.out.println("7 - Extender tratamiento");
                    System.out.println("8 - Ver pacientes que finalizaron el tratamiento");
                    System.out.println("9 - Ver registro de un paciente");
                    System.out.println("0 - Para volver al menu anterior");
                    System.out.println("\n\tIngrese opcion:");
                    opcion = scan.nextInt();
                } catch (InputMismatchException ex) {
                    System.out.println("Debe ingresar obligatoriamente una opcion");
                    scan.next();
                    continua = true;
                }
            } while (continua);
            switch (opcion) {
                case 1 -> {
                    if (clinica.contadorPacientesNuevos(profesional) > 0) {
                        System.out.println("\nListado de pacientes sin tratamiento: \n");
                        clinica.verListadoPacientesProfNuevos(profesional);
                    } else {
                        System.out.println("No dispone de pacientes nuevos");
                    }
                    pausa();
                }
                case 2 -> {
                    if (clinica.contadorPacientesTratamiento(profesional) > 0) {
                        System.out.println("\nListado de pacientes En tratamiento: \n");
                        clinica.verListadoPacientesProfTratados(profesional);
                    } else {
                        System.out.println("No dispone de pacientes en tratamiento");
                    }
                    pausa();
                }
                case 3 -> {
                    clinica.verListadoControles();
                    pausa();
                }
                case 4 -> menuAsignar(clinica, profesional);
                case 5 -> {
                    System.out.println("\nListado de pacientes en tratamiento:\n");
                    clinica.verListadoPacientesProfTratados(profesional);
                    int idPaciente = 0;
                    do {
                        try {
                            continua = false;
                            System.out.println("\nIngrese ID paciente: ");
                            idPaciente = scan.nextInt();
                        } catch (InputMismatchException ex) {
                            System.out.println("Debe ingresar obligatoriamente una opcion");
                            scan.next();
                            continua = true;
                        }
                    } while (continua);
                    Paciente auxPaciente = clinica.buscarPacienteID(idPaciente);
                    if(auxPaciente!=null) {
                        System.out.println("\nLista de tratamientos aplicados vigentes");
                        auxPaciente.verOrdenInfo();
                    }else {
                        System.out.println("Paciente no encontrado");
                        menuProfesional(clinica,profesional);
                    }
                    int respuesta = 0;
                    do {
                        try {
                            continua = false;
                            System.out.println("[1] Dar de alta [0] Salir");
                            respuesta = scan.nextInt();
                        } catch (InputMismatchException ex) {
                            System.out.println("Debe ingresar obligatoriamente una opcion");
                            scan.next();
                            continua = true;
                        }
                    } while (continua);
                    if (respuesta == 1) {
                        int index = profesional.listaPacientesEnTratamiendoID.indexOf(idPaciente);
                        profesional.listaPacientesEnTratamiendoID.remove(index);
                        if(auxPaciente!=null) {
                            clinica.darAltaPaciente(auxPaciente);
                            System.out.println("Paciente dado de alta correctamente");
                            clinica.actualizarPaciente();
                            clinica.actualizarProfesional();
                        }else {
                            System.out.println("error al encontrar el paciente");
                        }
                    } else {
                        System.out.println("Se cancelo la orden");
                    }
                    pausa();
                    break;
                }
                case 6 -> {
                    clinica.profesionalNotifTareasIncompletas(profesional, clinica.listadoPaciente);
                    pausa();
                    break;
                }
                case 7 -> {
                    for (Integer id : (profesional).listaPacientesEnTratamiendoID) {
                        Paciente pacienteAux = clinica.buscarPacienteID(id);
                        System.out.println("\n" + pacienteAux.getNombre());
                        System.out.println(pacienteAux.getDni());
                        System.out.println(pacienteAux.getID());
                        System.out.println("--------------------------------------");
                    }
                    System.out.println("Ingrese ID paciente a extender tratamiento");
                    int idUsuario = -1;
                    try {
                        idUsuario = scan.nextInt();
                    } catch (InputMismatchException a) {
                        System.out.println("ingreso una opcion valida");
                        scan.next();
                        break;
                    }
                    Paciente auxPaciente = clinica.buscarPacienteID(idUsuario);
                    if (auxPaciente != null) {
                        while (true) {
                            System.out.println("Ingrese cantidad de dias que desea extender el tratamiento");
                            try {
                                int dias = scan.nextInt();
                                clinica.extenderTratamiento(auxPaciente, dias, clinica.getFECHAHOY());
                                System.out.println("Se extendio el tratamiento " + dias + " Dias");
                                clinica.actualizarPaciente();
                                clinica.actualizarProfesional();
                            } catch (InputMismatchException a) {
                                System.out.println("Valor invalido, ingrese cantidad de dias");
                            }
                        }
                    }
                    pausa();
                    break;
                }
                case 8 -> {
                    clinica.verPacientesFinalizadoTratamiemto(profesional, clinica.getFECHAHOY());
                    pausa();
                    break;
                }
                case 9 -> {
                    clinica.verRegistroPaciente(profesional);
                    pausa();
                    break;
                }
                default -> {
                    System.out.println("Opcion invalida");
                    pausa();
                }
                case 0 -> {
                }
            }
        } while (opcion != 0);
        login(clinica); // Vuelve al menu de validacion de usuario
    } // Menu profesional [Matias de Andrade / Braian D'Aleo]
    public static void menuAsignar(Sistema clinica, Profesional profesional) {
        Scanner scan = new Scanner(System.in);
        boolean continua;
        if (clinica.contadorPacientesNuevos(profesional) < 1) {
            System.out.println("No dispone de pacientes sin tratar");
        } else {
            int duracion = 0;
            int idControl = 0;
            int respuesta = 0;
            int idPaciente = 0;
            System.out.println("\nListado de pacientes sin tratamiento: \n");
            clinica.verListadoPacientesProfNuevos(profesional);
            do {
                try {
                    continua = false;
                    System.out.println("\nIngrese ID paciente a tratar: ");
                    idPaciente = scan.nextInt();
                } catch (InputMismatchException ex) {
                    System.out.println("Debe ingresar obligatoriamente una opcion");
                    scan.next();
                    continua = true;
                }
            } while (continua);
            do {
                try {
                    continua = false;
                    System.out.println("\nDesea agregar un control prestablecido? Ingrese: ");
                    System.out.println("[1] Control prestablecido [2] Crear control personalizado [0] Cancelar");
                    respuesta = scan.nextInt();
                } catch (InputMismatchException ex) {
                    System.out.println("Debe ingresar obligatoriamente una opcion");
                    scan.next();
                    continua = true;
                }
            } while (continua);
            switch (respuesta) {
                case 1 -> {
                    System.out.println("\n>> Listado de controles:");
                    clinica.verListadoControles();
                    do {
                        try {
                            continua = false;
                            System.out.println("\nIngrese ID de control a aplicar: ");
                            idControl = scan.nextInt();
                            System.out.println("Ingrese dias de duracion del tratamiento: ");
                            duracion = scan.nextInt();
                        } catch (InputMismatchException ex) {
                            System.out.println("Debe ingresar obligatoriamente una opcion");
                            scan.next();
                            continua = true;
                        }
                    } while (continua);
                    clinica.verEnfermedades();
                    boolean flag = false;
                    Enfermedad enfermedad = null;
                    do {
                        System.out.println("Ingrese ID enfermedad");
                        int id = -1;
                        try {
                            id = scan.nextInt();
                            enfermedad = clinica.buscarEnfermedad(id);
                            if (enfermedad != null) {
                                System.out.println("Se encontro la enfermedad " + enfermedad.getNombre());
                                flag = true;
                            } else {
                                System.out.println("Enfermedad no existente volver a asignar");
                            }
                        } catch (InputMismatchException a) {
                            System.out.println("Ingrese un ID correcto");
                            scan.next();
                        }
                    } while (!flag);
                    Control nuevoControl = clinica.buscarControlID(idControl);
                    clinica.cargarControlPacientes(profesional, idPaciente, nuevoControl, duracion, enfermedad);
                    System.out.println("Control asignado con exito");
                    clinica.actualizarPaciente();
                    clinica.actualizarProfesional();
                    pausa();
                    break;
                }
                case 2 -> {
                    ArrayList<Integer> tareas = new ArrayList<>();
                    System.out.println("Listado de enfermedades:");
                    clinica.verEnfermedades();
                    Enfermedad enfermedad = new Enfermedad();
                    int idEnfermedad = 0;
                    Control nuevoControl = new Control();
                    do {
                        try {
                            continua = false;
                            System.out.println("Ingrese ID enfermedad a asignar:");
                            idEnfermedad = scan.nextInt();
                        } catch (InputMismatchException ex) {
                            System.out.println("Debe ingresar obligatoriamente una opcion");
                            scan.next();
                            continua = true;
                        }
                    } while (continua);
                    int respuestaDos = 0;
                    enfermedad = clinica.buscarEnfermedad(idEnfermedad);
                    if (enfermedad == null) {
                        System.out.println("\nEnfermedad no encontrada!");
                    } else {
                        do {
                            do {
                                try {
                                    continua = false;
                                    System.out.println("\nListado de tareas disponibles");
                                    clinica.verTareasControl();
                                    System.out.println("\nIngrese ID tarea: ");
                                    int idTarea = scan.nextInt();
                                    boolean existeTarea = false;
                                    for (Integer c : tareas) {
                                        if (c.equals(idTarea)) {
                                            existeTarea = true;
                                        }
                                    }
                                    if (!existeTarea && clinica.buscarTarea(idTarea)) {
                                        nuevoControl.listadoTareas.add(clinica.buscarTareaID(idTarea));
                                    } else {
                                        System.out.println("\nTarea repetida");
                                    }
                                    System.out.println("Desea seguir agregando?");
                                    System.out.println("[0] No [1] Si");
                                    respuestaDos = scan.nextInt();
                                } catch (InputMismatchException ex) {
                                    System.out.println("Debe ingresar obligatoriamente una opcion");
                                    scan.next();
                                    continua = true;
                                }
                            } while (continua);
                        } while (respuestaDos != 0);
                        int duracionDos = 0;
                        do {
                            try {
                                continua = false;
                                System.out.println("Ingrese dias de duracion del tratamiento: ");
                                duracionDos = scan.nextInt();
                            } catch (InputMismatchException ex) {
                                System.out.println("Debe ingresar obligatoriamente una opcion");
                                scan.next();
                                continua = true;
                            }
                        } while (continua);
                        nuevoControl.setNombre("Control custom");
                        clinica.cargarControlPacientes(profesional, idPaciente, nuevoControl, duracionDos, enfermedad);
                        System.out.println("\nTratamiento agregado con exito!");
                        clinica.actualizarPaciente();
                        clinica.actualizarProfesional();
                    }
                    pausa();
                    break;
                }
                default -> System.out.println("Opcion invalida");
                case 0 -> {
                }
            }
        }
    } // Menu profesional -> avoid nested switch [Matias de Andrade / Braian D'Aleo]
    public static void notificacionPaciente(Sistema clinica, Profesional profesional) {
        if (clinica.contadorPacientesNuevos(profesional) == 0) {
            System.out.println("\n>> " + profesional.getNombre() + ", no tiene pacientes nuevos");
        } else if (clinica.contadorPacientesNuevos(profesional) == 1) {
            System.out.println("\n>> " + profesional.getNombre() + ", usted tiene un paciente nuevo");
        } else if (clinica.contadorPacientesNuevos(profesional) > 1) {
            System.out.println("\n>> " + profesional.getNombre() + ", usted tiene: " + clinica.contadorPacientesNuevos(profesional) + " pacientes nuevos");
        }
    } // Menu profesional -> Notificacion nuevo paciente [Matias de Andrade / Braian D'Aleo]
    // --------------------------------------------------------------------------------------
    public static void menuPaciente(Sistema clinica, Paciente paciente) { // -> Menu profesinal
        int opcion = 0;
        Scanner scan = new Scanner(System.in);
        if (paciente.ordenAsignada.control == null) {
            System.out.println("No dispone de un plan asignado, favor de presentarse con profesional");
        } else {
            do {
                boolean continua;
                do {
                    System.out.println("\n\tBienvendo " + paciente.getNombre());
                    System.out.println("\nPanel de administracion");
                    System.out.println("\n*********************************");
                    if (!paciente.registroTratamiento.getLast().completoRegistro) {
                        System.out.println("\n>> Usted dispone de tareas para cargar hoy");
                    } else {
                        System.out.println("\n>> Usted no dispone de tareas a realizar hoy");
                    }
                    try {
                        continua = false;
                        System.out.println("\n*********************************");
                        System.out.println("\n1 - Ver lista de tarea de hoy");
                        System.out.println("2 - Ver detalle del tratamiento");
                        System.out.println("3 - Completar tarea de hoy");
                        System.out.println("0 - Para volver al menu anterior");
                        System.out.println("\n\tIngrese opcion:");
                        opcion = scan.nextInt();
                    } catch (InputMismatchException ex) {
                        System.out.println("Debe ingresar obligatoriamente una opcion");
                        scan.next();
                        continua = true;
                    }
                } while (continua);
                switch (opcion) {
                    case 1 -> {
                        if (paciente.registroTratamiento.getLast() != null) {
                            System.out.println(paciente.ordenAsignada.control.listadoTareas.toString());
                        } else {
                            System.out.println("\n> No hay tratamientos asignados para hoy");
                        }
                        pausa();
                        break;
                    }
                    case 2 -> {
                        if (paciente.ordenAsignada != null) {
                            System.out.println(paciente.ordenAsignada.toString());
                        }
                        pausa();
                        break;
                    }
                    case 3 -> {
                        if (paciente.registroTratamiento.getLast() != null) {
                            paciente.completarTareaHoy(clinica.getFECHAHOY());
                            clinica.actualizarPaciente();
                            clinica.actualizarProfesional();
                        } else {
                            System.out.println("\n> No hay tratamientos asignados para hoy");
                        }
                        pausa();
                        break;
                    }
                    default -> {
                        System.out.println("Opcion incorrecta");
                        pausa();
                        break;
                    }
                    case 0 -> {
                    }
                }
            } while (opcion != 0);
        }
        pausa();
        login(clinica);
    } // Menu admin -> tareas [Matias de Andrade / Braian D'Aleo]

// -----------------------------------------------------------------------------------------
// -----------------------------------------------------------------------------------------
// -----------------------------------------------------------------------------------------
}