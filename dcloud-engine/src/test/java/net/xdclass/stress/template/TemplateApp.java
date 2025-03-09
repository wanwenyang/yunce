package net.xdclass.stress.template;

/**
 *
 **/
public class TemplateApp {
    public static void main(String[] args) {
        AbstractClass a = new AClass();
        a.templateMethod();

        AbstractClass b = new BClass();
        b.templateMethod();
    }
}
