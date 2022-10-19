package cutoff.controller;

import cutoff.model.CurrencyCutoffTime;
import cutoff.model.CutoffTimeUpdates;
import cutoff.model.PairCutoffTime;
import cutoff.service.CutoffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cutoff")
public class CutoffController {
    @Autowired
    CutoffService service;

    @GetMapping("")
    public ResponseEntity<String> getCutOff(@RequestParam String pair, @RequestParam String date) {
        return new ResponseEntity<>(service.getCutoffTime(pair, date), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<String> setCutOff(@RequestBody CutoffTimeUpdates cutoffTimeUpdates) {
        List<CurrencyCutoffTime> updates = cutoffTimeUpdates.getUpdates();
        updates.forEach(
                ct -> service.setCutoffTime(ct)
        );
        return new ResponseEntity<String>("OK", HttpStatus.OK);
    }
}
