package dominio.ej;

public class Vehiculo {

        protected int velocidad;
        protected int aceleracion;
        protected int anguloDeGiro;
        protected String patente;
        protected double peso;
        protected int ruedas;

        public int getVelocidad() {
                return velocidad;
        }

        public int getAceleracion() {
                return aceleracion;
        }

        public int getAnguloDeGiro() {
                return anguloDeGiro;
        }

        public String getPatente() {
                return patente;
        }

        public double getPeso() {
                return peso;
        }

        public int getRuedas() {
                return ruedas;
        }


        public boolean equals(Object ob2){
            return this.getPatente() == ((Vehiculo)ob2).getPatente();
        }

        public String toString(){
                return getPatente();
        }
}
