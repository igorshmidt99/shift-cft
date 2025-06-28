package org.cft;

public enum Flag {
    O("-o"),
    P("-p"),
    A("-a"),
    F("-f"),
    S("-s");

    private final String flag;

    Flag(String flag) {
        this.flag = flag;

    }

    public String getFlag() {
        return flag;
    }
}
