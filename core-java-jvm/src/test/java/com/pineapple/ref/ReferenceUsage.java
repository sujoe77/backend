package com.pineapple.ref;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

public class ReferenceUsage {
    public static void main(String args[]) {
        ReferenceQueue rq = new ReferenceQueue();
//        hold();
        //release();
        Reference wr = release2(rq);
        System.out.println("Getting the referent from the " + "weak reference returns " + wr.get());
        System.out.println("Polling the reference queue returns " + rq.poll());
        gcAndPrint(rq, wr);
    }

    public static void hold() {
        System.out.println("Example of incorrectly holding a strong " +
                "reference");
        //Create an object
        MyObject obj = new MyObject();
        System.out.println("object is " + obj);

        //Create a reference queue
        ReferenceQueue rq = new ReferenceQueue();

        //Create a weakReference to obj and associate our reference queue
        WeakReference wr = new WeakReference(obj, rq);

        System.out.println("The weak reference is " + wr);

        //Check to see if it's on the ref queue yet
        System.out.println("Polling the reference queue returns " +
                rq.poll());
        System.out.println("Getting the referent from the " +
                "weak reference returns " + wr.get());

        System.out.println("Calling GC");
        gcAndPrint(rq, wr);
    }

    public static Reference release2(ReferenceQueue rq) {
        System.out.println("");
        System.out.println("Example of correctly releasing a strong reference");
        //Create an object
        MyObject obj = new MyObject();
        System.out.println("object is " + obj);

        //Create a reference queue

        //Create a weakReference to obj and associate our reference queue
        WeakReference wr = new WeakReference(obj, rq);

        System.out.println("The weak reference is " + wr);

        //Check to see if it's on the ref queue yet
        System.out.println("Getting the referent from the " + "weak reference returns " + wr.get());
        System.out.println("Polling the reference queue returns " + rq.poll());

        return wr;
    }

    private static void gcAndPrint(ReferenceQueue rq, Reference wr) {
        System.out.println("calling gc!");
        System.gc();
        System.out.println("Getting the referent from the " + "weak reference returns " + wr.get());
        System.out.println("Polling the reference queue returns " + rq.poll());
    }

    public static void release() {
        System.out.println("");
        System.out.println("Example of correctly releasing a strong " +
                "reference");
        //Create an object
        MyObject obj = new MyObject();
        System.out.println("object is " + obj);

        //Create a reference queue
        ReferenceQueue rq = new ReferenceQueue();

        //Create a weakReference to obj and associate our reference queue
        WeakReference wr = new WeakReference(obj, rq);

        System.out.println("The weak reference is " + wr);

        //Check to see if it's on the ref queue yet
        System.out.println("Polling the reference queue returns " +
                rq.poll());
        System.out.println("Getting the referent from the " +
                "weak reference returns " + wr.get());

        System.out.println("Set the obj reference to null and call GC");
        obj = null;
        gcAndPrint(rq, wr);
    }
}
