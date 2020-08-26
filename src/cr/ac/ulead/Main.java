package cr.ac.ulead;

public class Main {


    public static void main(String[] args) throws Exception {
        pantallas p= new pantallas();
        sistema s = new sistema();
        int op;

        p.menu();
        s.carga_de_colas();
        s.ejecucion_sistema(p.getCajas(), p.getNueva());
    }
}
