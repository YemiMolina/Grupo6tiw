package es.uc3m.tiw.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Imagenes
 */
@WebServlet("/ServletImagenes")
public class ServletImagenes extends HttpServlet {
    private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletImagenes() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
           ServletContext sc = getServletContext();
           File file=null;
           
           String foto=(String)
                   request.getParameter("foto");
           if(foto==null || foto.length()==0){
               
               //file=new File("icono.jpg");
               InputStream in;
               in = this.getClass().getResourceAsStream("/icono.jpg");
               response.setContentType("image/jpeg");
               response.setContentLength(24576);
               int i = 0;
               
               OutputStream out = response.getOutputStream();
               
               
               byte[] buf = new byte[1024];
               int count = 0;
               while ((count = in.read(buf)) >= 0) {
                   out.write(buf, 0, count);}
               in.close();
               out.close();
               
           }else{

               file=new File("images"+ foto);
               
               Path path= Paths.get("images"+ foto);
               response.setContentType(Files.probeContentType(path));
               response.setContentLength((int)file.length());

               // Open the file and output streams
               FileInputStream in = new FileInputStream(file);
               OutputStream out = response.getOutputStream();
            
               // Copy the contents of the file to the output stream
               byte[] buf = new byte[1024];
               int count = 0;
               while ((count = in.read(buf)) >= 0) {
                   out.write(buf, 0, count);}
               in.close();
               out.close();
           }

          
       
        
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
    }

}