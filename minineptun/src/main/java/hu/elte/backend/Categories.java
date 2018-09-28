package hu.elte.backend;

public enum Categories {
    COMPUTERSCIENCE ("Computer Science"),
    NATURALSCIENCE ("Natural Science"),
    PROGRAMMING ("Programming");

    public final String name;

    Categories(String name){
        this.name = name;
    }

}
