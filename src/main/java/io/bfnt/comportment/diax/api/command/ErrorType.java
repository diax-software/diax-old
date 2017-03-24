package io.bfnt.comportment.diax.api.command;

/**
 * Created by Comporment on 24/03/2017 at 19:42
 * Dev'ving like a sir since 1998. | https://github.com/Comportment
 */
public enum ErrorType
{
    NO_PERMISSION("I do not have the permission to do that.");

    String description;

    ErrorType(String description)
    {
        this.description = description;
    }
    public String getDescription()
    {
        return description;
    }
}