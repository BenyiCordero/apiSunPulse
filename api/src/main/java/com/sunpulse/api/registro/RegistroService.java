package com.sunpulse.api.registro;

import java.util.List;
import java.util.Optional;

public interface RegistroService {

    Registro crearRegistro(Double temperatura);
    List<Registro> obtenerRegistrosTemp(Double temperatura);
    List<Registro> obtenerRegistros();

}
