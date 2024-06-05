// En esta línea definimos el paquete al que pertenece la clase
package Model;

// Declaramos la clase palabra (hija), que extiende de la clase PuzzleItem (padre)
public class Palabra extends PuzzleItem {
// Declaramos la variable de tipo String, donde almacenaremos la palabra introducida por el usuario
    private String palabra;
// Declaramos el constructor de la clase, introducimos una cadena String y la almacenamos en la variable
    public Palabra(String palabra) {
        this.palabra = palabra;
    }

// Método para obtener el valor de la palabra. Return devuelve la palabra que tenemos almacenada en la variable
    public String getPalabra() {
        return palabra;
    }

    @Override
// Este método devuelve la longitud de la palabra almacenada en la variable
    public int length() {
        return palabra.length();
    }

    @Override
// Este método sirve para devolver un array de enteros para buscar un objeto
    public int[] coordsOfMatch(Object o) {
        // Comprobamos que el objeto pertenece a la clase Character
        if (o instanceof Character) {
            // Si el objeto es carácter lo convierte a char
            char targetChar = (Character) o;
            //Creamos un array de 2 posiciones
            int[] coordinadas = new int[2];
            // Creamos un bucle anidado para recorrer las filas y las columnas
            for (int row = getIndexRowInit(); row <= getIndexRowEnd(); row++) {
                for (int col = getIndexColumnInit(); col <= getIndexColumnEnd(); col++) {
                    // Si se encuentra una coincidencia se actualiza el array y lo devuelve
                    if (palabra.charAt(row - getIndexRowInit()) == targetChar) {
                        coordinadas[0] = row;
                        coordinadas[1] = col;
                        return coordinadas;
                    }
                }
            }
        }
        // Si no se encuentra una coincidencia se devuelve el siguiente array para avisarlo
        return new int[]{-1, -1};
    }

    @Override
// Con este método comprobamos si la palabra está en el tablero
    public boolean tryGuess(Object itemToGuess) {
        // Con esta sentencia comprobamos que el objeto sea una instancia de la clase String
        if (itemToGuess instanceof String) {
            //Si el objeto es un texto lo convierte a un tipo de dato String
            String targetWord = (String) itemToGuess;
            // Compara la palabra con "targetWord" un boleano true o false dependiendo del resultado.
            return palabra.equalsIgnoreCase(targetWord);
        }
        return false;
    }

// Este método compruebe si una palabra se solapa o cruza con otra
    public boolean contiene(int row, int col) {
        return row >= getIndexRowInit() && row <= getIndexRowEnd() && col >= getIndexColumnInit() && col <= getIndexColumnEnd();
    }
}
