package com.rover12421.crack.mybatis.plugin;

import com.rover12421.crack.mybatis.plugin.asm.ClassReader;
import com.rover12421.crack.mybatis.plugin.asm.ClassWriter;
import com.rover12421.crack.mybatis.plugin.asm.tree.*;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.lang.reflect.Modifier;
import java.security.ProtectionDomain;
import java.util.List;

import static com.rover12421.crack.mybatis.plugin.asm.Opcodes.*;

/**
 * Created by rover12421 on 11/9/15.
 */
public class Agent {
    public static void premain(String args, Instrumentation inst) {
        System.out.println(Util.getVersionInfo());
        inst.addTransformer(new MethodEntryTransformer());
    }

    static class MethodEntryTransformer implements ClassFileTransformer {
        public byte[] transform(ClassLoader loader, String className,
                                Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer)
                throws IllegalClassFormatException {
            try {
                if (className!= null && className.equals("com/seventh7/mybatis/util/JavaUtils")) {
                    ClassReader cr = new ClassReader(classfileBuffer);
                    ClassNode cn = new ClassNode();
                    cr.accept(cn, 0);

                    ClassWriter cw = new ClassWriter(cr, ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);

                    List<MethodNode> methodNodes = cn.methods;
                    for (MethodNode methodNode : methodNodes) {
                        if (Modifier.isPublic(methodNode.access)
                                && methodNode.name.equals("refValid")
                                && methodNode.desc.startsWith("()Z")) {

                            InsnList insns = new InsnList();
                            insns.add(new InsnNode(ICONST_1));
                            insns.add(new InsnNode(IRETURN));

                            methodNode.instructions.insert(insns);
                            methodNode.visitEnd();

                            System.out.println("Modify : " + className + " > " + methodNode.name + " > " + methodNode.desc);
                            cn.accept(cw);
                            return cw.toByteArray();
                        }
                    }

                }
            } catch (Throwable e) {
//                e.printStackTrace();
            }

            try {
                if (className!= null && className.equals("com/seventh7/mybatis/service/JavaService")) {
                    ClassReader cr = new ClassReader(classfileBuffer);
                    ClassNode cn = new ClassNode();
                    cr.accept(cn, 0);

                    ClassWriter cw = new ClassWriter(cr, ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);

                    List<MethodNode> methodNodes = cn.methods;
                    for (MethodNode methodNode : methodNodes) {
                        if (Modifier.isPublic(methodNode.access)
                                && methodNode.name.equals("stop")
                                && methodNode.desc.startsWith("()V")) {

                            InsnList insns = new InsnList();
                            insns.add(new InsnNode(RETURN));

                            methodNode.instructions.insert(insns);
                            methodNode.visitEnd();

                            System.out.println("Modify : " + className + " > " + methodNode.name + " > " + methodNode.desc);
                            cn.accept(cw);
                            return cw.toByteArray();
                        }
                    }

                }
            } catch (Throwable e) {
//                e.printStackTrace();
            }

            try {
                if (className!= null && className.equals("com/seventh7/mybatis/dom/model/Completion")) {
                    ClassReader cr = new ClassReader(classfileBuffer);
                    ClassNode cn = new ClassNode();
                    cr.accept(cn, 0);

                    ClassWriter cw = new ClassWriter(cr, ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);

                    List<MethodNode> methodNodes = cn.methods;
                    for (MethodNode methodNode : methodNodes) {
                        if (Modifier.isPublic(methodNode.access)
                                && methodNode.desc.startsWith("()V")) {

                            if(methodNode.name.equals("run")) {
                                InsnList insns = new InsnList();
                                insns.add(new InsnNode(RETURN));

                                methodNode.instructions.insert(insns);
                                methodNode.visitEnd();

                                System.out.println("Modify : " + className + " > " + methodNode.name + " > " + methodNode.desc);
                            } else if (methodNode.name.equals("<init>")) {
                                InsnList insns = new InsnList();
                                insns.add(new VarInsnNode(ALOAD, 0));
                                insns.add(new MethodInsnNode(INVOKESPECIAL, "java/lang/Thread", "<init>", "()V", false));
                                insns.add(new InsnNode(RETURN));

                                methodNode.instructions.insert(insns);
                                methodNode.visitEnd();

                                System.out.println("Modify : " + className + " > " + methodNode.name + " > " + methodNode.desc);
                            }


                        }
                    }

                    cn.accept(cw);
                    return cw.toByteArray();

                }
            } catch (Throwable e) {
//                e.printStackTrace();
            }
            return classfileBuffer;
        }
    }
}
