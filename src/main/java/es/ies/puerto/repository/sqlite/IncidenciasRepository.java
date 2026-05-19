package es.ies.puerto.repository.sqlite;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import es.ies.puerto.modelo.Incidencias;
import es.ies.puerto.repository.IIncidenciasRepository;

public class IncidenciasRepository implements IIncidenciasRepository{
    
    public IncidenciasRepository(){
        super();
    }

    @Override
    public boolean save(Incidencias incidencia) {
        try (Connection connection = getConnection();
            PreparedStatement sentencia = connection.prepareStatement("INSERT INTO incidencias (id, id_usuario, asunto, descripcion, fecha, estado) VALUES (?,?,?,?,?,?)")){
            sentencia.setInt(1, incidencia.getIdIncidencia());
            sentencia.setInt(2, incidencia.getId());
            sentencia.setString(3, incidencia.getAsunto());
            sentencia.setString(4, incidencia.getDescripcion());
            sentencia.setString(5, incidencia.getFecha());
            sentencia.setString(6, incidencia.getAsunto());
            return sentencia.executeUpdate() > 0;
        } catch (Exception e) {
            System.err.println("Error creando incidencia");
            return false;
        }
    }

    @Override
    public boolean update(Incidencias incidencia) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public Incidencias findById(int idIncidencia) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

    @Override
    public List<Incidencias> findAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }

    @Override
    public boolean delete(int idIncidencia) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

}
