package main.edu.epam.tariffs.service;

import main.edu.epam.tariffs.entity.Tariffs;
import main.edu.epam.tariffs.entity.tariff.InternetIncludedTariff;
import main.edu.epam.tariffs.entity.tariff.RoamingTariff;
import main.edu.epam.tariffs.entity.tariff.Tariff;
import main.edu.epam.tariffs.exceptions.IncorrectFileException;
import main.edu.epam.tariffs.exceptions.XMLParserException;
import main.edu.epam.tariffs.parser.stax.StAXParser;
import main.edu.epam.tariffs.parser.dom.DOMParser;
import main.edu.epam.tariffs.parser.sax.SAXParser;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class ParsersHandler extends HttpServlet {
    private static final Logger logger = Logger.getLogger(ParsersHandler.class);

    private static String filePath;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("parsers.jsp");
        requestDispatcher.forward(req, resp);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response){

        response.setContentType("text/html");
        Tariffs tariffs = null;
        String parser = request.getParameter("parser");

        try{
            switch (parser){
                case "DOM": {
                    tariffs = new DOMParser().parse(filePath);
                    break;
                }
                case "SAX": {
                    tariffs = new SAXParser().parse(filePath);
                    break;
                }
                case "StAX": {
                    tariffs = new StAXParser().parse(filePath);
                    break;
                }

                default: logger.info("Incorrect Parser type " + parser);
            }
        }catch (IncorrectFileException e){
            logger.error(e.getMessage(), e);
        }catch (XMLParserException xe){
            logger.error(xe.getMessage(), xe);
        }

        printTariffsToTable(request, response, tariffs);

    }

    public static String getFilePath() {
        return filePath;
    }

    public static void setFilePath(String filePath) {
        ParsersHandler.filePath = filePath;
    }

    public void printTariffsToTable(HttpServletRequest request, HttpServletResponse response, Tariffs tariffs){

        StringBuilder row;
        ArrayList<String> strings = new ArrayList<>();

        try{
            java.io.PrintWriter out = response.getWriter();

            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet upload</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<div align = center>");
            out.println("Tariffs");
            out.println("</div>");
            out.println("<table border=2 name=Tariffs>");
            out.println("<tr><th>TariffName</th><th>TariffID</th><th>Operator</th>" +
                    "<th>InnerCallPrice</th><th>OuterCallPrice</th><th>CityCallPrice</th><th>Payroll</th>" +
                    "<th>SMS Price</th><th>FavoriteNumbersAvailable</th><th>Tariffication</th>" +
                    "<th>ConnectPrice</th><th>GigaByteCount</th><th>internationalCallPrice</th></tr>");

            for(Tariff tariff : tariffs.getTariffList()){

                logger.info(tariff.toString());
                row = new StringBuilder();
                row.append("<tr>");
                row.append("<td>");
                row.append(tariff.getTariffName());
                row.append("</td><td>");
                row.append(tariff.getTariffID());
                row.append("</td><td>");
                row.append(tariff.getOperator().toString());
                row.append("</td><td>");
                row.append(tariff.getCallPrices().getInnerCallPrice().toString());
                row.append("</td><td>");
                row.append(tariff.getCallPrices().getOuterCallPrice().toString());
                row.append("</td><td>");
                row.append(tariff.getCallPrices().getCityCallPrice().toString());
                row.append("</td><td>");
                row.append(tariff.getPayroll().toString());
                row.append("</td><td>");
                row.append(tariff.getSmsPrice().toString());
                row.append("</td><td>");
                row.append(tariff.getParameters().getFavoriteNumbersAvailable().toString());
                row.append("</td><td>");
                row.append(tariff.getParameters().getTariffication().toString());
                row.append("</td><td>");
                row.append(tariff.getParameters().getConnectPrice().toString());
                row.append("</td>");
                if(tariff instanceof InternetIncludedTariff){
                    row.append("<td>");
                    row.append(((InternetIncludedTariff) tariff).getGigaByteCount());
                    row.append("</td><td></td>");
                }
                else if( tariff instanceof RoamingTariff){
                    row.append("<td>");
                    row.append(((RoamingTariff) tariff).getGigaByteCount());
                    row.append("</td><td>");
                    row.append(((RoamingTariff) tariff).getInternationalCallPrice());
                    row.append("</td>");
                }
                else {
                    row.append("<td></td><td></td>");
                }
                row.append("</tr>");
                strings.add(row.toString());
            }
            for(String string : strings){
                out.println(string);
            }
            out.println("</table>");

            out.println("<br><br>");
            out.println("<a href = parsers.jsp>BACK TO PARSERS</a></div>");
            out.println("<br><br>");
            out.println("<a href = index.jsp>BACK TO MAIN PAGE</a></div>");
            out.println("</body>");
            out.println("</html>");
            out.close();
        }
        catch (IOException e){
            logger.error(e.getMessage(), e);
        }

    }
}
