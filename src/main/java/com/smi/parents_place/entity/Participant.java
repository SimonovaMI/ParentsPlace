package com.smi.parents_place.entity;


import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.Optional;

@Entity
@Table(name = "participants")
public class Participant {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "participants_id_seq")
    @SequenceGenerator(name = "participants_id_seq", sequenceName = "participants_id_seq", allocationSize = 1)
    @Column(name = "id")
    private long id;
    @Column(name = "name")
    @NotBlank(message = "Name can't be blank.")
    private String name;
    @Column(name = "surname")
    @NotBlank(message = "Surname can't be blank.")
    private String surname;
    @Column(name = "birthdate")
    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDate birthdate;
    @ManyToOne
    @JoinColumn(name = "edu_group_id")
    @NotNull(message = "Group can't be null.")
    private Group group;
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    @NotNull(message = "Status can't be null.")
    private Status status;
    @ManyToOne
    @JoinColumn(name = "parent1_id")
    private Participant firstParent;
    @ManyToOne
    @JoinColumn(name = "parent2_id")
    private Participant secondParent;
    @Column(name = "phone_number")
    @Pattern(regexp = "(\\+7|8)[0-9]{10}",
            message = "Phone number should start with either +7 or 8 and have 9 numbers in the range from 0 till 9.")
    private String phoneNumber;
    @Column(name = "email")
    @Email(message = "Incorrect email.")
    private String email;

    public Participant() {
    }

    public Participant(String name, String surname, LocalDate birthdate, Group group, Status status, String phoneNumber, String email) {
        this.name = name;
        this.surname = surname;
        this.birthdate = birthdate;
        this.group = group;
        this.status = status;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public Participant(String name, String surname, LocalDate birthdate, Group group, Status status, Participant firstParent, Participant secondParent, String phoneNumber, String email) {
        this.name = name;
        this.surname = surname;
        this.birthdate = birthdate;
        this.group = group;
        this.status = status;
        this.firstParent = firstParent;
        this.secondParent = secondParent;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public Participant(long id, String name, String surname, LocalDate birthdate, Group group, Status status, Participant firstParent, Participant secondParent, String phoneNumber, String email) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.birthdate = birthdate;
        this.group = group;
        this.status = status;
        this.firstParent = firstParent;
        this.secondParent = secondParent;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Participant getFirstParent() {
        return firstParent;
    }

    public void setFirstParent(Participant firstParent) {
        this.firstParent = firstParent;
    }

    public Participant getSecondParent() {
        return secondParent;
    }

    public void setSecondParent(Participant secondParent) {
        this.secondParent = secondParent;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        Optional<Participant> participantParent1 = Optional.of(firstParent);
        Optional<Participant> participantParent2 = Optional.of(secondParent);
        StringBuilder participantInfo = new StringBuilder("Participant{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", birthdate=" + birthdate +
                ", group=" + group +
                ", status=" + status +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'');
           /* if(!participantParent1.isEmpty() && participantParent2.isEmpty()){
                participantInfo.append(", firstParent=" + firstParent.name + '}');
            }else {
                if(!participantParent1.isEmpty() && !participantParent2.isEmpty()){
                    participantInfo.append( ", firstParent=" + firstParent.name +
                            ", secondParent=" + secondParent.name + '}');
                }
        }*/
            return participantInfo.toString();
    }
}
