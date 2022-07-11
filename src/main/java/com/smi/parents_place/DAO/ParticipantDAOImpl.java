package com.smi.parents_place.DAO;

import com.smi.parents_place.entity.Participant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;


@Repository
public class ParticipantDAOImpl implements ParticipantDAO {

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Participant> getParticipants() {
        Query query = entityManager.createQuery("from Participant");
        List<Participant> participants = query.getResultList();
        return participants;
    }

    @Transactional
    @Override
    public void addNewParticipant(Participant participant) {
        Participant newParticipant = entityManager.merge(participant);
        participant.setId(newParticipant.getId());
    }

    @Override
    public Participant getParticipant(long id) {
        return entityManager.find(Participant.class, id);
    }

    @Transactional
    @Override
    public void deleteParticipant(long id) {
        Query query = entityManager.createQuery("delete from Participant" +
                " where id=:id");
        query.setParameter("id", id);
        query.executeUpdate();
    }
}
