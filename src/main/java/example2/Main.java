package example2;

public class Main {
    public static void main(String[] arg) {
        try {
            //Creamos un Cliente
            Cliente cliente = Cliente.builder()
                    .nombre("Juan")
                    .edad(30)
                    .saldo(12345.67)
                    .build();

            //Convertimos a Json
            String json = JsonUtils.toJson(cliente);
            System.out.println("Objeto serializado a Json: " + json);

            //Reconstruimos el mismo objeto desde el Json
            Cliente mismoCliente = JsonUtils.fromjJson(json, Cliente.class);
            System.out.println("Objeto deserializado desde Json: " + mismoCliente);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
