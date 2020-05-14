package com.ab.multithread.visibility;

/**
 * @author Arpit Bhardwaj
 *
 * The volatile keyword does not cache the value of the variable and always read the variable from the main memory.
 * Any write to volatile variable in Java establishes a happen before the relationship with successive reads of that same variable.
 * The volatile keyword can be used either with primitive type or objects.
 * If you declared variable as volatile, Read and Writes are atomic
 *
 * NutShell: It is used to inform the compiler that multiple threads will access a particular statement.
 * It prevents the compiler from doing any reordering or any optimization (caching the vaiable in thread stack frame).
 */
public class VolatileDemo {
}
