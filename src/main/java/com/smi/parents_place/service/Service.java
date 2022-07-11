package com.smi.parents_place.service;


import com.smi.parents_place.entity.Participant;


import java.util.List;

public interface Service {

    List<Participant> getAllParticipants();

    void addNewParticipant(Participant participant);


    Participant getParticipant(long id);

    void deleteParticipant(long id);
}
