package main;

import ohtu.ohtuvarasto.Varasto;

public class Main {

    public static void main(String[] args) {
        Varasto varasto = new Varasto(0);
        System.out.println(varasto.getSaldo());
    }
}
