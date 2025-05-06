package com.sunpulse.api.registro;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table (name = "registros")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Registro {

    @Id
    @Column(name = "id_registro")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "temperatura")
    private Double temperatura;

    @Column(name = "fecha")
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
    private LocalDateTime fecha;





//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "username")
//    private User user;



}
