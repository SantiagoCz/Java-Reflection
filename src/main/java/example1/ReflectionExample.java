package example1;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectionExample {
    private String name;
    private int age;

    public ReflectionExample() {
        this.name = "Default Name";
        this.age = 25;
    }

    public void printInfo() {
        System.out.println("Name: " + name + ", Age: " + age);
    }

    public static void main(String[] args) throws Exception {
        // Obtener la clase
        Class<?> clazz = ReflectionExample.class;

        // Inspeccionar métodos
        System.out.println("Métodos de la clase:");
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            System.out.println("- " + method.getName());
        }

        // Inspeccionar campos
        System.out.println("\nCampos de la clase:");
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            System.out.println("- " + field.getName());
        }

        // Crear una instancia dinámica
        ReflectionExample instance = (ReflectionExample) clazz.getDeclaredConstructor().newInstance();

        // Modificar un campo privado
        Field nameField = clazz.getDeclaredField("name");
        nameField.setAccessible(true); // Hacer accesible el campo privado
        nameField.set(instance, "Nuevo Nombre");

        // Invocar un método dinámicamente
        Method printMethod = clazz.getDeclaredMethod("printInfo");
        printMethod.invoke(instance); // Llama a printInfo()
    }
}