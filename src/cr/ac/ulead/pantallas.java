package cr.ac.ulead;
import javax.swing.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class pantallas {

    static int cajas;
    static int nueva;



    public static int getCajas() {
        return cajas;
    }

    public static void setCajas(int cajas) {
        pantallas.cajas = cajas;
    }

    public static int getNueva() {
        return nueva;
    }

    public static void setNueva(int nueva) {
        pantallas.nueva = nueva;
    }

    public int validar(String texto){
        Scanner scan = new Scanner(System.in);
        String prueba= " ";
        boolean verificar=false;
        do{
            do{
                System.out.println(texto);
                prueba=scan.next();
                Scanner scanprueba = new Scanner(prueba);
                verificar=scanprueba.hasNextInt();
                if(!verificar)
                    JOptionPane.showMessageDialog(null, "Solo se aceptan Numeros", "Fatal ERROR!", JOptionPane.WARNING_MESSAGE);

            }while(!verificar);
            if(Integer.parseInt(prueba)<0 || Integer.parseInt(prueba)>30){
                JOptionPane.showMessageDialog(null, "Numeros negativos o Excedente a nuestra Capacidad", "Fatal ERROR!", JOptionPane.WARNING_MESSAGE);
                verificar=false;
            }
        }while (!verificar);
        return Integer.parseInt(prueba);
    }


    public void menu(){
        Scanner scan = new Scanner(System.in);
        int min = 3;

        do{
            System.out.println("Sistema de Colas");
            do{
                setCajas(validar("Ingresa el Numero de Cajas que estaran a servicio: "));
                if(getCajas()<min)
                    JOptionPane.showMessageDialog(null, "Error el minimo de Cajas que se pueden abrir son 3", "Fatal ERROR!", JOptionPane.WARNING_MESSAGE);
            }while(getCajas()<min);

            do{
                setNueva(validar("Ingrese la cantidad de Cajeros primerizos: "));
                if(getNueva()>getCajas())
                    JOptionPane.showMessageDialog(null, "Error logico! Ingrese una cantidad menor", "Fatal ERROR!", JOptionPane.WARNING_MESSAGE);
            }while(getNueva()>getCajas());

        }while(false);


    }
}


