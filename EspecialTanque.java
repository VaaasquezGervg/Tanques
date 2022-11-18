public class EspecialTanque extends Tanque{
    public EspecialTanque(int salud) {
        super(salud);
    }
    @Override
    public String toString(){
        if(getSalud() > 0){
            return "ET-"+getSalud();
        }
        else{
            return "  X  ";
        }
    }
}
