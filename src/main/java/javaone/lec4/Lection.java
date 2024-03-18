package javaone.lec4;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Lection {

    // Fatal > Error > Warn > Info > Debug> Trace

    public static void main(String[] args) {

        Logger logger = LogManager.getLogger(Lection.class);

        Season current = Season.SPRING;
        System.out.println(current);
        System.out.println(current.ordinal());
        current.setRuText("Spring");
        System.out.println(current);

        Season[] seasons = Season.values();
        System.out.println(current.ordinal());

        logger.trace("trace");
        logger.debug("debug");
        logger.info("info");
        logger.warn("warn");
        logger.error("error");
        logger.fatal("fatal");
    }

    private enum Season {
        WINTER ("Зима"),
        SPRING ("Весна"),
        SUMMER ("Лето"),
        AUTUMN ("Осень");

        private String ruText;

        Season(String ruText) {
            this.ruText = ruText;
        }

        @Override
        public String toString() {
            return "Season{" + this.name() +
                    " (" + ruText + ")}";
        }

        public String getRuText() {
            return ruText;
        }

        public void setRuText(String ruText) {
            this.ruText = ruText;
        }
    }
}
