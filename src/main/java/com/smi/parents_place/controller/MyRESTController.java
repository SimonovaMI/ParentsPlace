package com.smi.parents_place.controller;

import com.smi.parents_place.entity.Participant;
import com.smi.parents_place.exeption_handling.NoSuchParticipantException;
import com.smi.parents_place.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@Validated
public class MyRESTController {

    @Autowired
    private Service service;

    @GetMapping("/participants")
    public List<Participant> showAllParticipants() {
        return service.getAllParticipants();
    }

    @GetMapping("/participants/{id}")
    public Participant getParticipant(@PathVariable long id) {
        if (service.getParticipant(id) == null) {
            throw new NoSuchParticipantException("Participant with ID " + id + " doesn't exist.");
        }
        return service.getParticipant(id);
    }

    @PostMapping("/participants")
    public Participant addNewParticipant(@Valid @RequestBody Participant participant) {
        service.addNewParticipant(participant);
        return participant;
    }

    @PutMapping("/participants")
    public Participant updateParticipant(@Valid @RequestBody Participant participant) {
        if (participant == null) {
            throw new NoSuchParticipantException("Participant can't be null.");
        }
        Optional<Participant> optionalParticipant = Optional.ofNullable(service.getParticipant(participant.getId()));
        if (optionalParticipant.isEmpty()) {
            throw new NoSuchParticipantException("Participant with ID " + participant.getId() + " doesn't exist");
        }
        service.addNewParticipant(participant);
        return participant;
    }

    @DeleteMapping("/participants/{id}")
    public String deleteParticipant(@PathVariable long id) {
        if (service.getParticipant(id) == null) {
            throw new NoSuchParticipantException("Participant with ID " + id + " doesn't exist.");
        }
        service.deleteParticipant(id);
        return "Participant was deleted with id = " + id;
    }
}
