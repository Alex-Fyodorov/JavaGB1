FROM bellsoft/liberica-openjdk-alpine:11.0.16.1-1
COPY ./java ./java
RUN mkdir ./out
RUN javac -sourcepath ./java -d out java/hw/hw101/main/Main.java -encoding UTF-8
CMD java -classpath ./out hw.hw101.main.Main 221 222 223 224
