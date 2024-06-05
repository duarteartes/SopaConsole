// Definimos el paquete al que pertenece la clase
package Model;

// Importamos las clases ArrayList, List y Random de java.util
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// Declaramos la clase
public class SopaDeLetras {
// Declaramos un array 2D para representar el tablero
    private char[][] puzzle;
// Declaramos un arrayList que representa una lista de palabras
    private List<Palabra> palabras;
// Declaramos una variable para llevar el conteo de las palabras que ha acertado el usuario
    private int palabrasEncontradas;

// Creamos este método getter para acceder al array de chars desde otra clase fuera del modelo de datos
    public char[][] getPuzzle() {
        return puzzle;
    }

// Creamos este método getter para acceder al array de chars desde otra clase fuera del modelo de datos
    public List<Palabra> getPalabras() {
        return palabras;
    }

// Creamos el constructor del tablero
    public SopaDeLetras() {
        // Iniciamos un array 2D de 10x10
        puzzle = new char[10][10];
        // Iniciamos el arrayList donde se guardarán las palabras introducidas por el usuario
        palabras = new ArrayList<>();
        // Iniciamos el contador de las palabras que va encontrando a 0
        palabrasEncontradas = 0;
    }

// Creamos este método que toma como argumento el objeto palabra
    public void agregarPalabra(Palabra palabra) {
        // Convierte la palabra introducida a mayúsculas y la añade al objeto
        palabra = new Palabra(palabra.getPalabra().toUpperCase());
        Random random = new Random();
        // Obtenemos el tamaño del tablero
        int puzzleSize = puzzle.length;
        // Inicializamos la variable a false
        boolean colocada = false;

        // Creamos este bucle while para colocar las palabras
        while (!colocada) {
            // Utilizamos random para colocar la palabra en una dirección u otra
            // Dependiendo del número se pondrá en horizontal o vertical
            int direction = random.nextInt(2);

            // Llamamos al método colocarPalabra para ver si se puede colocar en el tablero
            // Si es así cambiamos la variable colocada a true
            if (direction == 0) {
                int row = random.nextInt(puzzleSize);
                int col = random.nextInt(puzzleSize - palabra.length() + 1);

                if (colocarPalabra(palabra, row, col, 0, 1)) {
                    palabraColocada(palabra, row, col, 0, 1);
                    colocada = true;
                }
            } else {
                int row = random.nextInt(puzzleSize - palabra.length() + 1);
                int col = random.nextInt(puzzleSize);

                if (colocarPalabra(palabra, row, col, 1, 0)) {
                    palabraColocada(palabra, row, col, 1, 0);
                    colocada = true;
                }
            }
        }

        // Añadimos esta palabra al arrayList palabras
        palabras.add(palabra);
    }

// Este método confirma que se pueda colocar una palabra en el tablero
    private boolean colocarPalabra(Palabra palabra, int row, int col, int rowIncrement, int colIncrement) {
        // Creamos un bucle for para recorrer los caracteres de la palabra
        for (int i = 0; i < palabra.length(); i++) {
            int newRow = row + i * rowIncrement;
            int newCol = col + i * colIncrement;

            // Comprobamos que la letra esté dentro del tablero, si no es así retornamos false
            if (newRow < 0 || newRow >= puzzle.length || newCol < 0 || newCol >= puzzle[newRow].length) {
                return false;
            }

            // Con esto obtenemos la letra de la palabra que vamos a colocar y la letra que tenemos en el tablero
            char letraActual = puzzle[newRow][newCol];
            char letra = palabra.getPalabra().charAt(i);

            // Si la letra que intentamos colocar no coincide con la letra de la palabra retornamos false
            if (letraActual != '\0' && letraActual != letra) {
                return false;
            }
        }
        // Si podemos colocar todas las letras correctamente retornamos true
        return true;
    }

// Con este método colocamos la palabra en el tablero si se puede
    private void palabraColocada(Palabra palabra, int row, int col, int rowIncrement, int colIncrement) {
        // utilizamos este condicional para verificar que la palabra se puede colocar
        if (colocarPalabra(palabra, row, col, rowIncrement, colIncrement)) {
            // Utilizamos un bucle for para recorrer cada letra de la palabra y colocarla en el tablero
            for (int i = 0; i < palabra.length(); i++) {
                int newRow = row + i * rowIncrement;
                int newCol = col + i * colIncrement;
                puzzle[newRow][newCol] = palabra.getPalabra().charAt(i);
            }
            // Actualizamos las coordenadas de la palabra en el tablero
            palabra.setIndexRowInit(row);
            palabra.setIndexColumnInit(col);
            palabra.setIndexRowEnd(row + (palabra.length() - 1) * rowIncrement);
            palabra.setIndexColumnEnd(col + (palabra.length() - 1) * colIncrement);
        }
    }

// Con este método verificamos que una palabra se pueda colocar en el tablero
    public boolean esPalabraValida(String palabra) {
        // Si la palabra tiene 10 letras o menos retorna true y sino retornará false
        return palabra.length() <= 10;
    }

// Con este método devolvemos el número de palabras para adivinar que contiene el arrayList
    public int numeroPalabras() {
        return palabras.size();
    }

// Con este método buscamos las palabras en el tablero
    public boolean buscarPalabra(String palabra) {
        // Convierte la palabra a mayúsculas para hacer la búsqueda
        palabra = palabra.toUpperCase();
        // Con un bucle for recorremos el arrayList
        for (Palabra p : palabras) {
            // Si encontramos una coincidencia incrementamos el contador de palabras encontradas y retornamos true
            if (!p.isDiscovered() && p.tryGuess(palabra)) {
                p.setDiscovered();
                palabrasEncontradas++;
                return true;
            }
        }
        // Si no encuentra coincidencia retornamos false
        return false;
    }

// COn este método comprobamos que el juego termina y el usuario ha ganado
    public boolean haTerminado() {
        //Si palabrasEncontradas es igual al tamaño del arrayList, retornamos true
        return palabrasEncontradas == palabras.size();
    }
}