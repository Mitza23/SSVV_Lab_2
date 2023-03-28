package ssvv.gogiacobgrebla.repository;

import ssvv.gogiacobgrebla.domain.*;
import ssvv.gogiacobgrebla.validation.*;

public class NotaRepository extends AbstractCRUDRepository<Pair<String, String>, Nota> {
    public NotaRepository(Validator<Nota> validator) {
        super(validator);
    }
}
