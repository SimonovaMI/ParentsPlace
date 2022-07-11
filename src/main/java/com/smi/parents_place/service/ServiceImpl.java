package com.smi.parents_place.service;

import com.smi.parents_place.DAO.ParticipantDAO;
import com.smi.parents_place.entity.Participant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ServiceImpl implements com.smi.parents_place.service.Service {
    @Autowired
    private ParticipantDAO participantDAO;

    @Transactional
    @Override
    public List<Participant> getAllParticipants() {
        return participantDAO.getParticipants();
    }


    public void addNewParticipant(Participant participant) {
        participantDAO.addNewParticipant(participant);
    }

    @Override
    public Participant getParticipant(long id) {
        Participant participant = participantDAO.getParticipant(id);
        return participant;
    }

    @Override
    public void deleteParticipant(long id) {
        participantDAO.deleteParticipant(id);
    }


}
