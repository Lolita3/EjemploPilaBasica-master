package cr.ac.ulead;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Random;

public class sistema {
    Queue colas = new Queue();

    public void de_txt_a_cola(String documento){

        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;
        int cont=0;


        try {
            // Apertura del fichero y creacion de BufferedReader para poder
            // hacer una lectura comoda (disponer del metodo readLine()).

            //Ruta donde se encuentra la carpeta
            String rutaProyecto = "C:\\Users\\lolit";
            //Ruta ya dentro del programa NO CAMBIAR
            String rutaInterna= "\\EjemploPilaBasica-master\\src\\cr\\ac\\ulead\\";
            //Nombre del TXT a leer

            archivo = new File (rutaProyecto+rutaInterna+documento+".txt");
            fr = new FileReader (archivo);
            br = new BufferedReader(fr);

            // Lectura del fichero
            String linea;
            while((linea=br.readLine())!=null){
                if(documento=="cola_especial"){
                    colas.esp_enqueue(Integer.parseInt(linea));
                    //System.out.println(colas.esp_dequeue());
                }
                if(documento=="cola_regular"){
                    colas.reg_enqueue(Integer.parseInt(linea));
                }
                //System.out.println(linea+"\t\t\t"+(cont+=Integer.parseInt(linea)));

            }


        }
        catch(Exception e){
            e.printStackTrace();
        }finally{
            // En el finally cerramos el fichero, para asegurarnos
            // que se cierra tanto si t'odo va bien como si salta
            // una excepcion.
            try{
                if( null != fr ){
                    fr.close();
                }
            }catch (Exception e2){
                e2.printStackTrace();
            }
        }
    }

    public void carga_de_colas(){
        de_txt_a_cola("cola_especial");
        de_txt_a_cola("cola_regular");
    }

    public void ejecucion_sistema(int cajas, int nuevas){
        //Queue cola = new Queue();
        int caja[] = new int[cajas];
        int entrada_minuto_reg[] = new int[480];
        int entrada_minuto_esp[] = new int[480];
        int acumPersona_esp = 0;
        int acumPersona_reg=0;
        int acumEspera=0;
        int acumTramites=0;
        int Total=0;
        int esperaencaja[] = new int[cajas];
        int tramite=0;
        int inc_esp=0;
        int inc_reg=0;
        int atendido=0;


        for (int i=0; i<cajas; i++){
            esperaencaja[i]=0;
        }
        for(int i=0; i<480; i++){
            System.out.println("\n\n############################      MINUTO #"+(i+1)+"       ##########################");
            entrada_minuto_reg[i]+=colas.reg_dequeue();
            entrada_minuto_esp[i]+=colas.esp_dequeue();
            acumPersona_esp+=entrada_minuto_esp[i];
//            System.out.println((i+1)+"\t\t\t"+entrada_minuto_esp[i]+"\t\t\t"+acumPersona_esp);
//            System.out.println((i+1)+"\t\t\t"+entrada_minuto_reg[i]+"\t\t\t"+acumPersona_reg);
            acumPersona_reg+=entrada_minuto_reg[i];
            System.out.println("##########En este minuto llegaron "+entrada_minuto_esp[i]+" personas a la cola especial y "+entrada_minuto_reg[i]+" personas a la cola regular");
            System.out.println("##### Hay "+acumPersona_esp+" personas en la cola especial y "+acumPersona_reg+" personas en la cola regular \n");
            for(int j=0;j<cajas;j++){
               // System.out.println("###############     "+acumPersona_esp+"\t"+acumPersona_reg+"       ##############");
                if(acumPersona_esp==0 && acumPersona_reg==0){
                    esperaencaja[j]=(i+1);
                }

                if(acumPersona_esp>0 && i==esperaencaja[j]){
                    tramite=num_random();
                    if(nuevas<j){
                        tramite=tramite+1;
                    }
                    atendido++;
                    esperaencaja[j]+=tramite;
                    acumTramites+=tramite;
                    System.out.println("Cliente #"+atendido+" atendido en la caja #"+(j+1)+" en el minuto "+(i+1)+" su tiempo de tramite fue "+tramite+" minutos, Persona con discapacidad");
                    System.out.println("La proxima persona puede ser atendida desde el minuto "+(esperaencaja[j]+1));
                    acumPersona_esp--;
                    while (entrada_minuto_esp[inc_esp]==0){
                        inc_esp++;
                    }
                    System.out.println("Esta persona llego en el minuto "+inc_esp+" espero "+(i-inc_esp)+" minutos, estando un tiempo total en el sistema de "+((i-inc_esp)+tramite)+" minutos \n");
                    acumEspera+=(i-inc_esp);
                    Total+=((i-inc_esp)+tramite);
                    entrada_minuto_esp[inc_esp]--;
                }else
                if(acumPersona_reg>0 && i==esperaencaja[j]){
                    tramite=num_random();
                    if(nuevas<j){
                        tramite++;
                    }
                    atendido++;
                    esperaencaja[j]+=tramite;
                    acumTramites+=tramite;
                    System.out.println("Cliente #"+atendido+" atendido en la caja #"+(j+1)+" en el minuto "+(i+1)+" su tiempo de tramite fue "+tramite+" minutos");
                    System.out.println("La proxima persona puede ser atendida desde el minuto "+(esperaencaja[j]+1));
                    acumPersona_reg--;

                    while (entrada_minuto_reg[inc_reg]==0){
                        inc_reg++;
                    }

                    //System.out.println("\t\t\t\t"+entrada_minuto_reg[inc_reg]+"\t\t"+(inc_reg+1));
                    System.out.println("Esta persona llego en el minuto "+inc_reg+" espero "+(i-inc_reg)+" minutos, estando un tiempo total en el sistema de "+((i-inc_reg)+tramite)+" minutos \n");
                    acumEspera+=(i-inc_reg);
                    Total+=((i-inc_reg)+tramite);
                    entrada_minuto_reg[inc_reg]--;

                }
            }
        }
        System.out.println("#############################################################################");
        System.out.println("##########################CONCLUSIONES#######################################");
        System.out.println("Se habilitaron "+cajas+" cajas donde "+nuevas+" eran nuevos en el puesto");
        System.out.println("Se atendieron "+atendido+" personas, no se pudieron atender "+(acumPersona_esp+acumPersona_reg)+" personas en total de las cuales "+acumPersona_esp+" eran de la cola especial y "+acumPersona_reg+" de la cola regular");
        System.out.println("El tiempo promedio de espera fue de "+(acumEspera/atendido)+" minutos");
        System.out.println("El tiempo promedio realizando el tramite fue de "+(acumTramites/atendido)+" minutos");
        System.out.println("El promedio de tiempo total en el que una persona se esta en el sistema es de "+(Total/atendido)+" minutos");
    }

    public int num_random(){
        Random r = new Random();
        int aleatorio = r.nextInt(101);
        int num=0;

        if(aleatorio>=0 && aleatorio<20){
            num=1;
        }else if(aleatorio>=20 && aleatorio<40){
            num=2;
        }else if(aleatorio>=40 && aleatorio<60){
            num=3;
        }else if(aleatorio>=60 && aleatorio<80){
            num=5;
        }else if(aleatorio>=80 && aleatorio<90){
            num=8;
        }else if(aleatorio>=90 && aleatorio<95){
            num=13;
        }else if(aleatorio>=95){
            num=(13 + (int)(13*Math.random()));
        }
        return num;
    }

}
