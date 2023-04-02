package pe.edu.cibertec.moviles1.sesion2.models;

public class Country {

    private Name name;

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Country{" +
                "name=" + name +
                '}';
    }

    public static class Name {

        private String common;
        private String official;

        public String getCommon() {
            return common;
        }

        public void setCommon(String common) {
            this.common = common;
        }

        public String getOfficial() {
            return official;
        }

        public void setOfficial(String official) {
            this.official = official;
        }

        @Override
        public String toString() {
            return "Name{" +
                    "common='" + common + '\'' +
                    ", official='" + official + '\'' +
                    '}';
        }
    }

}
