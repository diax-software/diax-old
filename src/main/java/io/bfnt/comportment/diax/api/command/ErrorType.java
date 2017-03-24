package io.bfnt.comportment.diax.api.command;

/**
 * Created by Comporment on 24/03/2017 at 19:42
 * Dev'ving like a sir since 1998. | https://github.com/Comportment
 */
public enum ErrorType
{
    SELF_NO_PERMISSION("I do not have the permission to do that."),
    NOT_ENOUGH_ARGS("You did not specify enough args for this command."),
    NO_PERMISSION("You do not have permission to do that."),
    NOT_IN_GUILD("This command can not be used in a direct message."),
    USER_NOT_FOUND("That user could not be found.");

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