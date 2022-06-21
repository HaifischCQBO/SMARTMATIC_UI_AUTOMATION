package Helpers.otp;
public interface HOTPGenerator {
    String generate(long counter) throws IllegalArgumentException;
}
