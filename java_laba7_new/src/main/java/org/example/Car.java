package org.example;

class Car implements Runnable {
    private static int CARS_COUNT;
    private Race race;
    private int speed;
    private String name;

    private final String winMessage = "Победитель!";

    static {
        CARS_COUNT = 0;
    }

    public String getName() {
        return name;
    }

    public int getSpeed() {
        return speed;
    }

    public Car(Race race, int speed) {
        this.race = race;
        this.speed = speed;
        synchronized (Main.class) {
            CARS_COUNT++;
            this.name = "Участник #" + CARS_COUNT;
        }
    }

    @Override
    public void run() {
        try {
            System.out.println(this.name + " готовится");
            Thread.sleep(500 + (int) (Math.random() * 800));
            System.out.println(this.name + " готов");
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (int i = 0; i < race.getStages().size(); i++) {
            race.getStages().get(i).go(this);
        }
        synchronized (Main.monitor) {
            Main.finishCounter++;
            if (Main.finishCounter < 4) {
                System.out.println(this.name + " - " + winMessage);
            }
        }
    }
}
