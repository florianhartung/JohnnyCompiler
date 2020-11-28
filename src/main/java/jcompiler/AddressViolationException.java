package jcompiler;

import java.io.IOException;

public class AddressViolationException extends IOException {
    public AddressViolationException() {
        super();
    }
    public AddressViolationException(String identifier) {
        super("Address not found: " + identifier);
    }
}
