package com.UdeA.Ciclo3.repo;

import com.UdeA.Ciclo3.modelos.MovimientoDinero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface MovimientosRepository extends JpaRepository<MovimientoDinero, Integer> {
    //Metodo para filtrar movimientos por empleado
    @Query(value ="select * from movimientos where empleado_id= ?1", nativeQuery = true)
    public abstract ArrayList<MovimientoDinero> findByEmpleado(Integer id);

    //Metodo para filtrar movimientos por empresa
    @Query(value="select * from movimientos where empleado_id in (select id from empleado where empresa_id= ?1)", nativeQuery = true)
    public abstract ArrayList<MovimientoDinero> findByEmpresa(Integer id);


    //suma movieento por movimiento
    @Query(value = "SELECT SUM(monto) FROM movimientos", nativeQuery = true)
    public abstract long SumarMonto();

    //por empleado
    @Query(value = "SELECT SUM(monto) FROM movimientos WHERE empleado_id=?1", nativeQuery = true)
    public abstract long MontosPorEmpleado(Integer id);

    //por emprea
    @Query(value = "select SUM(monto) from movimientos where empleado_id in (select id from empleado where empresa_id= ?1)", nativeQuery = true)
    public abstract long MontosPorEmpresa(Integer id);
}
