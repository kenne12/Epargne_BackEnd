package com.projet.epargne.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VersementRequest {
    private Long idVersement;
    private double montant;
    @Temporal(TemporalType.DATE)
    @Column(nullable = true)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date date;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = true)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date heure;
    private int idclient;
}
