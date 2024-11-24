package example2;

import java.lang.reflect.Field;

public class JsonUtils {

    //Serialización: Convertir un objeto a JSON
    public static String toJson(Object object) throws IllegalAccessException {
        if(object == null) {
            return null;
        }

        StringBuilder json = new StringBuilder("{");
        Field[] fields = object.getClass().getDeclaredFields();

        for(Field field : fields) {
            field.setAccessible(true); //Permite acceder a campos privados
            String name = field.getName();
            Object value = field.get(object);

            json.append("\"").append(name).append("\":");
            if (value instanceof String) {
                json.append("\"").append(value).append("\"");
            } else {
                json.append(value);
            }

            json.append(",");
        }

        //Eliminar la última coma
        if(fields.length > 0 ) {
            json.setLength(json.length() - 1);
        }

        json.append("}");
        return json.toString();
    }



    //Deserialización: Convertir JSON a un objeto Java
    public static <T> T fromjJson(String json, Class<T> tClass) throws Exception {
        if (json == null || json.equals("null")) {
            return null;
        }

        T object = tClass.getDeclaredConstructor().newInstance();
        String jsonBody = json.substring(1, json.length() -1); //Quitar llaves {}

        String[] keyValues = jsonBody.split(",");

        for (String keyValue : keyValues) {
            String[] pair = keyValue.split(":");
            String fieldName = pair[0].replace("\"", "").trim();
            String fieldValue = pair[1].replace("\"", "").trim();
            
            Field field = tClass.getDeclaredField(fieldName);
            field.setAccessible(true);
            
            //Convertir el valor en el tipo adecuado
            if (field.getType().equals(String.class)) {
                field.set(object, fieldValue);
            } else if (field.getType().equals(int.class) || field.getType().equals(Integer.class)) {
                field.set(object, Integer.parseInt(fieldValue));
            } else if (field.getType().equals(double.class) || field.getType().equals(Double.class)) {
                field.set(object, Double.parseDouble(fieldValue));
            }

        }

        return object;
    }
}
