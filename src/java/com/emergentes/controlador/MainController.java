
package com.emergentes.controlador;

import com.emergentes.modelo.peliculas;
import com.emergentes.utiles.conexionbd;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "MainController", urlPatterns = {"/MainController"})
public class MainController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            
        String op;
        op = (request.getParameter("op") != null) ? request.getParameter("op") : "list";

        ArrayList<peliculas> lista = new ArrayList<peliculas>();

        conexionbd canal = new conexionbd();
        Connection conn = canal.conectar();

        PreparedStatement ps;
        ResultSet rs;

        if (op.equals("list")) {
            try {
                String sql = "SELECT * from peliculas";
                ps = conn.prepareStatement(sql);
                rs = ps.executeQuery();

                while (rs.next()) {
                    peliculas peli = new peliculas();
                    peli.setId(rs.getInt("id"));
                    peli.setFecha(rs.getString("fecha"));
                    peli.setTitulo(rs.getString("titulo"));
                    peli.setContenido(rs.getString("contenido"));
                    peli.setAutor(rs.getString("autor"));

                    lista.add(peli);
                }
                request.setAttribute("lista", lista);
                request.getRequestDispatcher("index.jsp").forward(request, response);
            } catch (SQLException ex) {
                System.out.println("Error en SQl " + ex.getMessage());
            } finally {
                canal.desconectar();
            }
        }
        
        if (op.equals("nuevo")) {
            peliculas l = new peliculas();
            request.setAttribute("peliculas", l);
            request.getRequestDispatcher("editar.jsp").forward(request, response);
        }
        if(op.equals("editar")){
             
            try{
               
                int id = Integer.parseInt(request.getParameter(("id")));
                String sql ="SELECT * from peliculas where id=?";
                ps = conn.prepareStatement(sql);
                ps.setInt(1, id);
                
                rs=ps.executeQuery();
                
                peliculas li = new peliculas();
                
                while(rs.next()){
                 li.setId(rs.getInt("id"));
                 li.setFecha(rs.getString("fecha"));
                 li.setTitulo(rs.getString("titulo"));
                 li.setContenido(rs.getString("contenido"));
                 li.setAutor(rs.getString("autor"));
                }
            request.setAttribute("peliculas", li);
            request.getRequestDispatcher("editar.jsp").forward(request, response);
            }catch(SQLException ex){
                System.out.println("Error en SQL"+ex.getMessage());
            }
        }
        if (op.equals("eliminar")) {
            
            int id = Integer.parseInt(request.getParameter(("id")));
            try {
           
                String sql = "DELETE from peliculas where id = ?";
                ps = conn.prepareStatement(sql);
                ps.setInt(1, id);

                ps.executeUpdate();
            } catch (SQLException ex) {
                System.out.println("Error de SQL " + ex.getMessage());
            } finally {
                canal.desconectar();
            }
            response.sendRedirect("MainController");
        }
       
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                int id = Integer.parseInt(request.getParameter("id"));
        String isbn = request.getParameter("fecha");
        String titulo = request.getParameter("titulo");
        String categoria = request.getParameter("contenido");
        String autor = request.getParameter("autor");

        peliculas pe = new peliculas();
        pe.setId(id);
        pe.setFecha(isbn);
        pe.setTitulo(titulo);
        pe.setContenido(categoria);
        pe.setAutor(autor);

        conexionbd canal = new conexionbd();
        Connection conn = canal.conectar();
        PreparedStatement ps;
        ResultSet rs;

        if (id == 0) {
            String sql = "INSERT into peliculas (fecha, titulo, contenido, autor) values (?,?,?,?)";
            try {
                ps = conn.prepareStatement(sql);
                ps.setString(1, pe.getFecha());
                ps.setString(2, pe.getTitulo());
                ps.setString(3, pe.getContenido());
                ps.setString(4, pe.getAutor());
                
                ps.executeUpdate();
                
            } catch (SQLException ex) {
                System.out.println("Error de SQL "+ ex.getMessage());            
            } finally {
                canal.desconectar();
            }
            response.sendRedirect("MainController");
        }
        else{
            try {
                String sql ="UPDATE peliculas set fecha=?,titulo=?,contenido=?, autor=? where id=?";
                ps = conn.prepareStatement(sql);
                
                ps.setString(1, pe.getFecha());
                ps.setString(2, pe.getTitulo());
                ps.setString(3, pe.getContenido());
                ps.setString(4, pe.getAutor());
                ps.setInt(5, pe.getId());
                ps.executeUpdate();
            } catch (SQLException ex) {
                System.out.println("Error al Actualizar"+ex.getMessage());
            }
            response.sendRedirect("MainController");
            
        }
    }
      
    }
