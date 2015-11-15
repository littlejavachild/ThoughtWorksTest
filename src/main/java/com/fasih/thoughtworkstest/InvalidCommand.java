package com.fasih.thoughtworkstest;

public class InvalidCommand implements Command<String> {

    @Override
    public String execute() {
        return "I have no idea what you are talking about";
    }

}
