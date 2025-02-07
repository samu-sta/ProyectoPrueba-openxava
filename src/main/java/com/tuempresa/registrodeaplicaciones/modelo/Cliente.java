package com.tuempresa.registrodeaplicaciones.modelo;

import java.util.*;

import javax.persistence.*;

import org.openxava.annotations.*;

import com.tuempresa.registrodeaplicaciones.acciones.*;

import lombok.*;

@Entity @Getter @Setter
@Views({
    @View(name="Simple", members="codigo; denominacion; email"),
    @View(members="codigo; denominacion; email; aplicaciones")
})
public class Cliente {
	
	@Id
	@Column(length=10)
	private String codigo;
	
	@Column(length=50)
	private String denominacion;

	@Column(length=50)
	private String email;

	@ManyToMany(fetch=FetchType.LAZY)
    @JoinTable(
        name="CLIENTE_APLICACION",
        joinColumns=@JoinColumn(name="cliente_id"),
        inverseJoinColumns=@JoinColumn(name="aplicacion_id")
    )
	private Collection<Aplicacion> aplicaciones;

	@PrePersist
    private void onPrePersist() {
        try {
            BienvenidaClienteAccion accion = new BienvenidaClienteAccion();
            accion.setCliente(this);
            accion.execute();
        } catch (Exception e) {
            // Log error
        }
    }

    @OneToMany(mappedBy="cliente")
    private Collection<Licencia> licencias;
}
