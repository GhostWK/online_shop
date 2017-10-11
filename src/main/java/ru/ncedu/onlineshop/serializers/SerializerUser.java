package ru.ncedu.onlineshop.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import ru.ncedu.onlineshop.entities.EntityUser;

import java.io.IOException;

public class SerializerUser extends StdSerializer<EntityUser>{

    protected SerializerUser(Class<EntityUser> t) {
        super(t);
    }

    protected SerializerUser() {
        this(null);
    }

    @Override
    public void serialize(EntityUser entityUser, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeObject(entityUser.getLogin());
    }
}
