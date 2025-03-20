public class InsectMaster extends Player {
    private Insect insect;

    public InsectMaster(){}

    public Insect getInsect(){
        return insect;
    }

    public void setInsect(Insect newInsect){
        insect = newInsect;
    }

    public void initiateSporeEating(Tekton t){}

    public void initiateMovement(Tekton t){}

    public void initiateMyceliumCutting(Mycelium m){}

    public void onRoundStart(){}

    public void selfReport(){}
}
