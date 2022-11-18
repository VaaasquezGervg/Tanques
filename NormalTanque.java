public class NormalTanque extends Tanque {
    public NormalTanque (int salud) {
        super(salud);
    }
    @Override
    public String toString(){
        if(getSalud() > 0){
            return "TN-"+getSalud();
        }
        else{
            return "  X  ";
        }
    }
}
