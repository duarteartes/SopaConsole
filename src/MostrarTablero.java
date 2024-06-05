// En esta línea importamos las clases que utilizamos del paquete Model
import Model.Palabra;
import Model.SopaDeLetras;

// Importamos las clases List y Random de java.util
import java.util.List;
import java.util.Random;

// Declaramos la clase que utilizaremos para mostrar el tablero
public class MostrarTablero {

// Declaramos el array 2D para mostrar el tablero
    private char[][] puzzle;
// Declaramos el arrayList que representa la lista de palabras que ingresa el usuario
    private List<Palabra> palabras;
// Declaramos las constantes para dar los colores rojo y blanco a la sopa de letras
    public static final String TEXT_RED = "\u001B[31m";
    public static final String TEXT_RESET = "\u001B[0m";

// Declaramos el constructor cogiendo el objeto SopaDeLetras que tenemos en la clase SopaDeLetras
    public MostrarTablero(SopaDeLetras sopaDeLetras) {
        this.puzzle = sopaDeLetras.getPuzzle();
        this.palabras = sopaDeLetras.getPalabras();
    }

// Creamos el método para mostrar la sopa de letras en la consola
    public void mostrarSopaDeLetras() {
        // Creamos un array con las letras de alfabeto para colocarlas en el tablero de manera aleatoria posteriormente
        Random random = new Random();
        char[] alfabeto = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

        // Utilizamos el bucle for para recorrer las filas y columnas del array 2D
        for (int row = 0; row < puzzle.length; row++) {
            for (int col = 0; col < puzzle[0].length; col++) {
                // Obtenemos el caracter en la posición del tablero e iniciamos la variable palabraEncontrada a false
                char letra = puzzle[row][col];
                boolean palabraEncontrada = false;

                // En este bucle buscamos las palabras en el arrayList
                for (Palabra p : palabras) {
                    // Si encuentra la palabra se modifica la variable palabraEncontrada a true
                    if (p.isDiscovered() && p.contiene(row, col)) {
                        palabraEncontrada = true;
                        break;
                    }
                }

                // Entramos en esta condición si no se ha encontrado una palabra
                if (!palabraEncontrada) {
                    // Si una coordenada del tablero está vacía será null
                    if (letra == '\0') {
                        // Y se generará un índice aleatorio para colocar una letra aleatoria en la celda vacía
                        int indiceAleatorio = random.nextInt(alfabeto.length);
                        letra = alfabeto[indiceAleatorio];
                        puzzle[row][col] = letra;
                    // Si la celda no está vacía se convierte la letra que ha encontrado a mayúsculas
                    } else {
                        letra = Character.toUpperCase(letra);
                    }
                    System.out.print(letra + " ");
                // Si hemos encontrado una palabra se imprime en la consola en color rojo
                } else {
                    System.out.print(TEXT_RED + letra + TEXT_RESET + " ");
                }
            }
            System.out.println();
        }
    }
}
