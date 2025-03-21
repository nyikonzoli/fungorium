public class SuperMushroomBody extends  MushroomBody {

    public SuperMushroomBody() {
        super();
        System.out.println("SuperMushroombody.ctor()");
    }

    @Override
    public void  spreadSpore(Tekton t){
        System.out.println("SuperMushroombody.spreadSpore(Tekton t)");
    }
}
