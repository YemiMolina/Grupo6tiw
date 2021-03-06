package es.uc3m.tiw.web;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import es.uc3m.tiw.model.Direccion;
import es.uc3m.tiw.model.Leccion;
//import es.uc3m.tiw.model.ROL;
import es.uc3m.tiw.model.Usuario;
import es.uc3m.tiw.model.daos.CursoDao;
import es.uc3m.tiw.model.daos.ICurso;
import es.uc3m.tiw.model.daos.IPersona;
import es.uc3m.tiw.model.Curso;

/**
 * Servlet implementation class PersistenceServletCursos
 */
@WebServlet("/PersistenceServletCursos")
@MultipartConfig

public class PersistenceServletCursos extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static List <Curso> Listacursos = new ArrayList<Curso>();   
	@PersistenceContext( unitName="Grupo6-model")
	EntityManager em;
	@Resource
	UserTransaction ut;
	ICurso dao;
    
    public static int contadorId=0;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    
    public PersistenceServletCursos() {
        super();
        // TODO Auto-generated constructor stub
    }
    public void init(ServletConfig config) throws ServletException {
    	dao = new CursoDao(em, ut);
    	// Always call super.init(config) first  (servlet mantra #1)
        super.init(config);
        //Curso curso3= new Curso("titulo3", "descripcion3", "dificultad3", 3, 3, "imagenuri3", 10, false, false, 0.3, 0.7, 0, 0);
       // Curso curso1= new Curso("curso1","descripcion1","dificultad1", 1, 20,130,10.0, );
        //Curso curso1 = new Curso("titulo1", "descripcion1", "dificultad1", 1, 1, "imagenUri1", 10,null);
       // Curso curso2= new Curso("curso2","descripcion2","dificultad2", 2, 20,131,10.0);
        //Listacursos.add(curso1);
        //Listacursos.add(curso3);
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
        Usuario usuActual= (Usuario)session.getAttribute("usuario");
        
		List<Curso> listaCursosBusqueda = null;
		String accion=(String) request.getParameter("action");
		String id=(String) request.getParameter("id");
		String redireccion="/Catalogo.jsp";
		Integer idInt=0;
		Long lidInt=0L;
		if(id!=null){
		idInt= Integer.valueOf(id);
		lidInt=Long.valueOf(id);// no usar este
		}
		if(accion!=null && accion.equals("listarCursos")){
				try {
					listaCursosBusqueda = dao.findAll();
				} catch (InstantiationException | IllegalAccessException
						| ClassNotFoundException | SQLException e) {
					e.printStackTrace();
				}
				PrintWriter out = response.getWriter();
				redireccion="/Catalogo.jsp";		
			}
			if(accion!=null && accion.equals("delete")){
				try {
					Curso cursoBorrar= dao.findById(idInt);
					dao.removeCurso(cursoBorrar);
				} catch (SQLException | IllegalStateException | SecurityException | NotSupportedException | SystemException | HeuristicMixedException | HeuristicRollbackException | RollbackException e) {
					
					e.printStackTrace();
				}try {
					listaCursosBusqueda=dao.findAll();
				} catch (InstantiationException | IllegalAccessException
						| ClassNotFoundException | SQLException e) {
					e.printStackTrace();
				}	
			}else if (accion!=null && accion.equals("modificar")){
				try {
					Curso cursoModificar2= dao.findById(idInt);
					request.setAttribute("CursoModificar", (Curso) cursoModificar2);
					redireccion="/Modificacion.jsp";
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}else {
				redireccion="/Catalogo.jsp";
			}
			request.setAttribute("Listacursos", getListaCursos());
		    request.setAttribute("usuario", usuActual);
			this.getServletConfig().getServletContext().getRequestDispatcher(redireccion).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			//para la persistencia de la sesion
			HttpSession session = request.getSession();
	        Usuario usuActual= (Usuario)session.getAttribute("usuario");
	        // TODO Auto-generated method stub
			String idcurso="";
			idcurso=request.getParameter("id");// ahora no hace nada
			 String promocion=request.getParameter("promocion");
			if(idcurso!=null&&promocion.equals("nulo")==false){
			}
			else{
				String titulo =request.getParameter("titulo");
				String descripcion =request.getParameter("descripcion");
				String dificultad =request.getParameter("dificultad");
				int numeroh =Integer.valueOf(request.getParameter("numeroh"));
				double precio= Double.valueOf(request.getParameter("precio"));
				Part filePart = request.getPart("imagenuri");
				String uri= guardarImagen(filePart);

				//Para poder realizar el descuento
				String descuento = request.getParameter("descuento");
				Double tipoDescuento = null;
				if(descuento.equals("fijo")){
					tipoDescuento=10.0;
				}else if(descuento.equals("variable")) {
					tipoDescuento=precio*0.10;
				}else if (descuento.equals("ninguno")){
					tipoDescuento=0.0;
				}
				if(idcurso!=null){// aqui entrasa si hay un curso creado ya por lo tanto es una modificacion
					String id=(String) request.getParameter("id");
					Integer idInt=0;	
					if(id!=null){
						idInt= Integer.valueOf(id);
						}
					try {
						Curso cursoModificar= dao.findById(idInt);
						cursoModificar.setTitulo(titulo);
						cursoModificar.setDescripcion(descripcion);
						cursoModificar.setDificultad(dificultad);
						cursoModificar.setImagenuri(uri);
						cursoModificar.setNumeroh(numeroh);
						cursoModificar.setPrecio(precio);
						cursoModificar.setDescuento(tipoDescuento);
						cursoModificar.setDineroPortal(0);
						cursoModificar.setDineroProfesor(0);
						cursoModificar.setComisionPortal(0.3);
						cursoModificar.setComisionProfesor(0.7);
						//cursoModificar.setProfesor(usuActual);
						Curso cursoRecuperado=dao.createCurso(cursoModificar);// esto lo que va a hacer es llamar al metodo createCurso que detecta si ya esta creado el curso lo modifica para que el usuario pueda guardar los cambios que ha hecho
						
					} catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException | IllegalStateException | SecurityException | NotSupportedException | SystemException | HeuristicMixedException | HeuristicRollbackException | RollbackException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}		
				}else{
				Curso curso= new Curso();
				contadorId++;// no creo que haga falta preguntar a rocio
				curso.setTitulo(titulo);
				curso.setDescripcion(descripcion);
				curso.setDificultad(dificultad);
				curso.setImagenuri(uri);
				curso.setNumeroh(numeroh);
				curso.setPrecio(precio);
				curso.setDescuento(tipoDescuento);
				curso.setDineroPortal(0);
				curso.setDineroProfesor(0);
				curso.setComisionPortal(0.3);
				curso.setComisionProfesor(0.7);
				curso.setProfesor(usuActual);
				
	
				Leccion leccion1= new Leccion();
				leccion1.setMaterial("material16.32");
				leccion1.setCurso(curso);
				leccion1.setDescripcion("descripcion2.1.1");
				leccion1.setIdentificador(0);
				curso.setLeccion(leccion1);

				try {
					dao.createCurso(curso);
					
				} catch (InstantiationException | IllegalAccessException
						| ClassNotFoundException | IllegalStateException
						| SecurityException | SQLException | NotSupportedException
						| SystemException | HeuristicMixedException
						| HeuristicRollbackException | RollbackException e) {
					
					e.printStackTrace();
				}
				response.sendRedirect("/Grupo6-web/PersistenceServletCursos");// 
				}
			
			}
			request.setAttribute("usuario", usuActual);
			//this.getServletConfig().getServletContext().getRequestDispatcher(redireccion).forward(request, response);
		}
		
	
public static String guardarImagen(Part filePart){
		
		Date date = new Date();
		String archivoNombre ="";
		
		if (filePart!=null && filePart.getSize()!= 0){
			archivoNombre= "imagen" + date.getTime();
		//donde se guardan las imagenes
		//lo va leyendo del Part y lo guarda en un lugar del disco
		try {
			FileOutputStream outputStream = new FileOutputStream("/home/tiw/fotos/" + archivoNombre);
			
			int read = 0;
			InputStream inputStream =filePart.getInputStream();
			byte[] bytes = new byte[1024];

			while ((read = inputStream.read(bytes)) != -1) {
				outputStream.write(bytes, 0, read);
			}
			
		} catch (FileNotFoundException e) {
		
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}}
		
		return archivoNombre;
	}

	
	public List <Curso> getListaCursos(){// solo quiero que me devuelva la lista de cursos
		List<Curso> ListaCursos=null;
		try {
			ListaCursos = dao.findAll();
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ListaCursos ;
	}
	
	public Curso ponerPromocion(Integer id, double descuentoPromocion){// este metodo lo q hace es  o poner el descuento po r promocion que hace el admin sobre un determinado curso 
		String idcurso="";
		//idcurso=request.getParameter("id");// ahora no hace nada
		Curso cursoDevolver=null;

		
		if(idcurso!=null){// aqui entrasa si hay un curso creado ya por lo tanto es una modificacion
			//String id=(String) request.getParameter("id");
			Integer idInt=0;
			System.out.println("estas entrando dentro del apartado de promociones de dopost con titulo "+ id);
			if(id!=null){// no haria falta esto
				idInt= Integer.valueOf(id);
				
				}
			try {
				Curso cursoModificar= dao.findById(idInt);
				double precioCurso= cursoModificar.getPrecio();
				double rtdo=precioCurso*0.3;
				double descuentoReal=rtdo*descuentoPromocion/100;
				
				
				//cursoModificar.setDescuentoPromocion(descuentoReal);
			
				Curso cursoRecuperado=dao.createCurso(cursoModificar);// esto lo que va a hacer es llamar al metodo createCurso que detecta si ya esta creado el curso lo modifica para que el usuario pueda guardar los cambios que ha hecho
				System.out.println(" (dopost debajo de sets )titulo del curso  "+cursoRecuperado.getTitulo()+ " con descripcion "+cursoRecuperado.getDescripcion());
				cursoDevolver=cursoRecuperado;
			} catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException | IllegalStateException | SecurityException | NotSupportedException | SystemException | HeuristicMixedException | HeuristicRollbackException | RollbackException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				}
			}
			return cursoDevolver;

		}
	

}
