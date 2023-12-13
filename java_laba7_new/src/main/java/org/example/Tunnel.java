package org.example;

class Tunnel extends Stage {
    private static final int MAX_CARS_IN_TUNNEL = 4;
    private static volatile int carsInTunnel = 0;

    public Tunnel() {
        this.length = 80;
        this.description = "Тоннель " + length + " метров";
    }

    @Override
    public void go(Car c) {
        try {
            System.out.println(c.getName() + " готовится к этапу(ждет): " + description);
            synchronized (Main.monitor) {
                if (carsInTunnel < MAX_CARS_IN_TUNNEL) {
                    carsInTunnel++;
                } else {
                    Main.monitor.wait();
                }
            }
            System.out.println(c.getName() + " начал этап: " + description);
            Thread.sleep(length / c.getSpeed() * 1000);
            System.out.println(c.getName() + " закончил этап: " + description);
            synchronized (Main.monitor) {
                carsInTunnel--;
                if (carsInTunnel == 0) {
                    Main.monitor.notifyAll();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}