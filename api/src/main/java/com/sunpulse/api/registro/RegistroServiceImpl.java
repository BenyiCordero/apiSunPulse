package com.sunpulse.api.registro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class RegistroServiceImpl implements RegistroService{

    @Autowired
    private RegistroRepository registroRepository;

    @Override
    public Registro crearRegistro(Double temperatura) {
        Registro registro = new Registro();
        registro.setFecha(LocalDateTime.now());
        registro.setTemperatura(temperatura);
        registroRepository.save(registro);
        return registro;
    }

    @Override
    public List<Registro> obtenerRegistrosTemp(Double temperatura) {
        try {
            List<Registro> registros = obtenerRegistros(); // Si esto falla, se captura la excepción
            List<Registro> registroList = new ArrayList<>();
            final double EPSILON = 0.001;
            for (Registro r : registros) {
                if (r.getTemperatura() != null && Math.abs(r.getTemperatura() - temperatura) < EPSILON) {
                    registroList.add(r);
                }
            }
            return registroList;
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener registros: " + e.getMessage(), e);
        }
    }
    @Override
    public List<Registro> obtenerRegistros() {
        List<Registro> registros = registroRepository.findAll();
        return registros;
    }

}
