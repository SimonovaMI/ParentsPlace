package com.smi.parents_place;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.smi.parents_place.controller.MyRESTController;
import com.smi.parents_place.entity.Group;
import com.smi.parents_place.entity.Participant;
import com.smi.parents_place.entity.Status;
import com.smi.parents_place.exeption_handling.NoSuchParticipantException;
import com.smi.parents_place.service.ServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(MyRESTController.class)
class MyRestControllerTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper mapper;
    @MockBean
    MyRESTController controller;
    @MockBean
    ServiceImpl service;


    Group group = new Group(1L, "1 группа");


    Participant participantParent1 = new Participant(1L, "Катя", "Возникова",
            LocalDate.of(1996,2,15), group,
            Status.PARENT, null, null,
            "89046578945","katya@mail.ru");
    Participant participantParent2 = new Participant(2L, "Павел", "Возников",
            LocalDate.of(1994,5,19), group,
            Status.PARENT, null, null, "" +
            "+79679052345","pasha@mail.ru");
    Participant participantChild = new Participant(3L, "Полина", "Возникова",
            LocalDate.of(2019,3,20), group,
            Status.CHILD, participantParent1, participantParent2,
            null, null);

    @Test
    void showAllParticipants() throws Exception {
        List<Participant> participants = new ArrayList<>(Arrays.asList(participantParent1,
                participantParent2, participantChild));

        Mockito.when(controller.showAllParticipants()).thenReturn(participants);

    mockMvc.perform(MockMvcRequestBuilders
                    .get("/api/participants")
                    .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(3)))
            .andExpect(jsonPath("$[2].name").value("Полина"));


    }
    @Test
    void getParticipant() throws Exception {
        Mockito.when(controller.getParticipant(1L)).thenReturn(participantParent1);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/participants/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$.name").value("Катя"));
    }

    @Test
    void getParticipant_notFound() throws Exception {
        Mockito.when(controller.getParticipant(500L)).thenThrow(new NoSuchParticipantException("Participant with ID 500 doesn't exist."));;

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/participants/500")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(result ->
                        assertTrue(result.getResolvedException() instanceof NoSuchParticipantException))
                .andExpect(result ->
                        assertEquals("Participant with ID 500 doesn't exist.", result.getResolvedException().getMessage()));
    }




    @Test
    void addNewParticipant() throws Exception {
        Participant participant = new Participant("Андрей","Возников",
                LocalDate.of(2019,3,20), group, Status.CHILD,
                participantParent1,participantParent2, null, null);
        Mockito.when(controller.addNewParticipant(participant)).thenReturn(participant);

        MockHttpServletRequestBuilder mockRequest = post("/api/participants")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(participant));

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk());

    }



     /* @Test
      void updateParticipant() throws Exception {
          Participant participant = new Participant(1L, "Катя", "Возникова",
                  LocalDate.of(1996,3,15), group,
                  Status.PARENT, null, null,
                  "89046578945","katya@mail.ru");


          Mockito.when(service.getParticipant(participantParent1.getId())).thenReturn(participantParent1);
          Mockito.when(controller.addNewParticipant(participant)).thenReturn(participant);

          MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/aop/participants")
                  .contentType(MediaType.APPLICATION_JSON)
                  .accept(MediaType.APPLICATION_JSON)
                  .content(this.mapper.writeValueAsString(participant));

          mockMvc.perform(mockRequest)
                  .andExpect(status().isOk());
      }*/

    @Test
    void deleteParticipant() throws Exception {
        Mockito.when(controller.deleteParticipant(1L)).thenReturn("Participant was deleted with id = 1");

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/api/participants/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Participant was deleted with id = 1"));
    }

   @Test
    void deleteParticipant_notFound() throws Exception {
        Mockito.when(controller.deleteParticipant(500L)).thenThrow(new NoSuchParticipantException("Participant with ID 500 doesn't exist."));;

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/api/participants/500")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(result ->
                        assertTrue(result.getResolvedException() instanceof NoSuchParticipantException))
                .andExpect(result ->
                        assertEquals("Participant with ID 500 doesn't exist.", result.getResolvedException().getMessage()));
    }


}
