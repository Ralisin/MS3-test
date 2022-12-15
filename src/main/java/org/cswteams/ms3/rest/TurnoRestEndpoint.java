package org.cswteams.ms3.rest;

import org.cswteams.ms3.control.assegnazioneTurni.IControllerAssegnazioneTurni;
import org.cswteams.ms3.dto.AssegnazioneTurnoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/turni/")
public class TurnoRestEndpoint {

    @Autowired
    private IControllerAssegnazioneTurni controllerAssegnazioneTurni;

    @RequestMapping(method = RequestMethod.POST, path = "")
    public ResponseEntity<?> creaTurno(@RequestBody(required = true) AssegnazioneTurnoDTO c) {
        if (c != null) {
            return new ResponseEntity<>(controllerAssegnazioneTurni.creaTurno(c), HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/utente_id={idUtente}")
    public ResponseEntity<?> leggiTurniUtente(@PathVariable Long idUtente) throws ParseException {
        if (idUtente != null) {
            Set <AssegnazioneTurnoDTO> c = controllerAssegnazioneTurni.leggiTurniUtente(idUtente);
            if (c == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>( c, HttpStatus.FOUND);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }



    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> leggiTurni() throws ParseException {
        Set<AssegnazioneTurnoDTO> tuttiITurni = controllerAssegnazioneTurni.leggiTurni();
        return new ResponseEntity<>(tuttiITurni, HttpStatus.FOUND);
    }


}
