package org.cswteams.ms3.entity;

import lombok.Data;
import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Questa classe modella richieste di rimozione da un turno da parte di utenti ad essi assegnati.
 * Gli utenti richiedenti possono fornire una motivazione/descrizione tramite l'apposito attributo.
 */
@Entity
@Data
public class RichiestaRimozioneDaTurno {
    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "assegnazioneturno_id")
    private AssegnazioneTurno assegnazioneTurno;

    /**
     * Utente richiedente.
     */
    @OneToOne
    private Utente utente;

    /**
     * Eventuale descrizione della motivazione della richiesta.
     */
    private String descrizione;

    public RichiestaRimozioneDaTurno() {
    }

    public RichiestaRimozioneDaTurno(@NotNull AssegnazioneTurno assegnazioneTurno, @NotNull Utente utente, String descrizione) {
        this.assegnazioneTurno = assegnazioneTurno;
        this.utente = utente;
        this.descrizione = descrizione;
    }
}
