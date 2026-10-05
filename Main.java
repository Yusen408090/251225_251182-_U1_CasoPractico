import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GestionEmpresa empresa = new GestionEmpresa(10);
        int opcion = 0;

        do {
            System.out.println("\n========== SISTEMA DE RECURSOS HUMANOS ==========");
            System.out.println("1. Registrar nuevo Departamento");
            System.out.println("2. Registrar Empleado en un Departamento");
            System.out.println("3. Consultar Empleado (Filtrado por Departamento)");
            System.out.println("4. Mostrar Plantillas");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");
            
            try {
                opcion = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                opcion = 0;
            }

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese nombre del departamento: ");
                    String nomDep = scanner.nextLine().trim();
                    System.out.print("Seleccione ubicación (1: Matriz, 2: Sucursal): ");
                    String ubiSel = scanner.nextLine().trim();
                    String ubicacion = ubiSel.equals("1") ? "Matriz" : "Sucursal";

                    if (empresa.buscarDepartamentoPorNombre(nomDep) != null) {
                        System.out.println("Error: Ya existe un departamento con ese nombre.");
                    } else {
                        Departamento nuevoDep = new Departamento(nomDep, ubicacion);
                        if (empresa.agregarDepartamento(nuevoDep)) {
                            System.out.println("¡Departamento registrado exitosamente!");
                        } else {
                            System.out.println("Error: Límite de departamentos alcanzado.");
                        }
                    }
                    break;

                case 2:
                    System.out.print("Ingrese el nombre del departamento: ");
                    String nomDepReg = scanner.nextLine().trim();
                    Departamento depEnc = empresa.buscarDepartamentoPorNombre(nomDepReg);

                    if (depEnc == null) {
                        System.out.println("Error: El departamento especificado no existe.");
                    } else if (depEnc.getNumEmpleados() >= 5) {
                        System.out.println("Error: La plantilla alcanzó el límite máximo de 5 empleados.");
                    } else {
                        System.out.print("Ingrese ID único del empleado (5 dígitos): ");
                        String id = scanner.nextLine().trim();
                        
                        if (id.length() != 5 || !id.matches("\\d+")) {
                            System.out.println("Error: El ID debe tener exactamente 5 dígitos numéricos.");
                            break;
                        }

                        if (depEnc.buscarEmpleadoPorId(id) != null) {
                            System.out.println("Error: Ya existe un empleado con el ID " + id + " en este departamento.");
                            break;
                        }

                        System.out.print("Ingrese el nombre completo del empleado: ");
                        String nombreEmp = scanner.nextLine().trim();

                        depEnc.agregarEmpleado(new Empleado(id, nombreEmp));
                        System.out.println("¡Empleado registrado con éxito!");
                    }
                    break;

                case 3:
                    System.out.println("\n--- CONSULTA DE EMPLEADO ---");
                    System.out.print("1° Paso - Ingrese el nombre del departamento a filtrar: ");
                    String depFiltro = scanner.nextLine().trim();

                    Departamento depBusc = empresa.buscarDepartamentoPorNombre(depFiltro);
                    if (depBusc == null) {
                        System.out.println("Resultado: No se encontró el departamento '" + depFiltro + "'.");
                    } else {
                        System.out.print("2° Paso - Ingrese el ID del empleado a consultar (5 dígitos): ");
                        String idBusc = scanner.nextLine().trim();

                        Empleado empBusc = depBusc.buscarEmpleadoPorId(idBusc);
                        if (empBusc != null) {
                            System.out.println("\n¡EMPLEADO ENCONTRADO!");
                            System.out.println("Departamento: " + depBusc.getNombre() + " (" + depBusc.getUbicacion() + ")");
                            System.out.println(empBusc.toString());
                        } else {
                            System.out.println("Resultado: No existe ningún empleado con ID " + idBusc + " en este departamento.");
                        }
                    }
                    break;

                case 4:
                    empresa.listarDepartamentos();
                    break;

                case 5:
                    System.out.println("Saliendo del sistema...");
                    break;

                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        } while (opcion != 5);

        scanner.close();
    }
}