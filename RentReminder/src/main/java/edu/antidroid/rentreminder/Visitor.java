package edu.antidroid.rentreminder;

/**
 * Simple visitor interface.
 */
public interface Visitor<TArgument> {

    void visit(TArgument argument);
}
