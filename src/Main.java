// En esta línea importamos las clases que utilizamos del paquete Model
import Model.Palabra;
import Model.SopaDeLetras;

// Importamos la clase Scanner de java.util para poder interactuar con el usuario desde consola
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Utilizamos esta línea para poder interactuar con el usuario
        Scanner scanner = new Scanner(System.in);
        // Creamos una instancia de la clase SopaDeLetras para gestionar la lógica del juego
        SopaDeLetras sopaDeLetras = new SopaDeLetras();
        // Creamos una instancia de la clase MostrarTablero para mostrar el tablero de la sopa de letras
        MostrarTablero mostrarTablero = new MostrarTablero(sopaDeLetras);

        // Damos la bienvenida al juego y explicamos brevemente cómo funciona
        System.out.println("-------------------------------------");
        System.out.println("Bienvenido al juego de Sopa de Letras");
        System.out.println("-------------------------------------\n");
        System.out.println("Introduce las palabras que quieres que aparezcan en la Sopa de Letras:");
        System.out.println("-------------------------------------");
        System.out.println("Escribe "+"fin"+" cuando hayas terminado de introducir las palabras");
        System.out.println("No puedes introducir palabras de más de 10 letras\n");
        // Declaramos la variable palabra de tipo String
        String palabra;

        // Creamos un bucle while para pedir palabras al usuario
        while (true) {
            // Almacenamos la palabra que introduce el usuario en la variable hasta que introduzca "fin"
            palabra = scanner.nextLine();
            if (palabra.equalsIgnoreCase("fin")) {
                break;
            }

            // Verificamos si la palabra tiene 10 letras o menos
            if (!sopaDeLetras.esPalabraValida(palabra)) {
                // Si tiene más de 10 letras mostramos un mensaje de error
                System.out.println("ERROR: La palabra tiene más de 10 letras. No se agregará a la sopa de letras.");
            } else {
                // Si no tiene más de 10 letras creamos una instancia de Palabra
                Palabra nuevaPalabra = new Palabra(palabra);
                sopaDeLetras.agregarPalabra(nuevaPalabra);
            }
        }

        // Imprimimos la cantidad de palabras que tiene que buscar el usuario e imprimimos el tablero
        System.out.println("Tienes que buscar " +sopaDeLetras.numeroPalabras()+ " palabras\n");
        mostrarTablero.mostrarSopaDeLetras();

        // Creamos un bucle while que ejecutamos hasta que el juego se termine
        while (!sopaDeLetras.haTerminado()) {
            System.out.println("-------------------------------------");
            System.out.println("Introduce una palabra para ver si está en la sopa:");
            // Solicitamos al usuario que introduzca una palabra
            String palabraAdivinar = scanner.nextLine();
            // Si la palabra se encuentra en el tablero mostramos mensajes de felicitación
            if (sopaDeLetras.buscarPalabra(palabraAdivinar)) {
                System.out.println("¡Bien!");
                System.out.println("Has encontrado una palabra\n");
            // Si la palabra no se encuentra en la sopa avisamos al usuario con un mensaje
            } else {
                System.out.println("¡Oh no! Esta palabra no está en la sopa de letras.");
            }
            // Mostramos el tablero después de cada intento
            mostrarTablero.mostrarSopaDeLetras();
        }

        // Cuando el jugador ha encontrado todas las palabras mostramos un mensaje de enhorabuena y fin del juego
        System.out.println("-------------------------------------");
        System.out.println("¡ENHORABUENA! HAS GANADO");
        System.out.println("Has encontrado todas las palabras");
        System.out.println("-------------------------------------");
    }
}
