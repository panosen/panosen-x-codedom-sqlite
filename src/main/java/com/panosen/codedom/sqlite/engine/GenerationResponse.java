package com.panosen.codedom.sqlite.engine;

public class GenerationResponse {

    private String sql;

    private Object[] args;

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public Object[] getArgs() {
        return args;
    }

    public void setArgs(Object[] args) {
        this.args = args;
    }

    public String[] getArgsAsStrings() {
        if (args == null) {
            return null;
        }

        String[] items = new String[args.length];
        for (int index = 0, length = args.length; index < length; index++) {
            if (args[index] != null) {
                items[index] = args[index].toString();
            }
        }

        return items;
    }
}
