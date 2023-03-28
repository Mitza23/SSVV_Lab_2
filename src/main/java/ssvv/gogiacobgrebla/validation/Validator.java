package ssvv.gogiacobgrebla.validation;

public interface Validator<E> {
    void validate(E entity) throws ValidationException;
}