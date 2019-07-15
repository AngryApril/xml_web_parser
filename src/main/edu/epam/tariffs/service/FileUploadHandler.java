package main.edu.epam.tariffs.service;

import java.io.*;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.edu.epam.tariffs.entity.tariff.Tariff;
import main.edu.epam.tariffs.util.XMLValidator;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;


public class FileUploadHandler extends HttpServlet {

    private static final Logger logger = Logger.getLogger(FileUploadHandler.class);

    private static final String NO_FILE_UPLOADED_MESSAGE = "No File Uploaded";
    private static final String INVALID_XML_FILE_MESSAGE = "Invalid XML-file";
    private static final String VALID_XML_FILE_MESSAGE = "Congratulations! XML-file is valid!";
    private final static String XSD_SCHEMA_FILE_PATH = "schema.xsd";
    private static final String MAIN_PAGE_LINK = "/index.jsp";
    private static final String MAIN_PAGE_LINK_NAME = "BACK TO MAIN PAGE";
    private static final String PARSER_PAGE_LINK = "/parsers.jsp";
    private static final String PARSER_PAGE_LINK_NAME = "Choose parser";

    private boolean isMultipart;
    private String filePath;
    private int maxFileSize = 50 * 1024;
    private int maxMemSize = 4 * 1024;
    private File file ;

    public void init( ){
        // Get the file location where it would be stored.
        filePath = getServletContext().getInitParameter("file-upload");
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, java.io.IOException {


        try{
            // Check that we have a file upload request
            isMultipart = ServletFileUpload.isMultipartContent(request);

            if( !isMultipart ) {
                printHTMLpage(request, response, NO_FILE_UPLOADED_MESSAGE, MAIN_PAGE_LINK, MAIN_PAGE_LINK_NAME);
                return;
            }

            response.setContentType("text/html");
            java.io.PrintWriter out = response.getWriter( );
            String fileName;

            DiskFileItemFactory factory = new DiskFileItemFactory();

            // maximum size that will be stored in memory
            factory.setSizeThreshold(maxMemSize);

            // Location to save data that is larger than maxMemSize.
            factory.setRepository(new File(""));

            // Create a new file upload handler
            ServletFileUpload upload = new ServletFileUpload(factory);

            // maximum file size to be uploaded.
            upload.setSizeMax( maxFileSize );

            try {
                // Parse the request to get file items.
                List fileItems = upload.parseRequest(request);

                // Process the uploaded file items
                Iterator i = fileItems.iterator();

                out.println("<html>");
                out.println("<head>");
                out.println("<title>Servlet upload</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<div align = center>");

                while ( i.hasNext () ) {
                    FileItem fi = (FileItem)i.next();
                    if ( !fi.isFormField () ) {
                        // Get the uploaded file parameters
                        fileName = fi.getName();

                        // Write the file
                        if( fileName.lastIndexOf("\\") >= 0 ) {
                            file = new File( filePath + fileName.substring( fileName.lastIndexOf("\\"))) ;
                        } else {
                            file = new File( filePath + fileName.substring(fileName.lastIndexOf("\\")+1)) ;
                        }
                        fi.write( file ) ;
                        out.println("Uploaded File: " + fileName + "<br>");
                    }
                }

                XMLValidator xmlValidator = new XMLValidator();
                boolean isXMLFileValid = xmlValidator.validateXMLFIle(file.getPath(), filePath + XSD_SCHEMA_FILE_PATH);
                if(!isXMLFileValid){
                    printHTMLpage(request, response, INVALID_XML_FILE_MESSAGE, MAIN_PAGE_LINK, MAIN_PAGE_LINK_NAME);
                    return;
                }

                out.println("</div>");
                out.println("</body>");
                out.println("</html>");

                ParsersHandler.setFilePath(file.getPath());
                printHTMLpage(request, response, VALID_XML_FILE_MESSAGE, PARSER_PAGE_LINK, PARSER_PAGE_LINK_NAME);

        }catch (FileNotFoundException e){
            logger.error(e.getMessage(), e);
            printHTMLpage(request, response, NO_FILE_UPLOADED_MESSAGE, MAIN_PAGE_LINK, MAIN_PAGE_LINK_NAME);

        }

        } catch(Exception ex) {
            logger.error(ex);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("index.jsp");
        requestDispatcher.forward(req, resp);
    }

    public void printHTMLpage(HttpServletRequest request, HttpServletResponse response, String message,
                              String link, String linkName){

        response.setContentType("text/html");
        try {
            PrintWriter out = response.getWriter();

            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet upload</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<div align = center>");
            out.println(message);
            out.println("<br><br>");
            out.println("<a href = " + link + ">" + linkName + "</a></div>");
            out.println("</body>");
            out.println("</html>");

            out.close();

        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }

    }

}