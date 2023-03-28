package ssvv.gogiacobgrebla.repository;

import ssvv.gogiacobgrebla.domain.Tema;
import ssvv.gogiacobgrebla.validation.*;

public class TemaRepository extends AbstractCRUDRepository<String, Tema> {
    public TemaRepository(Validator<Tema> validator) {
        super(validator);
    }
}

