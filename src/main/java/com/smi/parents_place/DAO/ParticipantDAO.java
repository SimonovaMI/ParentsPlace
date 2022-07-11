package com.smi.parents_place.DAO;

import com.smi.parents_place.entity.Participant;

import java.util.List;

public interface ParticipantDAO {
    List<Participant> getParticipants();

    void addNewParticipant(Participant participant);

    Participant getParticipant(long id);

    void deleteParticipant(long id);
}
