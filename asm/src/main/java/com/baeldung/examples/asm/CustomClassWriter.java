package com.baeldung.examples.asm;

import com.baeldung.examples.asm.visitor.AddFieldAdapter;
import com.baeldung.examples.asm.visitor.AddInterfaceAdapter;
import com.baeldung.examples.asm.visitor.PublicizeMethodAdapter;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.objectweb.asm.Opcodes.ACC_PUBLIC;

public class CustomClassWriter {
    public final static String CLASSNAME = "java.lang.Integer";
    public final static String CLONEABLE = "java/lang/Cloneable";

    ClassReader reader;
    ClassWriter writer;

    public static void main(String[] args) {
        new CustomClassWriter(CLASSNAME).publicizeMethod();
    }

    public CustomClassWriter(String classname) {
        try {
            reader = new ClassReader(classname);
            writer = new ClassWriter(reader, 0);
        } catch (IOException ex) {
            Logger.getLogger(CustomClassWriter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public CustomClassWriter(byte[] contents) {
        reader = new ClassReader(contents);
        writer = new ClassWriter(reader, 0);
    }

    public byte[] addField() {
        return apply(new AddFieldAdapter("aNewBooleanField", ACC_PUBLIC, writer));
    }

    public byte[] publicizeMethod() {
        return apply(new PublicizeMethodAdapter(writer));
    }

    public byte[] addInterface() {
        return apply(new AddInterfaceAdapter(writer));
    }

    private byte[] apply(ClassVisitor visitor) {
        reader.accept(visitor, 0);
        return writer.toByteArray();
    }
}
