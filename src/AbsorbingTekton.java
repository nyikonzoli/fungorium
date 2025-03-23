public class AbsorbingTekton extends Tekton {
    public AbsorbingTekton() {
        System.out.println("AbsorbingTekton.AbsorbingTekton()");
    }
    @Override
    public void onRoundStart(){
        System.out.println("AbsorbingTekton.onRoundStart()");
    }
}
