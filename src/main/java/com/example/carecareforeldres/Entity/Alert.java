package com.example.carecareforeldres.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Alert implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idalert")
    private Integer idalert;
    @Column(name = "datedalert")
    private LocalDate datedalert;
    @Column(name = "message")
    private String message;
    @ManyToOne(cascade = CascadeType.ALL)

    Patient patient;
    @ManyToMany(cascade = CascadeType.ALL)

    List<Medecin>medecinssy=new ArrayList<>();



}
