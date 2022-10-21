package cutoff.controller;

import cutoff.model.CurrencyCutoffTime;
import cutoff.model.CutoffTimeUpdates;
import cutoff.service.CutoffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

import static cutoff.service.CutoffService.NOT_AVAILABLE;

@RestController
@RequestMapping("/cutoff")
public class CutoffController {
    @Autowired
    CutoffService service;

    @GetMapping("")
    public ResponseEntity<String> getCutOff(@RequestParam String pair, @RequestParam String date) throws SQLException {
        String body = service.getCutoffTime(pair, date);
        HttpStatus status = NOT_AVAILABLE.equals(body) ? HttpStatus.NO_CONTENT : HttpStatus.OK;
        return new ResponseEntity<>(body, status);
    }

    @PostMapping("")
    public ResponseEntity<String> setCutOff(@RequestBody CutoffTimeUpdates cutoffTimeUpdates) throws SQLException {
        List<CurrencyCutoffTime> updates = cutoffTimeUpdates.getUpdates();
        service.setCutoffTime(updates);
        return new ResponseEntity<String>("OK", HttpStatus.OK);
    }
}
