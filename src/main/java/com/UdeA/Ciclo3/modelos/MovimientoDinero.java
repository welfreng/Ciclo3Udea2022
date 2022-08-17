package com.UdeA.Ciclo3.modelos;

import javax.persistence.*;

@Entity
@Table(name = "MovimientoDinero")
public class MovimientoDinero {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    //private bigInteger monto;
    //private bigInteger monto;

}
