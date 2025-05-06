package com.sunpulse.api.registro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/registros")
public class RegistroController {

    @Autowired
    private RegistroService registroService;

    @GetMapping
    public ResponseEntity<?> obtenerRegistros (){
        List<Registro> registros = registroService.obtenerRegistros();
        if (registros.isEmpty()){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No existe registro alguno");
        }
        return ResponseEntity.status(HttpStatus.OK).body(registros);
    }

    @GetMapping("/{temperatura}")
    public ResponseEntity<?> obtenerRegistrosTemp(@PathVariable Double temperatura) {
        try {
            List<Registro> registros = registroService.obtenerRegistrosTemp(temperatura);
            return registros.isEmpty() ?
                    ResponseEntity.status(HttpStatus.NOT_FOUND)
                            .body("No se encontraron registros con la temperatura: " + temperatura) :
                    ResponseEntity.ok(registros);
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().body("Temperatura inválida: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error interno: " + e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> crearRegistro (@RequestBody Registro registro){
        registroService.crearRegistro(registro.getTemperatura());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping ("/prueba")
    public ResponseEntity<?> obtenerRegistroTemp (@RequestParam Double temperatura){
        try {
            List<Registro> registros = registroService.obtenerRegistrosTemp(temperatura);
            return registros.isEmpty() ?
                    ResponseEntity.status(HttpStatus.NOT_FOUND)
                            .body("No se encontraron registros con la temperatura: " + temperatura) :
                    ResponseEntity.ok(registros);
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().body("Temperatura inválida: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error interno: " + e.getMessage());
        }
    }

}
