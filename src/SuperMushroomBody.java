public class SuperMushroomBody extends  MushroomBody {

    public SuperMushroomBody() {
        super();
        System.out.println("SuperMushroomBody.SuperMushroomBody()");
    }

    @Override
    public void  spreadSpore(Tekton t){
        System.out.println("SuperMushroombody.spreadSpore(Tekton t)");
    }
}
