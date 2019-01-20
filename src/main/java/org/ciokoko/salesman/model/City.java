package org.ciokoko.salesman.model;

public class City {
    private double x;
    private double y;

    private City(CityBuilder builder) {
        this.x = builder.x;
        this.y = builder.y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public static class CityBuilder {
        private double x;
        private double y;

        public CityBuilder setX(Double x) {
            this.x = x;
            return this;
        }

        public CityBuilder setY(Double y) {
            this.y = y;
            return this;
        }


        public City build() {
            return new City(this);
        }
    }

}
