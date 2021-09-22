import model.GoodGidObject;

import java.lang.reflect.Field;

public class FieldCheck {

    private static void checkGoodGidObject(GoodGidObject goodGidObject) throws IllegalAccessException {

        // Reflection으로 Object Field에 접근한다.
        Field[] declaredFields = goodGidObject.getClass().getDeclaredFields();

        for (Field field : declaredFields) {
            Object value = null;

            // private Field일 경우 접근을 허용한다.
            field.setAccessible(true);
            value = field.get(goodGidObject);
            System.out.println("value: " + value);
        }
    }

    public static void main(String[] args) throws IllegalAccessException {
        System.out.println((char[]) null);
        GoodGidObject obj = new GoodGidObject();
        checkGoodGidObject(obj);
    }


}
