import java.util.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;      
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;


public class Methods {
    Scanner scan = new Scanner(System.in);
    Random random = new Random();
    Tanque[][] arrra = new Tanque[2][2];
    //Definimos contador. 
    public static int contador = 0;

    public void Main() {
        System.out.println("--------JUEGO DE MI TANQUE----------");
        
        Random r = new Random();
        //El número de tanques a crear (Entre 1 al 4) 
        int value = r.nextInt(4)+1; 
        int controlador = 0;
        
        //Inicio en la posición 0,0. 
        int vertical = 0, horizontal = 0;
        
    //El for esta hecho para crear los tanques. 
    //El tipo del tanque será hecho por el switch. (Entre el 1 al 2) 
    
        for (int j = 0; j < value; j++) {
            int value2 = random.nextInt(2)+1;

            switch (value2) {
                case 1:
                    Tanque bn = new NormalTanque(10);
                    arrra[horizontal][vertical] = bn;
                    break;

                case 2:
                    Tanque bl = new EspecialTanque(20);
                    arrra[horizontal][vertical] = bl;
                    break;
            }
            
            //Al finalizar la creación del primero.
            vertical++;
            //Va cambiar a la siguiente posición. 
            if (vertical > 1) {
                //Si es mayor a 1, entonces :
                vertical = 0;
                //Volvemos a cero. 
                horizontal++;
                //Ya no será cero, sino uno. 
            }
        }

        System.out.println("Menu de juego");
        boolean control = true;
        System.out.println("1. Disparar una bala eligiendo la posición sobre la cual disparar. (Cada bala quita 5 de sangre)");
        System.out.println("2. Activar bomba atómica. (La cual mata al tanque en una posición aleatoria automáticamente)");  
        System.out.println("3. Activar tanque mutante.");      
        System.out.println("4. La frase de la abuela.");    
        System.out.println("5. Número de disparos.");
        System.out.println("6. Leer cantidad de sangre derramada.");
        System.out.println("7. Guardar en archovo txt la cantidad de sangre de los tanques. ");
        
        
                
  
        while (true) {

            this.mostrarTablero();
            if (!control) {
               System.out.println("El juego de mi tanque a terminado");
                break;
            }
            System.out.println("Selecciona una opcion del menu de juego");
            int option = scan.nextInt();
            switch (option) {
                case 1:
                    this.dispararPosicion();
                    break;

                case 2:
                    this.bombaAtomica();
                    break;

                case 3:
                    this.tanqueMutante();
                    break;

                case 4:
                    System.out.println("Esa es la vida buena, mijito.");
                    break;

                case 5:
                    System.out.println("N° de disparos: "+ contador);
                    break;
                case 6:
                    int sangre = contador * 5;
                    System.out.println("Cantidad de sangre total derramada : "+sangre);
                    break;
                case 7:
                    this.guardarArchivo();
                    break;
                default:
                    System.out.print("Ingrese otro numero.");
                    break;
            }
            //Si todos tienen salud negativa o esten vacios. Cierra el programa.    
            for(int i = 0; i < arrra.length ; i++){
                for(int j = 0; j < arrra[i].length; j++){
                    if(arrra[i][j] != null && arrra[i][j].getSalud() < 0){
                        controlador++;
                        if(controlador == value){
                            control = false;
                        }

                    }
                }
            }
            controlador = 0;

        }

    } // Principal;

    public void mostrarTablero() {
        System.out.println("-------------------");
        for (int i = 0; i < arrra.length; i++) {
            for (int j = 0; j < arrra[i].length; j++) {
                if (arrra[i][j] == null) {
                    System.out.printf("|%5s", "");
                } else {
                    System.out.printf("|" + arrra[i][j].toString());
                }
            }
            System.out.print("|\n-------------------\n");
        }
    } // Tablero de juego.

    public void dispararPosicion() {
        while (true) {
            System.out.println("Selecciona la fila para disparar 0-2");
            int posi1;
            while (true) {
                int pos1 = scan.nextInt();
                if (pos1 >= 3) {
                    System.out.println("Selecciona una opcion valida");
                } else {
                    posi1 = pos1;
                    break;
                }

            }
            System.out.println("");
            System.out.println("Selecciona la columna para disparar 0-2");
            int posi2;
            while (true) {
                int pos2 = scan.nextInt();
                if (pos2 >= 3) {
                    System.out.println("Selecciona una opcion valida");
                } else {
                    posi2 = pos2;
                    break;
                }

            }
            System.out.println("");
            if (arrra[posi1][posi2] != null) {
                System.out.println("Puuuum hpta");
                arrra[posi1][posi2].setSalud(arrra[posi1][posi2].getSalud() - 5);
                System.out.println("Se ha disparado a " + arrra[posi1][posi2].toString());
                contador ++;
                break;
            } else {
                System.out.println("Selecciona un bicho existente");
            }

        }

    } // Disparar bicho.

    public void bombaAtomica() {
        while (true) {
            Random rand = new Random();
            int upperbound = 2;
            int value1 = rand.nextInt(upperbound); 
            int value2 = rand.nextInt(upperbound); 

            if (arrra[value1][value2] != null && arrra[value1][value2].getSalud() > 0) {
                arrra[value1][value2].setSalud(0);
                System.out.println("Se ha matado a un tanque en la posicion" + value1 + value2);
                break;
                
            }
        }
    } // Mata a un tanque

    public void tanqueMutante() {
        //Definimos como el menor (la salud) al primero, y así comparamos. 
        int menor = arrra[0][0].getSalud();
        int x = 0, y = 0;
        for (int i = 0; i < arrra.length; i++) {
            for (int j = 0; j < arrra[i].length; j++) {
                if (arrra[i][j] != null) {
                    if (arrra[i][j].getSalud() > 0) {
                        //Miramos la salud.
                        int numeroActual = arrra[i][j].getSalud();
                        //La comparamos, y si es menor a la que ya habiamos puesto menor,
                        //entonces será esa la menor. 
                        if (numeroActual < menor) {
                            menor = numeroActual;
                            x = i;
                            y = j;
                        }
                    }

                }
            }
        }
        arrra[x][y].setSalud(arrra[x][y].getSalud() * 2);

    } // Duplica vida
    
    public void guardarArchivo() {
        
        File archivo = new File("vale.txt");
        
        try {
            FileWriter escritor = new FileWriter(archivo);
            for (int i = 0; i < arrra.length; i++) {
                for (int j = 0; j < arrra[i].length; j++) {
                    Tanque tanque = arrra[i][j];
                    
                    if(tanque !=null){
                        escritor.write("La cantidad de sangre del tanque en la posición" +i+" "+j+ "es"+
                            tanque.getSalud());
                            
                            escritor.write("\n");
                    }
                }
            }
            
            System.out.println("El archivo se guardó en" + archivo.getAbsolutePath());
            escritor.close();
        }catch ( Exception e){
            System.out.println("No se pudo escribir el archivo.");
        }
        
    }
    
}



